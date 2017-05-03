package com.mehmet.kwetter.rest;

import com.mehmet.kwetter.dao.impl.TweetFaulLanguageInceptor;
import com.mehmet.kwetter.domain.Tweet;
import com.mehmet.kwetter.exception.TweetNotFoundException;
import com.mehmet.kwetter.exception.UserAlreadyExcistException;
import com.mehmet.kwetter.exception.UserNotFoundException;
import com.mehmet.kwetter.service.TweetService;
import io.swagger.annotations.Api;

import javax.inject.Inject;
import javax.interceptor.Interceptors;
import javax.ws.rs.*;
import java.util.List;

/**
 * Created by Mehmet on 3/14/2017.
 * CRUD Operations of Tweet
 */

@Path("tweet")
@Produces("application/json")
@Api(value = "tweets")
public class TweetResource {

    @Inject
    TweetService tweetService;

    // READ OPERATIONS
    @GET
    public List<Tweet> getAllTweets() {
        return tweetService.getAllTweets();
    }

    @GET
    @Path("{id}")
    public Tweet getTweetById(@PathParam("id") Long id) throws UserNotFoundException, TweetNotFoundException {
        return tweetService.getTweetById(id);
    }

    // CREATE OPERATION
    @POST
    @Consumes("application/json")
    public void createTweet(Tweet tweet) throws UserAlreadyExcistException {
        tweetService.createTweet(tweet);
    }

    // UPDATE OPERATION
    @PUT
    @Consumes("application/json")
    public void updateTweet(Tweet tweet) throws UserNotFoundException {
        tweetService.updateTweet(tweet);
    }

    // DELETE OPERATION
    @DELETE
    @Consumes("application/json")
    public void deleteTweet(Tweet tweet) throws UserNotFoundException, TweetNotFoundException {
        tweetService.removeTweet(tweet);
    }

}
