/**
 * 
 */
package com.mocha.cooperate.page;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.coral.foundation.core.impl.MochaEventBus;
import com.coral.vaadin.controller.ContentChangeEvent;
import com.coral.vaadin.controller.Presenter;
import com.coral.vaadin.widget.view.CommonPresenter;
import com.google.common.collect.Maps;
import com.mocha.cooperate.PresenterProperty;
import com.mocha.cooperate.SystemProperty;
import com.mocha.cooperate.model.Discuss;
import com.mocha.cooperate.service.ForumService;
import com.mocha.cooperate.widget.listener.PagingListener;

/**
 * @author Coral
 *
 */
public class ForumTopicsPresenter extends CommonPresenter implements Presenter {

	private ForumService forumService = new ForumService();
	
	public ForumTopicsPresenter(MochaEventBus eventBus) {
		this.eventBus = eventBus;
		String category = (String)eventBus.getContext().get(SystemProperty.FORUM_CATEGORY);
		Discuss selectedTopic = (Discuss)eventBus.getContext().get(SystemProperty.SELECTED_TOPIC);
		String categoryTitle = SystemProperty.getForumCategoryTitle(category);
		List<Discuss> topics = new ArrayList<Discuss>();
//		if(SystemProperty.NOTIFY_CATEGORY.equals(category)) {
//			categoryTitle = "Notification";
//		} else if(SystemProperty.QUESTION_CATEGORY.equals(category)) {
//			categoryTitle = "Question";
//		} else if(SystemProperty.DISCUSS_CATEGORY.equals(category)) {
//			categoryTitle = "Discuss";
//		}
		topics = forumService.queryTopic(category, 0);
		ForumTopicsViewer forumTopicsViewer = new ForumTopicsViewer(categoryTitle, category, topics, eventBus);
		forumTopicsViewer.setSelectedTopic(selectedTopic);
		this.viewer = forumTopicsViewer;
		
	}

	@Override
	public void bind() {
		final ForumTopicsViewer forumTopicsViewer = (ForumTopicsViewer)viewer;
		forumTopicsViewer.setListener(new ForumTopicListener() {
			
			@Override
			public void categoryHomeListener() {
				ContentChangeEvent changeEvent = new ContentChangeEvent();
				changeEvent.setPresenterName(PresenterProperty.FORUM);
				eventBus.post(changeEvent);
			}
			
			@Override
			public void newTopicListener() {
				ContentChangeEvent changeEvent = new ContentChangeEvent();
				changeEvent.setPresenterName(PresenterProperty.FORUM_EDIT);
				Map<Object,Object> context = Maps.newHashMap();
				context.put(SystemProperty.FORUM_CATEGORY, forumTopicsViewer.getCategoryType());
				eventBus.setContext(context);
				eventBus.post(changeEvent);
			}
		});
		
		forumTopicsViewer.getTopicsLayout().setListener(new PagingListener() {
			
			@Override
			public void showMore(String type, int pageNum) {
				List<Discuss> nextTopics = forumService.queryTopic(forumTopicsViewer.getCategoryType(), 0);
				forumTopicsViewer.buildTopic(nextTopics);
			}
		});
	}

	@Override
	public String getPresenterName() {
		return PresenterProperty.FORUM_TOPICS;
	}
	
	public interface ForumTopicListener {
		public void categoryHomeListener();
		public void newTopicListener();
	}

}
