package com.mocha.cooperate.model;
import java.util.*;
import java.math.BigDecimal;
import javax.persistence.*;
import com.coral.foundation.persistence.*;

/**
  * <p>Title: com.mocha.cooperate.model.Attachment + "</p>
  * <p>Description: The Attachment entity </p>
  */
@Entity(name = "Attachment")
@Table(name = "T_ATTACHMENT")
public class Attachment extends JPABaseEntity {
	
	@Id()
	@Column (name = "ATTACHMENT_ID")
	@GeneratedValue(generator="ATTACHMENTID_SEQ")
	@TableGenerator(name="ATTACHMENTID_SEQ", table="SEQUENCE", pkColumnName="SEQ_NAME", valueColumnName="SEQ_COUNT", allocationSize=1)
	private Long attachmentId;
	
	@Column(name = "FILE_NAME" )
	private String fileName;
	
	
	@Column(name = "FILE_PATH" )
	private String filePath;
	
	
	@Column(name = "TYPE" )
	private String type;
	
	
	@Column(name = "FILE_SIZE" )
	private Long fileSize;
	
	
	@ManyToOne
	@JoinColumn(name="status")
	private Status status;
	
	@ManyToOne
	@JoinColumn(name="discuss")
	private Discuss discuss;
	
	@ManyToOne
	@JoinColumn(name="todo")
	private ToDo todo;
	
	@ManyToOne
	@JoinColumn(name="comment")
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
}

