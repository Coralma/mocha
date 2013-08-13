package com.mocha.soicalAPI;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import com.coral.foundation.security.model.BasicUser;
import com.coral.foundation.security.model.LinkedinConnection;
import com.coral.foundation.security.model.LinkedinConnectionNetworkUpdate;
import com.coral.vaadin.view.template.sat.panel.impl.EntityViewPanel;
import com.coral.vaadin.widget.Result;
import com.coral.vaadin.widget.Viewer;
import com.coral.vaadin.widget.Widget;
import com.coral.vaadin.widget.WidgetFactory;
import com.coral.vaadin.widget.component.ToolbarAdvance;
import com.coral.vaadin.widget.table.PagedTable;
import com.coral.vaadin.widget.table.PagedTableContainer;
import com.vaadin.Application;
import com.vaadin.data.Container;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.Container.Indexed;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.terminal.PaintException;
import com.vaadin.terminal.PaintTarget;
import com.vaadin.terminal.Resource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.NativeButton;
import com.vaadin.ui.TreeTable;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class SoicalConnectionProfileViewer extends EntityViewPanel implements Viewer {

	private LinkedinConnection linkedConn;

	private VerticalLayout settingContentPanel = new VerticalLayout();
	private Widget photoUploader;
	private Button changeButton;
	private String labelWidth = "120px";
	private String BASIC_INFO = "BasicInfo";
	private String CHANGE_PASSWORD = "ChangePassword";
	private NativeButton syncStatusBtn = new NativeButton("Sync Connection Status Now");

	VerticalLayout mainLayout = new VerticalLayout();

	public SoicalConnectionProfileViewer(LinkedinConnection linkedConn) {
		this.addStyleName("mocha-coding-viewer");
		this.setLinkedConn(linkedConn);
	}

	@Override
	public void attach() {
		buildProfileLayout();
	}

	private void buildProfileLayout() {
		mainLayout.removeAllComponents();
		ToolbarAdvance toolbar = new ToolbarAdvance();
		// Button basicInfoBtn = WidgetFactory.createLink("Basic Information", "BasicInfo", this);
		// toolbar.addRightComponent(basicInfoBtn);
		// Button changePassword = WidgetFactory.createLink("Change Password", "ChangePassword", this);
		// toolbar.addRightComponent(changePassword);
		mainLayout.addComponent(toolbar);
		settingContentPanel.setWidth("100%");
		mainLayout.addComponent(settingContentPanel);
		this.addComponent(mainLayout);
		this.buildBasicInformationPanel();
	}

	@Override
	public void build() {
		attach();
	}

	public void buildBasicInformationPanel() {
		settingContentPanel.removeAllComponents();
		VerticalLayout settingSection = new VerticalLayout();
		settingSection.addStyleName("setting-user-info");
		settingSection.setWidth("500px");

		FormLayout userFormLayout = new FormLayout();
		userFormLayout.setCaption(getLinkedConn().getFirstName() + "." + getLinkedConn().getLastName() + "'s Profile");
		userFormLayout.setSpacing(true);
		Label firstName = WidgetFactory.createCaptionLabel("First Name", getLinkedConn().getFirstName());
		userFormLayout.addComponent(firstName);
		Label lastName = WidgetFactory.createCaptionLabel("Last Name", getLinkedConn().getLastName());
		userFormLayout.addComponent(lastName);

		Label headLine = WidgetFactory.createCaptionLabel("HeadLine", getLinkedConn().getHeadline());
		userFormLayout.addComponent(headLine);

		Label companyName = WidgetFactory.createCaptionLabel("Company Name", getLinkedConn().getCompanyName());
		userFormLayout.addComponent(companyName);

		Label curernetStatus = WidgetFactory.createCaptionLabel("Curernet Status", getLinkedConn().getCurrentStatus());
		userFormLayout.addComponent(curernetStatus);

		Label imAccount = WidgetFactory.createCaptionLabel("IM Account", getLinkedConn().getImAccount());
		userFormLayout.addComponent(imAccount);

		Label industry = WidgetFactory.createCaptionLabel("Industry", getLinkedConn().getIndustry());
		userFormLayout.addComponent(industry);

		Label location = WidgetFactory.createCaptionLabel("Location", getLinkedConn().getLocation());
		userFormLayout.addComponent(location);

		Label locationCountry = WidgetFactory.createCaptionLabel("Location Country", getLinkedConn().getLocationCountry());
		userFormLayout.addComponent(locationCountry);

		Label summary = WidgetFactory.createCaptionLabel("Summary", getLinkedConn().getSummary());
		userFormLayout.addComponent(summary);

		Label twitterAccount = WidgetFactory.createCaptionLabel("Twitter Account", getLinkedConn().getTwitterAccount());
		userFormLayout.addComponent(twitterAccount);

		Label email = WidgetFactory.createCaptionLabel("Email Address", "");
		userFormLayout.addComponent(email);

		settingContentPanel.addComponent(settingSection);

		syncStatusBtn.addStyleName("mocha-button");
		settingContentPanel.addComponent(getSyncStatusBtn());
		settingContentPanel.setComponentAlignment(getSyncStatusBtn(), Alignment.TOP_RIGHT);

		mainLayout.addComponent(userFormLayout);

		buildUpdateMessageLayout();
	}

	private void buildUpdateMessageLayout() {

		// VerticalLayout recentlyLayout = new VerticalLayout();
		// recentlyLayout.setCaption("Recent Updates Messages");
		// List<LinkedinConnectionNetworkUpdate> updates = getLinkedConn().getLinkedinConnectionNetworkUpdate();
		// HashSet<String> updateMessages = new HashSet<String>();
		// Indexed messageConatiner = new IndexedContainer(updateMessages);
		// if (updates != null && updates.size() > 0) {
		// VerticalLayout recentlyLayoutMessage = new VerticalLayout();
		// recentlyLayoutMessage.setCaption("User Update Message");
		// recentlyLayoutMessage.addStyleName("linkedinConnectionsUpdateMessageLayout");
		//
		// PagedTableContainer ic = new PagedTableContainer(messageConatiner);
		// ic.setPageLength(messageConatiner.size());
		// ic.addContainerProperty("Update Message", String.class, null);
		// int i = 0;
		// for (LinkedinConnectionNetworkUpdate update : updates) {
		// if (update.getUpdateMessage() != null) {
		// updateMessages.add(update.getUpdateMessage());
		// Item item = messageConatiner.addItem(i++);
		// item.getItemProperty("Update Message").setValue(update.getUpdateMessage());
		// }
		// }
		// }
		// PagedTable pt = new PagedTable("");
		// pt.setWidth("800px");
		// pt.setContainerDataSource(messageConatiner);
		// settingContentPanel.addComponent(pt);
		// pt.getItemsPerPageSelect().setVisible(false);
		// recentlyLayout.addComponent(pt);
		// recentlyLayout.addComponent(pt.createControls());
		// this.addComponent(recentlyLayout);

		VerticalLayout recentlyLayout = new VerticalLayout();
		recentlyLayout.setCaption("Recent Updates Messages");
		recentlyLayout.setStyleName("linkedinConnectionsUpdateMessageLayout");

		List<LinkedinConnectionNetworkUpdate> updates = getLinkedConn().getLinkedinConnectionNetworkUpdate();
		if (updates != null && updates.size() > 0) {
			for (LinkedinConnectionNetworkUpdate update : updates) {
				if (update.getUpdateMessage() != null) {
					Label updateMessage = new Label();
					updateMessage.addStyleName("connectionMessage");
					updateMessage.setCaption(update.getUpdateMessage());
					recentlyLayout.addComponent(updateMessage);
					recentlyLayout.setComponentAlignment(updateMessage, Alignment.MIDDLE_LEFT);
				}
			}
		}
		this.addComponent(recentlyLayout);

	}

	public NativeButton getSyncStatusBtn() {
		return syncStatusBtn;
	}

	public void setSyncStatusBtn(NativeButton syncStatusBtn) {
		this.syncStatusBtn = syncStatusBtn;
	}

	public LinkedinConnection getLinkedConn() {
		return linkedConn;
	}

	public void setLinkedConn(LinkedinConnection linkedConn) {
		this.linkedConn = linkedConn;
	}

}