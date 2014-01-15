package com.mocha.template.general;

import java.util.List;

import com.google.common.collect.Lists;
import com.mocha.template.IAppActionSection;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;

public class GeneralAppActionSection extends VerticalLayout implements IAppActionSection {
	
	private List<Button> mainButtonList = Lists.newArrayList();
	private List<Button> attachedButtonList = Lists.newArrayList();

	public GeneralAppActionSection() {
		this.addStyleName("app-section-content");
	}
	
	public void attach() {
		HorizontalLayout actionLayout = new HorizontalLayout();
		actionLayout.setSpacing(true);
		for(Button attachBtn : attachedButtonList) {
			actionLayout.addComponent(attachBtn);
		}
		for(Button mainBtn : mainButtonList) {
			actionLayout.addComponent(mainBtn);
		}
		this.addComponent(actionLayout);
	}

	@Override
	public void addMainButton(Button mainButton) {
		mainButtonList.add(mainButton);		
	}

	@Override
	public void addAttachedButton(Button attachedButton) {
		attachedButtonList.add(attachedButton);
	}
}
