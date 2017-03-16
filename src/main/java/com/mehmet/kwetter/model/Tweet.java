package com.mehmet.kwetter.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author Mehmet
 */
@Entity
@NamedQueries({
        @NamedQuery(name = "Tweet.findAll", query = "SELECT k FROM Tweet k "),
        @NamedQuery(name = "Tweet.findByUserId", query = "SELECT k FROM Tweet k WHERE k.user.id = :userId ORDER BY k.date DESC"),
})
public class Tweet implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String tweet;
    private Calendar date;

    @Transient
    private String dateString;

    @OneToMany(mappedBy = "tweet", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Heart> hearts = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Tweet() {
        date = new GregorianCalendar();
    }

    public Tweet(String tweet, User user) {
        this.tweet = tweet;
        this.user = user;
        this.date = new GregorianCalendar();
    }

    public Tweet(String tweet, GregorianCalendar date, User owner) {
        this.tweet = tweet;
        this.date = date;
        this.user = owner;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTweet() {
        return tweet;
    }

    public void setTweet(String tweet) {
        this.tweet = tweet;
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    public Set<Heart> getHearts() {
        return hearts;
    }

    public void setHearts(Set<Heart> hearts) {
        this.hearts = hearts;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User owner) {
        this.user = owner;
    }

    public String getDateString() {
        SimpleDateFormat fmt = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
        fmt.setCalendar(date);
        return fmt.format(date.getTime());
    }

    public void setDateString(String dateString) {
        this.dateString = dateString;
    }

    public void addHeart(Heart heart) {
        hearts.add(heart);
    }
}
