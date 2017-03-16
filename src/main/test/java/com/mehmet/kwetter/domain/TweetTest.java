package com.mehmet.kwetter.domain;

import com.mehmet.kwetter.util.DatabaseCleaner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Mehmet on 3/16/2017.
 */
public class TweetTest {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("KwetterTestPU");
    private EntityManager em;
    private EntityTransaction tx;

    public TweetTest() {
    }

    @Before
    public void setUp() {
        try {
            new DatabaseCleaner(emf.createEntityManager()).clean();
        } catch (SQLException ex) {
            Logger.getLogger(TweetTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        em = emf.createEntityManager();
        tx = em.getTransaction();
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void TweetMapping() {
        User user = new User("Mehmet", "geen locatie", new UserDetail("ik ben Mehmet en woon in Breda", "Breda", ""));
        Tweet tweet = new Tweet("Tweet", user);
        Tweet tweet1 = new Tweet("Tweet", new GregorianCalendar(2017, Calendar.MARCH, 15, 15, 30, 30), user);

        tx.begin();
        em.persist(user);
        em.persist(tweet);
        em.persist(tweet1);
        tx.commit();

        tx.begin();
        em.remove(tweet);
        em.remove(tweet1);
        em.remove(user);
        tx.commit();
    }
}