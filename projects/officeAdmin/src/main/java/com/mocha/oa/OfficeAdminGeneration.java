/**
 * 
 */
package com.mocha.oa;

import java.util.ArrayList;
import java.util.List;

import com.coral.foundation.jpa.impl.JPAGenerator;
import com.coral.foundation.md.model.Resource;
import com.coral.foundation.utils.FileUtils;
import com.coral.vaadin.gen.SATFaceGenerator;

/**
 * @author Coral.Ma
 *
 */
public class OfficeAdminGeneration {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		 List<String> mappingXMLs = new ArrayList<String>();
//       mappingXMLs.add("define/officeAdminView.xml");
       mappingXMLs.add("src/main/resource/officeAdminModel.xml");
       Resource resource = new Resource();
       resource.setEntityXMLPaths(mappingXMLs);
       resource.setComponentName("officeAdmin");
       
       FileUtils.cleanFolder("src-gen");
       JPAGenerator generator = new JPAGenerator();
       generator.generater(resource);
//       VaadinFaceGenerator vaadinGen = new VaadinFaceGenerator(resource);
//       vaadinGen.generater();
       SATFaceGenerator faceGenerator = new SATFaceGenerator(resource);
       faceGenerator.generater();
	}
}
