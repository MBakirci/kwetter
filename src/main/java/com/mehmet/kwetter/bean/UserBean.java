package com.mehmet.kwetter.bean;

import com.mehmet.kwetter.domain.User;
import com.mehmet.kwetter.domain.UserDetail;
import com.mehmet.kwetter.exception.TweetNotFoundException;
import com.mehmet.kwetter.exception.UserNotFoundException;
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
    private User user;
    private String userName;

    public User getUser(Long id) throws UserNotFoundException, TweetNotFoundException {
        return user;
    }

    public void loadUser() throws UserNotFoundException {
        User u = userService.getUser(userName);
        this.setUser(u);
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
