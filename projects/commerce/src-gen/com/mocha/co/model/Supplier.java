package com.mocha.co.model;
import java.util.*;
import java.math.BigDecimal;
import javax.persistence.*;
import com.coral.foundation.persistence.*;

/**
  * <p>Title: com.mocha.co.model.Supplier + "</p>
  * <p>Description: The Supplier entity </p>
  */
@Entity(name = "Supplier")
@Table(name = "T_SUPPLIER")
public class Supplier extends JPABaseEntity {
	
	@Id()
	@Column (name = "SUPPLIER_ID")
	@GeneratedValue(generator="SUPPLIERID_SEQ")
	@TableGenerator(name="SUPPLIERID_SEQ", table="SEQUENCE", pkColumnName="SEQ_NAME", valueColumnName="SEQ_COUNT", allocationSize=1)
	private Long supplierId;
	
	@Column(name = "SUPPLIER_NAME" )
	private String supplierName;
	
	
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
	
	
	@Column(name = "URL" )
	private String url;
	
	
	@Column(name = "EMAIL" )
	private String email;
	
	
	@Column(name = "MARK" )
	private String mark;
	
	
	@OneToMany(targetEntity=CommerceProduct.class, cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
	@JoinColumn(name="SUPPLIER_ID")
	private List<CommerceProduct> products = new ArrayList<CommerceProduct>();
	

	public void setSupplierId (Long supplierId) {
		this.supplierId = supplierId;
	} 
	public Long getSupplierId () {
		return supplierId;
	}
	public void setSupplierName (String supplierName) {
		this.supplierName = supplierName;
	} 
	public String getSupplierName () {
		return supplierName;
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
	public void setUrl (String url) {
		this.url = url;
	} 
	public String getUrl () {
		return url;
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
	public void setProducts (List<CommerceProduct> products) {
		this.products = products;
	} 
	public List<CommerceProduct> getProducts () {
		return products;
	}

	public Long getID() {
		return getSupplierId();
	}
}

