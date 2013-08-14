package com.mocha.crm.model;
import java.util.*;
import java.math.BigDecimal;
import javax.persistence.*;
import com.coral.foundation.persistence.*;

/**
  * <p>Title: com.mocha.crm.model.Customer + "</p>
  * <p>Description: The Customer entity </p>
  */
@Entity(name = "Customer")
@Table(name = "T_CUSTOMER")
public class Customer extends JPABaseEntity {
	
	@Id()
	@Column (name = "CUSTOMER_ID")
	@GeneratedValue(generator="CUSTOMERID_SEQ")
	@TableGenerator(name="CUSTOMERID_SEQ", table="SEQUENCE", pkColumnName="SEQ_NAME", valueColumnName="SEQ_COUNT", allocationSize=1)
	private Long customerId;
	
	@Column(name = "NAME" )
	private String name;
	
	
	@Column(name = "STATUS" )
	private String status;
	
	
	@Column(name = "CONTECT_PERSON" )
	private String contectPerson;
	
	
	@Column(name = "DISTRICT" )
	private String district;
	
	
	@Column(name = "POSTCODE" )
	private String postcode;
	
	
	@Column(name = "ADDRESS" )
	private String address;
	
	
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
	
	
	@Column(name = "MARK" )
	private String mark;
	
	

	public void setCustomerId (Long customerId) {
		this.customerId = customerId;
	} 
	public Long getCustomerId () {
		return customerId;
	}
	public void setName (String name) {
		this.name = name;
	} 
	public String getName () {
		return name;
	}
	public void setStatus (String status) {
		this.status = status;
	} 
	public String getStatus () {
		return status;
	}
	public void setContectPerson (String contectPerson) {
		this.contectPerson = contectPerson;
	} 
	public String getContectPerson () {
		return contectPerson;
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
	public void setMark (String mark) {
		this.mark = mark;
	} 
	public String getMark () {
		return mark;
	}

	public Long getID() {
		return getCustomerId();
	}
}

