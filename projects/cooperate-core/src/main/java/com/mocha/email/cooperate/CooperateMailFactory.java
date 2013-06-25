/**
 * 
 */
package com.mocha.email.cooperate;

import com.coral.foundation.email.EmailContent;
import com.coral.foundation.security.model.BasicUser;
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
}
