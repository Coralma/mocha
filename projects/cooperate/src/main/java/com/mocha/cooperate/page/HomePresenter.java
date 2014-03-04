/**
 * 
 */
package com.mocha.cooperate.page;

import com.coral.foundation.core.impl.MochaEventBus;
import com.coral.vaadin.controller.Presenter;
import com.coral.vaadin.widget.view.CommonPresenter;
import com.mocha.cooperate.PresenterProperty;
import com.mocha.cooperate.model.TimeLine;
import com.mocha.cooperate.page.event.HomePageListener;

/**
 * @author Administrator
 *
 */
public class HomePresenter extends CommonPresenter implements Presenter {

	public HomePresenter(MochaEventBus eventBus) {
		this.eventBus = eventBus;
		this.viewer = new HomeViewer(eventBus.getUser(), eventBus);
	}
	
	@Override
	public String getPresenterName() {
		return PresenterProperty.HOME;
	}

	@Override
	public void bind() {
		final HomeViewer homeViewer = (HomeViewer) viewer;
		homeViewer.setListener(new HomePageListener() {
			@Override
			public void newPost(TimeLine newTimeLine) {
				homeViewer.getHomeLineSheetWrap().newPostTimeline(newTimeLine);
			}
		});
	}
	
	

}
