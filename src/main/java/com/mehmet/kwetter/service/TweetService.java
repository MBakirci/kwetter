package com.mehmet.kwetter.service;

import com.mehmet.kwetter.dao.TweetDao;
import com.mehmet.kwetter.dao.impl.TweetFaulLanguageInceptor;
import com.mehmet.kwetter.exception.TweetNotFoundException;
import com.mehmet.kwetter.exception.UserAlreadyExcistException;
import com.mehmet.kwetter.exception.UserNotFoundException;
import com.mehmet.kwetter.domain.Tweet;
import com.mehmet.kwetter.domain.User;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
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

    public Tweet getTweetById(Long id) throws UserNotFoundException, TweetNotFoundException {
        return tweetDao.find(id);
    }

    @Interceptors({TweetFaulLanguageInceptor.class})
    public void createTweet(Tweet tweet) throws UserAlreadyExcistException {
        tweetDao.create(tweet);
    }

    @Interceptors({TweetFaulLanguageInceptor.class})
    public void updateTweet(Tweet tweet) throws UserNotFoundException {
        tweetDao.update(tweet);
    }

    public void removeTweet(Tweet tweet) throws UserNotFoundException, TweetNotFoundException {
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
