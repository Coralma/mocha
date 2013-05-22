/**
 * 
 */
package com.mocha.cooperate.page;

import java.util.List;

import com.coral.foundation.security.model.BasicUser;
import com.coral.vaadin.widget.Viewer;
import com.coral.vaadin.widget.WidgetFactory;
import com.coral.vaadin.widget.component.ToolbarAdvance;
import com.coral.vaadin.widget.listener.EnterClickListener;
import com.coral.vaadin.widget.view.CommonViewer;
import com.coral.vaadin.widget.view.builder.PageBuildHelper;
import com.mocha.cooperate.SystemProperty;
import com.mocha.cooperate.page.ColleaguePresenter.ColleagueListener;
import com.vaadin.event.LayoutEvents.LayoutClickEvent;
import com.vaadin.event.LayoutEvents.LayoutClickListener;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class ColleagueView extends CommonViewer implements Viewer, ClickListener, EnterClickListener {

	private List<BasicUser> listUsers;
	private String cardWidth = "150px";
	private ColleagueListener listener;
	private TextField searchField;
	private VerticalLayout userListlayout = new VerticalLayout();
	
	public ColleagueView(List<BasicUser> basicUsers) {
		this.listUsers = basicUsers;
	}
	
	@Override
	public void attach() {
		super.attach();
		ToolbarAdvance toolbart = new ToolbarAdvance();
		toolbart.addLeftComponent(WidgetFactory.createLink(message.getString("cooperate.colleague.AllColleague"), "all", this));
//		toolbart.addLeftComponent(WidgetFactory.createLink("AllColleague", "all", this));
		searchField = WidgetFactory.createSearchTextField(message.getString("cooperate.search"), "search", this);
		toolbart.addLeftComponent(searchField);
		this.addComponent(toolbart);
		userListlayout.setWidth(SystemProperty.content_page_width);
		this.addComponent(userListlayout);
		buildListUserPanel();
	}
	
	public void buildListUserPanel() {
		userListlayout.removeAllComponents();
		int index = 0;
		HorizontalLayout rowLayout = null;
		for(BasicUser user : listUsers) {
			if(index == 0 || index == 4) {
				rowLayout = new HorizontalLayout();
				rowLayout.setSpacing(true);
				userListlayout.addComponent(rowLayout);
				index = 0;
			}
			ColleagueCard userCard = new ColleagueCard(user);
			rowLayout.addComponent(userCard);
			index++;
		}
		if(index > 0 && index < 4) {
			for(int i= index; i<4; i++) {
				HorizontalLayout empty = new HorizontalLayout();
				empty.setWidth(cardWidth);
				rowLayout.addComponent(empty);
			}
		}
	}
	

	@Override
	public void handleEnter(String text) {
		listener.searchUser(text);
	}
	

	@Override
	public void buttonClick(ClickEvent event) {
		if("all".equals(event.getButton().getData())) {
			searchField.setValue(null);
			listener.searchUser(null);
		}
	}
	
	@Override
	public String getViewerTitle() {
		return "cooperate.colleague.title";
	}
	
	public class ColleagueCard extends HorizontalLayout {

		private BasicUser user;
		protected String frame_size = "58px";
		private String photo_size = "50px";
		
		public ColleagueCard(BasicUser user) {
			this.user = user;
			this.setWidth(cardWidth);
			this.addStyleName("colleague-card");
			this.addListener(new LayoutClickListener() {
				@Override
				public void layoutClick(LayoutClickEvent event) {
					clickCard();
				}
			});
		}

		public void attach() {
			VerticalLayout userArea = new VerticalLayout();
			userArea.addStyleName("user-card-photo");
			userArea.setWidth(frame_size);
			userArea.setHeight(frame_size);
			
			Embedded userPhoto = PageBuildHelper.buildUserPhoto(user.getUserPhoto(), getApplication());
			userPhoto.setWidth(photo_size);
			userPhoto.setHeight(photo_size);
			userArea.addComponent(userPhoto);
			this.addComponent(userArea);
			
			Label useName = new Label(user.getRealName(), Label.CONTENT_XHTML);
			useName.setWidth("100px");
			this.addComponent(useName);
			this.setComponentAlignment(useName, Alignment.MIDDLE_LEFT);
		}
		
		public void clickCard() {
			listener.cardClick(user);
		}
	}

	/**
	 * @return the listener
	 */
	public ColleagueListener getListener() {
		return listener;
	}

	/**
	 * @param listener the listener to set
	 */
	public void setListener(ColleagueListener listener) {
		this.listener = listener;
	}

	/**
	 * @return the listUsers
	 */
	public List<BasicUser> getListUsers() {
		return listUsers;
	}

	/**
	 * @param listUsers the listUsers to set
	 */
	public void setListUsers(List<BasicUser> listUsers) {
		this.listUsers = listUsers;
	}
}
