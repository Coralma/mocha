/**
 * 
 */
package com.coral.vaadin.widget.helper;

import com.vaadin.ui.Window.Notification;

/**
 * @author Coral.Ma
 *
 */
public class NotificationHelper {

	public static int delayMsec = 3000;
	
	public static Notification getErrorNotification(String errorMessage) {
		Notification notification = new Notification(null,errorMessage ,Notification.TYPE_ERROR_MESSAGE);
        notification.setDelayMsec(delayMsec);
        notification.setPosition(Notification.POSITION_BOTTOM_RIGHT);
        return notification;
	}
	
	public static Notification getNotification(String errorMessage) {
		Notification notification = new Notification(null,errorMessage ,Notification.TYPE_TRAY_NOTIFICATION);
        notification.setDelayMsec(delayMsec);
        notification.setPosition(Notification.POSITION_BOTTOM_RIGHT);
        return notification;
	}
}
