package com.mehmet.kwetter.exception;

/**
 * Created by Mehmet on 3/16/2017.
 */
public class TweetNotFoundException extends Exception {
    public TweetNotFoundException() {
        super("Tweet not found");
    }

    public TweetNotFoundException(String message) {
        super(message);
    }
}
