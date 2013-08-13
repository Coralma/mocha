package com.mocha.co.model;
import java.util.*;
import java.math.BigDecimal;
import javax.persistence.*;
import com.coral.foundation.persistence.*;

/**
  * <p>Title: com.mocha.co.model.Order + "</p>
  * <p>Description: The Order entity </p>
  */
@Entity(name = "Order")
@Table(name = "T_ORDER")
public class Order extends JPABaseEntity {
	
	@Id()
	@Column (name = "ORDER_ID")
	@GeneratedValue(generator="ORDERID_SEQ")
	@TableGenerator(name="ORDERID_SEQ", table="SEQUENCE", pkColumnName="SEQ_NAME", valueColumnName="SEQ_COUNT", allocationSize=1)
	private Long orderId;
	
	@ManyToOne
	@JoinColumn(name="customer")
	private CommerceCustomer customer;
	
	@Column(name = "STATUS" )
	private String status;
	
	
	@Column(name = "PURCHASE_DATE" )
	@Temporal(TemporalType.DATE)
	private Date purchaseDate;
	
	@Column(name = "EXCEPTED_SHIP_DATE" )
	@Temporal(TemporalType.DATE)
	private Date exceptedShipDate;
	
	@Column(name = "ESTIMATED_DELIVERY" )
	@Temporal(TemporalType.DATE)
	private Date estimatedDelivery;
	
	@Column(name = "SHIPPING_SERVICE" )
	private String shippingService;
	
	
	@Column(name = "SALES_CHANNEL" )
	private String salesChannel;
	
	
	@Column(name = "ORDER_TOTALS" )
	private String orderTotals;
	
	
	@Column(name = "ORDER_PRODUCT_SUMMARY" )
	private String orderProductSummary;
	
	
	@OneToMany(targetEntity=OrderProduct.class, cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
	@JoinColumn(name="ORDER_ID")
	private List<OrderProduct> orderProducts = new ArrayList<OrderProduct>();
	

	public void setOrderId (Long orderId) {
		this.orderId = orderId;
	} 
	public Long getOrderId () {
		return orderId;
	}
	public void setCustomer (CommerceCustomer customer) {
		this.customer = customer;
	} 
	public CommerceCustomer getCustomer () {
		return customer;
	}
	public void setStatus (String status) {
		this.status = status;
	} 
	public String getStatus () {
		return status;
	}
	public void setPurchaseDate (Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	} 
	public Date getPurchaseDate () {
		return purchaseDate;
	}
	public void setExceptedShipDate (Date exceptedShipDate) {
		this.exceptedShipDate = exceptedShipDate;
	} 
	public Date getExceptedShipDate () {
		return exceptedShipDate;
	}
	public void setEstimatedDelivery (Date estimatedDelivery) {
		this.estimatedDelivery = estimatedDelivery;
	} 
	public Date getEstimatedDelivery () {
		return estimatedDelivery;
	}
	public void setShippingService (String shippingService) {
		this.shippingService = shippingService;
	} 
	public String getShippingService () {
		return shippingService;
	}
	public void setSalesChannel (String salesChannel) {
		this.salesChannel = salesChannel;
	} 
	public String getSalesChannel () {
		return salesChannel;
	}
	public void setOrderTotals (String orderTotals) {
		this.orderTotals = orderTotals;
	} 
	public String getOrderTotals () {
		return orderTotals;
	}
	public void setOrderProductSummary (String orderProductSummary) {
		this.orderProductSummary = orderProductSummary;
	} 
	public String getOrderProductSummary () {
		return orderProductSummary;
	}
	public void setOrderProducts (List<OrderProduct> orderProducts) {
		this.orderProducts = orderProducts;
	} 
	public List<OrderProduct> getOrderProducts () {
		return orderProducts;
	}

	public Long getID() {
		return getOrderId();
	}
}

