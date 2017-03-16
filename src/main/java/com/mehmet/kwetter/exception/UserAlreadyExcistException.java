package com.mehmet.kwetter.exception;

/**
 * Created by Mehmet on 3/16/2017.
 */
public class UserAlreadyExcistException extends Exception {
    public UserAlreadyExcistException() {
        super("User Already excist");
    }

    public UserAlreadyExcistException(String message) {
        super(message);
    }
}
