package com.mocha.cooperate;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.Cookie;

import org.vaadin.peter.contextmenu.ContextMenu;
import org.vaadin.peter.contextmenu.ContextMenu.ContextMenuItem;

import com.coral.foundation.constant.RuntimeConstant;
import com.coral.foundation.security.model.BasicUser;
import com.coral.vaadin.app.AbstractMainPage;
import com.coral.vaadin.controller.PageChangeEvent;
import com.coral.vaadin.controller.RefreshMainPageEvent;
import com.coral.vaadin.widget.component.SearchTextField;
import com.google.common.collect.Lists;
import com.google.common.eventbus.Subscribe;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.NativeButton;
import com.vaadin.ui.VerticalLayout;

public class CooperateMainPage extends AbstractMainPage implements Button.ClickListener {

	private static final long serialVersionUID = 5003822334158074903L;

	private static String TIMELINE= "timeline";
	private static String APPLICATION = "application";
//	private static String SIGNOUT = "sign out";
	private static String logoWidth = "160px";
	private static String headHeight = "38px";
	private List<Button> menuButtons = Lists.newArrayList();
	
	public CooperateMainPage() {
		super();
		eventBus.register(this);
	}
	
	@Override
	public PageChangeEvent indexPage() {
		PageChangeEvent changeEvent = new PageChangeEvent(PresenterProperty.INDEX);
		changeEvent.setContentPresenterName(PresenterProperty.HOME);
		return changeEvent;
	}
	
	@Subscribe
	public void refreshMainPage(RefreshMainPageEvent event) {
		this.getContent().removeAllComponents();
		initContent();
	}
	
	public void initContent() {
		// set application language and write to cookie. 
		BasicUser user = (BasicUser)getApplication().getUser();
		if(RuntimeConstant.SUPPORTED_LANGUAGES[1].equals(user.getLanguage())) {
			getApplication().setLocale(Locale.CHINESE);
		} else {
			getApplication().setLocale(Locale.ENGLISH);
		}
		if(response != null) {
			Cookie usernameCookie = new Cookie(SystemProperty.COOKIE_USERNAME, user.getUserName());
	        usernameCookie.setPath("/cooperate");
	        response.addCookie(usernameCookie);
	        Cookie languageCookie = new Cookie(SystemProperty.COOKIE_LANGUAGE, user.getLanguage());
	        languageCookie.setPath("/cooperate");
	        response.addCookie(languageCookie);
		}
		super.initContent();
	}
	
	@Override
	public void buttonClick(ClickEvent event) {
		Button selectedBtn = event.getButton();
		String data = (String) selectedBtn.getData();
		if(PresenterProperty.HOME.equals(data)) {
			PageChangeEvent changeEvent = indexPage();
			eventBus.post(changeEvent);
		} else if(TIMELINE.equals(data)) {
			PageChangeEvent changeEvent = new PageChangeEvent(PresenterProperty.TIMELINE);
			eventBus.post(changeEvent);
		} else if(APPLICATION.equals(data)) {
			
		}
	}

	@Override
	public Layout getWebHead() {
		VerticalLayout head = new VerticalLayout();
		head.setWidth("100%");
		head.addStyleName("mocha-head-bar");
		
		HorizontalLayout headContent = new HorizontalLayout();
		headContent.setWidth(pageWidth);
		headContent.setHeight(headHeight);
		
		HorizontalLayout logoLayout = new HorizontalLayout();
		logoLayout.setWidth(logoWidth);
		Label logo = new Label(message.getString("cooperate.Mocha"));
		logo.addStyleName("mocha-logo");
		logoLayout.addComponent(logo);
		headContent.addComponent(logoLayout);
		headContent.setComponentAlignment(logoLayout, Alignment.MIDDLE_LEFT);

		HorizontalLayout menuButtons = new HorizontalLayout();
		menuButtons.setWidth("300px");
		menuButtons.addStyleName("mocha-head-menu");
		menuButtons.setHeight(headHeight);
		menuButtons.addComponent(createButton(message.getString("cooperate.Home"), PresenterProperty.HOME,true));
		menuButtons.addComponent(createButton(message.getString("cooperate.TimeLine"), TIMELINE));
		menuButtons.addComponent(createButton(message.getString("cooperate.Application"), APPLICATION));
		headContent.addComponent(menuButtons);
		headContent.setComponentAlignment(menuButtons,Alignment.MIDDLE_LEFT);
		
		HorizontalLayout logInfo = new HorizontalLayout();
		logInfo.setHeight(headHeight);

		HorizontalLayout searchTextLayout = new HorizontalLayout();
		searchTextLayout.addStyleName("head-search-field");
		
		SearchTextField searchTextField = new SearchTextField();
		searchTextField.setWidth("200px");
		searchTextLayout.addComponent(searchTextField);
		logInfo.addComponent(searchTextLayout);

		Button settingBtn = createButton(eventBus.getUser().getRealName(), null);
		settingBtn.removeListener(this);
		loadContextMenu(settingBtn);
		logInfo.addComponent(settingBtn);
//		logInfo.addComponent(createButton(message.getString("cooperate.Signout"), SIGNOUT));
		headContent.addComponent(logInfo);
		headContent.setComponentAlignment(logInfo, Alignment.MIDDLE_RIGHT);
		
		headContent.setExpandRatio(logoLayout, 5);
		headContent.setExpandRatio(menuButtons, 150);
		headContent.setExpandRatio(logInfo, 10);
		
		head.addComponent(headContent);
		head.setComponentAlignment(headContent, Alignment.TOP_CENTER);
		return head;
	}

	public Button createButton(String caption, String data) {
		return createButton(caption, data, false);
	}

	public Button createButton(String caption, String data, boolean isSelected) {
		Button menuButton = new NativeButton(caption);
		menuButton.setData(data);
		menuButton.setHeight(headHeight);
		menuButton.addListener(this);
//		if(isSelected) {
//			menuButton.addStyleName("v-nativebutton-selected");
//		}
		menuButtons.add(menuButton);
		return menuButton;
	}
	
    @SuppressWarnings("serial")
	private ContextMenu loadContextMenu(final Button parentBtn) {
    	parentBtn.setDebugId("settingMenu");
        final ContextMenu cm = new ContextMenu();
		final ContextMenuItem profile = cm.addItem("Profile");
		final ContextMenuItem signOut = cm.addItem("Sign out");
		// Enable separator line under this item
		profile.setSeparatorVisible(true);
        parentBtn.addListener(new ClickListener() {
            private static final long serialVersionUID = -3199636718233539982L;
            public void buttonClick(final com.vaadin.ui.Button.ClickEvent event) {
                // put menu at bottom left of button
                cm.show(event.getClientX() - event.getRelativeX() - 30,
                        event.getClientY() - event.getRelativeY() + 38);
            }
        });
        cm.addListener(new org.vaadin.peter.contextmenu.ContextMenu.ClickListener() {
			@Override
			public void contextItemClick(
					org.vaadin.peter.contextmenu.ContextMenu.ClickEvent event) {
				final ContextMenuItem clickedItem = event.getClickedItem();
				if(profile.equals(clickedItem)) {
					PageChangeEvent changeEvent = new PageChangeEvent(PresenterProperty.INDEX);
					changeEvent.setContentPresenterName(PresenterProperty.SETTING);
					eventBus.post(changeEvent);
				} else if(signOut.equals(clickedItem)) {
					getApplication().setUser(null);
					getWindow().getApplication().close();
				}
			}
		});
        this.mainContent.addComponent(cm);
        return cm;
    }

	@Override
	public Layout getWebFoot() {
		VerticalLayout foot = new VerticalLayout();
		foot.addStyleName("mocha-app-foot");
		foot.setWidth("100%");
		foot.setHeight("80px");

		VerticalLayout footContent = new VerticalLayout();
		footContent.addStyleName("copy-right");
		footContent.setWidth(pageWidth);
		Label copyrightLabel = new Label("© Mocha business software platform. All rights reserved.");
		footContent.addComponent(copyrightLabel);
		
		foot.addComponent(footContent);
		foot.setComponentAlignment(footContent, Alignment.TOP_CENTER);
		return foot;
	}
}
