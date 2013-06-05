/**
 * 
 */
package com.coral.foundation.jpa;

import java.util.List;

import com.coral.foundation.jpa.search.SearchFilterBuilder;

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
    
    List<E> fuzzySearch(SearchFilterBuilder filterBuilder);
}
