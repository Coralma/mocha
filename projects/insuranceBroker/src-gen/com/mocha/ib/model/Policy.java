package com.mocha.ib.model;
import java.util.*;
import java.math.BigDecimal;
import javax.persistence.*;
import com.coral.foundation.persistence.*;

/**
  * <p>Title: com.mocha.ib.model.Policy + "</p>
  * <p>Description: The Policy entity </p>
  */
@Entity(name = "Policy")
@Table(name = "T_POLICY")
public class Policy extends JPABaseEntity {
	
	@Id()
	@Column (name = "POLICY_ID")
	@GeneratedValue(generator="POLICYID_SEQ")
	@TableGenerator(name="POLICYID_SEQ", table="SEQUENCE", pkColumnName="SEQ_NAME", valueColumnName="SEQ_COUNT", allocationSize=1)
	private Long policyId;
	
	@ManyToOne
	@JoinColumn(name="customer")
	private InsuranceCustomer customer;
	
	@OneToMany(targetEntity=Claim.class, cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
	@JoinColumn(name="POLICY_ID")
	private List<Claim> claim = new ArrayList<Claim>();
	
	@Column(name = "INSURANCE_COMPANY" )
	private String insuranceCompany;
	
	
	@Column(name = "CATEGORY" )
	private String category;
	
	
	@Column(name = "INSURANCE_PRODUCT" )
	private String insuranceProduct;
	
	
	@Column(name = "POLICY_NO" )
	private String policyNo;
	
	
	@Column(name = "EFFECTIVE_DATE" )
	@Temporal(TemporalType.DATE)
	private Date effectiveDate;
	
	@Column(name = "EXPIRY_DATE" )
	@Temporal(TemporalType.DATE)
	private Date expiryDate;
	
	@Column(name = "PREMIUM" )
	private String premium;
	
	
	@Column(name = "COMMISSION" )
	private String commission;
	
	
	@Column(name = "MARK" )
	private String mark;
	
	

	public void setPolicyId (Long policyId) {
		this.policyId = policyId;
	} 
	public Long getPolicyId () {
		return policyId;
	}
	public void setCustomer (InsuranceCustomer customer) {
		this.customer = customer;
	} 
	public InsuranceCustomer getCustomer () {
		return customer;
	}
	public void setClaim (List<Claim> claim) {
		this.claim = claim;
	} 
	public List<Claim> getClaim () {
		return claim;
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
	public void setEffectiveDate (Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	} 
	public Date getEffectiveDate () {
		return effectiveDate;
	}
	public void setExpiryDate (Date expiryDate) {
		this.expiryDate = expiryDate;
	} 
	public Date getExpiryDate () {
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
}

