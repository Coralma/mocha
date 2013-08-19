package com.mocha.newsfeeds;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import com.coral.foundation.security.model.LinkedinConnection;
import com.coral.vaadin.view.template.sat.panel.impl.EntityViewPanel;
import com.coral.vaadin.widget.Viewer;
import com.coral.vaadin.widget.view.builder.PageBuildHelper;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

public class NewsFeedsViewer extends EntityViewPanel implements Viewer {

	private VerticalLayout mainLayout = new VerticalLayout();
	private List<LinkedinConnection> linkedinConnections;

	public NewsFeedsViewer() {
		this.addComponent(mainLayout);
	}

	public NewsFeedsViewer(List<LinkedinConnection> linkedinConnections) {
		this.addComponent(mainLayout);
		this.linkedinConnections = linkedinConnections;
	}

	@Override
	public void build() {
		for (LinkedinConnection con : linkedinConnections) {
			NewsFeedsCard newsFeedsCard = new NewsFeedsCard(con);
			mainLayout.addComponent(newsFeedsCard);
		}
	}

	public void attach() {
		build();
	}

	public class NewsFeedsCard extends Panel {

		private VerticalLayout cardMainLayout = new VerticalLayout();
		private LinkedinConnection linkeCon;

		public NewsFeedsCard(LinkedinConnection linkeCon) {
			this.linkeCon = linkeCon;
			this.addComponent(cardMainLayout);
			buildCardCompoent();
		}

		private void buildCardCompoent() {
			HorizontalLayout cardLayout = new HorizontalLayout();
			cardMainLayout.addComponent(cardLayout);
			URL url = null;
			try {
				url = new URL(linkeCon.getPictUrl());
			}
			catch (MalformedURLException e) {
//				e.printStackTrace();
			}
			Embedded userPict = PageBuildHelper.buildUserPhotoFromURL(url, getApplication());
			cardLayout.addComponent(userPict);

			VerticalLayout userInfoAndComments = new VerticalLayout();
			cardLayout.addComponent(userInfoAndComments);

			Label userName = new Label(linkeCon.getFirstName() + " " + linkeCon.getLastName());
			userName.addStyleName("newsFeedsUserName");
			userInfoAndComments.addComponent(userName);

			Label companyName = new Label("@"+linkeCon.getCompanyName());
			companyName.addStyleName("newsFeedsCompanyName");
			userInfoAndComments.addComponent(companyName);

			Label lastUpdateStatus = new Label(linkeCon.getCurrentStatus());
			lastUpdateStatus.addStyleName("newsFeedslastUpdateStatus");
			userInfoAndComments.addComponent(lastUpdateStatus);
		}
	}
}
