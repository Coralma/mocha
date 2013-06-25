package com.mocha.cooperate.model;
import java.util.*;
import java.math.BigDecimal;
import javax.persistence.*;
import com.coral.foundation.model.BaseEntity;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
  * <p>Title: com.mocha.cooperate.model.Status + "</p>
  * <p>Description: The Status entity </p>
  */
@Entity(name = "Status")
@Table(name = "T_STATUS")
public class Status extends BaseEntity {
	
	@Id()
	@Column (name = "STATUS_ID")
	@GeneratedValue(strategy = GenerationType. AUTO)
	private Long statusId;
	
	@Basic(optional = true)
	@Column(name = "CONTENT" ,length = 1000)
	private String content;
	
	
	@OneToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH }, targetEntity = com.coral.foundation.security.model.BasicUser.class, fetch=FetchType.EAGER)
	@Fetch(FetchMode.JOIN)
	private com.coral.foundation.security.model.BasicUser creator;
	
	@Basic(optional = true)
	@Column(name = "TYPE" )
	private String type;
	
	
	@OneToMany(cascade = { CascadeType.ALL }, targetEntity = Attachment.class, fetch=FetchType.EAGER)
	@Fetch(FetchMode.SUBSELECT)
	@JoinColumn(name="STATUS_ID")
	private List<Attachment> attachments = new ArrayList<Attachment>();
	
	@OneToMany(cascade = { CascadeType.ALL }, targetEntity = Comment.class, fetch=FetchType.EAGER)
	@Fetch(FetchMode.SUBSELECT)
	@JoinColumn(name="STATUS_ID")
	private List<Comment> comments = new ArrayList<Comment>();
	
	@OneToMany(cascade = { CascadeType.ALL }, targetEntity = NotifyLine.class, fetch=FetchType.EAGER)
	@Fetch(FetchMode.SUBSELECT)
	@JoinColumn(name="STATUS_ID")
	private List<NotifyLine> notifyLines = new ArrayList<NotifyLine>();
	

	public void setStatusId (Long statusId) {
		this.statusId = statusId;
	} 
	public Long getStatusId () {
		return statusId;
	}
	public void setContent (String content) {
		this.content = content;
	} 
	public String getContent () {
		return content;
	}
	public void setCreator (com.coral.foundation.security.model.BasicUser creator) {
		this.creator = creator;
	} 
	public com.coral.foundation.security.model.BasicUser getCreator () {
		return creator;
	}
	public void setType (String type) {
		this.type = type;
	} 
	public String getType () {
		return type;
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

	public Long getID() {
		return getStatusId();
	}
	
	public void setID(Long id) {
		setStatusId(id);
	}
}

