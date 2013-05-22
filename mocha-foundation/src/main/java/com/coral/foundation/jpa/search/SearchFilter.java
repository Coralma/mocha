/**
 * 
 */
package com.coral.foundation.jpa.search;

/**
 * @author Coral
 *
 */
public class SearchFilter {

	public enum SearchStatus {
		EQ, BT, LT, LIKE
	}
	
	private SearchStatus searchStatus;
	private String propertyName;
	private Object value;
	
	public static SearchFilter eq(String propertyName, Object value) {
		SearchFilter searchFilter = new SearchFilter();
		searchFilter.setSearchStatus(SearchStatus.EQ); 
		return searchFilter;
	}
	

	/**
	 * @param searchStatus the searchStatus to set
	 */
	public void setSearchStatus(SearchStatus searchStatus) {
		this.searchStatus = searchStatus;
	}


	/**
	 * @return the propertyName
	 */
	public String getPropertyName() {
		return propertyName;
	}


	/**
	 * @param propertyName the propertyName to set
	 */
	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}


	/**
	 * @return the value
	 */
	public Object getValue() {
		return value;
	}


	/**
	 * @param value the value to set
	 */
	public void setValue(Object value) {
		this.value = value;
	}


	/**
	 * @return the searchStatus
	 */
	public SearchStatus getSearchStatus() {
		return searchStatus;
	}
}
