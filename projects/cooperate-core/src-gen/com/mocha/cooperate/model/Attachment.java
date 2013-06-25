package com.mocha.cooperate.model;
import java.util.*;
import java.math.BigDecimal;
import javax.persistence.*;
import com.coral.foundation.model.BaseEntity;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
  * <p>Title: com.mocha.cooperate.model.Attachment + "</p>
  * <p>Description: The Attachment entity </p>
  */
@Entity(name = "Attachment")
@Table(name = "T_ATTACHMENT")
public class Attachment extends BaseEntity {
	
	@Id()
	@Column (name = "ATTACHMENT_ID")
	@GeneratedValue(strategy = GenerationType. AUTO)
	private Long attachmentId;
	
	@Basic(optional = true)
	@Column(name = "FILE_NAME" )
	private String fileName;
	
	
	@Basic(optional = true)
	@Column(name = "FILE_PATH" )
	private String filePath;
	
	
	@Basic(optional = true)
	@Column(name = "TYPE" )
	private String type;
	
	
	@Basic(optional = true)
	@Column(name = "FILE_SIZE" )
	private Long fileSize;
	
	
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
	
	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST,CascadeType.REFRESH }, targetEntity = Comment.class, fetch=FetchType.EAGER)
	@JoinColumns({ @JoinColumn(name = "comment") })
	@Fetch(FetchMode.JOIN)
	private Comment comment;
	

	public void setAttachmentId (Long attachmentId) {
		this.attachmentId = attachmentId;
	} 
	public Long getAttachmentId () {
		return attachmentId;
	}
	public void setFileName (String fileName) {
		this.fileName = fileName;
	} 
	public String getFileName () {
		return fileName;
	}
	public void setFilePath (String filePath) {
		this.filePath = filePath;
	} 
	public String getFilePath () {
		return filePath;
	}
	public void setType (String type) {
		this.type = type;
	} 
	public String getType () {
		return type;
	}
	public void setFileSize (Long fileSize) {
		this.fileSize = fileSize;
	} 
	public Long getFileSize () {
		return fileSize;
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
	public void setComment (Comment comment) {
		this.comment = comment;
	} 
	public Comment getComment () {
		return comment;
	}

	public Long getID() {
		return getAttachmentId();
	}
	
	public void setID(Long id) {
		setAttachmentId(id);
	}
}

