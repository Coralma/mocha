package com.mocha.template.general.widget.vo;

import java.util.List;

import com.google.common.collect.Lists;

public class GeneralTableVO {
	
	public String caption;
	public List<String> tableHeadLabels = Lists.newArrayList();
	public List<String> tableHeadVariables = Lists.newArrayList();
	public List<Object> datas = Lists.newArrayList();
	public Class clazz;

	/**
	 * @return the tableHeadLabels
	 */
	public List<String> getTableHeadLabels() {
		return tableHeadLabels;
	}
	/**
	 * @param tableHeadLabels the tableHeadLabels to set
	 */
	public void setTableHeadLabels(List<String> tableHeadLabels) {
		this.tableHeadLabels = tableHeadLabels;
	}
	/**
	 * @return the tableHeadVariables
	 */
	public List<String> getTableHeadVariables() {
		return tableHeadVariables;
	}
	/**
	 * @param tableHeadVariables the tableHeadVariables to set
	 */
	public void setTableHeadVariables(List<String> tableHeadVariables) {
		this.tableHeadVariables = tableHeadVariables;
	}
	/**
	 * @return the caption
	 */
	public String getCaption() {
		return caption;
	}
	/**
	 * @param caption the caption to set
	 */
	public void setCaption(String caption) {
		this.caption = caption;
	}
	/**
	 * @return the clazz
	 */
	public Class getClazz() {
		return clazz;
	}
	/**
	 * @param clazz the clazz to set
	 */
	public void setClazz(Class clazz) {
		this.clazz = clazz;
	}
	/**
	 * @param datas the datas to set
	 */
	public void setDatas(List<Object> datas) {
		this.datas = datas;
	}
	
	public void addData(Object data) {
		this.datas.add(data);
	}
	/**
	 * @return the datas
	 */
	public List<Object> getDatas() {
		return datas;
	}

}
