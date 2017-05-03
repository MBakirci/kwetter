package com.mehmet.kwetter.rest;

import com.mehmet.kwetter.dao.Secured;
import com.mehmet.kwetter.domain.RoleEnum;
import com.mehmet.kwetter.domain.Tweet;
import com.mehmet.kwetter.domain.User;
import com.mehmet.kwetter.exception.TweetNotFoundException;
import com.mehmet.kwetter.exception.UserAlreadyExcistException;
import com.mehmet.kwetter.exception.UserNotFoundException;
import com.mehmet.kwetter.service.MailService;
import com.mehmet.kwetter.service.TweetService;
import com.mehmet.kwetter.service.UserService;
import io.swagger.annotations.Api;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Set;

/**
 * Created by Mehmet on 3/14/2017.
 */

@Path("user")
@Produces("application/json")
@Api(value = "user")
public class UserResource {

    @Inject
    UserService userService;

    @Inject
    MailService mailService;

    @Inject
    TweetService tweetService;

    //region CRUD OPERATIONS
    //region READ OPERATIONS
    @GET
    @Secured({RoleEnum.ADMIN})
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
    public void createUser(User user) throws UserAlreadyExcistException, UserNotFoundException, TweetNotFoundException {
        userService.createUser(user);
        String url = "http://localhost:8080/kwetter/api/user/" + user.getId() + "/" + user.getActivationCode();
        mailService.send("mbakirci94@gmail.com","Welcome to Kwetter", url);
    }

    @GET
    @Path("{id}/{activationCode}")
    public Response activateUser(@PathParam("id") Long id, @PathParam("activationCode") String code) throws UserNotFoundException, TweetNotFoundException {
        userService.setActivate(userService.getUser(id),true, code);
        return Response.ok().build();
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
