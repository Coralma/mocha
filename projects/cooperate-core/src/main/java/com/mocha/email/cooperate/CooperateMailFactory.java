/**
 * 
 */
package com.mocha.email.cooperate;

import com.coral.foundation.email.EmailContent;
import com.coral.foundation.security.model.BasicUser;
import com.mocha.cooperate.model.TimeLine;
import com.mocha.email.cooperate.template.NotifyEmail;
import com.mocha.email.cooperate.template.UserRegister;

/**
 * @author Coral
 *
 */
public class CooperateMailFactory {

	public static EmailContent getUserRegister(BasicUser basicUser) {
		EmailContent emailContent = new EmailContent();
		UserRegister register = new UserRegister();
		emailContent.setContent(register.getEmailContent(basicUser).toString());
		emailContent.setSubject(register.getSubject());
		emailContent.setSender(basicUser);
		return emailContent;
	}
	
	public static EmailContent getNotifyEmail(BasicUser sendUser, TimeLine timeLine, BasicUser recievedUser) {
		EmailContent emailContent = new EmailContent();
		NotifyEmail notify = new NotifyEmail();
		notify.init(sendUser, timeLine, recievedUser);
		emailContent.setContent(notify.getEmailContent().toString());
		emailContent.setSubject(notify.getSubject().toString());
		emailContent.setSender(sendUser);
		return emailContent;
	}
}
