package com.mehmet.dao;

import com.mehmet.model.Tweet;

import java.util.List;

/**
 * Created by Mehmet on 3/1/2017.
 */
public class TweetDao implements IDao<Tweet, Long> {

    @Override
    public boolean create(Tweet tweet) {
        return false;
    }

    @Override
    public Tweet find(Long id) {
        return null;
    }

    @Override
    public Tweet update(Tweet tweet) {
        return null;
    }

    @Override
    public void delete(Tweet tweet) {

    }

    @Override
    public List<Tweet> findAll() {
        return null;
    }
}
