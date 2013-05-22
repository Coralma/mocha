/**
 * 
 */
package com.mocha.cooperate.widget;

import com.coral.vaadin.widget.WidgetFactory;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.themes.Reindeer;

/**
 * @author Coral
 *
 */
public abstract class ConfirmDialog extends Window implements ClickListener {
	
	private String content;
	private String CONFIRM = "Confirm";
	private String CANCEL = "Cancel";
	
	public ConfirmDialog(String content) {
		this("Confirm", content);
	}
	
	public ConfirmDialog(String caption, String content) {
		this.content = content;
		this.addStyleName(Reindeer.WINDOW_LIGHT);
		this.setWidth("380px");
		this.center();
		this.setClosable(false);
		this.setResizeLazy(true);
		this.setResizable(false);
		this.setModal(true);
		this.addStyleName("mocha-app");
		this.setCaption(caption);
	}
	
	public void attach() {
		VerticalLayout layout = new VerticalLayout();
		layout.setSpacing(true);
		layout.setMargin(true);
		Label confirmLabel = new Label(content, Label.CONTENT_XHTML);
		layout.addComponent(confirmLabel);
		
		HorizontalLayout buttonLayout = new HorizontalLayout();
		buttonLayout.setSpacing(true);
		buttonLayout.addComponent(WidgetFactory.createButton("Confirm", CONFIRM, this));
		buttonLayout.addComponent(WidgetFactory.createButton("Cancel", CANCEL, this));
		layout.addComponent(buttonLayout);
		layout.setComponentAlignment(buttonLayout,Alignment.MIDDLE_RIGHT);
		this.setContent(layout);
	}

	@Override
	public void buttonClick(ClickEvent event) {
		if(CONFIRM.equals(event.getButton().getData())) {
			confirm();
		} else if(CANCEL.equals(event.getButton().getData())) {
			cancel();
		}
		this.close();
	}
	
	public abstract void confirm();
	public abstract void cancel();
}
