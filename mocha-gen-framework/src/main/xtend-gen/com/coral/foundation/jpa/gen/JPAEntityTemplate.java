package com.coral.foundation.jpa.gen;

import com.coral.foundation.md.model.Entity;
import com.coral.foundation.md.model.Mocha;
import com.coral.foundation.md.model.Property;
import com.coral.foundation.utils.StrUtils;
import com.google.common.base.Objects;
import java.util.List;
import org.eclipse.xtend2.lib.StringConcatenation;

@SuppressWarnings("all")
public class JPAEntityTemplate {
  private Mocha coral;
  
  private Entity entity;
  
  private String packageName;
  
  private String entityName;
  
  private String entityIdField;
  
  public String init(final Mocha coral, final Entity entity) {
    String _xblockexpression = null;
    {
      this.coral = coral;
      this.entity = entity;
      String _entityPackage = coral.getEntityPackage();
      this.packageName = _entityPackage;
      this.entityName = entity.entityName;
      String _lowCaseFirstLetter = StrUtils.lowCaseFirstLetter(this.entityName);
      String _plus = (_lowCaseFirstLetter + "Id");
      String _entityIdField = this.entityIdField = _plus;
      _xblockexpression = (_entityIdField);
    }
    return _xblockexpression;
  }
  
  public CharSequence generate() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("package ");
    _builder.append(this.packageName, "");
    _builder.append(";");
    _builder.newLineIfNotEmpty();
    _builder.append("import java.util.*;");
    _builder.newLine();
    _builder.append("import java.math.BigDecimal;");
    _builder.newLine();
    _builder.append("import javax.persistence.*;");
    _builder.newLine();
    _builder.append("import com.coral.foundation.persistence.*;");
    _builder.newLine();
    _builder.newLine();
    _builder.append("/**");
    _builder.newLine();
    _builder.append("  ");
    _builder.append("* <p>Title: ");
    _builder.append(this.packageName, "  ");
    _builder.append(".");
    _builder.append(this.entityName, "  ");
    _builder.append(" + \"</p>");
    _builder.newLineIfNotEmpty();
    _builder.append("  ");
    _builder.append("* <p>Description: The ");
    _builder.append(this.entityName, "  ");
    _builder.append(" entity </p>");
    _builder.newLineIfNotEmpty();
    _builder.append("  ");
    _builder.append("*/");
    _builder.newLine();
    String _tableName = this.entity.getTableName();
    CharSequence _entityAnnotation = this.getEntityAnnotation(this.entityName, _tableName);
    _builder.append(_entityAnnotation, "");
    _builder.newLineIfNotEmpty();
    _builder.append("public class ");
    _builder.append(this.entityName, "");
    _builder.append(" extends JPABaseEntity {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    CharSequence _idAnnotation = this.getIdAnnotation(this.entityIdField);
    _builder.append(_idAnnotation, "	");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("private Long ");
    _builder.append(this.entityIdField, "	");
    _builder.append(";");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    CharSequence _generatePropertyAnnotation = this.generatePropertyAnnotation(this.entity);
    _builder.append(_generatePropertyAnnotation, "	");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("\t");
    CharSequence _generateNormalGetterSetter = this.generateNormalGetterSetter("Long", this.entityIdField);
    _builder.append(_generateNormalGetterSetter, "	");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    CharSequence _generateEntityMethod = this.generateEntityMethod(this.entity);
    _builder.append(_generateEntityMethod, "	");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public Long getID() {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("return ");
    String _ter = StrUtils.getter(this.entityIdField);
    _builder.append(_ter, "		");
    _builder.append("();");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence generatePropertyAnnotation(final Entity entity) {
    StringConcatenation _builder = new StringConcatenation();
    {
      for(final Property m : entity.properties) {
        final String type = m.getType();
        _builder.newLineIfNotEmpty();
        final String length = m.length;
        _builder.newLineIfNotEmpty();
        final String ref = m.getRef();
        _builder.newLineIfNotEmpty();
        final boolean refer = m.isRefer();
        _builder.newLineIfNotEmpty();
        final String mappedBy = m.getMappedBy();
        _builder.newLineIfNotEmpty();
        final String orm = m.getOrm();
        _builder.newLineIfNotEmpty();
        final String columnName = m.getColumnName();
        _builder.newLineIfNotEmpty();
        final String propertyName = m.getPropertyName();
        _builder.newLineIfNotEmpty();
        {
          boolean _equals = type.equals("Date");
          if (_equals) {
            CharSequence _dateAnnotation = this.getDateAnnotation(propertyName, columnName);
            _builder.append(_dateAnnotation, "");
            _builder.newLineIfNotEmpty();
            _builder.append("private ");
            _builder.append(type, "");
            _builder.append(" ");
            _builder.append(propertyName, "");
            _builder.append(";");
            _builder.newLineIfNotEmpty();
          } else {
            boolean _equals_1 = "one-to-many".equals(orm);
            if (_equals_1) {
              CharSequence _oneToManyAnnotation = this.getOneToManyAnnotation(ref);
              _builder.append(_oneToManyAnnotation, "");
              _builder.newLineIfNotEmpty();
              {
                boolean _equals_2 = type.equals("List");
                if (_equals_2) {
                  _builder.append("private List<");
                  _builder.append(ref, "");
                  _builder.append("> ");
                  _builder.append(propertyName, "");
                  _builder.append(" = new ArrayList<");
                  _builder.append(ref, "");
                  _builder.append(">();");
                  _builder.newLineIfNotEmpty();
                } else {
                  _builder.append("private ");
                  _builder.append(type, "");
                  _builder.append("<");
                  _builder.append(ref, "");
                  _builder.append("> ");
                  _builder.append(propertyName, "");
                  _builder.append(";");
                  _builder.newLineIfNotEmpty();
                }
              }
            } else {
              boolean _equals_3 = "one-to-one".equals(orm);
              if (_equals_3) {
                CharSequence _oneToOneAnnotation = this.getOneToOneAnnotation(ref, mappedBy, refer);
                _builder.append(_oneToOneAnnotation, "");
                _builder.newLineIfNotEmpty();
                _builder.append("private ");
                _builder.append(ref, "");
                _builder.append(" ");
                _builder.append(propertyName, "");
                _builder.append(";");
                _builder.newLineIfNotEmpty();
              } else {
                boolean _equals_4 = "many-to-one".equals(orm);
                if (_equals_4) {
                  CharSequence _manyToOneAnnotation = this.getManyToOneAnnotation(ref, propertyName);
                  _builder.append(_manyToOneAnnotation, "");
                  _builder.newLineIfNotEmpty();
                  _builder.append("private ");
                  _builder.append(ref, "");
                  _builder.append(" ");
                  _builder.append(propertyName, "");
                  _builder.append(";");
                  _builder.newLineIfNotEmpty();
                } else {
                  boolean _isIgnore = m.isIgnore();
                  if (_isIgnore) {
                    CharSequence _transientAnnotation = this.getTransientAnnotation();
                    _builder.append(_transientAnnotation, "");
                    _builder.newLineIfNotEmpty();
                    String _defaultValue = m.getDefaultValue();
                    CharSequence _variableDefinition = this.getVariableDefinition(type, propertyName, _defaultValue);
                    _builder.append(_variableDefinition, "");
                    _builder.newLineIfNotEmpty();
                  } else {
                    CharSequence _commonAnnotation = this.getCommonAnnotation(propertyName, columnName, length);
                    _builder.append(_commonAnnotation, "");
                    _builder.newLineIfNotEmpty();
                    String _defaultValue_1 = m.getDefaultValue();
                    CharSequence _variableDefinition_1 = this.getVariableDefinition(type, propertyName, _defaultValue_1);
                    _builder.append(_variableDefinition_1, "");
                    _builder.newLineIfNotEmpty();
                  }
                }
              }
            }
          }
        }
        _builder.newLine();
      }
    }
    return _builder;
  }
  
  public CharSequence generateEntityMethod(final Entity entity) {
    StringConcatenation _builder = new StringConcatenation();
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
          boolean _equals = type.equals("List");
          if (_equals) {
            CharSequence _generateListGetterSetter = this.generateListGetterSetter(type, ref, propertyName);
            _builder.append(_generateListGetterSetter, "");
            _builder.newLineIfNotEmpty();
          } else {
            CharSequence _generateNormalGetterSetter = this.generateNormalGetterSetter(type, propertyName);
            _builder.append(_generateNormalGetterSetter, "");
            _builder.newLineIfNotEmpty();
          }
        }
      }
    }
    return _builder;
  }
  
  public CharSequence generateNormalGetterSetter(final String type, final String propertyName) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("public void ");
    String _setter = StrUtils.setter(propertyName);
    _builder.append(_setter, "");
    _builder.append(" (");
    _builder.append(type, "");
    _builder.append(" ");
    _builder.append(propertyName, "");
    _builder.append(") {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("this.");
    _builder.append(propertyName, "	");
    _builder.append(" = ");
    _builder.append(propertyName, "	");
    _builder.append(";");
    _builder.newLineIfNotEmpty();
    _builder.append("} ");
    _builder.newLine();
    _builder.append("public ");
    _builder.append(type, "");
    _builder.append(" ");
    String _ter = StrUtils.getter(propertyName);
    _builder.append(_ter, "");
    _builder.append(" () {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("return ");
    _builder.append(propertyName, "	");
    _builder.append(";");
    _builder.newLineIfNotEmpty();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence generateListGetterSetter(final String type, final String ref, final String propertyName) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("public void ");
    String _setter = StrUtils.setter(propertyName);
    _builder.append(_setter, "");
    _builder.append(" (");
    _builder.append(type, "");
    _builder.append("<");
    _builder.append(ref, "");
    _builder.append("> ");
    _builder.append(propertyName, "");
    _builder.append(") {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("this.");
    _builder.append(propertyName, "	");
    _builder.append(" = ");
    _builder.append(propertyName, "	");
    _builder.append(";");
    _builder.newLineIfNotEmpty();
    _builder.append("} ");
    _builder.newLine();
    _builder.append("public ");
    _builder.append(type, "");
    _builder.append("<");
    _builder.append(ref, "");
    _builder.append("> ");
    String _ter = StrUtils.getter(propertyName);
    _builder.append(_ter, "");
    _builder.append(" () {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("return ");
    _builder.append(propertyName, "	");
    _builder.append(";");
    _builder.newLineIfNotEmpty();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence getEntityAnnotation(final String entityName, final String tableName) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("@Entity(name = \"");
    _builder.append(entityName, "");
    _builder.append("\")");
    _builder.newLineIfNotEmpty();
    {
      boolean _notEquals = (!Objects.equal(tableName, null));
      if (_notEquals) {
        _builder.append("@Table(name = \"");
        _builder.append(tableName, "");
        _builder.append("\")");
        _builder.newLineIfNotEmpty();
      } else {
        _builder.append("@Table(name = \"T_");
        String _genDBName = StrUtils.genDBName(entityName);
        _builder.append(_genDBName, "");
        _builder.append("\")");
        _builder.newLineIfNotEmpty();
      }
    }
    return _builder;
  }
  
  public CharSequence getIdAnnotation(final String entityIdField) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("@Id()");
    _builder.newLine();
    _builder.append("@Column (name = \"");
    String _genDBName = StrUtils.genDBName(entityIdField);
    _builder.append(_genDBName, "");
    _builder.append("\")");
    _builder.newLineIfNotEmpty();
    String _upperCase = entityIdField.toUpperCase();
    final String seqName = (_upperCase + "_SEQ");
    _builder.newLineIfNotEmpty();
    _builder.append("@GeneratedValue(generator=\"");
    _builder.append(seqName, "");
    _builder.append("\")");
    _builder.newLineIfNotEmpty();
    _builder.append("@TableGenerator(name=\"");
    _builder.append(seqName, "");
    _builder.append("\", table=\"SEQUENCE\", pkColumnName=\"SEQ_NAME\", valueColumnName=\"SEQ_COUNT\", allocationSize=1)");
    _builder.newLineIfNotEmpty();
    return _builder;
  }
  
  public CharSequence getDateAnnotation(final String fieldName, final String columnName) {
    StringConcatenation _builder = new StringConcatenation();
    CharSequence _columnAnnotation = this.getColumnAnnotation(fieldName, columnName, null);
    _builder.append(_columnAnnotation, "");
    _builder.newLineIfNotEmpty();
    _builder.append("@Temporal(TemporalType.DATE)");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence getCommonAnnotation(final String fieldName, final String columnName, final String length) {
    StringConcatenation _builder = new StringConcatenation();
    CharSequence _columnAnnotation = this.getColumnAnnotation(fieldName, columnName, length);
    _builder.append(_columnAnnotation, "");
    _builder.newLineIfNotEmpty();
    return _builder;
  }
  
  public CharSequence getColumnAnnotation(final String fieldName, final String columnName, final String length) {
    StringConcatenation _builder = new StringConcatenation();
    {
      boolean _notEquals = (!Objects.equal(columnName, null));
      if (_notEquals) {
        _builder.append("@Column(name = \"");
        _builder.append(columnName, "");
        _builder.append("\" ");
        String _lengthParameter = this.getLengthParameter(length);
        _builder.append(_lengthParameter, "");
        _builder.append(")");
        _builder.newLineIfNotEmpty();
      } else {
        _builder.append("@Column(name = \"");
        String _genDBName = StrUtils.genDBName(fieldName);
        _builder.append(_genDBName, "");
        _builder.append("\" ");
        String _lengthParameter_1 = this.getLengthParameter(length);
        _builder.append(_lengthParameter_1, "");
        _builder.append(")");
        _builder.newLineIfNotEmpty();
      }
    }
    return _builder;
  }
  
  public String getLengthParameter(final String length) {
    boolean _equals = Objects.equal(length, null);
    if (_equals) {
      return "";
    } else {
      return (",length = " + length);
    }
  }
  
  public CharSequence getOneToOneAnnotation(final String ref, final String mappedBy, final boolean refer) {
    StringConcatenation _builder = new StringConcatenation();
    {
      if (refer) {
        _builder.append("@OneToOne(targetEntity = ");
        _builder.append(ref, "");
        _builder.append(".class)");
        _builder.newLineIfNotEmpty();
      } else {
        _builder.append("@OneToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, targetEntity = ");
        _builder.append(ref, "");
        _builder.append(".class)");
        _builder.newLineIfNotEmpty();
      }
    }
    return _builder;
  }
  
  public CharSequence getOneToManyAnnotation(final String ref) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("@OneToMany(targetEntity=");
    _builder.append(ref, "");
    _builder.append(".class, cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})");
    _builder.newLineIfNotEmpty();
    _builder.append("@JoinColumn(name=\"");
    String _genDBName = StrUtils.genDBName(this.entityIdField);
    _builder.append(_genDBName, "");
    _builder.append("\")");
    _builder.newLineIfNotEmpty();
    return _builder;
  }
  
  public CharSequence getManyToOneAnnotation(final String ref, final String fieldName) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("@ManyToOne");
    _builder.newLine();
    _builder.append("@JoinColumn(name=\"");
    _builder.append(fieldName, "");
    _builder.append("\")");
    _builder.newLineIfNotEmpty();
    return _builder;
  }
  
  public CharSequence getTransientAnnotation() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("@Transient");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence getVariableDefinition(final String type, final String propertyName, final String defaultValue) {
    StringConcatenation _builder = new StringConcatenation();
    {
      boolean _equals = Objects.equal(defaultValue, null);
      if (_equals) {
        _builder.append("private ");
        _builder.append(type, "");
        _builder.append(" ");
        _builder.append(propertyName, "");
        _builder.append(";");
        _builder.newLineIfNotEmpty();
      } else {
        {
          boolean _equals_1 = "String".equals(type);
          if (_equals_1) {
            _builder.append("private ");
            _builder.append(type, "");
            _builder.append(" ");
            _builder.append(propertyName, "");
            _builder.append(" = \"");
            _builder.append(defaultValue, "");
            _builder.append("\";");
            _builder.newLineIfNotEmpty();
          } else {
            boolean _equals_2 = "Long".equals(type);
            if (_equals_2) {
              _builder.append("private ");
              _builder.append(type, "");
              _builder.append(" ");
              _builder.append(propertyName, "");
              _builder.append(" = new Long(");
              _builder.append(defaultValue, "");
              _builder.append(");");
              _builder.newLineIfNotEmpty();
            } else {
              boolean _equals_3 = "BigDecimal".equals(type);
              if (_equals_3) {
                _builder.append("private ");
                _builder.append(type, "");
                _builder.append(" ");
                _builder.append(propertyName, "");
                _builder.append(" = new BigDecimal(\"");
                _builder.append(defaultValue, "");
                _builder.append("\");");
                _builder.newLineIfNotEmpty();
              }
            }
          }
        }
      }
    }
    _builder.newLine();
    return _builder;
  }
}
