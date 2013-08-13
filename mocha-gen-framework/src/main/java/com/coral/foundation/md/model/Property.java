/**
 * 
 */
package com.coral.foundation.md.model;

import java.io.Serializable;

/**
 * @author coral.ma
 *
 */
public class Property implements Serializable {

	public String propertyName;
	// define the column name of database.
	public String columnName;
	// define a variable with @Transient, it won't persistent in database.
	public boolean ignore=false;
	// define a field label for view to display.
	public String label;
	// define variable type use java type. such as textField, IntField...
	public String type;
	// define the default value of this property. The key work is "default"
	public String defaultValue;
	@Deprecated
	public String code;
	public String length;
	public String ref;
	@Deprecated
	public String style;
	@Deprecated
	public String size;
	public String orm;
	public String mappedBy;
	@Deprecated
	public boolean notnull = false;
	@Deprecated
	public boolean required = false;
	@Deprecated
	public String refProperty;
	@Deprecated
	public boolean refer = false; // check is refer or contain. true means refer, no mention means contain.
	
	/**
	 * @return the propertyName
	 */
	public String getPropertyName() {
		return propertyName;
	}
	/**
	 * @param propertyName the propertyName to set
	 */
	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
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
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * @return the ref
	 */
	public String getRef() {
		return ref;
	}
	/**
	 * @param ref the ref to set
	 */
	public void setRef(String ref) {
		this.ref = ref;
	}
//	/**
//	 * @return the isTableColumn
//	 */
//	public String getIsTableColumn() {
//		return isTableColumn;
//	}
//	/**
//	 * @param isTableColumn the isTableColumn to set
//	 */
//	public void setIsTableColumn(String isTableColumn) {
//		this.isTableColumn = isTableColumn;
//	}
//	/**
//	 * @return the isViewColumn
//	 */
//	public String getIsViewColumn() {
//		return isViewColumn;
//	}
//	/**
//	 * @param isViewColumn the isViewColumn to set
//	 */
//	public void setIsViewColumn(String isViewColumn) {
//		this.isViewColumn = isViewColumn;
//	}
//	/**
//	 * @return the pk
//	 */
//	public String getPk() {
//		return pk;
//	}
//	/**
//	 * @param pk the pk to set
//	 */
//	public void setPk(String pk) {
//		this.pk = pk;
//	}
	/**
	 * @return the length
	 */
	public String getLength() {
		return length;
	}
	/**
	 * @param length the length to set
	 */
	public void setLength(String length) {
		this.length = length;
	}
	/**
	 * @return the size
	 */
	public String getSize() {
		return size;
	}
	/**
	 * @param size the size to set
	 */
	public void setSize(String size) {
		this.size = size;
	}
	/**
	 * @return the orm
	 */
	public String getOrm() {
		return orm;
	}
	/**
	 * @param orm the orm to set
	 */
	public void setOrm(String orm) {
		this.orm = orm;
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
	 * @return the code
	 */
	public String getCode() {
		return code;
	}
	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
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

	/**
	 * @return the refProperty
	 */
	public String getRefProperty() {
		return refProperty;
	}
	/**
	 * @param refProperty the refProperty to set
	 */
	public void setRefProperty(String refProperty) {
		this.refProperty = refProperty;
	}
	/**
	 * @return the notnull
	 */
	public boolean isNotnull() {
		return notnull;
	}
	/**
	 * @param notnull the notnull to set
	 */
	public void setNotnull(boolean notnull) {
		this.notnull = notnull;
	}
	/**
	 * @return the mappedBy
	 */
	public String getMappedBy() {
		return mappedBy;
	}
	/**
	 * @param mappedBy the mappedBy to set
	 */
	public void setMappedBy(String mappedBy) {
		this.mappedBy = mappedBy;
	}
	public String getStyle() {
		return style;
	}
	public void setStyle(String style) {
		this.style = style;
	}
	/**
	 * @return the columnName
	 */
	public String getColumnName() {
		return columnName;
	}
	/**
	 * @param columnName the columnName to set
	 */
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	/**
	 * @return the ignore
	 */
	public boolean isIgnore() {
		return ignore;
	}
	/**
	 * @param ignore the ignore to set
	 */
	public void setIgnore(boolean ignore) {
		this.ignore = ignore;
	}
	/**
	 * @return the refer
	 */
	public boolean isRefer() {
		return refer;
	}
	/**
	 * @param refer the refer to set
	 */
	public void setRefer(boolean refer) {
		this.refer = refer;
	}
	/**
	 * @return the defaultValue
	 */
	public String getDefaultValue() {
		return defaultValue;
	}
	/**
	 * @param defaultValue the defaultValue to set
	 */
	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}
}
