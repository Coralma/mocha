package com.coral.vaadin.template.sat;

import com.coral.foundation.constant.SystemConstant;
import com.coral.foundation.md.model.App;
import com.coral.foundation.md.model.AppMenu;
import com.coral.foundation.md.model.AppNavigation;
import com.coral.foundation.md.model.Mocha;
import com.coral.foundation.md.model.ReportDef;
import com.coral.foundation.md.model.View;
import com.coral.foundation.md.model.helper.VAppGenHelper;
import com.coral.foundation.md.model.helper.VGenHelper;
import com.google.common.base.Objects;
import java.util.List;
import org.eclipse.xtend2.lib.StringConcatenation;

@SuppressWarnings("all")
public class TAppMainPage {
  private String appClassName;
  
  private String controlMenuPanel;
  
  private String functionPanel;
  
  private App app;
  
  private List<Mocha> mochas;
  
  private ReportDef reportDef;
  
  public List<Mocha> init(final App app, final ReportDef reportDef, final List<Mocha> mochas) {
    List<Mocha> _xblockexpression = null;
    {
      this.app = app;
      this.reportDef = reportDef;
      String _name = app.getName();
      String _genAppMainPageClassName = VAppGenHelper.genAppMainPageClassName(_name);
      this.appClassName = _genAppMainPageClassName;
      String _name_1 = app.getName();
      String _genControllerMenuPanelClassName = VAppGenHelper.genControllerMenuPanelClassName(_name_1);
      this.controlMenuPanel = _genControllerMenuPanelClassName;
      String _name_2 = app.getName();
      String _genFunctionPanelClassName = VAppGenHelper.genFunctionPanelClassName(_name_2);
      this.functionPanel = _genFunctionPanelClassName;
      List<Mocha> _mochas = this.mochas = mochas;
      _xblockexpression = (_mochas);
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
    CharSequence _GENAttachMethod = this.GENAttachMethod();
    _builder.append(_GENAttachMethod, "	");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    CharSequence _GENImplementMethod = this.GENImplementMethod();
    _builder.append(_GENImplementMethod, "	");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    CharSequence _GENGetMethod = this.GENGetMethod();
    _builder.append(_GENGetMethod, "	");
    _builder.newLineIfNotEmpty();
    String _classEnd = VGenHelper.getClassEnd();
    _builder.append(_classEnd, "");
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
    _builder.append("import com.coral.vaadin.controller.Presenter;");
    _builder.newLine();
    _builder.append("import com.coral.vaadin.view.template.sat.AppMainPage;");
    _builder.newLine();
    _builder.append("import com.coral.vaadin.view.template.sat.AppContentEvent;");
    _builder.newLine();
    _builder.append("import com.coral.foundation.report.AbstrctAppRawData;");
    _builder.newLine();
    _builder.append("import com.coral.foundation.core.impl.MochaEventBus;");
    _builder.newLine();
    _builder.append("import com.coral.vaadin.view.template.sat.ControllerMenuPanel.ControllerMenuListener;");
    _builder.newLine();
    _builder.append("import ");
    _builder.append(SystemConstant.ENTITY_EDIT_PRESENTER_PKG, "");
    _builder.append(".*;");
    _builder.newLineIfNotEmpty();
    _builder.append("import ");
    _builder.append(SystemConstant.GENERATED_PAGE, "");
    _builder.append(".");
    _builder.append(this.controlMenuPanel, "");
    _builder.append(";");
    _builder.newLineIfNotEmpty();
    _builder.append("import ");
    _builder.append(SystemConstant.GENERATED_PAGE, "");
    _builder.append(".");
    _builder.append(this.functionPanel, "");
    _builder.append(";");
    _builder.newLineIfNotEmpty();
    return _builder;
  }
  
  public CharSequence GENClassHead() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("public class ");
    _builder.append(this.appClassName, "");
    _builder.append(" extends AppMainPage implements ControllerMenuListener {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("private ");
    _builder.append(this.controlMenuPanel, "	");
    _builder.append(" controllerMenu = new ");
    _builder.append(this.controlMenuPanel, "	");
    _builder.append("();");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("private ");
    _builder.append(this.functionPanel, "	");
    _builder.append(" functionPanel = new ");
    _builder.append(this.functionPanel, "	");
    _builder.append("();");
    _builder.newLineIfNotEmpty();
    {
      boolean _notEquals = (!Objects.equal(this.reportDef, null));
      if (_notEquals) {
        _builder.append("\t");
        _builder.append("private static AbstrctAppRawData reportData = new ");
        String _name = this.reportDef.getName();
        _builder.append(_name, "	");
        _builder.append("();");
        _builder.newLineIfNotEmpty();
      }
    }
    return _builder;
  }
  
  public CharSequence GENAttachMethod() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("public void attach() {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("addComponent(controllerMenu);");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("addComponent(functionPanel);");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("// init the main page.");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("AppContentEvent event = new AppContentEvent();");
    _builder.newLine();
    _builder.append("\t");
    AppNavigation _appNavigation = this.app.getAppNavigation();
    List<AppMenu> _appMenus = _appNavigation.getAppMenus();
    final AppMenu home = _appMenus.get(0);
    _builder.newLineIfNotEmpty();
    {
      String _customizedClass = home.getCustomizedClass();
      boolean _notEquals = (!Objects.equal(_customizedClass, null));
      if (_notEquals) {
        _builder.append("\t");
        _builder.append("event.setCustomizeClass(\"");
        String _customizedClass_1 = home.getCustomizedClass();
        _builder.append(_customizedClass_1, "	");
        _builder.append("\");");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append("controllerMenu.setMenuStyle(null, \"");
        String _customizedClass_2 = home.getCustomizedClass();
        _builder.append(_customizedClass_2, "	");
        _builder.append("\");");
        _builder.newLineIfNotEmpty();
      } else {
        String _viewName = home.getViewName();
        boolean _notEquals_1 = (!Objects.equal(_viewName, null));
        if (_notEquals_1) {
          _builder.append("\t");
          _builder.append("event.setViewName(\"");
          String _viewName_1 = home.getViewName();
          _builder.append(_viewName_1, "	");
          _builder.append("\");");
          _builder.newLineIfNotEmpty();
          _builder.append("\t");
          _builder.append("controllerMenu.setMenuStyle(\"");
          String _viewName_2 = home.getViewName();
          _builder.append(_viewName_2, "	");
          _builder.append("\", null);");
          _builder.newLineIfNotEmpty();
        }
      }
    }
    {
      boolean _notEquals_2 = (!Objects.equal(this.reportDef, null));
      if (_notEquals_2) {
        _builder.append("\t");
        _builder.append("eventBus.put(\"appCustomReprotRowData\", reportData);");
        _builder.newLine();
      }
    }
    _builder.append("\t");
    _builder.append("eventBus.post(event);");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence GENImplementMethod() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("public void showView(String viewName) {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("Presenter presenter = null;");
    _builder.newLine();
    {
      for(final Mocha mocha : this.mochas) {
        {
          List<View> _viewList = mocha.getViewList();
          for(final View viewer : _viewList) {
            _builder.append("\t");
            final String viewPresenterName = VAppGenHelper.genAppMainPagePresenterClassName(viewer.name);
            _builder.newLineIfNotEmpty();
            _builder.append("\t");
            _builder.append("if(\"");
            String _name = viewer.getName();
            _builder.append(_name, "	");
            _builder.append("\".equals(viewName)) {");
            _builder.newLineIfNotEmpty();
            _builder.append("\t");
            _builder.append("\t");
            _builder.append("presenter = new ");
            _builder.append(viewPresenterName, "		");
            _builder.append("(eventBus);");
            _builder.newLineIfNotEmpty();
            _builder.append("\t");
            _builder.append("}");
            _builder.newLine();
          }
        }
      }
    }
    _builder.append("\t");
    _builder.append("if(presenter == null) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("throw new RuntimeException(this.getClass() + \"show view : \" + viewName + \" doesn\'t exist.\");");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("functionPanel.setContent(presenter.go());");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("presenter.bind();");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("public void showPanel(Class customizedPresenter) {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("try {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("Presenter presenter = (Presenter) customizedPresenter.getConstructor(MochaEventBus.class).newInstance(eventBus);");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("if(presenter.isFullSize()) {");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("functionPanel.setFullContent(presenter.go());");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("} else {");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("functionPanel.setContent(presenter.go());");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("presenter.bind();");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("} catch (Exception e) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("e.printStackTrace();");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("throw new RuntimeException(this.getClass() + \"Show Customized Class : \" + customizedPresenter + \" error.\");");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence GENGetMethod() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("/**");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* @return the controllerMenu");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("*/");
    _builder.newLine();
    _builder.append("public ");
    _builder.append(this.controlMenuPanel, "");
    _builder.append(" getControllerMenu() {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("return controllerMenu;");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("/**");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* @return the functionPanel");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("*/");
    _builder.newLine();
    _builder.append("public ");
    _builder.append(this.functionPanel, "");
    _builder.append(" getFunctionPanel() {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("return functionPanel;");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
}
