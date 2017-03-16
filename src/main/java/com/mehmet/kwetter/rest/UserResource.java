package com.mehmet.kwetter.rest;

import com.mehmet.kwetter.exception.TweetNotFoundException;
import com.mehmet.kwetter.exception.UserAlreadyExcistException;
import com.mehmet.kwetter.exception.UserNotFoundException;
import com.mehmet.kwetter.model.Tweet;
import com.mehmet.kwetter.model.User;
import com.mehmet.kwetter.service.TweetService;
import com.mehmet.kwetter.service.UserService;

import javax.inject.Inject;
import javax.ws.rs.*;
import java.util.List;
import java.util.Set;

/**
 * Created by Mehmet on 3/14/2017.
 */

@Path("user")
@Produces("application/json")
public class UserResource {

    @Inject
    UserService userService;

    @Inject
    TweetService tweetService;

    //region CRUD OPERATIONS
    //region READ OPERATIONS
    @GET
    public List<User> getAllUsers() {
        return userService.getUsers();
    }

    @GET
    @Path("{id}")
    public User getUser(@PathParam("id") Long id) throws UserNotFoundException, TweetNotFoundException {
        return userService.getUser(id);
    }
    //endregion

    // CREATE OPERATION
    @POST
    @Consumes("application/json")
    public void createUser(User user) throws UserAlreadyExcistException {
        userService.createUser(user);
    }

    // UPDATE OPERATION
    @PUT
    @Consumes("application/json")
    public void updateUser(User user) throws UserNotFoundException {
        userService.updateUser(user);
    }

    // DELETE OPERATION
    @DELETE
    @Consumes("application/json")
    public void deleteUser(User user) throws UserNotFoundException, TweetNotFoundException {
        userService.removeUser(user);
    }
    //endregion

    //region Tweets by user
    @GET
    @Path("{id}/tweets")
    public List<Tweet> getTweetsByUserId(@PathParam("id") Long id) {
        return tweetService.tweetsByUser(id);
    }

    @GET
    @Path("{id}/tweets/recent")
    public List<Tweet> getRecentTweetsByUserId(@PathParam("id") Long id) {
        return tweetService.recentTweetsByUser(id);
    }
    //endregion

    //region Followers
    @GET
    @Path("{id}/followers")
    public Set<User> getFollowers(@PathParam("id") Long id) throws UserNotFoundException, TweetNotFoundException {
        return userService.getUser(id).getFollowers();
    }

    @POST
    @Path("follower")
    @Consumes("application/json")
    public void follow(List<User> users) throws UserNotFoundException {
        userService.followUser(users.get(1).getId(), users.get(0).getId());
    }

    @DELETE
    @Path("follower")
    @Consumes("application/json")
    public void unFollow(List<User> users) throws UserNotFoundException {
        userService.unFollowUser(users.get(1).getId(), users.get(0).getId());
    }
    //endregion

}
