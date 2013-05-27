/**
 * 
 */
package com.coral.vaadin.view.template.sat;

import java.util.Map;

import com.coral.foundation.core.impl.MochaEventBus;
import com.coral.vaadin.widget.Result;
import com.coral.vaadin.widget.Viewer;
import com.coral.vaadin.widget.Widget;
import com.coral.vaadin.widget.field.ActionButton;
import com.google.common.eventbus.Subscribe;
import com.vaadin.ui.HorizontalLayout;

/**
 * @author Coral.Ma
 *
 */
public abstract class AppMainPage extends HorizontalLayout implements Viewer {

	protected MochaEventBus eventBus;
	
	public AppMainPage() {
		this.setWidth("975px");
		this.addStyleName("app-page");
	}
	
	/**
	 * @param eventBus the eventBus to set
	 */
	public void setEventBus(MochaEventBus eventBus) {
		this.eventBus = eventBus;
		eventBus.register(this);
	}
	
	@Override
    public void detach() {
		eventBus.unregister(this);
	}
	
	@Subscribe
	public void appContentChange(AppContentEvent event) {
		if(event.getViewName() != null) {
			showView(event.getViewName());
		} else if(event.getCustomizeClass() != null) {
			try {
				showPanel(Class.forName(event.getCustomizeClass()));
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}
	
	public abstract void showView(String viewName);
	
	public abstract void showPanel(Class customizedPresenter);
	
	@Override
	public String getViewerTitle() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ActionButton getButton(String action) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Widget getWidget(String widgetName) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Map<String, Widget> getWidgets() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Object getValue() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void setValue(Object value) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public Result validate(String type) {
		// TODO Auto-generated method stub
		return new Result();
	}

	/**
	 * @return the eventBus
	 */
	public MochaEventBus getEventBus() {
		return eventBus;
	}
}
