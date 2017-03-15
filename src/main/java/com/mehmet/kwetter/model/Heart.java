package com.mehmet.kwetter.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlTransient;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Mehmet on 3/14/2017.
 */
@Entity
public class Heart {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_heart",
            joinColumns =
            @JoinColumn(name = "heart_id"),
            inverseJoinColumns =
            @JoinColumn(name = "user_id")
    )
    private Set<User> user = new HashSet<>();

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "tweet_id")
    private Tweet tweet;

    public Heart() {
    }

    public Heart(Tweet tweet, User user) {
        this.tweet = tweet;
        if (!this.user.contains(user)) {
            this.user.add(user);
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<User> getUser() {
        return user;
    }

    public void setUser(Set<User> user) {
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
