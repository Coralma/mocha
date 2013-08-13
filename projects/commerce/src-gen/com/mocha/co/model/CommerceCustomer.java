package com.mocha.co.model;
import java.util.*;
import java.math.BigDecimal;
import javax.persistence.*;
import com.coral.foundation.persistence.*;

/**
  * <p>Title: com.mocha.co.model.CommerceCustomer + "</p>
  * <p>Description: The CommerceCustomer entity </p>
  */
@Entity(name = "CommerceCustomer")
@Table(name = "T_COMMERCE_CUSTOMER")
public class CommerceCustomer extends JPABaseEntity {
	
	@Id()
	@Column (name = "COMMERCE_CUSTOMER_ID")
	@GeneratedValue(generator="COMMERCECUSTOMERID_SEQ")
	@TableGenerator(name="COMMERCECUSTOMERID_SEQ", table="SEQUENCE", pkColumnName="SEQ_NAME", valueColumnName="SEQ_COUNT", allocationSize=1)
	private Long commerceCustomerId;
	
	@Column(name = "CUSTOMER_TYPE" )
	private String customerType;
	
	
	@Column(name = "NAME" )
	private String name;
	
	
	@Column(name = "STATUS" )
	private String status;
	
	
	@OneToMany(targetEntity=SourceApplication.class, cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
	@JoinColumn(name="COMMERCE_CUSTOMER_ID")
	private List<SourceApplication> sourceApplications = new ArrayList<SourceApplication>();
	
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
	
	
	@Column(name = "MARK" )
	private String mark;
	
	
	@OneToOne(targetEntity = com.coral.foundation.security.model.BasicUser.class)
	private com.coral.foundation.security.model.BasicUser referUser;
	
	@OneToMany(targetEntity=Order.class, cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
	@JoinColumn(name="COMMERCE_CUSTOMER_ID")
	private List<Order> orders = new ArrayList<Order>();
	

	public void setCommerceCustomerId (Long commerceCustomerId) {
		this.commerceCustomerId = commerceCustomerId;
	} 
	public Long getCommerceCustomerId () {
		return commerceCustomerId;
	}
	public void setCustomerType (String customerType) {
		this.customerType = customerType;
	} 
	public String getCustomerType () {
		return customerType;
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
	public void setSourceApplications (List<SourceApplication> sourceApplications) {
		this.sourceApplications = sourceApplications;
	} 
	public List<SourceApplication> getSourceApplications () {
		return sourceApplications;
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
	public void setMark (String mark) {
		this.mark = mark;
	} 
	public String getMark () {
		return mark;
	}
	public void setReferUser (com.coral.foundation.security.model.BasicUser referUser) {
		this.referUser = referUser;
	} 
	public com.coral.foundation.security.model.BasicUser getReferUser () {
		return referUser;
	}
	public void setOrders (List<Order> orders) {
		this.orders = orders;
	} 
	public List<Order> getOrders () {
		return orders;
	}

	public Long getID() {
		return getCommerceCustomerId();
	}
}

