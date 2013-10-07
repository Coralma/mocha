package com.mocha.emailMarketing;

import org.vaadin.teemu.wizards.WizardStep;
import org.vaadin.teemu.wizards.event.WizardCancelledEvent;
import org.vaadin.teemu.wizards.event.WizardCompletedEvent;
import org.vaadin.teemu.wizards.event.WizardProgressListener;
import org.vaadin.teemu.wizards.event.WizardStepActivationEvent;
import org.vaadin.teemu.wizards.event.WizardStepSetChangedEvent;

public class EmailWizardListener implements WizardProgressListener {

	private WizardStep nStep;

	public EmailWizardListener(WizardStep nStep) {
		this.nStep = nStep;
	}

	@Override
	public void activeStepChanged(WizardStepActivationEvent event) {
		// System.out.println("activeStepChanged to" + event.getActivatedStep());
	}

	@Override
	public void stepSetChanged(WizardStepSetChangedEvent event) {
		// System.out.println("stepSetChanged");
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
