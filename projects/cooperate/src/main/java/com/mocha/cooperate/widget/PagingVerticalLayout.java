/**
 * 
 */
package com.mocha.cooperate.widget;

import com.coral.foundation.constant.RuntimeConstant;
import com.mocha.cooperate.SystemProperty;
import com.mocha.cooperate.widget.listener.PagingListener;
import com.vaadin.event.LayoutEvents.LayoutClickEvent;
import com.vaadin.event.LayoutEvents.LayoutClickListener;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.BaseTheme;

public class PagingVerticalLayout extends VerticalLayout implements ClickListener,LayoutClickListener {

	private static final long serialVersionUID = 7394921109034793326L;
	private PagingListener listener;
	private int currentPage = 0;
	private int paging_size = RuntimeConstant.PAGING_SIZE;
	private String type;
	private VerticalLayout contentLayout = new VerticalLayout();
	private Button nextButton = new Button("Loading next " + paging_size + " messages");
	private VerticalLayout nextPageLayout = new VerticalLayout();
	
	public PagingVerticalLayout() {
		contentLayout.setWidth("100%");
		contentLayout.setSpacing(true);
		super.addComponent(contentLayout);
		
		nextButton.addStyleName(BaseTheme.BUTTON_LINK);
		nextButton.addListener(this);
		nextPageLayout.addComponent(nextButton);
		nextPageLayout.addStyleName("next-paging-layout");
		nextPageLayout.addListener(this);
		nextPageLayout.setComponentAlignment(nextButton, Alignment.BOTTOM_CENTER);
		nextPageLayout.setWidth(SystemProperty.content_widget_width);
		super.addComponent(nextPageLayout);
	}
	
	public void addComponent(Component c) {
		contentLayout.addComponent(c);
	}
	
	public void removeAllComponents() {
		contentLayout.removeAllComponents();
		nextPageLayout.setVisible(true);
	}

	@Override
	public void buttonClick(ClickEvent event) {
		showMore();
	}
	
	@Override
	public void layoutClick(LayoutClickEvent event) {
		showMore();		
	}
	
	private void showMore() {
		if(listener != null) {
			listener.showMore(type, ++currentPage);
		}
	}
	
	public void setMoreSize(int size) {
		if(size < RuntimeConstant.PAGING_SIZE) {
			setFullSize(false);	
		} else {
			setFullSize(true);
		}
	}
	
	public void setFullSize(boolean fullSize) {
		nextPageLayout.setVisible(fullSize);
	}

	/**
	 * @return the currentPage
	 */
	public int getCurrentPage() {
		return currentPage;
	}

	/**
	 * @param currentPage the currentPage to set
	 */
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	/**
	 * @return the listener
	 */
	public PagingListener getListener() {
		return listener;
	}

	/**
	 * @param listener the listener to set
	 */
	public void setListener(PagingListener listener) {
		this.listener = listener;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

}
