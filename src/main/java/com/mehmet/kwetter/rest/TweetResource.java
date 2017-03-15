package com.mehmet.kwetter.rest;

import com.mehmet.kwetter.model.Tweet;
import com.mehmet.kwetter.service.TweetService;

import javax.inject.Inject;
import javax.ws.rs.*;
import java.util.List;

/**
 * Created by Mehmet on 3/14/2017.
 * CRUD Operations of Tweet
 */

@Path("tweet")
@Produces("application/json")
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
    public Tweet getTweetById(@PathParam("id") Long id) {
        return tweetService.getTweetById(id);
    }

    // CREATE OPERATION
    @POST
    @Consumes("application/json")
    public void createTweet(Tweet tweet) {
        tweetService.createTweet(tweet);
    }

    // UPDATE OPERATION
    @PUT
    @Consumes("application/json")
    public void updateTweet(Tweet tweet) {
        tweetService.updateTweet(tweet);
    }

    // DELETE OPERATION
    @DELETE
    @Consumes("application/json")
    public void deleteTweet(Tweet tweet) {
        tweetService.removeTweet(tweet);
    }

}
