/**
 * 
 */
package com.mocha.cooperate.widget.wrap;

import java.util.Date;
import java.util.List;

import com.coral.foundation.constant.RuntimeConstant;
import com.coral.foundation.core.impl.MochaEventBus;
import com.coral.foundation.security.model.BasicUser;
import com.google.common.collect.Lists;
import com.mocha.cooperate.SystemProperty;
import com.mocha.cooperate.model.TimeLine;

public class TimeLineSheetWrap extends HomeLineSheetWrap {

	private Date date;
	
	public TimeLineSheetWrap(BasicUser user, Date date, MochaEventBus eventBus) {
		super(user, eventBus);
		this.date = date;
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
			timelines = timeLineService.loadTimeLineByUser(user, date, pageNum);
			timeLineSheet.refreshAll(timelines);
		} else if(SystemProperty.STATUS.equals(type)) {
			timelines = timeLineService.loadStatusByUser(user, pageNum);
			timeLineSheet.refreshStatus(timelines);
		} else if(SystemProperty.DISCUSS.equals(type)) {
			timelines = timeLineService.loadDiscussByUser(user, pageNum);
			timeLineSheet.refreshDiscuss(timelines);
		} else if(SystemProperty.ToDo.equals(type)) {
			timelines = timeLineService.loadTodoByUser(user, pageNum);
			timeLineSheet.refreshToDo(timelines);
		}
		if(timelines.size() < RuntimeConstant.PAGING_SIZE) {
			timeLineSheet.getCurrentLayout().setFullSize(false);
		}
		return timelines;
	}
}
