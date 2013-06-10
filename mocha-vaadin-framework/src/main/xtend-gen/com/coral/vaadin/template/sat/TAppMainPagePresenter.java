package com.coral.vaadin.template.sat;

import com.coral.foundation.constant.SystemConstant;
import com.coral.foundation.md.model.App;
import com.coral.foundation.md.model.Mocha;
import com.coral.foundation.md.model.helper.VAppGenHelper;
import java.util.List;
import org.eclipse.xtend2.lib.StringConcatenation;

@SuppressWarnings("all")
public class TAppMainPagePresenter {
  private String appPresenterClass;
  
  private String appMainPageClass;
  
  private String controlMenuPanel;
  
  private String functionPanel;
  
  private App app;
  
  private List<Mocha> corals;
  
  public List<Mocha> init(final App app, final List<Mocha> corals) {
    List<Mocha> _xblockexpression = null;
    {
      this.app = app;
      String _name = app.getName();
      String _genAppMainPagePresenterClassName = VAppGenHelper.genAppMainPagePresenterClassName(_name);
      this.appPresenterClass = _genAppMainPagePresenterClassName;
      String _name_1 = app.getName();
      String _genAppMainPageClassName = VAppGenHelper.genAppMainPageClassName(_name_1);
      this.appMainPageClass = _genAppMainPageClassName;
      String _name_2 = app.getName();
      String _genControllerMenuPanelClassName = VAppGenHelper.genControllerMenuPanelClassName(_name_2);
      this.controlMenuPanel = _genControllerMenuPanelClassName;
      String _name_3 = app.getName();
      String _genFunctionPanelClassName = VAppGenHelper.genFunctionPanelClassName(_name_3);
      this.functionPanel = _genFunctionPanelClassName;
      List<Mocha> _corals = this.corals = corals;
      _xblockexpression = (_corals);
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
    CharSequence _GENFunctionMethod = this.GENFunctionMethod();
    _builder.append(_GENFunctionMethod, "	");
    _builder.newLineIfNotEmpty();
    CharSequence _GENClassEnd = this.GENClassEnd();
    _builder.append(_GENClassEnd, "");
    _builder.newLineIfNotEmpty();
    return _builder;
  }
  
  public CharSequence GENPackageImport() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("package ");
    _builder.append(SystemConstant.GENERATED_PRESENTER, "");
    _builder.append(";");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("import org.vaadin.peter.contextmenu.ContextMenu.ClickEvent;");
    _builder.newLine();
    _builder.append("import org.vaadin.peter.contextmenu.ContextMenu.ClickListener;");
    _builder.newLine();
    _builder.append("import org.vaadin.peter.contextmenu.ContextMenu.ContextMenuItem;");
    _builder.newLine();
    _builder.newLine();
    _builder.append("import com.coral.foundation.core.impl.MochaEventBus;");
    _builder.newLine();
    _builder.append("import com.coral.vaadin.controller.Presenter;");
    _builder.newLine();
    _builder.append("import com.coral.vaadin.view.template.sat.AppContentEvent;");
    _builder.newLine();
    _builder.append("import com.coral.vaadin.view.template.sat.FunctionMenu;");
    _builder.newLine();
    _builder.append("import com.coral.vaadin.widget.view.CommonPresenter;");
    _builder.newLine();
    _builder.append("import ");
    _builder.append(SystemConstant.GENERATED_PAGE, "");
    _builder.append(".");
    _builder.append(this.appMainPageClass, "");
    _builder.append(";");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence GENClassHead() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("public class ");
    _builder.append(this.appPresenterClass, "");
    _builder.append(" extends CommonPresenter implements Presenter, ClickListener {");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public ");
    _builder.append(this.appPresenterClass, "	");
    _builder.append("() {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t");
    _builder.append("this.viewer = new ");
    _builder.append(this.appMainPageClass, "		");
    _builder.append("();");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence GENFunctionMethod() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("@Override");
    _builder.newLine();
    _builder.append("public String getPresenterName() {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("return \"");
    String _name = this.app.getName();
    _builder.append(_name, "	");
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
    _builder.append(this.appMainPageClass, "	");
    _builder.append(" page = (");
    _builder.append(this.appMainPageClass, "	");
    _builder.append(") viewer;");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("page.getControllerMenu().setControllerMenuListener(page);");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("page.getFunctionPanel().getCreateContextMenu().addListener(this);");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("page.getFunctionPanel().getSettingContextMenu().addListener(this);");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("@Override");
    _builder.newLine();
    _builder.append("public void contextItemClick(ClickEvent event) {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("final ContextMenuItem clickedItem = event.getClickedItem();");
    _builder.newLine();
    _builder.append("\t");
    _builder.append(this.appMainPageClass, "	");
    _builder.append(" oaPage = (");
    _builder.append(this.appMainPageClass, "	");
    _builder.append(") viewer;");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("oaPage.getControllerMenu().cleanMenuStyle();");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("FunctionMenu functionMenu = oaPage.getFunctionPanel().getFunctionMenu(clickedItem);");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("AppContentEvent appContentEvent = new AppContentEvent();");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("appContentEvent.setViewName(functionMenu.getViewName());");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("appContentEvent.setCustomizeClass(functionMenu.getCustomizeClass());");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("eventBus.post(appContentEvent);");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("/**");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* @param eventBus the eventBus to set");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("*/");
    _builder.newLine();
    _builder.append("public void setEventBus(MochaEventBus eventBus) {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("this.eventBus = eventBus;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("((");
    _builder.append(this.appMainPageClass, "	");
    _builder.append(") viewer).setEventBus(eventBus);");
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
