package com.mocha.co.model;
import java.util.*;
import java.math.BigDecimal;
import javax.persistence.*;
import com.coral.foundation.model.BaseEntity;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
  * <p>Title: com.mocha.co.model.Order + "</p>
  * <p>Description: The Order entity </p>
  */
@Entity(name = "Order")
@Table(name = "T_ORDER")
public class Order extends BaseEntity {
	
	@Id()
	@Column (name = "ORDER_ID")
	@GeneratedValue(strategy = GenerationType. AUTO)
	private Long orderId;
	
	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST,CascadeType.REFRESH }, targetEntity = CommerceCustomer.class, fetch=FetchType.EAGER)
	@JoinColumns({ @JoinColumn(name = "customer") })
	@Fetch(FetchMode.JOIN)
	private CommerceCustomer customer;
	
	@Basic(optional = true)
	@Column(name = "STATUS" )
	private String status;
	
	
	@Basic(optional = true)
	@Column(name = "PURCHASE_DATE" )
	@Temporal(TemporalType.DATE)
	private Date purchaseDate;
	
	@Basic(optional = true)
	@Column(name = "EXCEPTED_SHIP_DATE" )
	@Temporal(TemporalType.DATE)
	private Date exceptedShipDate;
	
	@Basic(optional = true)
	@Column(name = "ESTIMATED_DELIVERY" )
	@Temporal(TemporalType.DATE)
	private Date estimatedDelivery;
	
	@Basic(optional = true)
	@Column(name = "SHIPPING_SERVICE" )
	private String shippingService;
	
	
	@Basic(optional = true)
	@Column(name = "SALES_CHANNEL" )
	private String salesChannel;
	
	
	@Basic(optional = true)
	@Column(name = "ORDER_TOTALS" )
	private String orderTotals;
	
	
	@Basic(optional = true)
	@Column(name = "ORDER_PRODUCT_SUMMARY" )
	private String orderProductSummary;
	
	
	@OneToMany(cascade = { CascadeType.ALL }, targetEntity = OrderProduct.class, fetch=FetchType.EAGER)
	@Fetch(FetchMode.SUBSELECT)
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
	
	public void setID(Long id) {
		setOrderId(id);
	}
}

