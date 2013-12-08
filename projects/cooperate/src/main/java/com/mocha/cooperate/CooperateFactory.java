/**
 * 
 */
package com.mocha.cooperate;

import com.coral.foundation.core.impl.MochaEventBus;
import com.coral.vaadin.controller.PageFactory;
import com.coral.vaadin.controller.Presenter;
import com.mocha.cooperate.page.ChatPresenter;
import com.mocha.cooperate.page.ColleaguePresenter;
import com.mocha.cooperate.page.FilePresenter;
import com.mocha.cooperate.page.ForumEditorPresenter;
import com.mocha.cooperate.page.ForumPresenter;
import com.mocha.cooperate.page.ForumTopicsPresenter;
import com.mocha.cooperate.page.HomePresenter;
import com.mocha.cooperate.page.NotificationPresenter;
import com.mocha.cooperate.page.SearchPresenter;
import com.mocha.cooperate.page.SettingPresenter;
import com.mocha.cooperate.page.ToDoEditorPresenter;
import com.mocha.cooperate.page.TodoPresenter;
import com.mocha.cooperate.page.UserTimeLinePresenter;
import com.mocha.cooperate.page.admin.CompanyFilePresenter;
import com.mocha.cooperate.page.admin.UserPermissionPresenter;
import com.mocha.cooperate.page.cust.CustomerHomePresenter;
import com.mocha.cooperate.page.hrapp.HumanResourcePresenter;
import com.mocha.cooperate.page.index.CustomerIndexPresenter;
import com.mocha.cooperate.page.index.IndexPresenter;

/**
 * @author Administrator
 *
 */
public class CooperateFactory implements PageFactory {
	
	public Presenter getPresenter(String entityName, MochaEventBus eventBus) {
		if(PresenterProperty.INDEX.equals(entityName)) {
			return new IndexPresenter(eventBus);
		}
		if(PresenterProperty.HOME.equals(entityName)) {
			return new HomePresenter(eventBus);
		}
		if(PresenterProperty.NOTIFICATION.equals(entityName)) {
			return new NotificationPresenter(eventBus);
		}
		if(PresenterProperty.FILE.equals(entityName)) {
			return new FilePresenter(eventBus);
		}
		if(PresenterProperty.COLLEAGUE.equals(entityName)) {
			return new ColleaguePresenter(eventBus);
		}
		if(PresenterProperty.FORUM.equals(entityName)) {
			return new ForumPresenter(eventBus);
		}
		if(PresenterProperty.FORUM_TOPICS.equals(entityName)) {
			return new ForumTopicsPresenter(eventBus);
		}
		if(PresenterProperty.FORUM_EDIT.equals(entityName)) {
			return new ForumEditorPresenter(eventBus);
		}
		if(PresenterProperty.TODO.equals(entityName)) {
			return new TodoPresenter(eventBus);
		}
		if(PresenterProperty.TODO_EDIT.equals(entityName)) {
			return new ToDoEditorPresenter(eventBus);
		}
		if(PresenterProperty.SETTING.equals(entityName)) {
			return new SettingPresenter(eventBus);
		}
		if(PresenterProperty.TIMELINE.equals(entityName)) {
			return new UserTimeLinePresenter(eventBus);
		}
		if(PresenterProperty.USER_PERMISSION.equals(entityName)) {
			return new UserPermissionPresenter(eventBus);
		}
		if(PresenterProperty.COMPANY_FILE.equals(entityName)) {
			return new CompanyFilePresenter(eventBus);
		}
		if(PresenterProperty.CHAT.equals(entityName)) {
			return new ChatPresenter(eventBus);
		}
		if(PresenterProperty.SEARCH.equals(entityName)) {
			return new SearchPresenter(eventBus);
		}
		
		/* customer page*/
		if(PresenterProperty.CUSTOMER_INDEX.equals(entityName)) {
			return new CustomerIndexPresenter(eventBus);
		}
		if(PresenterProperty.CUSTOMER_HOME.equals(entityName)) {
			return new CustomerHomePresenter(eventBus);
		}
		if(PresenterProperty.HR.equals(entityName)) {
			return new HumanResourcePresenter(eventBus);
		}
		return null;
	}
}
