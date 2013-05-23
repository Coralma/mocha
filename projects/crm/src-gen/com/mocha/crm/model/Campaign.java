package com.mocha.crm.model;
import java.util.*;
import java.math.BigDecimal;
import javax.persistence.*;
import com.coral.foundation.model.BaseEntity;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
  * <p>Title: com.mocha.crm.model.Campaign + "</p>
  * <p>Description: The Campaign entity </p>
  */
@Entity(name = "Campaign")
@Table(name = "T_CAMPAIGN")
public class Campaign extends BaseEntity {
	
	@Id()
	@Column (name = "CAMPAIGN_ID")
	@GeneratedValue(strategy = GenerationType. AUTO)
	private Long campaignId;
	
	@Basic(optional = true)
	@Column(name = "OWNER" )
	private String owner;
	
	
	@Basic(optional = true)
	@Column(name = "CAMPAIGN_NAME" )
	private String campaignName;
	
	
	@Basic(optional = true)
	@Column(name = "CAMPAIGN_DESCRIPTION" )
	private String campaignDescription;
	
	
	@Basic(optional = true)
	@Column(name = "TYPE" )
	private String type;
	
	
	@Basic(optional = true)
	@Column(name = "STATUS" )
	private String status;
	
	
	@Basic(optional = true)
	@Column(name = "START_DATE" )
	private String startDate;
	
	
	@Basic(optional = true)
	@Column(name = "END_DATE" )
	private String endDate;
	
	
	@Basic(optional = true)
	@Column(name = "ADDRESS" )
	private String address;
	
	
	@Basic(optional = true)
	@Column(name = "EXPECTED_AMOUNT" )
	private String expectedAmount;
	
	
	@Basic(optional = true)
	@Column(name = "BUDGETED_COST" )
	private String budgetedCost;
	
	
	@Basic(optional = true)
	@Column(name = "EXPECTED_SALES_VOLUME" )
	private String expectedSalesVolume;
	
	
	@Basic(optional = true)
	@Column(name = "EXPECTED_RESPONSE" )
	private String expectedResponse;
	
	
	@Basic(optional = true)
	@Column(name = "ACTUAL_AMOUNT" )
	private String actualAmount;
	
	
	@Basic(optional = true)
	@Column(name = "ACTUAL_COST" )
	private String actualCost;
	
	
	@Basic(optional = true)
	@Column(name = "ACTUAL_SALES_VOLUME" )
	private String actualSalesVolume;
	
	
	@Basic(optional = true)
	@Column(name = "MARK" )
	private String mark;
	
	

	public void setCampaignId (Long campaignId) {
		this.campaignId = campaignId;
	} 
	public Long getCampaignId () {
		return campaignId;
	}
	public void setOwner (String owner) {
		this.owner = owner;
	} 
	public String getOwner () {
		return owner;
	}
	public void setCampaignName (String campaignName) {
		this.campaignName = campaignName;
	} 
	public String getCampaignName () {
		return campaignName;
	}
	public void setCampaignDescription (String campaignDescription) {
		this.campaignDescription = campaignDescription;
	} 
	public String getCampaignDescription () {
		return campaignDescription;
	}
	public void setType (String type) {
		this.type = type;
	} 
	public String getType () {
		return type;
	}
	public void setStatus (String status) {
		this.status = status;
	} 
	public String getStatus () {
		return status;
	}
	public void setStartDate (String startDate) {
		this.startDate = startDate;
	} 
	public String getStartDate () {
		return startDate;
	}
	public void setEndDate (String endDate) {
		this.endDate = endDate;
	} 
	public String getEndDate () {
		return endDate;
	}
	public void setAddress (String address) {
		this.address = address;
	} 
	public String getAddress () {
		return address;
	}
	public void setExpectedAmount (String expectedAmount) {
		this.expectedAmount = expectedAmount;
	} 
	public String getExpectedAmount () {
		return expectedAmount;
	}
	public void setBudgetedCost (String budgetedCost) {
		this.budgetedCost = budgetedCost;
	} 
	public String getBudgetedCost () {
		return budgetedCost;
	}
	public void setExpectedSalesVolume (String expectedSalesVolume) {
		this.expectedSalesVolume = expectedSalesVolume;
	} 
	public String getExpectedSalesVolume () {
		return expectedSalesVolume;
	}
	public void setExpectedResponse (String expectedResponse) {
		this.expectedResponse = expectedResponse;
	} 
	public String getExpectedResponse () {
		return expectedResponse;
	}
	public void setActualAmount (String actualAmount) {
		this.actualAmount = actualAmount;
	} 
	public String getActualAmount () {
		return actualAmount;
	}
	public void setActualCost (String actualCost) {
		this.actualCost = actualCost;
	} 
	public String getActualCost () {
		return actualCost;
	}
	public void setActualSalesVolume (String actualSalesVolume) {
		this.actualSalesVolume = actualSalesVolume;
	} 
	public String getActualSalesVolume () {
		return actualSalesVolume;
	}
	public void setMark (String mark) {
		this.mark = mark;
	} 
	public String getMark () {
		return mark;
	}

	public Long getID() {
		return getCampaignId();
	}
	
	public void setID(Long id) {
		setCampaignId(id);
	}
}

