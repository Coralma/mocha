package com.mocha.cooperate.model;
import java.util.*;
import java.math.BigDecimal;
import javax.persistence.*;
import com.coral.foundation.persistence.*;

/**
  * <p>Title: com.mocha.cooperate.model.Comment + "</p>
  * <p>Description: The Comment entity </p>
  */
@Entity(name = "Comment")
@Table(name = "T_COMMENT")
public class Comment extends JPABaseEntity {
	
	@Id()
	@Column (name = "COMMENT_ID")
	@GeneratedValue(generator="COMMENTID_SEQ")
	@TableGenerator(name="COMMENTID_SEQ", table="SEQUENCE", pkColumnName="SEQ_NAME", valueColumnName="SEQ_COUNT", allocationSize=1)
	private Long commentId;
	
	@Column(name = "CONTENT" ,length = 1000)
	private String content;
	
	
	@ManyToOne
	@JoinColumn(name="status")
	private Status status;
	
	@ManyToOne
	@JoinColumn(name="discuss")
	private Discuss discuss;
	
	@ManyToOne
	@JoinColumn(name="todo")
	private ToDo todo;
	
	@OneToOne(targetEntity = com.coral.foundation.security.model.BasicUser.class)
	private com.coral.foundation.security.model.BasicUser creator;
	
	@OneToMany(targetEntity=Attachment.class, cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
	@JoinColumn(name="COMMENT_ID")
	private List<Attachment> attachments = new ArrayList<Attachment>();
	

	public void setCommentId (Long commentId) {
		this.commentId = commentId;
	} 
	public Long getCommentId () {
		return commentId;
	}
	public void setContent (String content) {
		this.content = content;
	} 
	public String getContent () {
		return content;
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

	public Long getID() {
		return getCommentId();
	}
}

