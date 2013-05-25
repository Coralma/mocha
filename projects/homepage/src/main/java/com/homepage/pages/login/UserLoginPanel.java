package com.homepage.pages.login;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;

import com.homepage.model.User;

public abstract class UserLoginPanel extends Panel {

	private static User loginUser = new User();

	public UserLoginPanel(String id) {
		super(id);
		build();
	}

	private void build() {
		final FeedbackPanel feedbackPanel = new FeedbackPanel("feedbackpanel");
		feedbackPanel.setOutputMarkupId(true);
		add(feedbackPanel);

		final Form userLoginForm = new Form("userLoginForm");
		add(userLoginForm);
		final RequiredTextField userEmail = new RequiredTextField("userEmail",
				Model.of(""));
		userLoginForm.add(userEmail);
		final PasswordTextField password = new PasswordTextField(
				"userPassword", Model.of("password"));
		userLoginForm.add(password);

		AjaxButton loginBtn = new AjaxButton("loginBtn", userLoginForm) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void onSubmit(AjaxRequestTarget target, Form userLoginForm) {
				System.out.println("user input email address: "
						+ userEmail.getInput());
				getLoginUser().setEmailAddress(userEmail.getInput());
				getLoginUser().setPw(password.getInput());
				onLogin(target);
			}

			@Override
			public void onError(AjaxRequestTarget target, Form form) {
				feedbackPanel.setDefaultModel(Model
						.of("Invalide username and password"));
				target.add(feedbackPanel);
			}
		};

		userLoginForm.add(loginBtn);
		AjaxLink cancelBtn = new AjaxLink("cancelBtn") {
			@Override
			public void onClick(AjaxRequestTarget target) {
				onCancel(target);
			}

		};
		// userLoginForm.add(cancelBtn);

	}

	public abstract void onLogin(AjaxRequestTarget target);

	public abstract void onCancel(AjaxRequestTarget target);

	public abstract User getInputUser();

	public static User getLoginUser() {
		return loginUser;
	}

	public static void setLoginUser(User loginUser) {
		UserLoginPanel.loginUser = loginUser;
	}

}
