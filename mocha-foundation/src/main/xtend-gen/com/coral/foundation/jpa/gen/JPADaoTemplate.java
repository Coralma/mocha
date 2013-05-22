package com.coral.foundation.jpa.gen;

import com.coral.foundation.md.model.Entity;
import com.coral.foundation.md.model.Mocha;
import com.coral.foundation.md.model.helper.GLog;
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
  
  private String daoIntfClassName;
  
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
      String _daoIntfPackage = currentCoral.getDaoIntfPackage();
      this.daoIntfPackage = _daoIntfPackage;
      String _daoImplPackage = currentCoral.getDaoImplPackage();
      this.daoImplPackage = _daoImplPackage;
      String _genDaoIntf = VGenHelper.genDaoIntf(this.entityName);
      this.daoIntfClassName = _genDaoIntf;
      String _genDaoImpl = VGenHelper.genDaoImpl(this.entityName);
      String _daoImplClassName = this.daoImplClassName = _genDaoImpl;
      _xblockexpression = (_daoImplClassName);
    }
    return _xblockexpression;
  }
  
  public CharSequence generateInterface() {
    StringConcatenation _builder = new StringConcatenation();
    CharSequence _GENInterfacePackageImport = this.GENInterfacePackageImport();
    _builder.append(_GENInterfacePackageImport, "");
    _builder.newLineIfNotEmpty();
    CharSequence _GENInterfaceHead = this.GENInterfaceHead();
    _builder.append(_GENInterfaceHead, "");
    _builder.newLineIfNotEmpty();
    CharSequence _GENClassEnd = this.GENClassEnd(this.daoIntfClassName);
    _builder.append(_GENClassEnd, "");
    _builder.newLineIfNotEmpty();
    return _builder;
  }
  
  public CharSequence GENInterfacePackageImport() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("package ");
    _builder.append(this.daoIntfPackage, "");
    _builder.append(";");
    _builder.newLineIfNotEmpty();
    _builder.append("import java.util.List;");
    _builder.newLine();
    _builder.append("import com.coral.foundation.jpa.Dao;");
    _builder.newLine();
    _builder.append("import ");
    String _currentEntityPackage = VGenHelper.getCurrentEntityPackage(this.entity, this.corals);
    String _plus = (_currentEntityPackage + ".*");
    _builder.append(_plus, "");
    _builder.append(";");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence GENInterfaceHead() {
    StringConcatenation _builder = new StringConcatenation();
    GLog.startClass(this.daoIntfClassName);
    _builder.newLineIfNotEmpty();
    _builder.append("/**");
    _builder.newLine();
    _builder.append("  ");
    _builder.append("* ");
    _builder.append(this.daoIntfClassName, "  ");
    _builder.append(" is a auto Generated class. Please don\'t modify it.");
    _builder.newLineIfNotEmpty();
    _builder.append("  ");
    _builder.append("* @author Coral");
    _builder.newLine();
    _builder.append("  ");
    _builder.append("*/");
    _builder.newLine();
    _builder.append("public interface ");
    _builder.append(this.daoIntfClassName, "");
    _builder.append(" extends Dao<");
    _builder.append(this.entityName, "");
    _builder.append("> {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.newLine();
    return _builder;
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
    CharSequence _GENConstructor = this.GENConstructor();
    _builder.append(_GENConstructor, "	");
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
    _builder.append("import ");
    String _plus = (this.daoIntfPackage + ".*");
    _builder.append(_plus, "");
    _builder.append(";");
    _builder.newLineIfNotEmpty();
    _builder.append("import ");
    String _currentEntityPackage = VGenHelper.getCurrentEntityPackage(this.entity, this.corals);
    String _plus_1 = (_currentEntityPackage + ".*");
    _builder.append(_plus_1, "");
    _builder.append(";");
    _builder.newLineIfNotEmpty();
    _builder.append("import com.coral.foundation.jpa.impl.JpaDao;");
    _builder.newLine();
    _builder.append("import org.slf4j.Logger;");
    _builder.newLine();
    _builder.append("import org.slf4j.LoggerFactory;");
    _builder.newLine();
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence GENImplementHead() {
    StringConcatenation _builder = new StringConcatenation();
    GLog.startClass(this.daoImplClassName);
    _builder.newLineIfNotEmpty();
    _builder.append("/**");
    _builder.newLine();
    _builder.append("  ");
    _builder.append("* ");
    _builder.append(this.daoImplClassName, "  ");
    _builder.append(" is a auto Generated class. Please don\'t modify it.");
    _builder.newLineIfNotEmpty();
    _builder.append("  ");
    _builder.append("* @author Coral");
    _builder.newLine();
    _builder.append("  ");
    _builder.append("*/");
    _builder.newLine();
    _builder.append("public class ");
    _builder.append(this.daoImplClassName, "");
    _builder.append(" extends JpaDao<");
    _builder.append(this.entityName, "");
    _builder.append("> implements ");
    _builder.append(this.daoIntfClassName, "");
    _builder.append(" {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("Logger log=LoggerFactory.getLogger(");
    _builder.append(this.daoImplClassName, "	");
    _builder.append(".class);");
    _builder.newLineIfNotEmpty();
    return _builder;
  }
  
  public CharSequence GENConstructor() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("public ");
    _builder.append(this.daoImplClassName, "");
    _builder.append("() {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("super();");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("log.debug(\"\"+");
    _builder.append(this.daoImplClassName, "	");
    _builder.append(".class);");
    _builder.newLineIfNotEmpty();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence GENClassEnd(final String className) {
    StringConcatenation _builder = new StringConcatenation();
    GLog.endClass(className);
    _builder.newLineIfNotEmpty();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
}
