package com.mehmet.kwetter.domain;

/**
 * Created by mehmet on 3-5-17.
 */
public class ErrorToken {
    private final String error = "invalid_grant";
    private final String error_description = "bad credentials";

    public ErrorToken() {
    }

    public String getError() {
        return error;
    }

    public String getError_description() {
        return error_description;
    }
}
