package com.mocha.co.model;
import java.util.*;
import java.math.BigDecimal;
import javax.persistence.*;
import com.coral.foundation.model.BaseEntity;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
  * <p>Title: com.mocha.co.model.StockProduct + "</p>
  * <p>Description: The StockProduct entity </p>
  */
@Entity(name = "StockProduct")
@Table(name = "T_STOCK_PRODUCT")
public class StockProduct extends BaseEntity {
	
	@Id()
	@Column (name = "STOCK_PRODUCT_ID")
	@GeneratedValue(strategy = GenerationType. AUTO)
	private Long stockProductId;
	
	@OneToOne(cascade = { CascadeType.ALL }, targetEntity = CommerceProduct.class, fetch=FetchType.EAGER)
	@Fetch(FetchMode.JOIN)
	private CommerceProduct product;
	
	@Basic(optional = true)
	@Column(name = "EXISTENCE_QUANTITY" )
	private Long existenceQuantity;
	
	
	@Basic(optional = true)
	@Column(name = "MAXIMAL_QUANTITY" )
	private Long maximalQuantity;
	
	
	@Basic(optional = true)
	@Column(name = "MINIMUM_QUANTITY" )
	private Long minimumQuantity;
	
	
	@Basic(optional = true)
	@Column(name = "STOCK_PRICE" )
	private String stockPrice;
	
	

	public void setStockProductId (Long stockProductId) {
		this.stockProductId = stockProductId;
	} 
	public Long getStockProductId () {
		return stockProductId;
	}
	public void setProduct (CommerceProduct product) {
		this.product = product;
	} 
	public CommerceProduct getProduct () {
		return product;
	}
	public void setExistenceQuantity (Long existenceQuantity) {
		this.existenceQuantity = existenceQuantity;
	} 
	public Long getExistenceQuantity () {
		return existenceQuantity;
	}
	public void setMaximalQuantity (Long maximalQuantity) {
		this.maximalQuantity = maximalQuantity;
	} 
	public Long getMaximalQuantity () {
		return maximalQuantity;
	}
	public void setMinimumQuantity (Long minimumQuantity) {
		this.minimumQuantity = minimumQuantity;
	} 
	public Long getMinimumQuantity () {
		return minimumQuantity;
	}
	public void setStockPrice (String stockPrice) {
		this.stockPrice = stockPrice;
	} 
	public String getStockPrice () {
		return stockPrice;
	}

	public Long getID() {
		return getStockProductId();
	}
	
	public void setID(Long id) {
		setStockProductId(id);
	}
}

