package com.coral.vaadin.widget.component;

import java.util.List;

import com.coral.foundation.security.model.BasicUser;
import com.coral.foundation.security.service.BasicUserService;
import com.vaadin.ui.ComboBox;

public class UserComboBox extends ComboBox {
	
	protected List<BasicUser> allUsers;
	protected BasicUserService userService = new BasicUserService();
	
	public UserComboBox(BasicUser defaultUser) {
		this.allUsers = userService.findAll();
		for (BasicUser basicUser : this.allUsers) {
			this.addItem(basicUser);
			this.setItemCaption(basicUser, basicUser.getRealName());
			if(defaultUser.getID().equals(basicUser.getID())) {
				this.setValue(basicUser);
			}
		}
	}
	
	public void resetDefaultUser(BasicUser defaultUser) {
		for (BasicUser basicUser : this.allUsers) {
			if(defaultUser.getID().equals(basicUser.getID())) {
				this.setValue(basicUser);
			}
		}
	}
}
