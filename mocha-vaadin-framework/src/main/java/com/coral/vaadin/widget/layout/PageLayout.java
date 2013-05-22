/**
 * 
 */
package com.coral.vaadin.widget.layout;

import java.util.HashMap;
import java.util.Map;

import com.coral.vaadin.widget.field.ActionButton;
import com.coral.vaadin.widget.view.AbstractViewer;
import com.coral.vaadin.widget.view.builder.PageBuildHelper;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;

/**
 * @author Coral
 * 
 */
public abstract class PageLayout extends AbstractViewer {

	public VerticalLayout contentLayout;
	public VerticalLayout buttonLayout;
	public HorizontalLayout btnsLayout;
	public Map<String, ComponentContainer> sectionContainer = new HashMap<String, ComponentContainer>();

	public PageLayout() {
		this(null);
	}

	public PageLayout(String title) {
		this.setWidth("100%");
		this.setSpacing(true);
		contentLayout = new VerticalLayout();
		if (title != null) {
			VerticalLayout titleLayout = PageBuildHelper.buildPageTitle(title);
			this.addComponent(titleLayout);
		}
		contentLayout.setWidth("100%");
		contentLayout.setSpacing(true);
		super.addComponent(contentLayout);
	}

	@Override
	public void addComponent(Component c) {
		contentLayout.addComponent(c);
	}

	public void setButtons(ActionButton... buttons) {
		if (buttonLayout == null) {
			buttonLayout = new VerticalLayout();
			buttonLayout.setMargin(true, true, false, false);
			buttonLayout.setWidth("100%");
			super.addComponent(buttonLayout);
		}
		btnsLayout = new HorizontalLayout();
		btnsLayout.setSpacing(true);
		for (Button button : buttons) {
			btnsLayout.addComponent(button);
		}
		buttonLayout.addComponent(btnsLayout);
		buttonLayout.setComponentAlignment(btnsLayout, Alignment.MIDDLE_CENTER);
	}
	
	public void addButton(ActionButton button) {
		if (buttonLayout == null) {
			buttonLayout = new VerticalLayout();
			buttonLayout.setMargin(true, true, false, false);
			buttonLayout.setWidth("100%");
			super.addComponent(buttonLayout);
			btnsLayout = new HorizontalLayout();
			btnsLayout.setSpacing(true);
			buttonLayout.addComponent(btnsLayout);
			buttonLayout.setComponentAlignment(btnsLayout, Alignment.MIDDLE_CENTER);
		}
		btnsLayout.addComponent(button);
		
	}
}
