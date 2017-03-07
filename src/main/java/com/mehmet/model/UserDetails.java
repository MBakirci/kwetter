package com.mehmet.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Mehmet on 3/1/2017.
 */
@Entity
@Table(name = "user_detail")
public class UserDetails {

    @Id
    private long id;
    private String bio, location, website;

    public UserDetails() {
    }

    public UserDetails(String bio, String location, String website) {
        this.bio = bio;
        this.location = location;
        this.website = website;
    }

    public long getId() {
        return id;
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
