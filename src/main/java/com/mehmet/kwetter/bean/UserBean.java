package com.mehmet.kwetter.bean;

import com.mehmet.kwetter.domain.Heart;
import com.mehmet.kwetter.domain.Tweet;
import com.mehmet.kwetter.domain.User;
import com.mehmet.kwetter.domain.UserDetail;
import com.mehmet.kwetter.exception.TweetNotFoundException;
import com.mehmet.kwetter.exception.UserAlreadyExcistException;
import com.mehmet.kwetter.exception.UserNotFoundException;
import com.mehmet.kwetter.service.TweetService;
import com.mehmet.kwetter.service.UserService;

import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.*;

/**
 * Created by Mehmet on 2/4/2017.
 */
@ManagedBean(name = "userBean")
@SessionScoped
public class UserBean {

    @Inject
    private UserService userService;

    @Inject
    private TweetService tweetService;

    private User user;
    private int tweetsamount;
    private int followersamount;
    private int followingamount;
    private int heartsamount;

    private ArrayList<Tweet> recenttweets;
    private String tweetcontent;
    private ArrayList<Heart> heartedtweets;
    private ArrayList<Tweet> timelinetweets;

    private ArrayList<User> following;
    private ArrayList<User> followers;


    private ArrayList<User> filter;

    public User getUser() throws UserNotFoundException {
        if (user == null) {
            ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
            String userName = context.getUserPrincipal().getName();
            user = userService.getUser(userName);
        }
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getTweetsamount() {
        return tweetService.recentTweetsByUser(user.getId()).size();
    }

    public void setTweetsamount(int tweetsamount) {
        this.tweetsamount = tweetsamount;
    }

    public int getFollowersamount() {
        return user.getFollowers().size();
    }

    public void setFollowersamount(int followersamount) {
        this.followersamount = followersamount;
    }

    public int getFollowingamount() {
        return user.getFollowing().size();
    }

    public void setFollowingamount(int followingamount) {
        this.followingamount = followingamount;
    }

    public String register(String username, String password) throws UserAlreadyExcistException {
        User d = new User(username,null,null,password);
        userService.createUser(d);
        return "/login.xhtml?faces-redirect=true";
    }

    public String logout(){
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/login.xhtml?faces-redirect=true";
    }

    public String createTweet() throws UserAlreadyExcistException {
        tweetService.createTweet(new Tweet(tweetcontent,user));
        tweetcontent = "";
        return "viewid?faces-redirect=true";
    }

    public void heartTweet() throws UserNotFoundException, TweetNotFoundException {
        Map<String,String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        String id = params.get("id");
        Tweet tweet = tweetService.getTweetById(Long.valueOf(id).longValue());
        tweetService.likeTweet(tweet, user);
    }

    public String getTweetcontent() {
        return tweetcontent;
    }

    public void setTweetcontent(String tweetcontent) {
        this.tweetcontent = tweetcontent;
    }

    public List<Tweet> getTimelinetweets() throws UserNotFoundException, TweetNotFoundException {
        timelinetweets = new ArrayList<>();
        for (User u : this.user.getFollowing()) {
            timelinetweets.addAll(tweetService.tweetsByUser(u.getId()));
        }
        timelinetweets.addAll(tweetService.tweetsByUser(user.getId()));

        Collections.sort(timelinetweets, new Comparator<Tweet>() {
            @Override
            public int compare(Tweet o1, Tweet o2) {
                if (o1.getDate().before(o2.getDate()))
                    return 1;
                if (o1.getDate().after(o2.getDate()))
                    return -1;
                return 0;
            }
        });

        return timelinetweets;
    }

    public ArrayList<Tweet> getRecenttweets() {
        recenttweets = new ArrayList<>();
        recenttweets.addAll(tweetService.recentTweetsByUser(user.getId()));
        return recenttweets;
    }

    public void setRecenttweets(ArrayList<Tweet> recenttweets) {
        this.recenttweets = recenttweets;
    }

    public ArrayList<Heart> getHeartedtweets() {
        heartedtweets = new ArrayList<>();
//        heartedtweets.addAll(tweetService.);
        return heartedtweets;
    }

    public void setHeartedtweets(ArrayList<Heart> heartedtweets) {
        this.heartedtweets = heartedtweets;
    }

    public void setTimelinetweets(ArrayList<Tweet> timelinetweets) {
        this.timelinetweets = timelinetweets;
    }

    public int getHeartsamount() throws UserNotFoundException {
        User u = userService.getUser(user.getUsername());
        return u.getHearts().size();
    }

    public void setHeartsamount(int heartsamount) {
        this.heartsamount = heartsamount;
    }

    public ArrayList<User> getFollowing() throws UserNotFoundException {
        following = new ArrayList<>();
        User u = userService.getUser(user.getUsername());
        following.addAll(u.getFollowing());

        return following;
    }

    public void setFollowing(ArrayList<User> following) {
        this.following = following;
    }

    public ArrayList<User> getFollowers() throws UserNotFoundException {
        followers = new ArrayList<>();
        User u = userService.getUser(user.getUsername());
        followers.addAll(u.getFollowers());

        return followers;
    }

    public void setFollowers(ArrayList<User> followers) {
        this.followers = followers;
    }

    public String searchUser(String username){
        ArrayList<User> users = new ArrayList<>();
        for (User u: userService.getUsers()) {
            if(u.getUsername().toLowerCase().contains(username.toLowerCase())) {
                users.add(u);
            }
        }
        this.filter = users;
        return "/user/searchResults.xhtml?faces-redirect=true";
    }


    public ArrayList<User> getFilter() {
        return filter;
    }

    public void setFilter(ArrayList<User> filter) {
        this.filter = filter;
    }
}
