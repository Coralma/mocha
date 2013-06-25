package com.mocha.cooperate.model;
import java.util.*;
import java.math.BigDecimal;
import javax.persistence.*;
import com.coral.foundation.model.BaseEntity;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
  * <p>Title: com.mocha.cooperate.model.Comment + "</p>
  * <p>Description: The Comment entity </p>
  */
@Entity(name = "Comment")
@Table(name = "T_COMMENT")
public class Comment extends BaseEntity {
	
	@Id()
	@Column (name = "COMMENT_ID")
	@GeneratedValue(strategy = GenerationType. AUTO)
	private Long commentId;
	
	@Basic(optional = true)
	@Column(name = "CONTENT" )
	private String content;
	
	
	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST,CascadeType.REFRESH }, targetEntity = Status.class, fetch=FetchType.EAGER)
	@JoinColumns({ @JoinColumn(name = "status") })
	@Fetch(FetchMode.JOIN)
	private Status status;
	
	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST,CascadeType.REFRESH }, targetEntity = Discuss.class, fetch=FetchType.EAGER)
	@JoinColumns({ @JoinColumn(name = "discuss") })
	@Fetch(FetchMode.JOIN)
	private Discuss discuss;
	
	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST,CascadeType.REFRESH }, targetEntity = ToDo.class, fetch=FetchType.EAGER)
	@JoinColumns({ @JoinColumn(name = "todo") })
	@Fetch(FetchMode.JOIN)
	private ToDo todo;
	
	@OneToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH }, targetEntity = com.coral.foundation.security.model.BasicUser.class, fetch=FetchType.EAGER)
	@Fetch(FetchMode.JOIN)
	private com.coral.foundation.security.model.BasicUser creator;
	
	@OneToMany(cascade = { CascadeType.ALL }, targetEntity = Attachment.class, fetch=FetchType.EAGER)
	@Fetch(FetchMode.SUBSELECT)
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
	
	public void setID(Long id) {
		setCommentId(id);
	}
}

