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
public class NotifyLineDaoService {

	public NotifyLineDao notifyLineDao = SpringContextUtils.getBean(NotifyLineDao.class);
	
	public List<NotifyLine> loadNotifyLine(BasicUser basicUser) {
		List<NotifyLine> userNotifyLines = Lists.newArrayList();
		List<NotifyLine> notifyLines = notifyLineDao.findAll();
		for(NotifyLine notifyLine : notifyLines) {
			if(BasicHelper.isCurrentUser(basicUser,notifyLine.getNotifiedUser())) {
				userNotifyLines.add(notifyLine);
			}
		}
		return userNotifyLines;
	}
}