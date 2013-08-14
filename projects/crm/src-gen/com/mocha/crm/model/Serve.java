package com.mocha.crm.model;
import java.util.*;
import java.math.BigDecimal;
import javax.persistence.*;
import com.coral.foundation.persistence.*;

/**
  * <p>Title: com.mocha.crm.model.Serve + "</p>
  * <p>Description: The Serve entity </p>
  */
@Entity(name = "Serve")
@Table(name = "T_SERVE")
public class Serve extends JPABaseEntity {
	
	@Id()
	@Column (name = "SERVE_ID")
	@GeneratedValue(generator="SERVEID_SEQ")
	@TableGenerator(name="SERVEID_SEQ", table="SEQUENCE", pkColumnName="SEQ_NAME", valueColumnName="SEQ_COUNT", allocationSize=1)
	private Long serveId;
	
	@Column(name = "OWNER" )
	private String owner;
	
	
	@Column(name = "CUSTOMER_NAME" )
	private String customerName;
	
	
	@Column(name = "TYPE" )
	private String type;
	
	
	@Column(name = "PRIORITY" )
	private String priority;
	
	
	@Column(name = "DATE" )
	private String date;
	
	
	@Column(name = "STATUS" )
	private String status;
	
	
	@Column(name = "MARK" )
	private String mark;
	
	
	@Column(name = "RESULT" )
	private String result;
	
	
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
}

