package com.coral.vaadin.widget.layout;

import java.io.File;

import com.coral.foundation.security.basic.dao.BasicUserDao;
import com.coral.foundation.security.model.BasicUser;
import com.coral.foundation.spring.bean.SpringContextUtils;
import com.coral.vaadin.image.AbstractUserIconHelper;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.vaadin.Application;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.terminal.FileResource;
import com.vaadin.terminal.Resource;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.AbstractSelect;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.VerticalLayout;

public class AssginedUserSelect extends CustomComponent {

	private ComboBox assginedUserSelect;

	public BasicUserDao basicUserDao = SpringContextUtils
			.getBean(BasicUserDao.class);

	private static BasicUser selectedUser = null;

	private AbstractUserIconHelper userIconHelper=null;
	
	public AssginedUserSelect(AbstractUserIconHelper userIconHelper) {
		this.userIconHelper=userIconHelper;
		setCompositionRoot(buildAssginedUser());
	}

	public ComboBox buildAssginedUser() {
		VerticalLayout assginedUserPanel = new VerticalLayout();
		ComboBox combox=new ComboBox(null,
				getUserContainer(userIconHelper));
		combox.setImmediate(true);
		setAssginedUserSelect(combox);
		getAssginedUserSelect().setStyleName("subToDoAssginedSelect");
		getAssginedUserSelect().setInputPrompt("Specify one User");
		getAssginedUserSelect().setNullSelectionAllowed(false);
		getAssginedUserSelect().setItemCaptionMode(
				AbstractSelect.ITEM_CAPTION_MODE_PROPERTY);
		// getAssginedUserSelect().setFilteringMode(Filtering.FILTERINGMODE_CONTAINS);
		getAssginedUserSelect().setItemCaptionPropertyId("userName");
//		getAssginedUserSelect().setItemIconPropertyId("userIcon");
		getAssginedUserSelect().setImmediate(true);
		

		return getAssginedUserSelect();
	}

	private IndexedContainer getUserContainer(
			AbstractUserIconHelper userIconHelper) {
		IndexedContainer userContainer = new IndexedContainer();
		userContainer.addContainerProperty("userName", String.class, "");
//		userContainer.addContainerProperty("userIcon", File.class, "");
		userContainer.addContainerProperty("userId", Long.class, null);
		for (BasicUser basicUser : basicUserDao.findAll()) {
			Item item = userContainer.addItem(basicUser);
			item.getItemProperty("userId").setValue(basicUser.getBasicUserId());
			item.getItemProperty("userName").setValue(basicUser.getUserName());
			if (basicUser.getUserIcon() == null
					|| !new File(basicUser.getUserIcon().toString()).exists()) {
				basicUser.setUserIcon(userIconHelper.getDefaultUserIcon());
			}
			File userIcon = new File(basicUser.getUserIcon());
//			FileResource userPhotoFile = new FileResource(userIcon,
//					getApplication());
//			
//			item.getItemProperty("userIcon").setValue(userIcon);
			basicUserDao.merge(basicUser);
		}
		return userContainer;
	}

	public ComboBox getAssginedUserSelect() {
		return assginedUserSelect;
	}

	public void setAssginedUserSelect(ComboBox assginedUserSelect) {
		this.assginedUserSelect = assginedUserSelect;
	}

	

	@Override
	public void attach() {
		super.attach();
	}

	public static BasicUser getSelectedUser() {
		return selectedUser;
	}

	public static void setSelectedUser(BasicUser selectedUser) {
		AssginedUserSelect.selectedUser = selectedUser;
	}

}
