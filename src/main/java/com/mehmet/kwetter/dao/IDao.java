package com.mehmet.kwetter.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

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
    T find(Object id);

    /**
     * @param t
     * @return true when passed, false when failed
     */
    T create(T t);

    /**
     * This method is used to Delete the given t of type T(Object)
     *
     * @param t
     * @return The updated object T
     */
    T update(final T t);

    /**
     * This method is used to Delete the given t of type T(Object)
     *
     * @param t this is the given object
     */
    void delete(Object t);

}
