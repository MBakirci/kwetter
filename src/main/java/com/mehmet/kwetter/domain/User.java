package com.mehmet.kwetter.domain;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlTransient;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Mehmet
 */
@Entity
@NamedQueries({
        @NamedQuery(name = "User.findAll",
                query = "SELECT u FROM User u"),
        @NamedQuery(name = "User.findById",
                query = "SELECT u FROM User u WHERE u.id = :id"),
        @NamedQuery(name = "User.findByUsername",
                query = "SELECT u FROM User u WHERE u.username = UPPER(:username)")
})
public class User {

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
    }

    public User(String username, String profilePicUrl, UserDetail userDetail, String password) {
        this.username = username;
        this.profilePicUrl = profilePicUrl;
        this.userdetail = userDetail;
        this.password = password;
        this.role = RoleEnum.USER;
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
        role = role;
    }
}
