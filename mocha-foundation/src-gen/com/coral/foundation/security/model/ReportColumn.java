package com.coral.foundation.security.model;
import java.util.*;
import java.math.BigDecimal;
import javax.persistence.*;
import com.coral.foundation.persistence.*;

/**
  * <p>Title: com.coral.foundation.security.model.ReportColumn + "</p>
  * <p>Description: The ReportColumn entity </p>
  */
@Entity(name = "ReportColumn")
@Table(name = "T_REPORT_COLUMN")
public class ReportColumn extends JPABaseEntity {
	
	@Id()
	@Column (name = "REPORT_COLUMN_ID")
	@GeneratedValue(generator="REPORTCOLUMNID_SEQ")
	@TableGenerator(name="REPORTCOLUMNID_SEQ", table="SEQUENCE", pkColumnName="SEQ_NAME", valueColumnName="SEQ_COUNT", allocationSize=1)
	private Long reportColumnId;
	
	@Column(name = "COLUMN_NAME" )
	private String columnName;
	
	
	@Column(name = "COLUMN_LABEL" )
	private String columnLabel;
	
	
	@Column(name = "COLUMN_INDEX" )
	private String columnIndex;
	
	
	@Column(name = "COLUMN_USE_MODE" )
	private String columnUseMode;
	
	
	@Column(name = "REFERENCE_TABLE_NAME" )
	private String referenceTableName;
	
	
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
	public void setColumnLabel (String columnLabel) {
		this.columnLabel = columnLabel;
	} 
	public String getColumnLabel () {
		return columnLabel;
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
}

