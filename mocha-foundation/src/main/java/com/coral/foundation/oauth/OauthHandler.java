package com.coral.foundation.oauth;

import javax.servlet.http.HttpServletRequest;

import com.coral.foundation.security.basic.dao.BasicUserDao;
import com.coral.foundation.security.basic.dao.SoicalAppDao;
import com.coral.foundation.spring.bean.SpringContextUtils;

public abstract class OauthHandler {
	
	BasicUserDao buDao=SpringContextUtils.getBean(BasicUserDao.class);
	SoicalAppDao saDao=SpringContextUtils.getBean(SoicalAppDao.class);
	
	HttpServletRequest request;
	
	public OauthHandler(HttpServletRequest request){
		this.request=request;
	}

	abstract boolean saveUserAuthenToken(HttpServletRequest request);
}
