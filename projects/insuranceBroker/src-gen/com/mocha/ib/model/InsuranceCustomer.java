package com.mocha.ib.model;
import java.util.*;
import java.math.BigDecimal;
import javax.persistence.*;
import com.coral.foundation.persistence.*;

/**
  * <p>Title: com.mocha.ib.model.InsuranceCustomer + "</p>
  * <p>Description: The InsuranceCustomer entity </p>
  */
@Entity(name = "InsuranceCustomer")
@Table(name = "T_INSURANCE_CUSTOMER")
public class InsuranceCustomer extends JPABaseEntity {
	
	@Id()
	@Column (name = "INSURANCE_CUSTOMER_ID")
	@GeneratedValue(generator="INSURANCECUSTOMERID_SEQ")
	@TableGenerator(name="INSURANCECUSTOMERID_SEQ", table="SEQUENCE", pkColumnName="SEQ_NAME", valueColumnName="SEQ_COUNT", allocationSize=1)
	private Long insuranceCustomerId;
	
	@Column(name = "CUSTOMER_TYPE" )
	private String customerType;
	
	
	@Column(name = "STATUS" )
	private String status;
	
	
	@Column(name = "NAME" )
	private String name;
	
	
	@Column(name = "DISTRICT" )
	private String district;
	
	
	@Column(name = "POSTCODE" )
	private String postcode;
	
	
	@Column(name = "ADDRESS" )
	private String address;
	
	
	@Column(name = "CONTECT_PERSON" )
	private String contectPerson;
	
	
	@Column(name = "MOBILE" )
	private String mobile;
	
	
	@Column(name = "PHONE" )
	private String phone;
	
	
	@Column(name = "FAX" )
	private String fax;
	
	
	@Column(name = "EMAIL" )
	private String email;
	
	
	@Column(name = "ACCOUNT_BANK" )
	private String accountBank;
	
	
	@Column(name = "ACCOUNT_NUMBER" )
	private String accountNumber;
	
	
	@Column(name = "ACCOUNT_PERSON" )
	private String accountPerson;
	
	
	@Column(name = "ACCOUNT_MARK" )
	private String accountMark;
	
	
	@OneToMany(targetEntity=Policy.class, cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
	@JoinColumn(name="INSURANCE_CUSTOMER_ID")
	private List<Policy> policy = new ArrayList<Policy>();
	
	@OneToMany(targetEntity=InsuranceCustomerServe.class, cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
	@JoinColumn(name="INSURANCE_CUSTOMER_ID")
	private List<InsuranceCustomerServe> serve = new ArrayList<InsuranceCustomerServe>();
	
	@OneToOne(targetEntity = com.coral.foundation.security.model.BasicUser.class)
	private com.coral.foundation.security.model.BasicUser referUser;
	
	@OneToOne(targetEntity = com.coral.foundation.security.model.BasicUser.class)
	private com.coral.foundation.security.model.BasicUser agent;
	
	@Column(name = "MARK" )
	private String mark;
	
	

	public void setInsuranceCustomerId (Long insuranceCustomerId) {
		this.insuranceCustomerId = insuranceCustomerId;
	} 
	public Long getInsuranceCustomerId () {
		return insuranceCustomerId;
	}
	public void setCustomerType (String customerType) {
		this.customerType = customerType;
	} 
	public String getCustomerType () {
		return customerType;
	}
	public void setStatus (String status) {
		this.status = status;
	} 
	public String getStatus () {
		return status;
	}
	public void setName (String name) {
		this.name = name;
	} 
	public String getName () {
		return name;
	}
	public void setDistrict (String district) {
		this.district = district;
	} 
	public String getDistrict () {
		return district;
	}
	public void setPostcode (String postcode) {
		this.postcode = postcode;
	} 
	public String getPostcode () {
		return postcode;
	}
	public void setAddress (String address) {
		this.address = address;
	} 
	public String getAddress () {
		return address;
	}
	public void setContectPerson (String contectPerson) {
		this.contectPerson = contectPerson;
	} 
	public String getContectPerson () {
		return contectPerson;
	}
	public void setMobile (String mobile) {
		this.mobile = mobile;
	} 
	public String getMobile () {
		return mobile;
	}
	public void setPhone (String phone) {
		this.phone = phone;
	} 
	public String getPhone () {
		return phone;
	}
	public void setFax (String fax) {
		this.fax = fax;
	} 
	public String getFax () {
		return fax;
	}
	public void setEmail (String email) {
		this.email = email;
	} 
	public String getEmail () {
		return email;
	}
	public void setAccountBank (String accountBank) {
		this.accountBank = accountBank;
	} 
	public String getAccountBank () {
		return accountBank;
	}
	public void setAccountNumber (String accountNumber) {
		this.accountNumber = accountNumber;
	} 
	public String getAccountNumber () {
		return accountNumber;
	}
	public void setAccountPerson (String accountPerson) {
		this.accountPerson = accountPerson;
	} 
	public String getAccountPerson () {
		return accountPerson;
	}
	public void setAccountMark (String accountMark) {
		this.accountMark = accountMark;
	} 
	public String getAccountMark () {
		return accountMark;
	}
	public void setPolicy (List<Policy> policy) {
		this.policy = policy;
	} 
	public List<Policy> getPolicy () {
		return policy;
	}
	public void setServe (List<InsuranceCustomerServe> serve) {
		this.serve = serve;
	} 
	public List<InsuranceCustomerServe> getServe () {
		return serve;
	}
	public void setReferUser (com.coral.foundation.security.model.BasicUser referUser) {
		this.referUser = referUser;
	} 
	public com.coral.foundation.security.model.BasicUser getReferUser () {
		return referUser;
	}
	public void setAgent (com.coral.foundation.security.model.BasicUser agent) {
		this.agent = agent;
	} 
	public com.coral.foundation.security.model.BasicUser getAgent () {
		return agent;
	}
	public void setMark (String mark) {
		this.mark = mark;
	} 
	public String getMark () {
		return mark;
	}

	public Long getID() {
		return getInsuranceCustomerId();
	}
}

