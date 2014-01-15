package com.mocha.template;

import com.vaadin.ui.Button;
import com.vaadin.ui.ComponentContainer;

public interface IAppActionSection extends ComponentContainer {

	public void addMainButton(Button mainButton);
	public void addAttachedButton(Button attachedButton);
}
