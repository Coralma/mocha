package com.coral.foundation.md.model;

import java.util.List;

import com.google.common.collect.Lists;

public class ReportTableDef {

	public Entity entity;
	public String tableName;
	public String name;
	public String label;
	public List<ReportColumnDef> columns = Lists.newArrayList();
	public List<ReportJoinDef> joinDefs = Lists.newArrayList();
	/**
	 * @return the entity
	 */
	public Entity getEntity() {
		return entity;
	}

	/**
	 * @param entity the entity to set
	 */
	public void setEntity(Entity entity) {
		this.entity = entity;
	}

	/**
	 * @return the columns
	 */
	public List<ReportColumnDef> getColumns() {
		return columns;
	}

	/**
	 * @param columns the columns to set
	 */
	public void setColumns(List<ReportColumnDef> columns) {
		this.columns = columns;
	}
	
	public void addColumn(ReportColumnDef column) {
		this.columns.add(column);
	}

	/**
	 * @return the tableName
	 */
	public String getTableName() {
		return tableName;
	}

	/**
	 * @param tableName the tableName to set
	 */
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

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
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * @param label the label to set
	 */
	public void setLabel(String label) {
		this.label = label;
	}

	/**
	 * @return the joinDefs
	 */
	public List<ReportJoinDef> getJoinDefs() {
		return joinDefs;
	}

	/**
	 * @param joinDefs the joinDefs to set
	 */
	public void setJoinDefs(List<ReportJoinDef> joinDefs) {
		this.joinDefs = joinDefs;
	}
	
	public void addJoinDefs(ReportJoinDef joinDef) {
		this.joinDefs.add(joinDef);
	}
}
