/**
 * 
 */
package com.mocha.cooperate.widget;

import java.util.List;

import com.coral.foundation.utils.Message;
import com.mocha.cooperate.SystemProperty;
import com.mocha.cooperate.model.TimeLine;
import com.mocha.cooperate.page.event.TimelineListener;
import com.mocha.cooperate.widget.cards.DiscussCard;
import com.mocha.cooperate.widget.cards.StatusCard;
import com.mocha.cooperate.widget.cards.TodoCard;
import com.mocha.cooperate.widget.listener.PagingListener;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TabSheet.SelectedTabChangeEvent;
import com.vaadin.ui.TabSheet.SelectedTabChangeListener;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.Reindeer;

/**
 * @author Coral
 *
 */
public class TimeLineSheet extends VerticalLayout {

	private String pageWidth = SystemProperty.content_page_width;
	private String widgetWidth = SystemProperty.content_widget_width;
	private Message message;
	private TimelineListener timelineListener;
	private PagingListener pagingListener;
	private PagingVerticalLayout allLayout = new PagingVerticalLayout();
	private PagingVerticalLayout statusLayout = new PagingVerticalLayout();
	private PagingVerticalLayout discussLayout = new PagingVerticalLayout();
	private PagingVerticalLayout todoLayout = new PagingVerticalLayout();
	private PagingVerticalLayout currentLayout;
	
	public TimeLineSheet() {
	}
	
	public void attach() {
		this.message = new Message(getApplication().getLocale());
		TabSheet tabSheet = new TabSheet();
		tabSheet.addStyleName(Reindeer.TABSHEET_MINIMAL);
		tabSheet.setWidth(pageWidth);
		
		initLayout(allLayout, SystemProperty.ALL);
		initLayout(statusLayout, SystemProperty.STATUS);
		initLayout(discussLayout, SystemProperty.DISCUSS);
		initLayout(todoLayout, SystemProperty.ToDo);
		
		currentLayout = (PagingVerticalLayout) allLayout;
		refreshAll(timelineListener.loadMessages(SystemProperty.ALL));
		
		tabSheet.addStyleName("home-content");
		tabSheet.addTab(allLayout, message.getString("cooperate.home.All"),new ThemeResource("icons/home_icon.png"));
		tabSheet.addTab(statusLayout, message.getString("cooperate.home.Status"),new ThemeResource("icons/status_icon.png"));
		tabSheet.addTab(discussLayout, message.getString("cooperate.home.Discuss"),new ThemeResource("icons/discuss_icon.png"));
		tabSheet.addTab(todoLayout, message.getString("cooperate.home.ToDo"),new ThemeResource("icons/todo-icon.png"));
		
		tabSheet.addListener(new SelectedTabChangeListener() {
			@Override
			public void selectedTabChange(SelectedTabChangeEvent event) {
				if(timelineListener != null) {
					TabSheet tab = event.getTabSheet();
					VerticalLayout layout = (VerticalLayout)tab.getSelectedTab();
					currentLayout = (PagingVerticalLayout) layout;
					String data = (String) layout.getData();
					if(SystemProperty.ALL.equals(data)) {
						refreshAll(timelineListener.loadMessages(SystemProperty.ALL));
					} else if(SystemProperty.STATUS.equals(data)) {
						refreshStatus(timelineListener.loadMessages(SystemProperty.STATUS));
					} else if(SystemProperty.DISCUSS.equals(data)) {
						refreshDiscuss(timelineListener.loadMessages(SystemProperty.DISCUSS));
					} else if (SystemProperty.ToDo.equals(data)) {
						refreshToDo(timelineListener.loadMessages(SystemProperty.ToDo));
					}
				}
			}
		});
		this.addComponent(tabSheet);
	}
	
	public void initLayout(PagingVerticalLayout layout, String data) {
		// set data as type such as SystemProperty.ToDo, SystemProperty.DISCUSS
		layout.setType(data);
		layout.setListener(pagingListener);
		layout.setMargin(false,false,true,false);
		layout.addStyleName("home-tab-layout");
		layout.setSpacing(true);
		layout.setWidth(widgetWidth);
		layout.setData(data);
	}

	public void removeAllComponents(String type) {
		if(SystemProperty.ALL.equals(type)) {
			allLayout.removeAllComponents();
		} else if(SystemProperty.STATUS.equals(type)) {
			statusLayout.removeAllComponents();	
		} else if(SystemProperty.DISCUSS.equals(type)) {
			discussLayout.removeAllComponents();	
		} else if(SystemProperty.ToDo.equals(type)) {
			todoLayout.removeAllComponents();	
		}
	}
	
	public void refreshAll(List<TimeLine> timelines) {
		if(getApplication() == null) return;
//		allLayout.removeAllComponents();
		for(TimeLine timeline : timelines) {
			if(timeline.getStatus() != null) {
				StatusCard statusCard = new StatusCard(timeline);
				allLayout.addComponent(statusCard);
			}
			if(timeline.getDiscuss() != null) {
				DiscussCard discussCard = new DiscussCard(timeline); 
				allLayout.addComponent(discussCard);
			}
			if (timeline.getTodo() != null
					&& timeline.getTodo().getAssginedUser() != null) {
//				if (timeline.getTodo().getAssginedUser().getBasicUserId() == ((BasicUser) getApplication()
//						.getUser()).getBasicUserId()) {
					TodoCard toDoCard = new TodoCard(timeline);
					allLayout.addComponent(toDoCard);
//				}
			}
		}
		allLayout.requestRepaintAll();
	}
	
	public void refreshStatus(List<TimeLine> timelines) {
//		statusLayout.removeAllComponents();
		for(TimeLine timeline : timelines) {
			if(timeline.getStatus() != null) {
				StatusCard statusCard = new StatusCard(timeline);
				statusLayout.addComponent(statusCard);
			}
		}
		statusLayout.requestRepaintAll();
	}
	
	public void refreshDiscuss(List<TimeLine> timelines) {
//		discussLayout.removeAllComponents();
		for(TimeLine timeline : timelines) {
			if(timeline.getDiscuss() != null) {
				DiscussCard discussCard = new DiscussCard(timeline); 
				discussLayout.addComponent(discussCard);
			}
		}
		discussLayout.requestRepaintAll();
	}
	
	public void refreshToDo(List<TimeLine> timelines) {
//		todoLayout.removeAllComponents();
		for (TimeLine timeline : timelines) {
			if(timeline.getTodo()!=null){
				TodoCard todoCard = new TodoCard(timeline);
				todoLayout.addComponent(todoCard);				
			}
		}
		todoLayout.requestRepaintAll();
	}
	
	public void newPostTimeline(TimeLine timeline) {
		if(timeline.getStatus() != null) {
			StatusCard statusCard = new StatusCard(timeline);
			getCurrentLayout().addComponentAsFirst(statusCard);
		}
		if(timeline.getDiscuss() != null) {
			DiscussCard discussCard = new DiscussCard(timeline); 
			getCurrentLayout().addComponentAsFirst(discussCard);
		}
		if (timeline.getTodo() != null && timeline.getTodo().getAssginedUser() != null) {
			TodoCard toDoCard = new TodoCard(timeline);
			getCurrentLayout().addComponentAsFirst(toDoCard);
		}
	}
	
	@Override
	public void detach() {
//		eventBus.unregister(this);
	}


	/**
	 * @return the allLayout
	 */
	public PagingVerticalLayout getAllLayout() {
		return allLayout;
	}

	/**
	 * @return the statusLayout
	 */
	public VerticalLayout getStatusLayout() {
		return statusLayout;
	}

	/**
	 * @return the discussLayout
	 */
	public VerticalLayout getDiscussLayout() {
		return discussLayout;
	}

	/**
	 * @return the todoLayout
	 */
	public VerticalLayout getTodoLayout() {
		return todoLayout;
	}

	/**
	 * @return the timelineListener
	 */
	public TimelineListener getTimelineListener() {
		return timelineListener;
	}

	/**
	 * @param timelineListener the timelineListener to set
	 */
	public void setTimelineListener(TimelineListener timelineListener) {
		this.timelineListener = timelineListener;
	}

	/**
	 * @return the pagingListener
	 */
	public PagingListener getPagingListener() {
		return pagingListener;
	}

	/**
	 * @param pagingListener the pagingListener to set
	 */
	public void setPagingListener(PagingListener pagingListener) {
		this.pagingListener = pagingListener;
	}

	/**
	 * @return the currentLayout
	 */
	public PagingVerticalLayout getCurrentLayout() {
		return currentLayout;
	}

	/**
	 * @param currentLayout the currentLayout to set
	 */
	public void setCurrentLayout(PagingVerticalLayout currentLayout) {
		this.currentLayout = currentLayout;
	}
}
