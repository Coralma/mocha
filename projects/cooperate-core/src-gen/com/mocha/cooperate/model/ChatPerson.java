package com.mocha.cooperate.model;
import java.util.*;
import java.math.BigDecimal;
import javax.persistence.*;
import com.coral.foundation.model.BaseEntity;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
  * <p>Title: com.mocha.cooperate.model.ChatPerson + "</p>
  * <p>Description: The ChatPerson entity </p>
  */
@Entity(name = "ChatPerson")
@Table(name = "T_CHAT_PERSON")
public class ChatPerson extends BaseEntity {
	
	@Id()
	@Column (name = "CHAT_PERSON_ID")
	@GeneratedValue(strategy = GenerationType. AUTO)
	private Long chatPersonId;
	
	@OneToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH }, targetEntity = com.coral.foundation.security.model.BasicUser.class, fetch=FetchType.EAGER)
	@Fetch(FetchMode.JOIN)
	private com.coral.foundation.security.model.BasicUser person;
	
	@Basic(optional = true)
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
	
	public void setID(Long id) {
		setChatPersonId(id);
	}
}

