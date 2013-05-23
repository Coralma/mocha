/**
 * 
 */
package com.mocha.oa.page;

import java.util.List;

import com.coral.foundation.core.impl.MochaEventBus;
import com.coral.foundation.spring.bean.SpringContextUtils;
import com.coral.vaadin.controller.Presenter;
import com.coral.vaadin.widget.view.CommonPresenter;
import com.mocha.oa.dao.LeaveDao;
import com.mocha.oa.model.Leave;

/**
 * @author Coral.Ma
 *
 */
public class OfficeAdminSearchPresenter extends CommonPresenter implements Presenter {

	private LeaveDao dao = SpringContextUtils.getBean(LeaveDao.class);
	
	public OfficeAdminSearchPresenter(MochaEventBus eventBus) {
		this.eventBus = eventBus;
		this.viewer = new OfficeAdminSearchViewer();
		// load all data.
		List<Leave> leaves = dao.findAll();
		viewer.setValue(leaves);
	}
	
	@Override
	public void bind() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getPresenterName() {
		// TODO Auto-generated method stub
		return "Search";
	}

}
