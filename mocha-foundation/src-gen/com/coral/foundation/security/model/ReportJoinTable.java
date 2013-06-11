package com.coral.foundation.security.model;
import java.util.*;
import java.math.BigDecimal;
import javax.persistence.*;
import com.coral.foundation.model.BaseEntity;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
  * <p>Title: com.coral.foundation.security.model.ReportJoinTable + "</p>
  * <p>Description: The ReportJoinTable entity </p>
  */
@Entity(name = "ReportJoinTable")
@Table(name = "T_REPORT_JOIN_TABLE")
public class ReportJoinTable extends BaseEntity {
	
	@Id()
	@Column (name = "REPORT_JOIN_TABLE_ID")
	@GeneratedValue(strategy = GenerationType. AUTO)
	private Long reportJoinTableId;
	
	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST,CascadeType.REFRESH }, targetEntity = ReportTable.class, fetch=FetchType.EAGER)
	@JoinColumns({ @JoinColumn(name = "reportTable") })
	@Fetch(FetchMode.JOIN)
	private ReportTable reportTable;
	

	public void setReportJoinTableId (Long reportJoinTableId) {
		this.reportJoinTableId = reportJoinTableId;
	} 
	public Long getReportJoinTableId () {
		return reportJoinTableId;
	}
	public void setReportTable (ReportTable reportTable) {
		this.reportTable = reportTable;
	} 
	public ReportTable getReportTable () {
		return reportTable;
	}

	public Long getID() {
		return getReportJoinTableId();
	}
	
	public void setID(Long id) {
		setReportJoinTableId(id);
	}
}

