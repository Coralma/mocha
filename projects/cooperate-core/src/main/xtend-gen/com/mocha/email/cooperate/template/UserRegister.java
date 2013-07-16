package com.mocha.email.cooperate.template;

import com.coral.foundation.security.model.BasicUser;
import org.eclipse.xtend2.lib.StringConcatenation;

@SuppressWarnings("all")
public class UserRegister {
  public String getSubject() {
    return "Welcome to Mocha Platform!";
  }
  
  public CharSequence getEmailContent(final BasicUser user) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("<div style=\"font-size: 12px; font-family: Arial; color: #52555A\">");
    _builder.newLine();
    _builder.append("Dear ");
    String _realName = user.getRealName();
    _builder.append(_realName, "");
    _builder.append("<br/>");
    _builder.newLineIfNotEmpty();
    _builder.append("Thanks for joining the Mocha Platform! You can login the URL: <a href=\"http://www.mocha-platform.com/cooperate/\">http://www.mocha-platform.com/cooperate</a><br/> ");
    _builder.newLine();
    _builder.newLine();
    _builder.append("Your Mocha Platform login is : <span style=\"color: #0174DF;\">");
    String _userName = user.getUserName();
    _builder.append(_userName, "");
    _builder.append("</span>");
    _builder.newLineIfNotEmpty();
    _builder.append("Your Mocha Platform password is : <span style=\"color: #0174DF;\">");
    String _password = user.getPassword();
    _builder.append(_password, "");
    _builder.append("</span><br/>");
    _builder.newLineIfNotEmpty();
    _builder.append("<div style=\"word-wrap: break-word;word-break: normal;white-space : normal;\">");
    _builder.newLine();
    _builder.append("Mocha Platform makes it easier to communicate, collaborate and software operation. By unifying all your workflows, by breaking-barriers of hierarchy, task, topic discuss and file management,you can now transform the way you communicate and collaborate with your colleagues.");
    _builder.newLine();
    _builder.append("</div><br/>");
    _builder.newLine();
    _builder.append("Best Regards,<br/>");
    _builder.newLine();
    _builder.append("Mocha Platform Team.<br/>");
    _builder.newLine();
    _builder.append("<div style=\"font-size: 10px; color: #A4A4A4\">");
    _builder.newLine();
    _builder.append("Please do not reply to this email. This mailbox is not monitored and you will not receive a response.");
    _builder.newLine();
    _builder.append("</div>");
    _builder.newLine();
    _builder.append("</div>");
    _builder.newLine();
    return _builder;
  }
}
