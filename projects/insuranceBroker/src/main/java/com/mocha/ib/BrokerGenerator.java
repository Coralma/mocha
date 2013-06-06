/**
 * 
 */
package com.mocha.ib;

import java.util.ArrayList;
import java.util.List;

import com.coral.foundation.jpa.impl.JPAGenerator;
import com.coral.foundation.md.model.Resource;
import com.coral.foundation.utils.FileUtils;
import com.coral.vaadin.gen.SATFaceGenerator;

/**
 * @author Coral
 *
 */
public class BrokerGenerator {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
       List<String> mappingXMLs = new ArrayList<String>();
       mappingXMLs.add("src/main/resource/ib-app.xml");
       mappingXMLs.add("src/main/resource/ib-codetable.xml");
       mappingXMLs.add("src/main/resource/ib-models.xml");
       mappingXMLs.add("src/main/resource/view-insuranceCustomer.xml");
       mappingXMLs.add("src/main/resource/view-claim.xml");
       mappingXMLs.add("src/main/resource/view-policy.xml");
       mappingXMLs.add("src/main/resource/view-insuranceCustomerServe.xml");
       Resource resource = new Resource();
       resource.setEntityXMLPaths(mappingXMLs);
       resource.setComponentName("insurance");
       
       FileUtils.cleanFolder("src-gen");
       JPAGenerator generator = new JPAGenerator();
       generator.generater(resource);

       SATFaceGenerator faceGenerator = new SATFaceGenerator(resource);
       faceGenerator.generater();
	}

}
