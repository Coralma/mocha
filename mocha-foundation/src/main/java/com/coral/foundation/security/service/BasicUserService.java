/**
 * 
 */
package com.coral.foundation.security.service;

import com.coral.foundation.security.basic.dao.BasicUserDao;
import com.coral.foundation.security.model.BasicUser;
import com.coral.foundation.spring.bean.SpringContextUtils;

/**
 * @author Coral.Ma
 *
 */
public class BasicUserService {

	BasicUserDao basicUserDao = SpringContextUtils.getBean(BasicUserDao.class);
	
	public BasicUser loadUserById(Long id) {
		return basicUserDao.findById(id);
	}
}
