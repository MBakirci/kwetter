package com.mehmet.kwetter.dao.impl;

import com.mehmet.kwetter.dao.DaoFacade;
import com.mehmet.kwetter.dao.UserDao;
import com.mehmet.kwetter.domain.RoleEnum;
import com.mehmet.kwetter.domain.User;

import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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

    @PersistenceContext
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
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
    public RoleEnum getUserPermission(String username) {
        User user = (User) em.createNamedQuery("User.findByUsername").setParameter("username", username).getSingleResult();
        return user.getRole();
    }

    @Override
    public void setActivate(User user, boolean activate) {
        user.setActivated(activate);
        this.update(user);
    }

    @Override
    public void changeRole(User user, RoleEnum role) {
        user.setRole(role);
        this.update(user);
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
