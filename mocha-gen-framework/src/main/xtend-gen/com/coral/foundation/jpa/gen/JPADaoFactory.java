package com.coral.foundation.jpa.gen;

import com.coral.foundation.constant.SystemConstant;
import com.coral.foundation.md.model.Entity;
import com.coral.foundation.md.model.Mocha;
import com.coral.foundation.md.model.helper.VGenHelper;
import java.util.List;
import org.eclipse.xtend2.lib.StringConcatenation;

/**
 * public class GeneratedDaoFactory implements DaoFactory {
 * 
 * public Dao getDao(Class entityClass) {
 * if(entityClass.equals(CargoPolicy.class)) {
 * return SpringContextUtils.getBean(CargoPolicyDao.class);
 * }
 * if(entityClass.equals(CargoInsured.class)) {
 * return SpringContextUtils.getBean(CargoInsuredDao.class);
 * }
 * return null;
 * }
 * }
 */
@SuppressWarnings("all")
public class JPADaoFactory {
  private List<Mocha> corals;
  
  private String viewClassName;
  
  public String init(final List<Mocha> corals, final String viewClassName) {
    String _xblockexpression = null;
    {
      this.corals = corals;
      String _viewClassName = this.viewClassName = viewClassName;
      _xblockexpression = (_viewClassName);
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
    CharSequence _GENBuildMethod = this.GENBuildMethod();
    _builder.append(_GENBuildMethod, "	");
    _builder.newLineIfNotEmpty();
    CharSequence _GENClassEnd = this.GENClassEnd();
    _builder.append(_GENClassEnd, "");
    _builder.newLineIfNotEmpty();
    return _builder;
  }
  
  public CharSequence GENPackageImport() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("package ");
    _builder.append(SystemConstant.GENERATED_PAGE, "");
    _builder.append(";");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("import com.coral.vaadin.controller.PageFactory;");
    _builder.newLine();
    _builder.append("import ");
    String _plus = (SystemConstant.TCREATE_PAGE_PKG + ".*");
    _builder.append(_plus, "");
    _builder.append(";");
    _builder.newLineIfNotEmpty();
    _builder.append("import ");
    String _plus_1 = (SystemConstant.TSEARCH_PAGE_PKG + ".*");
    _builder.append(_plus_1, "");
    _builder.append(";");
    _builder.newLineIfNotEmpty();
    _builder.append("import ");
    String _plus_2 = (SystemConstant.TCREATE_PRESENTER_PKG + ".*");
    _builder.append(_plus_2, "");
    _builder.append(";");
    _builder.newLineIfNotEmpty();
    _builder.append("import ");
    String _plus_3 = (SystemConstant.TSEARCH_PRESENTER_PKG + ".*");
    _builder.append(_plus_3, "");
    _builder.append(";");
    _builder.newLineIfNotEmpty();
    _builder.append("import com.coral.vaadin.controller.Presenter;");
    _builder.newLine();
    _builder.append("import com.coral.vaadin.widget.Viewer;");
    _builder.newLine();
    _builder.append("import com.coral.foundation.core.impl.MochaEventBus;");
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
    _builder.append(this.viewClassName, "  ");
    _builder.append(" is a auto Generated class. Please don\'t modify it.");
    _builder.newLineIfNotEmpty();
    _builder.append("  ");
    _builder.append("* @author Coral");
    _builder.newLine();
    _builder.append("  ");
    _builder.append("*/");
    _builder.newLine();
    _builder.append("public class ");
    _builder.append(this.viewClassName, "");
    _builder.append(" implements PageFactory {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence GENBuildMethod() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("public Dao getDao(Class entityClass) {");
    _builder.newLine();
    {
      for(final Mocha coral : this.corals) {
        {
          List<Entity> _entityList = coral.getEntityList();
          for(final Entity entity : _entityList) {
            _builder.append("\t");
            final String entityName = entity.entityName;
            _builder.newLineIfNotEmpty();
            _builder.append("\t");
            final String daoIntfClassName = VGenHelper.genDaoIntf(entityName);
            _builder.newLineIfNotEmpty();
            _builder.append("\t");
            final String daoImplClassName = VGenHelper.genDaoImpl(entityName);
            _builder.newLineIfNotEmpty();
            _builder.append("\t");
            final String daoImplPackage = coral.getDaoImplPackage();
            _builder.newLineIfNotEmpty();
          }
        }
      }
    }
    _builder.append("\t");
    _builder.append("return null;");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence GENGetterMethod(final String presenterClass, final String viewClass) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("if(\"");
    _builder.append(viewClass, "");
    _builder.append("\".equals(viewerName)) {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("return new ");
    _builder.append(presenterClass, "	");
    _builder.append("(new ");
    _builder.append(viewClass, "	");
    _builder.append("(), eventBus);");
    _builder.newLineIfNotEmpty();
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
