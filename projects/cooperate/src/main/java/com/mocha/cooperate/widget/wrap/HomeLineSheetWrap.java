/**
 * 
 */
package com.mocha.cooperate.widget.wrap;

import java.util.List;

import com.coral.foundation.constant.RuntimeConstant;
import com.coral.foundation.security.model.BasicUser;
import com.google.common.collect.Lists;
import com.mocha.cooperate.SystemProperty;
import com.mocha.cooperate.model.TimeLine;
import com.mocha.cooperate.page.event.TimelineListener;
import com.mocha.cooperate.service.TimeLineService;
import com.mocha.cooperate.widget.TimeLineSheet;
import com.mocha.cooperate.widget.listener.PagingListener;

public class HomeLineSheetWrap {

	protected TimeLineSheet timeLineSheet;
	protected TimeLineService timeLineService = new TimeLineService();
	protected BasicUser currentUser;
	
	public HomeLineSheetWrap(BasicUser user) {
		this.currentUser = user;
		timeLineSheet = new TimeLineSheet();
		timeLineSheet.setTimelineListener(new TimelineListener() {
			@Override
			public List<TimeLine> loadMessages(String type) {
				List<TimeLine> timelines = loadTimelines(type, currentUser);
				return timelines;
			}
		});
		timeLineSheet.setPagingListener(new PagingListener() {
			@Override
			public void showMore(String type, int pageNum) {
				queryTimelines(currentUser, type, pageNum);
			}
		});
	}
	
	protected List<TimeLine> loadTimelines(String type, BasicUser user) {
		List<TimeLine> timelines = queryTimelines(user,type,0);
		timeLineSheet.removeAllComponents(type);
		if(timelines.size() < RuntimeConstant.PAGING_SIZE) {
			timeLineSheet.getCurrentLayout().setFullSize(false);
		}
		return timelines;
	}
	
	protected List<TimeLine> queryTimelines(BasicUser user, String type, int pageNum) {
		List<TimeLine> timelines = Lists.newArrayList();
		if(SystemProperty.ALL.equals(type)) {
			timelines = timeLineService.loadTimeLine(user, pageNum);
			timeLineSheet.refreshAll(timelines);
		} else if(SystemProperty.STATUS.equals(type)) {
			timelines = timeLineService.loadStatus(user, pageNum);
			timeLineSheet.refreshStatus(timelines);
		} else if(SystemProperty.DISCUSS.equals(type)) {
			timelines = timeLineService.loadDiscuss(user, pageNum);
			timeLineSheet.refreshDiscuss(timelines);
		} else if(SystemProperty.ToDo.equals(type)) {
			timelines = timeLineService.loadTodo(user, pageNum);
			timeLineSheet.refreshToDo(timelines);
		}
		if(timelines.size() < RuntimeConstant.PAGING_SIZE) {
			timeLineSheet.getCurrentLayout().setFullSize(false);
		}
		return timelines;
	}

	public void newPostTimeline(TimeLine timeline) {
		//FIXME the new post logic should be like below, But I got a file for deleting the new posted card after it was posted.
		List<TimeLine> timelines = loadTimelines(SystemProperty.ALL, currentUser);
		timeLineSheet.refreshAll(timelines);
	}
	
	public TimeLineSheet getTimeLineSheet() {
		return timeLineSheet;
	}
}
