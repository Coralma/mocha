/**
 * 
 */
package com.mocha.cooperate.page;

import com.coral.foundation.core.impl.MochaEventBus;
import com.coral.foundation.security.model.BasicUser;
import com.coral.foundation.security.service.BasicUserService;
import com.coral.foundation.utils.Message;
import com.coral.vaadin.widget.Viewer;
import com.coral.vaadin.widget.view.CommonViewer;
import com.mocha.cooperate.SystemProperty;
import com.mocha.cooperate.help.GettingStartedWindow;
import com.mocha.cooperate.page.event.HomePageListener;
import com.mocha.cooperate.widget.PublisherWidget;
import com.mocha.cooperate.widget.SimplePublisherWidget;
import com.mocha.cooperate.widget.wrap.HomeLineSheetWrap;
import com.mocha.cooperate.widget.wrap.TimeLineSheetWrap;

/**
 * @author Administrator
 *
 */
public class HomeViewer extends CommonViewer implements Viewer {

	private String pageWidth = SystemProperty.content_page_width;
//	private PublisherWidget publisherWidget;
	private SimplePublisherWidget publisherWidget;
	private HomeLineSheetWrap homeLineSheetWrap;
	private HomePageListener listener;
	private BasicUser currentUser;
	private MochaEventBus eventBus;
	private BasicUserService userService = new BasicUserService();
	
	public HomeViewer(BasicUser currentUser, MochaEventBus eventBus) {
		this.currentUser = currentUser;
		this.eventBus = eventBus;
		this.setWidth(pageWidth);
		this.setSpacing(true);
	}
	
	public void attach() {
		super.attach();
//		publisherWidget = new PublisherWidget(currentUser);
		publisherWidget = new SimplePublisherWidget(currentUser);
		
//		if(listener != null) {
//			publisherWidget.setListener(listener);
//		}
		this.addComponent(publisherWidget);
		
		homeLineSheetWrap = new HomeLineSheetWrap((BasicUser)getApplication().getUser(), eventBus);
		this.addComponent(homeLineSheetWrap.getTimeLineSheet());
		
		if(currentUser.getInit() == null || currentUser.getInit() == 0) {
			getWindow().addWindow(new GettingStartedWindow());
			currentUser.setInit(new Long(1));
//			userService.merge(currentUser);
		}
	}
	
	@Override
	public String getViewerTitle() {
		return null;
	}

	/**
	 * @return the timeLineSheetWrap
	 */
	public HomeLineSheetWrap getHomeLineSheetWrap() {
		return homeLineSheetWrap;
	}
//
//	/**
//	 * @return the publisherWidget
//	 */
//	public PublisherWidget getPublisherWidget() {
//		return publisherWidget;
//	}

	/**
	 * @return the listener
	 */
	public HomePageListener getListener() {
		return listener;
	}

	/**
	 * @param listener the listener to set
	 */
	public void setListener(HomePageListener listener) {
		this.listener = listener;
		publisherWidget.setListener(listener);
	}

}
