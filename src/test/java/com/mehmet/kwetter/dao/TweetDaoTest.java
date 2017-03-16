package com.mehmet.kwetter.dao;

import com.mehmet.kwetter.dao.impl.TweetDaoImpl;
import com.mehmet.kwetter.service.TweetServiceTest;
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
public class TweetDaoTest {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("KwetterTestPU");
    private EntityManager em;
    private EntityTransaction tx;
    private TweetDao tweetDao;

    @Before
    public void setUp() throws Exception {
        try {
            new DatabaseCleaner(emf.createEntityManager()).clean();
        } catch (SQLException ex) {
            Logger.getLogger(TweetServiceTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        em = emf.createEntityManager();
        tx = em.getTransaction();

        tweetDao = new TweetDaoImpl();
        tweetDao.setEm(em);
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
    public void likeTweet() throws Exception {

    }

    @Test
    public void findTweetByUserId() throws Exception {

    }

    @Test
    public void findRecentTweetByUserId() throws Exception {

    }

}