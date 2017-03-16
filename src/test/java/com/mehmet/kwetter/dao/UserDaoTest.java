package com.mehmet.kwetter.dao;

import com.mehmet.kwetter.dao.impl.UserDaoImpl;
import com.mehmet.kwetter.service.UserServiceTest;
import com.mehmet.kwetter.util.DatabaseCleaner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Mehmet on 3/16/2017.
 */
public class UserDaoTest {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("KwetterTestPU");
    private EntityManager em;
    private EntityTransaction tx;
    private UserDao userDao;

    @Before
    public void setUp() throws Exception {
        try {
            new DatabaseCleaner(emf.createEntityManager()).clean();
        } catch (SQLException ex) {
            Logger.getLogger(UserServiceTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        em = emf.createEntityManager();
        tx = em.getTransaction();

        userDao = new UserDaoImpl();
        userDao.setEm(em);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void findAll() throws Exception {

    }

    @Test
    public void find() throws Exception {

    }

    @Test
    public void create() throws Exception {

    }

    @Test
    public void update() throws Exception {

    }

    @Test
    public void delete() throws Exception {

    }

    @Test
    public void followUser() throws Exception {

    }

    @Test
    public void unFollowUser() throws Exception {

    }

}