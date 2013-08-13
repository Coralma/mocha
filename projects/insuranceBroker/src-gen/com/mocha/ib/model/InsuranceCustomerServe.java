package com.mocha.ib.model;
import java.util.*;
import java.math.BigDecimal;
import javax.persistence.*;
import com.coral.foundation.persistence.*;

/**
  * <p>Title: com.mocha.ib.model.InsuranceCustomerServe + "</p>
  * <p>Description: The InsuranceCustomerServe entity </p>
  */
@Entity(name = "InsuranceCustomerServe")
@Table(name = "T_INSURANCE_CUSTOMER_SERVE")
public class InsuranceCustomerServe extends JPABaseEntity {
	
	@Id()
	@Column (name = "INSURANCE_CUSTOMER_SERVE_ID")
	@GeneratedValue(generator="INSURANCECUSTOMERSERVEID_SEQ")
	@TableGenerator(name="INSURANCECUSTOMERSERVEID_SEQ", table="SEQUENCE", pkColumnName="SEQ_NAME", valueColumnName="SEQ_COUNT", allocationSize=1)
	private Long insuranceCustomerServeId;
	
	@ManyToOne
	@JoinColumn(name="customer")
	private InsuranceCustomer customer;
	
	@Column(name = "TYPE" )
	private String type;
	
	
	@Column(name = "PRIORITY" )
	private String priority;
	
	
	@Column(name = "DATE" )
	@Temporal(TemporalType.DATE)
	private Date date;
	
	@Column(name = "STATUS" )
	private String status;
	
	
	@Column(name = "MARK" )
	private String mark;
	
	
	@Column(name = "RESULT" )
	private String result;
	
	
	@Column(name = "FEEDBACK" )
	private String feedback;
	
	

	public void setInsuranceCustomerServeId (Long insuranceCustomerServeId) {
		this.insuranceCustomerServeId = insuranceCustomerServeId;
	} 
	public Long getInsuranceCustomerServeId () {
		return insuranceCustomerServeId;
	}
	public void setCustomer (InsuranceCustomer customer) {
		this.customer = customer;
	} 
	public InsuranceCustomer getCustomer () {
		return customer;
	}
	public void setType (String type) {
		this.type = type;
	} 
	public String getType () {
		return type;
	}
	public void setPriority (String priority) {
		this.priority = priority;
	} 
	public String getPriority () {
		return priority;
	}
	public void setDate (Date date) {
		this.date = date;
	} 
	public Date getDate () {
		return date;
	}
	public void setStatus (String status) {
		this.status = status;
	} 
	public String getStatus () {
		return status;
	}
	public void setMark (String mark) {
		this.mark = mark;
	} 
	public String getMark () {
		return mark;
	}
	public void setResult (String result) {
		this.result = result;
	} 
	public String getResult () {
		return result;
	}
	public void setFeedback (String feedback) {
		this.feedback = feedback;
	} 
	public String getFeedback () {
		return feedback;
	}

	public Long getID() {
		return getInsuranceCustomerServeId();
	}
}

