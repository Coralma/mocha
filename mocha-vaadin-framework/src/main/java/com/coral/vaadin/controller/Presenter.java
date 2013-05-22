/**
 * 
 */
package com.coral.vaadin.controller;

import com.coral.foundation.core.impl.MochaEventBus;
import com.coral.vaadin.widget.Viewer;
import com.coral.vaadin.widget.view.builder.ViewContext;

/**
 * @author Administrator
 *
 */
public interface Presenter {

	public Viewer go();
	public void bind();
	public Viewer getViewer();
	public String getPresenterName();
	public ViewContext getViewContext();
	public void setEditValue(Long id);
	public void setEventBus(MochaEventBus eventBus);
	public boolean isFullSize();
}
