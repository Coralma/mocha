package com.coral.vaadin.template.common;

import com.coral.foundation.constant.SystemConstant;
import com.coral.foundation.md.model.Mocha;
import com.coral.foundation.md.model.View;
import com.coral.foundation.md.model.ViewField;
import com.coral.foundation.md.model.ViewSection;
import com.coral.foundation.md.model.helper.VAppGenHelper;
import java.util.List;
import org.eclipse.xtend2.lib.StringConcatenation;

@SuppressWarnings("all")
public class EntityCardSearch {
  private String viewClassName;
  
  private String cardClassName;
  
  private View view;
  
  private List<Mocha> mochas;
  
  public String init(final View view, final List<Mocha> mochas) {
    String _xblockexpression = null;
    {
      this.view = view;
      this.mochas = mochas;
      String _generateClassName = VAppGenHelper.generateClassName(view.name);
      this.viewClassName = _generateClassName;
      String _generateCardClassName = VAppGenHelper.generateCardClassName(view.name);
      String _cardClassName = this.cardClassName = _generateCardClassName;
      _xblockexpression = (_cardClassName);
    }
    return _xblockexpression;
  }
  
  public CharSequence generate() {
    StringConcatenation _builder = new StringConcatenation();
    CharSequence _GENPackageImport = this.GENPackageImport();
    _builder.append(_GENPackageImport, "");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    CharSequence _GENClassHead = this.GENClassHead();
    _builder.append(_GENClassHead, "");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("\t");
    CharSequence _GENBuildMethod = this.GENBuildMethod();
    _builder.append(_GENBuildMethod, "	");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("\t");
    CharSequence _GENGetMethod = this.GENGetMethod();
    _builder.append(_GENGetMethod, "	");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    CharSequence _GENClassEnd = this.GENClassEnd();
    _builder.append(_GENClassEnd, "");
    _builder.newLineIfNotEmpty();
    return _builder;
  }
  
  public CharSequence GENPackageImport() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("package ");
    _builder.append(SystemConstant.ENTITY_EDIT_VIEW_PKG, "");
    _builder.append(";");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("import java.util.List;");
    _builder.newLine();
    _builder.newLine();
    _builder.append("import com.coral.vaadin.view.template.sat.panel.impl.SearchPanel;");
    _builder.newLine();
    _builder.append("import com.coral.vaadin.widget.fields.FieldStatus;");
    _builder.newLine();
    _builder.append("import com.google.common.collect.Lists;");
    _builder.newLine();
    _builder.append("import ");
    _builder.append(this.view.entity.entityClass, "");
    _builder.append(";");
    _builder.newLineIfNotEmpty();
    return _builder;
  }
  
  public CharSequence GENClassHead() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("public class ");
    _builder.append(this.viewClassName, "");
    _builder.append(" extends SearchPanel {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("private List entities = Lists.newArrayList();");
    _builder.newLine();
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence GENBuildMethod() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("public void build() {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("FieldStatus fieldStatus = null;");
    _builder.newLine();
    {
      List<ViewSection> _sections = this.view.getSections();
      for(final ViewSection section : _sections) {
        {
          String _template = section.getTemplate();
          boolean _equals = "SearchCondition".equals(_template);
          if (_equals) {
            {
              List<ViewField> _viewFields = section.getViewFields();
              for(final ViewField field : _viewFields) {
                _builder.append("\t");
                _builder.append("fieldStatus = ");
                String _generateFieldStatus = VAppGenHelper.generateFieldStatus(field);
                _builder.append(_generateFieldStatus, "	");
                _builder.newLineIfNotEmpty();
                _builder.append("\t");
                _builder.append("createFieldWidget(fieldStatus);");
                _builder.newLine();
                _builder.append("\t");
                _builder.newLine();
              }
            }
          }
        }
      }
    }
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence GENGetMethod() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("public Class getEntityCardClass() {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("return ");
    _builder.append(this.cardClassName, "	");
    _builder.append(".class;");
    _builder.newLineIfNotEmpty();
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("public String getViewerTitle() {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("return \"");
    String _generateSearchEntityViewTitle = VAppGenHelper.generateSearchEntityViewTitle(this.view);
    _builder.append(_generateSearchEntityViewTitle, "	");
    _builder.append("\";");
    _builder.newLineIfNotEmpty();
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("@Override");
    _builder.newLine();
    _builder.append("public List getEntityList() {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("return entities;");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("@Override");
    _builder.newLine();
    _builder.append("public void setValue(Object value) {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("if(value != null) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("entities = (List) value;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
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
