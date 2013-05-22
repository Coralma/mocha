/**
 * 
 */
package com.coral.vaadin.controller;

import org.springframework.transaction.annotation.Transactional;
import org.vaadin.jouni.animator.AnimatorProxy;
import org.vaadin.jouni.animator.client.ui.VAnimatorProxy.AnimType;

import com.coral.foundation.core.impl.MochaEventBus;
import com.coral.foundation.spring.bean.SpringContextUtils;
import com.coral.foundation.utils.Message;
import com.coral.vaadin.widget.Viewer;
import com.google.common.eventbus.Subscribe;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.VerticalLayout;

/**
 * @author Administrator
 *
 */
public class PageController {

	private VerticalLayout parentPanel;
	private MochaEventBus eventBus;
	private PageFactory pageFactory;
	private Message msg;
	private Viewer viewer;
	private AnimatorProxy proxy = new AnimatorProxy();
	
	public PageController(VerticalLayout parentPanel, MochaEventBus eventBus) {
		this.parentPanel = parentPanel;
		this.eventBus = eventBus;
		this.parentPanel.addComponent(proxy);
		//i18n
//		this.msg = new Message();
		pageFactory = SpringContextUtils.getBean("pageFactory", PageFactory.class);
		eventBus.register(this);
	}
	
	@Subscribe
	@Transactional
	public void pageChange(PageChangeEvent event) {
		Presenter presenter = pageFactory.getPresenter(event.getPresenterName(), eventBus);
		// find from application component.
		if(presenter == null) {
			presenter = SpringContextUtils.getBean(event.getPresenterName(), Presenter.class);
			presenter.setEventBus(eventBus);
		}
		presenter.setEditValue(event.getId());
		Viewer newViewer = presenter.go();
		
		if(viewer != null) {
			parentPanel.replaceComponent(viewer, newViewer);
		} else {
			parentPanel.addComponent(newViewer);
		}
		proxy.animate(newViewer, AnimType.ROLL_DOWN_OPEN).setDuration(500).setDelay(0);
		parentPanel.setComponentAlignment(newViewer, Alignment.TOP_CENTER);
		presenter.bind();
		viewer = newViewer;
		// if the page has subContentPresenter load it.
		String contentPresenterName = event.getContentPresenterName();
		if(contentPresenterName != null) {
			ContentChangeEvent contentChangeEvent = new ContentChangeEvent();
			contentChangeEvent.setPresenterName(contentPresenterName);
			eventBus.post(contentChangeEvent);
		}
	}
}
