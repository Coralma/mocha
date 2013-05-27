package com.coral.vaadin.widget.fields;

public class FieldStatus {

	private String type;
	private String path;
	private String label;
	private String codeTableName;
	private String style; // Eg: textarea
	
	private boolean isRequired = false;
	private boolean changeLine = false; //change a new line to display current field.
	private boolean wholeRow = false; // full whole row.
	private boolean wholeSection = false; // whole section FIXME DIDN'T USE
	
	public static FieldStatus create() {
		FieldStatus fieldStatus = new FieldStatus();
		return fieldStatus;
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
	public FieldStatus setChangeLine(boolean changeLine) {
		this.changeLine = changeLine;
		return this;
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
	public FieldStatus setWholeRow(boolean wholeRow) {
		this.wholeRow = wholeRow;
		return this;
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
	public FieldStatus setWholeSection(boolean wholeSection) {
		this.wholeSection = wholeSection;
		return this;
	}
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public FieldStatus setType(String type) {
		this.type = type;
		return this;
	}
	/**
	 * @return the path
	 */
	public String getPath() {
		return path;
	}
	/**
	 * @param path the property to set
	 */
	public FieldStatus setPath(String path) {
		this.path = path;
		return this;
	}
	/**
	 * @return the isRequired
	 */
	public boolean isRequired() {
		return isRequired;
	}
	/**
	 * @param isRequired the isRequired to set
	 */
	public FieldStatus setRequired(boolean isRequired) {
		this.isRequired = isRequired;
		return this;
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
	public FieldStatus setLabel(String label) {
		this.label = label;
		return this;
	}
	/**
	 * @return the codeTableName
	 */
	public String getCodeTableName() {
		return codeTableName;
	}
	/**
	 * @param codeTableName the codeTableName to set
	 */
	public FieldStatus setCodeTableName(String codeTableName) {
		this.codeTableName = codeTableName;
		return this;
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
	public FieldStatus setStyle(String style) {
		this.style = style;
		return this;
	}
	
	
}
