package com.mocha.cooperate.model;
import java.util.*;
import java.math.BigDecimal;
import javax.persistence.*;
import com.coral.foundation.persistence.*;

/**
  * <p>Title: com.mocha.cooperate.model.Status + "</p>
  * <p>Description: The Status entity </p>
  */
@Entity(name = "Status")
@Table(name = "T_STATUS")
public class Status extends JPABaseEntity {
	
	@Id()
	@Column (name = "STATUS_ID")
	@GeneratedValue(generator="STATUSID_SEQ")
	@TableGenerator(name="STATUSID_SEQ", table="SEQUENCE", pkColumnName="SEQ_NAME", valueColumnName="SEQ_COUNT", allocationSize=1)
	private Long statusId;
	
	@Column(name = "CONTENT" ,length = 1000)
	private String content;
	
	
	@OneToOne(targetEntity = com.coral.foundation.security.model.BasicUser.class)
	private com.coral.foundation.security.model.BasicUser creator;
	
	@Column(name = "TYPE" )
	private String type;
	
	
	@OneToMany(targetEntity=Attachment.class, cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
	@JoinColumn(name="STATUS_ID")
	private List<Attachment> attachments = new ArrayList<Attachment>();
	
	@OneToMany(targetEntity=Comment.class, cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
	@JoinColumn(name="STATUS_ID")
	private List<Comment> comments = new ArrayList<Comment>();
	
	@OneToMany(targetEntity=NotifyLine.class, cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
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
}

