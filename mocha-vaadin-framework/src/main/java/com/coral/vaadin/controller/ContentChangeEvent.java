/**
 * 
 */
package com.coral.vaadin.controller;

/**
 * @author Administrator
 *
 */
public class ContentChangeEvent {

	public String presenterName;
	public Long id;
	/**
	 * @return the presenterName
	 */
	public String getPresenterName() {
		return presenterName;
	}
	/**
	 * @param presenterName the presenterName to set
	 */
	public void setPresenterName(String presenterName) {
		this.presenterName = presenterName;
	}
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
}
