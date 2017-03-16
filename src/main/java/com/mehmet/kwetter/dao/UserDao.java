package com.mehmet.kwetter.dao;

import com.mehmet.kwetter.exception.UserNotFoundException;
import com.mehmet.kwetter.domain.User;

/**
 * Created by Mehmet on 3/10/2017.
 */
public interface UserDao extends IDao<User> {
    void followUser(Long followerId, Long toFollowId) throws UserNotFoundException;

    void unFollowUser(Long followerId, Long toFollowId) throws UserNotFoundException;

    User findByUsername(String username) throws UserNotFoundException;
}
