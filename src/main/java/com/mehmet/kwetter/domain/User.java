package com.mehmet.kwetter.domain;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.*;

/**
 * @author Mehmet
 */
@Entity
@NamedQueries({
        @NamedQuery(name = "User.findAll",
                query = "SELECT u FROM User u WHERE u.disabled = false"),
        @NamedQuery(name = "User.findById",
                query = "SELECT u FROM User u WHERE u.id = :id"),
        @NamedQuery(name = "User.findByUsername",
                query = "SELECT u FROM User u WHERE u.username = UPPER(:username)")
})
public class User implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique=true)
    private String username;
    private String profilePicUrl;
    private String password;

    @Enumerated(EnumType.STRING)
    private RoleEnum role;

    private UserDetail userdetail;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user", fetch = FetchType.EAGER)
    private Set<Tweet> tweets;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user", fetch = FetchType.EAGER)
    private Set<Heart> hearts;

    private String activationCode;
    private boolean activated;
    private Date registerationDate;
    private boolean disabled;

    @Transient
    private int followerCount;
    @Transient
    private int followingCount;
    @Transient
    private int tweetCount;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinTable(
            name = "follower",
            joinColumns = {@JoinColumn(name = "follower_id")},
            inverseJoinColumns = {@JoinColumn(name = "following_id")}
    )
    private Set<User> followers = new HashSet<>();

    @ManyToMany(mappedBy = "followers", fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private Set<User> following = new HashSet<>();

    public User() {
        this.role = RoleEnum.USER;
        this.activationCode = new BigInteger(130, new SecureRandom()).toString(32);
        this.registerationDate = new GregorianCalendar().getTime();
    }

    public User(String username, String profilePicUrl, UserDetail userDetail, String password) {
        this.username = username;
        this.profilePicUrl = profilePicUrl;
        this.userdetail = userDetail;
        this.password = password;
        this.role = RoleEnum.USER;
        this.activationCode = new BigInteger(130, new SecureRandom()).toString(32);
        this.activated = false;
        this.registerationDate = new GregorianCalendar(2017, Calendar.APRIL,1).getTime();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String userName) {
        this.username = userName;
    }

    public String getProfilePicUrl() {
        return profilePicUrl;
    }

    public void setProfilePicUrl(String profilePicLocation) {
        this.profilePicUrl = profilePicLocation;
    }

    public UserDetail getUserdetail() {
        return userdetail;
    }

    public void setUserdetail(UserDetail userDetail) {
        this.userdetail = userDetail;
    }

    @XmlTransient
    public Set<Tweet> getTweets() {
        return tweets;
    }

    public void setTweets(Set<Tweet> tweets) {
        this.tweets = tweets;
    }

    public void addTweet(Tweet tweet) {
        this.tweets.add(tweet);
    }

    @XmlTransient
    public Set<User> getFollowing() {
        return following;
    }

    public void setFollowing(Set<User> following) {
        this.following = following;
    }

    @XmlTransient
    public Set<User> getFollowers() {
        return followers;
    }

    public void setFollowers(Set<User> followers) {
        this.followers = followers;
    }

    @XmlTransient
    public Set<Heart> getHearts() {
        return hearts;
    }

    public void setHearts(Set<Heart> hearts) {
        this.hearts = hearts;
    }

    public void addFollower(User follower) {
        followers.add(follower);
        follower.following.add(this);
    }

    public void removeFollower(User follower) {
        followers.remove(follower);
        follower.following.remove(this);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public RoleEnum getRole() {
        return role;
    }

    public void setRole(RoleEnum role) {
        this.role = role;
    }

    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    public String getActivationCode() {
        return activationCode;
    }

    public void setActivationCode(String activationCode) {
        this.activationCode = activationCode;
    }

    public Date getRegisterationDate() {
        return registerationDate;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    public int getFollowerCount() {
        return this.followers.size();
    }

    public int getFollowingCount() {
        return this.following.size();
    }

    public int getTweetCount() {
        return this.tweets.size();
    }
}
