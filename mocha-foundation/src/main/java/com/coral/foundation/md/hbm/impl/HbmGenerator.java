package com.coral.foundation.md.hbm.impl;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Properties;

import com.coral.foundation.md.AbstractGenerator;
import com.coral.foundation.md.IHbmGenerator;
import com.coral.foundation.md.model.Mocha;
import com.coral.foundation.md.model.Entity;
import com.coral.foundation.md.model.Property;

public class HbmGenerator extends AbstractGenerator implements IHbmGenerator {

	private HDaoTemplate daoTemplate = new HDaoTemplate();
	private HbmTemplate hbmTemplate = new HbmTemplate();

	public void generater(List<Mocha> corals, Properties properties) throws Exception {
		if (corals == null) {
			throw new Exception("Generate entity error! Can not load data from XML.");
		}
		if(properties != null) {
			generateCfg(properties);
		}
		generateHbm(corals);
		generateHbmDAO(corals);
		generateHbmDAOBean(corals);
	}

	/**
	 * Generate business object handler java class by parsing xml.
	 * 
	 * @throws Exception
	 */
	public void generateCfg(Properties properties) throws Exception {
		FileWriter fw = null;
		PrintWriter pw = null;
		String genPath = "";
		try {
			genPath = srcPath + "\\";
			File bulidFile = new File(genPath);
			if (!bulidFile.exists()) {
				bulidFile.mkdirs();
			}

			String classContent = hbmTemplate.generateCfg(properties).toString();
			fw = new FileWriter(genPath + "hibernate.cfg.xml");
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

	public void generateHbm(List<Mocha> corals) throws Exception {
		FileWriter fw = null;
		PrintWriter pw = null;
		String genPath = "";
		try {
			genPath = srcPath + "\\";
			File bulidFile = new File(genPath);
			if (!bulidFile.exists()) {
				bulidFile.mkdirs();
			}

			String classContent = hbmTemplate.generateHbm(corals).toString();
			String fileName = "hibernate.hbm.xml";
			if(componentName != null) {
				fileName = componentName + ".hbm.xml";
			}
			fw = new FileWriter(genPath + fileName);
			pw = new PrintWriter(fw);
			pw.println(classContent);
			pw.flush();
		} catch (Exception e) {
			throw e;
		} finally {
			pw.close();
			fw.close();
		}
	}

	public void generateHbmDAO(List<Mocha> corals) throws Exception {
		for (Mocha coral : corals) {
			String daoPackage = coral.getDaoImplPackage();
			for (Entity entity : coral.getEntityList()) {
				FileWriter fw = null;
				PrintWriter pw = null;
				String genPath = "";
				try {
					genPath = srcPath + "\\" + daoPackage.replace(".", "\\") + "\\";
					File bulidFile = new File(genPath);
					if (!bulidFile.exists()) {
						bulidFile.mkdirs();
					}
					String classContent = daoTemplate.generate(coral, entity).toString();
					String daoName= entity.getEntityName()+"DAO";
					fw = new FileWriter(genPath + daoName + ".java");
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

	public void generateHbmDAOBean(List<Mocha> corals) throws Exception {
		FileWriter fw = null;
		PrintWriter pw = null;
		String genPath = "";
		try {
			genPath = srcPath + "\\";
			File bulidFile = new File(genPath);
			if (!bulidFile.exists()) {
				bulidFile.mkdirs();
			}
			HDaoSpringTemplate daoSpringTemplate = new HDaoSpringTemplate();
			String classContent = daoSpringTemplate.generate(corals).toString();
			String fileName = "daoBeanContext.xml";
			if(componentName != null) {
				fileName = componentName + "BeanContext.xml";
			}
			fw = new FileWriter(genPath + fileName);
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

	String srcPath;
	public boolean domainSeperate = false;
	public Properties properties;
	public String componentName;

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

	@Deprecated
	public String generateCfgFile() {
		String driverClass = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/user_role";
		String userName = "user";
		String password = "Password1";
		String poolSize = "1";
		String dialect = "org.hibernate.dialect.MySQLDialect";
		String showSql = "true";
		String formatSql = "true";
		String hbm2ddlAuto = "update";
		StringBuffer sb = new StringBuffer();
		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + spaceTag);
		sb.append("<!DOCTYPE hibernate-configuration PUBLIC");
		sb.append(" \"-//Hibernate/Hibernate Configuration DTD 3.0//EN\"");
		sb.append(" \"http://hibernate.sourceforge.net/hibernate-configuration-3.0.dtd\">" + spaceTag);
		sb.append("<hibernate-configuration>" + spaceTag);
		sb.append(tabTag + "<session-factory>" + spaceTag);
		sb.append(tabTag2 + "<property name=\"hibernate.connection.driver_class\">" + driverClass + "</property>"
				+ spaceTag);
		sb.append(tabTag2 + "<property name=\"hibernate.connection.url\">" + url + "</property>" + spaceTag);
		sb.append(tabTag2 + "<property name=\"hibernate.connection.username\">" + userName + "</property>" + spaceTag);
		sb.append(tabTag2 + "<property name=\"connection.password\">" + password + "</property>" + spaceTag);
		sb.append(tabTag2 + "<property name=\"connection.pool_size\">" + poolSize + "</property>" + spaceTag);
		sb.append(tabTag2 + "<property name=\"hibernate.dialect\">" + dialect + "</property>" + spaceTag);
		sb.append(tabTag2 + "<property name=\"show_sql\">" + showSql + "</property>" + spaceTag);
		sb.append(tabTag2 + "<property name=\"format_sql\">" + formatSql + "</property>" + spaceTag);
		sb.append(tabTag2 + "<property name=\"hbm2ddl.auto\">" + hbm2ddlAuto + "</property>" + spaceTag);
		sb.append(tabTag2 + "<property name=\"hibernate.connection.useUnicode\">true</property>" + spaceTag);
		sb.append(tabTag2 + "<property name=\"hibernate.connection.characterEncoding\">UTF-8</property>" + spaceTag);
		sb.append(tabTag2 + "<property name=\"current_session_context_class\">managed</property>" + spaceTag);
		sb.append(spaceTag);
		sb.append(tabTag2 + "<mapping resource=\"hibernate.hbm.xml\"/>" + spaceTag);
		sb.append(tabTag + "</session-factory>" + spaceTag);
		sb.append("</hibernate-configuration>" + spaceTag);
		return sb.toString();
	}

	@Deprecated
//	public String generateHbmFile(List<Coral> coralList) {
//		String schema = "user_role";
//		StringBuffer sb = new StringBuffer();
//		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + spaceTag);
//		sb.append("<!DOCTYPE hibernate-mapping PUBLIC");
//		sb.append(" \"-//Hibernate/Hibernate Mapping DTD 3.0//EN\"");
//		sb.append(" \"http://hibernate.sourceforge.net/hibernate-mapping-3.0.dtd\">" + spaceTag);
//		sb.append("<hibernate-mapping auto-import=\"false\" schema=\"" + schema + "\">" + spaceTag);
//		for (Coral coral : coralList) {
//			List<Entity> entities = coral.getEntityList();
//			for (Entity entity : entities) {
//				// parse class
//				String entityClass = coral.getEntityPackage() + "" + entity.getEntityName();
//				String tableName = genDBName(entity.getEntityName());
//				sb.append("<class name=\"" + entityClass + "\" table=\"" + tableName + "\">");
//				// parse property
//				List<Property> properties = entity.getProperties();
//				for (Property p : properties) {
//					String pn = p.getPropertyName(); // columnName
//					String cn = genDBName(pn);
//					String type = p.getType();
//					// check if a column is a PK
//					if (isPK(p)) {
//						sb.append("<id name=\"" + pn + "\" column=\"" + cn + "\" type=\"java.lang.Long\">");
//						sb.append("<generator class=\"native\"/>");
//						sb.append("</id>");
//					} else {
//						// not PK but it should be a table column.
//						if (isColumn(p)) {
//							if ("String".equals(type)) {
//								sb.append("<property name=\"" + pn + "\" column=\"" + cn
//										+ "\" not-null=\"false\" type=\"java.lang.String\"/>");
//							} else if ("Integer".equals(type)) {
//								sb.append("<property name=\"" + pn + "\" column=\"" + cn
//										+ "\" not-null=\"false\" type=\"java.lang.Integer\"/>");
//							} else if ("Long".equals(type)) {
//								sb.append("<property name=\"" + pn + "\" column=\"" + cn
//										+ "\" not-null=\"false\" type=\"java.lang.Long\"/>");
//							} else if ("Date".equals(type)) {
//								sb.append("<property name=\"" + pn + "\" column=\"" + cn
//										+ "\" not-null=\"false\" type=\"timestamp\"/>");
//							}
//						} else {
//							// not PK not Column but it a reference type
//							if (p.getRef() != null) {
//
//							}
//						}
//					}
//				}
//			}
//		}
//		sb.append("</hibernate-mapping>" + spaceTag);
//		return null;
//	}

	/**
	 * @return the properties
	 */
	public Properties getProperties() {
		return properties;
	}

	/**
	 * @param properties
	 *            the properties to set
	 */
	public void setProperties(Properties properties) {
		this.properties = properties;
	}

	/**
	 * @return the componentName
	 */
	public String getComponentName() {
		return componentName;
	}

	/**
	 * @param componentName the componentName to set
	 */
	public void setComponentName(String componentName) {
		this.componentName = componentName;
	}

}
