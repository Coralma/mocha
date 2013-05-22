/**
 * 
 */
package com.coral.vaadin.widget.validate;

/**
 * @author Coral
 *
 */
public interface Validator {
	
	public static String COMMIT = "COMMIT";
	public static String ONBLUR = "ONBLUR";

	public boolean validate(String status);
}
