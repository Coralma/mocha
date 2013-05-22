/**
 * 
 */
package com.mocha.cooperate.page.admin;

import com.coral.foundation.core.impl.MochaEventBus;
import com.coral.vaadin.controller.Presenter;
import com.coral.vaadin.widget.view.CommonPresenter;
import com.mocha.cooperate.PresenterProperty;

/**
 * @author Coral
 *
 */
public class CompanyFilePresenter extends CommonPresenter implements Presenter {

//	private CompanyFileService fileService;
	
	public CompanyFilePresenter(MochaEventBus eventBus) {
		this.eventBus = eventBus;
//		fileService = new CompanyFileService(eventBus.getUser().getAccount().getName());
		this.viewer = new CompanyFileViewer();
	}
	
	@Override
	public void bind() {
	}

	@Override
	public String getPresenterName() {
		return PresenterProperty.COMPANY_FILE;
	}

}
