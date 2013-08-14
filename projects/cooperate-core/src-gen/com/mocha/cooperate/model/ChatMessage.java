package com.mocha.cooperate.model;
import java.util.*;
import java.math.BigDecimal;
import javax.persistence.*;
import com.coral.foundation.persistence.*;

/**
  * <p>Title: com.mocha.cooperate.model.ChatMessage + "</p>
  * <p>Description: The ChatMessage entity </p>
  */
@Entity(name = "ChatMessage")
@Table(name = "T_CHAT_MESSAGE")
public class ChatMessage extends JPABaseEntity {
	
	@Id()
	@Column (name = "CHAT_MESSAGE_ID")
	@GeneratedValue(generator="CHATMESSAGEID_SEQ")
	@TableGenerator(name="CHATMESSAGEID_SEQ", table="SEQUENCE", pkColumnName="SEQ_NAME", valueColumnName="SEQ_COUNT", allocationSize=1)
	private Long chatMessageId;
	
	@ManyToOne
	@JoinColumn(name="person")
	private com.coral.foundation.security.model.BasicUser person;
	
	@Column(name = "MESSAGE" )
	private String message;
	
	
	@ManyToOne
	@JoinColumn(name="chat")
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
}

