package com.mocha.soicalAPI;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import com.coral.foundation.security.basic.dao.BasicUserDao;
import com.coral.foundation.security.model.BasicUser;
import com.coral.foundation.security.model.LinkedinConnection;
import com.coral.foundation.spring.bean.SpringContextUtils;
import com.coral.vaadin.view.template.sat.panel.impl.EntityViewPanel;
import com.coral.vaadin.widget.Result;
import com.coral.vaadin.widget.Viewer;
import com.coral.vaadin.widget.Widget;
import com.coral.vaadin.widget.WidgetFactory;
import com.vaadin.Application;
import com.vaadin.terminal.PaintException;
import com.vaadin.terminal.PaintTarget;
import com.vaadin.terminal.Resource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.Button.ClickEvent;

public class IbFollowedMembersViewer extends EntityViewPanel implements Viewer {

	private static final long serialVersionUID = 1L;
	String width = "765px";
	private BasicUser user;
	private final VerticalLayout mainLayout = new VerticalLayout();
	BasicUserDao buDao = SpringContextUtils.getBean(BasicUserDao.class);
	private List<LinkedinConnection> followedConnection = new ArrayList<LinkedinConnection>();
	private Button groupbtn = new Button();
	private LinkedinConnection selectCon;

	public IbFollowedMembersViewer(List<LinkedinConnection> linkedinConnections, BasicUser basicUser) {
		this.followedConnection = linkedinConnections;
		this.user = basicUser;
		build();
	}

	public void attach() {
		this.addComponent(mainLayout);
		buildFollowedConnectionsLayout();
	}

	@Override
	public void build() {
		buildFollowedConnectionsLayout();
	}

	public void buildFollowedConnectionsLayout() {
		mainLayout.removeAllComponents();
		final LinkedInConnectionGroup group = new LinkedInConnectionGroup("Subscribers", followedConnection);
		group.setDescritption("Here are the subscribers that we followed from LinkedIn connections");
		group.getViewDetailBtn().addListener(new ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				setSelectCon(group.getSelectedConn());
				getGroupbtn().click();
			}
		});
		mainLayout.addComponent(group);
	}

	public Button getGroupbtn() {
		return groupbtn;
	}

	public void setGroupbtn(Button groupbtn) {
		this.groupbtn = groupbtn;
	}

	public LinkedinConnection getSelectCon() {
		return selectCon;
	}

	public void setSelectCon(LinkedinConnection selectCon) {
		this.selectCon = selectCon;
	}

}
