package com.mocha.cooperate.model;
import java.util.*;
import java.math.BigDecimal;
import javax.persistence.*;
import com.coral.foundation.persistence.*;

/**
  * <p>Title: com.mocha.cooperate.model.ChatNotify + "</p>
  * <p>Description: The ChatNotify entity </p>
  */
@Entity(name = "ChatNotify")
@Table(name = "T_CHAT_NOTIFY")
public class ChatNotify extends JPABaseEntity {
	
	@Id()
	@Column (name = "CHAT_NOTIFY_ID")
	@GeneratedValue(generator="CHATNOTIFYID_SEQ")
	@TableGenerator(name="CHATNOTIFYID_SEQ", table="SEQUENCE", pkColumnName="SEQ_NAME", valueColumnName="SEQ_COUNT", allocationSize=1)
	private Long chatNotifyId;
	
	@OneToOne(targetEntity = com.coral.foundation.security.model.BasicUser.class)
	private com.coral.foundation.security.model.BasicUser notifiedUser;
	
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
}

