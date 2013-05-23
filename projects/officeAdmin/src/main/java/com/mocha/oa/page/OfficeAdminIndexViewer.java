/**
 * 
 */
package com.mocha.oa.page;

import com.coral.vaadin.view.template.sat.panel.ISectionPanel;
import com.coral.vaadin.view.template.sat.panel.IViewPanel;
import com.coral.vaadin.view.template.sat.panel.impl.EntityViewPanel;
import com.coral.vaadin.widget.Viewer;
import com.coral.vaadin.widget.field.FieldStatus;
import com.coral.vaadin.widget.fields.CodeTableWidget;
import com.coral.vaadin.widget.fields.DateFieldWidget;
import com.coral.vaadin.widget.fields.FieldWidget;
import com.coral.vaadin.widget.fields.LongFieldWidget;
import com.coral.vaadin.widget.fields.StringAreaFieldWidget;
import com.coral.vaadin.widget.fields.StringFieldWidget;
import com.google.common.collect.Lists;
import com.mocha.oa.model.Leave;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Window.Notification;

/**
 * @author Coral.Ma
 *
 */
public class OfficeAdminIndexViewer extends EntityViewPanel implements Viewer {

	private static final long serialVersionUID = 3433135598691573395L;

	public OfficeAdminIndexViewer() {
		super();
		Leave leave = new Leave();
		leave.setComment("abc");
		leave.setType("Additional Leave");
		setEntity(leave);
	}
	
	
	@Override
	public void build() {
		final IViewPanel viewPanel = createViewPanel();

		ISectionPanel sectionPanel = null;
		FieldStatus fieldStatus;
		sectionPanel = createSectionPanel();
		sectionPanel.setLabel("Section Title");
		viewPanel.addSection(sectionPanel);
		
		fieldStatus = new FieldStatus();
		fieldStatus.setLabel("Comment");
		fieldStatus.setPath("comment");
		fieldStatus.setType("String");
		sectionPanel.addField(createFieldWidget(fieldStatus));
		
		fieldStatus = new FieldStatus();
		fieldStatus.setLabel("Date From");
		fieldStatus.setPath("dateFrom");
		fieldStatus.setType("Date");
		sectionPanel.addField(createFieldWidget(fieldStatus));
		
		
		
//		FieldWidget field = new StringFieldWidget("Comment");
////		Property property = new NestedMethodProperty(leave, "comment");
//		field.setProperty(createFieldProperty("comment"));
//		field.setRequired(true);
//		sectionPanel.addField(field, new FieldStatus());
//		
//		field = new CodeTableWidget("Type", Lists.newArrayList("Mandatory Leave","Additional Leave"));
//		field.setProperty(createFieldProperty("type"));
//		field.setRequired(true);
//		sectionPanel.addField(field, new FieldStatus());
//		
//		field = new DateFieldWidget("Date From");
//		field.setProperty(createFieldProperty("dateFrom"));
//		field.setRequired(true);
//		sectionPanel.addField(field, new FieldStatus());
//		
//		field = new DateFieldWidget("Date To");
//		field.setProperty(createFieldProperty("dateTo"));
//		field.setRequired(true);
//		sectionPanel.addField(field, new FieldStatus());
//
//		field = new LongFieldWidget("Duration");
//		field.setProperty(createFieldProperty("duration"));
//		field.setRequired(true);
//		FieldStatus f = new FieldStatus();
//		f.setChangeLine(true);
//		sectionPanel.addField(field, f);
//		
//		StringAreaFieldWidget areaField = new StringAreaFieldWidget("Type");
////		property = new NestedMethodProperty(leave, "type");
//		areaField.setProperty(createFieldProperty("type"));
//		areaField.setRequired(true);
//		f = new FieldStatus();
//		f.setWholeRow(true);
//		sectionPanel.addField(areaField, f);
		
//		Button button = new Button("Submit");
//		button.addListener(new ClickListener() {
//			@Override
//			public void buttonClick(ClickEvent event) {
//				viewPanel.validate();
//				getWindow().showNotification("Validate Error","Data validate error. Please check your input value.",Notification.TYPE_TRAY_NOTIFICATION);
//			}
//		});
//		viewPanel.addActionButton(button);
		addComponent(viewPanel);
	}
}
