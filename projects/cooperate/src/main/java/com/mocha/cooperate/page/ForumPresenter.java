/**
 * 
 */
package com.mocha.cooperate.page;

import java.util.HashMap;
import java.util.Map;

import com.coral.foundation.core.impl.MochaEventBus;
import com.coral.vaadin.controller.ContentChangeEvent;
import com.coral.vaadin.controller.Presenter;
import com.coral.vaadin.widget.view.CommonPresenter;
import com.mocha.cooperate.PresenterProperty;
import com.mocha.cooperate.SystemProperty;
import com.mocha.cooperate.model.Discuss;
import com.mocha.cooperate.service.ForumService;

/**
 * @author Coral
 *
 */
public class ForumPresenter extends CommonPresenter implements Presenter {

	private ForumService forumService = new ForumService();
	
	public ForumPresenter(MochaEventBus eventBus) {
		this.eventBus = eventBus;
		ForumViewer forumViewer = new ForumViewer();
		forumViewer.setTopDiscusses(forumService.queryLastThreeDiscuss());
		forumViewer.setTopNotifications(forumService.queryLastThreeNotification());
		forumViewer.setTopQuestions(forumService.queryLastThreeQuestion());
		this.viewer = forumViewer;
	}

	@Override
	public void bind() {
		final ForumViewer forumViewer = (ForumViewer)viewer;
		forumViewer.setForumListener(new ForumListener() {
			@Override
			public void categoryClickListener(String categoryName) {
				ContentChangeEvent changeEvent = new ContentChangeEvent();
				changeEvent.setPresenterName(PresenterProperty.FORUM_TOPICS);
				Map<Object, Object> context = new HashMap<Object, Object>();
				context.put(SystemProperty.FORUM_CATEGORY, categoryName);
				eventBus.setContext(context);
				eventBus.post(changeEvent);
			}
			
			@Override
			public void topicClickListener(Discuss clickedDiscuss) {
				ContentChangeEvent changeEvent = new ContentChangeEvent();
				changeEvent.setPresenterName(PresenterProperty.FORUM_TOPICS);
				Map<Object, Object> context = new HashMap<Object, Object>();
				context.put(SystemProperty.FORUM_CATEGORY, clickedDiscuss.getStatus());
				context.put(SystemProperty.SELECTED_TOPIC, clickedDiscuss);
				eventBus.setContext(context);
				eventBus.post(changeEvent);
			}
			
			@Override
			public void newTopicListener() {
				ContentChangeEvent changeEvent = new ContentChangeEvent();
				changeEvent.setPresenterName(PresenterProperty.FORUM_EDIT);
				eventBus.post(changeEvent);
			}
		});
	}

	@Override
	public String getPresenterName() {
		return PresenterProperty.FORUM;
	}

	public interface ForumListener {
		public void categoryClickListener(String categoryName);
		public void topicClickListener(Discuss clickedDiscuss);
		public void newTopicListener();
	}
}
