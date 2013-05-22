/**
 * 
 */
package com.coral.foundation.md.model;

import java.io.Serializable;
import java.util.List;

import com.google.common.collect.Lists;

/**
 * @author Coral
 *
 */
public class ViewSection implements Serializable {

	public String name;
	public String label;
	public String style;
	public String type;
	public String group;
	public String viewer;
	public String data;
	public String template;
	public Integer column;
	public List<ViewSection> viewSections;
	public List<ViewField> viewFields;
	public List<ViewAction> viewActions = Lists.newArrayList();
	public View view;
	public ViewSection viewSection;
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
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
	 * @return the viewFields
	 */
	public List<ViewField> getViewFields() {
		return viewFields;
	}
	/**
	 * @param viewFields the viewFields to set
	 */
	public void setViewFields(List<ViewField> viewFields) {
		this.viewFields = viewFields;
	}
	/**
	 * @return the column
	 */
	public Integer getColumn() {
		return column;
	}
	/**
	 * @param column the column to set
	 */
	public void setColumn(Integer column) {
		this.column = column;
	}
	/**
	 * @return the viewSections
	 */
	public List<ViewSection> getViewSections() {
		return viewSections;
	}
	/**
	 * @param viewSections the viewSections to set
	 */
	public void setViewSections(List<ViewSection> viewSections) {
		this.viewSections = viewSections;
	}
	public String getStyle() {
		return style;
	}
	public void setStyle(String style) {
		this.style = style;
	}
	public String getGroup() {
		return group;
	}
	public void setGroup(String group) {
		this.group = group;
	}
	/**
	 * @return the viewer
	 */
	public String getViewer() {
		return viewer;
	}
	/**
	 * @param viewer the viewer to set
	 */
	public void setViewer(String viewer) {
		this.viewer = viewer;
	}
	/**
	 * @return the data
	 */
	public String getData() {
		return data;
	}
	/**
	 * @param data the data to set
	 */
	public void setData(String data) {
		this.data = data;
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
	 * @return the view
	 */
	public View getView() {
		return view;
	}
	/**
	 * @param view the view to set
	 */
	public void setView(View view) {
		this.view = view;
	}
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
	 * @return the viewActions
	 */
	public List<ViewAction> getViewActions() {
		return viewActions;
	}
	/**
	 * @param viewActions the viewActions to set
	 */
	public void setViewActions(List<ViewAction> viewActions) {
		this.viewActions = viewActions;
	}
	/**
	 * @return the template
	 */
	public String getTemplate() {
		return template;
	}
	/**
	 * @param template the template to set
	 */
	public void setTemplate(String template) {
		this.template = template;
	}
}
