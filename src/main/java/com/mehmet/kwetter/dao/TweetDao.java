package com.mehmet.kwetter.dao;

import com.mehmet.kwetter.domain.Tweet;
import com.mehmet.kwetter.domain.User;

import java.util.List;

/**
 * Created by Mehmet on 3/14/2017.
 */
public interface TweetDao extends IDao<Tweet> {
    /**
     * @param tweet object of the tweet that should liked
     * @param liker object of the user that likes the tweet
     */
    void likeTweet(Tweet tweet, User liker);

    /**
     * @param userId the id of the user
     * @return tweet by users id
     */
    List<Tweet> findTweetByUserId(Long userId);

    /**
     * @param userId the id of the user
     * @return List of most recent(10) tweets by users id
     */
    List<Tweet> findRecentTweetByUserId(Long userId);
}
