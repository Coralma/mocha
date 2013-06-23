/**
 * 
 */
package com.mocha.cooperate.page.event;

import com.mocha.cooperate.model.Chat;

/**
 * @author Coral
 *
 */
public interface ChatListener {

//	public void newChat();
	public void selectChat(Chat chat);
	public void saveChat(Chat chat);
	public void refreashMessage();
	public void postMessage(String message);
}
