package com.mocha.crm.dao.impl;
import java.util.List;

import javax.annotation.Resource;

import com.mocha.crm.dao.*;
import com.mocha.crm.model.*;
import com.coral.foundation.jpa.impl.JpaDao;
import com.coral.foundation.jpa.search.CommonSearchDao;
import com.coral.foundation.jpa.search.SearchFilter;
import com.coral.foundation.jpa.search.SearchFilterBuilder;
import com.coral.foundation.spring.bean.SpringContextUtils;
import com.google.common.collect.Lists;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
  * CustomerDaoImpl is a auto Generated class. Please don't modify it.
  * @author Coral
  */
public class CustomerDaoImpl extends JpaDao<Customer> implements CustomerDao {
	
	Logger log=LoggerFactory.getLogger(CustomerDaoImpl.class);
	public CustomerDaoImpl() {
		super();
		log.debug(""+CustomerDaoImpl.class);
	}
	
	public List<Customer> fuzzySearch(String condition) {
		CommonSearchDao commonSearchDao = SpringContextUtils.getBean("commonSearchDao", CommonSearchDao.class);
		SearchFilterBuilder filterBuilder = new SearchFilterBuilder();
		filterBuilder.setSearchEntityClass(Customer.class);
		
		List<SearchFilter> searchFilters = Lists.newArrayList();
		searchFilters.add(SearchFilter.eq("name", condition));
		filterBuilder.setSearchFilters(searchFilters);
		
		List<Object> objs = commonSearchDao.searchByFilter(filterBuilder);
		List<Customer> customers = Lists.newArrayList();
//		customers.add(e);
		return customers;
	}
}

