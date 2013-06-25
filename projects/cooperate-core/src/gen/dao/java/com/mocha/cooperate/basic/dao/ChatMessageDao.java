package com.mocha.cooperate.basic.dao;
import java.util.List;
import com.coral.foundation.jpa.Dao;
import com.mocha.cooperate.model.*;

/**
  * ChatMessageDao is a auto Generated class. Please don't modify it.
  * @author Coral
  */
public interface ChatMessageDao extends Dao<ChatMessage> {
	
	public List<ChatMessage> loadNewMessage(ChatMessage lastMessage, Chat currentChat);
}

