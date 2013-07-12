package com.mocha.cooperate.model;
import java.util.*;
import java.math.BigDecimal;
import javax.persistence.*;
import com.coral.foundation.model.BaseEntity;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
  * <p>Title: com.mocha.cooperate.model.NotifyLine + "</p>
  * <p>Description: The NotifyLine entity </p>
  */
@Entity(name = "NotifyLine")
@Table(name = "T_NOTIFY_LINE")
public class NotifyLine extends BaseEntity {
	
	@Id()
	@Column (name = "NOTIFY_LINE_ID")
	@GeneratedValue(strategy = GenerationType. AUTO)
	private Long notifyLineId;
	
	@Basic(optional = true)
	@Column(name = "TYPE" )
	private Long type = new Long(1);
	
	
	@OneToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH }, targetEntity = com.coral.foundation.security.model.BasicUser.class, fetch=FetchType.EAGER)
	@Fetch(FetchMode.JOIN)
	private com.coral.foundation.security.model.BasicUser notifiedUser;
	
	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST,CascadeType.REFRESH }, targetEntity = Status.class, fetch=FetchType.EAGER)
	@JoinColumns({ @JoinColumn(name = "status") })
	@Fetch(FetchMode.JOIN)
	private Status status;
	
	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST,CascadeType.REFRESH }, targetEntity = Discuss.class, fetch=FetchType.EAGER)
	@JoinColumns({ @JoinColumn(name = "discuss") })
	@Fetch(FetchMode.JOIN)
	private Discuss discuss;
	
	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST,CascadeType.REFRESH }, targetEntity = ToDo.class, fetch=FetchType.EAGER)
	@JoinColumns({ @JoinColumn(name = "todo") })
	@Fetch(FetchMode.JOIN)
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
	
	public void setID(Long id) {
		setNotifyLineId(id);
	}
}

