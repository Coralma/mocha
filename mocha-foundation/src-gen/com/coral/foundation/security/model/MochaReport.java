package com.coral.foundation.security.model;
import java.util.*;
import java.math.BigDecimal;
import javax.persistence.*;
import com.coral.foundation.persistence.*;

/**
  * <p>Title: com.coral.foundation.security.model.MochaReport + "</p>
  * <p>Description: The MochaReport entity </p>
  */
@Entity(name = "MochaReport")
@Table(name = "T_MochaReport")
public class MochaReport extends JPABaseEntity {
	
	@Id()
	@Column (name = "MOCHA_REPORT_ID")
	@GeneratedValue(generator="MOCHAREPORTID_SEQ")
	@TableGenerator(name="MOCHAREPORTID_SEQ", table="SEQUENCE", pkColumnName="SEQ_NAME", valueColumnName="SEQ_COUNT", allocationSize=1)
	private Long mochaReportId;
	
	@Column(name = "REPORT_NAME" )
	private String reportName;
	
	
	@ManyToOne
	@JoinColumn(name="creator")
	private com.coral.foundation.security.model.BasicUser creator;
	
	@Column(name = "CREATED_DATE" )
	@Temporal(TemporalType.DATE)
	private Date createdDate;
	
	@ManyToOne
	@JoinColumn(name="appReport")
	private AppReport appReport;
	
	@Column(name = "REPORT_PURE_QUERY" ,length = 1000)
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
}

