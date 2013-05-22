package com.coral.vaadin.configuration;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.Properties;

public class MemoryPropertyConfiguration extends PropertyConfiguration {
	
	private static Properties prop=new Properties();

	static {
		String fileName="system.properties";
		
		try {
			InputStream in=Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
			prop.load(in);
			System.getProperties().putAll(prop);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String findConfiguration(String key) {
		if(prop!=null){
			return prop.getProperty(key);
		}
		return null;
	}

}
