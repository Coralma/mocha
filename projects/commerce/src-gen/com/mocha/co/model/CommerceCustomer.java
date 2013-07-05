package com.mocha.co.model;
import java.util.*;
import java.math.BigDecimal;
import javax.persistence.*;
import com.coral.foundation.model.BaseEntity;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
  * <p>Title: com.mocha.co.model.CommerceCustomer + "</p>
  * <p>Description: The CommerceCustomer entity </p>
  */
@Entity(name = "CommerceCustomer")
@Table(name = "T_COMMERCE_CUSTOMER")
public class CommerceCustomer extends BaseEntity {
	
	@Id()
	@Column (name = "COMMERCE_CUSTOMER_ID")
	@GeneratedValue(strategy = GenerationType. AUTO)
	private Long commerceCustomerId;
	
	@Basic(optional = true)
	@Column(name = "CUSTOMER_TYPE" )
	private String customerType;
	
	
	@Basic(optional = true)
	@Column(name = "NAME" )
	private String name;
	
	
	@Basic(optional = true)
	@Column(name = "STATUS" )
	private String status;
	
	
	@Basic(optional = true)
	@Column(name = "SOURCE" )
	private String source;
	
	
	@Basic(optional = true)
	@Column(name = "DISTRICT" )
	private String district;
	
	
	@Basic(optional = true)
	@Column(name = "POSTCODE" )
	private String postcode;
	
	
	@Basic(optional = true)
	@Column(name = "ADDRESS" )
	private String address;
	
	
	@Basic(optional = true)
	@Column(name = "CONTECT_PERSON" )
	private String contectPerson;
	
	
	@Basic(optional = true)
	@Column(name = "MOBILE" )
	private String mobile;
	
	
	@Basic(optional = true)
	@Column(name = "PHONE" )
	private String phone;
	
	
	@Basic(optional = true)
	@Column(name = "FAX" )
	private String fax;
	
	
	@Basic(optional = true)
	@Column(name = "EMAIL" )
	private String email;
	
	
	@Basic(optional = true)
	@Column(name = "MARK" )
	private String mark;
	
	
	@OneToMany(cascade = { CascadeType.ALL }, targetEntity = Order.class, fetch=FetchType.EAGER)
	@Fetch(FetchMode.SUBSELECT)
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
	public void setSource (String source) {
		this.source = source;
	} 
	public String getSource () {
		return source;
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
	public void setOrders (List<Order> orders) {
		this.orders = orders;
	} 
	public List<Order> getOrders () {
		return orders;
	}

	public Long getID() {
		return getCommerceCustomerId();
	}
	
	public void setID(Long id) {
		setCommerceCustomerId(id);
	}
}

