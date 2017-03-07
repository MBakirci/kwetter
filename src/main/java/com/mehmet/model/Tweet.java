package com.mehmet.model;

import javax.persistence.*;

/**
 * @author Mehmet
 */
@Entity
@Table(name = "TWEET")
public class Tweet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String tweet;
    private String DateTime;

}
