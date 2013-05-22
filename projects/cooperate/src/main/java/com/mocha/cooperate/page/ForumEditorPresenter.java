/**
 * 
 */
package com.mocha.cooperate.page;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.coral.foundation.core.impl.MochaEventBus;
import com.coral.foundation.security.model.BasicUser;
import com.coral.vaadin.controller.ContentChangeEvent;
import com.coral.vaadin.controller.Presenter;
import com.coral.vaadin.widget.view.CommonPresenter;
import com.mocha.cooperate.PresenterProperty;
import com.mocha.cooperate.SystemProperty;
import com.mocha.cooperate.model.Attachment;
import com.mocha.cooperate.model.Discuss;
import com.mocha.cooperate.page.event.ForumEditorListener;
import com.mocha.cooperate.service.TimeLineService;

/**
 * @author Coral
 *
 */
public class ForumEditorPresenter extends CommonPresenter implements Presenter {

	private TimeLineService timeLineService = new TimeLineService();
	private String topicCategory;
	
	public ForumEditorPresenter(MochaEventBus eventBus) {
		this.eventBus = eventBus;
		topicCategory = (String)eventBus.getContext().get(SystemProperty.FORUM_CATEGORY);
		if(topicCategory == null) {
			return;
		} else {
			eventBus.setContext(null);
		}
		this.viewer = new ForumEditorViewer(topicCategory);
	}
	
	@Override
	public void bind() {
		ForumEditorViewer forumEditorViewer = (ForumEditorViewer)viewer;
		forumEditorViewer.setListener(new ForumEditorListener() {
			
			@Override
			public void saveTopic(Discuss discuss, BasicUser creator,
					Set<BasicUser> notifiedUsers, List<Attachment> attachments) {
				timeLineService.saveDiscuss(discuss, creator, notifiedUsers, attachments);
				backToTopic();
			}
			
			@Override
			public void cancelAndBack() {
				backToTopic();
			}
			
			private void backToTopic() {
				ContentChangeEvent changeEvent = new ContentChangeEvent();
				changeEvent.setPresenterName(PresenterProperty.FORUM_TOPICS);
				Map<Object, Object> context = new HashMap<Object, Object>();
				context.put(SystemProperty.FORUM_CATEGORY, topicCategory);
				eventBus.setContext(context);
				eventBus.post(changeEvent);
			}
		});
	}

	@Override
	public String getPresenterName() {
		return PresenterProperty.FORUM_EDIT;
	}
	
}
