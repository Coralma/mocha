package com.mocha.ib.model;
import java.util.*;
import java.math.BigDecimal;
import javax.persistence.*;
import com.coral.foundation.model.BaseEntity;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
  * <p>Title: com.mocha.ib.model.InsuranceProduct + "</p>
  * <p>Description: The InsuranceProduct entity </p>
  */
@Entity(name = "InsuranceProduct")
@Table(name = "T_INSURANCE_PRODUCT")
public class InsuranceProduct extends BaseEntity {
	
	@Id()
	@Column (name = "INSURANCE_PRODUCT_ID")
	@GeneratedValue(strategy = GenerationType. AUTO)
	private Long insuranceProductId;
	
	@Basic(optional = true)
	@Column(name = "PRODUCT_NAME" )
	private String productName;
	
	
	@Basic(optional = true)
	@Column(name = "CATEGORY" )
	private String category;
	
	
	@Basic(optional = true)
	@Column(name = "COMMISSION_RATE" )
	private String commissionRate;
	
	
	@Basic(optional = true)
	@Column(name = "RENEWAL_REMIND" )
	private String renewalRemind;
	
	
	@Basic(optional = true)
	@Column(name = "DESCRIPTION" )
	private String description;
	
	
	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST,CascadeType.REFRESH }, targetEntity = InsuranceCompany.class, fetch=FetchType.EAGER)
	@JoinColumns({ @JoinColumn(name = "insuranceCompany") })
	@Fetch(FetchMode.JOIN)
	private InsuranceCompany insuranceCompany;
	

	public void setInsuranceProductId (Long insuranceProductId) {
		this.insuranceProductId = insuranceProductId;
	} 
	public Long getInsuranceProductId () {
		return insuranceProductId;
	}
	public void setProductName (String productName) {
		this.productName = productName;
	} 
	public String getProductName () {
		return productName;
	}
	public void setCategory (String category) {
		this.category = category;
	} 
	public String getCategory () {
		return category;
	}
	public void setCommissionRate (String commissionRate) {
		this.commissionRate = commissionRate;
	} 
	public String getCommissionRate () {
		return commissionRate;
	}
	public void setRenewalRemind (String renewalRemind) {
		this.renewalRemind = renewalRemind;
	} 
	public String getRenewalRemind () {
		return renewalRemind;
	}
	public void setDescription (String description) {
		this.description = description;
	} 
	public String getDescription () {
		return description;
	}
	public void setInsuranceCompany (InsuranceCompany insuranceCompany) {
		this.insuranceCompany = insuranceCompany;
	} 
	public InsuranceCompany getInsuranceCompany () {
		return insuranceCompany;
	}

	public Long getID() {
		return getInsuranceProductId();
	}
	
	public void setID(Long id) {
		setInsuranceProductId(id);
	}
}

