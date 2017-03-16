package com.mehmet.kwetter.service;

import com.mehmet.kwetter.dao.TweetDao;
import com.mehmet.kwetter.domain.Tweet;
import com.mehmet.kwetter.domain.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

/**
 * Created by Mehmet on 3/16/2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class TweetServiceTest {

    private TweetService tweetService;
    @Mock
    private TweetDao tweetDao;

    @Before
    public void setUp() throws Exception {
        tweetService = new TweetService();
        tweetService.setTweetDao(tweetDao);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void createTweet() throws Exception {
        Tweet t1 = new Tweet("tweet1", new User());
        tweetService.createTweet(t1);
        verify(tweetDao, Mockito.times(1)).create(t1);
    }
}