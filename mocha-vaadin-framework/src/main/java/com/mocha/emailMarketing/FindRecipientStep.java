package com.mocha.emailMarketing;

import java.util.ArrayList;

import org.vaadin.teemu.wizards.Wizard;
import org.vaadin.teemu.wizards.event.WizardCancelledEvent;
import org.vaadin.teemu.wizards.event.WizardCompletedEvent;
import org.vaadin.teemu.wizards.event.WizardProgressListener;
import org.vaadin.teemu.wizards.event.WizardStepActivationEvent;
import org.vaadin.teemu.wizards.event.WizardStepSetChangedEvent;

import com.coral.foundation.security.basic.dao.CampaingnsDao;
import com.coral.foundation.security.model.Campaingns;
import com.coral.foundation.security.model.Contact;
import com.coral.foundation.security.model.Email;
import com.coral.foundation.spring.bean.SpringContextUtils;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.NativeButton;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;

public class FindRecipientStep extends AbstractCreateEmailSteps {

	private Wizard wizard;
	private ArrayList<Email> emailList = new ArrayList<Email>();
	private ArrayList<Contact> contactList = new ArrayList<Contact>();
	private VerticalLayout createContactLayout = new VerticalLayout();
	private String compainginskey;

	public FindRecipientStep(Wizard wizard, String compainginskey) {
		super(wizard, compainginskey);
		this.compainginskey = compainginskey;
		this.wizard = wizard;
		addListener();
	}

	@Override
	public String getCaption() {
		return "Find Recipients";
	}

	@Override
	public Component getContent() {
		VerticalLayout layout = new VerticalLayout();
		final VerticalLayout entireListsLayout = new VerticalLayout();
		final CheckBox entireLists = new CheckBox("Send to Entire Lists");
		final CheckBox newlists = new CheckBox("Send to New Users");
		entireListsLayout.addComponent(entireLists);
		entireLists.setValue(true);
		entireLists.setImmediate(true);
		entireLists.addListener(new Property.ValueChangeListener() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void valueChange(ValueChangeEvent event) {
				Label entireLabel = new Label("Will send to the Users From Your Mail Lists");
				if (entireLists.getValue() == Boolean.TRUE) {
					entireListsLayout.addComponent(entireLabel);
					newlists.setValue(false);
				}
				else {
					entireListsLayout.removeAllComponents();
					entireListsLayout.addComponent(entireLists);

				}
				entireListsLayout.requestRepaintAll();

			}
		});
		final VerticalLayout newListsLayout = new VerticalLayout();
		newListsLayout.addComponent(newlists);
		newlists.setImmediate(true);
		newlists.addListener(new Property.ValueChangeListener() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void valueChange(ValueChangeEvent event) {
				Label entireLayout = new Label("Send to new User Groups");
				if (newlists.getValue() == Boolean.TRUE) {
					newListsLayout.addComponent(entireLayout);
					entireLists.setValue(false);
					newListsLayout.addComponent(buildCreateContactLayout());
				}
				else if (newlists.getValue() == Boolean.FALSE) {
					newListsLayout.removeAllComponents();
					newListsLayout.addComponent(newlists);
				}
				newListsLayout.requestRepaintAll();
			}
		});
		layout.addComponent(entireListsLayout);
		layout.addComponent(newListsLayout);
		return layout;
	}

	protected Component buildCreateContactLayout() {
		// btn
		NativeButton createBtn = new NativeButton("Create New Contact");
		createBtn.addStyleName("mocha-button");
		createContactLayout.addComponent(createBtn);
		createContactLayout.setComponentAlignment(createBtn, Alignment.TOP_RIGHT);
		buildCreateTempNewCotnactLayout();
		return createContactLayout;
	}

	private void buildCreateTempNewCotnactLayout() {
		// input layout
		final HorizontalLayout inputLayout = new HorizontalLayout();
		createContactLayout.addComponent(inputLayout);
		final TextField userName = new TextField();
		userName.setCaption("User Name");
		inputLayout.addComponent(userName);
		final TextField emailAddress = new TextField();
		emailAddress.setCaption("EmailAddress Name");
		inputLayout.addComponent(emailAddress);
		NativeButton saveContactBtn = new NativeButton("Save");
		saveContactBtn.addStyleName("mocha-button");
		inputLayout.addComponent(saveContactBtn);
		saveContactBtn.addListener(new ClickListener() {

			/**
					 * 
					 */
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				Email email = new Email();
				email.setEmailAddress(emailAddress.getValue().toString());
				Contact contact = new Contact();
				contact.getEmailAddress().add(email);
				getCampaingns(compainginskey).getContacts().add(contact);
				// refresh layout
				inputLayout.removeAllComponents();
				Label saveUsername = new Label(userName.getValue().toString());
				inputLayout.addComponent(saveUsername);
				buildCreateTempNewCotnactLayout();
			}
		});
	}

	@Override
	public boolean onAdvance() {
		return true;
	}

	@Override
	public boolean onBack() {
		return true;
	}

	public Wizard getWizard() {
		return wizard;
	}

	public void setWizard(Wizard wizard) {
		this.wizard = wizard;
	}

	@Override
	void addListener() {
		wizard.addListener(new WizardProgressListener() {

			@Override
			public void activeStepChanged(WizardStepActivationEvent event) {

			}

			@Override
			public void stepSetChanged(WizardStepSetChangedEvent event) {

			}

			@Override
			public void wizardCompleted(WizardCompletedEvent event) {
				// TODO Auto-generated method stub

			}

			@Override
			public void wizardCancelled(WizardCancelledEvent event) {
				

			}

		});
	}

	public ArrayList<Email> getEmailList() {
		return emailList;
	}

	public void setEmailList(ArrayList<Email> emailList) {
		this.emailList = emailList;
	}

	public ArrayList<Contact> getContactList() {
		return contactList;
	}

	public void setContactList(ArrayList<Contact> contactList) {
		this.contactList = contactList;
	}
}
