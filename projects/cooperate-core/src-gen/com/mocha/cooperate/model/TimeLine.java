package com.mocha.cooperate.model;
import java.util.*;
import java.math.BigDecimal;
import javax.persistence.*;
import com.coral.foundation.persistence.*;

/**
  * <p>Title: com.mocha.cooperate.model.TimeLine + "</p>
  * <p>Description: The TimeLine entity </p>
  */
@Entity(name = "TimeLine")
@Table(name = "T_TIME_LINE")
public class TimeLine extends JPABaseEntity {
	
	@Id()
	@Column (name = "TIME_LINE_ID")
	@GeneratedValue(generator="TIMELINEID_SEQ")
	@TableGenerator(name="TIMELINEID_SEQ", table="SEQUENCE", pkColumnName="SEQ_NAME", valueColumnName="SEQ_COUNT", allocationSize=1)
	private Long timeLineId;
	
	@OneToOne(targetEntity = com.coral.foundation.security.model.BasicUser.class)
	private com.coral.foundation.security.model.BasicUser creator;
	
	@OneToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, targetEntity = Status.class)
	private Status status;
	
	@OneToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, targetEntity = Discuss.class)
	private Discuss discuss;
	
	@OneToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, targetEntity = ToDo.class)
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
}

