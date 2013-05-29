/**
 * 
 */
package com.coral.vaadin.widget.view;

import com.coral.vaadin.view.template.sat.AppContentEvent;

/**
 * @author Coral
 *
 */
public abstract class AppCommonPresenter extends CommonPresenter {
	
	public void postViewer(String viewerName) {
		AppContentEvent appContentEvent = new AppContentEvent();
		appContentEvent.setViewName(viewerName);
		eventBus.post(appContentEvent);
	}
	
	public void postViewer(String viewer, Object entity) {
		AppContentEvent appContentEvent = new AppContentEvent();
		appContentEvent.setViewName(viewer);
		eventBus.resetContext();
		eventBus.put("Entity", entity);
		eventBus.post(appContentEvent);
	}
	
	public void postCustomizeClass(String customizeClass) {
		AppContentEvent appContentEvent = new AppContentEvent();
		appContentEvent.setCustomizeClass(customizeClass);
		eventBus.post(appContentEvent);
	}

}
