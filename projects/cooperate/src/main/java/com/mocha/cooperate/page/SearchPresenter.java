/**
 * 
 */
package com.mocha.cooperate.page;

import com.coral.foundation.core.impl.MochaEventBus;
import com.coral.vaadin.controller.Presenter;
import com.coral.vaadin.widget.view.CommonPresenter;

public class SearchPresenter extends CommonPresenter implements Presenter {

	public SearchPresenter(MochaEventBus eventBus) {
		this.eventBus = eventBus;
		this.viewer = new SearchViewer((String)eventBus.getContext().get("searchText"));
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
