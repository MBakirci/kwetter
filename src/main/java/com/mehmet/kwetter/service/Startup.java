package com.mehmet.kwetter.service;

import com.mehmet.kwetter.model.User;
import com.mehmet.kwetter.model.UserDetails;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

/**
 * Created by Mehmet on 3/4/2017.
 */
@javax.ejb.Startup
@Singleton
public class Startup {
    @Inject
    private UserService service;

    public Startup() {
    }

    @PostConstruct
    private void initData() {
        User u = new User("Mehmet", "geen locatie", new UserDetails("ik ben Mehmet en woon in Breda", "Breda", ""));
        service.addUser(u);
    }
}
