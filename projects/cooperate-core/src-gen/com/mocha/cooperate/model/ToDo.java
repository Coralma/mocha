package com.mocha.cooperate.model;
import java.util.*;
import java.math.BigDecimal;
import javax.persistence.*;
import com.coral.foundation.model.BaseEntity;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
  * <p>Title: com.mocha.cooperate.model.ToDo + "</p>
  * <p>Description: The ToDo entity </p>
  */
@Entity(name = "ToDo")
@Table(name = "T_ToDO")
public class ToDo extends BaseEntity {
	
	@Id()
	@Column (name = "TO_DO_ID")
	@GeneratedValue(strategy = GenerationType. AUTO)
	private Long toDoId;
	
	@Basic(optional = true)
	@Column(name = "NAME" )
	private String name;
	
	
	@Basic(optional = true)
	@Column(name = "DESCIPTION" )
	private String desciption;
	
	
	@Basic(optional = true)
	@Column(name = "STATUS" )
	private Long status = new Long(0);
	
	
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
	
	@OneToMany(cascade = { CascadeType.ALL }, targetEntity = SubToDoItem.class, fetch=FetchType.EAGER)
	@Fetch(FetchMode.SUBSELECT)
	@JoinColumn(name="TO_DO_ID")
	private List<SubToDoItem> subToDoItems = new ArrayList<SubToDoItem>();
	
	@OneToMany(cascade = { CascadeType.ALL }, targetEntity = Attachment.class, fetch=FetchType.EAGER)
	@Fetch(FetchMode.SUBSELECT)
	@JoinColumn(name="TO_DO_ID")
	private List<Attachment> attachments = new ArrayList<Attachment>();
	
	@OneToMany(cascade = { CascadeType.ALL }, targetEntity = Comment.class, fetch=FetchType.EAGER)
	@Fetch(FetchMode.SUBSELECT)
	@JoinColumn(name="TO_DO_ID")
	private List<Comment> comments = new ArrayList<Comment>();
	
	@OneToMany(cascade = { CascadeType.ALL }, targetEntity = NotifyLine.class, fetch=FetchType.EAGER)
	@Fetch(FetchMode.SUBSELECT)
	@JoinColumn(name="TO_DO_ID")
	private List<NotifyLine> notifyLines = new ArrayList<NotifyLine>();
	
	@OneToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH }, targetEntity = com.coral.foundation.security.model.BasicUser.class, fetch=FetchType.EAGER)
	@Fetch(FetchMode.JOIN)
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
	
	public void setID(Long id) {
		setToDoId(id);
	}
}

