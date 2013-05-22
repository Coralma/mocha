/**
 * 
 */
package com.coral.vaadin.controller;

import com.coral.foundation.jpa.Dao;

/**
 * @author Administrator
 *
 */
public interface DaoFactory {

	public Dao getDao(Class entityClass);
}
