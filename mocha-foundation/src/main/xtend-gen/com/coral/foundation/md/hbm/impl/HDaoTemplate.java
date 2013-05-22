package com.coral.foundation.md.hbm.impl;

import com.coral.foundation.md.model.Entity;
import com.coral.foundation.md.model.Mocha;
import org.eclipse.xtend2.lib.StringConcatenation;

@SuppressWarnings("all")
public class HDaoTemplate {
  public CharSequence generate(final Mocha coral, final Entity entity) {
    StringConcatenation _builder = new StringConcatenation();
    final String packageName = coral.getDaoImplPackage();
    _builder.newLineIfNotEmpty();
    final String entityPackage = coral.getEntityPackage();
    _builder.newLineIfNotEmpty();
    final String entityName = entity.getEntityName();
    _builder.newLineIfNotEmpty();
    final String daoName = (entityName + "DAO");
    _builder.newLineIfNotEmpty();
    _builder.append("package ");
    _builder.append(packageName, "");
    _builder.append(";");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("import com.coral.foundation.hibernate.impl.GenericHibernateDAO;");
    _builder.newLine();
    _builder.append("import ");
    _builder.append(entityPackage, "");
    _builder.append(".");
    _builder.append(entityName, "");
    _builder.append(";");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("/**");
    _builder.newLine();
    _builder.append("  ");
    _builder.append("* @author Coral");
    _builder.newLine();
    _builder.append("  ");
    _builder.append("*");
    _builder.newLine();
    _builder.append("  ");
    _builder.append("*/");
    _builder.newLine();
    _builder.append("public class ");
    _builder.append(daoName, "");
    _builder.append(" extends GenericHibernateDAO<");
    _builder.append(entityName, "");
    _builder.append(", Long> {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
}
