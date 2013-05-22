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
public class View implements Serializable {

	public String name;
	public String template;
	public String dialogSize;
	public String label;
	public String type;
	public boolean root;
	public List<ViewSection> sections;
	public List<ViewAction> viewActions = Lists.newArrayList();
	public Entity entity;
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
	/**
	 * @return the sections
	 */
	public List<ViewSection> getSections() {
		return sections;
	}
	/**
	 * @param sections the sections to set
	 */
	public void setSections(List<ViewSection> sections) {
		this.sections = sections;
	}
	/**
	 * @return the dialogSize
	 */
	public String getDialogSize() {
		return dialogSize;
	}
	/**
	 * @param dialogSize the dialogSize to set
	 */
	public void setDialogSize(String dialogSize) {
		this.dialogSize = dialogSize;
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
	
	public boolean isRoot() {
		return root;
	}
	public void setRoot(boolean root) {
		this.root = root;
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
	 * @return the entity
	 */
	public Entity getEntity() {
		return entity;
	}
	/**
	 * @param entity the entity to set
	 */
	public void setEntity(Entity entity) {
		this.entity = entity;
	}
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


}
