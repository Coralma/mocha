/**
 * 
 */
package com.coral.foundation.utils;

import java.util.Locale;
import java.util.ResourceBundle;

import org.springframework.context.MessageSource;

import com.coral.foundation.spring.bean.SpringContextUtils;

/**
 * @author Administrator
 * 
 */
public class Message {

	// no spring
//	private ResourceBundle resourceBundle = ResourceBundle.getBundle("message_en");
//
//	public String getString(String key) {
//		try {
//			return new String(resourceBundle.getString(key).getBytes(
//					"ISO-8859-1"), "UTF8");
//		} catch (Exception e) {
//			return key;
//		}
//	}
//	
//	public void changeLanguage(String language) {
//		String resource = "message_" + language;
//		changeResource(resource);
//	}
//	
//	public void changeResource(String bundle) {
//		resourceBundle = ResourceBundle.getBundle(bundle);
//	}
	
	// using spring
	private MessageSource messageSource = SpringContextUtils.getBean("messageSource", MessageSource.class);
	private Locale locale;
	
	public Message(Locale locale) {
		this.locale = locale;
	}

	public String getString(String key) {
		try {
			return messageSource.getMessage(key, null, locale);
		} catch (Exception e) {
			return key;
		}
	}
	/**
	 * @return the messageSource
	 */
	public MessageSource getMessageSource() {
		return messageSource;
	}

	/**
	 * @param messageSource the messageSource to set
	 */
	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}
	
	
}
