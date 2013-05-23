package com.homepage.pages;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.Model;
import org.apache.wicket.validation.validator.EmailAddressValidator;
import org.apache.wicket.validation.validator.StringValidator;

import com.homepage.model.ContactMessage;
import com.homepage.model.ContactMessageDao;

public class ContactUsPage extends BasePage {

	public ContactUsPage() {
		super();
		build();
	}

	public void build() {


		Form contactUsForm = new Form("contactUsForm");
		add(contactUsForm);
		
		final FeedbackPanel feedBackPanel = new FeedbackPanel(
				"contactUsFeedbak");
		feedBackPanel.setOutputMarkupId(true);
		contactUsForm.add(feedBackPanel);
		contactUsForm.setOutputMarkupId(true);
		
		final Label successLabel=new Label("successLabel",Model.of(""));
		successLabel.setOutputMarkupId(true);
		contactUsForm.add(successLabel);
		
		final RequiredTextField contactUsTextfield = new RequiredTextField<String>(
				"contactUsTextfield", Model.of(""));
		
		contactUsTextfield.add(StringValidator.minimumLength(4));
		contactUsForm.add(contactUsTextfield);

		final RequiredTextField emailAddress = new RequiredTextField<String>(
				"emailAddress", Model.of(""));
		emailAddress.add(EmailAddressValidator.getInstance());
		contactUsForm.add(emailAddress);

		final RequiredTextField subject = new RequiredTextField<String>(
				"subject", Model.of(""));
		subject.add(StringValidator.minimumLength(2));
		contactUsForm.add(subject);

		AjaxButton contactUsBtn = new AjaxButton("contactUsBtn", contactUsForm) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				successLabel
						.setDefaultModel(Model
								.of("Thanks for sending feed back to us. We will reply it soon."));
				target.add(successLabel);

				ContactMessage contactMessage = new ContactMessage();
				contactMessage.setEmailAddress(emailAddress.getInput()
						.toString());
				contactMessage.setSubject(subject.getInput().toString());
				contactMessage.setMessageDetail(contactUsTextfield.getInput()
						.toString());
				ContactMessageDao contactMessageDao = new ContactMessageDao(
						contactMessage);
				contactMessageDao.saveContactMessage(contactMessage);
			}

			@Override
			protected void onError(AjaxRequestTarget target, Form<?> form) {

				target.add(feedBackPanel);
			}
		};
		contactUsForm.add(contactUsBtn);
	}

}
