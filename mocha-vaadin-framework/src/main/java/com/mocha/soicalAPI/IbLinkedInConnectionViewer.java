package com.mocha.soicalAPI;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import com.coral.foundation.constant.RuntimeConstant;
import com.coral.foundation.linkedin.LinkedinImpl;
import com.coral.foundation.security.basic.dao.BasicUserDao;
import com.coral.foundation.security.model.BasicUser;
import com.coral.foundation.security.model.LinkedinConnection;
import com.coral.foundation.security.model.LinkedinPersonProfile;
import com.coral.foundation.security.model.SoicalApp;
import com.coral.foundation.spring.bean.SpringContextUtils;
import com.coral.vaadin.view.template.sat.panel.impl.EntityViewPanel;
import com.coral.vaadin.widget.Viewer;
import com.coral.vaadin.widget.WidgetFactory;
import com.coral.vaadin.widget.component.ToolbarAdvance;
import com.coral.vaadin.widget.table.PagedTable;
import com.coral.vaadin.widget.table.PagedTableContainer;
import com.google.code.linkedinapi.client.oauth.LinkedInAccessToken;
import com.invient.vaadin.charts.InvientCharts;
import com.mocha.report.CrmReportViewer.ReportCardGroup;
import com.vaadin.data.Container.Indexed;
import com.vaadin.data.Item;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.Link;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;

public class IbLinkedInConnectionViewer extends EntityViewPanel implements Viewer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String width = "500px";
	private BasicUser user;
	BasicUserDao buDao = SpringContextUtils.getBean(BasicUserDao.class);
	private Button allLink = WidgetFactory.createLink("All LinkedIn Connections");
	private Button followedLink = WidgetFactory.createLink("Followed Connections");
	private List<LinkedinConnection> followedConnection = new ArrayList<LinkedinConnection>();
	private LinkedinThumbnailListener tnListener;
	private LinkedinPersonProfile personProfile;

	public IbLinkedInConnectionViewer(BasicUser user) {
		this.user = user;
	}

	@Override
	public void attach() {
		setSizeFull();
		removeAllComponents();
		buildToolBar();
		buildSearchConnectionLayout();
	}

	public void buildFollowedConnectionsLayout() {
		this.removeAllComponents();
		buildToolBar();
		LinkedInConnectionGroup group = new LinkedInConnectionGroup("Followed Connections", followedConnection);
		this.addComponent(group);
	}

	public void buildSearchConnectionLayout() {
		this.removeAllComponents();
		buildToolBar();
		buildConnectsInfo();
	}

	private void buildToolBar() {
		ToolbarAdvance toolbar = new ToolbarAdvance();
		toolbar.setToolbarWidth(RuntimeConstant.APP_CONTENT_WIDTH);
		toolbar.addLeftComponent(getAllLink());
		toolbar.addLeftComponent(getFollowedLink());

		// createButton = WidgetFactory.createButton("Create Report");
		// toolbar.addRightComponent(createButton);
		this.addComponent(toolbar);
	}

	public Panel buildPanel(String caption) {
		Panel dash = new Panel();
		dash.setWidth("100%");
		dash.setCaption(caption);
		dash.setHeight("300px");
		return dash;
	}

	protected void buildConnectsInfo() {
		VerticalLayout portalLayout = new VerticalLayout();
		portalLayout.addStyleName("app-dashboard");
		portalLayout.setSpacing(true);
		// portalLayout.setWidth(width);

		LinkedinImpl apiImpl = new LinkedinImpl();
		// List<LinkedinConnection> connections = user.getSoicalApp().get(0).getLinkedinPersonProfiles().get(0).getLinkedinConnections();

		List<LinkedinConnection> connections = new ArrayList<LinkedinConnection>();
		for (SoicalApp soicalApp : user.getSoicalApp()) {
			if (soicalApp.getName().equals("linkedin") && soicalApp.getAuthToken() != null) {
				if (soicalApp.getLinkedinPersonProfiles() != null) {
					List<LinkedinPersonProfile> profiles = soicalApp.getLinkedinPersonProfiles();
					for (LinkedinPersonProfile lp : soicalApp.getLinkedinPersonProfiles()) {
						this.personProfile = lp;
						if (lp.getLinkedinConnections().size() > 0) {
							connections = lp.getLinkedinConnections();
						}
						else {
							LinkedInAccessToken token = new LinkedInAccessToken(soicalApp.getAuthToken(), soicalApp.getAuthTokenSecret());
							connections = apiImpl.getAlUserConnects(token);
							user.getSoicalApp().remove(soicalApp);
							LinkedinPersonProfile linkedProfile = soicalApp.getLinkedinPersonProfiles().get(0);
							linkedProfile.setLinkedinConnections(connections);
							for (LinkedinConnection linkedConn : connections) {
								linkedConn.setLinkedinPersonProfile(linkedProfile);
							}
							user.getSoicalApp().add(soicalApp);
							buDao.merge(user);
							break;
						}
					}
				}
				else {
					System.out.println("No linkedin person profile found!");
					// LinkedInAccessToken token = new LinkedInAccessToken(soicalApp.getAuthToken(), soicalApp.getAuthTokenSecret());
					// connections = apiImpl.getAlUserConnects(token);
					// user.getSoicalApp().remove(soicalApp);
					// soicalApp.getLinkedinPersonProfiles().get(0).setLinkedinConnections(connections);
					// user.getSoicalApp().add(soicalApp);
					// buDao.merge(user);
					// break;
				}
			}
		}

		VerticalLayout userInfoLayout = new VerticalLayout();
		userInfoLayout.setSpacing(true);
		userInfoLayout.addStyleName("linkedinUserInfoLayout");
		portalLayout.addComponent(userInfoLayout);

		HashSet<LinkedinConnectsCard> linkedinConnectsCards = new HashSet<LinkedinConnectsCard>();

		Indexed conatiner = new IndexedContainer(linkedinConnectsCards);
		PagedTableContainer ic = new PagedTableContainer(conatiner);
		ic.setPageLength(connections.size());
		ic.addContainerProperty("LinkedinConnectsCard", LinkedinConnectsCard.class, null);
		int i = 0;
		for (LinkedinConnection connect : connections) {
			connect.setLinkedinPersonProfile(personProfile);
			LinkedinConnectsCard lCard = new LinkedinConnectsCard(connect, this.personProfile);
			linkedinConnectsCards.add(lCard);
			Item item = conatiner.addItem(i++);
			item.getItemProperty("LinkedinConnectsCard").setValue(lCard);
		}
		PagedTable pt = new PagedTable("Connections");
		pt.setWidth("800px");
		pt.setContainerDataSource(conatiner);
		userInfoLayout.addComponent(pt);
		pt.getItemsPerPageSelect().setVisible(false);
		userInfoLayout.addComponent(pt.createControls());
		this.requestRepaintAll();
		this.addComponent(portalLayout);
	}

	protected void buildPersonInfo() {
		// mainLayout.removeAllComponents();
		// mainLayout.addComponent(new Label("You have already authencation from LinkedIn"));

		Label userHasTokenLabel = new Label("Here are your connections profile:");
		this.addComponent(userHasTokenLabel);
		GridLayout userInfoLayout = new GridLayout(2, 1);
		userInfoLayout.setSpacing(true);
		userInfoLayout.addStyleName("linkedinUserInfoLayout");
		this.addComponent(userInfoLayout);
		if (user != null) {
			for (SoicalApp soicalApp : user.getSoicalApp()) {
				if (soicalApp.getName().equals("linkedin") && soicalApp.getAuthToken() != null) {
					if (soicalApp.getLinkedinPersonProfiles() != null) {
						List<LinkedinPersonProfile> profiles = soicalApp.getLinkedinPersonProfiles();
						for (LinkedinPersonProfile p : profiles) {
							Label userName = new Label(p.getFirstName() + "." + p.getLastName());
							userInfoLayout.addComponent(userName);
							Label userHead = new Label(p.getHeadline());
							userInfoLayout.addComponent(userHead);
						}
					}
				}
			}
		}

	}

	@Override
	public void build() {
		attach();
	}

	public Button getAllLink() {
		return allLink;
	}

	public void setAllLink(Button allLink) {
		this.allLink = allLink;
	}

	public Button getFollowedLink() {
		return followedLink;
	}

	public void setFollowedLink(Button followedLink) {
		this.followedLink = followedLink;
	}

	public List<LinkedinConnection> getFollowedConnection() {
		return followedConnection;
	}

	public void setFollowedConnection(List<LinkedinConnection> followedConnection) {
		this.followedConnection = followedConnection;
	}
}
