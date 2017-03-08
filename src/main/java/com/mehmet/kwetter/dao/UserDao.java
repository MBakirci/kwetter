package com.mehmet.kwetter.dao;

import com.mehmet.kwetter.model.User;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by Mehmet on 3/1/2017.
 */
@ApplicationScoped
public class UserDao implements IDao<User, Long> {

    @PersistenceContext
    private EntityManager em;

    private String i;

    public UserDao() {
    }

    @Override
    public boolean create(User user) {
        try {
            em.persist(user);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public User find(Long id) {
        return em.find(User.class, id);
    }

    @Override
    public User update(User user) {
        return null;
    }

    @Override
    public void delete(User user) {
        em.remove(user);
    }

    @Override
    public List<User> findAll() {
        Query query = em.createQuery("SELECT u FROM com.mehmet.kwetter.model.User u");
        return query.getResultList();
    }

//  public User findByUserName(String username) {
//    return em.find(User.class, username);
//  }

}
