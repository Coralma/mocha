/**
 * 
 */
package com.coral.vaadin.controller;

/**
 * @author Administrator
 *
 */
public class PageChangeEvent {

	public String presenterName;
	public String contentPresenterName;
	public Long id;
	
	public PageChangeEvent() {
		
	}
	
	public PageChangeEvent(String presenterName) {
		this(presenterName, null);
	}
	
	public PageChangeEvent(String presenterName, Long id) {
		this.presenterName = presenterName;
		this.id = id;
	}
	
	public String getPresenterName() {
		return presenterName;
	}
	public void setPresenterName(String presenterName) {
		this.presenterName = presenterName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the contentPresenterName
	 */
	public String getContentPresenterName() {
		return contentPresenterName;
	}

	/**
	 * @param contentPresenterName the contentPresenterName to set
	 */
	public void setContentPresenterName(String contentPresenterName) {
		this.contentPresenterName = contentPresenterName;
	}
}
