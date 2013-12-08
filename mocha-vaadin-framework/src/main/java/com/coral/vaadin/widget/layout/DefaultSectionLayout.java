package com.coral.vaadin.widget.layout;

import com.coral.vaadin.widget.Widget;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class DefaultSectionLayout extends AbstractSectionLayout implements Section {

	private GridLayout gridLayout;
//	private int columns;

	public DefaultSectionLayout(String label, int columns) {
//    	this.columns = columns;
    	this.setWidth("99%");
    	this.setSpacing(true);
    	this.setStyleName("mocha-page-section");
    	
    	gridLayout = new GridLayout(columns, 1);
    	gridLayout.setMargin(false,true,false,true);
    	gridLayout.setWidth("100%");
    	gridLayout.setSpacing(true);

    	addComponent(createSectionLabel(label));
    	addComponent(gridLayout);
    }

	public Label createSectionLabel(String label) {
	    Label sectionLabel = new Label(label);
	    sectionLabel.setStyleName("mocha-section-title");
	    return sectionLabel;
	}
	
	public void addField(Widget field) {
		try {
			gridLayout.addComponent(field);
	    	gridLayout.setColumnExpandRatio(1, 3);
	    	gridLayout.setColumnExpandRatio(2, 3);
	    	gridLayout.setColumnExpandRatio(3, 3);
	    	gridLayout.setComponentAlignment(field, Alignment.MIDDLE_LEFT);	
		} catch (Exception e) {
//			System.out.println(field.getData());
			e.printStackTrace();
		}
    	
    }
}
