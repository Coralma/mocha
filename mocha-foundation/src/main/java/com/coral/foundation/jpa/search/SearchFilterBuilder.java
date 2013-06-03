/**
 * 
 */
package com.coral.foundation.jpa.search;

import java.util.List;

import com.google.common.collect.Lists;

/**
 * @author Coral
 *
 */
public class SearchFilterBuilder {

	public List<SearchFilter> searchFilters = Lists.newArrayList();
	private RelationStatus relationStatus = RelationStatus.AND;
	public Class searchEntityClass;
	/**
	 * @return the searchFilters
	 */
	public List<SearchFilter> getSearchFilters() {
		return searchFilters;
	}
	/**
	 * @param searchFilters the searchFilters to set
	 */
	public void setSearchFilters(List<SearchFilter> searchFilters) {
		this.searchFilters = searchFilters;
	}
	/**
	 * @return the searchEntityClass
	 */
	public Class getSearchEntityClass() {
		return searchEntityClass;
	}
	/**
	 * @param searchEntityClass the searchEntityClass to set
	 */
	public void setSearchEntityClass(Class searchEntityClass) {
		this.searchEntityClass = searchEntityClass;
	}
	/**
	 * @return the relationStatus
	 */
	public RelationStatus getRelationStatus() {
		return relationStatus;
	}
	/**
	 * @param relationStatus the relationStatus to set
	 */
	public void setRelationStatus(RelationStatus relationStatus) {
		this.relationStatus = relationStatus;
	}
	
}
