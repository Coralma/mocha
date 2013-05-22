/**
 * 
 */
package com.coral.foundation.md.hbm.impl;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.coral.foundation.md.AbstractGenerator;
import com.coral.foundation.md.IEntityGenerator;
import com.coral.foundation.md.model.Mocha;
import com.coral.foundation.md.model.Entity;
import com.coral.foundation.md.model.Property;

/**
 * @author coral.ma
 * 
 */
public class HEntityGenerator extends AbstractGenerator implements IEntityGenerator {

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
				HEntityTemplate entityTemplate = new HEntityTemplate();
//				String classContent = parseEntity(coral,entity);
				String classContent =entityTemplate.generate(coral, entity).toString();
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

	public String parseEntity(Mocha coral,Entity entity) {
		String packageName =  coral.getEntityPackage();
		String entityName = entity.getEntityName();
		StringBuffer sb = new StringBuffer();
	    sb.append("package " + packageName + endTag);
	    sb.append(spaceTag);
	    sb.append(importGenerator(entity,coral));
	    
	    sb.append(spaceTag);
	    sb.append("/**" + spaceTag);
	    sb.append(" * <p>Title: " + packageName + "." + entityName + "</p>"
	        + spaceTag);
	    sb.append(" * <p>Description: " + entityName + " entity </p>" + spaceTag);
	    sb.append(" * <p>Copyright: Copyright (c) 2011 </p>" + spaceTag);
	    sb.append(" * <p>Company: Coral Co., Ltd. </p>" + spaceTag);
	    sb.append(" * <p>Create Time: "
	        + new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date())
	        + " </p>" + spaceTag);
	    sb.append(" */" + spaceTag);
	    sb.append("public class " + entityName + " implements java.io.Serializable " + braceStartTag);
	    sb.append(spaceTag);
	    // property declare
	    for(Property property : entity.getProperties()) {
	    	String type = validType(property);
	    	if("Date".equals(type)) {
	    		sb.append(tabTag + "private " + type + os + property.getPropertyName() +" = new Date()" + endTag);
	    	} else {
	    		sb.append(tabTag + "private " + type + os + property.getPropertyName() + endTag);
	    	}
	    }
	    sb.append(spaceTag);
	    for(Property property : entity.getProperties()) {
	    	String type = validType(property);
	    	sb.append(tabTag + "public " + type + os + getter(property.getPropertyName()) +  braceStartTag);
	    	sb.append(tabTag2 + "return " + property.getPropertyName() + endTag);
	    	sb.append(tabTag + braceEndTag);
	    	sb.append(tabTag + "public void " + setter(type,property.getPropertyName()) +  braceStartTag);
	    	sb.append(tabTag2 + setterContent(property.getPropertyName()) + endTag);
	    	sb.append(tabTag + braceEndTag);
	    	sb.append(spaceTag);
	    }
	    sb.append(braceEndTag);
		return sb.toString();
	}
	
//	/** test case. */
//	public static void main(String[] args) {
//	    try {
//	      EntityXmlParser mappingXmlParser = new EntityXmlParser();
//	      List<Coral> coralList = mappingXmlParser.parseMappingXml();
//	      // generate entity
//	      EntityGenerator entityGenerator = new EntityGenerator(coralList.get(0));
//	      entityGenerator.generater();
//	    } catch (Exception e) {
//	      e.printStackTrace();
//	    }
//	  }
}
