/**
 * 
 */
package com.coral.foundation.md.model;

import java.io.Serializable;
import java.util.List;

import com.google.common.collect.Lists;

/**
 * @author Coral
 *
 */
public class Mocha implements Serializable {

	private static final long serialVersionUID = -1541598742536340585L;
	private String name;
	private String entityPackage;
	private String daoIntfPackage; 
	private String daoImplPackage;
	private String servicePackage;
	private List<Entity> entityList = Lists.newArrayList();
	private List<View> viewList = Lists.newArrayList();
	private List<App> apps = Lists.newArrayList();
	private List<CodeTable> codeTableList = Lists.newArrayList();
	private List<Menu> menuList = Lists.newArrayList();
	private List<MobileMenu> mobileMenuList = Lists.newArrayList();
	private List<ReportDef> reportDefList = Lists.newArrayList();
	
	public List<Menu> getMenuList() {
		return menuList;
	}
	public void setMenuList(List<Menu> menuList) {
		this.menuList = menuList;
	}
	/**
	 * @return the entityPackage
	 */
	public String getEntityPackage() {
		return entityPackage;
	}
	/**
	 * @param entityPackage the entityPackage to set
	 */
	public void setEntityPackage(String entityPackage) {
		this.entityPackage = entityPackage;
	}
	/**
	 * @return the daoIntfPackage
	 */
	public String getDaoIntfPackage() {
		return daoIntfPackage;
	}
	/**
	 * @param daoIntfPackage the daoIntfPackage to set
	 */
	public void setDaoIntfPackage(String daoIntfPackage) {
		this.daoIntfPackage = daoIntfPackage;
	}
	/**
	 * @return the daoImplPackage
	 */
	public String getDaoImplPackage() {
		return daoImplPackage;
	}
	/**
	 * @param daoImplPackage the daoImplPackage to set
	 */
	public void setDaoImplPackage(String daoImplPackage) {
		this.daoImplPackage = daoImplPackage;
	}
	/**
	 * @return the entityList
	 */
	public List<Entity> getEntityList() {
		return entityList;
	}
	/**
	 * @param entityList the entityList to set
	 */
	public void setEntityList(List<Entity> entityList) {
		this.entityList = entityList;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the servicePackage
	 */
	public String getServicePackage() {
		return servicePackage;
	}
	/**
	 * @param servicePackage the servicePackage to set
	 */
	public void setServicePackage(String servicePackage) {
		this.servicePackage = servicePackage;
	}
	/**
	 * @return the codeTableList
	 */
	public List<CodeTable> getCodeTableList() {
		return codeTableList;
	}
	/**
	 * @param codeTableList the codeTableList to set
	 */
	public void setCodeTableList(List<CodeTable> codeTableList) {
		this.codeTableList = codeTableList;
	}
	/**
	 * @return the mobileMenuList
	 */
	public List<MobileMenu> getMobileMenuList() {
		return mobileMenuList;
	}
	/**
	 * @param mobileMenuList the mobileMenuList to set
	 */
	public void setMobileMenuList(List<MobileMenu> mobileMenuList) {
		this.mobileMenuList = mobileMenuList;
	}
	/**
	 * @return the viewList
	 */
	public List<View> getViewList() {
		return viewList;
	}
	/**
	 * @param viewList the viewList to set
	 */
	public void setViewList(List<View> viewList) {
		this.viewList = viewList;
	}
	/**
	 * @return the apps
	 */
	public List<App> getApps() {
		return apps;
	}
	/**
	 * @param apps the apps to set
	 */
	public void setApps(List<App> apps) {
		this.apps = apps;
	}
	/**
	 * @return the reportDefList
	 */
	public List<ReportDef> getReportDefList() {
		return reportDefList;
	}
	/**
	 * @param reportDefList the reportDefList to set
	 */
	public void setReportDefList(List<ReportDef> reportDefList) {
		this.reportDefList = reportDefList;
	}
}
