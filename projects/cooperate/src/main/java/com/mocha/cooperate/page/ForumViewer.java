/**
 * 
 */
package com.mocha.cooperate.page;

import java.util.List;

import com.coral.vaadin.widget.Viewer;
import com.coral.vaadin.widget.view.CommonViewer;
import com.google.common.collect.Lists;
import com.mocha.cooperate.SystemProperty;
import com.mocha.cooperate.model.Discuss;
import com.mocha.cooperate.page.ForumPresenter.ForumListener;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.BaseTheme;

/**
 * @author Coral
 * 
 */
public class ForumViewer extends CommonViewer implements Viewer,ClickListener {

	private List<Discuss> topNotifications;
	private List<Discuss> topDiscusses;
	private List<Discuss> topQuestions;
	private ForumListener forumListener;
	private VerticalLayout forumPanel = new VerticalLayout();
	
	/**
	 * @param forumListener the forumListener to set
	 */
	public void setForumListener(ForumListener forumListener) {
		this.forumListener = forumListener;
	}
	
	public ForumViewer() {
	}

	public void attach() {
		super.attach();
		this.addComponent(forumPanel);
		this.addStyleName("forum-panel");
		buildCategoryPanel();
	}
	
	public void buildCategoryPanel() {
		VerticalLayout forumLayout = new VerticalLayout();
		forumLayout.setWidth(SystemProperty.content_page_width);

		// notify
		ForumCategoryLayout notifyLayout = new ForumCategoryLayout(
				message.getString("cooperate.forum.Notification"),
				message.getString("cooperate.forum.notification.desc"),
				topNotifications, SystemProperty.NOTIFY_CATEGORY);
		// question
		ForumCategoryLayout questionLayout = new ForumCategoryLayout(
				message.getString("cooperate.forum.Question"),
				message.getString("cooperate.forum.question.desc"),
				topQuestions, SystemProperty.QUESTION_CATEGORY);
		questionLayout.setReversal(true);
		// discuss
		ForumCategoryLayout discussLayout = new ForumCategoryLayout(
				message.getString("cooperate.forum.Discussion"),
				message.getString("cooperate.forum.discussion.desc"),
				topDiscusses, SystemProperty.DISCUSS_CATEGORY);

		forumLayout.addComponent(notifyLayout);
		forumLayout.addComponent(questionLayout);
		forumLayout.addComponent(discussLayout);

		HorizontalLayout postInfoLayout = new HorizontalLayout();
		VerticalLayout lastPostLayout = new VerticalLayout();
		VerticalLayout popularPostLayout = new VerticalLayout();
		postInfoLayout.addComponent(lastPostLayout);
		postInfoLayout.addComponent(popularPostLayout);
		forumLayout.addComponent(postInfoLayout);

		forumPanel.addComponent(forumLayout);
	}

	public HorizontalLayout getForumCard(String icon, String title,
			String description) {
		HorizontalLayout forumCardLayout = new HorizontalLayout();
		return forumCardLayout;
	}

//	public void changetoCategoryPanel(List<Discuss> discuss) {
//		this.addStyleName("forum-panel");
//		forumPanel.removeAllComponents();
//		buildCategoryPanel();
//	}

	@Override
	public String getViewerTitle() {
		return "cooperate.forum.title";
	}
	
	@Override
	public void buttonClick(ClickEvent event) {
		Object data = event.getButton().getData();
		if(data instanceof ForumCategoryLayout) {
			forumListener.categoryClickListener(((ForumCategoryLayout) data).getCategoryName());
		} else if(data instanceof Discuss) {
			forumListener.topicClickListener((Discuss)data);
		}
	}

	public class ForumCategoryLayout extends HorizontalLayout {

		private List<Discuss> topics = Lists.newArrayList();
		private String title;
		private String description;
		private boolean reversal = false;
		private String categoryName;

		public ForumCategoryLayout(String title, String description, List<Discuss> topics, String categoryName) {
			this.setMargin(true);
			this.setWidth("700px");
			this.setHeight("160px");
			this.title= title;
			this.description = description;
		    this.topics = topics;
		    this.categoryName = categoryName;
		}

		public void attach() {
			HorizontalLayout contentLayout = new HorizontalLayout();
			contentLayout.setWidth("610px");
			VerticalLayout notifyTitleLayout = createCategoryTitleLayout(reversal);
			VerticalLayout topicLayout = createTopicLayout();
			if(reversal) {
				contentLayout.addComponent(topicLayout);
				contentLayout.addComponent(notifyTitleLayout);
				contentLayout.setComponentAlignment(notifyTitleLayout, Alignment.MIDDLE_RIGHT);
				this.addComponent(contentLayout);
				this.addStyleName("category-card-reversal");
			} else {
				contentLayout.addComponent(notifyTitleLayout);
				contentLayout.setComponentAlignment(notifyTitleLayout, Alignment.MIDDLE_LEFT);
				contentLayout.addComponent(topicLayout);
				this.addComponent(contentLayout);
				this.addStyleName("category-card");
			}
		}
		
		public VerticalLayout createCategoryTitleLayout(boolean reversal) {
			VerticalLayout notifyTitleLayout = new VerticalLayout();
			notifyTitleLayout.setWidth("250px");
			Button categoryTitleButton = new Button(title);
			// add category title button listener.
			categoryTitleButton.addListener(ForumViewer.this);
			categoryTitleButton.setData(this);
			categoryTitleButton.addStyleName(BaseTheme.BUTTON_LINK);
			categoryTitleButton.addStyleName("card-title");
			notifyTitleLayout.addComponent(categoryTitleButton);
			if(reversal) {
				notifyTitleLayout.setComponentAlignment(categoryTitleButton, Alignment.MIDDLE_RIGHT);
			}
			Label notifyDescLabel = new Label(description);
			notifyDescLabel.setWidth("250px");
			notifyDescLabel.addStyleName("card-desc");
			notifyTitleLayout.addComponent(notifyDescLabel);
			return notifyTitleLayout;
		}
		
		public VerticalLayout createTopicLayout() {
			VerticalLayout topicLayout = new VerticalLayout();
			topicLayout.addStyleName("latest-post-panel");
			topicLayout.setSpacing(true);
			topicLayout.setWidth("360px");

			HorizontalLayout headLayout = new HorizontalLayout();
			Label head = new Label("Last Posts");
			headLayout.addStyleName("p-head");
			headLayout.addComponent(head);
			topicLayout.addComponent(headLayout);

			if(topics != null) {
				for (Discuss topic : topics) {
					String titleWidth = "350px";
					HorizontalLayout titleLayout = new HorizontalLayout();
					titleLayout.addStyleName("latest-topic-title");
					titleLayout.setWidth(titleWidth);
					Button title = new Button(topic.getTitle());
					title.setData(topic);
					title.addStyleName(BaseTheme.BUTTON_LINK);
					title.setWidth(titleWidth);
					// add click listener to latest topic.
					title.addListener(ForumViewer.this);
					titleLayout.addComponent(title);
					topicLayout.addComponent(titleLayout);
				}
			}
			return topicLayout;
		}

		/**
		 * @return the reversal
		 */
		public boolean isReversal() {
			return reversal;
		}

		/**
		 * @param reversal the reversal to set
		 */
		public void setReversal(boolean reversal) {
			this.reversal = reversal;
		}

		/**
		 * @return the categoryName
		 */
		public String getCategoryName() {
			return categoryName;
		}

		/**
		 * @param categoryName the categoryName to set
		 */
		public void setCategoryName(String categoryName) {
			this.categoryName = categoryName;
		}
	}

	/**
	 * @return the topNotifications
	 */
	public List<Discuss> getTopNotifications() {
		return topNotifications;
	}

	/**
	 * @param topNotifications the topNotifications to set
	 */
	public void setTopNotifications(List<Discuss> topNotifications) {
		this.topNotifications = topNotifications;
	}

	/**
	 * @return the topDiscusses
	 */
	public List<Discuss> getTopDiscusses() {
		return topDiscusses;
	}

	/**
	 * @param topDiscusses the topDiscusses to set
	 */
	public void setTopDiscusses(List<Discuss> topDiscusses) {
		this.topDiscusses = topDiscusses;
	}

	/**
	 * @return the topQuestions
	 */
	public List<Discuss> getTopQuestions() {
		return topQuestions;
	}

	/**
	 * @param topQuestions the topQuestions to set
	 */
	public void setTopQuestions(List<Discuss> topQuestions) {
		this.topQuestions = topQuestions;
	}
}
