/**
 * 
 */
package com.coral.vaadin.util;

import java.util.Collection;
import java.util.List;

import com.coral.vaadin.widget.Widget;

/**
 * @author Coral
 *
 */
public class VaadinDataBinding extends AbstractDataBinding {

	/**
	 * Fields to POJO value.
	 * @param children
	 * @param entity
	 * @param cls
	 * @return POJO
	 */
	public static <T> T bindingData(Collection<Widget> children, T entity, Class<T> cls) {
		String abstractData = "";
		for(Widget child : children) {
			Object obj = child.getData();
			if(obj != null) {
				Object value = child.getValue();
				if(value != null) {
					abstractData= abstractData + value.toString()+" ";
					injectData(child.getData().toString(), value, entity, cls);
				}
			}
		}
		setAbstractData(abstractData, entity, cls);
		return entity;
	}
	
	/**
	 * POJO value to fields
	 * @param entity
	 * @param children
	 * @return fields
	 */
	public static void bindingToFields(Object entity, Collection<Widget> children) {
		if(entity == null) {
			return;
		}
		for(Widget child : children) {
			Object data = child.getData();
			if(data != null) {
				Object value = digData(data.toString(),entity,entity.getClass());
				if(value != null)
					child.setValue(value);
			}
		}
	}
}
