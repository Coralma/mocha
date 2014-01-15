package com.mocha.common.widget.vo;

import com.coral.vaadin.widget.WidgetFactory;
import com.vaadin.ui.Label;

public class EntityGridVO {

	private String label;
	private String value;
	private boolean changeLine = false; //change a new line to display current field.
	private boolean wholeRow = false; // full whole row.
	
	public EntityGridVO(String label, String value) {
		this.label = label;
		this.value = value;
	}
	
	public Label getWidget() {
		return WidgetFactory.createCaptionLabel(label, value);
	}

	/**
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * @param label the label to set
	 */
	public void setLabel(String label) {
		this.label = label;
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * @return the changeLine
	 */
	public boolean isChangeLine() {
		return changeLine;
	}

	/**
	 * @param changeLine the changeLine to set
	 */
	public void setChangeLine(boolean changeLine) {
		this.changeLine = changeLine;
	}

	/**
	 * @return the wholeRow
	 */
	public boolean isWholeRow() {
		return wholeRow;
	}

	/**
	 * @param wholeRow the wholeRow to set
	 */
	public void setWholeRow(boolean wholeRow) {
		this.wholeRow = wholeRow;
	}

}
