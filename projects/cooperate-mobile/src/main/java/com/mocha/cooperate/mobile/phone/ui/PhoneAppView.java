package com.mocha.cooperate.mobile.phone.ui;

import com.mocha.mobile.controller.MobileView;
import com.vaadin.addon.touchkit.ui.NavigationButton;
import com.vaadin.addon.touchkit.ui.NavigationView;
import com.vaadin.addon.touchkit.ui.VerticalComponentGroup;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Button.ClickListener;

public class PhoneAppView extends NavigationView implements MobileView, ClickListener {

	private NavigationButton faButton = new NavigationButton();
	private NavigationButton ecButton = new NavigationButton();
	private NavigationButton adButton = new NavigationButton();
	private NavigationButton appStoreButton = new NavigationButton();
	
	@Override
	public void attach() {
		super.attach();
		setCaption("Application");
		
		CssLayout newTopicLayout = new CssLayout();
		newTopicLayout.setSizeFull();
		newTopicLayout.setMargin(true);
		
		VerticalComponentGroup availableAppGroup = new VerticalComponentGroup();
		availableAppGroup.setCaption("Available Apps");
		availableAppGroup.setMargin(true);

		faButton.setWidth("100%");
		faButton.addListener(this);
		faButton.setIcon(new ThemeResource("icons/icon_fa.png"));
		faButton.setCaption("Financial Advisor");
		availableAppGroup.addComponent(faButton);
		
		ecButton.setWidth("100%");
		ecButton.addListener(this);
		ecButton.setIcon(new ThemeResource("icons/icon_ec.png"));
		ecButton.setCaption("E-Commerce");
		availableAppGroup.addComponent(ecButton);
		
		adButton.setWidth("100%");
		adButton.addListener(this);
		adButton.setIcon(new ThemeResource("icons/icon_ad.png"));
		adButton.setCaption("Mocha Administration");
		availableAppGroup.addComponent(adButton);
		
		newTopicLayout.addComponent(availableAppGroup);
		
		VerticalComponentGroup group = new VerticalComponentGroup();
		group.setCaption("Apps Store");
		group.setMargin(true);
		appStoreButton.addListener(this);
		appStoreButton.setIcon(new ThemeResource("icons/app-store.png"));
		appStoreButton.setCaption("Get More Apps");
		group.addComponent(appStoreButton);
		
		newTopicLayout.addComponent(group);
		
		this.setContent(newTopicLayout);
	}

	@Override
	public void buttonClick(ClickEvent event) {
		getNavigationManager().navigateTo(new WorkingView());
	}
}
