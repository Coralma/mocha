package com.mocha.hr.view;

import com.coral.vaadin.widget.Viewer;
import com.coral.vaadin.widget.fields.FieldStatus;
import com.mocha.hr.model.LeaveApply;
import com.mocha.template.IAppActionSection;
import com.mocha.template.IAppSection;
import com.mocha.template.IAppView;
import com.mocha.template.general.GeneralEntityView;
import com.mocha.template.general.utils.GeneralTemplateCst;
import com.vaadin.ui.Layout;
import com.vaadin.ui.VerticalLayout;

public class LeaveApplyCreateView extends GeneralEntityView implements Viewer {
	
	public LeaveApplyCreateView() {
		setEntity(new LeaveApply());
	}

	@Override
	public IAppView buildEditView() {
		final IAppView viewPanel = createGeneralAppView();
		viewPanel.setLabel("Leave Application");
		IAppSection sectionPanel;
		FieldStatus fieldStatus;
		
		sectionPanel = createGeneralAppSection(null);
		viewPanel.addSection(sectionPanel);
		
		fieldStatus = FieldStatus.create().setLabel("Leave Type").setPath("leaveType").setType("String").setCodeTableName("ins-customer-type").setChangeLine(true);
		sectionPanel.addField(createFieldWidget(fieldStatus));
		
		fieldStatus = FieldStatus.create().setLabel("Date From").setPath("leaveDateFrom").setType("Date");
		sectionPanel.addField(createFieldWidget(fieldStatus));
		
		fieldStatus = FieldStatus.create().setLabel("Date To").setPath("leaveDateTo").setType("Date");
		sectionPanel.addField(createFieldWidget(fieldStatus));
		
		fieldStatus = FieldStatus.create().setLabel("Duration").setPath("leaveDuration").setType("String");
		sectionPanel.addField(createFieldWidget(fieldStatus));
		
		fieldStatus = FieldStatus.create().setLabel("Comment").setPath("comment").setType("String").setStyle("textarea").setWholeRow(true);
		sectionPanel.addField(createFieldWidget(fieldStatus));
		
		IAppActionSection actionSection = createGeneralAppActionSection();
		viewPanel.setActionSection(actionSection);
		actionSection.addMainButton(createActionButton("save", "Save", "Save"));
		actionSection.addMainButton(createActionButton("back", "Back", "Back"));
		
		return viewPanel;
	}

	@Override
	public Layout buildControlView() {
		VerticalLayout layout = new VerticalLayout();
		layout.setWidth(GeneralTemplateCst.right_control_layout_width);
		return layout;
	}

}
