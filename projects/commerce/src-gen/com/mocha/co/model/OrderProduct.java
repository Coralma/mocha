package com.mocha.co.model;
import java.util.*;
import java.math.BigDecimal;
import javax.persistence.*;
import com.coral.foundation.persistence.*;

/**
  * <p>Title: com.mocha.co.model.OrderProduct + "</p>
  * <p>Description: The OrderProduct entity </p>
  */
@Entity(name = "OrderProduct")
@Table(name = "T_ORDER_PRODUCT")
public class OrderProduct extends JPABaseEntity {
	
	@Id()
	@Column (name = "ORDER_PRODUCT_ID")
	@GeneratedValue(generator="ORDERPRODUCTID_SEQ")
	@TableGenerator(name="ORDERPRODUCTID_SEQ", table="SEQUENCE", pkColumnName="SEQ_NAME", valueColumnName="SEQ_COUNT", allocationSize=1)
	private Long orderProductId;
	
	@Column(name = "PRODUCT" )
	private String product;
	
	
	@Column(name = "STATUS" )
	private String status;
	
	
	@Column(name = "QUANTITY_ORDERED" )
	private Long quantityOrdered;
	
	
	@Column(name = "PRICE" )
	private String price;
	
	
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
}

