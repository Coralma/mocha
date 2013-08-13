package com.mocha.cooperate.model;
import java.util.*;
import java.math.BigDecimal;
import javax.persistence.*;
import com.coral.foundation.persistence.*;

/**
  * <p>Title: com.mocha.cooperate.model.SubToDoItem + "</p>
  * <p>Description: The SubToDoItem entity </p>
  */
@Entity(name = "SubToDoItem")
@Table(name = "T_SubToDoItem")
public class SubToDoItem extends JPABaseEntity {
	
	@Id()
	@Column (name = "SUB_TO_DO_ITEM_ID")
	@GeneratedValue(generator="SUBTODOITEMID_SEQ")
	@TableGenerator(name="SUBTODOITEMID_SEQ", table="SEQUENCE", pkColumnName="SEQ_NAME", valueColumnName="SEQ_COUNT", allocationSize=1)
	private Long subToDoItemId;
	
	@Column(name = "CONTENT" )
	private String content;
	
	
	@OneToOne(targetEntity = com.coral.foundation.security.model.BasicUser.class)
	private com.coral.foundation.security.model.BasicUser assginedUser;
	
	@Column(name = "EXPIRED_DATE" )
	@Temporal(TemporalType.DATE)
	private Date expiredDate;
	
	@Column(name = "FINISH_DATE" )
	@Temporal(TemporalType.DATE)
	private Date finishDate;
	
	@Column(name = "STATUS" )
	private Long status = new Long(0);
	
	
	@ManyToOne
	@JoinColumn(name="ToDo")
	private ToDo ToDo;
	

	public void setSubToDoItemId (Long subToDoItemId) {
		this.subToDoItemId = subToDoItemId;
	} 
	public Long getSubToDoItemId () {
		return subToDoItemId;
	}
	public void setContent (String content) {
		this.content = content;
	} 
	public String getContent () {
		return content;
	}
	public void setAssginedUser (com.coral.foundation.security.model.BasicUser assginedUser) {
		this.assginedUser = assginedUser;
	} 
	public com.coral.foundation.security.model.BasicUser getAssginedUser () {
		return assginedUser;
	}
	public void setExpiredDate (Date expiredDate) {
		this.expiredDate = expiredDate;
	} 
	public Date getExpiredDate () {
		return expiredDate;
	}
	public void setFinishDate (Date finishDate) {
		this.finishDate = finishDate;
	} 
	public Date getFinishDate () {
		return finishDate;
	}
	public void setStatus (Long status) {
		this.status = status;
	} 
	public Long getStatus () {
		return status;
	}
	public void setToDo (ToDo ToDo) {
		this.ToDo = ToDo;
	} 
	public ToDo getToDo () {
		return ToDo;
	}

	public Long getID() {
		return getSubToDoItemId();
	}
}

