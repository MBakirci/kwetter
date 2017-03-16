package com.mehmet.kwetter.dao;

import com.mehmet.kwetter.model.User;

/**
 * Created by Mehmet on 3/10/2017.
 */
public interface UserDao extends IDao<User> {
    void followUser(Long followerId, Long toFollowId);

    void unFollowUser(Long followerId, Long toFollowId);

    User findByUsername(String username);
}
