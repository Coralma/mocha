/**
 * 
 */
package com.mocha.cooperate.widget;

import java.util.List;

import com.google.common.collect.Lists;
import com.vaadin.ui.Component;
import com.vaadin.ui.VerticalLayout;

/**
 * Will coding later.
 * @author Coral.Ma
 */
public class TabComponent extends VerticalLayout {
	
	protected List<Component> tabs = Lists.newArrayList();
	protected List<String> tabCaptions = Lists.newArrayList();
	
	public TabComponent() {
		
	}
	
	public void addTab(Component tab, String tabCaption) {
		tabs.add(tab);
		tabCaptions.add(tabCaption);
	}
}
