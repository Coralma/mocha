package com.homepage.pages;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptReferenceHeaderItem;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.request.resource.ContextRelativeResource;
import org.apache.wicket.request.resource.CssResourceReference;
import org.apache.wicket.request.resource.JavaScriptResourceReference;
import org.apache.wicket.request.resource.PackageResourceReference;

import com.homepage.model.User;
import com.homepage.model.UserDao;

import de.agilecoders.wicket.core.markup.html.bootstrap.navbar.Navbar;

public class SimpleHomePage<T> extends SimpleBasePage<T> {

	/**
	 * Construct.
	 * 
	 * @param parameters
	 *            the current page parameters.
	 */
	public SimpleHomePage(PageParameters parameters) {
		super(parameters);
		buildMainSlideComponent();
	}

	private void buildMainSlideComponent() {
		// build email address register page
		buildEmailRegisterPage();
	}

	private void buildEmailRegisterPage() {
		Form registerForm = new Form("registerForm", Model.of("registerForm"));
		registerForm.setOutputMarkupId(true);
		add(registerForm);
		final RequiredTextField<String> emailAddressTextField = new RequiredTextField<String>("registerEmail", Model.of(""));
		emailAddressTextField.setOutputMarkupId(true);
		registerForm.add(emailAddressTextField);
		final Label messageLabel = new Label("registerMessageLabel");
		messageLabel.setOutputMarkupId(true);
		registerForm.add(messageLabel);
		AjaxButton registerBtn = new AjaxButton("registerBtn", registerForm) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void onSubmit(AjaxRequestTarget target, Form<?> form) {
				messageLabel.setDefaultModel(Model.of("谢谢您！我们已经发了一封注册邮件到您的邮箱，请查收！"));
				System.out.println("User Click Register Btn");
//				UserDao userDao = new UserDao();
//				User newRegisterUser = new User();
//				newRegisterUser.setEmailAddress(emailAddressTextField.getValue());
//				newRegisterUser.setRegisterFlg(true);
//				userDao.persist(newRegisterUser);
				target.add(messageLabel);
			}
		};
		registerForm.add(registerBtn);
	}

	@Override
	public void renderHead(IHeaderResponse response) {
		super.renderHead(response);
		// response.render(JavaScriptReferenceHeaderItem.forReference(basepageJs));
		// response.render(CssHeaderItem.forReference(FixBootstrapStylesCssResourceReference.INSTANCE));
		// response.render(CssHeaderItem.forReference(basePageCss));
	}
}
