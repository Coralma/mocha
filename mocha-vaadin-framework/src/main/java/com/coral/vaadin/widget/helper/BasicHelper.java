/**
 * 
 */
package com.coral.vaadin.widget.helper;

import com.coral.foundation.security.model.BasicUser;

/**
 * @author Coral.Ma
 *
 */
public class BasicHelper {

	public static boolean isCurrentUser(BasicUser creatorUser, BasicUser systemUser) {
		if(creatorUser.getBasicUserId().equals(systemUser.getBasicUserId())) {
			return true;
		} else {
			return false;
		}
	}
	
	public static String styleUsername(String userName) {
		return "<span style=\"color: #1650ad; font-weight: bold;font-size: 13px;\">"+userName+" : </span>";
	}
}
