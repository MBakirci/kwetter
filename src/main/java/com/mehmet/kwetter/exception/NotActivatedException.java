package com.mehmet.kwetter.exception;

/**
 * Created by mehmet on 3-5-17.
 */
public class NotActivatedException extends Exception {
    public NotActivatedException() {
        super("User not activated");
    }
}
