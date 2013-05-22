package com.mocha.cooperate.model;
import java.util.*;
import java.math.BigDecimal;
import javax.persistence.*;
import com.coral.foundation.model.BaseEntity;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
  * <p>Title: com.mocha.cooperate.model.TimeLine + "</p>
  * <p>Description: The TimeLine entity </p>
  */
@Entity(name = "TimeLine")
@Table(name = "T_TIME_LINE")
public class TimeLine extends BaseEntity {
	
	@Id()
	@Column (name = "TIME_LINE_ID")
	@GeneratedValue(strategy = GenerationType. AUTO)
	private Long timeLineId;
	
	@OneToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH }, targetEntity = com.coral.foundation.security.model.BasicUser.class, fetch=FetchType.EAGER)
	@Fetch(FetchMode.JOIN)
	private com.coral.foundation.security.model.BasicUser creator;
	
	@OneToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH }, targetEntity = Status.class, fetch=FetchType.EAGER)
	@Fetch(FetchMode.JOIN)
	private Status status;
	
	@OneToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH }, targetEntity = Discuss.class, fetch=FetchType.EAGER)
	@Fetch(FetchMode.JOIN)
	private Discuss discuss;
	
	@OneToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH }, targetEntity = ToDo.class, fetch=FetchType.EAGER)
	@Fetch(FetchMode.JOIN)
	private ToDo todo;
	

	public void setTimeLineId (Long timeLineId) {
		this.timeLineId = timeLineId;
	} 
	public Long getTimeLineId () {
		return timeLineId;
	}
	public void setCreator (com.coral.foundation.security.model.BasicUser creator) {
		this.creator = creator;
	} 
	public com.coral.foundation.security.model.BasicUser getCreator () {
		return creator;
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
		return getTimeLineId();
	}
	
	public void setID(Long id) {
		setTimeLineId(id);
	}
}

