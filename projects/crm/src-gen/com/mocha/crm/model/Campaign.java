package com.mocha.crm.model;
import java.util.*;
import java.math.BigDecimal;
import javax.persistence.*;
import com.coral.foundation.persistence.*;

/**
  * <p>Title: com.mocha.crm.model.Campaign + "</p>
  * <p>Description: The Campaign entity </p>
  */
@Entity(name = "Campaign")
@Table(name = "T_CAMPAIGN")
public class Campaign extends JPABaseEntity {
	
	@Id()
	@Column (name = "CAMPAIGN_ID")
	@GeneratedValue(generator="CAMPAIGNID_SEQ")
	@TableGenerator(name="CAMPAIGNID_SEQ", table="SEQUENCE", pkColumnName="SEQ_NAME", valueColumnName="SEQ_COUNT", allocationSize=1)
	private Long campaignId;
	
	@Column(name = "OWNER" )
	private String owner;
	
	
	@Column(name = "CAMPAIGN_NAME" )
	private String campaignName;
	
	
	@Column(name = "CAMPAIGN_DESCRIPTION" )
	private String campaignDescription;
	
	
	@Column(name = "TYPE" )
	private String type;
	
	
	@Column(name = "STATUS" )
	private String status;
	
	
	@Column(name = "START_DATE" )
	private String startDate;
	
	
	@Column(name = "END_DATE" )
	private String endDate;
	
	
	@Column(name = "ADDRESS" )
	private String address;
	
	
	@Column(name = "EXPECTED_AMOUNT" )
	private String expectedAmount;
	
	
	@Column(name = "BUDGETED_COST" )
	private String budgetedCost;
	
	
	@Column(name = "EXPECTED_SALES_VOLUME" )
	private String expectedSalesVolume;
	
	
	@Column(name = "EXPECTED_RESPONSE" )
	private String expectedResponse;
	
	
	@Column(name = "ACTUAL_AMOUNT" )
	private String actualAmount;
	
	
	@Column(name = "ACTUAL_COST" )
	private String actualCost;
	
	
	@Column(name = "ACTUAL_SALES_VOLUME" )
	private String actualSalesVolume;
	
	
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
}

