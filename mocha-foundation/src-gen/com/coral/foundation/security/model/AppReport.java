package com.coral.foundation.security.model;
import java.util.*;
import java.math.BigDecimal;
import javax.persistence.*;
import com.coral.foundation.model.BaseEntity;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
  * <p>Title: com.coral.foundation.security.model.AppReport + "</p>
  * <p>Description: The AppReport entity </p>
  */
@Entity(name = "AppReport")
@Table(name = "T_APP_REPORT")
public class AppReport extends BaseEntity {
	
	@Id()
	@Column (name = "APP_REPORT_ID")
	@GeneratedValue(strategy = GenerationType. AUTO)
	private Long appReportId;
	
	@Basic(optional = true)
	@Column(name = "NAME" )
	private String name;
	
	
	@Basic(optional = true)
	@Column(name = "DESCRIPTION" )
	private String description;
	
	
	@Basic(optional = true)
	@Column(name = "TYPE" )
	private String type;
	
	
	@OneToMany(cascade = { CascadeType.ALL }, targetEntity = ReportFilter.class, fetch=FetchType.EAGER)
	@Fetch(FetchMode.SUBSELECT)
	private List<ReportFilter> reportFilters = new ArrayList<ReportFilter>();
	
	@OneToMany(cascade = { CascadeType.ALL }, targetEntity = ReportTable.class, fetch=FetchType.EAGER)
	@Fetch(FetchMode.SUBSELECT)
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
	
	public void setID(Long id) {
		setAppReportId(id);
	}
}

