/**
 * 
 */
package com.coral.vaadin.widget.layout;

import com.coral.vaadin.widget.Field;
import com.coral.vaadin.widget.Widget;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;

/**
 * @author Administrator
 *
 */
public class TipBoxSectionLayout extends AbstractSectionLayout implements Section {

	private GridLayout gridLayout;
//	private int columns;

	public TipBoxSectionLayout(String label, int columns) {
//    	this.columns = columns;
    	this.setWidth("99%");
    	this.setSpacing(true);
    	this.setStyleName("mocha-page-tipbox-section");
    	
    	gridLayout = new GridLayout(columns, 1);
    	gridLayout.addStyleName("mocha-section-gridLayout");
    	gridLayout.setMargin(false,true,false,true);
    	gridLayout.setWidth("100%");
    	gridLayout.setSpacing(true);

    	CssLayout titleBlock = new CssLayout();
    	titleBlock.setWidth("100%");
    	titleBlock.setStyleName("mocha-section-block");
    	titleBlock.addComponent(createSectionLabel(label));
    	addComponent(titleBlock);
    	addComponent(gridLayout);
    }

	public Label createSectionLabel(String label) {
	    Label sectionLabel = new Label(label);
	    sectionLabel.setStyleName("mocha-section-title");
	    return sectionLabel;
	}
	
	public void addField(Widget field) {
		if(fieldWidth != null) {
			((Field)field).setFieldWidth(fieldWidth);
		}
    	gridLayout.addComponent(field);
    	gridLayout.setColumnExpandRatio(1, 3);
    	gridLayout.setColumnExpandRatio(2, 3);
    	gridLayout.setColumnExpandRatio(3, 3);
    	gridLayout.setComponentAlignment(field, Alignment.MIDDLE_LEFT);
    }
}
