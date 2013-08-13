package com.mocha.co.model;
import java.util.*;
import java.math.BigDecimal;
import javax.persistence.*;
import com.coral.foundation.persistence.*;

/**
  * <p>Title: com.mocha.co.model.CommerceProduct + "</p>
  * <p>Description: The CommerceProduct entity </p>
  */
@Entity(name = "CommerceProduct")
@Table(name = "T_COMMERCE_PRODUCT")
public class CommerceProduct extends JPABaseEntity {
	
	@Id()
	@Column (name = "COMMERCE_PRODUCT_ID")
	@GeneratedValue(generator="COMMERCEPRODUCTID_SEQ")
	@TableGenerator(name="COMMERCEPRODUCTID_SEQ", table="SEQUENCE", pkColumnName="SEQ_NAME", valueColumnName="SEQ_COUNT", allocationSize=1)
	private Long commerceProductId;
	
	@Column(name = "PRODUCT_CODE" )
	private String productCode;
	
	
	@Column(name = "PRODUCT_NAME" )
	private String productName;
	
	
	@Column(name = "PRODUCT_IMAGE" )
	private String productImage;
	
	
	@Column(name = "BRAND" )
	private String brand;
	
	
	@Column(name = "UNIT" )
	private String unit;
	
	
	@Column(name = "SPEC" )
	private String spec;
	
	
	@Column(name = "COLOR" )
	private String color;
	
	
	@Column(name = "SALE_PRICE" )
	private String salePrice;
	
	
	@Column(name = "MARK" )
	private String mark;
	
	
	@OneToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, targetEntity = StockProduct.class)
	private StockProduct stockProduct;
	
	@OneToMany(targetEntity=Promotion.class, cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
	@JoinColumn(name="COMMERCE_PRODUCT_ID")
	private List<Promotion> promotions = new ArrayList<Promotion>();
	
	@ManyToOne
	@JoinColumn(name="supplier")
	private Supplier supplier;
	

	public void setCommerceProductId (Long commerceProductId) {
		this.commerceProductId = commerceProductId;
	} 
	public Long getCommerceProductId () {
		return commerceProductId;
	}
	public void setProductCode (String productCode) {
		this.productCode = productCode;
	} 
	public String getProductCode () {
		return productCode;
	}
	public void setProductName (String productName) {
		this.productName = productName;
	} 
	public String getProductName () {
		return productName;
	}
	public void setProductImage (String productImage) {
		this.productImage = productImage;
	} 
	public String getProductImage () {
		return productImage;
	}
	public void setBrand (String brand) {
		this.brand = brand;
	} 
	public String getBrand () {
		return brand;
	}
	public void setUnit (String unit) {
		this.unit = unit;
	} 
	public String getUnit () {
		return unit;
	}
	public void setSpec (String spec) {
		this.spec = spec;
	} 
	public String getSpec () {
		return spec;
	}
	public void setColor (String color) {
		this.color = color;
	} 
	public String getColor () {
		return color;
	}
	public void setSalePrice (String salePrice) {
		this.salePrice = salePrice;
	} 
	public String getSalePrice () {
		return salePrice;
	}
	public void setMark (String mark) {
		this.mark = mark;
	} 
	public String getMark () {
		return mark;
	}
	public void setStockProduct (StockProduct stockProduct) {
		this.stockProduct = stockProduct;
	} 
	public StockProduct getStockProduct () {
		return stockProduct;
	}
	public void setPromotions (List<Promotion> promotions) {
		this.promotions = promotions;
	} 
	public List<Promotion> getPromotions () {
		return promotions;
	}
	public void setSupplier (Supplier supplier) {
		this.supplier = supplier;
	} 
	public Supplier getSupplier () {
		return supplier;
	}

	public Long getID() {
		return getCommerceProductId();
	}
}

