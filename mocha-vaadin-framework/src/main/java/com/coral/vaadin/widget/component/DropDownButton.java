/**
 * 
 */
package com.coral.vaadin.widget.component;

import com.vaadin.ui.Button;

/**
 * @author Coral.Ma
 *
 */
public class DropDownButton extends Button {

	public DropDownButton(String label) {
		super();
		this.setCaption(label + "<div></div>");
	}
}
