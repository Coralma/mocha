/**
 * 
 */
package com.mocha.cooperate;

import java.util.ArrayList;
import java.util.List;

import com.coral.foundation.jpa.impl.CodeTableScriptGenerator;
import com.coral.foundation.jpa.impl.JPAGenerator;
import com.coral.foundation.md.model.Resource;
import com.coral.vaadin.gen.VaadinFaceGenerator;

/**
 * @author Coral
 *
 */
public class CooperateGenerator {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		
	   List<String> mappingXMLs = new ArrayList<String>();
       mappingXMLs.add("src/main/resource/widgetModel.xml");
       Resource resource = new Resource();
       resource.setEntityXMLPaths(mappingXMLs);
       resource.setComponentName("cooperate");
       
       JPAGenerator generator = new JPAGenerator();
       generator.generater(resource);
       VaadinFaceGenerator vaadinGen = new VaadinFaceGenerator(resource);
       vaadinGen.generater();
       
//       CodeTableScriptGenerator ctGen = new CodeTableScriptGenerator();
//       ctGen.generate();
	}

}
