package com.mocha.emailMarketing;

import java.util.List;

import org.glassfish.jersey.client.ClientResponse;
import org.vaadin.teemu.wizards.Wizard;

import com.coral.foundation.edm.mailgun.EDMSender;
import com.coral.foundation.security.basic.dao.CampaingnsDao;
import com.coral.foundation.security.model.Campaingns;
import com.coral.foundation.security.model.Contact;
import com.coral.foundation.security.model.Email;
import com.coral.foundation.spring.bean.SpringContextUtils;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.NativeButton;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;

public class ConfirmEmailStep extends AbstractCreateEmailSteps {

	private Wizard wizard;
	private Campaingns campaingns;
	private CampaingnsDao dao = SpringContextUtils.getBean(CampaingnsDao.class);
	private String compainginskey;

	public ConfirmEmailStep(Wizard wizard, String compainginskey) {
		super(wizard, compainginskey);
		this.wizard = wizard;
		this.compainginskey = compainginskey;

		addListener();
	}

	@Override
	public String getCaption() {
		return "Confirm";
	}

	@Override
	public Component getContent() {
		VerticalLayout mainLayout = new VerticalLayout();
		Label comainginsName = new Label(getCampaingns(compainginskey).getName());
		mainLayout.addComponent(comainginsName);
		Label emailContext = new Label(getCampaingns(compainginskey).getEmailContext());
		mainLayout.addComponent(emailContext);
		dao.persist(getCampaingns(compainginskey));

		NativeButton sendEmailBtn = new NativeButton("Send");
		sendEmailBtn.addStyleName("Mocha-Button");
		mainLayout.addComponent(sendEmailBtn);
		mainLayout.setComponentAlignment(sendEmailBtn, Alignment.BOTTOM_RIGHT);

		sendEmailBtn.addListener(new ClickListener() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@SuppressWarnings("unused")
			@Override
			public void buttonClick(ClickEvent event) {
				Campaingns cam = getCampaingns(compainginskey);
				List<Contact> contacts = cam.getContacts();
				EDMSender emSender = new EDMSender();
				emSender.setCampaingns(cam);
				ClientResponse cr = emSender.execute();
				// Email email = new Email();
				// email.setEmailAddress("vancezhao@gmail.com");
				// contact.getEmailAddress().add(email);
				// Campaingns campaingns = new Campaingns();
				// campaingns.getContacts().add(contact);
				// String emailContext = "<h1>Hi This is what I need to talk to u</h1>";
				// campaingns.setEmailContext(emailContext);
				// emSender.setCampaingns(campaingns);
			}
		});

		return mainLayout;
	}

	@Override
	void addListener() {
		wizard.addListener(new EmailWizardListener(this));
	}

}
