/**
 * 
 */
package com.coral.vaadin.gen;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.coral.foundation.md.IFaceGenerator;
import com.coral.foundation.md.model.Mocha;
import com.coral.foundation.md.model.Resource;
import com.coral.foundation.md.model.parser.EntityXmlParser;

public abstract class AbstractFaceGenerator implements IFaceGenerator {

	protected String srcPath;
	protected String srcGenPath;
	protected Resource resource;
	protected List<Mocha> mochas;
	protected String seperate = System.getProperty("file.separator");

	public AbstractFaceGenerator(Resource resource) {
		try {
			this.resource = resource;
			srcPath = resource.getSrcPath() + seperate;
			srcGenPath = resource.getEntityGenPath() + seperate;
			EntityXmlParser mappingXmlParser = new EntityXmlParser();
			mochas = mappingXmlParser.parseMappingXml(resource);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void fileGenerater(GenerateModel generateModel) {
		FileWriter fw = null;
		PrintWriter pw = null;
		String genPath = generateModel.getPath() + seperate;
		if (generateModel.getPkg() != null) {
			genPath = generateModel.getPath()
					+ generateModel.getPkg().replace(".", seperate) + seperate;
		}
		// These codes don't replace the generated file again after it
		// generated.
		if (generateModel.isIgnoreExisted()) {
			File existedFile = new File(genPath + generateModel.getClassName()
					+ "." + generateModel.getSuffix());
			if (existedFile.exists()) {
				return;
			}
		}
		try {
			File bulidFile = new File(genPath);
			if (!bulidFile.exists()) {
				bulidFile.mkdirs();
			}

			fw = new FileWriter(genPath + generateModel.getClassName() + "."
					+ generateModel.getSuffix());
			pw = new PrintWriter(fw);
			pw.println(generateModel.getClassContent());
			pw.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pw.close();
			try {
				fw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public class GenerateModel {
		String path;
		String className;
		String pkg;
		String classContent;
		String suffix = "java";
		boolean ignoreExisted = false;

		/**
		 * @return the path
		 */
		public String getPath() {
			return path;
		}

		/**
		 * @param path
		 *            the path to set
		 */
		public void setPath(String path) {
			this.path = path;
		}

		/**
		 * @return the className
		 */
		public String getClassName() {
			return className;
		}

		/**
		 * @param className
		 *            the className to set
		 */
		public void setClassName(String className) {
			this.className = className;
		}

		/**
		 * @return the pkg
		 */
		public String getPkg() {
			return pkg;
		}

		/**
		 * @param pkg
		 *            the pkg to set
		 */
		public void setPkg(String pkg) {
			this.pkg = pkg;
		}

		/**
		 * @return the classContent
		 */
		public String getClassContent() {
			return classContent;
		}

		/**
		 * @param classContent
		 *            the classContent to set
		 */
		public void setClassContent(String classContent) {
			this.classContent = classContent;
		}

		/**
		 * @return the ignoreExisted
		 */
		public boolean isIgnoreExisted() {
			return ignoreExisted;
		}

		/**
		 * @param ignoreExisted
		 *            the ignoreExisted to set
		 */
		public void setIgnoreExisted(boolean ignoreExisted) {
			this.ignoreExisted = ignoreExisted;
		}

		/**
		 * @return the suffix
		 */
		public String getSuffix() {
			return suffix;
		}

		/**
		 * @param suffix
		 *            the suffix to set
		 */
		public void setSuffix(String suffix) {
			this.suffix = suffix;
		}
	}

	/**
	 * @return the resource
	 */
	public Resource getResource() {
		return resource;
	}

	/**
	 * @param resource
	 *            the resource to set
	 */
	public void setResource(Resource resource) {
		this.resource = resource;
	}
}
