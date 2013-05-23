/**
 * 
 */
package com.mocha.oa.page;

import java.util.List;

import com.coral.vaadin.view.template.sat.panel.impl.SearchPanel;
import com.coral.vaadin.widget.field.FieldStatus;
import com.google.common.collect.Lists;
import com.mocha.oa.model.Leave;

/**
 * @author Coral.Ma
 *
 */
public class OfficeAdminSearchViewer extends SearchPanel {
	
	private List leaves = Lists.newArrayList();
	
	public void build() {
		FieldStatus fieldStatus;
		fieldStatus = FieldStatus.create().setLabel("Type").setPath("type").setType("String").setRequired(true).setCodeTableName("leave_type");
		createFieldWidget(fieldStatus);

		fieldStatus = FieldStatus.create().setLabel("Date From").setPath("dateFrom").setType("Date").setRequired(true).setChangeLine(true);
		createFieldWidget(fieldStatus);
		
		fieldStatus = FieldStatus.create().setLabel("Date To").setPath("dateTo").setType("Date").setRequired(true);
		createFieldWidget(fieldStatus);
	}
	
	public Class getEntityCardClass() {
		return EntityQueryCard.class;
	}

	@Override
	public List getEntityList() {
		return leaves;
	}

	@Override
	public void setValue(Object value) {
		if(value != null) {
			leaves = (List<Leave>) value;
		}
	}

}
