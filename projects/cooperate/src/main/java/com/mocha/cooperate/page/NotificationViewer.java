package com.mocha.cooperate.page;

import java.util.List;

import com.coral.foundation.security.model.BasicUser;
import com.coral.vaadin.widget.Viewer;
import com.coral.vaadin.widget.view.CommonViewer;
import com.coral.vaadin.widget.view.builder.PageBuildHelper;
import com.mocha.cooperate.model.NotifyLine;
import com.mocha.cooperate.service.NotifyLineDaoService;
import com.mocha.cooperate.widget.cards.DiscussCard;
import com.mocha.cooperate.widget.cards.StatusCard;
import com.vaadin.ui.VerticalLayout;

public class NotificationViewer extends CommonViewer implements Viewer {
	
	private VerticalLayout notifyLayout = new VerticalLayout();
	
	public NotificationViewer() {
		this.setSpacing(true);
		this.addStyleName("home-content");
	}

	public void attach() {
		super.attach();
//		VerticalLayout discussTitle = PageBuildHelper.buildPageTitle(message.getString("cooperate.notification.title"));
//		addComponent(discussTitle);

		NotifyLineDaoService notifyLineDaoService = new NotifyLineDaoService();
		List<NotifyLine> notifies = notifyLineDaoService.loadNotifyLine((BasicUser) getApplication().getUser());
		
		notifyLayout.setMargin(false,false,true,false);
		notifyLayout.addStyleName("home-tab-layout");
		notifyLayout.setSpacing(true);
		notifyLayout.setWidth("100%");
		
		for(NotifyLine notifie : notifies) {
			if(notifie.getStatus() != null) {
				StatusCard statusCard = new StatusCard(notifie);
				notifyLayout.addComponent(statusCard);
			}
			if(notifie.getDiscuss() != null) {
				DiscussCard discussCard = new DiscussCard(notifie); 
				notifyLayout.addComponent(discussCard);
			}
		}
		this.addComponent(notifyLayout);
		
	}
	
	@Override
	public String getViewerTitle() {
		return "cooperate.notification.title";
	}

}
