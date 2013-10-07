package com.mocha.soicalAPI;

import java.util.List;

import com.coral.foundation.security.model.LinkedinConnection;
import com.coral.vaadin.widget.WidgetFactory;
import com.vaadin.event.LayoutEvents.LayoutClickEvent;
import com.vaadin.event.LayoutEvents.LayoutClickListener;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.NativeButton;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

public class LinkedInConnectionGroup extends VerticalLayout {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String category;
	public List<LinkedinConnection> linkedInConnections;
	public LinkedinThumbnailListener tnListener;
	private NativeButton viewDetailBtn = new NativeButton("View Details");
	private LinkedinConnection selectedConn;
	private int connNum = 10;

	public LinkedInConnectionGroup(String category, List<LinkedinConnection> linkedInConnections) {
		this.category = category;
		this.linkedInConnections = linkedInConnections;
		this.setWidth("100%");
		this.addStyleName("report-card-group");
	}

	public void attach() {

		Label categoryLabel = new Label();
		categoryLabel.setHeight("5px");
		ThemeResource icon=new ThemeResource("icons/icon-linkedin.jpg");
		categoryLabel.setIcon(icon);
		categoryLabel.setCaption(category);
		categoryLabel.addStyleName("report-category");
		this.addComponent(categoryLabel);

		GridLayout groupLayout = new GridLayout(4, getConnNum());
		groupLayout.setSpacing(true);
		groupLayout.setSizeFull();
		if (linkedInConnections == null || linkedInConnections.size() == 0) {
			groupLayout.setCaption("None LinkedIn Connections Selected");
		}
		else {
			for (final LinkedinConnection linkedinCon : linkedInConnections) {
				LinkedinConnectionthumbnailCard linkedConCard = new LinkedinConnectionthumbnailCard(linkedinCon, linkedinCon.getLinkedinPersonProfile());
				linkedConCard.setWidth("100px");
				groupLayout.addComponent(linkedConCard);
				groupLayout.setComponentAlignment(linkedConCard, Alignment.MIDDLE_LEFT);
				linkedConCard.addListener(new LayoutClickListener() {

					@Override
					public void layoutClick(LayoutClickEvent event) {
						setSelectedConn(linkedinCon);
						viewDetailBtn.click();
					}
				});
			}

			// // add the empty
			// int appSize = linkedInConnections.size();
			// int c = appSize % 3;
			// if (c != 0) {
			// c = 3 - c;
			// }
			// for (int i = 0; i < c; i++) {
			// VerticalLayout layout = new VerticalLayout();
			// layout.setWidth("248px");
			// layout.addComponent(WidgetFactory.createLabel(" "));
			// groupLayout.addComponent(layout);
			// }
		}
		this.addComponent(groupLayout);
	}

	public NativeButton getViewDetailBtn() {
		return viewDetailBtn;
	}

	public void setViewDetailBtn(NativeButton viewDetailBtn) {
		this.viewDetailBtn = viewDetailBtn;
	}

	public LinkedinConnection getSelectedConn() {
		return selectedConn;
	}

	public void setSelectedConn(LinkedinConnection selectedConn) {
		this.selectedConn = selectedConn;
	}

	public int getConnNum() {
		return connNum;
	}

	public void setConnNum(int connNum) {
		this.connNum = connNum;
	}
}
