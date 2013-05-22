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
    _builder.append("import javax.persistence.*;");
    _builder.newLine();
    _builder.append("import com.coral.foundation.model.BaseEntity;");
    _builder.newLine();
    _builder.append("import org.hibernate.annotations.Fetch;");
    _builder.newLine();
    _builder.append("import org.hibernate.annotations.FetchMode;");
    _builder.newLine();
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
    String _tableName = entity.getTableName();
    CharSequence _entityAnnotation = this.getEntityAnnotation(entityName, _tableName);
    _builder.append(_entityAnnotation, "");
    _builder.newLineIfNotEmpty();
    _builder.append("public class ");
    _builder.append(entityName, "");
    _builder.append(" extends BaseEntity {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    String _lowCaseFirstLetter = StrUtils.lowCaseFirstLetter(entityName);
    final String entityIdField = (_lowCaseFirstLetter + "Id");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    CharSequence _idAnnotation = this.getIdAnnotation(entityIdField);
    _builder.append(_idAnnotation, "	");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("private Long ");
    _builder.append(entityIdField, "	");
    _builder.append(";");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    CharSequence _generatePropertyAnnotation = this.generatePropertyAnnotation(entity);
    _builder.append(_generatePropertyAnnotation, "	");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("\t");
    CharSequence _generateNormalGetterSetter = this.generateNormalGetterSetter("Long", entityIdField);
    _builder.append(_generateNormalGetterSetter, "	");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    CharSequence _generateEntityMethod = this.generateEntityMethod(entity);
    _builder.append(_generateEntityMethod, "	");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public Long getID() {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("return ");
    String _ter = StrUtils.getter(entityIdField);
    _builder.append(_ter, "		");
    _builder.append("();");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public void setID(Long id) {");
    _builder.newLine();
    _builder.append("\t\t");
    String _setter = StrUtils.setter(entityIdField);
    _builder.append(_setter, "		");
    _builder.append("(id);");
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
    _builder.append("@GeneratedValue(strategy = GenerationType. AUTO)");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence getDateAnnotation(final String fieldName, final String columnName) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("@Basic(optional = true)");
    _builder.newLine();
    CharSequence _columnAnnotation = this.getColumnAnnotation(fieldName, columnName, null);
    _builder.append(_columnAnnotation, "");
    _builder.newLineIfNotEmpty();
    _builder.append("@Temporal(TemporalType.DATE)");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence getCommonAnnotation(final String fieldName, final String columnName, final String length) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("@Basic(optional = true)");
    _builder.newLine();
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
        {
          boolean _isEmpty = StrUtils.isEmpty(mappedBy);
          if (_isEmpty) {
            _builder.append("@OneToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH }, targetEntity = ");
            _builder.append(ref, "");
            _builder.append(".class, fetch=FetchType.EAGER)");
            _builder.newLineIfNotEmpty();
          } else {
            _builder.append("@OneToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH }, mappedBy = \"");
            _builder.append(mappedBy, "");
            _builder.append("\", targetEntity = ");
            _builder.append(ref, "");
            _builder.append(".class, fetch=FetchType.EAGER)");
            _builder.newLineIfNotEmpty();
          }
        }
      } else {
        {
          boolean _isEmpty_1 = StrUtils.isEmpty(mappedBy);
          if (_isEmpty_1) {
            _builder.append("@OneToOne(cascade = { CascadeType.ALL }, targetEntity = ");
            _builder.append(ref, "");
            _builder.append(".class, fetch=FetchType.EAGER)");
            _builder.newLineIfNotEmpty();
          } else {
            _builder.append("@OneToOne(cascade = { CascadeType.ALL }, mappedBy = \"");
            _builder.append(mappedBy, "");
            _builder.append("\", targetEntity = ");
            _builder.append(ref, "");
            _builder.append(".class, fetch=FetchType.EAGER)");
            _builder.newLineIfNotEmpty();
          }
        }
      }
    }
    _builder.append("@Fetch(FetchMode.JOIN)");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence getOneToManyAnnotation(final String ref) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("@OneToMany(cascade = { CascadeType.ALL }, targetEntity = ");
    _builder.append(ref, "");
    _builder.append(".class, fetch=FetchType.EAGER)");
    _builder.newLineIfNotEmpty();
    _builder.append("@Fetch(FetchMode.SUBSELECT)");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence getManyToOneAnnotation(final String ref, final String fieldName) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST,CascadeType.REFRESH }, targetEntity = ");
    _builder.append(ref, "");
    _builder.append(".class, fetch=FetchType.EAGER)");
    _builder.newLineIfNotEmpty();
    _builder.append("@JoinColumns({ @JoinColumn(name = \"");
    _builder.append(fieldName, "");
    _builder.append("\") })");
    _builder.newLineIfNotEmpty();
    _builder.append("@Fetch(FetchMode.JOIN)");
    _builder.newLine();
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
