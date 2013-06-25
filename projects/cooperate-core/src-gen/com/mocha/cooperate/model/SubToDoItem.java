package com.mocha.cooperate.model;
import java.util.*;
import java.math.BigDecimal;
import javax.persistence.*;
import com.coral.foundation.model.BaseEntity;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
  * <p>Title: com.mocha.cooperate.model.SubToDoItem + "</p>
  * <p>Description: The SubToDoItem entity </p>
  */
@Entity(name = "SubToDoItem")
@Table(name = "T_SubToDoItem")
public class SubToDoItem extends BaseEntity {
	
	@Id()
	@Column (name = "SUB_TO_DO_ITEM_ID")
	@GeneratedValue(strategy = GenerationType. AUTO)
	private Long subToDoItemId;
	
	@Basic(optional = true)
	@Column(name = "CONTENT" )
	private String content;
	
	
	@OneToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH }, targetEntity = com.coral.foundation.security.model.BasicUser.class, fetch=FetchType.EAGER)
	@Fetch(FetchMode.JOIN)
	private com.coral.foundation.security.model.BasicUser assginedUser;
	
	@Basic(optional = true)
	@Column(name = "EXPIRED_DATE" )
	@Temporal(TemporalType.DATE)
	private Date expiredDate;
	
	@Basic(optional = true)
	@Column(name = "FINISH_DATE" )
	@Temporal(TemporalType.DATE)
	private Date finishDate;
	
	@Basic(optional = true)
	@Column(name = "STATUS" )
	private Long status = new Long(0);
	
	
	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST,CascadeType.REFRESH }, targetEntity = ToDo.class, fetch=FetchType.EAGER)
	@JoinColumns({ @JoinColumn(name = "ToDo") })
	@Fetch(FetchMode.JOIN)
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
	
	public void setID(Long id) {
		setSubToDoItemId(id);
	}
}

