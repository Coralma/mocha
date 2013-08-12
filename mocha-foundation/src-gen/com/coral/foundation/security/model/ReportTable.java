package com.coral.foundation.security.model;
import java.util.*;
import java.math.BigDecimal;
import javax.persistence.*;
import com.coral.foundation.persistence.*;

/**
  * <p>Title: com.coral.foundation.security.model.ReportTable + "</p>
  * <p>Description: The ReportTable entity </p>
  */
@Entity(name = "ReportTable")
@Table(name = "T_REPORT_TABLE")
public class ReportTable extends JPABaseEntity {
	
	@Id()
	@Column (name = "REPORT_TABLE_ID")
	@GeneratedValue(generator="REPORTTABLEID_SEQ")
	@TableGenerator(name="REPORTTABLEID_SEQ", table="SEQUENCE", pkColumnName="SEQ_NAME", valueColumnName="SEQ_COUNT", allocationSize=1)
	private Long reportTableId;
	
	@Column(name = "TABLE_NAME" )
	private String tableName;
	
	
	@Column(name = "TABLE_LABEL" )
	private String tableLabel;
	
	
	@Column(name = "TYPE" )
	private String type;
	
	
	@OneToMany(targetEntity=ReportColumn.class, cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
	@JoinColumn(name="REPORT_TABLE_ID")
	private List<ReportColumn> reportColumns = new ArrayList<ReportColumn>();
	
	@OneToMany(targetEntity=ReportJoinTable.class, cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
	@JoinColumn(name="REPORT_TABLE_ID")
	private List<ReportJoinTable> reportJoinReportTableId = new ArrayList<ReportJoinTable>();
	
	@Column(name = "JOIN_TYPE" )
	private String joinType;
	
	
	@OneToMany(targetEntity=AppReport.class, cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
	@JoinColumn(name="REPORT_TABLE_ID")
	private List<AppReport> appReport = new ArrayList<AppReport>();
	

	public void setReportTableId (Long reportTableId) {
		this.reportTableId = reportTableId;
	} 
	public Long getReportTableId () {
		return reportTableId;
	}
	public void setTableName (String tableName) {
		this.tableName = tableName;
	} 
	public String getTableName () {
		return tableName;
	}
	public void setTableLabel (String tableLabel) {
		this.tableLabel = tableLabel;
	} 
	public String getTableLabel () {
		return tableLabel;
	}
	public void setType (String type) {
		this.type = type;
	} 
	public String getType () {
		return type;
	}
	public void setReportColumns (List<ReportColumn> reportColumns) {
		this.reportColumns = reportColumns;
	} 
	public List<ReportColumn> getReportColumns () {
		return reportColumns;
	}
	public void setReportJoinReportTableId (List<ReportJoinTable> reportJoinReportTableId) {
		this.reportJoinReportTableId = reportJoinReportTableId;
	} 
	public List<ReportJoinTable> getReportJoinReportTableId () {
		return reportJoinReportTableId;
	}
	public void setJoinType (String joinType) {
		this.joinType = joinType;
	} 
	public String getJoinType () {
		return joinType;
	}
	public void setAppReport (List<AppReport> appReport) {
		this.appReport = appReport;
	} 
	public List<AppReport> getAppReport () {
		return appReport;
	}

	public Long getID() {
		return getReportTableId();
	}
}

