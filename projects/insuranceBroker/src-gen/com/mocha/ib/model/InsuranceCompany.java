package com.mocha.ib.model;
import java.util.*;
import java.math.BigDecimal;
import javax.persistence.*;
import com.coral.foundation.model.BaseEntity;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
  * <p>Title: com.mocha.ib.model.InsuranceCompany + "</p>
  * <p>Description: The InsuranceCompany entity </p>
  */
@Entity(name = "InsuranceCompany")
@Table(name = "T_INSURANCE_COMPANY")
public class InsuranceCompany extends BaseEntity {
	
	@Id()
	@Column (name = "INSURANCE_COMPANY_ID")
	@GeneratedValue(strategy = GenerationType. AUTO)
	private Long insuranceCompanyId;
	
	@Basic(optional = true)
	@Column(name = "COMPANY_NAME" )
	private String companyName;
	
	
	@Basic(optional = true)
	@Column(name = "DESCRIPTION" )
	private String description;
	
	
	@Basic(optional = true)
	@Column(name = "LEVEL" )
	private String level;
	
	
	@OneToMany(cascade = { CascadeType.ALL }, targetEntity = InsuranceProduct.class, fetch=FetchType.EAGER)
	@Fetch(FetchMode.SUBSELECT)
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
	
	public void setID(Long id) {
		setInsuranceCompanyId(id);
	}
}

