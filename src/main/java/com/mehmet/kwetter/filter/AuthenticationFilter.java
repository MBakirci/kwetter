package com.mehmet.kwetter.filter;

import com.mehmet.kwetter.dao.Secured;
import com.mehmet.kwetter.exception.AuthenticationException;
import com.mehmet.kwetter.service.AuthState;

import javax.annotation.Priority;
import javax.inject.Inject;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.security.Principal;

/**
 * Created by mehmet on 3-5-17.
 */
@Secured
@Provider
@Priority(Priorities.AUTHENTICATION)
public class AuthenticationFilter implements ContainerRequestFilter {

    @Inject
    AuthState authState;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {


        // Get the HTTP Authorization header from the request
        String authorizationHeader =
                requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);

        // Check if the HTTP Authorization header is present and formatted correctly
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            throw new NotAuthorizedException("Authorization header must be provided");
        }

        // Extract the token from the HTTP Authorization header
        String token = authorizationHeader.substring("Bearer".length()).trim();

        // Overriding security context, so we can still use the securityContext to get the user
        overrideSecurityContext(requestContext, token);

        try {

            // Validate the token
            validateToken(token);

        } catch (AuthenticationException e) {
            requestContext.abortWith(
                    Response.status(Response.Status.UNAUTHORIZED).build());
        }
    }

    private void validateToken(String token) throws AuthenticationException {
        // Check if it was issued by the server and if it's not expired
        // Throw an Exception if the token is invalid
        if (!authState.isTokenValid(token)) throw new AuthenticationException("Token is not valid");
    }

    private void overrideSecurityContext(ContainerRequestContext requestContext, final String token) {

        requestContext.setSecurityContext(new SecurityContext() {
            @Override
            public Principal getUserPrincipal() {

                return new Principal() {

                    @Override
                    public String getName() {
                        try {
                            return authState.getUsernameByToken(token);
                        } catch (AuthenticationException e) {
                            e.printStackTrace();
                        }
                        return null;
                    }

                };
            }

            @Override
            public boolean isUserInRole(String s) {
                return true;
            }

            @Override
            public boolean isSecure() {
                return false;
            }

            @Override
            public String getAuthenticationScheme() {
                return null;
            }
        });

    }
}