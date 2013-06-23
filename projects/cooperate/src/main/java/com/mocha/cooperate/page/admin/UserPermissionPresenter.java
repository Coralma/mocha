/**
 * 
 */
package com.mocha.cooperate.page.admin;

import java.util.List;

import com.coral.foundation.core.impl.MochaEventBus;
import com.coral.foundation.security.basic.dao.AccountDao;
import com.coral.foundation.security.basic.dao.BasicRoleDao;
import com.coral.foundation.security.basic.dao.BasicUserDao;
import com.coral.foundation.security.model.Account;
import com.coral.foundation.security.model.BasicUser;
import com.coral.foundation.spring.bean.SpringContextUtils;
import com.coral.foundation.utils.StrUtils;
import com.coral.vaadin.widget.view.CommonPresenter;
import com.mocha.cooperate.PresenterProperty;
import com.mocha.cooperate.page.event.UserPermissionListener;
import com.mocha.cooperate.widget.listener.PagingListener;


/**
 * @author Coral.Ma
 */
@SuppressWarnings("serial")
public class UserPermissionPresenter extends CommonPresenter {
	
	public BasicUserDao basicUserDao = SpringContextUtils.getBean(BasicUserDao.class);
	public BasicRoleDao basicRoleDao = SpringContextUtils.getBean(BasicRoleDao.class);
	public AccountDao accountDao = SpringContextUtils.getBean(AccountDao.class);
	
	private BasicUser currentUser;
	
	public UserPermissionPresenter(MochaEventBus eventBus) {
		this.eventBus = eventBus;
		this.currentUser = eventBus.getUser();
		UserPermissionViewer userOperationViewer = new UserPermissionViewer(eventBus.getUser());
		List<BasicUser> activeUsers = basicUserDao.findActiveUsers(0);
		userOperationViewer.setActiveUsers(activeUsers);
		this.viewer = userOperationViewer;
	}

	public void bind() {
		final UserPermissionViewer userPermissionViewer = (UserPermissionViewer)viewer;
		userPermissionViewer.setListener(new UserPermissionListener() {
			@Override
			public BasicUser saveUser(BasicUser user) {
//				basicUserDao.persist(user);
				if(user.getAccount() == null) {
					Account account = accountDao.findById(currentUser.getAccount().getID());
					user.setAccount(account);
				}
				user = basicUserDao.merge(user);
				return user; 
			}

			@Override
			public void refreshPanel() {
				userPermissionViewer.buildUserListPanel();
				userPermissionViewer.requestRepaintAll();
			}

			@Override
			public void searchUser(String text) {
				if(StrUtils.isEmpty(text)) {
					List<BasicUser> listUsers = basicUserDao.findActiveUsers(0);
					userPermissionViewer.setUserList(listUsers);
					userPermissionViewer.buildUserListPanel();
				} else {
					List<BasicUser> fuzzyListUsers = basicUserDao.fuzzyUserSearch(text);
					userPermissionViewer.setUserList(fuzzyListUsers);
					userPermissionViewer.buildUserListPanel();
				}
			}

			@Override
			public void showActiveUsers() {
				List<BasicUser> listUsers = basicUserDao.findActiveUsers(0);
				userPermissionViewer.setUserList(listUsers);
				userPermissionViewer.buildUserListPanel();
			}

			@Override
			public void showInActiveUsers() {
				List<BasicUser> listUsers = basicUserDao.findInActiveUsers(0);
				userPermissionViewer.setUserList(listUsers);
				userPermissionViewer.buildUserListPanel();
			}
		});
		userPermissionViewer.getUsersListPanel().setListener(new PagingListener() {
			
			@Override
			public void showMore(String type, int pageNum) {
				List<BasicUser> activeUsers = basicUserDao.findActiveUsers(pageNum);
				userPermissionViewer.getUsersListPanel().setMoreSize(activeUsers.size());
				userPermissionViewer.moreUserList(activeUsers);
				userPermissionViewer.requestRepaintAll();
			}
		});
//		final UserOperationViewer userOperationViewer = (UserOperationViewer)viewer;
//		// init data.
//		userOperationViewer.reload(loadAllBasicUsers());
//		
//		ActionButton saveBtn = userOperationViewer.getButton("save");
//		saveBtn.addListener(new ClickListener() {
//			@Override
//			public void buttonClick(ClickEvent event) {
//				BasicUser basicUser = (BasicUser) userOperationViewer.getValue();
//				if(basicUser != null) {
//					// set admin role to user;
//					BasicRole basicRole = basicRoleDao.findById(new Long(1));
//					basicUser.setBasicRole(basicRole);
//					basicUserDao.merge(basicUser);
//					userOperationViewer.reload(loadAllBasicUsers());
//					userOperationViewer.rebuildUserEditor();
//				}
//			}
//		});
//		userOperationViewer.getUserTable().addListener(new ItemClickListener() {
//			@Override
//			public void itemClick(ItemClickEvent event) {
//				userOperationViewer.setValue(event.getItemId());
//				userOperationViewer.getButton("delete").setVisible(true);
//				userOperationViewer.getButton("new").setVisible(true);
//			}
//		});
//		userOperationViewer.getButton("new").addListener(new ClickListener() {
//			@Override
//			public void buttonClick(ClickEvent event) {
//				userOperationViewer.setValue(new BasicUser());
//				userOperationViewer.rebuildUserEditor();
//			}
//		});
//		
//		userOperationViewer.getButton("delete").addListener(new ClickListener() {
//			@Override
//			public void buttonClick(ClickEvent event) {
//				BasicUser basicUser = (BasicUser) userOperationViewer.getValue();
//				basicUserDao.remove(basicUser.getID());
//				userOperationViewer.reload(loadAllBasicUsers());
//				userOperationViewer.rebuildUserEditor();
//			}
//		});
	}

	public List<BasicUser> loadAllBasicUsers() {
		List<BasicUser> basicUsers = basicUserDao.findAll();
		return basicUsers;
	}
	
	@Override
	public String getPresenterName() {
		return PresenterProperty.USER_PERMISSION;
	}

}
