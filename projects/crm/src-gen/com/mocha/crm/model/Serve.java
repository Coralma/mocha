package com.mocha.crm.model;
import java.util.*;
import java.math.BigDecimal;
import javax.persistence.*;
import com.coral.foundation.model.BaseEntity;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
  * <p>Title: com.mocha.crm.model.Serve + "</p>
  * <p>Description: The Serve entity </p>
  */
@Entity(name = "Serve")
@Table(name = "T_SERVE")
public class Serve extends BaseEntity {
	
	@Id()
	@Column (name = "SERVE_ID")
	@GeneratedValue(strategy = GenerationType. AUTO)
	private Long serveId;
	
	@Basic(optional = true)
	@Column(name = "OWNER" )
	private String owner;
	
	
	@Basic(optional = true)
	@Column(name = "CUSTOMER_NAME" )
	private String customerName;
	
	
	@Basic(optional = true)
	@Column(name = "TYPE" )
	private String type;
	
	
	@Basic(optional = true)
	@Column(name = "PRIORITY" )
	private String priority;
	
	
	@Basic(optional = true)
	@Column(name = "DATE" )
	private String date;
	
	
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
	
	

	public void setServeId (Long serveId) {
		this.serveId = serveId;
	} 
	public Long getServeId () {
		return serveId;
	}
	public void setOwner (String owner) {
		this.owner = owner;
	} 
	public String getOwner () {
		return owner;
	}
	public void setCustomerName (String customerName) {
		this.customerName = customerName;
	} 
	public String getCustomerName () {
		return customerName;
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
	public void setDate (String date) {
		this.date = date;
	} 
	public String getDate () {
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
		return getServeId();
	}
	
	public void setID(Long id) {
		setServeId(id);
	}
}

