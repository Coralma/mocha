/**
 * 
 */
package com.coral.foundation.md.model;

import java.io.Serializable;

/**
 * @author Coral
 *
 */
public class CodeTableValue implements Serializable {
	
	public String codeTableName;
	public String language;
	public String datas;
	/**
	 * @return the language
	 */
	public String getLanguage() {
		return language;
	}
	/**
	 * @param language the language to set
	 */
	public void setLanguage(String language) {
		this.language = language;
	}
	/**
	 * @return the datas
	 */
	public String getDatas() {
		return datas;
	}
	/**
	 * @param datas the datas to set
	 */
	public void setDatas(String datas) {
		this.datas = datas;
	}
	/**
	 * @return the codeTableName
	 */
	public String getCodeTableName() {
		return codeTableName;
	}
	/**
	 * @param codeTableName the codeTableName to set
	 */
	public void setCodeTableName(String codeTableName) {
		this.codeTableName = codeTableName;
	}

}
