/**
 * 
 */
package com.coral.foundation.jpa.impl;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.List;

import com.coral.foundation.jpa.gen.JPADaoSpringContextTemplate;
import com.coral.foundation.jpa.gen.JPADaoTemplate;
import com.coral.foundation.jpa.gen.JPAEntityTemplate;
import com.coral.foundation.md.IGenerator;
import com.coral.foundation.md.model.Mocha;
import com.coral.foundation.md.model.Entity;
import com.coral.foundation.md.model.Resource;
import com.coral.foundation.md.model.helper.VGenHelper;
import com.coral.foundation.md.model.parser.EntityXmlParser;

/**
 * @author Coral.Ma
 *
 */
public class JPAGenerator implements IGenerator {

	List<Mocha> coralList;
	String srcPath;
	String srcGenPath;
	String srcDaoPath;
	protected String seperate = System.getProperty("file.separator");
	
	String contextName = "generatedDaoContext.xml";
	
	public void generater(Resource resource) throws Exception {
		try {
			srcPath = resource.getSrcPath() + seperate;
			srcGenPath = resource.getEntityGenPath()+ seperate;
			srcDaoPath = resource.getDaoGenPath()+seperate;
			EntityXmlParser mappingXmlParser = new EntityXmlParser();
			coralList = mappingXmlParser.parseMappingXml(resource);
			// generate entity
			for(Mocha coral:coralList) {
				for(Entity entity : coral.getEntityList()) {
					entityGenerate(coral, entity);
					daoGenerate(coral,entity);
				}
			}
			daoSpringContextGenerate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void entityGenerate(Mocha coral, Entity entity) throws Exception {
		JPAEntityTemplate entityTemplate = new JPAEntityTemplate();
		entityTemplate.init(coral, entity);
		fileGenerater(srcGenPath, entity.getEntityName(), coral.getEntityPackage(), entityTemplate.generate().toString(), false);
	}
	
	public void daoGenerate(Mocha coral, Entity entity) throws Exception {
		JPADaoTemplate daoTemplate = new JPADaoTemplate();
		daoTemplate.init(entity, coralList);
//		fileGenerater(srcDaoPath, VGenHelper.genDaoIntf(entity.getEntityName()), coral.getDaoIntfPackage(), daoTemplate.generateInterface().toString(), true);
		fileGenerater(srcDaoPath, VGenHelper.genDao(entity.getEntityName()), coral.getDaoImplPackage(), daoTemplate.generateImplement().toString(), true);
	}
	
	public void daoSpringContextGenerate() throws Exception {
		JPADaoSpringContextTemplate spring = new JPADaoSpringContextTemplate();
		spring.init(coralList);
		fileGenerater(srcDaoPath, contextName, "", spring.generateSpringContextXml().toString(), false);
	}
	
	public void fileGenerater(String path, String className, String pkg, String classContent,boolean ignoreExisted) throws Exception {
		FileWriter fw = null;
		PrintWriter pw = null;
		String genPath ="";
		if(pkg != null) {
			genPath = path + pkg.replace(".", seperate) + seperate;
		}
		// These codes don't replace the generated file again after it generated.
		if(ignoreExisted) {
			File existedFile = new File(genPath + className + ".java");
			if (existedFile.exists()) {
				return;
			}
		}
		try{
			File bulidFile = new File(genPath);
			if (!bulidFile.exists()) {
				bulidFile.mkdirs();
			}
			if(className.endsWith(".xml")) {
				fw = new FileWriter(genPath + className);
			} else {
				fw = new FileWriter(genPath + className + ".java");
			}
			pw = new PrintWriter(fw);
			pw.println(classContent);
			pw.flush();
			System.out.println("Generate " + genPath + className);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			pw.close();
			fw.close();
		}
	}

}
