package com.mehmet.kwetter.dao;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Mehmet on 3/8/2017.
 */
public class UserDaoTest {
    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testDit(){
        String ikke = "Meh met";
        assertEquals("Mehmet",ikke);
    }

}