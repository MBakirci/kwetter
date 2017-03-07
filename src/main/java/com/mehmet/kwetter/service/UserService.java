package com.mehmet.kwetter.service;

import com.mehmet.kwetter.dao.UserDao;
import com.mehmet.kwetter.model.User;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

/**
 * Created by Mehmet on 3/1/2017.
 */
@ApplicationScoped
public class UserService {

    @Inject
    private UserDao userDao;

    public UserService() {
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    /**
     * Create and add an user. true when an username is not used. false when
     * username already in use
     *
     * @param user
     * @return true when username is not in use and everything got well. false
     * when username is in use
     */
    public boolean addUser(User user) {
        return userDao.create(user);
    }

    public List<User> getUsers() {
        return userDao.findAll();
    }

}
