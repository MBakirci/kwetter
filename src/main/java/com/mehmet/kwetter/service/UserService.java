package com.mehmet.kwetter.service;

import com.mehmet.kwetter.dao.TweetDao;
import com.mehmet.kwetter.dao.UserDao;
import com.mehmet.kwetter.exception.TweetNotFoundException;
import com.mehmet.kwetter.exception.UserAlreadyExcistException;
import com.mehmet.kwetter.exception.UserNotFoundException;
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

    @Inject
    private TweetDao tweetDao;

    public UserService() {
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public List<User> getUsers() {
        return userDao.findAll();
    }

    public User getUser(Long id) throws UserNotFoundException, TweetNotFoundException {
        return userDao.find(id);
    }

    public User getUser(String username) throws UserNotFoundException {
        return userDao.findByUsername(username);
    }

    public void createUser(User user) throws UserAlreadyExcistException {
        userDao.create(user);
    }

    public void updateUser(User user) throws UserNotFoundException {
        userDao.update(user);
    }

    public void removeUser(User user) throws UserNotFoundException, TweetNotFoundException {
        userDao.delete(user.getId());
    }

    public void followUser(Long followerId, Long toFollowId) throws UserNotFoundException {
        userDao.followUser(followerId, toFollowId);
    }


    public void unFollowUser(Long followerId, Long toFollowId) throws UserNotFoundException {
        userDao.unFollowUser(followerId, toFollowId);
    }

}
