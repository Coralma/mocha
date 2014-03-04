package com.coral.vaadin.template;

import com.coral.foundation.constant.SystemConstant;
import com.coral.foundation.md.model.Entity;
import com.coral.foundation.md.model.Mocha;
import com.coral.foundation.md.model.Property;
import com.coral.foundation.md.model.View;
import com.coral.foundation.md.model.ViewField;
import com.coral.foundation.md.model.ViewSection;
import com.coral.foundation.md.model.helper.VGenHelper;
import com.coral.vaadin.widget.Viewer;
import java.util.List;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.Exceptions;

@SuppressWarnings("all")
public class TTable {
  private Entity entity;
  
  private List<Mocha> corals;
  
  private String tableClassName;
  
  private String entityName;
  
  private String tableContainer;
  
  public String init(final Entity entity, final List<Mocha> corals) {
    String _xblockexpression = null;
    {
      this.entity = entity;
      this.corals = corals;
      this.entityName = entity.entityName;
      String _genTableClassName = VGenHelper.genTableClassName(this.entityName);
      this.tableClassName = _genTableClassName;
      String _genTableContainerClassName = VGenHelper.genTableContainerClassName(this.entityName);
      String _tableContainer = this.tableContainer = _genTableContainerClassName;
      _xblockexpression = (_tableContainer);
    }
    return _xblockexpression;
  }
  
  public CharSequence generateTable() {
    StringConcatenation _builder = new StringConcatenation();
    CharSequence _GENPackageImport = this.GENPackageImport();
    _builder.append(_GENPackageImport, "");
    _builder.newLineIfNotEmpty();
    CharSequence _GENClassHead = this.GENClassHead();
    _builder.append(_GENClassHead, "");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    CharSequence _GENBuildVeriable = this.GENBuildVeriable();
    _builder.append(_GENBuildVeriable, "\t");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    CharSequence _GENConstructor = this.GENConstructor();
    _builder.append(_GENConstructor, "\t");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    CharSequence _GENBuildContainerClass = this.GENBuildContainerClass();
    _builder.append(_GENBuildContainerClass, "\t");
    _builder.newLineIfNotEmpty();
    CharSequence _GENClassEnd = this.GENClassEnd();
    _builder.append(_GENClassEnd, "");
    _builder.newLineIfNotEmpty();
    return _builder;
  }
  
  public CharSequence GENPackageImport() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("package ");
    _builder.append(SystemConstant.TTable_PKG, "");
    _builder.append(";");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("import ");
    String _currentEntityPackage = VGenHelper.getCurrentEntityPackage(this.entity, this.corals);
    String _plus = (_currentEntityPackage + ".*");
    _builder.append(_plus, "");
    _builder.append(";");
    _builder.newLineIfNotEmpty();
    _builder.append("import java.io.Serializable;");
    _builder.newLine();
    _builder.append("import com.vaadin.data.util.BeanItemContainer;");
    _builder.newLine();
    _builder.append("import com.coral.vaadin.widget.table.AbstractTable;");
    _builder.newLine();
    _builder.append("import com.coral.vaadin.widget.table.EntityTable;");
    _builder.newLine();
    _builder.append("import com.coral.vaadin.widget.table.EntityContainer;");
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
    _builder.append(this.tableClassName, "  ");
    _builder.append(" is a auto Generated class. Please don\'t modify it.");
    _builder.newLineIfNotEmpty();
    _builder.append("  ");
    _builder.append("* @author Coral");
    _builder.newLine();
    _builder.append("  ");
    _builder.append("*/");
    _builder.newLine();
    _builder.append("public class ");
    _builder.append(this.tableClassName, "");
    _builder.append(" extends AbstractTable {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("private static final long serialVersionUID = ");
    String _genserialVersionUID = VGenHelper.genserialVersionUID();
    _builder.append(_genserialVersionUID, "\t");
    _builder.append("L;");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence GENConstructor() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("public ");
    _builder.append(this.tableClassName, "");
    _builder.append("(String viewClass) {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("this(EntityTable.POPUP, viewClass);");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("public ");
    _builder.append(this.tableClassName, "");
    _builder.append("(String type, String viewClass) {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("super(type,viewClass);");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("table.setDataContainer(new ");
    _builder.append(this.tableContainer, "\t");
    _builder.append("());");
    _builder.newLineIfNotEmpty();
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence GENBuildVeriable() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      final View viewer = VGenHelper.getCurrentView(this.entity, Viewer.TTable);
      _builder.newLineIfNotEmpty();
      _builder.append("public static final Object[] NATURAL_COL_ORDER = new Object[] {");
      _builder.newLine();
      {
        List<ViewSection> _sections = viewer.getSections();
        for(final ViewSection section : _sections) {
          {
            for(final ViewField field : section.viewFields) {
              _builder.append("\t");
              _builder.append("\"");
              _builder.append(field.fieldName, "\t");
              _builder.append("\",");
              _builder.newLineIfNotEmpty();
            }
          }
        }
      }
      _builder.append("};");
      _builder.newLine();
      _builder.newLine();
      _builder.append("public static final String[] COL_HEADERS_ENGLISH = new String[] {");
      _builder.newLine();
      {
        List<ViewSection> _sections_1 = viewer.getSections();
        for(final ViewSection section_1 : _sections_1) {
          {
            for(final ViewField field_1 : section_1.viewFields) {
              _builder.append("\t");
              final Property p = VGenHelper.getFieldProperty(this.entity, field_1.fieldName);
              _builder.newLineIfNotEmpty();
              _builder.append("\t");
              _builder.append("\"");
              _builder.append(p.label, "\t");
              _builder.append("\",");
              _builder.newLineIfNotEmpty();
            }
          }
        }
      }
      _builder.append("};");
      _builder.newLine();
      return _builder;
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  public CharSequence GENBuildContainerClass() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("public class ");
    _builder.append(this.tableContainer, "");
    _builder.append(" extends BeanItemContainer<");
    _builder.append(this.entityName, "");
    _builder.append("> implements Serializable, EntityContainer {");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("private static final long serialVersionUID = ");
    String _genserialVersionUID = VGenHelper.genserialVersionUID();
    _builder.append(_genserialVersionUID, "\t");
    _builder.append("L;");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public ");
    _builder.append(this.tableContainer, "\t");
    _builder.append("() {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t");
    _builder.append("super(");
    _builder.append(this.entityName, "\t\t");
    _builder.append(".class);");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@Override");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public Object[] getVisibleColumns() {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("return NATURAL_COL_ORDER;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@Override");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public String[] getColumnHeaders() {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("return COL_HEADERS_ENGLISH;");
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
