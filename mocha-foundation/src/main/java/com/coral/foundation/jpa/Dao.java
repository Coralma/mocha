/**
 * 
 */
package com.coral.foundation.jpa;

import java.util.List;

/**
 * @author Coral.Ma
 * 
 */
public interface Dao<E> {

    void persist(E entity);
    public E merge(E entity);
    void remove(E entity);
    void remove(Long id);

    E findById(Long id);
    
    List<E> findAll();
}
