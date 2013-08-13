package com.mocha.ib.model;
import java.util.*;
import java.math.BigDecimal;
import javax.persistence.*;
import com.coral.foundation.persistence.*;

/**
  * <p>Title: com.mocha.ib.model.Claim + "</p>
  * <p>Description: The Claim entity </p>
  */
@Entity(name = "Claim")
@Table(name = "T_CLAIM")
public class Claim extends JPABaseEntity {
	
	@Id()
	@Column (name = "CLAIM_ID")
	@GeneratedValue(generator="CLAIMID_SEQ")
	@TableGenerator(name="CLAIMID_SEQ", table="SEQUENCE", pkColumnName="SEQ_NAME", valueColumnName="SEQ_COUNT", allocationSize=1)
	private Long claimId;
	
	@ManyToOne
	@JoinColumn(name="policy")
	private Policy policy;
	
	@Column(name = "STATUS" )
	private String status;
	
	
	@Column(name = "CLAIM_REASON" )
	private String claimReason;
	
	
	@Column(name = "CLAIM_AMOUNT" )
	private String claimAmount;
	
	
	@Column(name = "MARK" )
	private String mark;
	
	

	public void setClaimId (Long claimId) {
		this.claimId = claimId;
	} 
	public Long getClaimId () {
		return claimId;
	}
	public void setPolicy (Policy policy) {
		this.policy = policy;
	} 
	public Policy getPolicy () {
		return policy;
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
}

