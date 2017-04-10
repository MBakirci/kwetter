package com.mehmet.kwetter.rest;

import org.shipstone.swagger.integration.annotation.SwaggerUIConfiguration;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 * Created by Mehmet on 3/14/2017.
 */
@ApplicationPath("api")
@SwaggerUIConfiguration
public class RestApplication extends Application {

}