/**
 * 
 */
package com.coral.foundation.email;

import java.util.Collection;
import java.util.List;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.HtmlEmail;

import com.coral.foundation.security.model.BasicUser;

/**
 * @author Coral
 *
 */
public class EmailUtils extends Thread {
	
	private static String HOST = "smtp.mail.yahoo.com";
	private static String USER = "mocha.platform";
	private static int PORT = 465;
	private static String PASSWORD = "mochaplatform2013";
	private static String FROM = "mocha.platform@yahoo.com";
	private static String NAME = "Mocha Platform";
	private EmailContent emailContent;
	private Collection<BasicUser> revievers;
	
	public EmailUtils(EmailContent emailContent) {
		this.emailContent = emailContent;
	}
	
	public EmailUtils(EmailContent emailContent, Collection<BasicUser> revievers) {
		this.emailContent = emailContent;
		this.revievers = revievers;
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
	
	@Override
	public void run() {
		send();
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
		sender.setEmail("maqujun@gmail.com");
		emailContent.setReciever(sender);
		EmailUtils emailUtils = new EmailUtils(emailContent);
		emailUtils.start();
	}
	
}
