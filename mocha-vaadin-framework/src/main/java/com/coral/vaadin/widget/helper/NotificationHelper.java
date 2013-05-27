/**
 * 
 */
package com.coral.vaadin.widget.helper;

import java.util.List;

import com.coral.vaadin.widget.Result;
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
	
	public static Notification getErrorNotification(List<Result> results) {
		String errorMessage = "";
		int erroIndex = 1;
		int errorSize = results.size();
		for(Result result : results) {
			if(!result.isPass()) {
				if(errorSize > 1) {
					errorMessage += erroIndex++ + ". "+ result.getErrorMessage() + "<br/>";
				} else {
					errorMessage += result.getErrorMessage();
				}
			}
		}
		if(errorSize > 1) {
			errorMessage = errorMessage.substring(0, errorMessage.lastIndexOf("<br/>"));
		}
		Notification notification = new Notification(null,errorMessage ,Notification.TYPE_ERROR_MESSAGE);
        notification.setDelayMsec(delayMsec);
        notification.setPosition(Notification.POSITION_BOTTOM_RIGHT);
        return notification;
	}
}
