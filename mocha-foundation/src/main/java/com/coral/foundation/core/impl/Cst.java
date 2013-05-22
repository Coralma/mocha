/**
 * 
 */
package com.coral.foundation.core.impl;

import java.io.FileInputStream;
import java.util.Properties;

/**
 * @author Coral.Ma
 * 
 */
public class Cst {

	public static Properties properties;

	public static synchronized String getProperty(String key) throws Exception {
		if (properties == null) {
			String rs = Cst.class.getClassLoader().getResource("").getPath();
			String path = rs + "coral.properties";
			FileInputStream fis = new FileInputStream(path);
			properties = new Properties();
			properties.load(fis);
		}
		String value = (String) properties.get(key);
		if (value == null) {
			Log.error("System can not load " + key + " from coral.properties.",
					Cst.class);
		}
		return value;
	}
}
