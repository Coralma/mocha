package com.mocha.cooperate.model;
import java.util.*;
import java.math.BigDecimal;
import javax.persistence.*;
import com.coral.foundation.model.BaseEntity;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
  * <p>Title: com.mocha.cooperate.model.ChatMessage + "</p>
  * <p>Description: The ChatMessage entity </p>
  */
@Entity(name = "ChatMessage")
@Table(name = "T_CHAT_MESSAGE")
public class ChatMessage extends BaseEntity {
	
	@Id()
	@Column (name = "CHAT_MESSAGE_ID")
	@GeneratedValue(strategy = GenerationType. AUTO)
	private Long chatMessageId;
	
	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST,CascadeType.REFRESH }, targetEntity = com.coral.foundation.security.model.BasicUser.class, fetch=FetchType.EAGER)
	@JoinColumns({ @JoinColumn(name = "person") })
	@Fetch(FetchMode.JOIN)
	private com.coral.foundation.security.model.BasicUser person;
	
	@Basic(optional = true)
	@Column(name = "MESSAGE" )
	private String message;
	
	
	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST,CascadeType.REFRESH }, targetEntity = Chat.class, fetch=FetchType.EAGER)
	@JoinColumns({ @JoinColumn(name = "chat") })
	@Fetch(FetchMode.JOIN)
	private Chat chat;
	

	public void setChatMessageId (Long chatMessageId) {
		this.chatMessageId = chatMessageId;
	} 
	public Long getChatMessageId () {
		return chatMessageId;
	}
	public void setPerson (com.coral.foundation.security.model.BasicUser person) {
		this.person = person;
	} 
	public com.coral.foundation.security.model.BasicUser getPerson () {
		return person;
	}
	public void setMessage (String message) {
		this.message = message;
	} 
	public String getMessage () {
		return message;
	}
	public void setChat (Chat chat) {
		this.chat = chat;
	} 
	public Chat getChat () {
		return chat;
	}

	public Long getID() {
		return getChatMessageId();
	}
	
	public void setID(Long id) {
		setChatMessageId(id);
	}
}

