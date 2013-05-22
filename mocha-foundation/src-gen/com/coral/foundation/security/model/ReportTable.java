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
	private List<ReportColumn> reportColumns = new ArrayList<ReportColumn>();
	
	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST,CascadeType.REFRESH }, targetEntity = AppReport.class, fetch=FetchType.EAGER)
	@JoinColumns({ @JoinColumn(name = "appReport") })
	@Fetch(FetchMode.JOIN)
	private AppReport appReport;
	

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
	public void setAppReport (AppReport appReport) {
		this.appReport = appReport;
	} 
	public AppReport getAppReport () {
		return appReport;
	}

	public Long getID() {
		return getReportTableId();
	}
	
	public void setID(Long id) {
		setReportTableId(id);
	}
}

