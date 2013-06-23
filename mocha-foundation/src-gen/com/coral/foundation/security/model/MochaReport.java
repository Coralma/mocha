package com.coral.foundation.security.model;
import java.util.*;
import java.math.BigDecimal;
import javax.persistence.*;
import com.coral.foundation.model.BaseEntity;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
  * <p>Title: com.coral.foundation.security.model.MochaReport + "</p>
  * <p>Description: The MochaReport entity </p>
  */
@Entity(name = "MochaReport")
@Table(name = "T_MochaReport")
public class MochaReport extends BaseEntity {
	
	@Id()
	@Column (name = "MOCHA_REPORT_ID")
	@GeneratedValue(strategy = GenerationType. AUTO)
	private Long mochaReportId;
	
	@Basic(optional = true)
	@Column(name = "REPORT_NAME" )
	private String reportName;
	
	
	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST,CascadeType.REFRESH }, targetEntity = com.coral.foundation.security.model.BasicUser.class, fetch=FetchType.EAGER)
	@JoinColumns({ @JoinColumn(name = "creator") })
	@Fetch(FetchMode.JOIN)
	private com.coral.foundation.security.model.BasicUser creator;
	
	@Basic(optional = true)
	@Column(name = "CREATED_DATE" )
	@Temporal(TemporalType.DATE)
	private Date createdDate;
	
	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST,CascadeType.REFRESH }, targetEntity = AppReport.class, fetch=FetchType.EAGER)
	@JoinColumns({ @JoinColumn(name = "appReport") })
	@Fetch(FetchMode.JOIN)
	private AppReport appReport;
	
	@Basic(optional = true)
	@Column(name = "REPORT_PURE_QUERY" ,length = 65536)
	private String reportPureQuery;
	
	

	public void setMochaReportId (Long mochaReportId) {
		this.mochaReportId = mochaReportId;
	} 
	public Long getMochaReportId () {
		return mochaReportId;
	}
	public void setReportName (String reportName) {
		this.reportName = reportName;
	} 
	public String getReportName () {
		return reportName;
	}
	public void setCreator (com.coral.foundation.security.model.BasicUser creator) {
		this.creator = creator;
	} 
	public com.coral.foundation.security.model.BasicUser getCreator () {
		return creator;
	}
	public void setCreatedDate (Date createdDate) {
		this.createdDate = createdDate;
	} 
	public Date getCreatedDate () {
		return createdDate;
	}
	public void setAppReport (AppReport appReport) {
		this.appReport = appReport;
	} 
	public AppReport getAppReport () {
		return appReport;
	}
	public void setReportPureQuery (String reportPureQuery) {
		this.reportPureQuery = reportPureQuery;
	} 
	public String getReportPureQuery () {
		return reportPureQuery;
	}

	public Long getID() {
		return getMochaReportId();
	}
	
	public void setID(Long id) {
		setMochaReportId(id);
	}
}

