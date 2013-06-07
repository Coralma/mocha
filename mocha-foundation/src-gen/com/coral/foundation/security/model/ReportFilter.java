package com.coral.foundation.security.model;
import java.util.*;
import java.math.BigDecimal;
import javax.persistence.*;
import com.coral.foundation.model.BaseEntity;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
  * <p>Title: com.coral.foundation.security.model.ReportFilter + "</p>
  * <p>Description: The ReportFilter entity </p>
  */
@Entity(name = "ReportFilter")
@Table(name = "T_REPORT_FILTER")
public class ReportFilter extends BaseEntity {
	
	@Id()
	@Column (name = "REPORT_FILTER_ID")
	@GeneratedValue(strategy = GenerationType. AUTO)
	private Long reportFilterId;
	
	@Basic(optional = true)
	@Column(name = "FILTER_NAME" )
	private String filterName;
	
	
	@Basic(optional = true)
	@Column(name = "FILTER_TYPE" )
	private String filterType;
	
	
	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST,CascadeType.REFRESH }, targetEntity = AppReport.class, fetch=FetchType.EAGER)
	@JoinColumns({ @JoinColumn(name = "appReport") })
	@Fetch(FetchMode.JOIN)
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
	public void setAppReport (AppReport appReport) {
		this.appReport = appReport;
	} 
	public AppReport getAppReport () {
		return appReport;
	}

	public Long getID() {
		return getReportFilterId();
	}
	
	public void setID(Long id) {
		setReportFilterId(id);
	}
}

