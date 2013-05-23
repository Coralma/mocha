package com.homepage.pages.register;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Date;
import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormValidatingBehavior;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.EmailTextField;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.time.Duration;
import org.apache.wicket.validation.validator.EmailAddressValidator;

import com.homepage.mail.task.MailQueueConsumer;
import com.homepage.mail.task.MailQueueProvider;
import com.homepage.model.Account;
import com.homepage.model.AccountFee;
import com.homepage.model.AccountFeeDao;
import com.homepage.model.User;
import com.homepage.model.UserDao;
import com.homepage.pages.BasePage;
import com.homepage.payment.AccountFeeType;
import com.homepage.shorturl.SimpleShortUrl;
import com.ibm.icu.util.Calendar;

public class RegisterPage extends BasePage {

	BlockingQueue<String> mailQueue = new LinkedBlockingQueue<String>();
	private static boolean needToStart = true;
	private String cooperateUrl;

	public RegisterPage() {
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

		final RequiredTextField<String> emailAddress = new RequiredTextField<String>(
				"emailAddress", Model.of(""));
		userRegisterForm.add(emailAddress);
		emailAddress.add(EmailAddressValidator.getInstance());

		final RequiredTextField<String> userName = new RequiredTextField<String>(
				"userName", Model.of(""));
		userRegisterForm.add(userName);
		userName.add(new RegisterValidation());

		final RequiredTextField<String> password = new RequiredTextField<String>(
				"userPassword", Model.of(""));
		userRegisterForm.add(password);
		password.add(new RegisterValidation());

		RequiredTextField<String> rePassword = new RequiredTextField<String>(
				"rePassword", Model.of(""));
		userRegisterForm.add(rePassword);
		rePassword.add(new RegisterValidation());

		final RequiredTextField<String> accountName = new RequiredTextField<String>(
				"accountName", Model.of(""));
		userRegisterForm.add(accountName);
		accountName.add(new RegisterValidation());

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

		AjaxButton registerLink = new AjaxButton("register", userRegisterForm) {

			@Override
			public void onSubmit(AjaxRequestTarget target, Form form) {
				setEnabled(false);

				Account account = new Account();
				account.setName(accountName.getInput());
				account.setAccountAddress(accountAddress.getInput());
				account.setAccountType(accountType.getInput());
				account.setAccountSize(accountSize.getInput());

				User user = new User();
				user.setAccount(account);
				user.setEmailAddress(emailAddress.getInput());
				user.setPw(password.getInput());
				user.setUserName(userName.getInput());
				user.setCooperateUrl(getCooperateUrl());
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
				// AccountFeeDao accountFeeDao=new AccountFeeDao();
				// accountFeeDao.createAccountFee(accountFee);
				// ArrayList<AccountFee> accountFees=new ArrayList() ;
				// accountFees.add(accountFee);
				// user.setAccountFees(accountFees);
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
						MailQueueProvider.getMailQueue().put(emailAddressName);
						// new Thread(new MailQueueConsumer(queue)).run();
					} catch (InterruptedException e) {

						e.printStackTrace();
					}
				}

				// try {
				// queue.put(emailAddress);
				// MailQueueProvider.getMailQueue().put(emailAddress);
				// // new Thread(new MailQueueConsumer(queue)).run();
				// } catch (InterruptedException e) {
				//
				// e.printStackTrace();
				// }
			}

			@Override
			public void onError(AjaxRequestTarget target, Form form) {
				System.out.println("error occurs");
				target.add(errorMessagePanel);
			}
		};

		userRegisterForm.add(registerLink);
		// AjaxFormValidatingBehavior.addToAllFormComponents(userRegisterForm,
		// "onkeyup", Duration.ONE_SECOND);
				
        add(new NavomaticBorder("navomaticBorder"));

	}
	private void startMailQueueConsumer() {
		if (needToStart) {
			needToStart = false;
			Thread mailQueueConsumer = new Thread(new MailQueueConsumer(
					mailQueue));
			mailQueueConsumer.start();
		}
	}
	public String getCooperateUrl() {
		SimpleShortUrl simeShortUrl = new SimpleShortUrl();
		return simeShortUrl.getUrl();
	}
	public void setCooperateUrl(String cooperateUrl) {
		this.cooperateUrl = cooperateUrl;
	}
	@Override
	public void build() {
		// TODO Auto-generated method stub
		
	}
}
