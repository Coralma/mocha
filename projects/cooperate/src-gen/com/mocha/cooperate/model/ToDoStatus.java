package com.mocha.cooperate.model;
import java.util.*;
import java.math.BigDecimal;
import javax.persistence.*;
import com.coral.foundation.model.BaseEntity;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
  * <p>Title: com.mocha.cooperate.model.ToDoStatus + "</p>
  * <p>Description: The ToDoStatus entity </p>
  */
@Entity(name = "ToDoStatus")
@Table(name = "T_ToDoStatus")
public class ToDoStatus extends BaseEntity {
	
	@Id()
	@Column (name = "TO_DO_STATUS_ID")
	@GeneratedValue(strategy = GenerationType. AUTO)
	private Long toDoStatusId;
	
	@Basic(optional = true)
	@Column(name = "TO_DO_STATUS_NAME" )
	private String toDoStatusName;

	@OneToOne(cascade = { CascadeType.ALL }, targetEntity = SubToDoItem.class, fetch=FetchType.EAGER)
	@Fetch(FetchMode.JOIN)
	private SubToDoItem subToDoItem;

	@OneToOne(cascade = { CascadeType.ALL }, targetEntity = com.coral.foundation.security.model.BasicUser.class, fetch=FetchType.EAGER)
	@Fetch(FetchMode.JOIN)
	private com.coral.foundation.security.model.BasicUser committedUser;

	@Basic(optional = true)
	@Column(name = "STATUS_UPDATE_DATE" )
	@Temporal(TemporalType.DATE)
	private Date statusUpdateDate;


	public void setToDoStatusId (Long toDoStatusId) {
		this.toDoStatusId = toDoStatusId;
	} 
	public Long getToDoStatusId () {
		return toDoStatusId;
	}
	public void setToDoStatusName (String toDoStatusName) {
		this.toDoStatusName = toDoStatusName;
	} 
	public String getToDoStatusName () {
		return toDoStatusName;
	}
	public void setSubToDoItem (SubToDoItem subToDoItem) {
		this.subToDoItem = subToDoItem;
	} 
	public SubToDoItem getSubToDoItem () {
		return subToDoItem;
	}
	public void setCommittedUser (com.coral.foundation.security.model.BasicUser committedUser) {
		this.committedUser = committedUser;
	} 
	public com.coral.foundation.security.model.BasicUser getCommittedUser () {
		return committedUser;
	}
	public void setStatusUpdateDate (Date statusUpdateDate) {
		this.statusUpdateDate = statusUpdateDate;
	} 
	public Date getStatusUpdateDate () {
		return statusUpdateDate;
	}

	public Long getID() {
		return getToDoStatusId();
	}
	
	public void setID(Long id) {
		setToDoStatusId(id);
	}
}

