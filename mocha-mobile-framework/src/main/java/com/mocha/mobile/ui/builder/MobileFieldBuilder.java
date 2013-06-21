/**
 * 
 */
package com.mocha.mobile.ui.builder;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.coral.foundation.md.model.ViewField;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.Field;

/**
 * @author Administrator
 *
 */
public class MobileFieldBuilder implements IMobileFieldBuilder {
	
	private List<IMobileFieldBuilder> buildChains;
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	public MobileFieldBuilder() {
		buildChains = new ArrayList<IMobileFieldBuilder>();
		buildChains.add(new MobileReferenceBuilderImpl());
		buildChains.add(new MobileTypeBuilderImpl());
	}

	public Field build(ViewField viewField) {
		boolean isRequired = viewField.getProperty().isRequired();
		for(IMobileFieldBuilder builder : buildChains) {
			Field field = (Field)builder.build(viewField);
			if(field != null) {
				field.setWidth("100%");
				field.setRequired(isRequired);
				((AbstractComponent) field).setData(viewField.getFieldName());
//				field.setData(fieldElement.getData());
//					field.setFieldWidth(section.getFieldWidth());
//					if(!StrUtils.isEmpty(fieldWidth)) {
//						((Field)widget).setFieldWidth(fieldWidth);	
//					}
//					section.addField((Component)widget);
//					widgets.add(widget);
				return field;
			}
		}
		return null;
	}
}
