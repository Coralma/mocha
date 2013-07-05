package com.mocha.co.model;
import java.util.*;
import java.math.BigDecimal;
import javax.persistence.*;
import com.coral.foundation.model.BaseEntity;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
  * <p>Title: com.mocha.co.model.OrderProduct + "</p>
  * <p>Description: The OrderProduct entity </p>
  */
@Entity(name = "OrderProduct")
@Table(name = "T_ORDER_PRODUCT")
public class OrderProduct extends BaseEntity {
	
	@Id()
	@Column (name = "ORDER_PRODUCT_ID")
	@GeneratedValue(strategy = GenerationType. AUTO)
	private Long orderProductId;
	
	@Basic(optional = true)
	@Column(name = "PRODUCT" )
	private String product;
	
	
	@Basic(optional = true)
	@Column(name = "STATUS" )
	private String status;
	
	
	@Basic(optional = true)
	@Column(name = "QUANTITY_ORDERED" )
	private Long quantityOrdered;
	
	
	@Basic(optional = true)
	@Column(name = "PRICE" )
	private String price;
	
	
	@Basic(optional = true)
	@Column(name = "SUB_TOTAL" )
	private String subTotal;
	
	

	public void setOrderProductId (Long orderProductId) {
		this.orderProductId = orderProductId;
	} 
	public Long getOrderProductId () {
		return orderProductId;
	}
	public void setProduct (String product) {
		this.product = product;
	} 
	public String getProduct () {
		return product;
	}
	public void setStatus (String status) {
		this.status = status;
	} 
	public String getStatus () {
		return status;
	}
	public void setQuantityOrdered (Long quantityOrdered) {
		this.quantityOrdered = quantityOrdered;
	} 
	public Long getQuantityOrdered () {
		return quantityOrdered;
	}
	public void setPrice (String price) {
		this.price = price;
	} 
	public String getPrice () {
		return price;
	}
	public void setSubTotal (String subTotal) {
		this.subTotal = subTotal;
	} 
	public String getSubTotal () {
		return subTotal;
	}

	public Long getID() {
		return getOrderProductId();
	}
	
	public void setID(Long id) {
		setOrderProductId(id);
	}
}

