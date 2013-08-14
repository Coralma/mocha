package com.mocha.co.model;
import java.util.*;
import java.math.BigDecimal;
import javax.persistence.*;
import com.coral.foundation.persistence.*;

/**
  * <p>Title: com.mocha.co.model.StockProduct + "</p>
  * <p>Description: The StockProduct entity </p>
  */
@Entity(name = "StockProduct")
@Table(name = "T_STOCK_PRODUCT")
public class StockProduct extends JPABaseEntity {
	
	@Id()
	@Column (name = "STOCK_PRODUCT_ID")
	@GeneratedValue(generator="STOCKPRODUCTID_SEQ")
	@TableGenerator(name="STOCKPRODUCTID_SEQ", table="SEQUENCE", pkColumnName="SEQ_NAME", valueColumnName="SEQ_COUNT", allocationSize=1)
	private Long stockProductId;
	
	@OneToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, targetEntity = CommerceProduct.class)
	private CommerceProduct product;
	
	@Column(name = "EXISTENCE_QUANTITY" )
	private Long existenceQuantity;
	
	
	@Column(name = "MAXIMAL_QUANTITY" )
	private Long maximalQuantity;
	
	
	@Column(name = "MINIMUM_QUANTITY" )
	private Long minimumQuantity;
	
	
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
}

