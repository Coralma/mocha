/**
 * 
 */
package com.coral.foundation.md.model;

import java.io.Serializable;
import java.util.List;

import com.google.common.collect.Lists;

/**
 * @author Coral
 *
 */
public class CodeTable implements Serializable {

	public String name;
	public String ids;
	public List<CodeTableValue> values = Lists.newArrayList(); 
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the ids
	 */
	public String getIds() {
		return ids;
	}
	/**
	 * @param ids the ids to set
	 */
	public void setIds(String ids) {
		this.ids = ids;
	}
	/**
	 * @return the values
	 */
	public List<CodeTableValue> getValues() {
		return values;
	}
	/**
	 * @param values the values to set
	 */
	public void setValues(List<CodeTableValue> values) {
		this.values = values;
	}
	
	public void addValue(CodeTableValue value) {
		this.values.add(value);
	}
}
