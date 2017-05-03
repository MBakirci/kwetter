package com.mehmet.kwetter.service;

import com.mehmet.kwetter.dao.UserDao;
import com.mehmet.kwetter.domain.RoleEnum;

import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * Created by mehmet on 3-5-17.
 */
@Stateless
public class AuthorizationService {

    @Inject
    UserDao userDAO;

    public RoleEnum getUserPermission(String username) {
        return userDAO.getUserPermission(username);
    }

}
