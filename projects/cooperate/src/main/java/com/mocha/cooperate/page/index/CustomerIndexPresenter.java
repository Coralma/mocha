/**
 * 
 */
package com.mocha.cooperate.page.index;

import com.coral.foundation.core.impl.MochaEventBus;
import com.coral.vaadin.controller.Presenter;
import com.coral.vaadin.widget.view.CommonPresenter;
import com.mocha.cooperate.PresenterProperty;

/**
 * @author Coral
 *
 */
public class CustomerIndexPresenter extends CommonPresenter implements Presenter {
	
	public CustomerIndexPresenter(MochaEventBus eventBus) {
		this.eventBus = eventBus;
		this.viewer = new CustomerIndexViewer(eventBus);
	}

	@Override
	public String getPresenterName() {
		return PresenterProperty.INDEX;
	}

	@Override
	public void bind() {
		//TODO
	}
}
