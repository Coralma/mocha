/**
 * 
 */
package com.mocha.co.presenter;

import com.coral.foundation.core.impl.MochaEventBus;
import com.coral.foundation.spring.bean.SpringContextUtils;
import com.coral.vaadin.controller.Presenter;
import com.coral.vaadin.widget.view.CommonPresenter;
import com.mocha.co.dao.CommerceCustomerDao;
import com.mocha.co.model.CommerceCustomer;

/**
 * @author Coral
 *
 */
public class CoDashboardPresenter extends CommonPresenter implements Presenter {
	
	CommerceCustomerDao ccDao=SpringContextUtils.getBean(CommerceCustomerDao.class);
	boolean needAuth=false;
	
	public CoDashboardPresenter(MochaEventBus eventBus) {
		this.eventBus = eventBus;
		CommerceCustomer cc=ccDao.findCCByUser(eventBus.getUser());
		if(cc==null || cc.getSourceApplications()==null || cc.getSourceApplications().get(0).getAuthToken()==null){
			needAuth=true;
		}
		this.viewer = new CoDashboardView(eventBus.getUser(),needAuth);
	}

	@Override
	public void bind() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getPresenterName() {
		// TODO Auto-generated method stub
		return "Home";
	}

	@Override
	public boolean isFullSize() {
		return false;
	}

}
