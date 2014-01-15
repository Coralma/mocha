package com.mocha.hr.gen;

import java.util.ArrayList;
import java.util.List;

import com.coral.foundation.jpa.impl.JPAGenerator;
import com.coral.foundation.md.model.Resource;
import com.coral.foundation.utils.FileUtils;
import com.coral.vaadin.gen.SATFaceGenerator;

public class HumanResourceGenerator {

	public static void main(String[] args) throws Exception {
       List<String> mappingXMLs = new ArrayList<String>();
       mappingXMLs.add("src/main/resource/metadata/hr-models.xml");
       mappingXMLs.add("src/main/resource/metadata/view-leave.xml");
       Resource resource = new Resource();
       resource.setEntityXMLPaths(mappingXMLs);
       resource.setComponentName("humanResource");
       
       FileUtils.cleanFolder("src-gen");
       JPAGenerator generator = new JPAGenerator();
       generator.generater(resource);
       
       // no face at all
//       SATFaceGenerator faceGenerator = new SATFaceGenerator(resource);
//       faceGenerator.generater();
	}
}