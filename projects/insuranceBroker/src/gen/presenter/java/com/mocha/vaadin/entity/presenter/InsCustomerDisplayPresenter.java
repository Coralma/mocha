package com.mocha.vaadin.entity.presenter;

import com.coral.foundation.core.impl.MochaEventBus;
import com.coral.foundation.model.BaseEntity;
import com.coral.foundation.spring.bean.SpringContextUtils;
import com.coral.vaadin.controller.Presenter;
import com.coral.vaadin.view.template.sat.panel.ISectionPanel;
import com.coral.vaadin.view.template.sat.panel.impl.EntityDisplayPanel.EntityDisplayListener;
import com.coral.vaadin.view.template.sat.panel.impl.ListSectionPanel;
import com.coral.vaadin.widget.fields.CodeTableWidget;
import com.coral.vaadin.widget.view.AppCommonPresenter;
import com.mocha.ib.dao.InsuranceCustomerDao;
import com.mocha.ib.model.InsuranceCustomer;
import com.mocha.ib.model.InsuranceCustomerServe;
import com.mocha.ib.model.Policy;
import com.mocha.vaadin.entity.view.InsCustomerDisplay;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

public class InsCustomerDisplayPresenter extends AppCommonPresenter implements Presenter {

	private InsuranceCustomerDao dao = SpringContextUtils.getBean(InsuranceCustomerDao.class);
	
	public InsCustomerDisplayPresenter(MochaEventBus eventBus) {
		this.eventBus = eventBus;
		InsCustomerDisplay newView = new InsCustomerDisplay();
		Object entity = this.eventBus.getContext().get("Entity");
		if(entity != null) {
			newView.setEntity(entity);
			eventBus.resetContext();
		}
		this.viewer = newView;
	}

	@Override
	public String getPresenterName() {
		return "InsCustomerDisplay";
	}
	
	@Override
	public void bind() {
		final InsCustomerDisplay customerView = (InsCustomerDisplay) viewer;
		initViewer(customerView);
		
		customerView.setListener(new EntityDisplayListener() {
			
			@Override
			public void previous(Object value) {
				
			}
			
			@Override
			public void next(Object value) {
				
			}
			
			@Override
			public void edit(Object value) {
				postViewer("InsCustomerView",value);				
			}
			
			@Override
			public void delete(Object value) {
				InsCustomerDisplayPresenter.this.remove(value);
				InsCustomerDisplayPresenter.this.back();
			}
			
			@Override
			public void back() {
				InsCustomerDisplayPresenter.this.back();
			}
		});
		ListSectionPanel policyPanel = (ListSectionPanel)customerView.getSection("Policy");
		policyPanel.getNewButton().addListener(new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				Policy policy = new Policy();
				policy.setCustomer((InsuranceCustomer) customerView.getEntity());
				postViewer("PolicyView",policy);
			}
		});
		
		ListSectionPanel servePanel = (ListSectionPanel)customerView.getSection("Serve");
		servePanel.getNewButton().addListener(new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				InsuranceCustomerServe serve = new InsuranceCustomerServe();
				serve.setCustomer((InsuranceCustomer) customerView.getEntity());
				postViewer("InsCustomerServeView", serve);
			}
		});
	}
	
	public void initViewer(InsCustomerDisplay viewer) {
		InsuranceCustomer customerEntity = (InsuranceCustomer) viewer.getEntity();
		String type = customerEntity.getCustomerType();
		if(type == null) {
			type = "1";
		}
//		CodeTableWidget ctWidget = (CodeTableWidget) viewer.getField("customerType");
//		ctWidget.setNullSelectionAllowed(false);
//		ctWidget.setValue(type);
		customerTypeSelect(type, viewer);
	}
	
	public void customerTypeSelect(String type, InsCustomerDisplay viewer) {
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
	
	public void back() {
		postViewer("InsCustomerSearch");
	}
	
	public void remove(Object entity) {
		if(entity != null) {
			dao.remove(((BaseEntity)entity).getID());
		}
	}

//	@Override
//	public boolean isFullSize() {
//		return false;
//	}
}

