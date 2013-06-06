package com.mocha.ib.model;
import java.util.*;
import java.math.BigDecimal;
import javax.persistence.*;
import com.coral.foundation.model.BaseEntity;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
  * <p>Title: com.mocha.ib.model.Policy + "</p>
  * <p>Description: The Policy entity </p>
  */
@Entity(name = "Policy")
@Table(name = "T_POLICY")
public class Policy extends BaseEntity {
	
	@Id()
	@Column (name = "POLICY_ID")
	@GeneratedValue(strategy = GenerationType. AUTO)
	private Long policyId;
	
	@Basic(optional = true)
	@Column(name = "CUSTOMER_NAME" )
	private String customerName;
	
	
	@Basic(optional = true)
	@Column(name = "INSURANCE_COMPANY" )
	private String insuranceCompany;
	
	
	@Basic(optional = true)
	@Column(name = "CATEGORY" )
	private String category;
	
	
	@Basic(optional = true)
	@Column(name = "INSURANCE_PRODUCT" )
	private String insuranceProduct;
	
	
	@Basic(optional = true)
	@Column(name = "POLICY_NO" )
	private String policyNo;
	
	
	@Basic(optional = true)
	@Column(name = "EFFECTIVE_DATE" )
	private String effectiveDate;
	
	
	@Basic(optional = true)
	@Column(name = "EXPIRY_DATE" )
	private String expiryDate;
	
	
	@Basic(optional = true)
	@Column(name = "PREMIUM" )
	private String premium;
	
	
	@Basic(optional = true)
	@Column(name = "COMMISSION" )
	private String commission;
	
	
	@Basic(optional = true)
	@Column(name = "MARK" )
	private String mark;
	
	

	public void setPolicyId (Long policyId) {
		this.policyId = policyId;
	} 
	public Long getPolicyId () {
		return policyId;
	}
	public void setCustomerName (String customerName) {
		this.customerName = customerName;
	} 
	public String getCustomerName () {
		return customerName;
	}
	public void setInsuranceCompany (String insuranceCompany) {
		this.insuranceCompany = insuranceCompany;
	} 
	public String getInsuranceCompany () {
		return insuranceCompany;
	}
	public void setCategory (String category) {
		this.category = category;
	} 
	public String getCategory () {
		return category;
	}
	public void setInsuranceProduct (String insuranceProduct) {
		this.insuranceProduct = insuranceProduct;
	} 
	public String getInsuranceProduct () {
		return insuranceProduct;
	}
	public void setPolicyNo (String policyNo) {
		this.policyNo = policyNo;
	} 
	public String getPolicyNo () {
		return policyNo;
	}
	public void setEffectiveDate (String effectiveDate) {
		this.effectiveDate = effectiveDate;
	} 
	public String getEffectiveDate () {
		return effectiveDate;
	}
	public void setExpiryDate (String expiryDate) {
		this.expiryDate = expiryDate;
	} 
	public String getExpiryDate () {
		return expiryDate;
	}
	public void setPremium (String premium) {
		this.premium = premium;
	} 
	public String getPremium () {
		return premium;
	}
	public void setCommission (String commission) {
		this.commission = commission;
	} 
	public String getCommission () {
		return commission;
	}
	public void setMark (String mark) {
		this.mark = mark;
	} 
	public String getMark () {
		return mark;
	}

	public Long getID() {
		return getPolicyId();
	}
	
	public void setID(Long id) {
		setPolicyId(id);
	}
}

