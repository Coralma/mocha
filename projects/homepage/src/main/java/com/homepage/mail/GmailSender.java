package com.homepage.mail;

import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.internet.MimeMessage.RecipientType;

import java.util.*;

public class GmailSender {

	// private static String HOST = "smtp.gmail.com";
	private static String HOST = "smtp.mail.yahoo.com";
	private static String USER = "mocha.project@yahoo.com";
	private static String PASSWORD = "vzP@ssw0rd1";
	private static String PORT = "465";
	private static String FROM = "mocha.project@yahoo.com";
	// private static String TO = "vancezhao@gmail.com";

	private static String STARTTLS = "true";
	private static String AUTH = "true";
	private static String DEBUG = "true";
	private static String SOCKET_FACTORY = "javax.net.ssl.SSLSocketFactory";
	private static String SUBJECT = "Greetings from Mocha Platform team";
	private static String mailBody = "<h4>Thank you for the registiation</h4>\n"
			+ "<h4>Our duty is making the daily work more efficiency.</h4>\n"
			+ "<h5>Here is your username and password:</h5>\n"
			+ "<h5>UserName: root</h5>\n"
			+ "<h5>Password: root</h5>\n"
			+ "http://ec2-54-248-1-49.ap-northeast-1.compute.amazonaws.com:8080/cooperate";

	public static synchronized void send(String userEmail) {

		// Use Properties object to set environment properties
		Properties props = new Properties();
		props.put("mail.smtp.host", HOST);
		props.put("mail.smtp.port", PORT);
		props.put("mail.smtp.user", USER);
		props.put("mail.smtp.auth", AUTH);
		props.put("mail.smtp.starttls.enable", STARTTLS);
		props.put("mail.smtp.debug", DEBUG);

		props.put("mail.smtp.socketFactory.port", PORT);
		props.put("mail.smtp.socketFactory.class", SOCKET_FACTORY);
		props.put("mail.smtp.socketFactory.fallback", "false");
		try {
			// Obtain the default mail session
			Session session = Session.getDefaultInstance(props, null);
			session.setDebug(true);
			// Construct the mail message
			MimeMessage message = new MimeMessage(session);
			message.setText(mailBody, "UTF-8", "html");
			message.setSubject(SUBJECT);
			message.setFrom(new InternetAddress(FROM));
			message.addRecipient(RecipientType.TO, new InternetAddress(
					userEmail));
			message.saveChanges();
			// Use Transport to deliver the message
			Transport transport = session.getTransport("smtp");
			transport.connect(HOST, USER, PASSWORD);
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	public static void testSendMail() {
		send("vancezhao@gmail.com");
	}

	public static void main(String args[]) {
		GmailSender.testSendMail();
	}

}
