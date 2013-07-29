package com.mocha.soicalAPI;

import java.util.Collection;
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
import com.vaadin.Application;
import com.vaadin.data.Property;
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
		mainLayout.addComponent(userFormLayout);
		settingContentPanel.addComponent(settingSection);

		VerticalLayout recentlyLayout = new VerticalLayout();
		userFormLayout.addComponent(recentlyLayout);
		// Label recentlyLayoutCaption = WidgetFactory.createCaptionLabel("Recent Updates", "");
		recentlyLayout.setCaption("Recent Updates Messages");
		List<LinkedinConnectionNetworkUpdate> updates = getLinkedConn().getLinkedinConnectionNetworkUpdate();
		if (updates != null && updates.size() > 0) {
			for (LinkedinConnectionNetworkUpdate update : updates) {
				if (update.getUpdateMessage() != null) {
					Label updateLabel = WidgetFactory.createCaptionLabel("Update Messages", update.getUpdateMessage());
					recentlyLayout.addComponent(updateLabel);
				}
			}

		}
		syncStatusBtn.addStyleName("mocha-button");
		settingContentPanel.addComponent(getSyncStatusBtn());
		settingContentPanel.setComponentAlignment(getSyncStatusBtn(),Alignment.TOP_RIGHT);
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