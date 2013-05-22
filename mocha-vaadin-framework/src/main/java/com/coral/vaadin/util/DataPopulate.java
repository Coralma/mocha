package com.coral.vaadin.util;

import com.coral.foundation.security.basic.dao.BasicRoleDao;
import com.coral.foundation.security.basic.dao.BasicUserDao;
import com.coral.foundation.security.model.BasicRole;
import com.coral.foundation.security.model.BasicUser;
import com.coral.foundation.spring.bean.SpringContextUtils;

public class DataPopulate {

	private static BasicUserDao userDao = SpringContextUtils
			.getBean(BasicUserDao.class);

	private static BasicRoleDao roleDao = SpringContextUtils
			.getBean(BasicRoleDao.class);

	public static void initDate() {

		if (userDao.findAll().size() == 0) {
			BasicUser basicUser=new BasicUser();
			basicUser.setUserName("root");
			basicUser.setPassword("root");
			BasicRole basicRole=new BasicRole();
			basicRole.setRoleName("Admin-Role");
			basicRole.getUser().add(basicUser);
			roleDao.persist(basicRole);
			
		}

	}

}
