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
public class UserTest {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("KwetterTestPU");
    private EntityManager em;
    private EntityTransaction tx;

    public UserTest() {
    }

    @Before
    public void setUp() {
        try {
            new DatabaseCleaner(emf.createEntityManager()).clean();
        } catch (SQLException ex) {
            Logger.getLogger(UserTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        em = emf.createEntityManager();
        tx = em.getTransaction();
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void UserMapping() {
        User user = new User("Mehmet", "geen locatie", new UserDetail("ik ben Mehmet en woon in Breda", "Breda", ""));

        tx.begin();
        em.persist(user);
        tx.commit();

        user.getUserdetail().setWebsite("www.bakirci.nl");
        tx.begin();
        em.merge(user);
        tx.commit();

        tx.begin();
        em.remove(user);
        tx.commit();
    }

}