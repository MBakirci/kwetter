package com.mehmet.kwetter.rest;

import com.mehmet.kwetter.dao.Secured;
import com.mehmet.kwetter.domain.Link;
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

import javax.annotation.PostConstruct;
import javax.batch.operations.JobOperator;
import javax.batch.runtime.BatchRuntime;
import javax.inject.Inject;
import javax.management.relation.Role;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
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

    private static User user;

    @Context
    SecurityContext securityContext;

    @PostConstruct
    public void postcontruct(){
        try {
            Principal principal = securityContext.getUserPrincipal();
            String username = principal.getName();
            user = userService.getUser(username);
        } catch (Exception e) {

        }
    }

    @GET
    @Path("curr/{currentuser}")
    @Secured({RoleEnum.ADMIN, RoleEnum.USER})
    public User getCurrentUser() {
        return user;
    }


    //region CRUD OPERATIONS
    //region READ OPERATIONS
    @GET
    @Secured({RoleEnum.ADMIN})
    public List<User> getAllUsers() {
        return userService.getUsers();
    }

    @GET
    @Path("{start}/run/job")
    public void deleteNotActivatedUsers(@PathParam("start") String start) {
        JobOperator jo = BatchRuntime.getJobOperator();
        jo.start("deleteNotActivated", new Properties());
    }

    @GET
    @Path("{id}")
    public User getUser(@PathParam("id") Long id, @Context UriInfo uriInfo) throws UserNotFoundException, TweetNotFoundException {
        User u =  userService.getUser(id);
        String url = uriInfo.getBaseUriBuilder()
                .path(UserResource.class)
                .path(Long.toString(u.getId()))
                .build()
                .toString();
        u.getLinks().add(new Link(url,"self"));
        return u;
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
    @Path("{id}/following")
    public Set<User> getFollowing(@PathParam("id") Long id) throws UserNotFoundException, TweetNotFoundException {
        return userService.getUser(id).getFollowing();
    }

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

    @GET
    @Path("search/{username}")
    public List searchUser(@PathParam("username") String username){
        ArrayList<User> users = new ArrayList<>();
        for (User u: userService.getUsers()) {
            if(u.getUsername().toLowerCase().contains(username.toLowerCase())) {
                users.add(u);
            }
        }
        return users;
    }

}
