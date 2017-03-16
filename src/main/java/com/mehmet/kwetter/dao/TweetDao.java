package com.mehmet.kwetter.dao;

import com.mehmet.kwetter.domain.Tweet;
import com.mehmet.kwetter.domain.User;

import java.util.List;

/**
 * Created by Mehmet on 3/14/2017.
 */
public interface TweetDao extends IDao<Tweet> {
    void likeTweet(Tweet tweet, User liker);
    List<Tweet> findTweetByUserId(Long userId);
    List<Tweet> findRecentTweetByUserId(Long userId);
}
