package com.coral.vaadin.template;

import com.coral.foundation.constant.SystemConstant;
import com.coral.foundation.md.model.Entity;
import com.coral.foundation.md.model.Mocha;
import com.coral.foundation.md.model.helper.GLog;
import com.coral.foundation.md.model.helper.VGenHelper;
import com.coral.foundation.utils.StrUtils;
import java.util.List;
import org.eclipse.xtend2.lib.StringConcatenation;

@SuppressWarnings("all")
public class TCreateListener {
  private Entity entity;
  
  private List<Mocha> corals;
  
  private String listenerName;
  
  private String entityName;
  
  private String entityVariable;
  
  public String init(final Entity entity, final List<Mocha> corals) {
    String _xblockexpression = null;
    {
      this.entity = entity;
      this.corals = corals;
      this.entityName = entity.entityName;
      String _lowCaseFirstLetter = StrUtils.lowCaseFirstLetter(this.entityName);
      this.entityVariable = _lowCaseFirstLetter;
      String _genCreateListenerName = VGenHelper.genCreateListenerName(this.entityName);
      String _listenerName = this.listenerName = _genCreateListenerName;
      _xblockexpression = (_listenerName);
    }
    return _xblockexpression;
  }
  
  public CharSequence generate() {
    StringConcatenation _builder = new StringConcatenation();
    CharSequence _GENPackageImport = this.GENPackageImport();
    _builder.append(_GENPackageImport, "");
    _builder.newLineIfNotEmpty();
    CharSequence _GENClassHead = this.GENClassHead();
    _builder.append(_GENClassHead, "");
    _builder.newLineIfNotEmpty();
    CharSequence _GENClassEnd = this.GENClassEnd();
    _builder.append(_GENClassEnd, "");
    _builder.newLineIfNotEmpty();
    return _builder;
  }
  
  public CharSequence GENPackageImport() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("package ");
    _builder.append(SystemConstant.TCREATE_LISTENER_PKG, "");
    _builder.append(";");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("import ");
    String _currentEntityPackage = VGenHelper.getCurrentEntityPackage(this.entity, this.corals);
    String _plus = (_currentEntityPackage + ".*");
    _builder.append(_plus, "");
    _builder.append(";");
    _builder.newLineIfNotEmpty();
    _builder.append("import com.coral.vaadin.widget.Viewer;");
    _builder.newLine();
    _builder.append("import com.coral.vaadin.widget.Widget;");
    _builder.newLine();
    _builder.append("import com.coral.vaadin.widget.listener.PresenterListener;");
    _builder.newLine();
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence GENClassHead() {
    StringConcatenation _builder = new StringConcatenation();
    GLog.startClass(this.listenerName);
    _builder.newLineIfNotEmpty();
    _builder.append("/**");
    _builder.newLine();
    _builder.append("  ");
    _builder.append("* ");
    _builder.append(this.listenerName, "  ");
    _builder.append(" is a auto Generated class. Please don\'t modify it.");
    _builder.newLineIfNotEmpty();
    _builder.append("  ");
    _builder.append("* @author Coral");
    _builder.newLine();
    _builder.append("  ");
    _builder.append("*/");
    _builder.newLine();
    _builder.append("public interface ");
    _builder.append(this.listenerName, "");
    _builder.append(" extends PresenterListener {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence GENClassEnd() {
    StringConcatenation _builder = new StringConcatenation();
    GLog.endClass(this.listenerName);
    _builder.newLineIfNotEmpty();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
}
