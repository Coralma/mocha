/**
 * 
 */
package com.coral.vaadin.widget.view.builder;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.coral.foundation.md.model.Property;
import com.coral.foundation.md.model.ViewField;
import com.coral.vaadin.widget.Field;
import com.coral.vaadin.widget.Widget;

/**
 * @author Administrator
 *
 */
public class FieldBuilder implements IFieldBuilder {
	
	private List<IFieldBuilder> buildChains;
//	private ViewContext viewContext;
	private Logger log = LoggerFactory.getLogger(this.getClass());

	public FieldBuilder() {
//		this.viewContext = viewContext;
		buildChains = new ArrayList<IFieldBuilder>();
		buildChains.add(new ReferenceBuilderImpl());
		buildChains.add(new StyleBuilderImpl());
		buildChains.add(new TypeBuilderImpl());
	}

	public Widget build(ViewField viewField) {
		Property dataProperty = viewField.getProperty();
		boolean isRequired = dataProperty.isRequired();
		for(IFieldBuilder builder : buildChains) {
			Widget widget = builder.build(viewField);
			if(widget != null) {
				if(widget instanceof Field) {
					((Field)widget).setRequired(isRequired);
				}
				widget.setData(dataProperty.getPropertyName());
				return widget;
			}
		}
		return null;
	}
}
