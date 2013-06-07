package com.mocha.vaadin.entity.presenter;

import com.coral.foundation.core.impl.MochaEventBus;
import com.coral.foundation.spring.bean.SpringContextUtils;
import com.coral.vaadin.controller.Presenter;
import com.coral.vaadin.view.template.sat.panel.ISectionPanel;
import com.coral.vaadin.widget.fields.CodeTableWidget;
import com.coral.vaadin.widget.view.AppCommonPresenter;
import com.mocha.ib.dao.InsuranceCustomerDao;
import com.mocha.ib.model.InsuranceCustomer;
import com.mocha.vaadin.entity.view.InsCustomerView;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

public class InsCustomerViewPresenter extends AppCommonPresenter implements Presenter {

	private InsuranceCustomerDao dao = SpringContextUtils.getBean(InsuranceCustomerDao.class);
	
	public InsCustomerViewPresenter(MochaEventBus eventBus) {
		this.eventBus = eventBus;
		InsCustomerView newView = new InsCustomerView();
		Object entity = this.eventBus.getContext().get("Entity");
		if(entity != null) {
			newView.setEntity(entity);
			eventBus.resetContext();
		}
		this.viewer = newView;
	}

	@Override
	public String getPresenterName() {
		return "InsCustomerView";
	}
	
	@Override
	public void bind() {
		final InsCustomerView customerView = (InsCustomerView) viewer;
		initViewer(customerView);
		
		customerView.getField("customerType").addListener(new ValueChangeListener() {
			@Override
			public void valueChange(ValueChangeEvent event) {
				customerTypeSelect(event.getProperty().getValue().toString(), customerView);
			}
		});
		
		//TODO add and edit your action.
		Button saveButton = viewer.getButton("save");
		saveButton.addListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				save();
			}
		});
		Button backButton = viewer.getButton("back");
		backButton.addListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				back();
			}
		});
	}
	
	public void initViewer(InsCustomerView viewer) {
		InsuranceCustomer customerEntity = (InsuranceCustomer) viewer.getEntity();
		String type = customerEntity.getCustomerType();
		if(type == null) {
			type = "1";
		}
		CodeTableWidget ctWidget = (CodeTableWidget) viewer.getField("customerType");
		ctWidget.setNullSelectionAllowed(false);
		ctWidget.setValue(type);
		customerTypeSelect(type, viewer);
	}
	
	public void customerTypeSelect(String type, InsCustomerView viewer) {
		ISectionPanel indiCustInfoSection = viewer.getSection("indiCustInfo");
		ISectionPanel companyCustInfoSection = viewer.getSection("companyCustInfo");
		ISectionPanel companyContectPersonSection = viewer.getSection("companyContectPerson");
		if(type.equals("1")) {
			indiCustInfoSection.setVisible(false);
			companyCustInfoSection.setVisible(true);
			companyContectPersonSection.setVisible(true);
		} else {
			indiCustInfoSection.setVisible(true);
			companyCustInfoSection.setVisible(false);
			companyContectPersonSection.setVisible(false);
		}
	}
	/**
	  * Save value of InsuranceCustomerCreateView.
	  */
	public void save() {
		InsuranceCustomer value = (InsuranceCustomer)viewer.getValue();
		if(value != null) {
			dao.persist(value);
			back();
		}
	}
	
	public void back() {
		postViewer("InsCustomerSearch");
	}

}

