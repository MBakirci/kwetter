package com.mehmet.kwetter.dao.impl;

import com.mehmet.kwetter.dao.Collection;
import com.mehmet.kwetter.dao.TweetDao;
import com.mehmet.kwetter.exception.TweetNotFoundException;
import com.mehmet.kwetter.model.Tweet;
import com.mehmet.kwetter.model.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by Mehmet on 3/16/2017.
 */
@Stateless
@Collection
public class TweetDaoCollection implements TweetDao {
    CopyOnWriteArrayList<Tweet> tweets = new CopyOnWriteArrayList<>();

    @Override
    public List<Tweet> findAll() {
        return new ArrayList<>(tweets);
    }

    @Override
    public Tweet find(Object id) throws TweetNotFoundException {
        for (Tweet tweet : tweets) {
            if (tweet.getId() == (Long) id) {
                return tweet;
            }
        }
        throw new TweetNotFoundException();
    }

    @Override
    public Tweet create(Tweet tweet) {
        tweets.add(tweet);
        return tweet;
    }

    @Override
    public Tweet update(Tweet tweet) {
        return null;
    }

    @Override
    public void delete(Object id) throws TweetNotFoundException {
        Tweet tweet = find(id);
        if (tweet != null) {
            tweets.remove(tweet);
        }
    }

    @Override
    public void likeTweet(Tweet tweet, User liker) {
    }

    @Override
    public List<Tweet> findTweetByUserId(Long userId) {
        List<Tweet> userTweets = new ArrayList<>();

        for (Tweet tweet : tweets) {
            if (tweet.getUser().getId() == userId) {
                userTweets.add(tweet);
            }
        }
        Collections.sort(userTweets, new Comparator<Tweet>() {
            @Override
            public int compare(Tweet tweet1, Tweet tweet) {
                return tweet.getDate().compareTo(tweet1.getDate());
            }
        });
        return userTweets;
    }

    @Override
    public List<Tweet> findRecentTweetByUserId(Long userId) {
        List<Tweet> userTweets = new ArrayList<>();

        for (Tweet tweet : tweets) {
            if (tweet.getUser().getId() == userId) {
                if(userTweets.size() < 11) {
                    userTweets.add(tweet);
                }
            }
        }
        Collections.sort(userTweets, new Comparator<Tweet>() {
            @Override
            public int compare(Tweet tweet1, Tweet tweet) {
                return tweet.getDate().compareTo(tweet1.getDate());
            }
        });
        return userTweets;
    }

    @Override
    public void setEm(EntityManager em) {

    }
}
