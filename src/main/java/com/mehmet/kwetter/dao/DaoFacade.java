package com.mehmet.kwetter.dao;

import javax.persistence.EntityManager;


/**
 * Created by Mehmet on 3/1/2017.
 */

public abstract class DaoFacade<T> implements IDao<T> {

    private final Class<T> type;

    public DaoFacade(Class<T> entityClass) {
        this.type = entityClass;
    }

    protected abstract EntityManager getEntityManager();

    @Override
    public void setEm(EntityManager em) {
        em = getEntityManager();
    }

    @Override
    public T find(final Object id) {
        return getEntityManager().find(type, id);
    }

    @Override
    public T create(final T t) {
        this.getEntityManager().persist(t);
        return t;
    }

    @Override
    public T update(final T t) {
        return getEntityManager().merge(t);
    }

    @Override
    public void delete(final Object id) {
        getEntityManager().remove(this.getEntityManager().getReference(type, id));
    }
}
