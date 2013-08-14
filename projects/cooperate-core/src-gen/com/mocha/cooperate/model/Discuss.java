package com.mocha.cooperate.model;
import java.util.*;
import java.math.BigDecimal;
import javax.persistence.*;
import com.coral.foundation.persistence.*;

/**
  * <p>Title: com.mocha.cooperate.model.Discuss + "</p>
  * <p>Description: The Discuss entity </p>
  */
@Entity(name = "Discuss")
@Table(name = "T_DISCUSS")
public class Discuss extends JPABaseEntity {
	
	@Id()
	@Column (name = "DISCUSS_ID")
	@GeneratedValue(generator="DISCUSSID_SEQ")
	@TableGenerator(name="DISCUSSID_SEQ", table="SEQUENCE", pkColumnName="SEQ_NAME", valueColumnName="SEQ_COUNT", allocationSize=1)
	private Long discussId;
	
	@Column(name = "TITLE" ,length = 500)
	private String title;
	
	
	@Column(name = "CONTENT" ,length = 1000)
	private String content;
	
	
	@Column(name = "STATUS" )
	private String status;
	
	
	@OneToOne(targetEntity = com.coral.foundation.security.model.BasicUser.class)
	private com.coral.foundation.security.model.BasicUser creator;
	
	@OneToMany(targetEntity=Attachment.class, cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
	@JoinColumn(name="DISCUSS_ID")
	private List<Attachment> attachments = new ArrayList<Attachment>();
	
	@OneToMany(targetEntity=Comment.class, cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
	@JoinColumn(name="DISCUSS_ID")
	private List<Comment> comments = new ArrayList<Comment>();
	
	@OneToMany(targetEntity=NotifyLine.class, cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
	@JoinColumn(name="DISCUSS_ID")
	private List<NotifyLine> notifyLines = new ArrayList<NotifyLine>();
	

	public void setDiscussId (Long discussId) {
		this.discussId = discussId;
	} 
	public Long getDiscussId () {
		return discussId;
	}
	public void setTitle (String title) {
		this.title = title;
	} 
	public String getTitle () {
		return title;
	}
	public void setContent (String content) {
		this.content = content;
	} 
	public String getContent () {
		return content;
	}
	public void setStatus (String status) {
		this.status = status;
	} 
	public String getStatus () {
		return status;
	}
	public void setCreator (com.coral.foundation.security.model.BasicUser creator) {
		this.creator = creator;
	} 
	public com.coral.foundation.security.model.BasicUser getCreator () {
		return creator;
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
		return getDiscussId();
	}
}

