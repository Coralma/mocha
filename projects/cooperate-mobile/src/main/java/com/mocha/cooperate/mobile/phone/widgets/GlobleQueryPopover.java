package com.mocha.cooperate.mobile.phone.widgets;

import com.vaadin.addon.touchkit.ui.Popover;
import com.vaadin.addon.touchkit.ui.VerticalComponentGroup;
import com.vaadin.ui.Button;
import com.vaadin.ui.TextField;
import com.vaadin.ui.TextArea;

public class GlobleQueryPopover extends Popover {

	private TextArea inputField = new TextArea();
//	private TextField inputField = new TextField();
	private Button queryButton = new Button("Globle Query");
	
	public GlobleQueryPopover() {
		this.setWidth("80%");
		this.setClosable(true);
		this.setModal(true);
		this.center();
	}
	
	public void attach() {
		VerticalComponentGroup group = new VerticalComponentGroup();
		inputField.setWidth("100%");
		inputField.setRows(2);
		group.addComponent(inputField);
		queryButton.setWidth("100%");
		group.addComponent(queryButton);
		this.setContent(group);
	}
}
