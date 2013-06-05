package com.mocha.crm.dao.impl;
import java.util.List;

import javax.annotation.Resource;

import com.mocha.crm.dao.*;
import com.mocha.crm.model.*;
import com.coral.foundation.jpa.impl.JpaDao;
import com.coral.foundation.jpa.search.CommonSearchDao;
import com.coral.foundation.jpa.search.RelationStatus;
import com.coral.foundation.jpa.search.SearchFilter;
import com.coral.foundation.jpa.search.SearchFilterBuilder;
import com.coral.foundation.jpa.search.SearchFilterFactory;
import com.coral.foundation.spring.bean.SpringContextUtils;
import com.coral.foundation.utils.StrUtils;
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
	
//	public List<Customer> fuzzySearch(String condition) {
//		List<Customer> customers = Lists.newArrayList();
//		if(StrUtils.isEmpty(condition)) {
//			customers = findAll();
//		} else {
//			SearchFilterBuilder filterBuilder = SearchFilterFactory.buildFuzzySearchFilter(Customer.class);
//			filterBuilder.getSearchFilters().add(SearchFilter.like("name", condition));
//			filterBuilder.getSearchFilters().add(SearchFilter.like("contectPerson", condition));
//			filterBuilder.getSearchFilters().add(SearchFilter.like("district", condition));
//			filterBuilder.getSearchFilters().add(SearchFilter.like("postcode", condition));
//			filterBuilder.getSearchFilters().add(SearchFilter.like("address", condition));
//			filterBuilder.getSearchFilters().add(SearchFilter.like("mobile", condition));
//			filterBuilder.getSearchFilters().add(SearchFilter.like("email", condition));
//			customers = getCommonSearchDao().searchByFilter(filterBuilder);
//		}
//		return customers;
//	}
}

