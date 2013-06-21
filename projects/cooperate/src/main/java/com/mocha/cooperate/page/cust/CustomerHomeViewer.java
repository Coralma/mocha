package com.mocha.cooperate.page.cust;

import com.coral.vaadin.view.template.sat.panel.ISectionPanel;
import com.coral.vaadin.view.template.sat.panel.impl.DefaultSectionPanel;
import com.coral.vaadin.view.template.sat.panel.impl.EditableSection;
import com.coral.vaadin.widget.Viewer;
import com.coral.vaadin.widget.WidgetFactory;
import com.coral.vaadin.widget.fields.FieldFactory;
import com.coral.vaadin.widget.fields.FieldStatus;
import com.coral.vaadin.widget.view.CommonViewer;
import com.mocha.cooperate.SystemProperty;
import com.mocha.ib.model.InsuranceCustomer;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

public class CustomerHomeViewer extends CommonViewer implements Viewer {

	private String width = SystemProperty.content_page_width;
	private InsuranceCustomer customer;
	private Button editBtn = WidgetFactory.createButton("Edit");
	private ISectionPanel sectionPanel = new DefaultSectionPanel();
	
	public CustomerHomeViewer(InsuranceCustomer customer) {
		this.customer = customer;
	}
	
	@Override
	public void attach() {
		String width = "745px";
		this.setWidth("770px");
		VerticalLayout customerInfoLayout = new VerticalLayout();
		customerInfoLayout.addStyleName("customer-home-info-layout");
		customerInfoLayout.setWidth(width);
		
		HorizontalLayout titleLayout = new HorizontalLayout();
		titleLayout.addStyleName("info-title");
		titleLayout.setWidth(width);
		Label title = WidgetFactory.createLabel(customer.getName() + " " + "Information");
		titleLayout.addComponent(title);
		
		titleLayout.addComponent(editBtn);
		titleLayout.setComponentAlignment(editBtn, Alignment.TOP_RIGHT);
		customerInfoLayout.addComponent(titleLayout);
		
		sectionPanel.addStyleName("customer-section");
		sectionPanel.setWidth(width);
		sectionPanel.setSpacing(false);
		FieldFactory fieldFactory = new FieldFactory(customer, true);
		FieldStatus fieldStatus = null;
		
		fieldStatus = FieldStatus.create().setLabel("Customer Name").setPath("name").setType("String");
		sectionPanel.addField(fieldFactory.createDisplay(fieldStatus));
		
		fieldStatus = FieldStatus.create().setLabel("Contect Person").setPath("contectPerson").setType("String");
		sectionPanel.addField(fieldFactory.createDisplay(fieldStatus));
		
		fieldStatus = FieldStatus.create().setLabel("Mobile").setPath("mobile").setType("String");
		sectionPanel.addField(fieldFactory.create(fieldStatus));
		
		fieldStatus = FieldStatus.create().setLabel("Phone").setPath("phone").setType("String");
		sectionPanel.addField(fieldFactory.create(fieldStatus));
		
		fieldStatus = FieldStatus.create().setLabel("Fax").setPath("fax").setType("String");
		sectionPanel.addField(fieldFactory.create(fieldStatus));
		
		fieldStatus = FieldStatus.create().setLabel("District").setPath("district").setType("String");
		sectionPanel.addField(fieldFactory.create(fieldStatus));

		fieldStatus = FieldStatus.create().setLabel("Postcode").setPath("postcode").setType("String").setChangeLine(true);
		sectionPanel.addField(fieldFactory.create(fieldStatus));
		
		fieldStatus = FieldStatus.create().setLabel("Address").setPath("address").setType("String").setWholeRow(true);
		sectionPanel.addField(fieldFactory.create(fieldStatus));
		
		
		sectionPanel.setReadOnly(true);
		customerInfoLayout.addComponent(sectionPanel);
		this.addComponent(customerInfoLayout);
		
		Layout policyLayout = buildPortalLayout();
		Panel policyPanel = buildPanel("My Policies");
		policyPanel.setHeight("280px");
		policyPanel.addComponent(new VerticalLayout());
		policyLayout.addComponent(policyPanel);
		this.addComponent(policyLayout);
		
		Layout claimLayout = buildPortalLayout();
		Panel claimPanel = buildPanel("My Claims");
		claimPanel.setHeight("120px");
		claimPanel.addComponent(new VerticalLayout());
		claimLayout.addComponent(claimPanel);
		this.addComponent(claimLayout);
	}
	
	public Layout buildPortalLayout() {
		HorizontalLayout portalLayout = new HorizontalLayout();
		portalLayout.addStyleName("app-dashboard");
		portalLayout.setSpacing(true);
		portalLayout.setWidth("768px");
		return portalLayout;
	}

	public Panel buildPanel(String caption) {
		Panel dash = new Panel();
		dash.setWidth("100%");
		dash.setCaption(caption);
//		dash.setHeight("300px");
		return dash;
	}
	
	@Override
	public String getViewerTitle() {
		return "Customer Dashboard";
	}

	/**
	 * @return the editBtn
	 */
	public Button getEditBtn() {
		return editBtn;
	}

	/**
	 * @param editBtn the editBtn to set
	 */
	public void setEditBtn(Button editBtn) {
		this.editBtn = editBtn;
	}

	/**
	 * @return the sectionPanel
	 */
	public ISectionPanel getSectionPanel() {
		return sectionPanel;
	}

	/**
	 * @param sectionPanel the sectionPanel to set
	 */
	public void setSectionPanel(ISectionPanel sectionPanel) {
		this.sectionPanel = sectionPanel;
	}

}
