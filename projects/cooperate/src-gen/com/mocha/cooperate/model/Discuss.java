package com.mocha.cooperate.model;
import java.util.*;
import java.math.BigDecimal;
import javax.persistence.*;
import com.coral.foundation.model.BaseEntity;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
  * <p>Title: com.mocha.cooperate.model.Discuss + "</p>
  * <p>Description: The Discuss entity </p>
  */
@Entity(name = "Discuss")
@Table(name = "T_DISCUSS")
public class Discuss extends BaseEntity {
	
	@Id()
	@Column (name = "DISCUSS_ID")
	@GeneratedValue(strategy = GenerationType. AUTO)
	private Long discussId;
	
	@Basic(optional = true)
	@Column(name = "TITLE" )
	private String title;
	
	
	@Basic(optional = true)
	@Column(name = "CONTENT" ,length = 1000)
	private String content;
	
	
	@Basic(optional = true)
	@Column(name = "STATUS" )
	private String status;
	
	
	@OneToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH }, targetEntity = com.coral.foundation.security.model.BasicUser.class, fetch=FetchType.EAGER)
	@Fetch(FetchMode.JOIN)
	private com.coral.foundation.security.model.BasicUser creator;
	
	@OneToMany(cascade = { CascadeType.ALL }, targetEntity = Attachment.class, fetch=FetchType.EAGER)
	@Fetch(FetchMode.SUBSELECT)
	private List<Attachment> attachments = new ArrayList<Attachment>();
	
	@OneToMany(cascade = { CascadeType.ALL }, targetEntity = Comment.class, fetch=FetchType.EAGER)
	@Fetch(FetchMode.SUBSELECT)
	private List<Comment> comments = new ArrayList<Comment>();
	
	@OneToMany(cascade = { CascadeType.ALL }, targetEntity = NotifyLine.class, fetch=FetchType.EAGER)
	@Fetch(FetchMode.SUBSELECT)
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
	
	public void setID(Long id) {
		setDiscussId(id);
	}
}

