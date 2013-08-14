package com.coral.foundation.security.model;
import java.util.*;
import java.math.BigDecimal;
import javax.persistence.*;
import com.coral.foundation.persistence.*;

/**
  * <p>Title: com.coral.foundation.security.model.ReportJoinTable + "</p>
  * <p>Description: The ReportJoinTable entity </p>
  */
@Entity(name = "ReportJoinTable")
@Table(name = "T_REPORT_JOIN_TABLE")
public class ReportJoinTable extends JPABaseEntity {
	
	@Id()
	@Column (name = "REPORT_JOIN_TABLE_ID")
	@GeneratedValue(generator="REPORTJOINTABLEID_SEQ")
	@TableGenerator(name="REPORTJOINTABLEID_SEQ", table="SEQUENCE", pkColumnName="SEQ_NAME", valueColumnName="SEQ_COUNT", allocationSize=1)
	private Long reportJoinTableId;
	
	@ManyToOne
	@JoinColumn(name="reportTable")
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
}

