package com.mehmet.kwetter.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


/**
 * Created by Mehmet on 3/1/2017.
 */

public abstract class DaoFacade<T> implements IDao<T> {

    private final Class<T> type;

    public DaoFacade(Class<T> entityClass) {
        this.type = entityClass;
    }

    @PersistenceContext
    protected EntityManager em;

    @Override
    public EntityManager getEm() {
        return em;
    }

    @Override
    public void setEm(EntityManager em) {
        this.em = em;
    }

    @Override
    public T find(final Object id) {
        return (T) em.find(type, id);
    }

    @Override
    public T create(final T t) {
        this.em.persist(t);
        return t;
    }

    @Override
    public T update(final T t) {
        return em.merge(t);
    }

    @Override
    public void delete(final Object id) {
        em.remove(this.em.getReference(type, id));
    }
}
