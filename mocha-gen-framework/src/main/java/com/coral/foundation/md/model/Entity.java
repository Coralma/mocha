/**
 * 
 */
package com.coral.foundation.md.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author coral.ma
 *
 */
public class Entity implements Serializable {

	private static final long serialVersionUID = -226202860480357243L;
	public String entityName;
	public String tableName;
	public List<Property> properties = new ArrayList<Property>();
	public List<View> views = new ArrayList<View>();
	public boolean needView;
	public boolean specifySeq = true;
	public String entityClass;
	public Mocha mocha;
	
	public Class getEntityType() {
		try {
			return Class.forName(entityClass);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * @return the entityName
	 */
	public String getEntityName() {
		return entityName;
	}
	/**
	 * @param entityName the entityName to set
	 */
	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}
	/**
	 * @return the properties
	 */
	public List<Property> getProperties() {
		return properties;
	}
	/**
	 * @param properties the properties to set
	 */
	public void setProperties(List<Property> properties) {
		this.properties = properties;
	}
	
	public void addProperty(Property p) {
		properties.add(p);
	}
	/**
	 * @return the needView
	 */
	public boolean isNeedView() {
		return needView;
	}
	/**
	 * @param needView the needView to set
	 */
	public void setNeedView(boolean needView) {
		this.needView = needView;
	}
	/**
	 * @return the views
	 */
	public List<View> getViews() {
		return views;
	}
	/**
	 * @param views the views to set
	 */
	public void setViews(List<View> views) {
		this.views = views;
	}

	/**
	 * @param entityClass the entityClass to set
	 */
	public void setEntityClass(String entityClass) {
		this.entityClass = entityClass;
	}

	/**
	 * @return the tableName
	 */
	public String getTableName() {
		return tableName;
	}

	/**
	 * @param tableName the tableName to set
	 */
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	/**
	 * @return the entityClass
	 */
	public String getEntityClass() {
		return entityClass;
	}

	/**
	 * @return the mocha
	 */
	public Mocha getMocha() {
		return mocha;
	}

	/**
	 * @param mocha the mocha to set
	 */
	public void setMocha(Mocha mocha) {
		this.mocha = mocha;
	}

	/**
	 * @return the specifySeq
	 */
	public boolean isSpecifySeq() {
		return specifySeq;
	}

	/**
	 * @param specifySeq the specifySeq to set
	 */
	public void setSpecifySeq(boolean specifySeq) {
		this.specifySeq = specifySeq;
	}

}
