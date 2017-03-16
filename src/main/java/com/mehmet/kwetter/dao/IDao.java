package com.mehmet.kwetter.dao;

import com.mehmet.kwetter.exception.TweetNotFoundException;
import com.mehmet.kwetter.exception.UserAlreadyExcistException;
import com.mehmet.kwetter.exception.UserNotFoundException;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by Mehmet on 3/1/2017.
 */
public interface IDao<T> {
    /**
     * @return a list of founded given objects of T
     */
    List<T> findAll();


    /**
     * This method is used to Get the object by them id
     *
     * @param id
     * @return Found object T by id
     */
    T find(Object id) throws UserNotFoundException, TweetNotFoundException;

    /**
     * @param t
     * @return true when passed, false when failed
     */
    T create(T t) throws UserAlreadyExcistException;

    /**
     * This method is used to Update the given t of type T(Object)
     *
     * @param t
     * @return The updated object T
     */
    T update(final T t) throws UserNotFoundException;

    /**
     * This method is used to Delete the founded T by them id
     *
     * @param id this is the id for the object
     */
    void delete(Object id) throws UserNotFoundException, TweetNotFoundException;


    void setEm(EntityManager em);
}
