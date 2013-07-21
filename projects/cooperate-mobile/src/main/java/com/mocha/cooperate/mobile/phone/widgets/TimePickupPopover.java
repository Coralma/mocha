package com.mocha.cooperate.mobile.phone.widgets;

import com.vaadin.addon.touchkit.ui.Popover;
import com.vaadin.addon.touchkit.ui.VerticalComponentGroup;
import com.vaadin.ui.Button;
import com.vaadin.ui.DateField;

public class TimePickupPopover extends Popover {

	private DateField timeField = new DateField();
	private Button pickupButton = new Button("Check This Timeline");
	
	public TimePickupPopover() {
		this.setWidth("80%");
		this.setClosable(true);
		this.setModal(true);
		this.center();
	}
	
	public void attach() {
		VerticalComponentGroup group = new VerticalComponentGroup();
		timeField.setWidth("100%");
		group.addComponent(timeField);
		
		pickupButton.setWidth("100%");
		group.addComponent(pickupButton);
		this.setContent(group);
	}

}
