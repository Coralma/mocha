package com.mocha.emailMarketing;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

import org.vaadin.teemu.wizards.Wizard;

import com.coral.vaadin.view.template.sat.panel.impl.EntityViewPanel;
import com.coral.vaadin.widget.Result;
import com.coral.vaadin.widget.Viewer;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.NativeButton;
import com.vaadin.ui.VerticalLayout;

public class CreateEmailViewer extends EntityViewPanel implements Viewer {

	private VerticalLayout mainLayout = new VerticalLayout();
	private NativeButton createEmailBtn = new NativeButton("Create Email Template");
	private Wizard wizard = new Wizard();

	public CreateEmailViewer() {

	}

	@Override
	public void build() {
		addComponent(getMainLayout());
		buildCreateEmailLayout();
	}

	private void buildCreateEmailLayout() {
		HorizontalLayout createEmailLayout = new HorizontalLayout();
		getMainLayout().addComponent(createEmailLayout);
		createEmailBtn.addStyleName("mocha-button");
		createEmailLayout.addComponent(getCreateEmailBtn());
	}

	protected void buildEmailStepsLayou() {
		String compainginskey = UUID.randomUUID().toString();
		wizard.addStep(new FindRecipientStep(wizard, compainginskey));
		wizard.addStep(new DesignEmailStep(wizard, compainginskey));
		wizard.addStep(new SettingEmailStep(wizard, compainginskey));
		wizard.addStep(new ConfirmEmailStep(wizard, compainginskey));
		getMainLayout().addComponent(wizard);
	}

	public void attach() {
		build();
	}

	public NativeButton getCreateEmailBtn() {
		return createEmailBtn;
	}

	public void setCreateEmailBtn(NativeButton createEmailBtn) {
		this.createEmailBtn = createEmailBtn;
	}

	public VerticalLayout getMainLayout() {
		return mainLayout;
	}

	public void setMainLayout(VerticalLayout mainLayout) {
		this.mainLayout = mainLayout;
	}
}