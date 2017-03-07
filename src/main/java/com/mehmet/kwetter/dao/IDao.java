package com.mehmet.kwetter.dao;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Mehmet on 3/1/2017.
 */
public interface IDao<T, PK extends Serializable> {
    /**
     * @param t
     * @return true when passed, false when failed
     */
    boolean create(T t);

    /**
     * This method is used to Get the object by them id
     *
     * @param id
     * @return Found object T by id
     */
    T find(PK id);

    /**
     * This method is used to Delete the given t of type T(Object)
     *
     * @param t
     * @return The updated object T
     */
    T update(T t);

    /**
     * This method is used to Delete the given t of type T(Object)
     *
     * @param t this is the given object
     */
    void delete(T t);

    /**
     * @return a list of founded given objects of T
     */
    List<T> findAll();
}
