/**
 * 
 */
package com.mocha.hr.presenter;

import com.coral.foundation.core.impl.MochaEventBus;
import com.coral.vaadin.controller.Presenter;
import com.coral.vaadin.widget.view.CommonPresenter;
import com.mocha.hr.view.MyHrView;

/**
 * @author Coral
 *
 */
public class MyHrPresenter extends CommonPresenter implements Presenter {

	public MyHrPresenter(MochaEventBus eventBus) {
		this.eventBus = eventBus;
		this.viewer = new MyHrView(eventBus.getUser());
	}

	@Override
	public void bind() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getPresenterName() {
		// TODO Auto-generated method stub
		return null;
	}

}