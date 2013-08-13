/**
 * 
 */
package com.coral.foundation.jpa.impl;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import org.springframework.core.io.Resource;

import com.coral.foundation.md.AbstractGenerator;
import com.coral.foundation.spring.bean.SpringContextUtils;

/**
 * @author Coral
 *
 */
public class CodeTableScriptGenerator extends AbstractGenerator{

	public void generate() throws Exception {
		Resource[] codeTableResources = SpringContextUtils.getApplicationContext().getResources("classpath*:codeTable.sql");
		StringBuilder sb = new StringBuilder();
		for(Resource codeTableResource : codeTableResources) {
			InputStream in = new FileInputStream(codeTableResource.getFile());
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));   
			String line = null;   
	        try {   
	            while ((line = reader.readLine()) != null) {   
	                sb.append(line + "\n");   
	            }   
	
	        } catch (IOException e) {   
	            e.printStackTrace();   
	        } finally {   
	            try {   
	                in.close();   
	            } catch (IOException e) {   
	                e.printStackTrace();   
	            }
	        }
		}
		// generate inner-codetable.sql
		FileWriter fw = null;
		PrintWriter pw = null;
		try {
			fw = new FileWriter("src-gen/all-codetable.sql");
			pw = new PrintWriter(fw);
			pw.println(sb);
			pw.flush();
		} catch (IOException e) {
			throw e;
		} finally {
			pw.close();
			fw.close();
		}
		System.out.println(sb);
	}
}
