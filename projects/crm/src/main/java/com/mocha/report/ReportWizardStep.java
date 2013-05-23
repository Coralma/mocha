/**
 * 
 */
package com.mocha.report;

import org.vaadin.teemu.wizards.WizardStep;

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
	
	public ReportWizardStep(String caption, Component content) {
		this.caption = caption;
		this.content = content;
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

}
