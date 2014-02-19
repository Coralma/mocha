package com.coral.vaadin.template;

import com.coral.foundation.constant.SystemConstant;
import com.coral.foundation.md.model.Entity;
import com.coral.foundation.md.model.Mocha;
import com.coral.foundation.md.model.View;
import com.coral.foundation.md.model.helper.VGenHelper;
import com.coral.vaadin.widget.Viewer;
import com.google.common.base.Objects;
import java.util.List;
import org.eclipse.xtend2.lib.StringConcatenation;

@SuppressWarnings("all")
public class PresenterFactory {
  private List<Mocha> corals;
  
  private String viewClassName;
  
  public String init(final List<Mocha> corals, final String viewClassName) {
    String _xblockexpression = null;
    {
      this.corals = corals;
      String _viewClassName = this.viewClassName = viewClassName;
      _xblockexpression = (_viewClassName);
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
    _builder.append("\t");
    CharSequence _GENBuildMethod = this.GENBuildMethod();
    _builder.append(_GENBuildMethod, "\t");
    _builder.newLineIfNotEmpty();
    CharSequence _GENClassEnd = this.GENClassEnd();
    _builder.append(_GENClassEnd, "");
    _builder.newLineIfNotEmpty();
    return _builder;
  }
  
  public CharSequence GENPackageImport() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("package ");
    _builder.append(SystemConstant.GENERATED_PAGE, "");
    _builder.append(";");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("import com.coral.vaadin.controller.PageFactory;");
    _builder.newLine();
    {
      boolean _hasView = VGenHelper.hasView(this.corals);
      if (_hasView) {
        _builder.append("import ");
        _builder.append((SystemConstant.TCREATE_PRESENTER_PKG + ".*"), "");
        _builder.append(";");
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.append("import com.coral.vaadin.controller.Presenter;");
    _builder.newLine();
    _builder.append("import com.coral.vaadin.widget.view.builder.ViewContext;");
    _builder.newLine();
    _builder.append("import com.coral.foundation.core.impl.MochaEventBus;");
    _builder.newLine();
    _builder.append("import org.slf4j.Logger;");
    _builder.newLine();
    _builder.append("import org.slf4j.LoggerFactory;");
    _builder.newLine();
    _builder.newLine();
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence GENClassHead() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("/**");
    _builder.newLine();
    _builder.append("  ");
    _builder.append("* ");
    _builder.append(this.viewClassName, "  ");
    _builder.append(" is a auto Generated class. Please don\'t modify it.");
    _builder.newLineIfNotEmpty();
    _builder.append("  ");
    _builder.append("* @author Coral");
    _builder.newLine();
    _builder.append("  ");
    _builder.append("*/");
    _builder.newLine();
    _builder.append("public class ");
    _builder.append(this.viewClassName, "");
    _builder.append(" implements PageFactory {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence GENBuildMethod() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("public Presenter getPresenter(String entityName, MochaEventBus eventBus) {");
    _builder.newLine();
    {
      for(final Mocha coral : this.corals) {
        {
          List<Entity> _entityList = coral.getEntityList();
          for(final Entity entity : _entityList) {
            {
              View _currentView = VGenHelper.getCurrentView(entity, Viewer.TCreate);
              boolean _notEquals = (!Objects.equal(_currentView, null));
              if (_notEquals) {
                _builder.append("\t");
                final String createPresenterClassName = VGenHelper.genCreatePresenterClassName(entity.entityName);
                _builder.newLineIfNotEmpty();
                _builder.append("\t");
                CharSequence _GENGetterMethod = this.GENGetterMethod(createPresenterClassName, entity.entityName);
                _builder.append(_GENGetterMethod, "\t");
                _builder.newLineIfNotEmpty();
              }
            }
          }
        }
      }
    }
    _builder.append("\t");
    _builder.append("return null;");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence GENGetterMethod(final String presenterClass, final String entityName) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("if(\"");
    _builder.append(entityName, "");
    _builder.append("\".equals(entityName)) {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("ViewContext viewContext = new ViewContext(entityName);");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("return new ");
    _builder.append(presenterClass, "\t");
    _builder.append("(viewContext, eventBus);");
    _builder.newLineIfNotEmpty();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence GENClassEnd() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
}
