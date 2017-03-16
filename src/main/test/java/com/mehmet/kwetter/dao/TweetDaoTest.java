package com.mehmet.kwetter.dao;

import com.mehmet.kwetter.dao.impl.TweetDaoJPA;
import com.mehmet.kwetter.dao.impl.UserDaoJPA;
import com.mehmet.kwetter.domain.Tweet;
import com.mehmet.kwetter.domain.User;
import com.mehmet.kwetter.domain.UserDetail;
import com.mehmet.kwetter.service.TweetServiceTest;
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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Mehmet on 3/16/2017.
 */
public class TweetDaoTest {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("KwetterTestPU");
    private EntityManager em;
    private EntityTransaction tx;
    private TweetDao tweetDao;
    private UserDao userDao;

    @Before
    public void setUp() throws Exception {
        try {
            new DatabaseCleaner(emf.createEntityManager()).clean();
        } catch (SQLException ex) {
            Logger.getLogger(TweetServiceTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        em = emf.createEntityManager();
        tx = em.getTransaction();

        userDao = new UserDaoJPA();
        userDao.setEm(em);
        tweetDao = new TweetDaoJPA();
        tweetDao.setEm(em);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void createAndfindAllTweets() throws Exception {
        Integer expectedResult = 1;
        User user = new User("Mehmet", "null", new UserDetail("ik ben Mehmet en woon in Breda", "Breda", ""));
        Tweet tweet = new Tweet("testTweet",user);
        tx.begin();
        userDao.create(user);
        tweetDao.create(tweet);
        tx.commit();

        tx.begin();
        int aantal = tweetDao.findAll().size();
        tx.commit();
        assertThat(aantal, is(expectedResult));
    }


    @Test
    public void update() throws Exception {
        Integer expectedResult = 1;
        User user = new User("Mehmet", "null", new UserDetail("ik ben Mehmet en woon in Breda", "Breda", ""));
        Tweet tweet = new Tweet("testTweet",user);
        tx.begin();
        userDao.create(user);
        tweetDao.create(tweet);
        tx.commit();
        tx.begin();
        int aantal = tweetDao.findAll().size();
        tx.commit();
        assertThat(aantal, is(expectedResult));

        //Update the tweet
        tweet.setTweet("TestTweetEdditted");
        tx.begin();
        tweetDao.update(tweet);
        Tweet tweet1 = tweetDao.find(tweet.getId());
        tx.commit();
        assertThat(tweet,is(tweet1));

    }

    @Test
    public void delete() throws Exception {
        Integer expectedResult = 1;
        User user = new User("Mehmet", "null", new UserDetail("ik ben Mehmet en woon in Breda", "Breda", ""));
        Tweet tweet = new Tweet("testTweet",user);
        tx.begin();
        userDao.create(user);
        tweetDao.create(tweet);
        tx.commit();
        tx.begin();
        int aantal = tweetDao.findAll().size();
        tx.commit();
        assertThat(aantal, is(expectedResult));

        //Delete the tweet by its id
        tweet.setTweet("TestTweetEdditted");
        tx.begin();
        tweetDao.delete(tweet.getId());
        Tweet tweet1 = tweetDao.find(tweet.getId());
        tx.commit();
        Assert.assertEquals(null,tweet1);
    }


    @Test
    public void likeTweet() throws Exception {
        //AddHeart
        Integer expectedResult = 2;
        User user = new User("Mehmet", "null", new UserDetail("ik ben Mehmet en woon in Breda", "Breda", ""));
        User user1 = new User("Mehmet1", "null", new UserDetail("ik ben Mehmet en woon in Breda", "Breda", ""));
        Tweet tweet = new Tweet("testTweet",user);


        tx.begin();
        userDao.create(user);
        userDao.create(user1);
        tweetDao.create(tweet);
        tx.commit();

        tx.begin();
        //Like tweet
        tweetDao.likeTweet(tweet,user);
        tweetDao.likeTweet(tweet,user1);
        tx.commit();

        tx.begin();
        List<Tweet> tweets = tweetDao.findAll();
        tx.commit();

        int aantal = tweets.size();
        assertThat(aantal, is(1));
        assertThat(tweets.get(0).getHearts().size(),is(expectedResult));
    }

    @Test
    public void findTweetByUserId() throws Exception {
        Integer expectedResult = 1;
        User user = new User("Mehmet", "null", new UserDetail("ik ben Mehmet en woon in Breda", "Breda", ""));
        Tweet tweet = new Tweet("testTweet",user);
        tx.begin();
        userDao.create(user);
        tweetDao.create(tweet);
        tx.commit();

        tx.begin();
        int aantal = tweetDao.findTweetByUserId(user.getId()).size();
        tx.commit();
        assertThat(aantal, is(expectedResult));
    }

    @Test
    public void findRecentTweetByUserId() throws Exception {
        int exceptedValue = 10;

        List<Tweet> tweetList = new ArrayList<>();
        User user = new User("Mehmet", "null", new UserDetail("ik ben Mehmet en woon in Breda", "Breda", ""));
        for (int i = 0; i < 20; i++) {
            tweetList.add(new Tweet("testTweet" + 1, user));
        }
        tx.begin();
        userDao.create(user);
        for (Tweet tweet: tweetList) {
            tweetDao.create(tweet);
        }
        tx.commit();

        tx.begin();
        int aantal = tweetDao.findRecentTweetByUserId(user.getId()).size();
        tx.commit();
        assertThat(exceptedValue, is(aantal));
    }

}