package com.mocha.cooperate.gen;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class JPAGenerateSchema {
	
	public static void main(String[] args) throws IOException {
		ApplicationContext context = new FileSystemXmlApplicationContext("src/main/webapp/WEB-INF/applicationContext.xml");
		EntityManagerFactory emf = context.getBean(EntityManagerFactory.class);
		EntityManager em = emf.createEntityManager();
		em.close();
		
		processDDL("createDDL.jdbc", "src-gen/ddl.sql");
		//processDDL("dropDDL.jdbc", "drop-ddl.sql");
	}

	private static void processDDL(String inputFileName, String outputFileName) throws FileNotFoundException, IOException {
//		Map<String, String> cache = new HashMap<String, String>();
		List<String> cache = new ArrayList<String>();
		BufferedReader reader = new BufferedReader(new FileReader(inputFileName));
		BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName));
		String strLine;
		while ((strLine = reader.readLine()) != null)   {
			if(!cache.contains(strLine)) {
				cache.add(strLine);
				//System.out.println (strLine);
				writer.write(strLine + ";");
				writer.newLine();
			}
		}
		reader.close();
		writer.close();
		new File(inputFileName).delete();
	}
}
