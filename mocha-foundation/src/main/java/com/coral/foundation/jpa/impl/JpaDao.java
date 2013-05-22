/**
 * 
 */
package com.coral.foundation.jpa.impl;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.transaction.annotation.Transactional;

import com.coral.foundation.jpa.Dao;
import com.coral.foundation.model.BaseEntity;

/**
 * @author Coral.Ma
 * 
 */
public class JpaDao<E extends BaseEntity> implements Dao<E> {
    protected Class<?> entityClass;

    @PersistenceContext
    protected EntityManager entityManager;

    public JpaDao() {
    	Object superClass = getClass().getGenericSuperclass();
    	if(superClass instanceof ParameterizedType) {
	        ParameterizedType genericSuperclass = (ParameterizedType) superClass;
	        Type[] types = genericSuperclass.getActualTypeArguments();
	        if(types.length > 0) {
	        	this.entityClass = (Class) types[0];
	        }
    	}
    }

    @Transactional
    public void persist(E entity) {
    	if(entity.getID() == null) {
    		entityManager.persist(entity);
    	} else {
    		merge(entity);
    	}
    }

    @Transactional
    public E merge(E entity) {
        E merged = entityManager.merge(entity);
        return merged;
    }
    
    @Transactional
    public void remove(E entity) {
        entityManager.remove(entity);
    }
    
    @Transactional
    public void remove(Long id) {
    	if(id != null) {
	    	Object data = findById(id);
	        entityManager.remove(data);
    	}
    }
    public E findById(Long id) {
        return (E) entityManager.find(entityClass, id);
    }
    
    public List<E> findAll() {
        final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        final CriteriaQuery criteria = builder.createQuery((entityClass));
        final Root root = criteria.from(entityClass);
        criteria.select(root);
        return entityManager.createQuery(criteria).getResultList();
    }
}