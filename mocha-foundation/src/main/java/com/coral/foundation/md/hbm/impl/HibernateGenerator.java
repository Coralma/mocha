/**
 * 
 */
package com.coral.foundation.md.hbm.impl;

import java.util.List;

import com.coral.foundation.md.IGenerator;
import com.coral.foundation.md.model.Mocha;
import com.coral.foundation.md.model.Resource;
import com.coral.foundation.md.model.parser.EntityXmlParser;

/**
 * @author Coral.Ma
 *
 */
public class HibernateGenerator implements IGenerator {

	public void generater(Resource resource) throws Exception {
		try {
			EntityXmlParser mappingXmlParser = new EntityXmlParser();
			List<Mocha> coralList = mappingXmlParser.parseMappingXml(resource);
			// generate entity
			for (Mocha coral : coralList) {
				// generate entity
				HEntityGenerator entityGenerator = new HEntityGenerator();
				entityGenerator.setSrcPath(resource.getEntityGenPath());
				entityGenerator.setDomainSeperate(resource.isDomainSeperate());
				entityGenerator.generater(coral);
				// generate hbm
			}
			HbmGenerator hbmGenerator = new HbmGenerator();
			hbmGenerator.setSrcPath(resource.getEntityGenPath());
			hbmGenerator.setDomainSeperate(resource.isDomainSeperate());
			hbmGenerator.setComponentName(resource.getComponentName());
			hbmGenerator.generater(coralList, resource.getProperties());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
