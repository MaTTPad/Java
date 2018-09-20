package com.example.hibernate.dao;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

@Transactional
public class BasicDAO<T extends Serializable> {

    private Class<T> clazz;

    @PersistenceContext
    EntityManager entityManager;

    public final void setClazz(Class<T> clazzToSet) {
        this.clazz = clazzToSet;
    }

    public void create(T entity) throws DuplicateKeyException {
        try {
            entityManager.persist(entity);
        } catch (DataIntegrityViolationException e) {
            throw new DuplicateKeyException("Entity exists");
        }

    }

    public T update(T entity) {
        return entityManager.merge(entity);
    }

    public void delete(T entity) {
        entityManager.remove(entity);
    }



}
