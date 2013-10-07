/**
 * 
 */
package com.mocha.report;

import org.vaadin.teemu.wizards.Wizard;
import org.vaadin.teemu.wizards.WizardStep;
import org.vaadin.teemu.wizards.event.WizardCancelledEvent;
import org.vaadin.teemu.wizards.event.WizardCompletedEvent;
import org.vaadin.teemu.wizards.event.WizardProgressListener;
import org.vaadin.teemu.wizards.event.WizardStepActivationEvent;
import org.vaadin.teemu.wizards.event.WizardStepSetChangedEvent;

import com.coral.foundation.security.model.BasicUser;
import com.vaadin.ui.Component;

/**
 * @author Coral
 * 
 */
public class ReportWizardStep implements WizardStep {

	String caption;
	Component content;
	boolean advance = true;
	boolean back = true;
	WizardProgressListener listener;
	WizardStep w;
	private static BasicUser user;

	public ReportWizardStep(WizardStep w, WizardStep nStep) {
		this.w = w;
	}

	public ReportWizardStep(String caption, Component content) {
		this.caption = caption;
		this.content = content;
		this.listener = new ReportWizardProgressListener();
	}

	public ReportWizardStep() {

	}

	@Override
	public String getCaption() {
		return caption;
	}

	@Override
	public Component getContent() {
		return content;
	}

	@Override
	public boolean onAdvance() {
		return advance;
	}

	@Override
	public boolean onBack() {
		return advance;
	}

	public static BasicUser getUser() {
		return user;
	}

	public static void setUser(BasicUser user) {
		ReportWizardStep.user = user;
	}

	public class ReportWizardProgressListener implements WizardProgressListener {

		@Override
		public void activeStepChanged(WizardStepActivationEvent event) {

		}

		@Override
		public void stepSetChanged(WizardStepSetChangedEvent event) {
			// TODO Auto-generated method stub

		}

		@Override
		public void wizardCompleted(WizardCompletedEvent event) {
			// TODO Auto-generated method stub

		}

		@Override
		public void wizardCancelled(WizardCancelledEvent event) {
			// TODO Auto-generated method stub

		}

	}

}
