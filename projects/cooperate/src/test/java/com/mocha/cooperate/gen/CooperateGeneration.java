package com.mocha.cooperate.gen;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.coral.foundation.jpa.impl.CodeTableScriptGenerator;
import com.coral.foundation.jpa.impl.JPAGenerator;
import com.coral.foundation.md.model.Resource;
import com.coral.vaadin.gen.VaadinFaceGenerator;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "applicationContext.xml")
public class CooperateGeneration {

	@Test
	public void testTaobaoData() throws Exception {
		List<String> mappingXMLs = new ArrayList<String>();
		mappingXMLs.add("define/widgetModel.xml");
		Resource resource = new Resource();
		resource.setEntityXMLPaths(mappingXMLs);
		resource.setComponentName("cooperate");

		JPAGenerator generator = new JPAGenerator();
		generator.generater(resource);
		VaadinFaceGenerator vaadinGen = new VaadinFaceGenerator(resource);
		vaadinGen.generater();

		CodeTableScriptGenerator ctGen = new CodeTableScriptGenerator();
		ctGen.generate();
	}
}
