package com.mehmet.kwetter.domain;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Created by Mehmet on 3/14/2017.
 */
@Entity
public class Heart {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "tweet_id")
    private Tweet tweet;

    public Heart() {
    }

    public Heart(Tweet tweet, User user) {
        this.tweet = tweet;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @XmlTransient
    public Tweet getTweet() {
        return tweet;
    }

    public void setTweet(Tweet tweet) {
        this.tweet = tweet;
    }

}
