package com.mocha.cooperate.model;
import java.util.*;
import java.math.BigDecimal;
import javax.persistence.*;
import com.coral.foundation.model.BaseEntity;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
  * <p>Title: com.mocha.cooperate.model.Chat + "</p>
  * <p>Description: The Chat entity </p>
  */
@Entity(name = "Chat")
@Table(name = "T_CHAT")
public class Chat extends BaseEntity {
	
	@Id()
	@Column (name = "CHAT_ID")
	@GeneratedValue(strategy = GenerationType. AUTO)
	private Long chatId;
	
	@Basic(optional = true)
	@Column(name = "TITLE" )
	private String title;
	
	
	@Basic(optional = true)
	@Column(name = "PERSON_NUMBER" )
	private Long personNumber;
	
	
	@OneToMany(cascade = { CascadeType.ALL }, targetEntity = ChatPerson.class, fetch=FetchType.EAGER)
	@Fetch(FetchMode.SUBSELECT)
	private List<ChatPerson> chatPersons = new ArrayList<ChatPerson>();
	
	@OneToMany(cascade = { CascadeType.ALL }, targetEntity = ChatMessage.class, fetch=FetchType.EAGER)
	@Fetch(FetchMode.SUBSELECT)
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
	
	public void setID(Long id) {
		setChatId(id);
	}
}

