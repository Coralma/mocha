/**
 * 
 */
package com.mocha.cooperate.service;

import java.util.List;

import com.coral.foundation.security.basic.dao.BasicUserDao;
import com.coral.foundation.security.model.BasicUser;
import com.coral.foundation.spring.bean.SpringContextUtils;

/**
 * @author Coral.Ma
 *
 */
public class UserService {

	private BasicUserDao basicUserDao = SpringContextUtils.getBean(BasicUserDao.class);
	
	public List<BasicUser> loadAllBasicUser() {
		return basicUserDao.findAll();
	}
}
