package com.mehmet.kwetter.model;

import javax.persistence.*;

/**
 * @author Mehmet
 */
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String userName;
    private String profilePicLocation;
    @OneToOne
    @MapsId
    private UserDetails userDetails;

    public User() {
    }

    public User(String userName, String profilePicLocation, UserDetails userDetails) {
        this.userName = userName;
        this.profilePicLocation = profilePicLocation;
        this.userDetails = userDetails;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getProfilePicLocation() {
        return profilePicLocation;
    }

    public void setProfilePicLocation(String profilePicLocation) {
        this.profilePicLocation = profilePicLocation;
    }

    public UserDetails getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(UserDetails userDetails) {
        this.userDetails = userDetails;
    }

}
