package com.mocha.template.general.widget.vo;

import java.util.List;

import com.google.common.collect.Lists;

public class GeneralEntityCardSectionVO {

	public String sectionLabel;
	public List<Object> entities = Lists.newArrayList();
	public String[] fieldVariables;
	public String[] fieldLabels;

	/**
	 * @return the sectionLabel
	 */
	public String getSectionLabel() {
		return sectionLabel;
	}
	/**
	 * @param sectionLabel the sectionLabel to set
	 */
	public void setSectionLabel(String sectionLabel) {
		this.sectionLabel = sectionLabel;
	}
	/**
	 * @return the entities
	 */
	public List<Object> getEntities() {
		return entities;
	}
	/**
	 * @param entities the entities to set
	 */
	public void setEntities(List<Object> entities) {
		this.entities = entities;
	}
	
	public void addEntity(Object entity) {
		this.entities.add(entity);
	}
	/**
	 * @return the fieldVariables
	 */
	public String[] getFieldVariables() {
		return fieldVariables;
	}
	/**
	 * @param fieldVariables the fieldVariables to set
	 */
	public void setFieldVariables(String[] fieldVariables) {
		this.fieldVariables = fieldVariables;
	}
	/**
	 * @return the fieldLabels
	 */
	public String[] getFieldLabels() {
		return fieldLabels;
	}
	/**
	 * @param fieldLabels the fieldLabels to set
	 */
	public void setFieldLabels(String[] fieldLabels) {
		this.fieldLabels = fieldLabels;
	}
}
