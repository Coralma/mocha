package com.mocha.cooperate.page.cust;

import java.util.List;

import com.coral.vaadin.view.template.sat.panel.ISectionPanel;
import com.coral.vaadin.view.template.sat.panel.impl.DefaultSectionPanel;
import com.coral.vaadin.view.template.sat.panel.impl.EditableSection;
import com.coral.vaadin.widget.Viewer;
import com.coral.vaadin.widget.WidgetFactory;
import com.coral.vaadin.widget.fields.FieldFactory;
import com.coral.vaadin.widget.fields.FieldStatus;
import com.coral.vaadin.widget.view.CommonViewer;
import com.google.common.collect.Lists;
import com.mocha.cooperate.SystemProperty;
import com.mocha.ib.model.Claim;
import com.mocha.ib.model.InsuranceCustomer;
import com.mocha.ib.model.Policy;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

public class CustomerHomeViewer extends CommonViewer implements Viewer {

//	private String width = SystemProperty.content_page_width;
	String width = "745px";
	private InsuranceCustomer customer;
	private Button editBtn = WidgetFactory.createButton("Edit");
	private ISectionPanel sectionPanel = new DefaultSectionPanel();
	
	public CustomerHomeViewer(InsuranceCustomer customer) {
		this.customer = customer;
	}
	
	@Override
	public void attach() {
//		String width = "745px";
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
		
		if("1".equals(customer.getCustomerType())) {
			fieldStatus = FieldStatus.create().setLabel("Customer Name").setPath("name").setType("String");
			sectionPanel.addField(fieldFactory.createDisplay(fieldStatus));
			
			fieldStatus = FieldStatus.create().setLabel("Contect Person").setPath("contectPerson").setType("String");
			sectionPanel.addField(fieldFactory.createDisplay(fieldStatus));
		} else {
			fieldStatus = FieldStatus.create().setLabel("Customer Name").setPath("name").setType("String").setWholeRow(true);
			sectionPanel.addField(fieldFactory.createDisplay(fieldStatus));
		}
		
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
		
		Layout policyLayout = buildPolicyLayout();
		this.addComponent(policyLayout);
		
		Layout claimLayout = buildClaimLayout();
		this.addComponent(claimLayout);
	}
	
	public Layout buildPolicyLayout() {
		Layout policyLayout = buildPortalLayout();
		Panel policyPanel = buildPanel("My Policies");
//		policyPanel.setHeight("280px");
//		policyPanel.addComponent(new VerticalLayout());
		List<Policy> policies = customer.getPolicy();
		if(policies.size() > 0) {
			for(int i=0; i< policies.size();i++) {
				Policy policy = policies.get(i);
				ISectionPanel sectionPanel = new DefaultSectionPanel();
				if(i > 0) {
					sectionPanel.addStyleName("portal-section-more");
				} else {
					sectionPanel.addStyleName("portal-section");
				}
				sectionPanel.setWidth(width);
				sectionPanel.setSpacing(false);
				FieldFactory fieldFactory = new FieldFactory(policy, true);
				
				FieldStatus fieldStatus = null;
				fieldStatus = FieldStatus.create().setLabel("Policy No").setPath("policyNo").setType("String");
				sectionPanel.addField(fieldFactory.create(fieldStatus));
				
				fieldStatus = FieldStatus.create().setLabel("Company").setPath("insuranceCompany").setType("String");
				sectionPanel.addField(fieldFactory.create(fieldStatus));
				
				fieldStatus = FieldStatus.create().setLabel("Category").setPath("category").setType("String");
				sectionPanel.addField(fieldFactory.create(fieldStatus));
				
				fieldStatus = FieldStatus.create().setLabel("Product").setPath("insuranceProduct").setType("String");
				sectionPanel.addField(fieldFactory.create(fieldStatus));
				
				fieldStatus = FieldStatus.create().setLabel("Effective Date").setPath("effectiveDate").setType("Date");
				sectionPanel.addField(fieldFactory.create(fieldStatus));
				
				fieldStatus = FieldStatus.create().setLabel("Expiry Date").setPath("expiryDate").setType("Date");
				sectionPanel.addField(fieldFactory.create(fieldStatus));
				
				fieldStatus = FieldStatus.create().setLabel("Premium").setPath("premium").setType("String");
				sectionPanel.addField(fieldFactory.create(fieldStatus));
				
				fieldStatus = FieldStatus.create().setLabel("Commission").setPath("commission").setType("String");
				sectionPanel.addField(fieldFactory.create(fieldStatus));
				
				sectionPanel.setReadOnly(true);
				policyPanel.addComponent(sectionPanel);
			}
		} else {
			VerticalLayout emptyContent = new VerticalLayout();
			emptyContent.setMargin(true);
			Label noRecordLabel = WidgetFactory.createLabel("No policy found");
			noRecordLabel.addStyleName("no-section-content");
			emptyContent.addComponent(noRecordLabel);
			policyPanel.addComponent(emptyContent);
		}
		policyLayout.addComponent(policyPanel);
		return policyLayout;
	}
	
	public Layout buildClaimLayout() {
		Layout claimLayout = buildPortalLayout();
		Panel claimPanel = buildPanel("My Claims");
//		claimPanel.setHeight("120px");
//		claimPanel.addComponent(new VerticalLayout());
		List<Policy> policies = customer.getPolicy();
		List<Claim> claims = Lists.newArrayList();
		for(Policy policy : policies) {
			claims.addAll(policy.getClaim());
		}
		if(claims.size() > 0) {
			for(int i=0; i < claims.size(); i++) {
				Claim claim = claims.get(i);
				ISectionPanel sectionPanel = new DefaultSectionPanel();
				if(i > 0) {
					sectionPanel.addStyleName("portal-section-more");
				} else {
					sectionPanel.addStyleName("portal-section");
				}
				FieldFactory fieldFactory = new FieldFactory(claim, true);
				sectionPanel.setWidth(width);
				sectionPanel.setSpacing(false);
				
				FieldStatus fieldStatus = null;
				fieldStatus = FieldStatus.create().setLabel("Status").setPath("status").setType("String").setCodeTableName("claim-status");
				sectionPanel.addField(fieldFactory.create(fieldStatus));
				
				fieldStatus = FieldStatus.create().setLabel("Claim Amount").setPath("claimAmount").setType("String");
				sectionPanel.addField(fieldFactory.create(fieldStatus));
				
				fieldStatus = FieldStatus.create().setLabel("Claim Reason").setPath("claimReason").setType("String").setWholeRow(true);
				sectionPanel.addField(fieldFactory.create(fieldStatus));

				sectionPanel.setReadOnly(true);
				claimPanel.addComponent(sectionPanel);
			}
		} else {
			VerticalLayout emptyContent = new VerticalLayout();
			emptyContent.setMargin(true);
			Label noRecordLabel = WidgetFactory.createLabel("No claim record found");
			noRecordLabel.addStyleName("no-section-content");
			emptyContent.addComponent(noRecordLabel);
			claimPanel.addComponent(emptyContent);
		}
		claimLayout.addComponent(claimPanel);
		return claimLayout;
	}
	/**
	 * Function layout.
	 * @return
	 */
	private Layout buildPortalLayout() {
		HorizontalLayout portalLayout = new HorizontalLayout();
		portalLayout.addStyleName("cust-home-dashboard");
		portalLayout.setSpacing(true);
		portalLayout.setWidth("768px");
		return portalLayout;
	}

	private Panel buildPanel(String caption) {
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

	/**
	 * @return the customer
	 */
	public InsuranceCustomer getCustomer() {
		return customer;
	}

	/**
	 * @param customer the customer to set
	 */
	public void setCustomer(InsuranceCustomer customer) {
		this.customer = customer;
	}

}
