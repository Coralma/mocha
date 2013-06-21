package com.mocha.cooperate.page.index;

import org.vaadin.jouni.animator.AnimatorProxy;
import org.vaadin.jouni.animator.client.ui.VAnimatorProxy.AnimType;

import com.coral.foundation.core.impl.MochaEventBus;
import com.coral.foundation.security.model.BasicUser;
import com.coral.foundation.spring.bean.SpringContextUtils;
import com.coral.vaadin.controller.ContentChangeEvent;
import com.coral.vaadin.controller.PageFactory;
import com.coral.vaadin.controller.Presenter;
import com.coral.vaadin.widget.Viewer;
import com.coral.vaadin.widget.view.CommonViewer;
import com.google.common.eventbus.Subscribe;
import com.mocha.cooperate.page.data.ExampleData;
import com.mocha.cooperate.widget.ShotcutPanel;
import com.mocha.cooperate.widget.UserPhotoWidget;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;

public class CustomerIndexViewer  extends CommonViewer implements Viewer {

	private String infoWidth = "180px";
	private String contentWidth = "770px";
	private HorizontalLayout layout = new HorizontalLayout();
	private VerticalLayout infoColumn = new VerticalLayout();
	private VerticalLayout contentColumn = new VerticalLayout();
	private AnimatorProxy proxy = new AnimatorProxy();
	private PageFactory pageFactory = SpringContextUtils.getBean("pageFactory", PageFactory.class);
	private Presenter currentPresenter;
	private BasicUser user;
	private MochaEventBus eventBus;
	
	public CustomerIndexViewer(MochaEventBus eventBus) {
		super();
		this.eventBus = eventBus; 
		this.user = eventBus.getUser();
		eventBus.register(this);
		build();
	}
	
	public void build() {
//		super.attach();
		layout.setWidth("980px");
//		layout.setSpacing(true);
		layout.addStyleName("mocha-index");
		layout.addComponent(buildInformationColumn());
		layout.addComponent(buildContentColumn());
		layout.addComponent(proxy);
		addComponent(layout);
		setComponentAlignment(layout, Alignment.TOP_CENTER);
	}
	
	/**
	 * Left area of index page for user photo and menu.
	 */
	public VerticalLayout buildInformationColumn() {
		infoColumn.addStyleName("index-info-panel");
		infoColumn.setWidth(infoWidth);
		UserPhotoWidget userPhoto =new UserPhotoWidget(user);
		infoColumn.addComponent(userPhoto);

		ShotcutPanel menu = new ShotcutPanel(eventBus, ExampleData.getCustomerShotcut());
		menu.setWidth(infoWidth);
		
		infoColumn.addComponent(menu);
		return infoColumn; 
	}

	public VerticalLayout buildContentColumn() {
		contentColumn.addStyleName("index-content-panel");
		contentColumn.setWidth(contentWidth);
		return contentColumn;
	}
	
	@Subscribe
	public void changeContent(ContentChangeEvent event) {
		String presenterName = event.getPresenterName();
		// return if it a same presenter.
		if(currentPresenter != null && presenterName.equals(currentPresenter.getPresenterName())) {
			return;
		}
		Presenter presenter = pageFactory.getPresenter(presenterName, eventBus);
		Viewer viewer = presenter.go();
		if(currentPresenter != null) {
			proxy.animate(currentPresenter.getViewer(), AnimType.ROLL_LEFT_CLOSE_REMOVE).setDuration(200).setDelay(0);
			proxy.animate(viewer, AnimType.ROLL_RIGHT_OPEN).setDuration(500).setDelay(200);
		}
		contentColumn.removeAllComponents();
		contentColumn.addComponent(viewer);
		presenter.bind();
		currentPresenter = presenter; 
	}
	
	@Override
	public void detach() {
		eventBus.unregister(this);
	}
	
	@Override
	public String getViewerTitle() {
		return null;
	}

}
