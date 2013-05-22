/**
 * 
 */
package com.mocha.cooperate.page.event;

import com.coral.foundation.security.model.BasicUser;

/**
 * @author Coral
 *
 */
public interface UserPermissionListener {

	public void saveUser(BasicUser user);
	public void searchUser(String text);
	public void showActiveUsers();
	public void showInActiveUsers();
}
