package com.mocha.ib.model;
import java.util.*;
import java.math.BigDecimal;
import javax.persistence.*;
import com.coral.foundation.persistence.*;

/**
  * <p>Title: com.mocha.ib.model.InsuranceCompany + "</p>
  * <p>Description: The InsuranceCompany entity </p>
  */
@Entity(name = "InsuranceCompany")
@Table(name = "T_INSURANCE_COMPANY")
public class InsuranceCompany extends JPABaseEntity {
	
	@Id()
	@Column (name = "INSURANCE_COMPANY_ID")
	@GeneratedValue(generator="INSURANCECOMPANYID_SEQ")
	@TableGenerator(name="INSURANCECOMPANYID_SEQ", table="SEQUENCE", pkColumnName="SEQ_NAME", valueColumnName="SEQ_COUNT", allocationSize=1)
	private Long insuranceCompanyId;
	
	@Column(name = "COMPANY_NAME" )
	private String companyName;
	
	
	@Column(name = "DESCRIPTION" )
	private String description;
	
	
	@Column(name = "LEVEL" )
	private String level;
	
	
	@OneToMany(targetEntity=InsuranceProduct.class, cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
	@JoinColumn(name="INSURANCE_COMPANY_ID")
	private List<InsuranceProduct> products = new ArrayList<InsuranceProduct>();
	

	public void setInsuranceCompanyId (Long insuranceCompanyId) {
		this.insuranceCompanyId = insuranceCompanyId;
	} 
	public Long getInsuranceCompanyId () {
		return insuranceCompanyId;
	}
	public void setCompanyName (String companyName) {
		this.companyName = companyName;
	} 
	public String getCompanyName () {
		return companyName;
	}
	public void setDescription (String description) {
		this.description = description;
	} 
	public String getDescription () {
		return description;
	}
	public void setLevel (String level) {
		this.level = level;
	} 
	public String getLevel () {
		return level;
	}
	public void setProducts (List<InsuranceProduct> products) {
		this.products = products;
	} 
	public List<InsuranceProduct> getProducts () {
		return products;
	}

	public Long getID() {
		return getInsuranceCompanyId();
	}
}

