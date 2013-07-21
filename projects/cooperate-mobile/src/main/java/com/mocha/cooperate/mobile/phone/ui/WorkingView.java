/**
 * 
 */
package com.mocha.cooperate.mobile.phone.ui;

import com.mocha.mobile.controller.MobileView;
import com.vaadin.addon.touchkit.ui.NavigationView;
import com.vaadin.addon.touchkit.ui.VerticalComponentGroup;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

/**
 * @author Coral
 *
 */
public class WorkingView extends NavigationView implements MobileView {

	public void attach() {
		super.attach();
		this.setCaption("Still Working");
		
		CssLayout workLayout = new CssLayout();
		workLayout.setSizeFull();
		workLayout.setMargin(true);
		
		VerticalLayout layout = new VerticalLayout();
		
		Label label = new Label();
		label.setIcon(new ThemeResource("images/working.jpg"));
		label.setWidth("100%");
		layout.addComponent(label);
		layout.setComponentAlignment(label, Alignment.TOP_CENTER);
		workLayout.addComponent(layout);
		
		
		VerticalComponentGroup group = new VerticalComponentGroup();
		
		Label descriptionLabel = new Label("We are working hard for this feature !");
		descriptionLabel.addStyleName("working-caption-title");
		group.addComponent(descriptionLabel);
		
		workLayout.addComponent(group);
		
		this.setContent(workLayout);
	}
}
