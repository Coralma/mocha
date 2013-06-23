package com.mocha.cooperate.model;
import java.util.*;
import java.math.BigDecimal;
import javax.persistence.*;
import com.coral.foundation.model.BaseEntity;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
  * <p>Title: com.mocha.cooperate.model.ChatNotify + "</p>
  * <p>Description: The ChatNotify entity </p>
  */
@Entity(name = "ChatNotify")
@Table(name = "T_CHAT_NOTIFY")
public class ChatNotify extends BaseEntity {
	
	@Id()
	@Column (name = "CHAT_NOTIFY_ID")
	@GeneratedValue(strategy = GenerationType. AUTO)
	private Long chatNotifyId;
	
	@OneToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH }, targetEntity = com.coral.foundation.security.model.BasicUser.class, fetch=FetchType.EAGER)
	@Fetch(FetchMode.JOIN)
	private com.coral.foundation.security.model.BasicUser notifiedUser;
	
	@Basic(optional = true)
	@Column(name = "MESSAGE_INDEX" )
	private Long messageIndex = new Long(0);
	
	

	public void setChatNotifyId (Long chatNotifyId) {
		this.chatNotifyId = chatNotifyId;
	} 
	public Long getChatNotifyId () {
		return chatNotifyId;
	}
	public void setNotifiedUser (com.coral.foundation.security.model.BasicUser notifiedUser) {
		this.notifiedUser = notifiedUser;
	} 
	public com.coral.foundation.security.model.BasicUser getNotifiedUser () {
		return notifiedUser;
	}
	public void setMessageIndex (Long messageIndex) {
		this.messageIndex = messageIndex;
	} 
	public Long getMessageIndex () {
		return messageIndex;
	}

	public Long getID() {
		return getChatNotifyId();
	}
	
	public void setID(Long id) {
		setChatNotifyId(id);
	}
}

