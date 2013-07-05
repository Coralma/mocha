package com.coral.vaadin.template.sat;

import com.coral.foundation.constant.SystemConstant;
import com.coral.foundation.md.model.App;
import com.coral.foundation.md.model.AppCreation;
import com.coral.foundation.md.model.AppMenu;
import com.coral.foundation.md.model.AppSetting;
import com.coral.foundation.md.model.Mocha;
import com.coral.foundation.md.model.helper.VAppGenHelper;
import com.coral.foundation.md.model.helper.VGenHelper;
import com.google.common.base.Objects;
import java.util.List;
import org.eclipse.xtend2.lib.StringConcatenation;

@SuppressWarnings("all")
public class TFunctionPanel {
  private App app;
  
  private List<Mocha> mochas;
  
  private String functionPanel;
  
  public List<Mocha> init(final App app, final List<Mocha> mochas) {
    List<Mocha> _xblockexpression = null;
    {
      this.app = app;
      String _name = app.getName();
      String _genFunctionPanelClassName = VAppGenHelper.genFunctionPanelClassName(_name);
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
    _builder.append("import java.util.List;");
    _builder.newLine();
    _builder.newLine();
    _builder.append("import com.coral.vaadin.view.template.sat.FunctionMenu;");
    _builder.newLine();
    _builder.append("import com.coral.vaadin.view.template.sat.FunctionPanel;");
    _builder.newLine();
    _builder.append("import com.google.common.collect.Lists;");
    _builder.newLine();
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence GENClassHead() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("public class ");
    _builder.append(this.functionPanel, "");
    _builder.append(" extends FunctionPanel {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence GENAttachMethod() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("@Override");
    _builder.newLine();
    _builder.append("public List<FunctionMenu> getCreationFunctionMenu() {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("List<FunctionMenu> creationMenus = Lists.newArrayList();");
    _builder.newLine();
    {
      AppCreation _appCreation = this.app.getAppCreation();
      boolean _notEquals = (!Objects.equal(_appCreation, null));
      if (_notEquals) {
        {
          AppCreation _appCreation_1 = this.app.getAppCreation();
          List<AppMenu> _appMenus = _appCreation_1.getAppMenus();
          for(final AppMenu creationMenu : _appMenus) {
            _builder.append("\t");
            _builder.append("creationMenus.add(");
            String _generateFunctionMenu = VAppGenHelper.generateFunctionMenu(creationMenu);
            _builder.append(_generateFunctionMenu, "	");
            _builder.append(");");
            _builder.newLineIfNotEmpty();
          }
        }
      }
    }
    _builder.append("\t");
    _builder.append("return creationMenus;");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("@Override");
    _builder.newLine();
    _builder.append("public List<FunctionMenu> getSettingFunctionMenu() {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("List<FunctionMenu> settingMenus = Lists.newArrayList();");
    _builder.newLine();
    {
      AppSetting _appSetting = this.app.getAppSetting();
      boolean _notEquals_1 = (!Objects.equal(_appSetting, null));
      if (_notEquals_1) {
        {
          AppSetting _appSetting_1 = this.app.getAppSetting();
          List<AppMenu> _appMenus_1 = _appSetting_1.getAppMenus();
          for(final AppMenu settingMenu : _appMenus_1) {
            _builder.append("\t");
            _builder.append("settingMenus.add(");
            String _generateFunctionMenu_1 = VAppGenHelper.generateFunctionMenu(settingMenu);
            _builder.append(_generateFunctionMenu_1, "	");
            _builder.append(");");
            _builder.newLineIfNotEmpty();
          }
        }
      }
    }
    _builder.append("\t");
    _builder.append("settingMenus.add(FunctionMenu.create().setName(\"exit\").setLabel(\"Back to Homepage\"));");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("return settingMenus;");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence GENGetMethod() {
    StringConcatenation _builder = new StringConcatenation();
    return _builder;
  }
}
