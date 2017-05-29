/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mehmet.kwetter.socket;

import com.mehmet.kwetter.domain.Tweet;
import com.mehmet.kwetter.domain.User;
import com.mehmet.kwetter.exception.TweetNotFoundException;
import com.mehmet.kwetter.exception.UserNotFoundException;
import com.mehmet.kwetter.service.UserService;

import javax.ejb.Singleton;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author jgeenen
 */
@ServerEndpoint(
        value = "/echo-socket/{userid}",
        encoders = JsonEncoder.class,
        decoders = JsonDecoder.class,
        configurator = HttpSessionProvider.class
)
public class ServerEchoEndpoint {

    private static final Logger LOG = Logger.getLogger(ServerEchoEndpoint.class.getName());

    private static Map<Long, Session> users = new HashMap();

    @Inject
    UserService userService;

    /**
     * stock glassfish 4.0 doesn't support dependency injection in Endpoints, which is a bug.
     * Hence the EJB lookup.
     */
    private static final EchoBean ECHO_BEAN;

    static {
        final String name = "java:global/kwetter/EchoBean";
        try {
            ECHO_BEAN = (EchoBean) InitialContext.doLookup(name);
        } catch (NamingException ex) {
            throw new IllegalStateException(ex);
        }

    }

    private HttpSession httpSession;

    private Session session;

    @OnOpen
    public void onOpen(EndpointConfig endpointConfig, Session session, @PathParam("userid") Long userid) throws UserNotFoundException, TweetNotFoundException {
        users.put(userid, session);
        this.httpSession = HttpSessionProvider.provide(endpointConfig);
        this.session = session;
        LOG.log(Level.INFO, "onOpen: endpointConfig: {0}, session: {1}", new Object[]{endpointConfig, session});
    }

    @OnMessage
    public void onMessage(Session session, Tweet tweet) throws UserNotFoundException, TweetNotFoundException {
        notifyFollowers(tweet);
        LOG.log(Level.INFO, "received message with text: {0}", tweet);
    }

    @OnClose
    public void onClose(Session session, CloseReason closeReason) {
        LOG.log(Level.INFO, "session {0} closed with reason {1}", new Object[]{session, closeReason});
        try {
            httpSession.invalidate();
        } catch (IllegalStateException ise) {
            //swallow: httpSession allready expired
        }
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        LOG.log(
                Level.WARNING,
                new StringBuilder("an error occured for session ").append(session).toString(),
                throwable
        );
    }

    private void notifyFollowers(Tweet tweet) throws UserNotFoundException, TweetNotFoundException {
        User user = userService.getUser(tweet.getUser().getId());
        for (User u : user.getFollowers()) {
            Session s = users.get(u.getId());
            if (s != null) {
                try {
                    s.getAsyncRemote().sendText(new JsonEncoder().encode(tweet));
                } catch (EncodeException e) {
                    LOG.log(Level.INFO, "sending of message got wrong {0}", e.getMessage());
                }
            }
        }

    }

    public Session getSession() {
        return session;
    }

    public HttpSession getHttpSession() {
        return httpSession;
    }

}
