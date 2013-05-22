/**
 * 
 */
package com.coral.foundation.md.model;

import java.io.Serializable;
import java.util.List;
import java.util.Properties;

import com.google.common.collect.Lists;

/**
 * @author Coral.Ma
 *
 */
public class Resource implements Serializable {

	public List<String> entityXMLPaths;
	public String srcPath = "src";
	public String entityGenPath = "src-gen";
	public String ddlGenPath = "src-gen";
	public String daoGenPath = "src/gen/dao/java";
	public String presenterGenPath = "src/gen/presenter/java/";
	public String serviceGenPath = "src-gen";
	public boolean domainSeperate = false;
	public String componentName;
	public Properties properties;

	public boolean isDomainSeperate() {
		return domainSeperate;
	}

	public void setDomainSeperate(boolean domainSeperate) {
		this.domainSeperate = domainSeperate;
	}
	
	public String getEntityGenPath() {
		return entityGenPath;
	}
	public void setEntityGenPath(String entityGenPath) {
		this.entityGenPath = entityGenPath;
	}
	public String getDdlGenPath() {
		return ddlGenPath;
	}
	public void setDdlGenPath(String ddlGenPath) {
		this.ddlGenPath = ddlGenPath;
	}
	public String getDaoGenPath() {
		return daoGenPath;
	}
	public void setDaoGenPath(String daoGenPath) {
		this.daoGenPath = daoGenPath;
	}
	public String getServiceGenPath() {
		return serviceGenPath;
	}
	public void setServiceGenPath(String serviceGenPath) {
		this.serviceGenPath = serviceGenPath;
	}
	public List<String> getEntityXMLPaths() {
		return entityXMLPaths;
	}
	public void setEntityXMLPaths(List<String> entityXMLPaths) {
		this.entityXMLPaths = entityXMLPaths;
	}

	/**
	 * @return the properties
	 */
	public Properties getProperties() {
		return properties;
	}

	/**
	 * @param properties the properties to set
	 */
	public void setProperties(Properties properties) {
		this.properties = properties;
	}

	/**
	 * @return the componentName
	 */
	public String getComponentName() {
		return componentName;
	}

	/**
	 * @param componentName the componentName to set
	 */
	public void setComponentName(String componentName) {
		this.componentName = componentName;
	}

	/**
	 * @return the srcPath
	 */
	public String getSrcPath() {
		return srcPath;
	}

	/**
	 * @param srcPath the srcPath to set
	 */
	public void setSrcPath(String srcPath) {
		this.srcPath = srcPath;
	}

	/**
	 * @return the presenterGenPath
	 */
	public String getPresenterGenPath() {
		return presenterGenPath;
	}

	/**
	 * @param presenterGenPath the presenterGenPath to set
	 */
	public void setPresenterGenPath(String presenterGenPath) {
		this.presenterGenPath = presenterGenPath;
	}
}
