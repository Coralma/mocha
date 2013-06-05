/**
 * 
 */
package com.coral.foundation.jpa.search;

import java.util.List;

import com.google.common.collect.Lists;

public class SearchFilterFactory {

    public static SearchFilterBuilder buildFuzzySearchFilter(Class entityClass) {
		SearchFilterBuilder filterBuilder = new SearchFilterBuilder();
		filterBuilder.setRelationStatus(RelationStatus.OR);
		filterBuilder.setSearchEntityClass(entityClass);
		
		List<SearchFilter> searchFilters = Lists.newArrayList();
		filterBuilder.setSearchFilters(searchFilters);
		return filterBuilder;
	}
}
