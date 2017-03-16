package com.mehmet.kwetter.dao.impl;

import com.mehmet.kwetter.dao.DaoFacade;
import com.mehmet.kwetter.dao.TweetDao;
import com.mehmet.kwetter.model.Heart;
import com.mehmet.kwetter.model.Tweet;
import com.mehmet.kwetter.model.User;

import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by Mehmet on 3/1/2017.
 */
@Stateless
@Default
public class TweetDaoJPA extends DaoFacade<Tweet> implements TweetDao {

    public TweetDaoJPA() {
        super(Tweet.class);
    }

    @PersistenceContext
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    public List<Tweet> findAll() {
        return em.createNamedQuery("Tweet.findAll").getResultList();
    }

    @Override
    public void setEm(EntityManager em) {
        this.em = em;
    }

    @Override
    public void likeTweet(Tweet tweet, User liker) {
        tweet.addHeart(new Heart(tweet,liker));
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
