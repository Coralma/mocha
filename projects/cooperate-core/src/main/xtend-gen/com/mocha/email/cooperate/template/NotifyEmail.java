package com.mocha.email.cooperate.template;

import com.coral.foundation.security.model.BasicUser;
import com.google.common.base.Objects;
import com.mocha.cooperate.model.Discuss;
import com.mocha.cooperate.model.Status;
import com.mocha.cooperate.model.SubToDoItem;
import com.mocha.cooperate.model.TimeLine;
import com.mocha.cooperate.model.ToDo;
import java.util.List;
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
        _builder.append(" post a new Status notify you");
        _builder.newLineIfNotEmpty();
      } else {
        Discuss _discuss = this.timeLine.getDiscuss();
        boolean _notEquals_1 = (!Objects.equal(_discuss, null));
        if (_notEquals_1) {
          String _realName_1 = this.sender.getRealName();
          _builder.append(_realName_1, "");
          _builder.append(" post a new Topic notify you");
          _builder.newLineIfNotEmpty();
        } else {
          ToDo _todo = this.timeLine.getTodo();
          boolean _notEquals_2 = (!Objects.equal(_todo, null));
          if (_notEquals_2) {
            String _realName_2 = this.sender.getRealName();
            _builder.append(_realName_2, "");
            _builder.append(" post a new Todo notify you");
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
    _builder.append("<br/>");
    _builder.newLineIfNotEmpty();
    {
      Status _status = this.timeLine.getStatus();
      boolean _notEquals = (!Objects.equal(_status, null));
      if (_notEquals) {
        String _realName_1 = this.sender.getRealName();
        _builder.append(_realName_1, "");
        _builder.append(" post a new Status notify you. The status content is:<br/>");
        _builder.newLineIfNotEmpty();
        _builder.append("<div style=\"word-wrap: break-word;word-break: normal;white-space : normal; font-weight: bold;\">");
        _builder.newLine();
        Status _status_1 = this.timeLine.getStatus();
        String _content = _status_1.getContent();
        _builder.append(_content, "");
        _builder.newLineIfNotEmpty();
        _builder.append("</div>");
        _builder.newLine();
      } else {
        Discuss _discuss = this.timeLine.getDiscuss();
        boolean _notEquals_1 = (!Objects.equal(_discuss, null));
        if (_notEquals_1) {
          String _realName_2 = this.sender.getRealName();
          _builder.append(_realName_2, "");
          _builder.append(" post a new Topic notify you. The topic content is:<br/>");
          _builder.newLineIfNotEmpty();
          _builder.append("<div style=\"font-size: 14px; font-family: Arial; color: #52555A; font-weight: bold;\">");
          _builder.newLine();
          Discuss _discuss_1 = this.timeLine.getDiscuss();
          String _title = _discuss_1.getTitle();
          _builder.append(_title, "");
          _builder.newLineIfNotEmpty();
          _builder.append("</div>");
          _builder.newLine();
          _builder.append("<div style=\"word-wrap: break-word;word-break: normal;white-space : normal; font-size: 12px; font-family: Arial; color: #52555A\">");
          _builder.newLine();
          Discuss _discuss_2 = this.timeLine.getDiscuss();
          String _content_1 = _discuss_2.getContent();
          _builder.append(_content_1, "");
          _builder.newLineIfNotEmpty();
          _builder.append("</div>");
          _builder.newLine();
        } else {
          ToDo _todo = this.timeLine.getTodo();
          boolean _notEquals_2 = (!Objects.equal(_todo, null));
          if (_notEquals_2) {
            String _realName_3 = this.sender.getRealName();
            _builder.append(_realName_3, "");
            _builder.append(" post a new Todo notify you. The task list is:<br/>");
            _builder.newLineIfNotEmpty();
            _builder.append("<div style=\"font-size: 14px; font-family: Arial; color: #000000; font-weight: bold;word-wrap: break-word;word-break: normal;\">");
            _builder.newLine();
            ToDo _todo_1 = this.timeLine.getTodo();
            _builder.append(_todo_1.getName(), "");
            _builder.append(" - ");
            ToDo _todo_2 = this.timeLine.getTodo();
            BasicUser _assginedUser = _todo_2.getAssginedUser();
            String _realName_4 = _assginedUser.getRealName();
            _builder.append(_realName_4, "");
            _builder.newLineIfNotEmpty();
            _builder.append("</div>");
            _builder.newLine();
            _builder.append("<div style=\"font-size: 12px; font-family: Arial; color: #52555A; font-weight: bold; word-wrap: break-word;word-break: normal;\">");
            _builder.newLine();
            {
              ToDo _todo_3 = this.timeLine.getTodo();
              List<SubToDoItem> _subToDoItems = _todo_3.getSubToDoItems();
              for(final SubToDoItem task : _subToDoItems) {
                String _content_2 = task.getContent();
                _builder.append(_content_2, "");
                _builder.append(" - ");
                BasicUser _assginedUser_1 = task.getAssginedUser();
                String _realName_5 = _assginedUser_1.getRealName();
                _builder.append(_realName_5, "");
                _builder.append("<br/>");
                _builder.newLineIfNotEmpty();
              }
            }
            _builder.append("</div>");
            _builder.newLine();
          }
        }
      }
    }
    _builder.append("<br/>");
    _builder.newLine();
    _builder.append("Please login in your Mocha account and check your notification <a href=\"www.mocha-platform.com/cooperate/\">www.mocha-platform.com/cooperate</a><br/>");
    _builder.newLine();
    _builder.append("Best Regards,<br/>");
    _builder.newLine();
    _builder.append("Mocha Platform Team.<br/>");
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
