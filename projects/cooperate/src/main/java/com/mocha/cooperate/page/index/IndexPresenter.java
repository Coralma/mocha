/**
 * 
 */
package com.mocha.cooperate.page.index;

import com.coral.foundation.core.impl.MochaEventBus;
import com.coral.vaadin.controller.Presenter;
import com.coral.vaadin.widget.view.CommonPresenter;
import com.mocha.cooperate.PresenterProperty;

/**
 * @author Administrator
 *
 */
public class IndexPresenter extends CommonPresenter implements Presenter {
	
	public IndexPresenter(MochaEventBus eventBus) {
		this.eventBus = eventBus;
		this.viewer = new IndexViewer(eventBus);
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
