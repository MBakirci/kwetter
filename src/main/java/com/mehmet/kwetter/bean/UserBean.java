package com.mehmet.kwetter.bean;

import com.mehmet.kwetter.model.User;
import com.mehmet.kwetter.model.UserDetails;
import com.mehmet.kwetter.service.UserService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;

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
    private UserDetails userDetails;

    private String filter;

    public ArrayList<User> getUsers() {
//        if(!filter.isEmpty() && filter != null){
//            ArrayList<User> filtered =  new ArrayList<>();
//            for (User u: userService.getUsers()) {
//                if(u.getUserName().startsWith(filter)){
//                    filtered.add(u);
//                }
//            }
//            return filtered;
//        }
        return new ArrayList<>(userService.getUsers());
    }

    public void addUser() {
        User u = new User(userName, profilePicLocation, userDetails);
        userService.addUser(u);
    }


    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getProfilePicLocation() {
        return profilePicLocation;
    }

    public void setProfilePicLocation(String profilePicLocation) {
        this.profilePicLocation = profilePicLocation;
    }

    public UserDetails getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(UserDetails userDetails) {
        this.userDetails = userDetails;
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }
}
