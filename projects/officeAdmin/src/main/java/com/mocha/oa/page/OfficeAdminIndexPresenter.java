/**
 * 
 */
package com.mocha.oa.page;

import com.coral.foundation.core.impl.MochaEventBus;
import com.coral.vaadin.controller.Presenter;
import com.coral.vaadin.widget.view.CommonPresenter;
import com.mocha.vaadin.entity.view.LeaveEntityView;

/**
 * @author Coral.Ma
 *
 */
public class OfficeAdminIndexPresenter extends CommonPresenter implements Presenter {
	
	public OfficeAdminIndexPresenter(MochaEventBus eventBus) {
		this.eventBus = eventBus;
		this.viewer = new LeaveEntityView();
	}

	@Override
	public String getPresenterName() {
		return "OfficeAdminIndex";
	}

	@Override
	public void bind() {
		//TODO
	}

}
