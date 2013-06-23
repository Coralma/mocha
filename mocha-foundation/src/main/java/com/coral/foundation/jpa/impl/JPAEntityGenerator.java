/**
 * 
 */
package com.coral.foundation.jpa.impl;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import com.coral.foundation.jpa.gen.JPAEntityTemplate;
import com.coral.foundation.md.AbstractGenerator;
import com.coral.foundation.md.IEntityGenerator;
import com.coral.foundation.md.model.Mocha;
import com.coral.foundation.md.model.Entity;

/**
 * @author coral.ma
 * 
 */
@Deprecated
public class JPAEntityGenerator extends AbstractGenerator implements IEntityGenerator {

//	private Coral coral;
	String srcPath;
	public boolean domainSeperate = false;

	public boolean isDomainSeperate() {
		return domainSeperate;
	}

	public void setDomainSeperate(boolean domainSeperate) {
		this.domainSeperate = domainSeperate;
	}

	public String getSrcPath() {
		return srcPath;
	}

	public void setSrcPath(String srcPath) {
		this.srcPath = srcPath;
	}

	/**
	 * Generate business object handler java class by parsing xml.
	 * 
	 * @throws Exception
	 */
	public void generater(Mocha coral) throws Exception {
		if (coral == null) {
			throw new Exception(
					"Generate entity error! Can not load data from XML.");
		}
//		String path = default_entity_path;
		String entityPackage = coral.getEntityPackage();
		
		for (Entity entity : coral.getEntityList()) {
			FileWriter fw = null;
			PrintWriter pw = null;
			String genPath = "";
			try {
				if(entityPackage != null) {
					if(domainSeperate) {
						genPath = srcPath + "\\" + coral.getName() + "\\" + entityPackage.replace(".", "\\") + "\\";
					} else {
						genPath = srcPath + "\\" + entityPackage.replace(".", "\\") + "\\";
					}
					File bulidFile = new File(genPath);
					if (!bulidFile.exists()) {
						bulidFile.mkdirs();
					}
				}
				JPAEntityTemplate entityTemplate = new JPAEntityTemplate();
//				String classContent = parseEntity(coral,entity);
				entityTemplate.init(coral, entity);
				String classContent =entityTemplate.generate().toString();
				fw = new FileWriter(genPath + entity.getEntityName() + ".java");
				pw = new PrintWriter(fw);
				pw.println(classContent);
				pw.flush();
			} catch (IOException e) {
				throw e;
			} finally {
				pw.close();
				fw.close();
			}
		}
	}
}
