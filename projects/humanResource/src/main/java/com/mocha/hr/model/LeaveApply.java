package com.mocha.hr.model;
import java.util.*;
import java.math.BigDecimal;
import javax.persistence.*;
import com.coral.foundation.persistence.*;

/**
  * <p>Title: com.mocha.hr.model.LeaveApply + "</p>
  * <p>Description: The LeaveApply entity </p>
  */
@Entity(name = "LeaveApply")
@Table(name = "T_LEAVE_APPLY")
public class LeaveApply extends JPABaseEntity {
	
	@Id()
	@Column (name = "LEAVE_APPLY_ID")
	@GeneratedValue(generator="LEAVEAPPLYID_SEQ")
	@TableGenerator(name="LEAVEAPPLYID_SEQ", table="SEQUENCE", pkColumnName="SEQ_NAME", valueColumnName="SEQ_COUNT", allocationSize=1)
	private Long leaveApplyId;
	
	@Column(name = "LEAVE_TYPE" )
	private String leaveType;
	
	
	@Column(name = "LEAVE_DATE_FROM" )
	@Temporal(TemporalType.DATE)
	private Date leaveDateFrom;
	
	@Column(name = "LEAVE_DATE_TO" )
	@Temporal(TemporalType.DATE)
	private Date leaveDateTo;
	
	@Column(name = "LEAVE_DURATION" )
	private String leaveDuration;
	
	
	@Column(name = "COMMENT" )
	private String comment;
	
	
	@Column(name = "STATUS" )
	private String status;
	
	
	@Column(name = "REFER_EMAILS" )
	private String referEmails;
	
	
	@OneToOne(targetEntity = com.coral.foundation.security.model.BasicUser.class)
	private com.coral.foundation.security.model.BasicUser referUser;
	
	@OneToOne(targetEntity = com.coral.foundation.security.model.BasicUser.class)
	private com.coral.foundation.security.model.BasicUser approvingAuthority;
	

	public void setLeaveApplyId (Long leaveApplyId) {
		this.leaveApplyId = leaveApplyId;
	} 
	public Long getLeaveApplyId () {
		return leaveApplyId;
	}
	public void setLeaveType (String leaveType) {
		this.leaveType = leaveType;
	} 
	public String getLeaveType () {
		return leaveType;
	}
	public void setLeaveDateFrom (Date leaveDateFrom) {
		this.leaveDateFrom = leaveDateFrom;
	} 
	public Date getLeaveDateFrom () {
		return leaveDateFrom;
	}
	public void setLeaveDateTo (Date leaveDateTo) {
		this.leaveDateTo = leaveDateTo;
	} 
	public Date getLeaveDateTo () {
		return leaveDateTo;
	}
	public void setLeaveDuration (String leaveDuration) {
		this.leaveDuration = leaveDuration;
	} 
	public String getLeaveDuration () {
		return leaveDuration;
	}
	public void setComment (String comment) {
		this.comment = comment;
	} 
	public String getComment () {
		return comment;
	}
	public void setStatus (String status) {
		this.status = status;
	} 
	public String getStatus () {
		return status;
	}
	public void setReferEmails (String referEmails) {
		this.referEmails = referEmails;
	} 
	public String getReferEmails () {
		return referEmails;
	}
	public void setReferUser (com.coral.foundation.security.model.BasicUser referUser) {
		this.referUser = referUser;
	} 
	public com.coral.foundation.security.model.BasicUser getReferUser () {
		return referUser;
	}
	public void setApprovingAuthority (com.coral.foundation.security.model.BasicUser approvingAuthority) {
		this.approvingAuthority = approvingAuthority;
	} 
	public com.coral.foundation.security.model.BasicUser getApprovingAuthority () {
		return approvingAuthority;
	}

	public Long getID() {
		return getLeaveApplyId();
	}
}

