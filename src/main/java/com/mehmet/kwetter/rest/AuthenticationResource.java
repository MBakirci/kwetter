package com.mehmet.kwetter.rest;

import com.mehmet.kwetter.domain.LoginCredentials;
import com.mehmet.kwetter.domain.Token;
import com.mehmet.kwetter.exception.AuthenticationException;
import com.mehmet.kwetter.exception.NotActivatedException;
import com.mehmet.kwetter.exception.UserNotFoundException;
import com.mehmet.kwetter.service.AuthenticationService;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.math.BigInteger;
import java.security.SecureRandom;

/**
 * Created by mehmet on 3-5-17.
 */
@Path("/auth")
@Produces("application/json")
public class AuthenticationResource {

    @Inject
    AuthenticationService authenticationService;

    @POST
    @Consumes("application/json")
    public Response authenticateUser(LoginCredentials credentials) throws UserNotFoundException, NotActivatedException, AuthenticationException {

        // Authenticate the user using the credentials provided
        if (authenticate(credentials.getUsername(), credentials.getPassword())) {
            // Return the token on the response
            return Response.ok(issueToken(credentials.getUsername())).build();
        }
        return Response.ok(Response.Status.UNAUTHORIZED, MediaType.APPLICATION_JSON_TYPE).build();

    }

    private boolean authenticate(String username, String password) throws UserNotFoundException, AuthenticationException, NotActivatedException {
        return authenticationService.authenticateUser(username, password);
    }

    private Token issueToken(String username) {
        SecureRandom random = new SecureRandom();
        String token = new BigInteger(130, random).toString(32);
        return authenticationService.issueToken(username, token);
    }
}
