package com.homepage.pages.login;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.swing.text.SimpleAttributeSet;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Component;
import org.apache.wicket.Page;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.behavior.AbstractAjaxBehavior;
import org.apache.wicket.extensions.markup.html.tabs.AbstractTab;
import org.apache.wicket.extensions.markup.html.tabs.TabbedPanel;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.HiddenField;
import org.apache.wicket.markup.html.form.ImageButton;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.pages.RedirectPage;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.request.IRequestHandler;
import org.apache.wicket.request.Url;
import org.apache.wicket.request.http.handler.RedirectRequestHandler;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.request.resource.CssResourceReference;
import org.apache.wicket.request.resource.IResource;
import org.apache.wicket.request.resource.JavaScriptResourceReference;
import org.apache.wicket.request.resource.PackageResourceReference;
import org.apache.wicket.request.resource.ResourceReference;
import org.apache.wicket.request.resource.UrlResourceReference;

import com.homepage.application.HomepageSystemProperty;
import com.homepage.model.Account;
import com.homepage.model.AccountFee;
import com.homepage.model.AccountFeeDao;
import com.homepage.model.Order;
import com.homepage.model.OrderDao;
import com.homepage.model.User;
import com.homepage.pages.BasePage;
import com.homepage.payment.AccountFeeType;
import com.homepage.payment.CheckOut;
import com.homepage.security.SecuritySession;

import de.agilecoders.wicket.markup.html.bootstrap.tabs.BootstrapTabbedPanel;

public class AccountProfilePage extends BasePage {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	static User user;
	static Account account;

	public AccountProfilePage() {
		SecuritySession session = (SecuritySession) SecuritySession.get();
		// user change to another account

		if (user == null || account == null || !session.isSignedIn()
				|| !user.equals(session.getUser())) {
			setUser(session.getAuthenciateUserByUserEmail(session.getUser()
					.getEmailAddress()));
			setAccount(session.getAccount());
		}
		build();
	}

	public void build() {

		add(getAccountProfilePanel());

		add(getAccountAdminPanel());

		add(getAccountFeePanel());

//		List<AbstractTab> tabs = new ArrayList();
//		tabs.add(new AbstractTab(new Model("Company Admin Profile")) {
//			/**
//			 * 
//			 */
//			private static final long serialVersionUID = 1L;
//
//			@Override
//			public Panel getPanel(String panelId) {
//				return new PersonalProfilePanel(panelId);
//			}
//		});
//		tabs.add(new AbstractTab(new Model("Permission And Right")) {
//			/**
//			 * 
//			 */
//			private static final long serialVersionUID = 1L;
//
//			@Override
//			public Panel getPanel(String panelId) {
//				return new PermissionAndRightPanel(panelId);
//			}
//		});
//		tabs.add(new AbstractTab(new Model("Application")) {
//			/**
//			 * 
//			 */
//			private static final long serialVersionUID = 1L;
//
//			@Override
//			public Panel getPanel(String panelId) {
//				return new AppPanel(panelId);
//			}
//		});
//
//		tabs.add(new AbstractTab(new Model("Account Fee")) {
//			/**
//			 * 
//			 */
//			private static final long serialVersionUID = 1L;
//
//			@Override
//			public Panel getPanel(String panelId) {
//				return new AccountFeePage(panelId);
//			}
//		});
//
//		BootstrapTabbedPanel simpleTabs = new BootstrapTabbedPanel<AbstractTab>(
//				"userProfileTabs", tabs);
//		add(simpleTabs);
	}

	private Component getAccountFeePanel() {
		WebMarkupContainer accountFeePanel = new WebMarkupContainer(
				"accountFeePanel");

		final Form accountFeeForm = new Form("accountFeeForm");
		accountFeePanel.add(accountFeeForm);
		boolean isPaidAccountFlag = getUser().isPaidAccountFlag();
		Label checkPaidAccount = new Label("checkPaidAccount");

		AccountFee accountFee = new AccountFee();
		AccountFeeDao accountFeeDao = new AccountFeeDao();
		accountFee = accountFeeDao.findLatestAccountFee(getUser());
		checkPaidAccount.setDefaultModel(Model.of("Account Info: "
				+ accountFee.getType().toString() + " will be expired in "
				+ accountFee.getExpireDate().toString()));

		final Form accountFeeDetails = new Form("accountFeeDetails");
		AjaxButton freeLink = new AjaxButton("freeLink", Model.of("Free"),
				accountFeeDetails) {

			private static final long serialVersionUID = 1L;

			@Override
			public void onSubmit(AjaxRequestTarget target, Form form) {
				System.out.println("click promoteButton?");
				form.setResponsePage(CheckOut.class);
			}
		};

		AjaxButton proLink = new AjaxButton("proLink", Model.of("Pro"),
				accountFeeDetails) {

			private static final long serialVersionUID = 1L;

			@Override
			public void onSubmit(AjaxRequestTarget target, Form form) {
				System.out.println("click promoteButton?");

				form.setResponsePage(CheckOut.class);

			}
		};

		AjaxButton advancedLink = new AjaxButton("advancedLink",
				Model.of("Advanced"), accountFeeDetails) {

			private static final long serialVersionUID = 2742429779079782353L;

			@Override
			public void onSubmit(AjaxRequestTarget target, Form form) {
				System.out.println("click promoteButton?");
				form.setResponsePage(CheckOut.class);
			}
		};
		accountFeeDetails.add(freeLink);
		accountFeeDetails.add(proLink);
		accountFeeDetails.add(advancedLink);
		accountFeeDetails.setOutputMarkupId(true);

		accountFeePanel.add(accountFeeDetails);
		accountFeeForm.add(checkPaidAccount);
		// accountFeeForm.add(promoteToPro);
		SimpleOrderForm simpleOrderForm = new SimpleOrderForm(
				"simpleOrderForm", getUser());
		accountFeePanel.add(simpleOrderForm);
		AjaxLink orderBtn = new AjaxLink("order") {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick(AjaxRequestTarget target) {
				System.out.println("user click demoBtn");
				if (getUser() == null) {
					System.out.println("Current user is null");

				} else {
					System.out.println("Current user" + user.getEmailAddress());
				}
				Order order = new Order();
				order.setOrderOID(UUID.randomUUID().toString());
				order.setOrderType(AccountFeeType.Pro);
				OrderDao orderDao = new OrderDao(order);
				// order.setAccount(user.getAccount());
				// order.setUser(user);
				long orderID = orderDao.createOrderWithAccountAndUser(order,
						user);
				if (orderID == -1) {
					System.out.println("Error create order!");
				}
				System.out
						.println("create new orderOID " + order.getOrderOID());
				String callUrl = "https://www.sandbox.paypal.com/cgi-bin/webscr";
				String orderOID = order.getOrderOID();
				target.appendJavaScript("makeCallBack( '" + callUrl + "','"
						+ orderOID + "' )");
			}
		};
		accountFeePanel.add(orderBtn);

		return accountFeePanel;
	}

	private Component getAccountAdminPanel() {

		WebMarkupContainer accountAdminContainer = new WebMarkupContainer(
				"accountAdminContainer");
		add(accountAdminContainer);

		Label userName = new Label("userName", Model.of(AccountProfilePage
				.getUser().getUserName()));
		accountAdminContainer.add(userName);
		Label pw = new Label("pw", getUser().getPw());
		accountAdminContainer.add(pw);
		Label lastLoginDate;
		if (getUser().getLastLoginDate() != null) {
			lastLoginDate = new Label("lastLoginDate", Model.of(getUser()
					.getLastLoginDate().toGMTString()));
		} else {
			lastLoginDate = new Label("lastLoginDate", Model.of("N/A"));
		}
		accountAdminContainer.add(lastLoginDate);

		Label cooperateUrl = new Label("cooperateUrl", Model.of(getUser()
				.getCooperateUrl()));
		accountAdminContainer.add(cooperateUrl);

		return accountAdminContainer;
	}

	/*
	 * <div wicket:id="accountName"></div> <div wcket:id="accountType"></div>
	 * <div wcket:id="accountSize"></div> <div wcket:id="accountTwitter"></div>
	 */
	private Component getAccountProfilePanel() {
		WebMarkupContainer accountProfileContainer = new WebMarkupContainer(
				"accountProfilePanel");
		Image accountDefaultLogo = new Image("accountProfileImage",
				Model.of("defaultBrandLogo.png"));

		accountProfileContainer.add(accountDefaultLogo);

		Label accoutName = new Label("accountName", Model.of(getAccount()
				.getName()));
		accountProfileContainer.add(accoutName);
		Label accountType = new Label("accountType", Model.of(getAccount()
				.getAccountType()));
		accountProfileContainer.add(accountType);
		Label accountSize = new Label("accountSize", Model.of(getAccount()
				.getAccountSize()));
		accountProfileContainer.add(accountSize);

		Label accountAddress = new Label("accountAddress",
				Model.of(getAccount().getAccountAddress()));
		accountProfileContainer.add(accountAddress);

		Label accountPhone = new Label("accountPhone", Model.of(getAccount()
				.getAccountPhone()));
		accountProfileContainer.add(accountPhone);

		Label accountTwitter = new Label("accountTwitter", Model.of(account
				.getTwitterAccount()));
		accountProfileContainer.add(accountTwitter);

		// accountProfileContainer.add(accountPanelPublicInfo);
		return accountProfileContainer;
	}

	private void buildPersonalProfile() {
		WebMarkupContainer personaProfilePanel = new WebMarkupContainer(
				"userProfile");
		add(personaProfilePanel);
		Label userName = new Label("userName");
		userName.setDefaultModel(Model.of(getUser().getUserName()));
		personaProfilePanel.add(userName);
	}

	public void renderHead(IHeaderResponse response) {
		// response.render(JavaScriptHeaderItem
		// .forReference(new JavaScriptResourceReference(
		// AccountProfilePage.class, "js/bootstrap-tab.js")));
		//
	}

	public static User getUser() {
		return user;
	}

	public static void setUser(User user) {
		AccountProfilePage.user = user;
	}

	public static Account getAccount() {
		return account;
	}

	public static void setAccount(Account account) {
		AccountProfilePage.account = account;
	}

	private class PersonalProfilePanel extends Panel {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public PersonalProfilePanel(String id) {
			super(id);
			build();
		}

		private void build() {

			Label userName = new Label("userName", Model.of(AccountProfilePage
					.getUser().getUserName()));
			add(userName);
			Label pw = new Label("pw", getUser().getPw());
			add(pw);
			Label lastLoginDate;
			if (getUser().getLastLoginDate() != null) {
				lastLoginDate = new Label("lastLoginDate", Model.of(getUser()
						.getLastLoginDate().toGMTString()));
			} else {
				lastLoginDate = new Label("lastLoginDate", Model.of("N/A"));
			}
			add(lastLoginDate);

			Label cooperateUrl = new Label("cooperateUrl", Model.of(getUser()
					.getCooperateUrl()));
			add(cooperateUrl);
		}
	}

	private class PermissionAndRightPanel extends Panel {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public PermissionAndRightPanel(String id) {
			super(id);
		}

	}

	private class AppPanel extends Panel {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public AppPanel(String id) {
			super(id);
			build();
		}

		private void build() {
			final Form accountFeeForm = new Form("appPanel");
			add(accountFeeForm);
		}

	}

	private class AccountFeePage extends Panel {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public AccountFeePage(String id) {
			super(id);
			build();
		}

		private void build() {
			final Form accountFeeForm = new Form("accountFeeForm");
			add(accountFeeForm);
			boolean isPaidAccountFlag = getUser().isPaidAccountFlag();
			Label checkPaidAccount = new Label("checkPaidAccount");

			AccountFee accountFee = new AccountFee();
			AccountFeeDao accountFeeDao = new AccountFeeDao();
			accountFee = accountFeeDao.findLatestAccountFee(getUser());
			checkPaidAccount.setDefaultModel(Model.of("Account Info: "
					+ accountFee.getType().toString() + " will be expired in "
					+ accountFee.getExpireDate().toString()));

			final Form accountFeeDetails = new Form("accountFeeDetails");
			AjaxButton freeLink = new AjaxButton("freeLink", Model.of("Free"),
					accountFeeDetails) {

				private static final long serialVersionUID = 1L;

				@Override
				public void onSubmit(AjaxRequestTarget target, Form form) {
					System.out.println("click promoteButton?");
					form.setResponsePage(CheckOut.class);
				}
			};

			AjaxButton proLink = new AjaxButton("proLink", Model.of("Pro"),
					accountFeeDetails) {

				private static final long serialVersionUID = 1L;

				@Override
				public void onSubmit(AjaxRequestTarget target, Form form) {
					System.out.println("click promoteButton?");

					form.setResponsePage(CheckOut.class);

				}
			};

			AjaxButton advancedLink = new AjaxButton("advancedLink",
					Model.of("Advanced"), accountFeeDetails) {

				private static final long serialVersionUID = 2742429779079782353L;

				@Override
				public void onSubmit(AjaxRequestTarget target, Form form) {
					System.out.println("click promoteButton?");
					form.setResponsePage(CheckOut.class);
				}
			};
			accountFeeDetails.add(freeLink);
			accountFeeDetails.add(proLink);
			accountFeeDetails.add(advancedLink);
			accountFeeDetails.setOutputMarkupId(true);

			add(accountFeeDetails);
			accountFeeForm.add(checkPaidAccount);
			// accountFeeForm.add(promoteToPro);
			SimpleOrderForm simpleOrderForm = new SimpleOrderForm(
					"simpleOrderForm", getUser());
			add(simpleOrderForm);
			AjaxLink orderBtn = new AjaxLink("order") {
				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				@Override
				public void onClick(AjaxRequestTarget target) {
					System.out.println("user click demoBtn");
					if (getUser() == null) {
						System.out.println("Current user is null");

					} else {
						System.out.println("Current user"
								+ user.getEmailAddress());
					}
					Order order = new Order();
					order.setOrderOID(UUID.randomUUID().toString());
					order.setOrderType(AccountFeeType.Pro);
					OrderDao orderDao = new OrderDao(order);
					// order.setAccount(user.getAccount());
					// order.setUser(user);
					long orderID = orderDao.createOrderWithAccountAndUser(
							order, user);
					if (orderID == -1) {
						System.out.println("Error create order!");
					}
					System.out.println("create new orderOID "
							+ order.getOrderOID());
					String callUrl = "https://www.sandbox.paypal.com/cgi-bin/webscr";
					String orderOID = order.getOrderOID();
					target.appendJavaScript("makeCallBack( '" + callUrl + "','"
							+ orderOID + "' )");
				}
			};
			add(orderBtn);
		}

	}

	private class TabModel<String> implements IModel<String> {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		private String tabName;

		public TabModel(String tabName) {
			this.tabName = tabName;
		}

		@Override
		public void detach() {
			// TODO Auto-generated method stub
		}

		@Override
		public String getObject() {
			return this.tabName;
		}

		@Override
		public void setObject(String object) {
			// TODO Auto-generated method stub
		}

	}
}

class SimpleOrderForm<T> extends Form<T> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private User user = new User();

	public SimpleOrderForm(String id, User user) {
		super(id);
		this.user = user;
		build();
	}

	private void build() {

		HiddenField<String> orderId = new HiddenField<String>("orderId",
				Model.of("orderId")) {
			/**
					 * 
					 */
			private static final long serialVersionUID = 1L;

			@Override
			protected void onComponentTag(final ComponentTag tag) {
				super.onComponentTag(tag);
				tag.put("value", UUID.randomUUID().toString());
				tag.put("name", "custom");
			}
		};
		// add(orderId);
		HiddenField<String> notifyUrl = new HiddenField<String>("notify_url",
				Model.of("notify_url")) {
			/**
					 * 
					 */
			private static final long serialVersionUID = 1L;

			@Override
			protected void onComponentTag(final ComponentTag tag) {
				super.onComponentTag(tag);
				tag.put("value", HomepageSystemProperty.getPaypal_ipn_url());
				// "http://vancezhao.fwd.wf/homepage/ipn/validate");
				tag.put("name", "notify_url");
			}
		};
		add(notifyUrl);
	}
}
