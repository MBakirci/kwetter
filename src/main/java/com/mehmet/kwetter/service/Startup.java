package com.mehmet.kwetter.service;

import com.mehmet.kwetter.exception.UserAlreadyExcistException;
import com.mehmet.kwetter.exception.UserNotFoundException;
import com.mehmet.kwetter.domain.Tweet;
import com.mehmet.kwetter.domain.User;
import com.mehmet.kwetter.domain.UserDetail;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by Mehmet on 3/4/2017.
 */
@javax.ejb.Startup
@Singleton
public class Startup {
    @Inject
    private UserService userService;

    @Inject
    private TweetService tweetService;

    public Startup() {
    }

    @PostConstruct
    private void initData() throws UserAlreadyExcistException, UserNotFoundException {
        List<User> users = new ArrayList<>();
        List<Tweet> tweets = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            users.add(new User("Mehmet" + i, "geen locatie", new UserDetail("ik ben Mehmet en woon in Breda", "Breda", ""),"password"));
        }

        for (User user : users) {
            userService.createUser(user);
        }

        for (int i = 0; i < 20; i++) {
            tweets.add(new Tweet("Hallo" + i, new GregorianCalendar(2017, 2, 14, 10, 10, i), users.get(i)));
        }

        for (int i = 0; i < 30; i++) {
            tweets.add(new Tweet("Hallo" + i, new GregorianCalendar(2017, 2, 14, 10, 20, i), users.get(0)));
        }

        for (Tweet tweet : tweets) {
            tweetService.createTweet(tweet);
        }

        tweetService.likeTweet(tweets.get(0), users.get(2));
        tweetService.likeTweet(tweets.get(0), users.get(3));
        tweetService.likeTweet(tweets.get(0), users.get(4));
        tweetService.likeTweet(tweets.get(0), users.get(5));
        tweetService.likeTweet(tweets.get(1), users.get(4));
        tweetService.likeTweet(tweets.get(1), users.get(5));
        userService.followUser(users.get(1).getId(), users.get(0).getId());
        userService.followUser(5L, 6L);
    }
}
