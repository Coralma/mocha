/**
 * 
 */
package com.mocha.co;

import java.util.ArrayList;
import java.util.List;

import com.coral.foundation.jpa.impl.JPAGenerator;
import com.coral.foundation.md.model.Resource;
import com.coral.foundation.utils.FileUtils;
import com.coral.vaadin.gen.SATFaceGenerator;

public class CommerceGenerator {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
       List<String> mappingXMLs = new ArrayList<String>();
       mappingXMLs.add("src/main/resource/co-app.xml");
       mappingXMLs.add("src/main/resource/co-models.xml");
       mappingXMLs.add("src/main/resource/view-customer.xml");
       mappingXMLs.add("src/main/resource/view-order.xml");
       mappingXMLs.add("src/main/resource/view-product.xml");
       mappingXMLs.add("src/main/resource/view-supplier.xml");
       Resource resource = new Resource();
       resource.setEntityXMLPaths(mappingXMLs);
       resource.setComponentName("commerce");
       
       FileUtils.cleanFolder("src-gen");
       JPAGenerator generator = new JPAGenerator();
       generator.generater(resource);

       SATFaceGenerator faceGenerator = new SATFaceGenerator(resource);
       faceGenerator.generater();
	}

}
