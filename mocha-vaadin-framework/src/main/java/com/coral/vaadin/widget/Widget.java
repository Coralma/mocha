/**
 * 
 */
package com.coral.vaadin.widget;

import com.vaadin.ui.Component;

/**
 * @author Coral
 * 
 */
public interface Widget extends Component {

	/** value setter/getter. */
	public Object getValue();

	public void setValue(Object value);

	/** data setter/getter. */
	public Object getData();

	public void setData(Object data);

	/** validate widget*/
	public Result validate(String type);
}
