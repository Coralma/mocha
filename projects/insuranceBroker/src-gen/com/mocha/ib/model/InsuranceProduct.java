package com.mocha.ib.model;
import java.util.*;
import java.math.BigDecimal;
import javax.persistence.*;
import com.coral.foundation.persistence.*;

/**
  * <p>Title: com.mocha.ib.model.InsuranceProduct + "</p>
  * <p>Description: The InsuranceProduct entity </p>
  */
@Entity(name = "InsuranceProduct")
@Table(name = "T_INSURANCE_PRODUCT")
public class InsuranceProduct extends JPABaseEntity {
	
	@Id()
	@Column (name = "INSURANCE_PRODUCT_ID")
	@GeneratedValue(generator="INSURANCEPRODUCTID_SEQ")
	@TableGenerator(name="INSURANCEPRODUCTID_SEQ", table="SEQUENCE", pkColumnName="SEQ_NAME", valueColumnName="SEQ_COUNT", allocationSize=1)
	private Long insuranceProductId;
	
	@Column(name = "PRODUCT_NAME" )
	private String productName;
	
	
	@Column(name = "CATEGORY" )
	private String category;
	
	
	@Column(name = "COMMISSION_RATE" )
	private String commissionRate;
	
	
	@Column(name = "RENEWAL_REMIND" )
	private String renewalRemind;
	
	
	@Column(name = "DESCRIPTION" )
	private String description;
	
	
	@ManyToOne
	@JoinColumn(name="insuranceCompany")
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
}

