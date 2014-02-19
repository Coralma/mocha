package com.mocha.template.general.xtend;

import com.coral.foundation.constant.SystemConstant;
import com.coral.foundation.md.model.Entity;
import com.coral.foundation.md.model.Mocha;
import com.coral.foundation.md.model.View;
import com.coral.foundation.md.model.helper.VAppGenHelper;
import com.coral.foundation.md.model.helper.VGenHelper;
import java.util.List;
import org.eclipse.xtend2.lib.StringConcatenation;

@SuppressWarnings("all")
public class GeneralEntityPresenterTemplate {
  private String entityName;
  
  private String entityPackage;
  
  private String viewPresenterName;
  
  private String viewClassName;
  
  private View view;
  
  private String entityDaoIntf;
  
  private String daoIntfPackage;
  
  private List<Mocha> mochas;
  
  public String init(final View view, final List<Mocha> mochas) {
    String _xblockexpression = null;
    {
      this.view = view;
      this.mochas = mochas;
      String _generateClassName = VAppGenHelper.generateClassName(view.name);
      this.viewClassName = _generateClassName;
      String _genAppMainPagePresenterClassName = VAppGenHelper.genAppMainPagePresenterClassName(view.name);
      this.viewPresenterName = _genAppMainPagePresenterClassName;
      Entity _entity = view.getEntity();
      this.entityName = _entity.entityName;
      String _genDaoIntf = VGenHelper.genDaoIntf(this.entityName);
      this.entityDaoIntf = _genDaoIntf;
      String _entityPackage = view.entity.mocha.getEntityPackage();
      this.entityPackage = _entityPackage;
      String _daoIntfPackage = view.entity.mocha.getDaoIntfPackage();
      String _daoIntfPackage_1 = this.daoIntfPackage = _daoIntfPackage;
      _xblockexpression = (_daoIntfPackage_1);
    }
    return _xblockexpression;
  }
  
  public CharSequence generate() {
    StringConcatenation _builder = new StringConcatenation();
    String _GENPackageImport = this.GENPackageImport();
    _builder.append(_GENPackageImport, "");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    String _GENClassHead = this.GENClassHead();
    _builder.append(_GENClassHead, "");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("\t");
    String _GENGetMethod = this.GENGetMethod();
    _builder.append(_GENGetMethod, "\t");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    String _GENClassEnd = this.GENClassEnd();
    _builder.append(_GENClassEnd, "");
    _builder.newLineIfNotEmpty();
    return _builder;
  }
  
  private String GENPackageImport() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("package ");
    _builder.append(SystemConstant.ENTITY_EDIT_PRESENTER_PKG, "");
    _builder.append(";");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("import com.coral.foundation.core.impl.MochaEventBus;");
    _builder.newLine();
    _builder.append("import com.coral.vaadin.controller.Presenter;");
    _builder.newLine();
    _builder.append("import com.coral.vaadin.widget.view.CommonPresenter;");
    _builder.newLine();
    _builder.newLine();
    _builder.append("import ");
    _builder.append(SystemConstant.ENTITY_EDIT_VIEW_PKG, "");
    _builder.append(".");
    _builder.append(this.viewClassName, "");
    _builder.append(";");
    _builder.newLineIfNotEmpty();
    _builder.append("import ");
    _builder.append(this.entityPackage, "");
    _builder.append(".");
    _builder.append(this.entityName, "");
    _builder.append(";");
    _builder.newLineIfNotEmpty();
    return _builder.toString();
  }
  
  private String GENClassHead() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("public class ");
    _builder.append(this.viewPresenterName, "");
    _builder.append(" extends CommonPresenter implements Presenter {");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public ");
    _builder.append(this.viewPresenterName, "\t");
    _builder.append("(MochaEventBus eventBus) {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t");
    _builder.append("this.eventBus = eventBus;");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append(this.viewClassName, "\t\t");
    _builder.append(" newView = new ");
    _builder.append(this.viewClassName, "\t\t");
    _builder.append("();");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t");
    _builder.append("this.viewer = newView;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    return _builder.toString();
  }
  
  private String GENGetMethod() {
    StringConcatenation _builder = new StringConcatenation();
    return _builder.toString();
  }
  
  private String GENClassEnd() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("}");
    _builder.newLine();
    return _builder.toString();
  }
}
