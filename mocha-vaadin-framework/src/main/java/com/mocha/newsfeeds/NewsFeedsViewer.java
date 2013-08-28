package com.mocha.newsfeeds;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import com.coral.foundation.security.model.LinkedinConnection;
import com.coral.foundation.security.model.LinkedinConnectionNetworkUpdate;
import com.coral.vaadin.view.template.sat.panel.impl.EntityViewPanel;
import com.coral.vaadin.widget.Viewer;
import com.coral.vaadin.widget.view.builder.PageBuildHelper;
import com.vaadin.event.LayoutEvents.LayoutClickEvent;
import com.vaadin.event.LayoutEvents.LayoutClickListener;
import com.vaadin.event.MouseEvents.ClickListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

public class NewsFeedsViewer extends EntityViewPanel implements Viewer {

	private VerticalLayout mainLayout = new VerticalLayout();
	// private List<LinkedinConnection> linkedinConnections;
	private List<LinkedinConnectionNetworkUpdate> updateStatues;

	public NewsFeedsViewer() {
		this.addComponent(mainLayout);
	}

	public NewsFeedsViewer(List<LinkedinConnectionNetworkUpdate> updateStatues) {
		this.addComponent(mainLayout);
		this.updateStatues = updateStatues;
	}

	@Override
	public void build() {
		Label bannheader = new Label("LinkedIn Updates");
		mainLayout.addComponent(bannheader);
		for (LinkedinConnectionNetworkUpdate conUpdateStatus : updateStatues) {
			NewsFeedsCard newsFeedsCard = new NewsFeedsCard(conUpdateStatus);
			mainLayout.addComponent(newsFeedsCard);
		}
	}

	public void attach() {
		build();
	}

	public class NewsFeedsCard extends Panel implements LayoutClickListener {

		private VerticalLayout cardMainLayout = new VerticalLayout();
		private LinkedinConnection linkeCon;
		private LinkedinConnectionNetworkUpdate conUpdateStatus;

		public NewsFeedsCard(LinkedinConnectionNetworkUpdate conUpdateStatus) {
			this.conUpdateStatus = conUpdateStatus;
			this.linkeCon = conUpdateStatus.getLinkedinConnection();
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
				// e.printStackTrace();
			}

			Embedded userPict = PageBuildHelper.buildUserPhotoFromURL(url, getApplication());
			userPict.addListener(new ClickListener() {

				@Override
				public void click(com.vaadin.event.MouseEvents.ClickEvent event) {

				}
			});
			cardLayout.addComponent(userPict);

			VerticalLayout userInfoAndComments = new VerticalLayout();
			userInfoAndComments.addStyleName("news-feed-userInfoAndComments");
			cardLayout.addComponent(userInfoAndComments);

			Button userNameBtn = new Button(linkeCon.getFirstName() + " " + linkeCon.getLastName());
			userNameBtn.setStyle(Button.STYLE_LINK);
			userNameBtn.addListener(new Button.ClickListener() {

				@Override
				public void buttonClick(ClickEvent event) {
					
				}
			});
			userNameBtn.addStyleName("newsFeedsUserName");
			userInfoAndComments.addComponent(userNameBtn);

			Label companyName = new Label(linkeCon.getCompanyName());
			companyName.addStyleName("newsFeedsCompanyName");
			userInfoAndComments.addComponent(companyName);

			// Label lastUpdateStatus = new Label(linkeCon.getCurrentStatus());
			// lastUpdateStatus.addStyleName("newsFeedslastUpdateStatus");
			// userInfoAndComments.addComponent(lastUpdateStatus);

			Label updateStatusLabel = new Label(conUpdateStatus.getUpdateMessage());
			updateStatusLabel.addStyleName("newsFeedslastUpdateStatus");
			userInfoAndComments.addComponent(updateStatusLabel);

		}

		@Override
		public void layoutClick(LayoutClickEvent event) {
			System.out.println("User Click");
		}

	}
}
