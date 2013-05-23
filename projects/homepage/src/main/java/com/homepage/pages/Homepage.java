package com.homepage.pages;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.wicket.Component;
import com.homepage.mail.task.MailQueueConsumer;
import com.homepage.mail.task.MailQueueProvider;
import com.homepage.model.Account;
import com.homepage.model.AccountFee;
import com.homepage.model.SimpleModel;
import com.homepage.model.User;
import com.homepage.model.UserDao;
import com.homepage.pages.register.NavomaticBorder;
import com.homepage.pages.register.RegisterPage;
import com.homepage.pages.register.RegisterValidation;
import com.homepage.payment.AccountFeeType;
import com.homepage.payment.Payment;
import com.homepage.payment.PaymentPage;
import com.homepage.shorturl.SimpleShortUrl;
import com.ibm.icu.util.Calendar;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormSubmitBehavior;
import org.apache.wicket.ajax.markup.html.AjaxFallbackLink;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow.CloseButtonCallback;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow.PageCreator;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow.WindowClosedCallback;
import org.apache.wicket.markup.html.WebComponent;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.ExternalLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.request.resource.ContextRelativeResource;
import org.apache.wicket.request.resource.PackageResourceReference;
import org.apache.wicket.util.visit.IVisit;
import org.apache.wicket.util.visit.IVisitor;
import org.apache.wicket.validation.Validatable;
import org.apache.wicket.validation.validator.EmailAddressValidator;

public class Homepage extends BasePage {

	private final static boolean needtoStart = true;

	public Homepage(PageParameters params) {
		System.out.println("Visit the homepage with params here");
		Set<String> keys = params.getNamedKeys();
		for (String key : keys) {
			System.out.println(key + ":" + params.get(key));
		}

		build();
	}

	public Homepage() {
		super();
		build();
	}

	public void build() {
		final ModalWindow modal = new ModalWindow("modal");
		Form userLoginform = new Form("userLoginform");
		AjaxButton userLoginBtn = new AjaxButton("userLoginBtn", userLoginform) {
			@Override
			public void onSubmit(AjaxRequestTarget target, Form form) {
				modal.show(target);
			}
		};

		final Label modalLabel = new Label("modalLabel");
		modalLabel.setOutputMarkupId(true);
		add(modalLabel);
		modal.setWindowClosedCallback(new WindowClosedCallback() {
			@Override
			public void onClose(AjaxRequestTarget target) {
				System.out.println("user click login on homepage.java");

			}
		});
		Button otherLogin = new Button("otherLogin") {
		};
		Form userRegisterForm = new Form("userRegisterForm");

		Button userRegisterBtn = new Button("userRegisterBtn");
		userRegisterForm.add(userRegisterBtn);
		add(userRegisterForm);
		add(modal);
		add(buildAccountRegisterComponent());
		add(buildHeadContext());
		// add(buildContactUsComponent());
	}

	private Component buildHeadContext() {
		Form headWebMarkContainer = new Form("headWebMarkContainer");

		String headContext01str = "Donec ullamcorper nulla non metus auctor"
				+ "fringilla. Vestibulum id ligula porta felis euismod semper. Praesent"
				+ "commodo cursus magna, vel scelerisque nisl consectetur. Fusce"
				+ "dapibus, tellus ac cursus commodo.Web build things";

		Label headContext01 = new Label("headContext01",
				Model.of(headContext01str));
		headWebMarkContainer.add(headContext01);

		String headContext02str = "Donec ullamcorper nulla non metus auctor"
				+ "fringilla. Vestibulum id ligula porta felis euismod semper. Praesent"
				+ "commodo cursus magna, vel scelerisque nisl consectetur. Fusce"
				+ "dapibus, tellus ac cursus commodo.Web build things";

		Label headContext02 = new Label("headContext02",
				Model.of(headContext02str));
		headWebMarkContainer.add(headContext02);

		String headContext03str = "Donec ullamcorper nulla non metus auctor"
				+ "fringilla. Vestibulum id ligula porta felis euismod semper. Praesent"
				+ "commodo cursus magna, vel scelerisque nisl consectetur. Fusce"
				+ "dapibus, tellus ac cursus commodo.Web build things";

		Label headContext03 = new Label("headContext03",
				Model.of(headContext03str));
		headWebMarkContainer.add(headContext03);

		String headContext04str = "Donec ullamcorper nulla non metus auctor"
				+ "fringilla. Vestibulum id ligula porta felis euismod semper. Praesent"
				+ "commodo cursus magna, vel scelerisque nisl consectetur. Fusce"
				+ "dapibus, tellus ac cursus commodo.Web build things";

		Label headContext04 = new Label("headContext04",
				Model.of(headContext04str));
		headWebMarkContainer.add(headContext04);

		String headContext05str = "Donec ullamcorper nulla non metus auctor"
				+ "fringilla. Vestibulum id ligula porta felis euismod semper. Praesent"
				+ "commodo cursus magna, vel scelerisque nisl consectetur. Fusce"
				+ "dapibus, tellus ac cursus commodo.Web build things";

		Label headContext05 = new Label("headContext05",
				Model.of(headContext05str));
		headWebMarkContainer.add(headContext05);

		String headContext06str = "Donec ullamcorper nulla non metus auctor"
				+ "fringilla. Vestibulum id ligula porta felis euismod semper. Praesent"
				+ "commodo cursus magna, vel scelerisque nisl consectetur. Fusce"
				+ "dapibus, tellus ac cursus commodo.Web build things";

		Label headContext06 = new Label("headContext06",
				Model.of(headContext04str));
		headWebMarkContainer.add(headContext06);

		return headWebMarkContainer;
	}

	private Component buildContactUsComponent() {

		ContactUsPanel contactUsPanel = new ContactUsPanel("contactUsPanel");
		return contactUsPanel;
	}

	private Component buildAccountRegisterComponent() {
		RegisterPage registerPanel = new RegisterPage("userRegister");
		return registerPanel;
	}

	private Component buildCooperateInfo(String makekupId) {
		WebMarkupContainer collaborationContainer = new WebMarkupContainer(
				makekupId);

		String info1 = "Enterprise Soical";
		WebMarkupContainer collaborationInfo1 = new WebMarkupContainer(
				"collaborationInfo1") {

			private static final long serialVersionUID = 1L;

		};
		Image image1 = new Image("collImage1", Model.of(new Model<String>(
				"home-meetings.png")));
		Label label1 = new Label("collIntro1", Model.of(info1));
		collaborationInfo1.add(label1);
		collaborationInfo1.add(image1);
		collaborationContainer.add(collaborationInfo1);

		String info2 = "Project Collaboration";
		WebMarkupContainer collaborationInfo2 = new WebMarkupContainer(
				"collaborationInfo2", Model.of(info2));
		Label label2 = new Label("collIntro2", Model.of(info2));
		Image image2 = new Image("collImage2", Model.of(new Model<String>(
				"home-meetings.png")));
		collaborationInfo2.add(label2);
		collaborationInfo2.add(image2);
		collaborationContainer.add(collaborationInfo2);

		String info3 = "Search";
		WebMarkupContainer collaborationInfo3 = new WebMarkupContainer(
				"collaborationInfo3", Model.of(info3));
		Label label3 = new Label("collIntro3", Model.of(info3));
		Image image3 = new Image("collImage3", Model.of(new Model<String>(
				"home-meetings.png")));
		collaborationInfo3.add(label3);
		collaborationInfo3.add(image3);
		collaborationContainer.add(collaborationInfo3);

		String info4 = "Admin Console";
		WebMarkupContainer collaborationInfo4 = new WebMarkupContainer(
				"collaborationInfo4", Model.of(info4));
		Label label4 = new Label("collIntro4", Model.of(info4));
		Image image4 = new Image("collImage4", Model.of(new Model<String>(
				"home-meetings.png")));
		collaborationInfo4.add(label4);
		collaborationInfo4.add(image4);
		collaborationContainer.add(collaborationInfo4);

		String info5 = "Private Cloudy";
		WebMarkupContainer collaborationInfo5 = new WebMarkupContainer(
				"collaborationInfo5", Model.of(info5));
		Label label5 = new Label("collIntro5", Model.of(info5));
		Image image5 = new Image("collImage5", Model.of(new Model<String>(
				"home-meetings.png")));
		collaborationInfo5.add(label5);
		collaborationInfo5.add(image5);
		collaborationContainer.add(collaborationInfo5);

		String info6 = "Share Files";
		WebMarkupContainer collaborationInfo6 = new WebMarkupContainer(
				"collaborationInfo6", Model.of(info6));
		Label label6 = new Label("collIntro6", Model.of(info6));
		Image image6 = new Image("collImage6", Model.of(new Model<String>(
				"home-meetings.png")));
		collaborationInfo6.add(label6);
		collaborationInfo6.add(image6);
		collaborationContainer.add(collaborationInfo6);

		return collaborationContainer;
	}

	private Component getAppComponets() {
		List lists = Arrays.asList(new String[]{"1", "2", "3"});
		ListView appLists = new ListView<Object>("appListViews", lists) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			protected void populateItem(ListItem item) {
				Image image = new Image("apps", new PackageResourceReference(
						Homepage.class, "star.jpg")) {

					private static final long serialVersionUID = 1L;
				};
				item.add(image);
			}
		};
		return appLists;
	}

	public static Component buildPaymentButton() {
		AjaxButton paymentButton = new AjaxButton("payment") {
			@Override
			public void onSubmit() {

			}
		};
		return null;
	}

	private static boolean needToStart = true;

	public class RegisterPage extends Panel {

		BlockingQueue<String> mailQueue = new LinkedBlockingQueue<String>();

		public RegisterPage(String id) {
			super(id);
			startMailQueueConsumer();

			final Label registerSuccessPanel = new Label("registerSuccessPanel");
			registerSuccessPanel.setOutputMarkupId(true);
			add(registerSuccessPanel);

			final FeedbackPanel errorMessagePanel = new FeedbackPanel(
					"registerSuccess");
			errorMessagePanel.setOutputMarkupId(true);
			add(errorMessagePanel);

			final Form userRegisterForm = new Form("userRegisterForm");
			add(userRegisterForm);

			final RequiredTextField accountName = new RequiredTextField(
					"accountName", Model.of(""));
			userRegisterForm.add(accountName);
			accountName.add(new RegisterValidation());

			final RequiredTextField emailAddress = new RequiredTextField(
					"emailAddress", Model.of(""));
			userRegisterForm.add(emailAddress);
			emailAddress.add(EmailAddressValidator.getInstance());

			final RequiredTextField<String> userName = new RequiredTextField<String>(
					"userName", Model.of(""));
			userRegisterForm.add(userName);
			userName.add(new RegisterValidation());

			final RequiredTextField password = new RequiredTextField(
					"userPassword", Model.of(""));
			userRegisterForm.add(password);
			password.add(new RegisterValidation());

			RequiredTextField rePassword = new RequiredTextField("rePassword",
					Model.of(""));
			userRegisterForm.add(rePassword);
			rePassword.add(new RegisterValidation());

			final RequiredTextField<String> accountType = new RequiredTextField<String>(
					"accountType", Model.of(""));
			userRegisterForm.add(accountType);
			accountType.add(new RegisterValidation());

			final RequiredTextField<String> accountAddress = new RequiredTextField<String>(
					"accountAddress", Model.of(""));
			userRegisterForm.add(accountAddress);
			accountAddress.add(new RegisterValidation());

			final RequiredTextField<String> accountSize = new RequiredTextField<String>(
					"accountSize", Model.of(""));
			userRegisterForm.add(accountSize);
			accountSize.add(new RegisterValidation());

			final RequiredTextField<String> twitterAccount = new RequiredTextField<String>(
					"twitterAccount", Model.of(""));
			userRegisterForm.add(twitterAccount);
			twitterAccount.add(new RegisterValidation());

			final RequiredTextField<String> accountPhone = new RequiredTextField<String>(
					"accountPhone", Model.of(""));
			userRegisterForm.add(accountPhone);
			accountPhone.add(new RegisterValidation());

			AjaxButton registerLink = new AjaxButton("register",
					userRegisterForm) {
				@Override
				public void onSubmit(AjaxRequestTarget target, Form form) {
					setEnabled(false);

					Account account = new Account();
					account.setName(accountName.getInput());
					account.setAccountAddress(accountAddress.getInput());
					account.setAccountType(accountType.getInput());
					account.setAccountSize(accountSize.getInput());
					account.setTwitterAccount(twitterAccount.getInput());
					account.setAccountPhone(accountPhone.getInput());

					User user = new User();
					user.setAccount(account);
					user.setEmailAddress(emailAddress.getInput());
					user.setPw(password.getInput());
					user.setUserName(userName.getInput());
					user.setCooperateUrl(new SimpleShortUrl().getUrl());
					UserDao userDao = new UserDao();

					java.util.Date currentDate = new java.util.Date();
					java.sql.Date formatDate = new java.sql.Date(
							currentDate.getTime());
					AccountFee accountFee = new AccountFee();
					accountFee.setStartDate(formatDate);
					accountFee.setType(AccountFeeType.Free);

					Calendar c = Calendar.getInstance();
					c.setTime(formatDate);
					c.add(Calendar.DATE, 30);

					java.sql.Date expireDate = new java.sql.Date(c.getTime()
							.getTime());

					accountFee.setExpireDate(expireDate);

					userDao.createUser(account, user, accountFee);
					System.out.println("User Click register button");
					registerSuccessPanel.setDefaultModel(Model
							.of("We have sent a email on "
									+ emailAddress.getInput()));
					target.add(registerSuccessPanel);
					
					target.add(errorMessagePanel);
					String emailAddressName = emailAddress.getInput();
					if (emailAddressName.length() > 0) {
						BlockingQueue<String> queue = new LinkedBlockingQueue<String>(
								10);
						try {
							queue.put(emailAddressName);
							MailQueueProvider.getMailQueue().put(
									emailAddressName);
							// new Thread(new MailQueueConsumer(queue)).run();
						} catch (InterruptedException e) {

							e.printStackTrace();
						}
					}

				}

				@Override
				public void onError(AjaxRequestTarget target, Form form) {
					System.out.println("error occurs");
					target.add(errorMessagePanel);
				}
			};
			
			
			userRegisterForm.add(registerLink);
			
		}

		private void startMailQueueConsumer() {
			if (needToStart) {
				needToStart = false;
				Thread mailQueueConsumer = new Thread(new MailQueueConsumer(
						mailQueue));
				mailQueueConsumer.start();
			}
		}
	}

	public class ContactUsPanel extends Panel {

		public ContactUsPanel(String id) {
			super(id);
			build();
		}

		private void build() {
			WebMarkupContainer contactContainer = new WebMarkupContainer(
					"contactUsContainer");
			add(contactContainer);

			final Label submitMessageSuccessPanel = new Label(
					"submitMessageSuccessPanel");
			contactContainer.add(submitMessageSuccessPanel);

			Form contactForm = new Form("contactForm");
			contactContainer.add(contactForm);

			TextField costumEmailAddress = new TextField("costumEmailAddress");
			contactForm.add(costumEmailAddress);

			TextField costumMessage = new TextField("costumMessage");
			contactForm.add(costumMessage);

			AjaxButton contactUsBtn = new AjaxButton("contactUsBtn",
					contactForm) {
				/**
						 * 
						 */
				private static final long serialVersionUID = 1L;

				@Override
				public void onSubmit() {
					System.out.println("Submit costoum message");
					submitMessageSuccessPanel.setDefaultModel(Model
							.of("Thank you for sending us message!"));
				}
			};
			contactForm.add(contactUsBtn);

		}

	}
}
