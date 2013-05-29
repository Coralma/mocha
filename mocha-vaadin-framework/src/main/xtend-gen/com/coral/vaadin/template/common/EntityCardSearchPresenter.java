package com.coral.vaadin.template.common;

import com.coral.foundation.constant.SystemConstant;
import com.coral.foundation.md.model.Entity;
import com.coral.foundation.md.model.Mocha;
import com.coral.foundation.md.model.View;
import com.coral.foundation.md.model.helper.VAppGenHelper;
import com.coral.foundation.md.model.helper.VGenHelper;
import com.google.common.base.Objects;
import java.util.List;
import org.eclipse.xtend2.lib.StringConcatenation;

@SuppressWarnings("all")
public class EntityCardSearchPresenter {
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
    CharSequence _GENPackageImport = this.GENPackageImport();
    _builder.append(_GENPackageImport, "");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    CharSequence _GENClassHead = this.GENClassHead();
    _builder.append(_GENClassHead, "");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("\t");
    CharSequence _GENGetMethod = this.GENGetMethod();
    _builder.append(_GENGetMethod, "	");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    CharSequence _GENClassEnd = this.GENClassEnd();
    _builder.append(_GENClassEnd, "");
    _builder.newLineIfNotEmpty();
    return _builder;
  }
  
  public CharSequence GENPackageImport() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("package ");
    _builder.append(SystemConstant.ENTITY_EDIT_PRESENTER_PKG, "");
    _builder.append(";");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("import ");
    String _plus = (this.daoIntfPackage + ".*");
    _builder.append(_plus, "");
    _builder.append(";");
    _builder.newLineIfNotEmpty();
    _builder.append("import java.util.List;");
    _builder.newLine();
    _builder.append("import com.coral.foundation.core.impl.MochaEventBus;");
    _builder.newLine();
    _builder.append("import com.coral.foundation.model.BaseEntity;");
    _builder.newLine();
    _builder.append("import com.coral.foundation.spring.bean.SpringContextUtils;");
    _builder.newLine();
    _builder.append("import com.coral.vaadin.controller.Presenter;");
    _builder.newLine();
    _builder.append("import com.coral.vaadin.view.template.sat.panel.impl.SearchPanel.SearchListener;");
    _builder.newLine();
    _builder.append("import com.coral.vaadin.widget.view.AppCommonPresenter;");
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
    _builder.newLine();
    _builder.append("import com.vaadin.ui.Button;");
    _builder.newLine();
    _builder.append("import com.vaadin.ui.Button.ClickEvent;");
    _builder.newLine();
    _builder.append("import com.vaadin.ui.Button.ClickListener;");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence GENClassHead() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("public class ");
    _builder.append(this.viewPresenterName, "");
    _builder.append(" extends AppCommonPresenter implements Presenter {");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("private ");
    _builder.append(this.entityDaoIntf, "	");
    _builder.append(" dao = SpringContextUtils.getBean(");
    _builder.append(this.entityDaoIntf, "	");
    _builder.append(".class);");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public ");
    _builder.append(this.viewPresenterName, "	");
    _builder.append("(MochaEventBus eventBus) {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t");
    _builder.append("this.eventBus = eventBus;");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("this.viewer = new ");
    _builder.append(this.viewClassName, "		");
    _builder.append("();");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t");
    _builder.append("// load all data.");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("List entities = dao.findAll();");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("viewer.setValue(entities);");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence GENGetMethod() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("@Override");
    _builder.newLine();
    _builder.append("public String getPresenterName() {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("return \"");
    _builder.append(this.viewClassName, "	");
    _builder.append("\";");
    _builder.newLineIfNotEmpty();
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("@Override");
    _builder.newLine();
    _builder.append("public void bind() {");
    _builder.newLine();
    _builder.append("\t");
    final String viewVariable = VAppGenHelper.asVariable(this.viewClassName);
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append(this.viewClassName, "	");
    _builder.append(" ");
    _builder.append(viewVariable, "	");
    _builder.append(" = (");
    _builder.append(this.viewClassName, "	");
    _builder.append(") viewer;");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    final String editViewName = VAppGenHelper.getEditViewName(this.view, this.mochas);
    _builder.newLineIfNotEmpty();
    {
      boolean _notEquals = (!Objects.equal(editViewName, null));
      if (_notEquals) {
        _builder.append("\t");
        _builder.append(viewVariable, "	");
        _builder.append(".getConditionPanel().getCreateBtn().addListener(new ClickListener() {");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append("\t");
        _builder.append("@Override");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("\t");
        _builder.append("public void buttonClick(ClickEvent event) {");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("\t\t");
        _builder.append("postViewer(\"");
        _builder.append(editViewName, "			");
        _builder.append("\");");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append("\t");
        _builder.append("}");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("});");
        _builder.newLine();
        _builder.append("\t");
        _builder.append(viewVariable, "	");
        _builder.append(".setListener(new SearchListener() {");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append("\t");
        _builder.append("@Override");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("\t");
        _builder.append("public void handleAction(String name, String action, Object entity) {");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("\t\t");
        _builder.append("if(\"Edit\".equals(action)) {");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("\t\t\t");
        _builder.append("postViewer(\"");
        _builder.append(editViewName, "				");
        _builder.append("\",entity);");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append("\t\t");
        _builder.append("} else if(\"Delete\".equals(action)) {");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("\t\t\t");
        _builder.append("remove(entity);");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("\t\t\t");
        _builder.append("postViewer(\"");
        _builder.append(this.viewClassName, "				");
        _builder.append("\");");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append("\t\t");
        _builder.append("}");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("\t");
        _builder.append("}");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("});");
        _builder.newLine();
      }
    }
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("public void remove(Object entity) {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("if(entity != null) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("dao.remove(((BaseEntity)entity).getID());");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
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
