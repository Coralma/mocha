/**
 * 
 */
package com.coral.vaadin.util;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.coral.foundation.md.model.CodeTable;
import com.coral.foundation.md.model.Mocha;
import com.coral.foundation.utils.StrUtils;
import com.coral.vaadin.resource.ModelCenter;
import com.google.common.collect.Maps;

/**
 * @author Coral
 *
 */
@Deprecated
public class CodeTableFactory {
	
	private static CodeTableFactory codeTableFactory = new CodeTableFactory();
	private static Map<String,String[]> codeTables;
	private static Logger logger=LoggerFactory.getLogger(CodeTableFactory.class);
	
//	public static CodeTableFactory getcodeTableFactory() {
//		return codeTableFactory;
//	}
	public static CodeTableFactory getFactory() {
		try {
			if(codeTables == null) {
				codeTables =Maps.newHashMap();
				codeTableFactory.init(ModelCenter.getModel());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return codeTableFactory;
	}
	public void init(List<Mocha> corals) {
		for(Mocha coral : corals) {
			List<CodeTable> codeTableList = coral.getCodeTableList();
			for(CodeTable code : codeTableList) {
//				String type = code.getType();
//				String name = code.getName();
//				String data = code.getData();
//				if(!StrUtils.isEmpty(type)) {
//					codeTables.put(name, getDBCodeTable(data));
//				} else {
//					codeTables.put(name, getDefaultCodeTable(data));
//				}
			}
		}
	}
	
	public String[] getDefaultCodeTable(String data) {
//		return StrUtils.cleanString(data).split(",");
		return data.split(",");
	}
	
	public String[] getDBCodeTable(String data) {
		//TODO add DB solution.
		return null;
	}
	
	public String[] getCodeTable(String name) {
		String[] codes = codeTables.get(name);
		if(codes == null) {
			logger.error("The " + name +" code table doesn't existed, please check your model definition.");
		}
		return codes;
	}
}
