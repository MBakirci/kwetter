package com.mehmet.kwetter.service;

import com.mehmet.kwetter.dao.TweetDao;
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

    public User getUser(Long id) {
        return userDao.find(id);
    }

    public void createUser(User user) {
        userDao.create(user);
    }

    public void updateUser(User user) {
        userDao.update(user);
    }

    public void removeUser(User user) {
        userDao.delete(user.getId());
    }

    public void followUser(Long followerId, Long toFollowId) {
        userDao.followUser(followerId, toFollowId);
    }


    public void unFollowUser(Long followerId, Long toFollowId) {
        userDao.unFollowUser(followerId, toFollowId);
    }
}
