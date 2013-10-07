package com.mocha.emailMarketing;

import java.util.HashMap;
import java.util.UUID;

import org.vaadin.teemu.wizards.Wizard;
import org.vaadin.teemu.wizards.WizardStep;
import org.vaadin.teemu.wizards.event.WizardProgressListener;

import com.coral.foundation.security.model.Campaingns;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.NativeButton;
import com.vaadin.ui.VerticalLayout;

public abstract class AbstractCreateEmailSteps implements WizardStep {

	private VerticalLayout mainCreateEmailLayout = new VerticalLayout();
	boolean advance = true;
	boolean back = true;
	public Wizard wizard;
	String compainginskey;
	private static HashMap<String, Campaingns> campaingns = new HashMap<String, Campaingns>();

	WizardProgressListener wizardListenr;

	public AbstractCreateEmailSteps(Wizard wizard, String compainginskey) {
		this.compainginskey = compainginskey;
		this.wizard = wizard;
	}

	abstract void addListener();

	@Override
	public String getCaption() {
		return "";
	}

	@Override
	public Component getContent() {
		buttonRefine();
		return mainCreateEmailLayout;
	}

	private void buttonRefine() {

	}

	@Override
	public boolean onAdvance() {
		return advance;
	}

	@Override
	public boolean onBack() {
		return back;
	}

	public VerticalLayout getMainCreateEmailLayout() {
		return mainCreateEmailLayout;
	}

	public void setMainCreateEmailLayout(VerticalLayout mainCreateEmailLayout) {
		this.mainCreateEmailLayout = mainCreateEmailLayout;
	}

	public Campaingns getCampaingns(String oid) {
		if (campaingns.containsKey(oid)) {
			return campaingns.get(oid);
		}
		else if (oid != null) {
			Campaingns camp = new Campaingns();
			campaingns.put(oid, camp);
		}
		else {

			oid = UUID.randomUUID().toString();
			Campaingns camp = new Campaingns();
			campaingns.put(oid, camp);
		}
		return getCampaingns(oid);
	}

	public String getCompainginskey() {
		return compainginskey;
	}

	public void setCompainginskey(String compainginskey) {
		this.compainginskey = compainginskey;
	}
}
