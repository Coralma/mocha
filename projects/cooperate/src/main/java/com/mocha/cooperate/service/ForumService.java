/**
 * 
 */
package com.mocha.cooperate.service;

import java.util.List;

import com.coral.foundation.spring.bean.SpringContextUtils;
import com.mocha.cooperate.basic.dao.DiscussDao;
import com.mocha.cooperate.model.Discuss;

/**
 * @author Coral
 *
 */
public class ForumService {

	private DiscussDao discussDao = SpringContextUtils.getBean(DiscussDao.class);
	
	public List<Discuss> queryLastThreeNotification() {
		return discussDao.queryLastThreeNotification();
	}
	
	public List<Discuss> queryLastThreeQuestion() {
		return discussDao.queryLastThreeQuestion();
	}
	
	public List<Discuss> queryLastThreeDiscuss() {
		return discussDao.queryLastThreeDiscuss();
	}
	
	public List<Discuss> queryTopic(String type, int pageNum) {
		return discussDao.queryTopic(type, pageNum);
	}
}
