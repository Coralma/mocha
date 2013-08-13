package com.mocha.cooperate.model;
import java.util.*;
import java.math.BigDecimal;
import javax.persistence.*;
import com.coral.foundation.persistence.*;

/**
  * <p>Title: com.mocha.cooperate.model.ToDo + "</p>
  * <p>Description: The ToDo entity </p>
  */
@Entity(name = "ToDo")
@Table(name = "T_ToDO")
public class ToDo extends JPABaseEntity {
	
	@Id()
	@Column (name = "TO_DO_ID")
	@GeneratedValue(generator="TODOID_SEQ")
	@TableGenerator(name="TODOID_SEQ", table="SEQUENCE", pkColumnName="SEQ_NAME", valueColumnName="SEQ_COUNT", allocationSize=1)
	private Long toDoId;
	
	@Column(name = "NAME" )
	private String name;
	
	
	@Column(name = "DESCIPTION" )
	private String desciption;
	
	
	@Column(name = "STATUS" )
	private Long status = new Long(0);
	
	
	@OneToOne(targetEntity = com.coral.foundation.security.model.BasicUser.class)
	private com.coral.foundation.security.model.BasicUser assginedUser;
	
	@Column(name = "EXPIRED_DATE" )
	@Temporal(TemporalType.DATE)
	private Date expiredDate;
	
	@Column(name = "FINISH_DATE" )
	@Temporal(TemporalType.DATE)
	private Date finishDate;
	
	@OneToMany(targetEntity=SubToDoItem.class, cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
	@JoinColumn(name="TO_DO_ID")
	private List<SubToDoItem> subToDoItems = new ArrayList<SubToDoItem>();
	
	@OneToMany(targetEntity=Attachment.class, cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
	@JoinColumn(name="TO_DO_ID")
	private List<Attachment> attachments = new ArrayList<Attachment>();
	
	@OneToMany(targetEntity=Comment.class, cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
	@JoinColumn(name="TO_DO_ID")
	private List<Comment> comments = new ArrayList<Comment>();
	
	@OneToMany(targetEntity=NotifyLine.class, cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
	@JoinColumn(name="TO_DO_ID")
	private List<NotifyLine> notifyLines = new ArrayList<NotifyLine>();
	
	@OneToOne(targetEntity = com.coral.foundation.security.model.BasicUser.class)
	private com.coral.foundation.security.model.BasicUser creator;
	

	public void setToDoId (Long toDoId) {
		this.toDoId = toDoId;
	} 
	public Long getToDoId () {
		return toDoId;
	}
	public void setName (String name) {
		this.name = name;
	} 
	public String getName () {
		return name;
	}
	public void setDesciption (String desciption) {
		this.desciption = desciption;
	} 
	public String getDesciption () {
		return desciption;
	}
	public void setStatus (Long status) {
		this.status = status;
	} 
	public Long getStatus () {
		return status;
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
	public void setSubToDoItems (List<SubToDoItem> subToDoItems) {
		this.subToDoItems = subToDoItems;
	} 
	public List<SubToDoItem> getSubToDoItems () {
		return subToDoItems;
	}
	public void setAttachments (List<Attachment> attachments) {
		this.attachments = attachments;
	} 
	public List<Attachment> getAttachments () {
		return attachments;
	}
	public void setComments (List<Comment> comments) {
		this.comments = comments;
	} 
	public List<Comment> getComments () {
		return comments;
	}
	public void setNotifyLines (List<NotifyLine> notifyLines) {
		this.notifyLines = notifyLines;
	} 
	public List<NotifyLine> getNotifyLines () {
		return notifyLines;
	}
	public void setCreator (com.coral.foundation.security.model.BasicUser creator) {
		this.creator = creator;
	} 
	public com.coral.foundation.security.model.BasicUser getCreator () {
		return creator;
	}

	public Long getID() {
		return getToDoId();
	}
}

