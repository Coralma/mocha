package com.coral.foundation.utils;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.PropertyUtils;

public class ReflectionUtils {

	public static Object getVariableValue(Object entity, String propertyName) {
		Object data = null;
		try {
			data = PropertyUtils.getProperty(entity, propertyName);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		return data;
	}
	
	public static void setVariableValue(Object entity, String propertyName, Object value) {
		try {
			PropertyUtils.setProperty(entity, propertyName, value);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
	}
}
