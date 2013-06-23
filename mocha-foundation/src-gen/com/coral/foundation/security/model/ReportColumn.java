package com.coral.foundation.security.model;
import java.util.*;
import java.math.BigDecimal;
import javax.persistence.*;
import com.coral.foundation.model.BaseEntity;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
  * <p>Title: com.coral.foundation.security.model.ReportColumn + "</p>
  * <p>Description: The ReportColumn entity </p>
  */
@Entity(name = "ReportColumn")
@Table(name = "T_REPORT_COLUMN")
public class ReportColumn extends BaseEntity {
	
	@Id()
	@Column (name = "REPORT_COLUMN_ID")
	@GeneratedValue(strategy = GenerationType. AUTO)
	private Long reportColumnId;
	
	@Basic(optional = true)
	@Column(name = "COLUMN_NAME" )
	private String columnName;
	
	
	@Basic(optional = true)
	@Column(name = "COLUMN_INDEX" )
	private String columnIndex;
	
	
	@Basic(optional = true)
	@Column(name = "COLUMN_USE_MODE" )
	private String columnUseMode;
	
	
	@Basic(optional = true)
	@Column(name = "REFERENCE_TABLE_NAME" )
	private String referenceTableName;
	
	
	@Basic(optional = true)
	@Column(name = "REFERENCE_COLUMN_NAME" )
	private String referenceColumnName;
	
	

	public void setReportColumnId (Long reportColumnId) {
		this.reportColumnId = reportColumnId;
	} 
	public Long getReportColumnId () {
		return reportColumnId;
	}
	public void setColumnName (String columnName) {
		this.columnName = columnName;
	} 
	public String getColumnName () {
		return columnName;
	}
	public void setColumnIndex (String columnIndex) {
		this.columnIndex = columnIndex;
	} 
	public String getColumnIndex () {
		return columnIndex;
	}
	public void setColumnUseMode (String columnUseMode) {
		this.columnUseMode = columnUseMode;
	} 
	public String getColumnUseMode () {
		return columnUseMode;
	}
	public void setReferenceTableName (String referenceTableName) {
		this.referenceTableName = referenceTableName;
	} 
	public String getReferenceTableName () {
		return referenceTableName;
	}
	public void setReferenceColumnName (String referenceColumnName) {
		this.referenceColumnName = referenceColumnName;
	} 
	public String getReferenceColumnName () {
		return referenceColumnName;
	}

	public Long getID() {
		return getReportColumnId();
	}
	
	public void setID(Long id) {
		setReportColumnId(id);
	}
}

