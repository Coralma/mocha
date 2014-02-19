package com.mocha.cooperate.page;

import java.util.List;

import com.coral.foundation.core.impl.MochaEventBus;
import com.coral.foundation.security.model.BasicUser;
import com.coral.vaadin.widget.Viewer;
import com.coral.vaadin.widget.view.CommonViewer;
import com.mocha.cooperate.model.NotifyLine;
import com.mocha.cooperate.service.NotifyLineService;
import com.mocha.cooperate.widget.PagingVerticalLayout;
import com.mocha.cooperate.widget.cards.DiscussCard;
import com.mocha.cooperate.widget.cards.StatusCard;
import com.mocha.cooperate.widget.listener.PagingListener;

public class NotificationViewer extends CommonViewer implements Viewer {
	
//	private VerticalLayout notifyLayout = new VerticalLayout();
	private PagingVerticalLayout notifyLayout = new PagingVerticalLayout(); 
	private MochaEventBus eventBus;
	private String data = "notify";
	
	public NotificationViewer(MochaEventBus eventBus) {
		this.eventBus = eventBus;
		this.setSpacing(true);
		this.addStyleName("home-content");
	}

	public void attach() {
		super.attach();
//		VerticalLayout discussTitle = PageBuildHelper.buildPageTitle(message.getString("cooperate.notification.title"));
//		addComponent(discussTitle);

//		NotifyLineService notifyLineDaoService = new NotifyLineService();
//		List<NotifyLine> notifies = notifyLineDaoService.loadNotifyLine((BasicUser) getApplication().getUser(), 0);
		
		notifyLayout.setType(data);
		notifyLayout.setData(data);
		notifyLayout.setMargin(false,false,true,false);
		notifyLayout.addStyleName("home-tab-layout");
		notifyLayout.setSpacing(true);
		notifyLayout.setWidth("100%");
		notifyLayout.setListener(new PagingListener() {
			@Override
			public void showMore(String type, int pageNum) {
				loadAndDisplay(pageNum);
				notifyLayout.requestRepaintAll();
			}
		});
		
//		for(NotifyLine notifie : notifies) {
//			if(notifie.getStatus() != null) {
//				StatusCard statusCard = new StatusCard(notifie);
//				notifyLayout.addComponent(statusCard);
//			}
//			if(notifie.getDiscuss() != null) {
//				DiscussCard discussCard = new DiscussCard(notifie, eventBus); 
//				notifyLayout.addComponent(discussCard);
//			}
//		}
		loadAndDisplay(0);
		this.addComponent(notifyLayout);
	}
	
	public void loadAndDisplay(int pageNum) {
		NotifyLineService notifyLineDaoService = new NotifyLineService();
		List<NotifyLine> notifies = notifyLineDaoService.loadNotifyLine((BasicUser) getApplication().getUser(), pageNum);
		for(NotifyLine notifie : notifies) {
			if(notifie.getStatus() != null) {
				StatusCard statusCard = new StatusCard(notifie);
				notifyLayout.addComponent(statusCard);
			}
			if(notifie.getDiscuss() != null) {
				DiscussCard discussCard = new DiscussCard(notifie, eventBus); 
				notifyLayout.addComponent(discussCard);
			}
		}
	}
	
	@Override
	public String getViewerTitle() {
		return "cooperate.notification.title";
	}

}
