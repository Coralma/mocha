package com.coral.foundation.security.model;
import java.util.*;
import java.math.BigDecimal;
import javax.persistence.*;
import com.coral.foundation.model.BaseEntity;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
  * <p>Title: com.coral.foundation.security.model.UnitCodeTable + "</p>
  * <p>Description: The UnitCodeTable entity </p>
  */
@Entity(name = "UnitCodeTable")
@Table(name = "T_UNIT_CODE_TABLE")
public class UnitCodeTable extends BaseEntity {
	
	@Id()
	@Column (name = "UNIT_CODE_TABLE_ID")
	@GeneratedValue(strategy = GenerationType. AUTO)
	private Long unitCodeTableId;
	
	@Basic(optional = true)
	@Column(name = "PARENT_DATA" )
	private String parentData;
	
	
	@Basic(optional = true)
	@Column(name = "DATA" )
	private String data;
	
	
	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST,CascadeType.REFRESH }, targetEntity = CodeTable.class, fetch=FetchType.EAGER)
	@JoinColumns({ @JoinColumn(name = "codeTable") })
	@Fetch(FetchMode.JOIN)
	private CodeTable codeTable;
	

	public void setUnitCodeTableId (Long unitCodeTableId) {
		this.unitCodeTableId = unitCodeTableId;
	} 
	public Long getUnitCodeTableId () {
		return unitCodeTableId;
	}
	public void setParentData (String parentData) {
		this.parentData = parentData;
	} 
	public String getParentData () {
		return parentData;
	}
	public void setData (String data) {
		this.data = data;
	} 
	public String getData () {
		return data;
	}
	public void setCodeTable (CodeTable codeTable) {
		this.codeTable = codeTable;
	} 
	public CodeTable getCodeTable () {
		return codeTable;
	}

	public Long getID() {
		return getUnitCodeTableId();
	}
	
	public void setID(Long id) {
		setUnitCodeTableId(id);
	}
}

