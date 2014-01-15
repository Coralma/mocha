/**
 * 
 */
package com.mocha.foundation.test;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.HtmlEmail;

import com.coral.foundation.email.EmailContent;
import com.coral.foundation.email.EmailUtils;
import com.coral.foundation.security.model.BasicUser;

/**
 * @author Coral
 *
 */
public class EmailTesting {


	private static String HOST = "smtp.exmail.qq.com";
	private static String USER = "cs@zhongan.com";
	private static int PORT = 465;
	private static String PASSWORD = "yunying1234";
	private static String FROM = "cs@zhongan.com";
	private static String NAME = "ZhongAn";
	private EmailContent emailContent;
	
	public EmailTesting(EmailContent emailContent) {
		this.emailContent = emailContent;
	}
	
	public void send() {
		for(BasicUser reciever : emailContent.getRecievers()) {
			try {
				HtmlEmail email = new HtmlEmail();
				email.setHostName(HOST);
				email.setSmtpPort(PORT);
				email.setAuthenticator(new DefaultAuthenticator(USER, PASSWORD));
				email.setSSLOnConnect(true);
				email.setFrom(FROM, NAME);
				email.setSubject(emailContent.getSubject());
				email.setMsg(emailContent.getContent());
				email.addTo(reciever.getEmail());
				email.send();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	
	/**
	 * @return the emailContent
	 */
	public EmailContent getEmailContent() {
		return emailContent;
	}

	/**
	 * @param emailContent the emailContent to set
	 */
	public void setEmailContent(EmailContent emailContent) {
		this.emailContent = emailContent;
	}

	public static void main(String[] args) {
		EmailContent emailContent = new EmailContent();
		emailContent.setContent("Hi Coral, it's a testing email for you.");
		emailContent.setSubject("mail testing message");
		BasicUser sender = new BasicUser();
		sender.setEmail("cs@zhongan.com");
		emailContent.setReciever(sender);
		
		EmailTesting testing = new EmailTesting(emailContent);
		testing.send();
//		EmailUtils emailUtils = new EmailUtils(emailContent);
//		emailUtils.start();
	}
}
