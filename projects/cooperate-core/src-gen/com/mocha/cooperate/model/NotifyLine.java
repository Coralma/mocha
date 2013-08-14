package com.mocha.cooperate.model;
import java.util.*;
import java.math.BigDecimal;
import javax.persistence.*;
import com.coral.foundation.persistence.*;

/**
  * <p>Title: com.mocha.cooperate.model.NotifyLine + "</p>
  * <p>Description: The NotifyLine entity </p>
  */
@Entity(name = "NotifyLine")
@Table(name = "T_NOTIFY_LINE")
public class NotifyLine extends JPABaseEntity {
	
	@Id()
	@Column (name = "NOTIFY_LINE_ID")
	@GeneratedValue(generator="NOTIFYLINEID_SEQ")
	@TableGenerator(name="NOTIFYLINEID_SEQ", table="SEQUENCE", pkColumnName="SEQ_NAME", valueColumnName="SEQ_COUNT", allocationSize=1)
	private Long notifyLineId;
	
	@Column(name = "TYPE" )
	private Long type = new Long(1);
	
	
	@OneToOne(targetEntity = com.coral.foundation.security.model.BasicUser.class)
	private com.coral.foundation.security.model.BasicUser notifiedUser;
	
	@ManyToOne
	@JoinColumn(name="status")
	private Status status;
	
	@ManyToOne
	@JoinColumn(name="discuss")
	private Discuss discuss;
	
	@ManyToOne
	@JoinColumn(name="todo")
	private ToDo todo;
	

	public void setNotifyLineId (Long notifyLineId) {
		this.notifyLineId = notifyLineId;
	} 
	public Long getNotifyLineId () {
		return notifyLineId;
	}
	public void setType (Long type) {
		this.type = type;
	} 
	public Long getType () {
		return type;
	}
	public void setNotifiedUser (com.coral.foundation.security.model.BasicUser notifiedUser) {
		this.notifiedUser = notifiedUser;
	} 
	public com.coral.foundation.security.model.BasicUser getNotifiedUser () {
		return notifiedUser;
	}
	public void setStatus (Status status) {
		this.status = status;
	} 
	public Status getStatus () {
		return status;
	}
	public void setDiscuss (Discuss discuss) {
		this.discuss = discuss;
	} 
	public Discuss getDiscuss () {
		return discuss;
	}
	public void setTodo (ToDo todo) {
		this.todo = todo;
	} 
	public ToDo getTodo () {
		return todo;
	}

	public Long getID() {
		return getNotifyLineId();
	}
}

