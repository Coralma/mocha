package com.mocha.ib.model;
import java.util.*;
import java.math.BigDecimal;
import javax.persistence.*;
import com.coral.foundation.model.BaseEntity;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
  * <p>Title: com.mocha.ib.model.Claim + "</p>
  * <p>Description: The Claim entity </p>
  */
@Entity(name = "Claim")
@Table(name = "T_CLAIM")
public class Claim extends BaseEntity {
	
	@Id()
	@Column (name = "CLAIM_ID")
	@GeneratedValue(strategy = GenerationType. AUTO)
	private Long claimId;
	
	@Basic(optional = true)
	@Column(name = "POLICY_NO" )
	private String policyNo;
	
	
	@Basic(optional = true)
	@Column(name = "STATUS" )
	private String status;
	
	
	@Basic(optional = true)
	@Column(name = "CLAIM_REASON" )
	private String claimReason;
	
	
	@Basic(optional = true)
	@Column(name = "CLAIM_AMOUNT" )
	private String claimAmount;
	
	
	@Basic(optional = true)
	@Column(name = "MARK" )
	private String mark;
	
	

	public void setClaimId (Long claimId) {
		this.claimId = claimId;
	} 
	public Long getClaimId () {
		return claimId;
	}
	public void setPolicyNo (String policyNo) {
		this.policyNo = policyNo;
	} 
	public String getPolicyNo () {
		return policyNo;
	}
	public void setStatus (String status) {
		this.status = status;
	} 
	public String getStatus () {
		return status;
	}
	public void setClaimReason (String claimReason) {
		this.claimReason = claimReason;
	} 
	public String getClaimReason () {
		return claimReason;
	}
	public void setClaimAmount (String claimAmount) {
		this.claimAmount = claimAmount;
	} 
	public String getClaimAmount () {
		return claimAmount;
	}
	public void setMark (String mark) {
		this.mark = mark;
	} 
	public String getMark () {
		return mark;
	}

	public Long getID() {
		return getClaimId();
	}
	
	public void setID(Long id) {
		setClaimId(id);
	}
}

