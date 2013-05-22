/**
 * 
 */
package com.coral.vaadin.widget.view.builder;

import java.util.HashMap;
import java.util.Map;

import com.coral.foundation.md.model.ViewSection;
import com.coral.vaadin.widget.layout.BoxSectionLayout;
import com.coral.vaadin.widget.layout.DefaultSectionLayout;
import com.coral.vaadin.widget.layout.Section;
import com.coral.vaadin.widget.layout.SectionLayout;
import com.coral.vaadin.widget.layout.TabSectionLayout;
import com.coral.vaadin.widget.layout.TipBoxSectionLayout;
import com.vaadin.ui.Component;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.TabSheet;

/**
 * @author Administrator
 *
 */
public class SectionBuilder {
	
	Map<String,ComponentContainer> container = new HashMap<String, ComponentContainer>();

	public Section build(ViewSection viewSection, ComponentContainer parent) {
		Section section = null;
		if(viewSection.getType() == null) {
			section = new DefaultSectionLayout(viewSection.getLabel(), viewSection.getColumn());
//			section = new TipBoxSectionLayout(viewSection.getLabel(), viewSection.getColumn());
		} else if("box".equals(viewSection.getType())) {
			section = new BoxSectionLayout(viewSection.getLabel(), viewSection.getColumn());
		} else if("wrap".equals(viewSection.getType())) {
			section = new SectionLayout(viewSection.getLabel(), viewSection.getColumn(), 1, viewSection.getType());
		}  else if("tipbox".equals(viewSection.getType())) {
			section = new TipBoxSectionLayout(viewSection.getLabel(), viewSection.getColumn());
		} else if("tab".equals(viewSection.getType())) {
			section = buildTabSection(viewSection,parent);
		}

        return section;
	}
	
	private Section buildTabSection(ViewSection viewSection,ComponentContainer parent) {
		String group = viewSection.getGroup();
		TabSectionLayout tab = (TabSectionLayout) container.get(group);
		if(tab == null) {
			tab = new TabSectionLayout();
	        tab.setWidth("100%");
			container.put(group, tab);
			parent.addComponent(tab);
		}
		Section section = new DefaultSectionLayout(viewSection.getLabel(), viewSection.getColumn());
		section.setMargin(true);
		tab.addTab(section,viewSection.getLabel());
		return section;
	}
}
