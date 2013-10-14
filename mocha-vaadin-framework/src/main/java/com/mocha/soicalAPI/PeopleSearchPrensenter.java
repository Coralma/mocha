package com.mocha.soicalAPI;

import java.lang.reflect.Field;

import com.coral.foundation.core.impl.MochaEventBus;
import com.coral.foundation.linkedin.LinkedinImpl;
import com.coral.foundation.oauth.APIKeys;
import com.coral.foundation.security.basic.dao.BasicUserDao;
import com.coral.foundation.security.model.BasicUser;
import com.coral.foundation.security.model.SoicalApp;
import com.coral.foundation.spring.bean.SpringContextUtils;
import com.coral.vaadin.controller.Presenter;
import com.coral.vaadin.widget.view.AppCommonPresenter;
import com.google.code.linkedinapi.client.constant.IndustryCodes;
import com.google.code.linkedinapi.client.oauth.LinkedInAccessToken;
import com.google.code.linkedinapi.schema.People;
import com.google.code.linkedinapi.schema.Person;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Select;

public class PeopleSearchPrensenter extends AppCommonPresenter implements Presenter {

	private BasicUserDao buDao = SpringContextUtils.getBean(BasicUserDao.class);

	public PeopleSearchPrensenter(MochaEventBus eventBus) {
		this.eventBus = eventBus;
		this.viewer = new PeopleSearchViewer();
	}

	@Override
	public void bind() {
		final PeopleSearchViewer peopelSearchView = (PeopleSearchViewer) this.viewer;
		peopelSearchView.attach();
		final Select select = peopelSearchView.getSelect();
		peopelSearchView.getSearchBtn().addListener(new ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				if (select.getValue() != null) {
					BasicUser bu = buDao.findUserByUserName(eventBus.getUser().getUserName());
					LinkedinImpl linkedinImpl = new LinkedinImpl();
					LinkedInAccessToken linkedinAccessToken = new LinkedInAccessToken(APIKeys.linkedinSystemAccessToken,
							APIKeys.linkedinSystemAccessTokenSecert);
					Class<IndustryCodes> linkedIndustryCodeClass = IndustryCodes.class;
					String selectValue = (String) select.getValue();
					try {
						Field field = linkedIndustryCodeClass.getDeclaredField(selectValue);
						// Object obj = linkedIndustryCodeClass.newInstance();
						selectValue = (String) field.get(null);
					}
					catch (SecurityException e) {
						e.printStackTrace();
					}
					catch (NoSuchFieldException e) {
						e.printStackTrace();
					}
					catch (IllegalAccessException e) {
						e.printStackTrace();
					}
					People matchedPeople = linkedinImpl.searchPeopleByIndustry(linkedinAccessToken, selectValue);
					peopelSearchView.buildSearchResultPanle(matchedPeople);
				}
			}
		});
	}

	@Override
	public String getPresenterName() {
		return null;
	}

}
