/**
 * 
 */
package com.coral.vaadin.view.template.sat.panel.impl;

import java.util.List;

import com.coral.vaadin.view.template.sat.panel.ISectionPanel;
import com.coral.vaadin.widget.Field;
import com.coral.vaadin.widget.Result;
import com.google.common.collect.Lists;
import com.vaadin.ui.VerticalLayout;

/**
 * @author Coral
 *
 */
public class SingleColumnSectionPanel extends VerticalLayout implements ISectionPanel {
	
	public List<Field> fields = Lists.newArrayList();
	
	public SingleColumnSectionPanel() {
		this.setWidth("100%");
		this.setSpacing(true);
		this.addStyleName("single_column_section_panel");
	}
	

	@Override
	public String getLabel() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setLabel(String label) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addField(Field field) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Result> validate() {
		// TODO Auto-generated method stub
		return null;
	}

}
