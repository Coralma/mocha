package com.mocha.co.model;
import java.util.*;
import java.math.BigDecimal;
import javax.persistence.*;
import com.coral.foundation.model.BaseEntity;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
  * <p>Title: com.mocha.co.model.Promotion + "</p>
  * <p>Description: The Promotion entity </p>
  */
@Entity(name = "Promotion")
@Table(name = "T_PROMOTION")
public class Promotion extends BaseEntity {
	
	@Id()
	@Column (name = "PROMOTION_ID")
	@GeneratedValue(strategy = GenerationType. AUTO)
	private Long promotionId;
	
	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST,CascadeType.REFRESH }, targetEntity = CommerceProduct.class, fetch=FetchType.EAGER)
	@JoinColumns({ @JoinColumn(name = "product") })
	@Fetch(FetchMode.JOIN)
	private CommerceProduct product;
	
	@Basic(optional = true)
	@Column(name = "STATUS" )
	private String status;
	
	
	@Basic(optional = true)
	@Column(name = "TYPE" )
	private String type;
	
	
	@Basic(optional = true)
	@Column(name = "DISCOUNT" )
	private String discount;
	
	
	@Basic(optional = true)
	@Column(name = "PRICE" )
	private String price;
	
	
	@Basic(optional = true)
	@Column(name = "START_DATE" )
	@Temporal(TemporalType.DATE)
	private Date startDate;
	
	@Basic(optional = true)
	@Column(name = "EXPIRY_DATE" )
	@Temporal(TemporalType.DATE)
	private Date expiryDate;
	

	public void setPromotionId (Long promotionId) {
		this.promotionId = promotionId;
	} 
	public Long getPromotionId () {
		return promotionId;
	}
	public void setProduct (CommerceProduct product) {
		this.product = product;
	} 
	public CommerceProduct getProduct () {
		return product;
	}
	public void setStatus (String status) {
		this.status = status;
	} 
	public String getStatus () {
		return status;
	}
	public void setType (String type) {
		this.type = type;
	} 
	public String getType () {
		return type;
	}
	public void setDiscount (String discount) {
		this.discount = discount;
	} 
	public String getDiscount () {
		return discount;
	}
	public void setPrice (String price) {
		this.price = price;
	} 
	public String getPrice () {
		return price;
	}
	public void setStartDate (Date startDate) {
		this.startDate = startDate;
	} 
	public Date getStartDate () {
		return startDate;
	}
	public void setExpiryDate (Date expiryDate) {
		this.expiryDate = expiryDate;
	} 
	public Date getExpiryDate () {
		return expiryDate;
	}

	public Long getID() {
		return getPromotionId();
	}
	
	public void setID(Long id) {
		setPromotionId(id);
	}
}

