package com.mocha.soicalAPI;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import com.coral.foundation.facebook.FBUserModel;
import com.coral.foundation.report.ProfileReport;
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
import com.coral.vaadin.widget.view.builder.PageBuildHelper;
import com.vaadin.Application;
import com.vaadin.data.Container;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.Container.Indexed;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.event.MouseEvents;
import com.vaadin.event.MouseEvents.ClickEvent;
import com.vaadin.terminal.ExternalResource;
import com.vaadin.terminal.FileResource;
import com.vaadin.terminal.PaintException;
import com.vaadin.terminal.PaintTarget;
import com.vaadin.terminal.Resource;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.NativeButton;
import com.vaadin.ui.Select;
import com.vaadin.ui.TreeTable;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class LinkedinReportProfileViewer extends EntityViewPanel implements Viewer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private LinkedinConnection linkedConn;
	private VerticalLayout settingContentPanel = new VerticalLayout();
	private NativeButton searchEngineBtn = new NativeButton("Get More Characteristic From Social Engine");
	private List<FBUserModel> fbUserModel;
	private NativeButton facebookCommonSearchBtn = new NativeButton("Search");
	private NativeButton facebookSearchFriend = new NativeButton("Search");

	private VerticalLayout mainLayout = new VerticalLayout();

	public LinkedinReportProfileViewer(LinkedinConnection linkedConn) {
		this.addStyleName("mocha-coding-viewer");
		this.setLinkedConn(linkedConn);
	}

	@Override
	public void attach() {
		getMainLayout().removeAllComponents();
		buildProfileLayout();
	}

	public void buildSoicalAppLayout() {
		VerticalLayout simpleAppsLayout = new VerticalLayout();
		mainLayout.addComponent(simpleAppsLayout);
		simpleAppsLayout.addStyleName("simpleApps");

		Embedded facebookCommonSearch = new Embedded("", new ThemeResource("images/fb-signIn.png"));
		Embedded facebookSign = new Embedded("", new ThemeResource("images/fb-signIn.png"));

		getMainLayout().addComponent(simpleAppsLayout);

		HorizontalLayout fbCommonSearchLayout = new HorizontalLayout();
		simpleAppsLayout.addComponent(fbCommonSearchLayout);
		facebookCommonSearch.setHeight("100px");
		facebookCommonSearch.setWidth("100px");
		fbCommonSearchLayout.addComponent(facebookCommonSearch);
		VerticalLayout intro = new VerticalLayout();
		intro.addStyleName("soicalAppIntro");
		fbCommonSearchLayout.addComponent(intro);
		Label introTitle = new Label("Facebook Search");
		intro.addComponent(introTitle);
		intro.setComponentAlignment(introTitle, Alignment.MIDDLE_CENTER);
		Label introIntro = new Label("Looking for people with name of" + linkedConn.getFirstName() + " " + linkedConn.getLastName()
				+ " within Facebook's public search engine");
		intro.addComponent(introIntro);
		intro.setComponentAlignment(introIntro, Alignment.MIDDLE_CENTER);

		getFacebookCommonSearchBtn().addStyleName("mocha-button");
		intro.addComponent(getFacebookCommonSearchBtn());
		intro.setComponentAlignment(getFacebookCommonSearchBtn(), Alignment.MIDDLE_CENTER);

		HorizontalLayout fbSignInLayout = new HorizontalLayout();
		simpleAppsLayout.addComponent(fbSignInLayout);
		facebookSign.setHeight("100px");
		facebookSign.setWidth("100px");
		fbSignInLayout.addComponent(facebookSign);
		VerticalLayout introFbSign = new VerticalLayout();
		fbSignInLayout.addComponent(introFbSign);
		introFbSign.addStyleName("soicalAppIntro");
		Label introTitleFBSign = new Label("Search within Facebook Friends");
		introFbSign.addComponent(introTitleFBSign);
		introFbSign.setComponentAlignment(introTitleFBSign, Alignment.MIDDLE_CENTER);
		Label introIntroFBSign = new Label("Looking for friends with name of " + linkedConn.getFirstName() + " " + linkedConn.getLastName()
				+ " within Your Facebook's Personal Account");
		introFbSign.addComponent(introIntroFBSign);
		introFbSign.setComponentAlignment(introIntroFBSign, Alignment.MIDDLE_CENTER);
		getFacebookSearchFriend().addStyleName("mocha-button");
		introFbSign.addComponent(getFacebookSearchFriend());
		introFbSign.setComponentAlignment(getFacebookSearchFriend(), Alignment.MIDDLE_CENTER);
	}

	private void buildProfileLayout() {
		ToolbarAdvance toolbar = new ToolbarAdvance();
		getMainLayout().addComponent(toolbar);
		settingContentPanel.setWidth("100%");
		getMainLayout().addComponent(settingContentPanel);
		this.addComponent(getMainLayout());
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

		Label eduction = WidgetFactory.createCaptionLabel("Eduction", getLinkedConn().getEduction());
		eduction.setContentMode(Label.CONTENT_XHTML);
		userFormLayout.addComponent(eduction);

		Label experience = WidgetFactory.createCaptionLabel("Experience", getLinkedConn().getExperience());
		experience.setContentMode(Label.CONTENT_XHTML);
		userFormLayout.addComponent(experience);
		settingContentPanel.addComponent(settingSection);
		getSearchEngineBtn().addStyleName("mocha-button");
		getMainLayout().addComponent(userFormLayout);
		getMainLayout().addComponent(searchEngineBtn);
		searchEngineBtn.setWidth("100%");

		if (fbUserModel != null) {
			mainLayout.removeComponent(buildFBUserLayout());
			mainLayout.addComponent(buildFBUserLayout());
		}
	}

	public Component buildFBUserLayout() {
//		buildSoicalAppLayout();
		VerticalLayout fbUserLayout = new VerticalLayout();
		final GridLayout gl = new GridLayout(10, fbUserModel.size() % 5 > 1 ? fbUserModel.size() : 1);
		gl.removeAllComponents();
		fbUserLayout.addComponent(gl);

		for (final FBUserModel fbUser : fbUserModel) {
			Resource res = new ExternalResource(fbUser.getPic_square());
			Embedded userPicture = new Embedded(null, res);
			userPicture.addListener(new MouseEvents.ClickListener() {
				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				@Override
				public void click(ClickEvent event) {
					ProfileReport pr = new ProfileReport();
					String fbProfileUrl = fbUser.getProfile_url();
					ArrayList<String> fbTimelineSections = pr.parseFacebookProfilePage(fbProfileUrl);
					buildFBTimelineSections(fbTimelineSections);
				}

				private void buildFBTimelineSections(ArrayList<String> fbTimelineSections) {
					gl.removeAllComponents();
					VerticalLayout fbTimelinelayout = new VerticalLayout();
					gl.addComponent(fbTimelinelayout);

					HorizontalLayout h = new HorizontalLayout();
					fbTimelinelayout.addComponent(h);
					Label picture = new Label(fbTimelineSections.get(0));
					picture.setContentMode(Label.CONTENT_XHTML);
					h.addComponent(picture);

					VerticalLayout v = new VerticalLayout();
					h.addComponent(v);
					for (int i = 1; i < fbTimelineSections.size(); i++) {
						Label timeline = new Label(fbTimelineSections.get(i));
						timeline.setContentMode(Label.CONTENT_XHTML);
						v.addComponent(timeline);
					}
				}
			});

			gl.addComponent(userPicture);
		}
		return fbUserLayout;
	}

	private void buildUpdateMessageLayout() {
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

	public LinkedinConnection getLinkedConn() {
		return linkedConn;
	}

	public void setLinkedConn(LinkedinConnection linkedConn) {
		this.linkedConn = linkedConn;
	}

	public NativeButton getSearchEngineBtn() {
		return searchEngineBtn;
	}

	public void setSearchEngineBtn(NativeButton searchEngineBtn) {
		this.searchEngineBtn = searchEngineBtn;
	}

	public List<FBUserModel> getFbUserModel() {
		return fbUserModel;
	}

	public void setFbUserModel(List<FBUserModel> fbUserModel) {
		this.fbUserModel = fbUserModel;
	}

	public VerticalLayout getMainLayout() {
		return mainLayout;
	}

	public void setMainLayout(VerticalLayout mainLayout) {
		this.mainLayout = mainLayout;
	}

	public NativeButton getFacebookCommonSearchBtn() {
		return facebookCommonSearchBtn;
	}

	public void setFacebookCommonSearchBtn(NativeButton facebookCommonSearchBtn) {
		this.facebookCommonSearchBtn = facebookCommonSearchBtn;
	}

	public NativeButton getFacebookSearchFriend() {
		return facebookSearchFriend;
	}

	public void setFacebookSearchFriend(NativeButton facebookSearchFriend) {
		this.facebookSearchFriend = facebookSearchFriend;
	}

}