package com.coral.vaadin.template.common;

import com.coral.foundation.constant.SystemConstant;
import com.coral.foundation.md.model.Entity;
import com.coral.foundation.md.model.Mocha;
import com.coral.foundation.md.model.View;
import com.coral.foundation.md.model.ViewAction;
import com.coral.foundation.md.model.helper.VAppGenHelper;
import com.coral.foundation.md.model.helper.VGenHelper;
import java.util.List;
import org.eclipse.xtend2.lib.StringConcatenation;

@SuppressWarnings("all")
public class EntityEditPresenter {
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
    _builder.append("import com.coral.foundation.core.impl.MochaEventBus;");
    _builder.newLine();
    _builder.append("import com.coral.foundation.spring.bean.SpringContextUtils;");
    _builder.newLine();
    _builder.append("import com.coral.vaadin.controller.Presenter;");
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
    _builder.append(this.viewClassName, "		");
    _builder.append(" newView = new ");
    _builder.append(this.viewClassName, "		");
    _builder.append("();");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t");
    _builder.append("Object entity = this.eventBus.getContext().get(\"Entity\");");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("if(entity != null) {");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("newView.setEntity(entity);");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("this.viewer = newView;");
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
    _builder.append("//TODO add and edit your action.");
    _builder.newLine();
    {
      List<ViewAction> _viewActions = this.view.getViewActions();
      for(final ViewAction viewAction : _viewActions) {
        _builder.append("\t");
        String _name = viewAction.getName();
        final String buttonName = (_name + "Button");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append("Button ");
        _builder.append(buttonName, "	");
        _builder.append(" = viewer.getButton(\"");
        String _name_1 = viewAction.getName();
        _builder.append(_name_1, "	");
        _builder.append("\");");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append(buttonName, "	");
        _builder.append(".addListener(new ClickListener() {");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append("\t");
        _builder.append("@Override");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("\t");
        _builder.append("public void buttonClick(ClickEvent event) {");
        _builder.newLine();
        {
          String _action = viewAction.getAction();
          boolean _equals = "Save".equals(_action);
          if (_equals) {
            _builder.append("\t");
            _builder.append("\t\t");
            _builder.append("save();");
            _builder.newLine();
          } else {
            String _action_1 = viewAction.getAction();
            boolean _equals_1 = "Back".equals(_action_1);
            if (_equals_1) {
              _builder.append("\t");
              _builder.append("\t\t");
              _builder.append("back();");
              _builder.newLine();
            } else {
              _builder.append("\t");
              _builder.append("\t\t");
              _builder.append("//TODO add action content.");
              _builder.newLine();
            }
          }
        }
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
    _builder.append("/**");
    _builder.newLine();
    _builder.append("  ");
    _builder.append("* Save value of ");
    _builder.append(this.entityName, "  ");
    _builder.append("CreateView.");
    _builder.newLineIfNotEmpty();
    _builder.append("  ");
    _builder.append("*/");
    _builder.newLine();
    _builder.append("public void save() {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append(this.entityName, "	");
    _builder.append(" value = (");
    _builder.append(this.entityName, "	");
    _builder.append(")viewer.getValue();");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("if(value != null) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("dao.persist(value);");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("back();");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("public void back() {");
    _builder.newLine();
    _builder.append("\t");
    final String searchViewName = VAppGenHelper.getSearchViewName(this.view, this.mochas);
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("postViewer(\"");
    _builder.append(searchViewName, "	");
    _builder.append("\");");
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
