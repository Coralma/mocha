package com.coral.foundation.security.model;
import java.util.*;
import java.math.BigDecimal;
import javax.persistence.*;
import com.coral.foundation.model.BaseEntity;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
  * <p>Title: com.coral.foundation.security.model.ReportTable + "</p>
  * <p>Description: The ReportTable entity </p>
  */
@Entity(name = "ReportTable")
@Table(name = "T_REPORT_TABLE")
public class ReportTable extends BaseEntity {
	
	@Id()
	@Column (name = "REPORT_TABLE_ID")
	@GeneratedValue(strategy = GenerationType. AUTO)
	private Long reportTableId;
	
	@Basic(optional = true)
	@Column(name = "TABLE_NAME" )
	private String tableName;
	
	
	@Basic(optional = true)
	@Column(name = "TYPE" )
	private String type;
	
	
	@OneToMany(cascade = { CascadeType.ALL }, targetEntity = ReportColumn.class, fetch=FetchType.EAGER)
	@Fetch(FetchMode.SUBSELECT)
	@JoinColumn(name="REPORT_TABLE_ID")
	private List<ReportColumn> reportColumns = new ArrayList<ReportColumn>();
	
	@OneToMany(cascade = { CascadeType.ALL }, targetEntity = ReportJoinTable.class, fetch=FetchType.EAGER)
	@Fetch(FetchMode.SUBSELECT)
	@JoinColumn(name="REPORT_TABLE_ID")
	private List<ReportJoinTable> reportJoinReportTableId = new ArrayList<ReportJoinTable>();
	
	@Basic(optional = true)
	@Column(name = "JOIN_TYPE" )
	private String joinType;
	
	
	@OneToMany(cascade = { CascadeType.ALL }, targetEntity = AppReport.class, fetch=FetchType.EAGER)
	@Fetch(FetchMode.SUBSELECT)
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
	
	public void setID(Long id) {
		setReportTableId(id);
	}
}

