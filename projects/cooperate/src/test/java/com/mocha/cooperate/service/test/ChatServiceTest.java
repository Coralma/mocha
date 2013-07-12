/**
 * 
 */
package com.mocha.cooperate.service.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.coral.foundation.security.service.BasicUserService;
import com.mocha.cooperate.model.Chat;
import com.mocha.cooperate.service.ChatService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"applicationContext.xml"})
public class ChatServiceTest {

	@Test
	public void testChatService() {
		ChatService chatService = new ChatService();
		BasicUserService userService = new BasicUserService();
		
		Chat chat = chatService.getChatByUsers(userService.findById(Long.valueOf(1)),userService.findById(Long.valueOf(4)));
		System.out.println(chat);
	}
}
