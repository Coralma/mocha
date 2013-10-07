package com.mocha.emailMarketing;

import org.vaadin.teemu.wizards.Wizard;

import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.NativeButton;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;

public class SettingEmailStep extends AbstractCreateEmailSteps {

	private Wizard wizard;
	private NativeButton saveButton = new NativeButton();

	public SettingEmailStep(Wizard wizard, String compainginskey) {
		super(wizard, compainginskey);
		this.compainginskey = compainginskey;
		this.wizard = wizard;
		addListener();
	}

	@Override
	public String getCaption() {
		return "Setup Email";
	}

	@Override
	public Component getContent() {
		final VerticalLayout layout = new VerticalLayout();
		layout.setCaption("Campaign Info");
		final TextField compainName = new TextField("Campain Name");
		layout.addComponent(compainName);
		TextField emailSubject = new TextField("Campain Subject");
		layout.addComponent(emailSubject);
		NativeButton saveBtn = new NativeButton("Save");
		saveBtn.addStyleName("mocha-button");
		layout.addComponent(saveBtn);
		saveBtn.addListener(new ClickListener() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				getCampaingns(compainginskey).setName(compainName.getValue().toString());
			}
		});
		return layout;
	}

	@Override
	void addListener() {
		wizard.addListener(new EmailWizardListener(this));
	}

}
