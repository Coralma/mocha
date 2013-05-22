/**
 * 
 */
package com.coral.foundation.utils;

import java.lang.reflect.Method;

/**
 * @author Coral
 *
 */
public class EntityUtils {

	public static String getPropertyValue(Object ref, String refProperty) {
		String value = null;
		Method m;
		try {
			m = ref.getClass().getMethod(StrUtils.getter(refProperty));
			value = (String) m.invoke(ref); 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return value;
	}
}
