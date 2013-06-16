/**
 * 
 */
package com.mocha.cooperate.page;

import java.util.List;

import com.coral.foundation.constant.RuntimeConstant;
import com.coral.foundation.core.impl.MochaEventBus;
import com.coral.vaadin.widget.Viewer;
import com.coral.vaadin.widget.WidgetFactory;
import com.coral.vaadin.widget.component.ToolbarAdvance;
import com.coral.vaadin.widget.view.CommonViewer;
import com.mocha.cooperate.model.Discuss;
import com.mocha.cooperate.page.ForumTopicsPresenter.ForumTopicListener;
import com.mocha.cooperate.widget.PagingVerticalLayout;
import com.mocha.cooperate.widget.cards.DiscussCard;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

/**
 * @author Coral
 *
 */
public class ForumTopicsViewer extends CommonViewer implements Viewer, ClickListener {

	private static final long serialVersionUID = -7810998408255674059L;
	private PagingVerticalLayout topicsLayout = new PagingVerticalLayout();
	private String category;
	private String categoryType;
	private List<Discuss> topics;
	private Discuss selectedTopic;
	private ForumTopicListener listener;
	private DiscussCard selectedDiscussCard;
	private MochaEventBus eventBus;
	
	public ForumTopicsViewer(String category, String categoryType, List<Discuss> topics, MochaEventBus eventBus) {
		this.setSpacing(true);
		this.addStyleName("home-content");
		this.topics = topics;
		this.category = category;
		this.categoryType = categoryType;
		this.eventBus = eventBus;
//		super.changeTitle(category);
	}
	
	public void attach() {
		super.attach();
		ToolbarAdvance topicHeadLinks = createTopicHeadLinkPanel();
		this.addComponent(topicHeadLinks);

		// show panel
		topicsLayout.setMargin(false,false,true,false);
		topicsLayout.addStyleName("home-tab-layout");
		topicsLayout.setSpacing(true);
		topicsLayout.setWidth("100%");
		
		this.addComponent(topicsLayout);
		
		buildTopic(topics);
	}
	
	public void buildTopic(List<Discuss> topics) {
		for(Discuss topic : topics) {
			DiscussCard discussCard = new DiscussCard(topic, eventBus); 
			if(selectedTopic != null && selectedTopic.getID().equals(topic.getID())) {
				discussCard.setDisplayReply(true);
				selectedDiscussCard = discussCard;
			}
			topicsLayout.addComponent(discussCard);
		}
		if(topics.size() < RuntimeConstant.PAGING_SIZE) {
			topicsLayout.setFullSize(false);
		}
	}
	
	public ToolbarAdvance createTopicHeadLinkPanel() {
		ToolbarAdvance toolbar = new ToolbarAdvance();
		toolbar.setWidth("768px");
		Button backLink = WidgetFactory.createLink(message.getString("cooperate.forum.backForumCategory"),"back",this);
		backLink.setIcon(new ThemeResource("icons/back.png"));
		toolbar.addLeftComponent(backLink);
		toolbar.addLeftComponent(WidgetFactory.createLink(message.getString("cooperate.forum.MyPosts"),"myPosts",this));
		toolbar.addLeftComponent(WidgetFactory.createLink(message.getString("cooperate.forum.MyReplies"),"myReplies",this));
		
		toolbar.addRightComponent(WidgetFactory.createButton(message.getString("cooperate.forum.NewPost"),"newPost",this));
		return toolbar;
	}

	@Override
	public void buttonClick(ClickEvent event) {
		if("newPost".equals(event.getButton().getData())) {
			listener.newTopicListener();
		} else if("myPosts".equals(event.getButton().getData())) {
		} else {
			listener.categoryHomeListener();
		}
	}

	@Override
	public String getViewerTitle() {
		return "cooperate.forum."+category;
	}

	/**
	 * @return the listener
	 */
	public ForumTopicListener getListener() {
		return listener;
	}

	/**
	 * @param listener the listener to set
	 */
	public void setListener(ForumTopicListener listener) {
		this.listener = listener;
	}

	/**
	 * @return the topicsLayout
	 */
	public PagingVerticalLayout getTopicsLayout() {
		return topicsLayout;
	}

	/**
	 * @return the categoryType
	 */
	public String getCategoryType() {
		return categoryType;
	}

	/**
	 * @return the selectedTopic
	 */
	public Discuss getSelectedTopic() {
		return selectedTopic;
	}

	/**
	 * @param selectedTopic the selectedTopic to set
	 */
	public void setSelectedTopic(Discuss selectedTopic) {
		this.selectedTopic = selectedTopic;
	}

}
