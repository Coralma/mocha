/**
 * 
 */
package com.coral.vaadin.widget;

import org.vaadin.weelayout.WeeLayout;
import org.vaadin.weelayout.WeeLayout.Direction;

import com.vaadin.ui.Layout;

/**
 * @author Coral
 *
 */
public class LayoutFactory {

	public Layout createHorizontalLayout() {
		return new WeeLayout(Direction.HORIZONTAL);
	}
	
	public Layout createVertialLayout() {
		return new WeeLayout(Direction.VERTICAL);
	}
}
