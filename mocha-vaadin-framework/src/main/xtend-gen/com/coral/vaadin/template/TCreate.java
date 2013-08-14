package com.coral.vaadin.template;

import com.coral.foundation.constant.SystemConstant;
import com.coral.foundation.md.model.Entity;
import com.coral.foundation.md.model.Mocha;
import com.coral.foundation.md.model.View;
import com.coral.foundation.md.model.ViewAction;
import com.coral.foundation.md.model.ViewField;
import com.coral.foundation.md.model.ViewSection;
import com.coral.foundation.md.model.helper.VGenHelper;
import com.coral.foundation.utils.StrUtils;
import com.coral.vaadin.widget.Viewer;
import com.coral.vaadin.widget.table.EntityTable;
import com.google.common.base.Objects;
import java.util.List;
import org.eclipse.xtend2.lib.StringConcatenation;

@SuppressWarnings("all")
public class TCreate {
  private Entity entity;
  
  private List<Mocha> corals;
  
  private String viewClassName;
  
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
      String _genCreateViewClassName = VGenHelper.genCreateViewClassName(this.entityName);
      String _viewClassName = this.viewClassName = _genCreateViewClassName;
      _xblockexpression = (_viewClassName);
    }
    return _xblockexpression;
  }
  
  public CharSequence generateCreateView() {
    StringConcatenation _builder = new StringConcatenation();
    CharSequence _GENPackageImport = this.GENPackageImport();
    _builder.append(_GENPackageImport, "");
    _builder.newLineIfNotEmpty();
    CharSequence _GENClassHead = this.GENClassHead();
    _builder.append(_GENClassHead, "");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    CharSequence _GENBuildVeriable = this.GENBuildVeriable();
    _builder.append(_GENBuildVeriable, "	");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    CharSequence _GENConstructor = this.GENConstructor();
    _builder.append(_GENConstructor, "	");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    CharSequence _GENBuildMethod = this.GENBuildMethod();
    _builder.append(_GENBuildMethod, "	");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    CharSequence _GENWidgetImplMethod = this.GENWidgetImplMethod();
    _builder.append(_GENWidgetImplMethod, "	");
    _builder.newLineIfNotEmpty();
    CharSequence _GENClassEnd = this.GENClassEnd();
    _builder.append(_GENClassEnd, "");
    _builder.newLineIfNotEmpty();
    return _builder;
  }
  
  public CharSequence GENPackageImport() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("package ");
    _builder.append(SystemConstant.TCREATE_PAGE_PKG, "");
    _builder.append(";");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("import ");
    String _currentEntityPackage = VGenHelper.getCurrentEntityPackage(this.entity, this.corals);
    String _plus = (_currentEntityPackage + ".*");
    _builder.append(_plus, "");
    _builder.append(";");
    _builder.newLineIfNotEmpty();
    _builder.append("import ");
    String _plus_1 = (SystemConstant.TTable_PKG + ".*");
    _builder.append(_plus_1, "");
    _builder.append(";");
    _builder.newLineIfNotEmpty();
    _builder.append("import com.coral.vaadin.widget.Viewer;");
    _builder.newLine();
    _builder.append("import com.coral.vaadin.widget.Widget;");
    _builder.newLine();
    _builder.append("import com.coral.vaadin.widget.view.AbstractEntityViewer;");
    _builder.newLine();
    _builder.append("import com.coral.vaadin.widget.view.WidgetParameter;");
    _builder.newLine();
    _builder.append("import com.coral.vaadin.widget.layout.SectionLayout;");
    _builder.newLine();
    _builder.append("import com.coral.vaadin.widget.field.ActionButton;");
    _builder.newLine();
    _builder.append("import com.coral.vaadin.widget.listener.PresenterListener;");
    _builder.newLine();
    _builder.append("import com.coral.vaadin.widget.table.EntityTable;");
    _builder.newLine();
    _builder.append("import com.vaadin.terminal.ThemeResource;");
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
    _builder.append(" extends AbstractEntityViewer implements Viewer {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("private static final long serialVersionUID = ");
    String _genserialVersionUID = VGenHelper.genserialVersionUID();
    _builder.append(_genserialVersionUID, "	");
    _builder.append("L;");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence GENBuildVeriable() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("private PresenterListener listener;");
    _builder.newLine();
    _builder.append("private ");
    _builder.append(this.entityName, "");
    _builder.append(" ");
    _builder.append(this.entityVariable, "");
    _builder.append(" = new ");
    _builder.append(this.entityName, "");
    _builder.append("();");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence GENConstructor() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("public ");
    _builder.append(this.viewClassName, "");
    _builder.append("(ViewContext viewContext) {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("super(viewContext);");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence GENBuildMethod() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("public void build() {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("Entity entity = ModelCenter.getEntity(");
    _builder.append(this.entityName, "	");
    _builder.append(");");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("ViewContext viewContext = new ViewContext(entity, null);");
    _builder.newLine();
    _builder.append("\t");
    final View viewer = VGenHelper.getCurrentView(this.entity, Viewer.TCreate);
    _builder.newLineIfNotEmpty();
    {
      List<ViewSection> _sections = viewer.getSections();
      for(final ViewSection section : _sections) {
        _builder.append("\t");
        final String sectionName = section.name;
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        CharSequence _GENSection = this.GENSection("null", section);
        _builder.append(_GENSection, "	");
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.append("\t");
    _builder.newLine();
    {
      if (viewer.root) {
        _builder.append("\t");
        CharSequence _GENViewAction = this.GENViewAction();
        _builder.append(_GENViewAction, "	");
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence GENSection(final String parentSection, final ViewSection section) {
    StringConcatenation _builder = new StringConcatenation();
    final String sectionName = section.name;
    _builder.newLineIfNotEmpty();
    int _xifexpression = (int) 0;
    boolean _equals = Objects.equal(section.column, null);
    if (_equals) {
      _xifexpression = 3;
    } else {
      _xifexpression = section.column;
    }
    final int column = _xifexpression;
    _builder.newLineIfNotEmpty();
    _builder.append("SectionLayout ");
    _builder.append(sectionName, "");
    _builder.append(" = buildSection(");
    _builder.append(section, "");
    _builder.append(");");
    _builder.newLineIfNotEmpty();
    {
      boolean _equals_1 = parentSection.equals("null");
      if (_equals_1) {
        _builder.append(parentSection, "");
        _builder.append(".addComponent(");
        _builder.append(section, "");
        _builder.append(");");
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.newLine();
    {
      boolean _notEquals = (!Objects.equal(section.viewFields, null));
      if (_notEquals) {
        {
          for(final ViewField field : section.viewFields) {
            _builder.newLine();
          }
        }
      }
    }
    {
      boolean _notEquals_1 = (!Objects.equal(section.viewSections, null));
      if (_notEquals_1) {
        {
          for(final ViewSection subSection : section.viewSections) {
            CharSequence _GENSection = this.GENSection(sectionName, subSection);
            _builder.append(_GENSection, "");
            _builder.newLineIfNotEmpty();
          }
        }
      }
    }
    {
      boolean _notEquals_2 = (!Objects.equal(section.viewer, null));
      if (_notEquals_2) {
        _builder.append("addViewer(");
        _builder.append(sectionName, "");
        _builder.append(", \"");
        _builder.append(section.viewer, "");
        _builder.append("\", \"");
        _builder.append(section.data, "");
        _builder.append("\");");
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.newLine();
    {
      for(final ViewAction action : section.viewActions) {
        String _name = action.getName();
        String _cleanString = StrUtils.cleanString(_name);
        final String actionName = StrUtils.lowCaseFirstLetter(_cleanString);
        _builder.newLineIfNotEmpty();
        _builder.append("ActionButton ");
        _builder.append(actionName, "");
        _builder.append("Button = addButton(\"");
        _builder.append(action.label, "");
        _builder.append("\",\"");
        _builder.append(actionName, "");
        _builder.append("\", listener, null);");
        _builder.newLineIfNotEmpty();
        _builder.append(sectionName, "");
        _builder.append(".addButton(");
        _builder.append(actionName, "");
        _builder.append("Button);");
        _builder.newLineIfNotEmpty();
      }
    }
    return _builder;
  }
  
  public CharSequence GENViewAction() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("ActionButton addButton = addButton(\"Save\",\"save\", listener, new ThemeResource(\"icons/save.png\"));");
    _builder.newLine();
    _builder.append("ActionButton backButton = addButton(\"Back\",\"back\", listener, new ThemeResource(\"icons/back.png\"));");
    _builder.newLine();
    _builder.append("setButtons(addButton,backButton);");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence GENWidgetImplMethod() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("public Object getValue() {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("return getEntityValue(");
    _builder.append(this.entityVariable, "	");
    _builder.append(",");
    _builder.append(this.entityName, "	");
    _builder.append(".class);");
    _builder.newLineIfNotEmpty();
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("public void setValue(Object value) {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("this.");
    _builder.append(this.entityVariable, "	");
    _builder.append(" = (");
    _builder.append(this.entityName, "	");
    _builder.append(")value;");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("setEntityValue(value);");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("public boolean validate(String type) {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("// TODO Auto-generated method stub");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("return true;");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("public String getViewerTitle() {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("// TODO Auto-generated method stub");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("return null;");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("public void setListener(PresenterListener listener) {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("this.listener = listener;");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence GENClassEnd() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  public String check(final Object value) {
    boolean _isEmpty = StrUtils.isEmpty(value);
    if (_isEmpty) {
      return "null";
    } else {
      String _plus = ("\"" + value);
      return (_plus + "\"");
    }
  }
  
  public String checkTableStyle(final String style) {
    boolean _and = false;
    boolean _notEquals = (!Objects.equal(style, null));
    if (!_notEquals) {
      _and = false;
    } else {
      boolean _equals = style.equals("inline");
      _and = (_notEquals && _equals);
    }
    if (_and) {
      return EntityTable.INLINE;
    } else {
      boolean _and_1 = false;
      boolean _notEquals_1 = (!Objects.equal(style, null));
      if (!_notEquals_1) {
        _and_1 = false;
      } else {
        boolean _equals_1 = style.equals("default");
        _and_1 = (_notEquals_1 && _equals_1);
      }
      if (_and_1) {
        return EntityTable.DEFAULT;
      } else {
        return EntityTable.POPUP;
      }
    }
  }
}
