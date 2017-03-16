package com.mehmet.kwetter.domain;

import javax.persistence.*;
import javax.validation.constraints.Size;

/**
 * Created by Mehmet on 3/1/2017.
 */
@Embeddable
public class UserDetail {
    @Size(max = 160)
    private String bio;
    private String location, website;

    public UserDetail() {
    }

    public UserDetail(String bio, String location, String website) {
        this.bio = bio;
        this.location = location;
        this.website = website;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

}
