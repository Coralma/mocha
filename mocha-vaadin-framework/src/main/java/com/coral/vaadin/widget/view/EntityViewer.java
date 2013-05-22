/**
 * 
 */
package com.coral.vaadin.widget.view;

import com.coral.foundation.md.model.View;
import com.coral.foundation.md.model.ViewAction;
import com.coral.foundation.md.model.ViewField;
import com.coral.foundation.md.model.ViewSection;
import com.coral.vaadin.widget.Viewer;
import com.coral.vaadin.widget.layout.Section;
import com.coral.vaadin.widget.view.builder.ViewContext;
import com.vaadin.ui.ComponentContainer;

/**
 * @author Administrator
 *
 */
public class EntityViewer extends AbstractEntityViewer implements Viewer {

	private static final long serialVersionUID = 7895396741213718163L;
	private Object value;
	
	public EntityViewer(ViewContext viewContext) {
		super(viewContext);
		
	}

	public void build() {
		View view = viewContext.getCurrentView();
		// build section and inner field of page.
		for(ViewSection section : view.getSections()) {
			drawSection(section,this);
		}
		
		// build foot action button
		for(ViewAction action : view.getViewActions()) {
			//TODO the action label should get from i18n definition.
			addButton(buildButton(action.getLabel(),action.getName()));
		}
		
	}

	private Section drawSection(ViewSection viewSection, ComponentContainer parent) {
		Section section = buildSection(viewSection,parent);
		// if field is not empty, add the field into current section.
		if(viewSection.getViewFields() != null) {
			for(ViewField viewField : viewSection.getViewFields()) {
				section.addField(buildWidget(viewField));
			}
		}
		// if this section has a sub section, create the sub-section and add into current section. 
		if(viewSection.getViewSections() != null) {
			for(ViewSection subViewSection : viewSection.getViewSections()) {
				drawSection(subViewSection,section);
//				Section subSection = drawSection(subViewSection,parent);
//				section.addComponent(subSection);
			}
		}
		// if action is not a null, build the button below current section.
		for(ViewAction action : viewSection.getViewActions()) {
			section.addButton(buildButton(action.getLabel(),action.getName()));
		}
		return section;
	}
	
	/**
	 * @return the value
	 */
	public Object getValue() {
		return getEntityValue(viewContext.getEntityClass());
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(Object value) {
		this.value = value;
		setEntityValue(value);
	}
}
