package com.mehmet.kwetter.service;

import com.mehmet.kwetter.dao.UserDao;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

/**
 * Created by Mehmet on 3/10/2017.
 */
public class UserServiceTest {

    private UserService userService;
    @Mock
    private UserDao userDao;

    @Before
    public void setUp() throws Exception {
        userService = new UserService();
        userService.setUserDao(userDao);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void setUserDao() throws Exception {

    }

    @Test
    public void getUsers1() throws Exception {

    }

    @Test
    public void getUser() throws Exception {

    }

    @Test
    public void createUser() throws Exception {

    }

    @Test
    public void updateUser() throws Exception {

    }

    @Test
    public void removeUser() throws Exception {

    }

    @Test
    public void followUser() throws Exception {

    }

    @Test
    public void unFollowUser() throws Exception {

    }

}