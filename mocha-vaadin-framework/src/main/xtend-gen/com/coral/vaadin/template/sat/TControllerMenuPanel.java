package com.coral.vaadin.template.sat;

import com.coral.foundation.constant.SystemConstant;
import com.coral.foundation.md.model.App;
import com.coral.foundation.md.model.AppMenu;
import com.coral.foundation.md.model.AppMenuGroup;
import com.coral.foundation.md.model.AppNavigation;
import com.coral.foundation.md.model.Mocha;
import com.coral.foundation.md.model.helper.VAppGenHelper;
import com.coral.foundation.md.model.helper.VGenHelper;
import com.google.common.base.Objects;
import java.util.List;
import org.eclipse.xtend2.lib.StringConcatenation;

@SuppressWarnings("all")
public class TControllerMenuPanel {
  private App app;
  
  private AppNavigation appNavigation;
  
  private String appName;
  
  private String appLabel;
  
  private List<Mocha> mochas;
  
  private String controlMenuPanel;
  
  public List<Mocha> init(final App app, final List<Mocha> mochas) {
    List<Mocha> _xblockexpression = null;
    {
      this.app = app;
      AppNavigation _appNavigation = app.getAppNavigation();
      this.appNavigation = _appNavigation;
      String _name = app.getName();
      this.appName = _name;
      String _label = app.getLabel();
      this.appLabel = _label;
      String _name_1 = app.getName();
      String _genControllerMenuPanelClassName = VAppGenHelper.genControllerMenuPanelClassName(_name_1);
      this.controlMenuPanel = _genControllerMenuPanelClassName;
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
    CharSequence _GENBuildMethod = this.GENBuildMethod();
    _builder.append(_GENBuildMethod, "	");
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
    _builder.append("import com.coral.vaadin.view.template.sat.ControllerMenuPanel;");
    _builder.newLine();
    _builder.append("import com.vaadin.ui.Button;");
    _builder.newLine();
    _builder.append("import com.vaadin.ui.Label;");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence GENClassHead() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("public class ");
    _builder.append(this.controlMenuPanel, "");
    _builder.append(" extends ControllerMenuPanel {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence GENBuildMethod() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("public void build() {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("try {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("// init app name title");
    _builder.newLine();
    _builder.append("\t\t");
    final String appTitle = (this.appName + "title");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t");
    _builder.append("Label ");
    _builder.append(appTitle, "		");
    _builder.append(" = createAppTitle(\"");
    _builder.append(this.appLabel, "		");
    _builder.append("\");");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t");
    _builder.append("addComponent(");
    _builder.append(appTitle, "		");
    _builder.append(");");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("MenuAction action = null;");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("Label groupTitle = null;");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("Button menuItem = null;");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("// create the main menu item.");
    _builder.newLine();
    {
      List<AppMenu> _appMenus = this.appNavigation.getAppMenus();
      for(final AppMenu mainAppMenu : _appMenus) {
        _builder.append("\t\t");
        CharSequence _GENMenuItem = this.GENMenuItem(mainAppMenu);
        _builder.append(_GENMenuItem, "		");
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.append("\t\t");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("// create the menu group and sub menu item.");
    _builder.newLine();
    {
      List<AppMenuGroup> _appMenuGroups = this.appNavigation.getAppMenuGroups();
      for(final AppMenuGroup group : _appMenuGroups) {
        _builder.append("\t\t");
        _builder.append("groupTitle = createMenuTitle(\"");
        String _label = group.getLabel();
        _builder.append(_label, "		");
        _builder.append("\");");
        _builder.newLineIfNotEmpty();
        _builder.append("\t\t");
        _builder.append("addComponent(groupTitle);");
        _builder.newLine();
        {
          List<AppMenu> _appMenus_1 = group.getAppMenus();
          for(final AppMenu mainAppMenu_1 : _appMenus_1) {
            _builder.append("\t\t");
            CharSequence _GENMenuItem_1 = this.GENMenuItem(mainAppMenu_1);
            _builder.append(_GENMenuItem_1, "		");
            _builder.newLineIfNotEmpty();
          }
        }
      }
    }
    _builder.append("\t");
    _builder.append("} catch (Exception e) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("// TODO Auto-generated catch block");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("e.printStackTrace();");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence GENMenuItem(final AppMenu menuItem) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("action = new MenuAction();");
    _builder.newLine();
    {
      String _viewName = menuItem.getViewName();
      boolean _notEquals = (!Objects.equal(_viewName, null));
      if (_notEquals) {
        _builder.append("action.setView(\"");
        String _viewName_1 = menuItem.getViewName();
        _builder.append(_viewName_1, "");
        _builder.append("\");");
        _builder.newLineIfNotEmpty();
      } else {
        String _customizedClass = menuItem.getCustomizedClass();
        boolean _notEquals_1 = (!Objects.equal(_customizedClass, null));
        if (_notEquals_1) {
          _builder.append("action.setPanel(Class.forName(\"");
          String _customizedClass_1 = menuItem.getCustomizedClass();
          _builder.append(_customizedClass_1, "");
          _builder.append("\"));");
          _builder.newLineIfNotEmpty();
        }
      }
    }
    _builder.append("menuItem = createMenu(\"");
    String _generateMenuLabel = VAppGenHelper.generateMenuLabel(menuItem);
    _builder.append(_generateMenuLabel, "");
    _builder.append("\",action);");
    _builder.newLineIfNotEmpty();
    _builder.append("addComponent(menuItem);");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence GENGetMethod() {
    StringConcatenation _builder = new StringConcatenation();
    return _builder;
  }
}
