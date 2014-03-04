package com.mocha.soicalAPI;

import com.coral.vaadin.view.template.sat.panel.impl.EntityViewPanel;
import com.coral.vaadin.widget.Viewer;
import com.vaadin.ui.VerticalLayout;

public class IbSocialNetworkProfileViewer extends EntityViewPanel implements Viewer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private VerticalLayout mainLayout = new VerticalLayout();

	public IbSocialNetworkProfileViewer() {
		
	}

	@Override
	public void build() {
		addComponent(mainLayout);
	}

}
