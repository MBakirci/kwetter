package com.mehmet.kwetter.filter;


import com.mehmet.kwetter.dao.Secured;
import com.mehmet.kwetter.domain.RoleEnum;
import com.mehmet.kwetter.exception.PermissionException;
import com.mehmet.kwetter.service.AuthorizationService;

import javax.annotation.Priority;
import javax.inject.Inject;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by mehmet on 3-5-17.
 */
@Secured
@Provider
@Priority(Priorities.AUTHORIZATION)
public class AuthorizationFilter implements ContainerRequestFilter {


    @Context
    private ResourceInfo resourceInfo;

    @Inject
    AuthorizationService authorizationService;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        // Get the resource class which matches with the requested URL
        // Extract the roles declared by it
        Class<?> resourceClass = resourceInfo.getResourceClass();
        List<RoleEnum> classRoles = extractRoles(resourceClass);

        // Get the resource method which matches with the requested URL
        // Extract the roles declared by it
        Method resourceMethod = resourceInfo.getResourceMethod();
        List<RoleEnum> methodRoles = extractRoles(resourceMethod);

        try {

            // Check if the user is allowed to execute the method
            // The method annotations override the class annotations
            if (methodRoles.isEmpty()) {
                checkPermissions(classRoles, requestContext);
            } else {
                checkPermissions(methodRoles, requestContext);
            }

        } catch (PermissionException e) {
            requestContext.abortWith(
                    Response.status(Response.Status.FORBIDDEN).build());
        }
    }

    // Extract the roles from the annotated element
    private List<RoleEnum> extractRoles(AnnotatedElement annotatedElement) {
        if (annotatedElement == null) {
            return new ArrayList<RoleEnum>();
        } else {
            Secured secured = annotatedElement.getAnnotation(Secured.class);
            if (secured == null) {
                return new ArrayList<RoleEnum>();
            } else {
                RoleEnum[] allowedRoles = secured.value();
                return Arrays.asList(allowedRoles);
            }
        }
    }

    private void checkPermissions(List<RoleEnum> allowedRoles, ContainerRequestContext requestContext) throws PermissionException {
        // Check if the user contains one of the allowed roles
        // Throw an Exception if the user has not permission to execute the method

        // Get username
        String username = requestContext.getSecurityContext().getUserPrincipal().getName();

        // Get role of user
        RoleEnum role = authorizationService.getUserPermission(username);

        if (!allowedRoles.contains(role)) throw new PermissionException("Role not allowed");
    }
}
