/**
 * 
 */
package com.coral.vaadin.widget.view;

import com.coral.foundation.core.impl.MochaEventBus;
import com.coral.foundation.security.model.BasicUser;
import com.coral.vaadin.controller.Presenter;
import com.coral.vaadin.widget.Viewer;
import com.coral.vaadin.widget.view.builder.ViewContext;

/**
 * @author Administrator
 *
 */
public abstract class CommonPresenter implements Presenter {

	protected MochaEventBus eventBus;
	protected Viewer viewer;
	protected BasicUser currentUser;
	
	@Override
	public Viewer go() {
//		bind();
		return viewer;
	}
	
	public Viewer getViewer() {
		return viewer;
	}
	
	public ViewContext getViewContext() {
		return null;
	}
	
	@Override
	public boolean isFullSize() {
		return true;
	}

	public void setEditValue(Long id) {
	}

	/**
	 * @return the eventBus
	 */
	public MochaEventBus getEventBus() {
		return eventBus;
	}

	/**
	 * @param eventBus the eventBus to set
	 */
	public void setEventBus(MochaEventBus eventBus) {
		this.eventBus = eventBus;
	}
	
	
}
