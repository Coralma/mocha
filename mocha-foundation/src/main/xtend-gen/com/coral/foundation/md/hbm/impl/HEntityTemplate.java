package com.coral.foundation.md.hbm.impl;

import com.coral.foundation.md.model.Entity;
import com.coral.foundation.md.model.Mocha;
import com.coral.foundation.md.model.Property;
import com.coral.foundation.utils.StrUtils;
import java.util.List;
import org.eclipse.xtend2.lib.StringConcatenation;

@SuppressWarnings("all")
public class HEntityTemplate {
  public CharSequence generate(final Mocha coral, final Entity entity) {
    StringConcatenation _builder = new StringConcatenation();
    final String packageName = coral.getEntityPackage();
    _builder.newLineIfNotEmpty();
    final String entityName = entity.entityName;
    _builder.newLineIfNotEmpty();
    _builder.append("package ");
    _builder.append(packageName, "");
    _builder.append(";");
    _builder.newLineIfNotEmpty();
    _builder.append("import java.util.*;");
    _builder.newLine();
    _builder.append("import java.math.BigDecimal;");
    _builder.newLine();
    _builder.append("import com.coral.foundation.model.ActiveRecord;");
    _builder.newLine();
    _builder.append("/**");
    _builder.newLine();
    _builder.append("  ");
    _builder.append("* <p>Title: ");
    _builder.append(packageName, "  ");
    _builder.append(".");
    _builder.append(entityName, "  ");
    _builder.append(" + \"</p>");
    _builder.newLineIfNotEmpty();
    _builder.append("  ");
    _builder.append("* <p>Description: The ");
    _builder.append(entityName, "  ");
    _builder.append(" entity </p>");
    _builder.newLineIfNotEmpty();
    _builder.append("  ");
    _builder.append("*/");
    _builder.newLine();
    _builder.append("public class ");
    _builder.append(entityName, "");
    _builder.append(" extends ActiveRecord implements java.io.Serializable {");
    _builder.newLineIfNotEmpty();
    {
      List<Property> _properties = entity.getProperties();
      for(final Property m : _properties) {
        final String type = m.getType();
        _builder.newLineIfNotEmpty();
        final String ref = m.getRef();
        _builder.newLineIfNotEmpty();
        final String propertyName = m.getPropertyName();
        _builder.newLineIfNotEmpty();
        {
          boolean _equals = type.equals("Date");
          if (_equals) {
            _builder.append("\t");
            _builder.append("private ");
            _builder.append(type, "	");
            _builder.append(" ");
            _builder.append(propertyName, "	");
            _builder.append(" = new Date();");
            _builder.newLineIfNotEmpty();
          } else {
            boolean _equals_1 = type.equals("List");
            if (_equals_1) {
              _builder.append("\t");
              _builder.append("private ");
              _builder.append(type, "	");
              _builder.append("<");
              _builder.append(ref, "	");
              _builder.append("> ");
              _builder.append(propertyName, "	");
              _builder.append(";");
              _builder.newLineIfNotEmpty();
            } else {
              _builder.append("\t");
              _builder.append("private ");
              _builder.append(type, "	");
              _builder.append(" ");
              _builder.append(propertyName, "	");
              _builder.append(";");
              _builder.newLineIfNotEmpty();
            }
          }
        }
        _builder.append("\t");
        _builder.append("log.debug(\"\"+HEntityTemplate.class);");
        _builder.newLine();
      }
    }
    _builder.newLine();
    {
      List<Property> _properties_1 = entity.getProperties();
      for(final Property m_1 : _properties_1) {
        final String type_1 = m_1.getType();
        _builder.newLineIfNotEmpty();
        final String ref_1 = m_1.getRef();
        _builder.newLineIfNotEmpty();
        final String propertyName_1 = m_1.getPropertyName();
        _builder.newLineIfNotEmpty();
        {
          boolean _equals_2 = type_1.equals("List");
          if (_equals_2) {
            _builder.newLine();
            _builder.append("\t");
            _builder.append("public void ");
            String _setter = StrUtils.setter(propertyName_1);
            _builder.append(_setter, "	");
            _builder.append(" (");
            _builder.append(type_1, "	");
            _builder.append("<");
            _builder.append(ref_1, "	");
            _builder.append("> ");
            _builder.append(propertyName_1, "	");
            _builder.append(") {");
            _builder.newLineIfNotEmpty();
            _builder.append("\t\t");
            _builder.append("this.");
            _builder.append(propertyName_1, "		");
            _builder.append(" = ");
            _builder.append(propertyName_1, "		");
            _builder.append(";");
            _builder.newLineIfNotEmpty();
            _builder.append("\t");
            _builder.append("} ");
            _builder.newLine();
            _builder.append("\t");
            _builder.append("public ");
            _builder.append(type_1, "	");
            _builder.append("<");
            _builder.append(ref_1, "	");
            _builder.append("> ");
            String _ter = StrUtils.getter(propertyName_1);
            _builder.append(_ter, "	");
            _builder.append(" () {");
            _builder.newLineIfNotEmpty();
            _builder.append("\t\t");
            _builder.append("return ");
            _builder.append(propertyName_1, "		");
            _builder.append(";");
            _builder.newLineIfNotEmpty();
            _builder.append("\t");
            _builder.append("}");
            _builder.newLine();
          } else {
            _builder.newLine();
            _builder.append("\t");
            _builder.append("public void ");
            String _setter_1 = StrUtils.setter(propertyName_1);
            _builder.append(_setter_1, "	");
            _builder.append(" (");
            _builder.append(type_1, "	");
            _builder.append(" ");
            _builder.append(propertyName_1, "	");
            _builder.append(") {");
            _builder.newLineIfNotEmpty();
            _builder.append("\t\t");
            _builder.append("this.");
            _builder.append(propertyName_1, "		");
            _builder.append(" = ");
            _builder.append(propertyName_1, "		");
            _builder.append(";");
            _builder.newLineIfNotEmpty();
            _builder.append("\t");
            _builder.append("} ");
            _builder.newLine();
            _builder.append("\t");
            _builder.append("public ");
            _builder.append(type_1, "	");
            _builder.append(" ");
            String _ter_1 = StrUtils.getter(propertyName_1);
            _builder.append(_ter_1, "	");
            _builder.append(" () {");
            _builder.newLineIfNotEmpty();
            _builder.append("\t\t");
            _builder.append("return ");
            _builder.append(propertyName_1, "		");
            _builder.append(";");
            _builder.newLineIfNotEmpty();
            _builder.append("\t");
            _builder.append("}");
            _builder.newLine();
          }
        }
      }
    }
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
}
