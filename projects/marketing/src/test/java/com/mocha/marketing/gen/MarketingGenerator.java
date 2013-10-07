/**
 * 
 */
package com.mocha.marketing.gen;

import java.util.ArrayList;
import java.util.List;

import com.coral.foundation.jpa.impl.JPAGenerator;
import com.coral.foundation.md.model.Resource;
import com.coral.foundation.utils.FileUtils;
import com.coral.vaadin.gen.SATFaceGenerator;
import com.mocha.report.CrmReportPresenter;

/**
 * @author Coral
 *
 */
public class MarketingGenerator {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
       List<String> mappingXMLs = new ArrayList<String>();
       mappingXMLs.add("src/main/resource/model.xml");
       mappingXMLs.add("src/main/resource/view-customer.xml");
       mappingXMLs.add("src/main/resource/view-campaign.xml");
       mappingXMLs.add("src/main/resource/view-server.xml");
       mappingXMLs.add("src/main/resource/app-main-define.xml");
       mappingXMLs.add("src/main/resource/app-codetable.xml");
       Resource resource = new Resource();
       resource.setEntityXMLPaths(mappingXMLs);
       resource.setComponentName("crm");
       
//       FileUtils.cleanFolder("src-gen");
       JPAGenerator generator = new JPAGenerator();
       generator.generater(resource);

//       SATFaceGenerator faceGenerator = new SATFaceGenerator(resource);
//       faceGenerator.generater();
	}

}
