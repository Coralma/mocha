package com.coral.vaadin.template;

import com.coral.foundation.constant.SystemConstant;
import com.coral.foundation.md.model.Entity;
import com.coral.foundation.md.model.Mocha;
import com.coral.foundation.md.model.Property;
import com.coral.foundation.md.model.View;
import com.coral.foundation.md.model.ViewField;
import com.coral.foundation.md.model.ViewSection;
import com.coral.foundation.md.model.helper.VGenHelper;
import com.coral.foundation.utils.StrUtils;
import com.coral.vaadin.widget.Viewer;
import com.google.common.base.Objects;
import java.util.List;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.Exceptions;

@SuppressWarnings("all")
public class TSearch {
  private Entity entity;
  
  private List<Mocha> corals;
  
  private String searchViewClassName;
  
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
      String _genSearchViewClassName = VGenHelper.genSearchViewClassName(this.entityName);
      String _searchViewClassName = this.searchViewClassName = _genSearchViewClassName;
      _xblockexpression = (_searchViewClassName);
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
    _builder.append(SystemConstant.TSEARCH_PAGE_PKG, "");
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
    _builder.append("import com.coral.vaadin.widget.field.ActionButton;");
    _builder.newLine();
    _builder.append("import com.coral.vaadin.widget.listener.PresenterListener;");
    _builder.newLine();
    _builder.append("import com.coral.vaadin.widget.view.AbstractView;");
    _builder.newLine();
    _builder.append("import com.coral.vaadin.widget.layout.SectionLayout;");
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
    _builder.append(this.searchViewClassName, "  ");
    _builder.append(" is a auto Generated class. Please don\'t modify it.");
    _builder.newLineIfNotEmpty();
    _builder.append("  ");
    _builder.append("* @author Coral");
    _builder.newLine();
    _builder.append("  ");
    _builder.append("*/");
    _builder.newLine();
    _builder.append("public class ");
    _builder.append(this.searchViewClassName, "");
    _builder.append(" extends AbstractView implements Widget,Viewer {");
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
    return _builder;
  }
  
  public CharSequence GENConstructor() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("public ");
    _builder.append(this.searchViewClassName, "");
    _builder.append("() {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("super();");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("public ");
    _builder.append(this.searchViewClassName, "");
    _builder.append("(String title) {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("super(title);");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence GENBuildMethod() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("public void build() {");
      _builder.newLine();
      _builder.append("\t");
      final View viewer = VGenHelper.getCurrentView(this.entity, Viewer.TSearch);
      _builder.newLineIfNotEmpty();
      _builder.append("\t");
      List<ViewSection> _sections = viewer.getSections();
      final ViewSection section = _sections.get(0);
      _builder.newLineIfNotEmpty();
      _builder.append("\t");
      final String sectionName = section.name;
      _builder.newLineIfNotEmpty();
      _builder.append("\t");
      int _xifexpression = (int) 0;
      boolean _equals = Objects.equal(section.column, null);
      if (_equals) {
        _xifexpression = 3;
      } else {
        _xifexpression = section.column;
      }
      final int column = _xifexpression;
      _builder.newLineIfNotEmpty();
      _builder.append("\t");
      _builder.append("SectionLayout ");
      _builder.append(sectionName, "	");
      _builder.append(" = createSection(\"");
      _builder.append(section.label, "	");
      _builder.append("\", ");
      _builder.append(column, "	");
      _builder.append(");");
      _builder.newLineIfNotEmpty();
      {
        for(final ViewField field : section.viewFields) {
          _builder.append("\t");
          final Property p = VGenHelper.getFieldProperty(this.entity, field.fieldName);
          _builder.newLineIfNotEmpty();
          {
            boolean _equals_1 = Objects.equal(p.ref, null);
            if (_equals_1) {
              _builder.append("\t");
              _builder.append("addWidget(");
              _builder.append(sectionName, "	");
              _builder.append(", \"");
              _builder.append(p.label, "	");
              _builder.append("\", \"");
              _builder.append(p.propertyName, "	");
              _builder.append("\", \"");
              _builder.append(p.type, "	");
              _builder.append("\",false);");
              _builder.newLineIfNotEmpty();
            }
          }
        }
      }
      _builder.append("\t");
      CharSequence _GENSearchAction = this.GENSearchAction(sectionName);
      _builder.append(_GENSearchAction, "	");
      _builder.newLineIfNotEmpty();
      _builder.append("\t");
      CharSequence _GENResultTable = this.GENResultTable();
      _builder.append(_GENResultTable, "	");
      _builder.newLineIfNotEmpty();
      _builder.append("\t");
      CharSequence _GENViewAction = this.GENViewAction();
      _builder.append(_GENViewAction, "	");
      _builder.newLineIfNotEmpty();
      _builder.append("}");
      _builder.newLine();
      return _builder;
    } catch (Exception _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  public CharSequence GENSearchAction(final String sectionName) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("ActionButton searchButton = addButton(\"\u67E5\u8BE2\",\"query\",listener,new ThemeResource(\"icons/search.png\"));");
    _builder.newLine();
    _builder.append("ActionButton clearButton = addButton(\"\u6E05\u7A7A\",\"clean\",listener,new ThemeResource(\"icons/refresh.png\"));");
    _builder.newLine();
    _builder.append(sectionName, "");
    _builder.append(".setButtons(searchButton, clearButton);");
    _builder.newLineIfNotEmpty();
    return _builder;
  }
  
  public CharSequence GENResultTable() {
    StringConcatenation _builder = new StringConcatenation();
    final String entityTable = VGenHelper.genTableClassName(this.entityName);
    _builder.newLineIfNotEmpty();
    final String entityTableVariable = StrUtils.lowCaseFirstLetter(entityTable);
    _builder.newLineIfNotEmpty();
    final String entityTableSection = (entityTableVariable + "Section");
    _builder.newLineIfNotEmpty();
    _builder.append("SectionLayout ");
    _builder.append(entityTableSection, "");
    _builder.append(" = createSection(\"\u4F9B\u5E94\u5546\u67E5\u8BE2\u7ED3\u6784\", 1);");
    _builder.newLineIfNotEmpty();
    _builder.append("addTableWidget(");
    _builder.append(entityTableSection, "");
    _builder.append(", null , \"");
    _builder.append(entityTable, "");
    _builder.append("\" , null ,");
    _builder.append(entityTable, "");
    _builder.append(".class, EntityTable.DEFAULT);");
    _builder.newLineIfNotEmpty();
    return _builder;
  }
  
  public CharSequence GENViewAction() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("ActionButton addButton = addButton(\"\u65B0\u589E\",\"add\",listener,new ThemeResource(\"icons/add.png\"));");
    _builder.newLine();
    _builder.append("ActionButton editButton = addButton(\"\u4FEE\u6539\",\"edit\",listener,new ThemeResource(\"icons/edit.png\"));");
    _builder.newLine();
    _builder.append("ActionButton deleteButton = addButton(\"\u5220\u9664\",\"delete\", listener, new ThemeResource(\"icons/del.png\"));");
    _builder.newLine();
    _builder.append("setButtons(addButton,editButton,deleteButton);");
    _builder.newLine();
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence GENWidgetImplMethod() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("public ");
    _builder.append(this.entityName, "");
    _builder.append(" getValue() {");
    _builder.newLineIfNotEmpty();
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
    _builder.append("return false;");
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
    return _builder;
  }
  
  public CharSequence GENClassEnd() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
}
