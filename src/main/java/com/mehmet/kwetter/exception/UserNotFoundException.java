package com.mehmet.kwetter.exception;

/**
 * Created by Mehmet on 3/16/2017.
 */
public class UserNotFoundException extends Exception  {
    public UserNotFoundException(){
        super("User not found");
    }

    public UserNotFoundException(String message) {
        super(message);
    }
}
