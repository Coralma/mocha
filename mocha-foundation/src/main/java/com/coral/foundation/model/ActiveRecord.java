package com.coral.foundation.model;

import java.util.Date;

public class ActiveRecord {

	private Long id;

	private Date createDate = new Date();

	private Date lastUpdateDate;
	
	private String abstractData;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the createDate
	 */
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 * @param createDate the createDate to set
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	/**
	 * @return the lastUpdateDate
	 */
	public Date getLastUpdateDate() {
		return new Date();
	}

	/**
	 * @param lastUpdateDate the lastUpdateDate to set
	 */
	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	/**
	 * @return the abstractData
	 */
	public String getAbstractData() {
		return abstractData;
	}

	/**
	 * @param abstractData the abstractData to set
	 */
	public void setAbstractData(String abstractData) {
		this.abstractData = abstractData;
	}

}
