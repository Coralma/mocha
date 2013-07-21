package com.mocha.soicalAPI;

import java.util.Collection;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;

import com.coral.foundation.security.model.BasicUser;
import com.coral.vaadin.view.template.sat.AppContentEvent;
import com.coral.vaadin.widget.Result;
import com.coral.vaadin.widget.Viewer;
import com.coral.vaadin.widget.Widget;
import com.coral.vaadin.widget.view.CommonViewer;
import com.google.code.linkedinapi.schema.Person;
import com.vaadin.Application;
import com.vaadin.terminal.PaintException;
import com.vaadin.terminal.PaintTarget;
import com.vaadin.terminal.Resource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Window;
import com.vaadin.ui.Window.CloseEvent;
import com.vaadin.ui.Window.CloseListener;

public class LinkedinLoginViewer extends CommonViewer implements Viewer {
	
	boolean userHasToken;
	BasicUser user;
	Person person;
	private AppAuthWindowLister listener;
	
	public LinkedinLoginViewer(boolean userHasToken,BasicUser user,Person person){
		this.user=user;
		this.userHasToken=userHasToken;
		this.person=person;
	}
	
	@Override
	public void attach(){
		AppAuthenciateWindow appAuthWin=new AppAuthenciateWindow(userHasToken,user,person);
		appAuthWin.addListener(new CloseListener() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void windowClose(CloseEvent e) {
				System.out.println("User click close button here");
				listener.closeWindow();
			}
		});
		getWindow().addWindow(appAuthWin);		
	}

	private void buildPersonInfoLayout() {
			HorizontalLayout mainLayout=new HorizontalLayout();
			addComponent(mainLayout);
			Label userFNameLabel=new Label(person.getFirstName());
			mainLayout.addComponent(userFNameLabel);
			Label userLNameLabel=new Label(person.getLastName());			
			mainLayout.addComponent(userLNameLabel);
	}

	@Override
	public String getViewerTitle() {
		return "Login With LinkedIn";
	}

	public AppAuthWindowLister getListener() {
		return listener;
	}

	public void setListener(AppAuthWindowLister listener) {
		this.listener = listener;
	}
}
