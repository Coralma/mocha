package com.coral.foundation.md.model;

import java.io.Serializable;

public class ViewField implements Serializable {

	private static final long serialVersionUID = -6989596200687040692L;
	public String fieldName;
	public String label;
	public String path;
	public String codeTable;
	public String style;
	public boolean required = false;
	private boolean changeLine = false; //change a new line to display current field.
	private boolean wholeRow = false; // full whole row.
	private boolean wholeSection = false; // whole section FIXME DIDN'T USE
	//	public Properties viewProperties = new Properties();
	public ViewSection viewSection;
	public Property property;
	
	/**
	 * @return the fieldName
	 */
	public String getFieldName() {
		return fieldName;
	}
	/**
	 * @param fieldName the fieldName to set
	 */
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
//	/**
//	 * @return the viewProperties
//	 */
//	public Properties getViewProperties() {
//		return viewProperties;
//	}
//	/**
//	 * @param viewProperties the viewProperties to set
//	 */
//	public void setViewProperties(Properties viewProperties) {
//		this.viewProperties = viewProperties;
//	}
	/**
	 * @return the viewSection
	 */
	public ViewSection getViewSection() {
		return viewSection;
	}
	/**
	 * @param viewSection the viewSection to set
	 */
	public void setViewSection(ViewSection viewSection) {
		this.viewSection = viewSection;
	}
	/**
	 * @return the property
	 */
	public Property getProperty() {
		return property;
	}
	/**
	 * @param property the property to set
	 */
	public void setProperty(Property property) {
		this.property = property;
	}
	/**
	 * @return the path
	 */
	public String getPath() {
		if(path == null) {
			return fieldName;
		}
		return path;
	}
	/**
	 * @param path the path to set
	 */
	public void setPath(String path) {
		this.path = path;
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
	 * @return the codeTable
	 */
	public String getCodeTable() {
		return codeTable;
	}
	/**
	 * @param codeTable the codeTable to set
	 */
	public void setCodeTable(String codeTable) {
		this.codeTable = codeTable;
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
	/**
	 * @return the wholeSection
	 */
	public boolean isWholeSection() {
		return wholeSection;
	}
	/**
	 * @param wholeSection the wholeSection to set
	 */
	public void setWholeSection(boolean wholeSection) {
		this.wholeSection = wholeSection;
	}
	/**
	 * @return the style
	 */
	public String getStyle() {
		return style;
	}
	/**
	 * @param style the style to set
	 */
	public void setStyle(String style) {
		this.style = style;
	}
	/**
	 * @return the required
	 */
	public boolean isRequired() {
		return required;
	}
	/**
	 * @param required the required to set
	 */
	public void setRequired(boolean required) {
		this.required = required;
	}
}
