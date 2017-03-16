package com.mehmet.kwetter.dao;

import com.mehmet.kwetter.dao.impl.UserDaoJPA;
import com.mehmet.kwetter.exception.UserAlreadyExcistException;
import com.mehmet.kwetter.exception.UserNotFoundException;
import com.mehmet.kwetter.domain.User;
import com.mehmet.kwetter.domain.UserDetail;
import com.mehmet.kwetter.service.UserServiceTest;
import com.mehmet.kwetter.util.DatabaseCleaner;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

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

        userDao = new UserDaoJPA();
        userDao.setEm(em);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void savingUserSuccessful() throws UserAlreadyExcistException {
        Integer expectedResult = 1;
        User user = new User("Mehmet", "null", new UserDetail("ik ben Mehmet en woon in Breda", "Breda", ""));
        tx.begin();
        userDao.create(user);
        tx.commit();
        tx.begin();
        int aantal = userDao.findAll().size();
        tx.commit();
        assertThat(aantal, is(expectedResult));
    }

    @Test
    public void findUserSuccessful() throws UserAlreadyExcistException, UserNotFoundException {
        User user = new User("Mehmet", "geen locatie", new UserDetail("ik ben Mehmet en woon in Breda", "Breda", ""));
        tx.begin();
        userDao.create(user);
        //all lower case
        User stud = userDao.findByUsername("mehmet");
        //all upper case
        User stud1 = userDao.findByUsername("MEHMET");
        //mixed case
        User stud2 = userDao.findByUsername("MeHmEt");
        tx.commit();
        assertThat(stud, is(user));
        assertThat(stud1, is(user));
        assertThat(stud2, is(user));
    }

    @Test
    public void updateUserSuccessful() throws Exception {
        User user = new User("Mehmet", "geen locatie", new UserDetail("ik ben Mehmet en woon in Breda", "Breda", ""));
        tx.begin();
        userDao.create(user);
        User stud = userDao.findByUsername("mehmet");
        tx.commit();
        assertThat(stud, is(user));

        //Update the user's profile picture url
        user.setProfilePicUrl("/profileImg.jpg");
        tx.begin();
        userDao.update(user);
        stud = userDao.findByUsername("mehmet");
        tx.commit();
        assertThat(stud, is(user));
    }

    @Test
    public void deleteUserSuccessful() throws Exception {
        User user = new User("Mehmet", "geen locatie", new UserDetail("ik ben Mehmet en woon in Breda", "Breda", ""));
        tx.begin();
        userDao.create(user);
        User stud = userDao.findByUsername("mehmet");
        tx.commit();
        assertThat(stud, is(user));

        tx.begin();
        //Delete user by id
        userDao.delete(user.getId());
        stud = userDao.find(user.getId());
        tx.commit();
        Assert.assertEquals(null, stud);
    }

    @Test
    public void followUserSuccessful() throws Exception {
        User user = new User("Mehmet", "null", new UserDetail("ik ben Mehmet en woon in Breda", "Breda", ""));
        User user1 = new User("Mehmet1", null, new UserDetail("ik ben Mehmet en woon in Breda", "Breda", ""));
        tx.begin();
        userDao.create(user);
        userDao.create(user1);
        user.addFollower(user1);
        User stud = userDao.findByUsername("mehmet");
        User stud1 = userDao.findByUsername("mehmet1");
        tx.commit();
        assertThat(stud.getFollowers().contains(stud1), is(stud1.getFollowing().contains(stud)));
    }

    @Test
    public void unFollowUserSuccessful() throws Exception {
        User user = new User("Mehmet", "null", new UserDetail("ik ben Mehmet en woon in Breda", "Breda", ""));
        User user1 = new User("Mehmet1", null, new UserDetail("ik ben Mehmet en woon in Breda", "Breda", ""));
        tx.begin();
        userDao.create(user);
        userDao.create(user1);
        user.addFollower(user1);
        User stud = userDao.findByUsername("mehmet");
        User stud1 = userDao.findByUsername("mehmet1");
        tx.commit();
        assertThat(stud.getFollowers().contains(stud1), is(stud1.getFollowing().contains(stud)));
        tx.begin();
        user.removeFollower(user1);
        tx.commit();
        assertThat(stud.getFollowers().size(), is(0));
    }

}