/**
 * 
 */
package com.coral.vaadin.util;

import java.lang.reflect.Method;

import org.apache.commons.beanutils.PropertyUtils;

import com.coral.foundation.utils.StrUtils;


/**
 * @author Coral
 *
 */
public class AbstractDataBinding {
	
	protected static void injectData(String propertyData, Object textValue, Object entity, Class<?> cls) {
		try {
			PropertyUtils.setProperty(entity, propertyData, textValue);
//			Class<?> param = cls.getDeclaredField(propertyData).getType();
//			Method m = cls.getMethod(StrUtils.setter(propertyData),param); 
//			m.invoke(entity,new Object[]{textValue});
		} catch (Exception e) {
//			Log.error("System injectData Error! The propertyData is " + propertyData 
//					+ " , and the injected value is " + textValue 
//					+ " , and the entity is " + entity + " , and the class is " + cls.getName(), cls, e.fillInStackTrace());
		}
	}
	
	protected static void setAbstractData(Object textValue, Object entity, Class<?> cls) {
		try {
			cls.getMethod("setAbstractData", String.class).invoke(entity, textValue);
		} catch (Exception e) {
//			Log.error("Set abstract data error! The value is " + textValue + " , and the entity is " + entity, cls, e.fillInStackTrace());

		}
	}
	
	protected static Object digData(String propertyData, Object entity, Class<?> cls) {
		Object fillData = null;
		try {
			fillData = PropertyUtils.getProperty(entity, propertyData);
//			Method m = cls.getMethod(StrUtils.getter(propertyData)); 
//			fillData = m.invoke(entity);
		} catch (Exception e) {
//			Log.error("System fillData Error! The propertyData is " + propertyData 
//					+ " , and the entity is " + entity + " , and the class is " + cls.getName(), cls, e.fillInStackTrace());
		}
		return fillData;
	}
}
