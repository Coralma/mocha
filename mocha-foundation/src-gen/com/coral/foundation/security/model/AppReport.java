package com.coral.foundation.security.model;
import java.util.*;
import java.math.BigDecimal;
import javax.persistence.*;
import com.coral.foundation.persistence.*;

/**
  * <p>Title: com.coral.foundation.security.model.AppReport + "</p>
  * <p>Description: The AppReport entity </p>
  */
@Entity(name = "AppReport")
@Table(name = "T_APP_REPORT")
public class AppReport extends JPABaseEntity {
	
	@Id()
	@Column (name = "APP_REPORT_ID")
	@GeneratedValue(generator="APPREPORTID_SEQ")
	@TableGenerator(name="APPREPORTID_SEQ", table="SEQUENCE", pkColumnName="SEQ_NAME", valueColumnName="SEQ_COUNT", allocationSize=1)
	private Long appReportId;
	
	@Column(name = "NAME" )
	private String name;
	
	
	@Column(name = "DESCRIPTION" )
	private String description;
	
	
	@Column(name = "TYPE" )
	private String type;
	
	
	@OneToMany(targetEntity=ReportFilter.class, cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
	@JoinColumn(name="APP_REPORT_ID")
	private List<ReportFilter> reportFilters = new ArrayList<ReportFilter>();
	
	@OneToMany(targetEntity=ReportTable.class, cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
	@JoinColumn(name="APP_REPORT_ID")
	private List<ReportTable> reportTables = new ArrayList<ReportTable>();
	

	public void setAppReportId (Long appReportId) {
		this.appReportId = appReportId;
	} 
	public Long getAppReportId () {
		return appReportId;
	}
	public void setName (String name) {
		this.name = name;
	} 
	public String getName () {
		return name;
	}
	public void setDescription (String description) {
		this.description = description;
	} 
	public String getDescription () {
		return description;
	}
	public void setType (String type) {
		this.type = type;
	} 
	public String getType () {
		return type;
	}
	public void setReportFilters (List<ReportFilter> reportFilters) {
		this.reportFilters = reportFilters;
	} 
	public List<ReportFilter> getReportFilters () {
		return reportFilters;
	}
	public void setReportTables (List<ReportTable> reportTables) {
		this.reportTables = reportTables;
	} 
	public List<ReportTable> getReportTables () {
		return reportTables;
	}

	public Long getID() {
		return getAppReportId();
	}
}

