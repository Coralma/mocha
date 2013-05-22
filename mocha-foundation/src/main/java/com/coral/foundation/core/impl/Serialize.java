/**
 * 
 */
package com.coral.foundation.core.impl;

import java.io.File;
import java.io.Writer;
import java.net.URL;

import com.thoughtworks.xstream.XStream;

/**
 * @author Coral
 *
 */
public class Serialize {

	private static XStream stream = new XStream();
	
	public static String serialize(Object obj) throws Exception {
		return stream.toXML(obj);
	}
	
	public static void serialize(Object obj, Writer writer) throws Exception {
		stream.toXML(obj,writer);
	}

	public static Object deserialize(String xml) throws Exception {
		return stream.fromXML(xml);
	}

	public static Object deserialize(File xml) throws Exception {
		return stream.fromXML(xml);
	}
	
	public static Object deserializeModel() throws Exception {
		Object rtObj = null;
		URL url = Thread.currentThread().getContextClassLoader().getResource("cmodel.xml");
		File file = new File(url.getFile());
		if(file.exists()) {
			rtObj = stream.fromXML(file);
		}
		return rtObj;
	}
	
	public static Object deserializeModel(String fileName) throws Exception {
		Object rtObj = null;
		URL url = Thread.currentThread().getContextClassLoader().getResource(fileName);
		File file = new File(url.getFile());
		if(file.exists()) {
			rtObj = stream.fromXML(file);
		}
		return rtObj;
	}
}
