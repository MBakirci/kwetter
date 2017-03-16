package com.mehmet.kwetter.service;

import com.mehmet.kwetter.dao.UserDao;
import com.mehmet.kwetter.model.User;
import com.mehmet.kwetter.model.UserDetail;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Mehmet on 3/10/2017.
 */
@RunWith(MockitoJUnitRunner.class)
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
    public void getUser() throws Exception {
        User user = new User("Mehmet", "geen locatie", new UserDetail("ik ben Mehmet en woon in Breda", "Breda", ""));

        when(userService.getUser(user.getUsername())).thenReturn(user);
        User found = userService.getUser(user.getUsername());
        assertThat(found, is(user));
    }


    @Test
    public void createUser() throws Exception {
        User user = new User("Mehmet", "geen locatie", new UserDetail("ik ben Mehmet en woon in Breda", "Breda", ""));
        userService.createUser(user);
        verify(userDao, Mockito.times(1)).create(user);
    }
}