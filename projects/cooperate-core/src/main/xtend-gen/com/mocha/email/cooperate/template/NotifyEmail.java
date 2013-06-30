package com.mocha.email.cooperate.template;

import com.coral.foundation.security.model.BasicUser;
import com.google.common.base.Objects;
import com.mocha.cooperate.model.Discuss;
import com.mocha.cooperate.model.Status;
import com.mocha.cooperate.model.TimeLine;
import com.mocha.cooperate.model.ToDo;
import org.eclipse.xtend2.lib.StringConcatenation;

@SuppressWarnings("all")
public class NotifyEmail {
  private BasicUser sender;
  
  private TimeLine timeLine;
  
  private BasicUser receiver;
  
  public BasicUser init(final BasicUser sender, final TimeLine timeLine, final BasicUser receiver) {
    BasicUser _xblockexpression = null;
    {
      this.sender = sender;
      this.timeLine = timeLine;
      BasicUser _receiver = this.receiver = receiver;
      _xblockexpression = (_receiver);
    }
    return _xblockexpression;
  }
  
  public CharSequence getSubject() {
    StringConcatenation _builder = new StringConcatenation();
    {
      Status _status = this.timeLine.getStatus();
      boolean _notEquals = (!Objects.equal(_status, null));
      if (_notEquals) {
        String _realName = this.sender.getRealName();
        _builder.append(_realName, "");
        _builder.append(" create a new Status notify you");
        _builder.newLineIfNotEmpty();
      } else {
        Discuss _discuss = this.timeLine.getDiscuss();
        boolean _notEquals_1 = (!Objects.equal(_discuss, null));
        if (_notEquals_1) {
          String _realName_1 = this.sender.getRealName();
          _builder.append(_realName_1, "");
          _builder.append(" create a new Topic notify you");
          _builder.newLineIfNotEmpty();
        } else {
          ToDo _todo = this.timeLine.getTodo();
          boolean _notEquals_2 = (!Objects.equal(_todo, null));
          if (_notEquals_2) {
            String _realName_2 = this.sender.getRealName();
            _builder.append(_realName_2, "");
            _builder.append(" create a new Todo notify you");
            _builder.newLineIfNotEmpty();
          }
        }
      }
    }
    return _builder;
  }
  
  public CharSequence getEmailContent() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("<div style=\"font-size: 12px; font-family: Arial; color: #52555A\">");
    _builder.newLine();
    _builder.append("Dear ");
    String _realName = this.receiver.getRealName();
    _builder.append(_realName, "");
    _builder.append("<br/><br/>");
    _builder.newLineIfNotEmpty();
    CharSequence _subject = this.getSubject();
    _builder.append(_subject, "");
    _builder.newLineIfNotEmpty();
    _builder.append("<br/>");
    _builder.newLine();
    _builder.append("Please login in your Mocha account and check your notification <a href=\"www.mocha-platform.com/cooperate/\">www.mocha-platform.com/cooperate</a><br/>");
    _builder.newLine();
    _builder.newLine();
    _builder.append("Best Regards,<br/>");
    _builder.newLine();
    _builder.append("Mocha Platform team.<br/>");
    _builder.newLine();
    _builder.append("<br/>");
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
