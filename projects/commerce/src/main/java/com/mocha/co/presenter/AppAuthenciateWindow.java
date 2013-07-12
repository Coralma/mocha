/**
 * 
 */
package com.mocha.co.presenter;
import org.vaadin.jouni.animator.AnimatorProxy;
import com.coral.foundation.ebay.ManageTransactions;
import com.coral.foundation.security.model.BasicUser;
import com.coral.foundation.spring.bean.SpringContextUtils;
import com.mocha.co.dao.CommerceCustomerDao;
import com.mocha.co.model.CommerceCustomer;
import com.mocha.co.model.SourceApplication;
import com.mocha.cooperate.SystemProperty;
import com.vaadin.terminal.ExternalResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.NativeButton;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.themes.Reindeer;

public class AppAuthenciateWindow extends Window implements ClickListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	VerticalLayout contentLayout = new VerticalLayout();
//	private AnimatorProxy proxy = new AnimatorProxy();
	BasicUser user;
	
	private CommerceCustomerDao ccDao=SpringContextUtils.getBean(CommerceCustomerDao.class); 
	
	public AppAuthenciateWindow(BasicUser user) {
		this.user=user;
		this.setCaption("Application Authenciation");
		this.center();
		this.addStyleName("mocha-app");
		this.setWidth("860px");
		this.setClosable(true);
		this.setResizeLazy(true);
		this.setResizable(true);
		this.setModal(true);
		this.addStyleName(Reindeer.WINDOW_LIGHT);
		this.setImmediate(true);
		this.setHeight("400px");
	}
	
	@Override
	public void attach() {
		
		final VerticalLayout layout = new VerticalLayout();
		layout.setSizeFull();
		
		GridLayout authWindowLayout = new GridLayout(2,1);
		OptionGroup optiongroup = new OptionGroup("Please choose one of Your network account");
		optiongroup.setSizeFull();
		optiongroup.addStyleName("horizontal");
		optiongroup.addItem("eBay");
		optiongroup.addItem("Amazon.UK");
		optiongroup.addItem("Amazon.US");
		optiongroup.addItem("Esty");
		optiongroup.addStyleName("appAuth-og");
		layout.addComponent(optiongroup);
		layout.setComponentAlignment(optiongroup, Alignment.MIDDLE_CENTER);
		
		NativeButton demoBtn=new NativeButton("Use Our Sanbox Account");
		demoBtn.addStyleName("mocha-button");
		demoBtn.addListener(new ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				CommerceCustomer cc=ccDao.findCCByUser(user);
				SourceApplication source=null;
				if(cc==null){
					cc=new CommerceCustomer();
					cc.setReferUser(user);
					source=new SourceApplication();
				}else{
					source=cc.getSourceApplications().get(0);
				}
				source.setSessionID(null);
				source.setAuthToken(SystemProperty.ebayToken);
				source.setName("eBay");
				cc.getSourceApplications().add(source);
				ccDao.merge(cc);
				getWindow().setVisible(false);
			}
		});
		authWindowLayout.addComponent(demoBtn);
		authWindowLayout.setComponentAlignment(demoBtn, Alignment.MIDDLE_CENTER);
		LinkButton authBtn=new LinkButton("","Authencation From Your Network");
		authBtn.addStyleName("mocha-button");
		authBtn.addListener(new ClickListener() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				requestRepaintAll();
				setPositionX(100);
				setPositionY(50);
				setWidth("90%");
				setHeight("80%");
				ManageTransactions m=new ManageTransactions();
				String ebayAuthURL=m.createRedirectURLByRuName(SystemProperty.ebayRuName);
				//get Token
				String sessionID=m.getSessionID();
				String secretID=m.getSecretID();
				CommerceCustomer cc=ccDao.findCCByUser(user);
				SourceApplication source=null;
				if(cc==null){
					cc=new CommerceCustomer();
					cc.setReferUser(user);
					source=new SourceApplication();
				}else{
					source=cc.getSourceApplications().get(0);
				}
				source.setSecretID(secretID);
				source.setSessionID(sessionID);
				cc.getSourceApplications().add(source);
				ccDao.merge(cc);
				ExternalResource er=new ExternalResource(ebayAuthURL);
				getWindow().open(new ExternalResource(ebayAuthURL));
			}
		});
		authWindowLayout.addComponent(authBtn);
		authWindowLayout.setComponentAlignment(authBtn, Alignment.MIDDLE_CENTER);
		authWindowLayout.setHeight("100%");
		authWindowLayout.setWidth("100%");
		contentLayout.setHeight("500px");
		authWindowLayout.addComponent(contentLayout);
		layout.addComponent(authWindowLayout);
		this.setContent(layout);
	}
	

	@Override
	public void buttonClick(ClickEvent event) {
		System.out.println(event.getSource());
	}
	
	public class LinkButton extends NativeButton {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private String url;

		public LinkButton(String url, String caption) {
			super(caption);
			this.url = url;

			setImmediate(true);
			addListener(new Button.ClickListener() {
				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				@Override
				public void buttonClick(ClickEvent event) {
					LinkButton.this.getWindow()
							.open(new ExternalResource(LinkButton.this.url));

				}
			});
		}
	}

}
