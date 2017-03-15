package com.mehmet.kwetter.service;

import com.mehmet.kwetter.dao.UserDao;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.inject.Inject;

/**
 * Created by Mehmet on 3/10/2017.
 */
public class UserServiceTest {
    @Inject
    private UserDao userDao;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {

    }

//    @Test
//    public void setUserDao() throws Exception {
//
//    }

    @Test
    public void addUser() throws Exception {
//        userDao.create(new User("Mehmetje",null,new UserDetail("geboren en getogen in Breda sinds 1994", "Breda", "bakirci.nl")));
//        userDao.create(new User("Mehmetje1",null,new UserDetail("geboren en getogen in Breda sinds 1994", "Breda", "bakirci.nl")));
//        userDao.create(new User("Mehmetje2",null,new UserDetail("geboren en getogen in Breda sinds 1994", "Breda", "bakirci.nl")));
//        userDao.create(new User("Mehmetje3",null,new UserDetail("geboren en getogen in Breda sinds 1994", "Breda", "bakirci.nl")));
//        assertEquals(4,userDao.findAll().size());
    }

    @Test
    public void getUsers() throws Exception {

    }

}