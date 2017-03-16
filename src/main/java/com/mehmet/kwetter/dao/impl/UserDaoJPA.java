package com.mehmet.kwetter.dao.impl;

import com.mehmet.kwetter.dao.DaoFacade;
import com.mehmet.kwetter.dao.UserDao;
import com.mehmet.kwetter.model.User;

import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by Mehmet on 3/1/2017.
 */
@Stateless
@Default
public class UserDaoJPA extends DaoFacade<User> implements UserDao {

    public UserDaoJPA() {
        super(User.class);
    }

    @Override
    public List<User> findAll() {
        return em.createNamedQuery("User.findAll").getResultList();
    }

    @Override
    public User findByUsername(String username) {
        return (User) em.createNamedQuery("User.findByUsername").setParameter("username", username).getSingleResult();
    }

    @Override
    public void setEm(EntityManager em) {
        this.em = em;
    }

    @Override
    public void followUser(Long followerId, Long toFollowId) {
        User following = find(toFollowId);
        User follower = find(followerId);
        following.addFollower(follower);
    }

    @Override
    public void unFollowUser(Long followerId, Long toFollowId) {
        User following = find(toFollowId);
        User follower = find(followerId);
        following.removeFollower(follower);
    }
}
