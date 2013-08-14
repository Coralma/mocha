package com.mocha.cooperate.model;
import java.util.*;
import java.math.BigDecimal;
import javax.persistence.*;
import com.coral.foundation.persistence.*;

/**
  * <p>Title: com.mocha.cooperate.model.Chat + "</p>
  * <p>Description: The Chat entity </p>
  */
@Entity(name = "Chat")
@Table(name = "T_CHAT")
public class Chat extends JPABaseEntity {
	
	@Id()
	@Column (name = "CHAT_ID")
	@GeneratedValue(generator="CHATID_SEQ")
	@TableGenerator(name="CHATID_SEQ", table="SEQUENCE", pkColumnName="SEQ_NAME", valueColumnName="SEQ_COUNT", allocationSize=1)
	private Long chatId;
	
	@Column(name = "TITLE" )
	private String title;
	
	
	@Column(name = "PERSON_NUMBER" )
	private Long personNumber;
	
	
	@OneToMany(targetEntity=ChatPerson.class, cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
	@JoinColumn(name="CHAT_ID")
	private List<ChatPerson> chatPersons = new ArrayList<ChatPerson>();
	
	@OneToMany(targetEntity=ChatMessage.class, cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
	@JoinColumn(name="CHAT_ID")
	private List<ChatMessage> chatMessages = new ArrayList<ChatMessage>();
	

	public void setChatId (Long chatId) {
		this.chatId = chatId;
	} 
	public Long getChatId () {
		return chatId;
	}
	public void setTitle (String title) {
		this.title = title;
	} 
	public String getTitle () {
		return title;
	}
	public void setPersonNumber (Long personNumber) {
		this.personNumber = personNumber;
	} 
	public Long getPersonNumber () {
		return personNumber;
	}
	public void setChatPersons (List<ChatPerson> chatPersons) {
		this.chatPersons = chatPersons;
	} 
	public List<ChatPerson> getChatPersons () {
		return chatPersons;
	}
	public void setChatMessages (List<ChatMessage> chatMessages) {
		this.chatMessages = chatMessages;
	} 
	public List<ChatMessage> getChatMessages () {
		return chatMessages;
	}

	public Long getID() {
		return getChatId();
	}
}

