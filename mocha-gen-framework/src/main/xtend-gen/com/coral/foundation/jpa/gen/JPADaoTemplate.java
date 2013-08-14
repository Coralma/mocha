package com.coral.foundation.jpa.gen;

import com.coral.foundation.md.model.Entity;
import com.coral.foundation.md.model.Mocha;
import com.coral.foundation.md.model.helper.VGenHelper;
import com.coral.foundation.utils.StrUtils;
import java.util.List;
import org.eclipse.xtend2.lib.StringConcatenation;

@SuppressWarnings("all")
public class JPADaoTemplate {
  private Entity entity;
  
  private List<Mocha> corals;
  
  private String entityName;
  
  private String entityVariable;
  
  private String daoImplClassName;
  
  private String daoIntfPackage;
  
  private String daoImplPackage;
  
  public String init(final Entity entity, final List<Mocha> corals) {
    String _xblockexpression = null;
    {
      this.entity = entity;
      this.corals = corals;
      this.entityName = entity.entityName;
      String _lowCaseFirstLetter = StrUtils.lowCaseFirstLetter(this.entityName);
      this.entityVariable = _lowCaseFirstLetter;
      final Mocha currentCoral = VGenHelper.getCurrentCoral(entity, corals);
      String _daoImplPackage = currentCoral.getDaoImplPackage();
      this.daoImplPackage = _daoImplPackage;
      String _genDao = VGenHelper.genDao(this.entityName);
      String _daoImplClassName = this.daoImplClassName = _genDao;
      _xblockexpression = (_daoImplClassName);
    }
    return _xblockexpression;
  }
  
  public CharSequence generateImplement() {
    StringConcatenation _builder = new StringConcatenation();
    CharSequence _GENImplementPackageImport = this.GENImplementPackageImport();
    _builder.append(_GENImplementPackageImport, "");
    _builder.newLineIfNotEmpty();
    CharSequence _GENImplementHead = this.GENImplementHead();
    _builder.append(_GENImplementHead, "");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    String _GENDefaultMethod = this.GENDefaultMethod();
    _builder.append(_GENDefaultMethod, "	");
    _builder.newLineIfNotEmpty();
    CharSequence _GENClassEnd = this.GENClassEnd(this.daoImplClassName);
    _builder.append(_GENClassEnd, "");
    _builder.newLineIfNotEmpty();
    return _builder;
  }
  
  public CharSequence GENImplementPackageImport() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("package ");
    _builder.append(this.daoImplPackage, "");
    _builder.append(";");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("import ");
    String _currentEntityPackage = VGenHelper.getCurrentEntityPackage(this.entity, this.corals);
    String _plus = (_currentEntityPackage + ".*");
    _builder.append(_plus, "");
    _builder.append(";");
    _builder.newLineIfNotEmpty();
    _builder.append("import com.coral.foundation.persistence.BaseDao;");
    _builder.newLine();
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence GENImplementHead() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("/**");
    _builder.newLine();
    _builder.append("  ");
    _builder.append("* ");
    _builder.append(this.daoImplClassName, "  ");
    _builder.append(" is a auto Generated class. Please don\'t modify it.");
    _builder.newLineIfNotEmpty();
    _builder.append("  ");
    _builder.append("*/");
    _builder.newLine();
    _builder.append("public class ");
    _builder.append(this.daoImplClassName, "");
    _builder.append(" extends BaseDao<");
    _builder.append(this.entityName, "");
    _builder.append("> {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.newLine();
    return _builder;
  }
  
  private String GENDefaultMethod() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("@Override");
    _builder.newLine();
    _builder.append("public Class<");
    _builder.append(this.entityName, "");
    _builder.append("> getEntityClass() {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("return ");
    _builder.append(this.entityName, "	");
    _builder.append(".class;");
    _builder.newLineIfNotEmpty();
    _builder.append("}");
    _builder.newLine();
    return _builder.toString();
  }
  
  public CharSequence GENClassEnd(final String className) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
}
