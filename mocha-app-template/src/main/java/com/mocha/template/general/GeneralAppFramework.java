package com.mocha.template.general;

import com.coral.foundation.core.impl.MochaEventBus;
import com.coral.foundation.security.model.BasicUser;
import com.coral.vaadin.controller.Presenter;
import com.coral.vaadin.widget.Viewer;
import com.coral.vaadin.widget.view.CommonViewer;
import com.google.common.eventbus.Subscribe;
import com.mocha.template.general.utils.GeneralTemplateWidgetFactory;
import com.mocha.template.general.widget.GeneralNavigationWidget;
import com.mocha.template.general.widget.event.GeneralAppContentChangeEvent;
import com.mocha.template.general.widget.vo.GeneralHeadVO;
import com.vaadin.ui.Layout;
import com.vaadin.ui.VerticalLayout;

public abstract class GeneralAppFramework extends CommonViewer {

	public VerticalLayout layout = new VerticalLayout();
	public Layout contentLayout = new VerticalLayout();
	public GeneralNavigationWidget navigationWidget;
	
	public String total_width = "1000px";
	public String app_name_width = "180px";
	public String menu_width = "820px";
	
	public String left_layout_width = "680px";
	public String left_content_width = "658px";
	public String right_layout_width = "320px";

	public MochaEventBus eventBus;
	public BasicUser currentUser;
	
	public GeneralAppFramework() {
		super();
		layout.addStyleName("general-app");
	}

	public void setEventBus(MochaEventBus eventBus) {
		this.eventBus = eventBus;
		this.currentUser = eventBus.getUser();
		this.eventBus.register(this);
	}
	
	@Subscribe
	public void appPageChange(GeneralAppContentChangeEvent event) {
		//load the refer presenter from subclass.
		Presenter presenter = loadPresenter(eventBus, event);
		if(presenter != null) {
			Viewer view = presenter.go();
			contentLayout.removeAllComponents();
			contentLayout.addComponent(view);
			presenter.bind();
			layout.requestRepaintAll();
		} else {
			throw new RuntimeException("Can not find the Presenter for the " + event.getViewName());
		}
	}
	
	public abstract Presenter loadPresenter(MochaEventBus eventBus, GeneralAppContentChangeEvent event);
	public abstract void defaultPresenter();
	public abstract GeneralHeadVO loadGeneralHead(); 
	
	public void attach() {
		GeneralHeadVO generalHead = loadGeneralHead();

		navigationWidget = GeneralTemplateWidgetFactory.buildGeneralNavigationWidget(generalHead);
		// add navigation menu widget.
		layout.addComponent(navigationWidget);
		
		// add main page content.
		layout.addComponent(contentLayout);
		this.addComponent(layout);
		
		// init main
		defaultPresenter();
	}
	
	/**
	 * @return the navigationWidget
	 */
	public GeneralNavigationWidget getNavigationWidget() {
		return navigationWidget;
	}
}
