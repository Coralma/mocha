package com.mocha.ib.model;
import java.util.*;
import java.math.BigDecimal;
import javax.persistence.*;
import com.coral.foundation.model.BaseEntity;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
  * <p>Title: com.mocha.ib.model.InsuranceCustomerServe + "</p>
  * <p>Description: The InsuranceCustomerServe entity </p>
  */
@Entity(name = "InsuranceCustomerServe")
@Table(name = "T_INSURANCE_CUSTOMER_SERVE")
public class InsuranceCustomerServe extends BaseEntity {
	
	@Id()
	@Column (name = "INSURANCE_CUSTOMER_SERVE_ID")
	@GeneratedValue(strategy = GenerationType. AUTO)
	private Long insuranceCustomerServeId;
	
	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST,CascadeType.REFRESH }, targetEntity = InsuranceCustomer.class, fetch=FetchType.EAGER)
	@JoinColumns({ @JoinColumn(name = "customer") })
	@Fetch(FetchMode.JOIN)
	private InsuranceCustomer customer;
	
	@Basic(optional = true)
	@Column(name = "TYPE" )
	private String type;
	
	
	@Basic(optional = true)
	@Column(name = "PRIORITY" )
	private String priority;
	
	
	@Basic(optional = true)
	@Column(name = "DATE" )
	@Temporal(TemporalType.DATE)
	private Date date;
	
	@Basic(optional = true)
	@Column(name = "STATUS" )
	private String status;
	
	
	@Basic(optional = true)
	@Column(name = "MARK" )
	private String mark;
	
	
	@Basic(optional = true)
	@Column(name = "RESULT" )
	private String result;
	
	
	@Basic(optional = true)
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
	
	public void setID(Long id) {
		setInsuranceCustomerServeId(id);
	}
}

