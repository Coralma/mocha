/**
 * 
 */
package com.coral.foundation.utils;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.coral.foundation.security.basic.dao.CodeTableDao;
import com.coral.foundation.security.basic.dao.CodeTableValueDao;
import com.coral.foundation.security.model.CodeTable;
import com.coral.foundation.security.model.CodeTableValue;
import com.coral.foundation.spring.bean.SpringContextUtils;
import com.google.common.collect.Maps;

/**
 * @author Coral.Ma
 *
 */
public class CodeTableUtils {
	
	public static Logger log =LoggerFactory.getLogger(CodeTableUtils.class);
	public static final String COMMON_KEY = "common"; 
	public static CodeTableCache codeTableCache = new CodeTableCache();
	public static CodeTableDao codeTableDao = SpringContextUtils.getBean(CodeTableDao.class);
	public static CodeTableValueDao codeTableValueDao = SpringContextUtils.getBean(CodeTableValueDao.class);
	
	@Deprecated
	public static CodeTable get(Long accountId, Long appId, String codeTableName) {
		String key = genKey(accountId, appId);
		CodeTable codeTable = codeTableCache.get(key, codeTableName);
		if(codeTable == null) {
			codeTable = codeTableDao.findByName(codeTableName);
			codeTableCache.put(key, codeTableName, codeTable);
		}
		return codeTable;
	}
	
	public static CodeTable get(String codeTableName) {
		CodeTable codeTable = codeTableCache.get(COMMON_KEY, codeTableName);
		if(codeTable == null) {
			codeTable = codeTableDao.findByName(codeTableName);
			if(codeTable != null) {
				if(codeTable.getCodeTableValues().size() == 0) {
					List<CodeTableValue> codeTableValues = codeTableValueDao.findByName(codeTableName);
					codeTable.setCodeTableValues(codeTableValues);
					codeTableDao.persist(codeTable);
				}
				codeTableCache.put(COMMON_KEY, codeTableName, codeTable);
			} else {
				String errorMessage = "There is not define the code table :" + codeTableName;
				log.error(errorMessage);
				throw new RuntimeException(errorMessage);
			}
		}
		return codeTable;
	}
	
	public static LinkedHashMap<String,String> parse(CodeTable codeTable, String local) {
		LinkedHashMap<String,String> codeTableValues = Maps.newLinkedHashMap();
		String[] ids = codeTable.getIds().split(";");
		String[] values = new String[0];
		for(CodeTableValue codeTableValue : codeTable.getCodeTableValues()) {
			if(codeTableValue.getLanguage().equals(local)) {
				values = codeTableValue.getDatas().split(";");
			}
		}
		if(!(ids.length == values.length)) {
			String errorMessage = "The size of id and value is different. Please check the codeTable define : " + codeTable.getName();
			log.error(errorMessage);
			throw new RuntimeException(errorMessage);
		}
		for(int i=0;i <values.length; i++) {
			String id = ids[i].trim();
			String value = values[i].trim(); 
			codeTableValues.put(id,value);
		}
		return codeTableValues;
	}
	
	private static String genKey(Long accountId, Long appId) {
		return accountId + "-" + appId;
	}
	
	public static class CodeTableCache {
		private Map<String, Map<String,CodeTable>> cache = Maps.newHashMap(); 
		
		public CodeTable get(String key, String codeTableName) {
			Map<String,CodeTable> codeTableMap = cache.get(key);
			if(codeTableMap != null) {
				CodeTable codeTable = codeTableMap.get(codeTableName);
				return codeTable;
			}
			return null;
		}
		
		public void put(String key, String codeTableName, CodeTable codeTable) {
			Map<String,CodeTable> codeTableMap = cache.get(key);
			if(codeTableMap == null) {
				codeTableMap = Maps.newHashMap();
			}
			codeTableMap.put(codeTableName, codeTable);
			cache.put(key, codeTableMap);
		}
	}

}
