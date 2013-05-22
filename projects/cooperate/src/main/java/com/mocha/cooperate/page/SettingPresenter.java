/**
 * 
 */
package com.mocha.cooperate.page;

import java.util.Locale;

import com.coral.foundation.core.impl.MochaEventBus;
import com.coral.foundation.security.basic.dao.BasicUserDao;
import com.coral.foundation.security.model.BasicUser;
import com.coral.foundation.spring.bean.SpringContextUtils;
import com.coral.vaadin.controller.PageChangeEvent;
import com.coral.vaadin.controller.Presenter;
import com.coral.vaadin.controller.RefreshMainPageEvent;
import com.coral.vaadin.widget.view.CommonPresenter;
import com.mocha.cooperate.PresenterProperty;
import com.mocha.cooperate.SystemProperty;
import com.mocha.cooperate.page.event.SettingListener;

/**
 * @author Administrator
 *
 */
public class SettingPresenter extends CommonPresenter implements Presenter {

	public BasicUserDao basicUserDao = SpringContextUtils.getBean(BasicUserDao.class);
	
	public SettingPresenter(MochaEventBus eventBus) {
		this.eventBus = eventBus;
		this.viewer = new SettingViewer(eventBus.getUser());
	}

	@Override
	public void bind() {
		final SettingViewer settingViewer = (SettingViewer) viewer;
		settingViewer.setListener(new SettingListener() {
			
			@Override
			public void userInfoChange() {
				BasicUser user = (BasicUser) settingViewer.getUser();
				Object newPhoto = settingViewer.getPhotoUploader().getValue();
				if(newPhoto != null) {
					user.setUserPhoto(newPhoto.toString());
				}
				basicUserDao.persist(user);
				settingViewer.getApplication().setUser(user);
				// return home
				refreshIndexPage();
			}

			@Override
			public void passwordChange(String newPassword) {
				BasicUser user = (BasicUser) settingViewer.getUser();
				user.setPassword(newPassword);
				basicUserDao.persist(user);
				settingViewer.getApplication().setUser(user);
				// return home
				refreshIndexPage();
			}
		});
	}
	
	public void refreshIndexPage() {
		RefreshMainPageEvent event = new RefreshMainPageEvent();
		eventBus.post(event);
//		PageChangeEvent changeEvent = new PageChangeEvent(PresenterProperty.INDEX);
//		changeEvent.setContentPresenterName(PresenterProperty.HOME);
//		eventBus.post(changeEvent);
	}
	
	@Override
	public String getPresenterName() {
		return PresenterProperty.SETTING;
	}
}
