package com.mocha.co.model;
import java.util.*;
import java.math.BigDecimal;
import javax.persistence.*;
import com.coral.foundation.persistence.*;

/**
  * <p>Title: com.mocha.co.model.Promotion + "</p>
  * <p>Description: The Promotion entity </p>
  */
@Entity(name = "Promotion")
@Table(name = "T_PROMOTION")
public class Promotion extends JPABaseEntity {
	
	@Id()
	@Column (name = "PROMOTION_ID")
	@GeneratedValue(generator="PROMOTIONID_SEQ")
	@TableGenerator(name="PROMOTIONID_SEQ", table="SEQUENCE", pkColumnName="SEQ_NAME", valueColumnName="SEQ_COUNT", allocationSize=1)
	private Long promotionId;
	
	@ManyToOne
	@JoinColumn(name="product")
	private CommerceProduct product;
	
	@Column(name = "STATUS" )
	private String status;
	
	
	@Column(name = "TYPE" )
	private String type;
	
	
	@Column(name = "DISCOUNT" )
	private String discount;
	
	
	@Column(name = "PRICE" )
	private String price;
	
	
	@Column(name = "START_DATE" )
	@Temporal(TemporalType.DATE)
	private Date startDate;
	
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
}

