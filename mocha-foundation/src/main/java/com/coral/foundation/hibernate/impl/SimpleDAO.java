/**
 * 
 */
package com.coral.foundation.hibernate.impl;

import java.io.Serializable;
import java.util.List;

/**
 * @author Coral
 *
 */
public interface SimpleDAO<T, ID extends Serializable> {
 
    T findById(ID id, boolean lock);
 
    List<T> findAll();
 
//    List<T> findByExample(T exampleInstance);
 
    T makePersistent(T entity);
 
    void makeTransient(T entity);

}
