package com.coral.foundation.persistence;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaBuilder.In;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.transaction.annotation.Transactional;

import com.coral.foundation.jpa.search.CommonSearchDao;
import com.coral.foundation.jpa.search.SearchFilterBuilder;
import com.coral.foundation.spring.bean.SpringContextUtils;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public abstract class BaseDao<T> {
    
    @PersistenceContext
    private EntityManager entityManager;
    
    public abstract Class<T> getEntityClass();

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**
     * Get a specific T using the id.
     */
    public T load(Object id) {
        return getEntityManager().find(getEntityClass(), id);
    }

    /**
     * Returns all the T objects.
     */
    public List<T> loadAll() {
        return loadAll(null, null);
    }
    
    public List<T> loadAll(Integer start, Integer limit) {
    	final CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        final CriteriaQuery<T> criteria = builder.createQuery(getEntityClass());
        final Root<T> root = criteria.from(getEntityClass());
        criteria.select(root);
        TypedQuery<T> query = getEntityManager().createQuery(criteria);
        if (start != null)
        	query.setFirstResult(start);
        if (limit != null)
        	query.setMaxResults(limit);
        return query.getResultList();
    }
    
    /**
     * Count all T objects
     */
    public int countAll() {
        final CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        final CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
        final Root<T> root = criteria.from(getEntityClass());
        criteria.select(builder.count(root));
        return getEntityManager().createQuery(criteria).getSingleResult()
                .intValue();
    }
    
    public int countBy(Map<String, Object> filterBy) {
    	final CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        final CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
        final Root<T> root = criteria.from(getEntityClass());
        criteria.select(builder.count(root));
        Expression<Boolean> rootExpression = createWhereClause(builder, root, filterBy);
        if (rootExpression != null)
        	criteria.where(rootExpression);
        return getEntityManager().createQuery(criteria).getSingleResult()
                .intValue();
    }
    
    /**
     * Return a list of T based on a certain property and its value.
     */
    public List<T> findBy(String property, Object value) {
    	return findBy(property, value, null, null);
    }
    
    public List<T> findBy(String property, Object value, Integer start, Integer limit) {
        return findBy(property, value, Lists.<Order>newArrayList(), start, limit);
    }
    
    public List<T> findBy(String property, Object value, List<Order> orders) {
    	return findBy(property, value, orders, null, null);
    }
    
    public List<T> findBy(String property, Object value, List<Order> orders, Integer start, Integer limit) {
    	Map<String, Object> map = Maps.newHashMap();
    	map.put(property, value);
    	return findByProperties(map, orders, start, limit);
    }

    /**
     * Return a list of T based on a property-value map, the prop-values are
     * combined in the where clause using and.
     */
    public List<T> findByProperties(Map<String, Object> filterBy) {
        return findByProperties(filterBy, Lists.<Order>newArrayList(), null, null);
    }
    
    public List<T> findByProperties(Map<String, Object> filterBy, Integer start, Integer limit) {
    	return findByProperties(filterBy, Lists.<Order>newArrayList(), start, limit);
    }
    
    public List<T> findByProperties(Map<String, Object> filterBy, List<Order> orders) {
    	return findByProperties(filterBy, orders, null, null);
    }
    
    public List<T> findByProperties(Map<String, Object> filterBy, List<Order> orders, Integer start, Integer limit) {
    	final CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        final CriteriaQuery<T> criteria = builder.createQuery(getEntityClass());
        final Root<T> root = criteria.from(getEntityClass());
        criteria.select(root);
        Expression<Boolean> rootExpression = createWhereClause(builder, root, filterBy);
        if (rootExpression != null)
        	criteria.where(rootExpression);
        List<javax.persistence.criteria.Order> cOrders = Lists.newArrayList();
        for (Order order : orders) {
        	cOrders.add(order.isAscending() ? builder.asc(root.get(order.getProperty())) : 
        									  builder.desc(root.get(order.getProperty())));
        }
        if (!cOrders.isEmpty())
        	criteria.orderBy(cOrders);
        TypedQuery<T> query = getEntityManager().createQuery(criteria);
        if (start != null)
        	query.setFirstResult(start);
        if (limit != null)
        	query.setMaxResults(limit);
        return query.getResultList();
    }

    /**
     * Removes t, first removes the dependent objects referenced by t.
     */
    @Transactional
    public void delete(T t) {
    	getEntityManager().remove(t);
    }
    
    @Transactional
    public void remove(Long id) {
    	T t = load(id);
    	if(t != null) {
    		delete(t);
    	}
    }

    /**
     * Calls {@link #remove(Object)} for each t in ts
     */
    @Transactional
    public void delete(List<T> ts) {
        for (T t : ts) {
            delete(t);
        }
    }

    /**
     * Calls {@link EntityManager#merge(Object)}.
     */
    @Transactional
    public T merge(T t) {
        T merged = getEntityManager().merge(t);
        return merged;
    }

    /**
     * Calls {@link EntityManager#persist(Object)}.
     */
//    @Transactional
    public void persist(T t) {
        getEntityManager().persist(t);
    }

    public void flush() {
    	getEntityManager().flush();
    }

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private Expression<Boolean> createWhereClause(CriteriaBuilder builder, Root<T> root, Map<String, Object> filterBy) {
		List<Predicate> predicates = Lists.newArrayList();
	    for (String property : filterBy.keySet()) {
	        Object value = filterBy.get(property);
	        Predicate predicate = null;
	        if (value instanceof Filter) {
	        	Filter filter = (Filter)value;
	        	String[] propertySplitted = property.split("\\.");
	        	Path<Comparable> path = null;
        		for (String segment : propertySplitted) {
        			if (path == null)
        				path = root.get(segment);
        			else
        				path = path.get(segment);
        		}
        		
	        	switch (filter.getType()) {
	    			case GREATER_AND_EQUAL:
	    				predicate = builder.<Comparable>greaterThanOrEqualTo(
	    						path, 
	    						(Comparable)filter.getValue());
	    				break;
	    			case LESS_AND_EQUAL:
	    				predicate = builder.<Comparable>lessThanOrEqualTo(
	    						path, 
	    						(Comparable)filter.getValue());
	    				break;
	    			case IN:
	    				In<Comparable> in = builder.in(path);
	    				Collection<Comparable> values = (Collection<Comparable>) filter.getValue();
	    				for (Comparable inValue : values)
	    					in = in.value(inValue);
	    				predicate = in;
	    				break;
    				default:
    					Preconditions.checkState(false);
    					break;
	        	}
	        } else {
	        	 predicate = builder.equal(root.get(property), value);       	
	        }
	        
	        predicates.add(predicate);
	    }
	    
	    int size = predicates.size();
	    if (size == 0)
	    	return null;
	    else {
	    	return builder.and(predicates.toArray(new Predicate[0]));
	    }	    
	}

	protected CommonSearchDao getCommonSearchDao() {
		CommonSearchDao commonSearchDao = SpringContextUtils.getBean("commonSearchDao", CommonSearchDao.class);
		return commonSearchDao;
	}

	public List<T> fuzzySearch(SearchFilterBuilder filterBuilder) {
		if(filterBuilder == null) {
			return loadAll();
		} else {
			return getCommonSearchDao().searchByFilter(filterBuilder);
		}
	}
}