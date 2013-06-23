/**
 * 
 */
package com.mocha.cooperate.page.cust;

import com.coral.foundation.core.impl.MochaEventBus;
import com.coral.foundation.spring.bean.SpringContextUtils;
import com.coral.vaadin.controller.Presenter;
import com.coral.vaadin.view.template.sat.panel.ISectionPanel;
import com.coral.vaadin.widget.view.CommonPresenter;
import com.mocha.ib.dao.InsuranceCustomerDao;
import com.mocha.ib.model.InsuranceCustomer;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

/**
 * @author Coral
 *
 */
public class CustomerHomePresenter extends CommonPresenter implements Presenter {

	private InsuranceCustomerDao customerDao = SpringContextUtils.getBean(InsuranceCustomerDao.class);
	
	public CustomerHomePresenter(MochaEventBus eventBus) {
		this.eventBus = eventBus;
		InsuranceCustomer customer = customerDao.findCustomerByUser(eventBus.getUser());
		this.viewer = new CustomerHomeViewer(customer);
	}
	
	@Override
	public void bind() {
		final CustomerHomeViewer homeViewer = (CustomerHomeViewer)getViewer();
		homeViewer.getEditBtn().addListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				ISectionPanel sectionPanel = homeViewer.getSectionPanel();
				if(sectionPanel.isReadOnly()) {
					homeViewer.getSectionPanel().setReadOnly(false);
					homeViewer.getEditBtn().setCaption("Save");
				} else {
					InsuranceCustomer customer = homeViewer.getCustomer();
					customerDao.merge(customer);
					homeViewer.getSectionPanel().setReadOnly(true);
					homeViewer.getEditBtn().setCaption("Edit");
				}
				
			}
		});
	}

	@Override
	public String getPresenterName() {
		// TODO Auto-generated method stub
		return null;
	}

}
