package com.mocha.oa.model;
import java.util.*;
import java.math.BigDecimal;
import javax.persistence.*;
import com.coral.foundation.model.BaseEntity;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
  * <p>Title: com.mocha.oa.model.Leave + "</p>
  * <p>Description: The Leave entity </p>
  */
@Entity(name = "Leave")
@Table(name = "T_LEAVE")
public class Leave extends BaseEntity {
	
	@Id()
	@Column (name = "LEAVE_ID")
	@GeneratedValue(strategy = GenerationType. AUTO)
	private Long leaveId;
	
	@Basic(optional = true)
	@Column(name = "TYPE" )
	private String type;

	@Basic(optional = true)
	@Column(name = "DATE_FROM" )
	@Temporal(TemporalType.DATE)
	private Date dateFrom;

	@Basic(optional = true)
	@Column(name = "DATE_TO" )
	@Temporal(TemporalType.DATE)
	private Date dateTo;

	@Basic(optional = true)
	@Column(name = "DURATION" )
	private Long duration;

	@Basic(optional = true)
	@Column(name = "COMMENT" )
	private String comment;

	@OneToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH }, targetEntity = com.coral.foundation.security.model.BasicUser.class, fetch=FetchType.EAGER)
	@Fetch(FetchMode.JOIN)
	private com.coral.foundation.security.model.BasicUser approver;

	@OneToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH }, targetEntity = com.coral.foundation.security.model.BasicUser.class, fetch=FetchType.EAGER)
	@Fetch(FetchMode.JOIN)
	private com.coral.foundation.security.model.BasicUser creator;


	public void setLeaveId (Long leaveId) {
		this.leaveId = leaveId;
	} 
	public Long getLeaveId () {
		return leaveId;
	}
	public void setType (String type) {
		this.type = type;
	} 
	public String getType () {
		return type;
	}
	public void setDateFrom (Date dateFrom) {
		this.dateFrom = dateFrom;
	} 
	public Date getDateFrom () {
		return dateFrom;
	}
	public void setDateTo (Date dateTo) {
		this.dateTo = dateTo;
	} 
	public Date getDateTo () {
		return dateTo;
	}
	public void setDuration (Long duration) {
		this.duration = duration;
	} 
	public Long getDuration () {
		return duration;
	}
	public void setComment (String comment) {
		this.comment = comment;
	} 
	public String getComment () {
		return comment;
	}
	public void setApprover (com.coral.foundation.security.model.BasicUser approver) {
		this.approver = approver;
	} 
	public com.coral.foundation.security.model.BasicUser getApprover () {
		return approver;
	}
	public void setCreator (com.coral.foundation.security.model.BasicUser creator) {
		this.creator = creator;
	} 
	public com.coral.foundation.security.model.BasicUser getCreator () {
		return creator;
	}

	public Long getID() {
		return getLeaveId();
	}
	
	public void setID(Long id) {
		setLeaveId(id);
	}
}

