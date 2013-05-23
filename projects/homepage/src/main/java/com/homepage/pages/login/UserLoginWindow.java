package com.homepage.pages.login;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;

import com.homepage.model.User;

public abstract class UserLoginWindow extends ModalWindow {
	protected ModalWindow userLogin;
	private static User user=new User();

	public UserLoginWindow(String id) {
		super(id);
		
		setCssClassName("defaultOutputWindows");
		
		setContent(new UserLoginPanel(this.getContentId()) {

			@Override
			public void onLogin(AjaxRequestTarget target) {
				user=getInputUser();
				System.out.println(user.getEmailAddress());
				UserLoginWindow.this.onLogin(target);
			}

			@Override
			public void onCancel(AjaxRequestTarget target) {
				UserLoginWindow.this.onCancel(target);
			}

			@Override
			public User getInputUser() {
				
				return getLoginUser();
			}
		});
		
	}

	public abstract void onLogin(AjaxRequestTarget target);

	public abstract void onCancel(AjaxRequestTarget target);

	public static User getUser() {
		return user;
	}

	public static void setUser(User user) {
		UserLoginWindow.user = user;
	}

}
