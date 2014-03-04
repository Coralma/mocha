/**
 * 
 */
package com.mocha.cooperate.service;

import java.util.List;

import com.coral.foundation.security.model.BasicUser;
import com.coral.foundation.spring.bean.SpringContextUtils;
import com.coral.vaadin.widget.helper.BasicHelper;
import com.google.common.collect.Lists;
import com.mocha.cooperate.basic.dao.NotifyLineDao;
import com.mocha.cooperate.model.NotifyLine;

/**
 * @author Coral.Ma
 *
 */
public class NotifyLineService {

	public NotifyLineDao notifyLineDao = SpringContextUtils.getBean(NotifyLineDao.class);
	
	public List<NotifyLine> loadNotifyLine(BasicUser basicUser, int page) {
//		List<NotifyLine> userNotifyLines = Lists.newArrayList();
//		List<NotifyLine> notifyLines = notifyLineDao.loadAll();
//		for(NotifyLine notifyLine : notifyLines) {
//			if(BasicHelper.isCurrentUser(basicUser,notifyLine.getNotifiedUser())) {
//				userNotifyLines.add(notifyLine);
//			}
//		}
		List<NotifyLine> notifyLines = notifyLineDao.loadNotifyLine(basicUser, page);
		return notifyLines;
	}
	
	public int loadNotifyNumber(BasicUser basicUser) {
		return notifyLineDao.loadNotifyNumber(basicUser);
	}
	
	public void readAllNotify(BasicUser basicUser) {
		notifyLineDao.readAllNotify(basicUser);
	}
	
	public NotifyLine loadNotifyByStatus() {
		//TODO
		return null;
	}
	
	public NotifyLine loadNotifyByDiscuss() {
		//TODO
		return null;
	}
	
	public NotifyLine loadNotifyByTodo() {
		//TODO
		return null;
	}
}
