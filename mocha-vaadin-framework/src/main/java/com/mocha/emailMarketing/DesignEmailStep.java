package com.mocha.emailMarketing;

import java.util.ArrayList;
import java.util.List;

import org.vaadin.openesignforms.ckeditor.CKEditorConfig;
import org.vaadin.openesignforms.ckeditor.CKEditorTextField;
import org.vaadin.teemu.wizards.Wizard;

import com.coral.foundation.security.model.Campaingns;
import com.coral.foundation.security.model.Email;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.NativeButton;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;

public class DesignEmailStep extends AbstractCreateEmailSteps {
	private Wizard wizard;
	private String compainginskey;

	public DesignEmailStep(Wizard wizard, String compainginskey) {
		super(wizard, compainginskey);
		this.compainginskey = compainginskey;
		this.wizard = wizard;
		addListener();
	}

	@Override
	public String getCaption() {
		return "Design Email";
	}

	@Override
	public Component getContent() {
		VerticalLayout mainLayout = new VerticalLayout();
		mainLayout.setImmediate(true);
		// Create a rich text area
		CKEditorConfig config = new CKEditorConfig();
		config.useCompactTags();
		config.disableElementsPath();
		config.setResizeDir(CKEditorConfig.RESIZE_DIR.HORIZONTAL);
		config.disableSpellChecker();
		config.setToolbarCanCollapse(false);
		config.setWidth("100%");

		final CKEditorTextField ckEditorTextField = new CKEditorTextField(config);
		mainLayout.addComponent(ckEditorTextField);
		final String editorInitialValue = "";

		ckEditorTextField.setValue(editorInitialValue);
		NativeButton previewBtn = new NativeButton("Preivew");
		mainLayout.addComponent(previewBtn);
		previewBtn.addStyleName("mocha-button");
		previewBtn.addListener(new ClickListener() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {

				Email emailContext = new Email();
				emailContext.setEmailContext(ckEditorTextField.getValue().toString());
				List<Email> createEmails = getCampaingns(compainginskey).getEmails();

				Campaingns cam = getCampaingns(compainginskey);

				if (createEmails == null) {
					System.out.println("Create Email is null");
				}
				else {
					cam.setEmailContext(emailContext.getEmailContext());
				}
				
			}
		});

		// ckEditorTextField.setReadOnly(true);
		return mainLayout;
	}

	@Override
	void addListener() {
		wizard.addListener(new EmailWizardListener(this));
	}
}
