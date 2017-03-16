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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Mehmet on 3/16/2017.
 */
public class HeartTest {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("KwetterTestPU");
    private EntityManager em;
    private EntityTransaction tx;

    @Before
    public void setUp() throws Exception {
        try {
            new DatabaseCleaner(emf.createEntityManager()).clean();
        } catch (SQLException ex) {
            Logger.getLogger(HeartTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        em = emf.createEntityManager();
        tx = em.getTransaction();
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void HeartMapping() {
        User user = new User("Mehmet", "geen locatie", new UserDetail("ik ben Mehmet en woon in Breda", "Breda", ""));
        Tweet tweet = new Tweet("Tweet", user);
        Heart heart = new Heart(tweet, user);

        tx.begin();
        em.persist(user);
        em.persist(tweet);
        em.persist(heart);
        tx.commit();

        tx.begin();
        em.remove(heart);
        em.remove(tweet);
        em.remove(user);
        tx.commit();
    }

}