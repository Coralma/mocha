package com.mocha.cooperate;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.NativeButton;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.themes.Reindeer;

public class SouceApplictionCalbackWindow extends Window {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected String pageWidth = "980px";
	private String referrer;
	
	
	
	public SouceApplictionCalbackWindow(String referrer){
		this.referrer=referrer;
		this.addStyleName("souceAppCallbackWindow");
		this.addStyleName(Reindeer.BUTTON_LINK);
	}
	
	@Override
	public void attach() {
//		System.out.println("refer url is: "+referrer);
//		System.out.println("Start to read the window name: ");
//		List<String> wNames = new ArrayList<String>();
//		for(Window w:getWindows()){
//			wNames.add(w.getName());
//			System.out.println("window name is: "+w.getName());
//		}
//		getApplication().getMainWindow().removeAllComponents();
		VerticalLayout userAuthLayout=new VerticalLayout();
		userAuthLayout.addStyleName("sourceAppLayout");
		Label comfireLable=new Label("Please Check the User Information");
		comfireLable.addStyleName("comfireLable");
		userAuthLayout.addComponent(comfireLable);
//		userAuthLayout.setComponentAlignment(comfireLable, Alignment.MIDDLE_RIGHT);
		
		HorizontalLayout g1=new HorizontalLayout();
		g1.addStyleName("sourceAPPAuthInfoLayout");
		userAuthLayout.addComponent(g1);
		userAuthLayout.setComponentAlignment(g1, Alignment.MIDDLE_LEFT);
		
		// network infor
		Label networkInfo=new Label("Authencation Network");
		networkInfo.addStyleName("inforCard");
		g1.addComponent(networkInfo);
		g1.setComponentAlignment(networkInfo,Alignment.TOP_LEFT);
		Label autheFromSiteName=new Label("eBay");
		autheFromSiteName.addStyleName("inforValue");
		g1.addComponent(autheFromSiteName);
		g1.setComponentAlignment(autheFromSiteName,Alignment.TOP_RIGHT);
		
		
		HorizontalLayout g2=new HorizontalLayout();
		g2.addStyleName("sourceAPPAuthInfoLayout");
		userAuthLayout.addComponent(g2);
		userAuthLayout.setComponentAlignment(g2, Alignment.MIDDLE_LEFT);
		// userName infor
		Label userNameInfo=new Label("User Info");
		userNameInfo.addStyleName("inforCard");
		userNameInfo.addStyleName("userNameInfo");
		g2.addComponent(userNameInfo);
		g2.setComponentAlignment(userNameInfo,Alignment.TOP_LEFT);
		Label authUserName=new Label(referrer.split("username=")[1]);
		authUserName.addStyleName("inforValue");
		g2.addComponent(authUserName);
		g2.setComponentAlignment(authUserName,Alignment.TOP_RIGHT);
		
//		// token expireDate infor
//		Label tokenInfo=new Label("Developer");
//		g.addComponent(tokenInfo);
//		g.setComponentAlignment(tokenInfo,Alignment.MIDDLE_CENTER);
//		Label tokenExpireDate=new Label(referrer.split("username=")[1]);
//		g.addComponent(authUserName);
//		g.setComponentAlignment(authUserName,Alignment.MIDDLE_CENTER);
		
		userAuthLayout.addComponent(getWebFoot());
		this.setContent(userAuthLayout);
	}
	
	public Layout getWebFoot() {
		VerticalLayout foot = new VerticalLayout();
		foot.addStyleName("mocha-app-foot");
		foot.setWidth("100%");
		foot.setHeight("80px");

		VerticalLayout footContent = new VerticalLayout();
		footContent.addStyleName("copy-right");
		footContent.setWidth(pageWidth);
		Label copyrightLabel = new Label("© Mocha business software platform. All rights reserved.");
		footContent.addComponent(copyrightLabel);
		
		foot.addComponent(footContent);
		foot.setComponentAlignment(footContent, Alignment.TOP_CENTER);
		return foot;
	}
}
