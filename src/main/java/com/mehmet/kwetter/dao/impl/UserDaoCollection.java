package com.mehmet.kwetter.dao.impl;

import com.mehmet.kwetter.dao.Collection;
import com.mehmet.kwetter.dao.UserDao;
import com.mehmet.kwetter.exception.UserAlreadyExcistException;
import com.mehmet.kwetter.exception.UserNotFoundException;
import com.mehmet.kwetter.domain.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by Mehmet on 3/16/2017.
 */
@Stateless
@Collection
public class UserDaoCollection implements UserDao {
    CopyOnWriteArrayList<User> users = new CopyOnWriteArrayList<>();

    @Override
    public List<User> findAll() {
        return new ArrayList<>(users);
    }

    @Override
    public User findByUsername(String username) throws UserNotFoundException {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        throw new UserNotFoundException();
    }

    @Override
    public User find(Object id) throws UserNotFoundException {
        for (User user : users) {
            if (user.getId() == (Long) id) {
                return user;
            }
        }
        throw new UserNotFoundException();
    }

    @Override
    public User create(User user) throws UserAlreadyExcistException {
        for (User userx : users) {
            if (userx.getUsername().equals(user.getUsername())) {
                throw new UserAlreadyExcistException();
            }
        }
        users.add(user);
        return user;
    }

    @Override
    public User update(User user) {
        return null;
    }

    @Override
    public void delete(Object id) throws UserNotFoundException {
        User user = find(id);
        if (user != null) {
            users.remove(user);
        }
    }

    @Override
    public void setEm(EntityManager em) {

    }

    @Override
    public void followUser(Long followerId, Long toFollowId) throws UserNotFoundException {
        User following = find(toFollowId);
        User follower = find(followerId);
        following.addFollower(follower);
    }

    @Override
    public void unFollowUser(Long followerId, Long toFollowId) throws UserNotFoundException {
        User following = find(toFollowId);
        User follower = find(followerId);
        following.removeFollower(follower);
    }
}
