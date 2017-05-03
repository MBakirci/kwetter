package com.mehmet.kwetter.service;

import com.mehmet.kwetter.dao.UserDao;
import com.mehmet.kwetter.domain.Token;
import com.mehmet.kwetter.domain.User;
import com.mehmet.kwetter.exception.AuthenticationException;
import com.mehmet.kwetter.exception.NotActivatedException;
import com.mehmet.kwetter.exception.UserNotFoundException;
import org.apache.commons.lang3.time.DateUtils;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.Date;

/**
 * Created by mehmet on 3-5-17.
 */
@Stateless
public class AuthenticationService {
    @Inject
    UserDao userDAO;

    @Inject
    AuthState authState;

    /**
     * Tries to see if the user already has a token, if not it tries to authenticate the user
     * (This method doesn't create a token, look at issueToken().
     *
     * @param username
     * @param password
     * @throws AuthenticationException
     */
    public boolean authenticateUser(String username, String password) throws AuthenticationException, UserNotFoundException, NotActivatedException {
        //Check if the authenticating user already has a valid token
        //if it doesn't create a new token for this user
        User user = userDAO.findByUsername(username);

        //Checko if user is activated by mail
        if (!user.isActivated()) {
            throw new NotActivatedException();
        }
        //Check if password is invalid
        if (!user.getPassword().equals(password) ) {
            throw new AuthenticationException("Incorrect Credentials");
        }
        return true;
    }

    /**
     * Creates a token and adds it to the requesting user
     *
     * @param username
     * @param token
     */
    public Token issueToken(String username, String token) {
        int tokenDuration = 10;
        Date expiryDate = DateUtils.addMinutes(new Date(), tokenDuration);

        Token t = new Token(username, token, expiryDate);
        authState.addToken(t);
        return t;
    }
}
