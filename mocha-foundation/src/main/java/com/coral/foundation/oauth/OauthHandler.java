package com.coral.foundation.oauth;

import javax.servlet.http.HttpServletRequest;

import com.coral.foundation.security.basic.dao.BasicUserDao;
import com.coral.foundation.spring.bean.SpringContextUtils;

public abstract class OauthHandler {
	
	BasicUserDao buDao=SpringContextUtils.getBean(BasicUserDao.class);
	HttpServletRequest request;
	
	public OauthHandler(HttpServletRequest request){
		this.request=request;
	}

	abstract void saveUserAuthenToken(HttpServletRequest request);
}
