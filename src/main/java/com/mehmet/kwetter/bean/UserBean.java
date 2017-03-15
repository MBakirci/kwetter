package com.mehmet.kwetter.bean;

import com.mehmet.kwetter.model.User;
import com.mehmet.kwetter.model.UserDetail;
import com.mehmet.kwetter.service.UserService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by Mehmet on 3/4/2017.
 */
@Named(value = "userBean")
@RequestScoped
public class UserBean {

    @Inject
    private UserService userService;



    private String userName;
    private String profilePicLocation;
    private UserDetail userDetails;

    private String filter;
}
