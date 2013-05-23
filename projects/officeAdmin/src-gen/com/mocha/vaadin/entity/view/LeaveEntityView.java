package com.mocha.vaadin.entity.view;
import com.coral.vaadin.view.template.sat.panel.ISectionPanel;
import com.coral.vaadin.view.template.sat.panel.IViewPanel;
import com.coral.vaadin.view.template.sat.panel.IActionPanel;
import com.coral.vaadin.view.template.sat.panel.impl.EntityViewPanel;
import com.coral.vaadin.widget.Viewer;
import com.coral.vaadin.widget.field.FieldStatus;
import com.mocha.oa.model.Leave;

public class LeaveEntityView extends EntityViewPanel implements Viewer {
	
	public LeaveEntityView() {
		super();
	}

	public void build() {
		final IViewPanel viewPanel = createViewPanel();
		ISectionPanel sectionPanel;
		FieldStatus fieldStatus;
		
		sectionPanel = createSectionPanel();
		sectionPanel.setLabel("Leave Apply");
		viewPanel.addSection(sectionPanel);
		
		fieldStatus = FieldStatus.create().setLabel("Type").setPath("type").setType("String").setRequired(true).setCodeTableName("leave_type");
		sectionPanel.addField(createFieldWidget(fieldStatus));
		
		fieldStatus = FieldStatus.create().setLabel("Date From").setPath("dateFrom").setType("Date").setRequired(true).setChangeLine(true);
		sectionPanel.addField(createFieldWidget(fieldStatus));
		
		fieldStatus = FieldStatus.create().setLabel("Date To").setPath("dateTo").setType("Date").setRequired(true);
		sectionPanel.addField(createFieldWidget(fieldStatus));
		
		fieldStatus = FieldStatus.create().setLabel("Duration").setPath("duration").setType("Long").setRequired(true).setChangeLine(true);
		sectionPanel.addField(createFieldWidget(fieldStatus));
		
		fieldStatus = FieldStatus.create().setLabel("Comment").setPath("comment").setType("String").setStyle("textarea").setWholeRow(true);
		sectionPanel.addField(createFieldWidget(fieldStatus));
		
		IActionPanel actionPanel = createActionPanel();
		viewPanel.setActionPanel(actionPanel);
		actionPanel.addButton(createActionButton("save", "Save", "Save"));
		actionPanel.addButton(createActionButton("back", "Back", ""));
		
		addComponent(viewPanel);
	}

	public Class getEntityClass() {
		return Leave.class;
	}
	
	public String getViewerTitle() {
		return "ViewName";
	}

}

