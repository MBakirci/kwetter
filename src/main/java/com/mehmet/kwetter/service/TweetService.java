package com.mehmet.kwetter.service;

import com.mehmet.kwetter.dao.TweetDao;
import com.mehmet.kwetter.model.Tweet;
import com.mehmet.kwetter.model.User;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

/**
 * Created by Mehmet on 3/14/2017.
 */
@ApplicationScoped
public class TweetService {

    @Inject
    private TweetDao tweetDao;

    public TweetService() {
    }

    public void setTweetDao(TweetDao tweetDao) {
        this.tweetDao = tweetDao;
    }

    public List<Tweet> getAllTweets() {
        return tweetDao.findAll();
    }

    public Tweet getTweetById(Long id) {
        return tweetDao.find(id);
    }

    public void createTweet(Tweet tweet) {
        tweetDao.create(tweet);
    }

    public void updateTweet(Tweet tweet) {
        tweetDao.update(tweet);
    }

    public void removeTweet(Tweet tweet) {
        tweetDao.delete(tweet.getId());
    }

    public void likeTweet(Tweet tweet, User liker) {
        tweetDao.likeTweet(tweet, liker);
    }

    public List<Tweet> tweetsByUser(Long userId) {
        return tweetDao.findTweetByUserId(userId);
    }

    public List<Tweet> recentTweetsByUser(Long userId) {
        return tweetDao.findRecentTweetByUserId(userId);
    }

}
