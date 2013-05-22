/**
 * 
 */
package com.coral.vaadin.widget.validate;

import com.coral.vaadin.widget.Field;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.Window.Notification;

/**
 * @author Coral
 *
 */
public class AbstractValidator {

	public Field field;
	
	public AbstractValidator(Field field) {
		this.field = field;
	}
	
	public void notification(String errorMsg) {
		Notification notification = new Notification("Error", errorMsg, Notification.TYPE_ERROR_MESSAGE);
		notification.setDelayMsec(-1);
		notification.setHtmlContentAllowed(true);
//		notification.setStyleName("c-Notification");
		notification.setIcon( new ThemeResource("icons/error.png"));
		notification.setPosition(Notification.POSITION_CENTERED_TOP);
//		((HorizontalLayout)field).getParent().getParent()
		Window window = ((HorizontalLayout)field).getWindow();
		if(window != null)
			window.showNotification(notification);
	}
}
