package com.mehmet.kwetter.dao.impl;

import com.mehmet.kwetter.dao.DaoFacade;
import com.mehmet.kwetter.dao.TweetDao;
import com.mehmet.kwetter.model.Heart;
import com.mehmet.kwetter.model.Tweet;
import com.mehmet.kwetter.model.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by Mehmet on 3/1/2017.
 */
@Stateless
public class TweetDaoImpl extends DaoFacade<Tweet> implements TweetDao {

    public TweetDaoImpl() {
        super(Tweet.class);
    }

    @Override
    public List<Tweet> findAll() {
        return null;
    }

    @Override
    public void setEm(EntityManager em) {
        this.em = em;
    }

    @Override
    public void LikeTweet(Tweet tweet, User liker) {
        em.persist(new Heart(tweet,liker));
    }

    @Override
    public List<Tweet> findTweetByUserId(Long userId) {
        return em.createNamedQuery("Tweet.findByUserId").setParameter("userId",userId).getResultList();
    }

    @Override
    public List<Tweet> findRecentTweetByUserId(Long userId) {
        return em.createNamedQuery("Tweet.findByUserId").setParameter("userId",userId)
                .setMaxResults(10)
                .getResultList();
    }
}
