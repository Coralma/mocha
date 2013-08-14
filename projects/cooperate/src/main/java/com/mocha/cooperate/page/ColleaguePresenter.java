/**
 * 
 */
package com.mocha.cooperate.page;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.coral.foundation.core.impl.MochaEventBus;
import com.coral.foundation.security.basic.dao.BasicUserDao;
import com.coral.foundation.security.model.BasicUser;
import com.coral.foundation.spring.bean.SpringContextUtils;
import com.coral.foundation.utils.StrUtils;
import com.coral.vaadin.controller.PageChangeEvent;
import com.coral.vaadin.controller.Presenter;
import com.coral.vaadin.widget.view.CommonPresenter;
import com.mocha.cooperate.PresenterProperty;

/**
 * @author Coral
 *
 */
public class ColleaguePresenter extends CommonPresenter implements Presenter {

	private BasicUserDao basicUserDao = SpringContextUtils.getBean(BasicUserDao.class);
	public ColleaguePresenter(MochaEventBus eventBus) {
		this.eventBus = eventBus;
		List<BasicUser> basicUsers = basicUserDao.loadAll();
		this.viewer = new ColleagueView(basicUsers);
	}
	
	@Override
	public void bind() {
		final ColleagueView colleagueView = (ColleagueView) viewer;
		colleagueView.setListener(new ColleagueListener() {
			@Override
			public void cardClick(BasicUser user) {
				PageChangeEvent pageChangeEvent = new PageChangeEvent(PresenterProperty.TIMELINE);
				Map<Object,Object> context = new HashMap<Object,Object>();
				context.put("selectedUser", user);
				eventBus.setContext(context);
				eventBus.post(pageChangeEvent);
			}

			@Override
			public void searchUser(String text) {
				if(StrUtils.isEmpty(text)) {
					List<BasicUser> listUsers = basicUserDao.loadAll();
					colleagueView.setListUsers(listUsers);
					colleagueView.buildListUserPanel();
				} else {
					List<BasicUser> fuzzyListUsers = basicUserDao.fuzzyUserSearch(text);
					colleagueView.setListUsers(fuzzyListUsers);
					colleagueView.buildListUserPanel();
				}
			}
		});
	}
	
	public interface ColleagueListener {
		public void cardClick(BasicUser user);
		public void searchUser(String text);
	}

	@Override
	public String getPresenterName() {
		// TODO Auto-generated method stub
		return null;
	}

}
