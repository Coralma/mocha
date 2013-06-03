package com.coral.foundation.jpa.search;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@SuppressWarnings({"unchecked","rawtypes"})
public class CommonSearchDao {

    @PersistenceContext
    protected EntityManager entityManager;
    
	public List<Object> searchByFilter(SearchFilterBuilder filterBuilder) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery criteria = builder.createQuery(filterBuilder.getSearchEntityClass());
		Root root = criteria.from(filterBuilder.getSearchEntityClass());
		criteria.select(root);
		Predicate predicate = null;
		predicate = buildPredicate(builder, root, filterBuilder);
		criteria.where(predicate);
		List<Object> result = entityManager.createQuery(criteria).getResultList();
		return result;
	}
    
	public Predicate buildPredicate(CriteriaBuilder builder, Root root, SearchFilterBuilder filterBuilder) {
		if(RelationStatus.AND.equals(filterBuilder.getRelationStatus())) {
			return builder.and(buildSubPredicate(builder, root, filterBuilder));
		} else if(RelationStatus.OR.equals(filterBuilder.getRelationStatus())) {
			return builder.or(buildSubPredicate(builder, root, filterBuilder));
		}
		return null;
	}
	
	private Predicate[] buildSubPredicate(CriteriaBuilder builder, Root root, SearchFilterBuilder filterBuilder) {
		List<SearchFilter> filters = filterBuilder.getSearchFilters();
		Predicate[] subPredicates = new Predicate[filters.size()];
		for(int i=0; i < filters.size(); i++) {
			SearchFilter filter = filters.get(i);
			if(SearchStatus.EQ.equals(filter.getSearchStatus())) {
				subPredicates[i] = builder.equal(root.get(filter.getPropertyName()), filter.getValue());
			}
		}
		return subPredicates;
	}
}