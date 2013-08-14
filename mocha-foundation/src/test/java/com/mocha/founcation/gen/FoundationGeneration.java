/**
 * 
 */
package com.mocha.founcation.gen;

import java.util.ArrayList;
import java.util.List;

import com.coral.foundation.jpa.impl.JPAGenerator;
import com.coral.foundation.md.model.Resource;

/**
 * @author Administrator
 * 
 */
public class FoundationGeneration {
	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		List<String> mappingXMLs = new ArrayList<String>();
//		mappingXMLs.add("define/app.xml");
		mappingXMLs.add("src/main/resource/user.xml");
		Resource resource = new Resource();
		resource.setEntityXMLPaths(mappingXMLs);
//		String testPath = "src-gen";
//		resource.setDaoGenPath(testPath);
//		resource.setDdlGenPath(testPath);
//		resource.setEntityGenPath(testPath);
//		resource.setServiceGenPath(testPath);
		resource.setComponentName("foundation");

		JPAGenerator generator = new JPAGenerator();
		generator.generater(resource);
	}
}
