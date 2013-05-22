package com.coral.foundation.md.hbm.impl;

import com.coral.foundation.md.model.Entity;
import com.coral.foundation.md.model.Mocha;
import com.coral.foundation.md.model.Property;
import com.coral.foundation.utils.StrUtils;
import com.google.common.base.Objects;
import java.util.List;
import java.util.Properties;
import org.eclipse.xtend2.lib.StringConcatenation;

@SuppressWarnings("all")
public class HbmTemplate {
  public CharSequence generateCfg(final Properties p) {
    StringConcatenation _builder = new StringConcatenation();
    final Object driverClass = p.get("driverClass");
    _builder.newLineIfNotEmpty();
    final Object url = p.get("url");
    _builder.newLineIfNotEmpty();
    final Object userName = p.get("userName");
    _builder.newLineIfNotEmpty();
    final Object password = p.get("password");
    _builder.newLineIfNotEmpty();
    final Object poolSize = p.get("poolSize");
    _builder.newLineIfNotEmpty();
    final Object dialect = p.get("dialect");
    _builder.newLineIfNotEmpty();
    final Object showSql = p.get("showSql");
    _builder.newLineIfNotEmpty();
    final Object formatSql = p.get("formatSql");
    _builder.newLineIfNotEmpty();
    final Object hbm2ddlAuto = p.get("hbm2ddlAuto");
    _builder.newLineIfNotEmpty();
    final Object hbmfile = p.get("hbmfile");
    _builder.newLineIfNotEmpty();
    _builder.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
    _builder.newLine();
    _builder.append("<!DOCTYPE hibernate-configuration PUBLIC \"-//Hibernate/Hibernate Configuration DTD 3.0//EN\" \"http://hibernate.sourceforge.net/hibernate-configuration-3.0.dtd\">");
    _builder.newLine();
    _builder.append("<hibernate-configuration>");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("<session-factory>");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("<property name=\"hibernate.connection.driver_class\">");
    _builder.append(driverClass, "        ");
    _builder.append("</property>");
    _builder.newLineIfNotEmpty();
    _builder.append("        ");
    _builder.append("<property name=\"hibernate.connection.url\">");
    _builder.append(url, "        ");
    _builder.append("</property>");
    _builder.newLineIfNotEmpty();
    _builder.append("        ");
    _builder.append("<property name=\"hibernate.connection.username\">");
    _builder.append(userName, "        ");
    _builder.append("</property>");
    _builder.newLineIfNotEmpty();
    _builder.append("        ");
    _builder.append("<property name=\"connection.password\">");
    _builder.append(password, "        ");
    _builder.append("</property>");
    _builder.newLineIfNotEmpty();
    _builder.append("        ");
    _builder.append("<property name=\"connection.useUnicode\">true</property>");
    _builder.newLine();
    _builder.append("        \t\t");
    _builder.append("<property name=\"connection.characterEncoding\">UTF-8</property>");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("<property name=\"connection.pool_size\">");
    _builder.append(poolSize, "        ");
    _builder.append("</property>");
    _builder.newLineIfNotEmpty();
    _builder.append("        ");
    _builder.append("<property name=\"hibernate.dialect\">");
    _builder.append(dialect, "        ");
    _builder.append("</property>");
    _builder.newLineIfNotEmpty();
    _builder.append("        ");
    _builder.append("<property name=\"show_sql\">");
    _builder.append(showSql, "        ");
    _builder.append("</property>");
    _builder.newLineIfNotEmpty();
    _builder.append("        ");
    _builder.append("<property name=\"format_sql\">");
    _builder.append(formatSql, "        ");
    _builder.append("</property>");
    _builder.newLineIfNotEmpty();
    _builder.append("        ");
    _builder.append("<property name=\"hbm2ddl.auto\">");
    _builder.append(hbm2ddlAuto, "        ");
    _builder.append("</property>");
    _builder.newLineIfNotEmpty();
    _builder.append("        ");
    _builder.append("<property name=\"hibernate.connection.useUnicode\">true</property>");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("<property name=\"hibernate.connection.characterEncoding\">UTF-8</property>");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("<!-- Enable Hibernate\'s automatic session context management -->");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("<property name=\"current_session_context_class\">thread</property>");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("<property name=\"javax.persistence.validation.mode\">none</property>");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("<property name=\"connection.autocommit\">true</property>");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("<mapping resource=\"");
    _builder.append(hbmfile, "        ");
    _builder.append("\"/>");
    _builder.newLineIfNotEmpty();
    _builder.append("    ");
    _builder.append("</session-factory>");
    _builder.newLine();
    _builder.append("</hibernate-configuration>");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence generateHbm(final List<Mocha> coralList) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
    _builder.newLine();
    _builder.append("<!DOCTYPE hibernate-mapping PUBLIC \"-//Hibernate/Hibernate Mapping DTD 3.0//EN\" \"http://hibernate.sourceforge.net/hibernate-mapping-3.0.dtd\">");
    _builder.newLine();
    _builder.append("<hibernate-mapping>");
    _builder.newLine();
    {
      for(final Mocha coral : coralList) {
        _builder.append("\t");
        final String entityPackage = coral.getEntityPackage();
        _builder.newLineIfNotEmpty();
        {
          List<Entity> _entityList = coral.getEntityList();
          for(final Entity e : _entityList) {
            _builder.append("\t");
            final String entityName = e.getEntityName();
            _builder.newLineIfNotEmpty();
            _builder.append("\t");
            String _plus = (entityPackage + ".");
            final String fullEntityName = (_plus + entityName);
            _builder.newLineIfNotEmpty();
            _builder.append("\t");
            _builder.append("<class name=\"");
            _builder.append(entityPackage, "	");
            _builder.append(".");
            _builder.append(entityName, "	");
            _builder.append("\" table=\"");
            String _genDBName = StrUtils.genDBName(entityName);
            _builder.append(_genDBName, "	");
            _builder.append("\" lazy=\"false\">");
            _builder.newLineIfNotEmpty();
            _builder.append("\t");
            _builder.append("\t");
            _builder.append("<id name=\"id\" column=\"ID\" type=\"java.lang.Long\">");
            _builder.newLine();
            _builder.append("\t");
            _builder.append("\t\t");
            _builder.append("<generator class=\"native\"/>");
            _builder.newLine();
            _builder.append("\t");
            _builder.append("\t");
            _builder.append("</id>");
            _builder.newLine();
            {
              List<Property> _properties = e.getProperties();
              for(final Property p : _properties) {
                _builder.append("\t");
                _builder.append("\t");
                final String pn = p.getPropertyName();
                _builder.newLineIfNotEmpty();
                _builder.append("\t");
                _builder.append("\t");
                final String cn = StrUtils.genDBName(pn);
                _builder.newLineIfNotEmpty();
                _builder.append("\t");
                _builder.append("\t");
                final String type = p.getType();
                _builder.newLineIfNotEmpty();
                _builder.append("\t");
                _builder.append("\t");
                final String ref = p.getRef();
                _builder.newLineIfNotEmpty();
                _builder.append("\t");
                _builder.append("\t");
                final String orm = p.getOrm();
                _builder.newLineIfNotEmpty();
                {
                  boolean _equals = Objects.equal(orm, null);
                  if (_equals) {
                    {
                      boolean _equals_1 = "String".equals(type);
                      if (_equals_1) {
                        _builder.append("\t");
                        _builder.append("\t");
                        _builder.append("<property name=\"");
                        _builder.append(pn, "		");
                        _builder.append("\" column=\"");
                        _builder.append(cn, "		");
                        _builder.append("\" not-null=\"");
                        _builder.append(p.required, "		");
                        _builder.append("\" type=\"java.lang.String\"/>");
                        _builder.newLineIfNotEmpty();
                      } else {
                        boolean _equals_2 = "Integer".equals(type);
                        if (_equals_2) {
                          _builder.append("\t");
                          _builder.append("\t");
                          _builder.append("<property name=\"");
                          _builder.append(pn, "		");
                          _builder.append("\" column=\"");
                          _builder.append(cn, "		");
                          _builder.append("\" not-null=\"");
                          _builder.append(p.required, "		");
                          _builder.append("\" type=\"java.lang.Integer\"/>");
                          _builder.newLineIfNotEmpty();
                        } else {
                          boolean _equals_3 = "Long".equals(type);
                          if (_equals_3) {
                            _builder.append("\t");
                            _builder.append("\t");
                            _builder.append("<property name=\"");
                            _builder.append(pn, "		");
                            _builder.append("\" column=\"");
                            _builder.append(cn, "		");
                            _builder.append("\" not-null=\"");
                            _builder.append(p.required, "		");
                            _builder.append("\" type=\"java.lang.Long\"/>");
                            _builder.newLineIfNotEmpty();
                          } else {
                            boolean _equals_4 = "Date".equals(type);
                            if (_equals_4) {
                              _builder.append("\t");
                              _builder.append("\t");
                              _builder.append("<property name=\"");
                              _builder.append(pn, "		");
                              _builder.append("\" column=\"");
                              _builder.append(cn, "		");
                              _builder.append("\" not-null=\"");
                              _builder.append(p.required, "		");
                              _builder.append("\" type=\"timestamp\"/>");
                              _builder.newLineIfNotEmpty();
                            } else {
                              boolean _equals_5 = "BigDecimal".equals(type);
                              if (_equals_5) {
                                _builder.append("\t");
                                _builder.append("\t");
                                _builder.append("<property name=\"");
                                _builder.append(pn, "		");
                                _builder.append("\" column=\"");
                                _builder.append(cn, "		");
                                _builder.append("\" not-null=\"");
                                _builder.append(p.required, "		");
                                _builder.append("\" type=\"java.math.BigDecimal\"/>");
                                _builder.newLineIfNotEmpty();
                              }
                            }
                          }
                        }
                      }
                    }
                  } else {
                    {
                      boolean _equals_6 = orm.equals("one-to-many");
                      if (_equals_6) {
                        {
                          boolean _equals_7 = type.equals("Set");
                          if (_equals_7) {
                            _builder.append("\t");
                            _builder.append("\t");
                            _builder.append("<set name=\"");
                            _builder.append(pn, "		");
                            _builder.append("\" cascade=\"all\" inverse=\"false\" lazy=\"false\">");
                            _builder.newLineIfNotEmpty();
                            _builder.append("\t");
                            _builder.append("\t");
                            _builder.append("\t");
                            _builder.append("<key column=\"");
                            _builder.append(entityName, "			");
                            _builder.append("__ID\"/>");
                            _builder.newLineIfNotEmpty();
                            _builder.append("\t");
                            _builder.append("\t");
                            _builder.append("\t");
                            _builder.append("<one-to-many class=\"");
                            String _refEntityClass = this.getRefEntityClass(coralList, ref);
                            _builder.append(_refEntityClass, "			");
                            _builder.append("\"/>");
                            _builder.newLineIfNotEmpty();
                            _builder.append("\t");
                            _builder.append("\t");
                            _builder.append("</set>");
                            _builder.newLine();
                          } else {
                            boolean _equals_8 = type.equals("List");
                            if (_equals_8) {
                              _builder.append("\t");
                              _builder.append("\t");
                              _builder.append("<list name=\"");
                              _builder.append(pn, "		");
                              _builder.append("\"  cascade=\"all\" inverse=\"false\" lazy=\"false\">");
                              _builder.newLineIfNotEmpty();
                              _builder.append("\t");
                              _builder.append("\t");
                              _builder.append("\t");
                              _builder.append("<key column=\"");
                              _builder.append(ref, "			");
                              _builder.append("__ID\" not-null=\"true\"/>");
                              _builder.newLineIfNotEmpty();
                              _builder.append("\t");
                              _builder.append("\t");
                              _builder.append("\t");
                              _builder.append("<list-index column=\"");
                              _builder.append(pn, "			");
                              _builder.append("Idx\" />");
                              _builder.newLineIfNotEmpty();
                              _builder.append("\t");
                              _builder.append("\t");
                              _builder.append("\t");
                              _builder.append("<one-to-many class=\"");
                              String _refEntityClass_1 = this.getRefEntityClass(coralList, ref);
                              _builder.append(_refEntityClass_1, "			");
                              _builder.append("\"/>");
                              _builder.newLineIfNotEmpty();
                              _builder.append("\t");
                              _builder.append("\t");
                              _builder.append("</list>");
                              _builder.newLine();
                            }
                          }
                        }
                      } else {
                        boolean _equals_9 = orm.equals("many-to-one");
                        if (_equals_9) {
                          _builder.append("\t");
                          _builder.append("\t");
                          _builder.append("<many-to-one name=\"");
                          _builder.append(pn, "		");
                          _builder.append("\" class=\"");
                          _builder.append(fullEntityName, "		");
                          _builder.append("\" ");
                          _builder.newLineIfNotEmpty();
                          _builder.append("\t");
                          _builder.append("\t");
                          _builder.append("\t");
                          _builder.append("cascade=\"save-update\" not-null=\"false\" column=\"");
                          _builder.append(ref, "			");
                          _builder.append("__ID\" />");
                          _builder.newLineIfNotEmpty();
                        } else {
                          boolean _equals_10 = orm.equals("one-to-one");
                          if (_equals_10) {
                          }
                        }
                      }
                    }
                  }
                }
              }
            }
            _builder.append("\t");
            _builder.append("\t");
            _builder.append("<property name=\"abstractData\" column=\"ABSTRACT_DATA\" not-null=\"false\" type=\"java.lang.String\"/>");
            _builder.newLine();
            _builder.append("\t");
            _builder.append("\t");
            _builder.append("<property name=\"createDate\" column=\"CREATE_TIME\" not-null=\"false\" type=\"timestamp\"/>");
            _builder.newLine();
            _builder.append("\t");
            _builder.append("\t");
            _builder.append("<property name=\"lastUpdateDate\" column=\"LAST_MODIFIED_TIME\" not-null=\"false\" type=\"timestamp\"/>");
            _builder.newLine();
            _builder.append("\t");
            _builder.append("</class>");
            _builder.newLine();
          }
        }
      }
    }
    _builder.append("</hibernate-mapping>");
    _builder.newLine();
    return _builder;
  }
  
  public String getRefEntityClass(final List<Mocha> coralList, final String ref) {
    for (final Mocha coral : coralList) {
      {
        final String entityPackage = coral.getEntityPackage();
        List<Entity> _entityList = coral.getEntityList();
        for (final Entity e : _entityList) {
          String _entityName = e.getEntityName();
          boolean _equals = ref.equals(_entityName);
          if (_equals) {
            String _plus = (entityPackage + ".");
            return (_plus + ref);
          }
        }
      }
    }
    return "";
  }
}
