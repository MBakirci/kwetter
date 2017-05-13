package com.mehmet.kwetter.dao;

import com.mehmet.kwetter.domain.RoleEnum;
import com.mehmet.kwetter.domain.User;
import com.mehmet.kwetter.exception.UserNotFoundException;

/**
 * Created by Mehmet on 3/10/2017.
 */
public interface UserDao extends IDao<User> {
    /**
     * @param followerId
     * @param toFollowId
     * @throws UserNotFoundException
     */
    void followUser(Long followerId, Long toFollowId) throws UserNotFoundException;

    /**
     * @param followerId
     * @param toFollowId
     * @throws UserNotFoundException
     */
    void unFollowUser(Long followerId, Long toFollowId) throws UserNotFoundException;


    /**
     * @param username String username of User
     * @return User object by username
     * @throws UserNotFoundException
     */
    User findByUsername(String username) throws UserNotFoundException;

    RoleEnum getUserPermission(String username);

    void setActivate(User user, boolean activate);

    void changeRole(User user, RoleEnum role);
}
