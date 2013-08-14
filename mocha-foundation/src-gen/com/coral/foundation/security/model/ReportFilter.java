package com.coral.foundation.security.model;
import java.util.*;
import java.math.BigDecimal;
import javax.persistence.*;
import com.coral.foundation.persistence.*;

/**
  * <p>Title: com.coral.foundation.security.model.ReportFilter + "</p>
  * <p>Description: The ReportFilter entity </p>
  */
@Entity(name = "ReportFilter")
@Table(name = "T_REPORT_FILTER")
public class ReportFilter extends JPABaseEntity {
	
	@Id()
	@Column (name = "REPORT_FILTER_ID")
	@GeneratedValue(generator="REPORTFILTERID_SEQ")
	@TableGenerator(name="REPORTFILTERID_SEQ", table="SEQUENCE", pkColumnName="SEQ_NAME", valueColumnName="SEQ_COUNT", allocationSize=1)
	private Long reportFilterId;
	
	@Column(name = "FILTER_NAME" )
	private String filterName;
	
	
	@Column(name = "FILTER_TYPE" )
	private String filterType;
	
	
	@Column(name = "FILTER_BUILD_STRING" )
	private String filterBuildString;
	
	
	@ManyToOne
	@JoinColumn(name="appReport")
	private AppReport appReport;
	

	public void setReportFilterId (Long reportFilterId) {
		this.reportFilterId = reportFilterId;
	} 
	public Long getReportFilterId () {
		return reportFilterId;
	}
	public void setFilterName (String filterName) {
		this.filterName = filterName;
	} 
	public String getFilterName () {
		return filterName;
	}
	public void setFilterType (String filterType) {
		this.filterType = filterType;
	} 
	public String getFilterType () {
		return filterType;
	}
	public void setFilterBuildString (String filterBuildString) {
		this.filterBuildString = filterBuildString;
	} 
	public String getFilterBuildString () {
		return filterBuildString;
	}
	public void setAppReport (AppReport appReport) {
		this.appReport = appReport;
	} 
	public AppReport getAppReport () {
		return appReport;
	}

	public Long getID() {
		return getReportFilterId();
	}
}

