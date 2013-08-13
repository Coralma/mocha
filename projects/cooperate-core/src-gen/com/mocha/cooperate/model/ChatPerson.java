package com.mocha.cooperate.model;
import java.util.*;
import java.math.BigDecimal;
import javax.persistence.*;
import com.coral.foundation.persistence.*;

/**
  * <p>Title: com.mocha.cooperate.model.ChatPerson + "</p>
  * <p>Description: The ChatPerson entity </p>
  */
@Entity(name = "ChatPerson")
@Table(name = "T_CHAT_PERSON")
public class ChatPerson extends JPABaseEntity {
	
	@Id()
	@Column (name = "CHAT_PERSON_ID")
	@GeneratedValue(generator="CHATPERSONID_SEQ")
	@TableGenerator(name="CHATPERSONID_SEQ", table="SEQUENCE", pkColumnName="SEQ_NAME", valueColumnName="SEQ_COUNT", allocationSize=1)
	private Long chatPersonId;
	
	@OneToOne(targetEntity = com.coral.foundation.security.model.BasicUser.class)
	private com.coral.foundation.security.model.BasicUser person;
	
	@Column(name = "LEADER" )
	private Long leader = new Long(0);
	
	

	public void setChatPersonId (Long chatPersonId) {
		this.chatPersonId = chatPersonId;
	} 
	public Long getChatPersonId () {
		return chatPersonId;
	}
	public void setPerson (com.coral.foundation.security.model.BasicUser person) {
		this.person = person;
	} 
	public com.coral.foundation.security.model.BasicUser getPerson () {
		return person;
	}
	public void setLeader (Long leader) {
		this.leader = leader;
	} 
	public Long getLeader () {
		return leader;
	}

	public Long getID() {
		return getChatPersonId();
	}
}

