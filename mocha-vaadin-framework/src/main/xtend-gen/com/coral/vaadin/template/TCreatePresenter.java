package com.coral.vaadin.template;

import com.coral.foundation.constant.SystemConstant;
import com.coral.foundation.md.model.Entity;
import com.coral.foundation.md.model.Mocha;
import com.coral.foundation.md.model.helper.VGenHelper;
import com.coral.foundation.utils.StrUtils;
import java.util.List;
import org.eclipse.xtend2.lib.StringConcatenation;

@SuppressWarnings("all")
public class TCreatePresenter {
  private Entity entity;
  
  private List<Mocha> corals;
  
  private String createPresenterClassName;
  
  private String entityName;
  
  private String entityVariable;
  
  private String entityDaoIntf;
  
  private String daoIntfPackage;
  
  private String searchViewClassName;
  
  public String init(final Entity entity, final List<Mocha> corals) {
    String _xblockexpression = null;
    {
      this.entity = entity;
      this.corals = corals;
      this.entityName = entity.entityName;
      String _lowCaseFirstLetter = StrUtils.lowCaseFirstLetter(this.entityName);
      this.entityVariable = _lowCaseFirstLetter;
      String _genCreatePresenterClassName = VGenHelper.genCreatePresenterClassName(this.entityName);
      this.createPresenterClassName = _genCreatePresenterClassName;
      String _genDaoIntf = VGenHelper.genDaoIntf(this.entityName);
      this.entityDaoIntf = _genDaoIntf;
      final Mocha currentCoral = VGenHelper.getCurrentCoral(entity, corals);
      String _daoIntfPackage = currentCoral.getDaoIntfPackage();
      this.daoIntfPackage = _daoIntfPackage;
      String _genSearchViewClassName = VGenHelper.genSearchViewClassName(this.entityName);
      String _searchViewClassName = this.searchViewClassName = _genSearchViewClassName;
      _xblockexpression = (_searchViewClassName);
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
    CharSequence _GENBuildVeriable = this.GENBuildVeriable();
    _builder.append(_GENBuildVeriable, "\t");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    CharSequence _GENConstructor = this.GENConstructor();
    _builder.append(_GENConstructor, "\t");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    CharSequence _GENBuildMethod = this.GENBuildMethod();
    _builder.append(_GENBuildMethod, "\t");
    _builder.newLineIfNotEmpty();
    CharSequence _GENClassEnd = this.GENClassEnd();
    _builder.append(_GENClassEnd, "");
    _builder.newLineIfNotEmpty();
    return _builder;
  }
  
  public CharSequence GENPackageImport() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("package ");
    _builder.append(SystemConstant.TCREATE_PRESENTER_PKG, "");
    _builder.append(";");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("import ");
    String _currentEntityPackage = VGenHelper.getCurrentEntityPackage(this.entity, this.corals);
    String _plus = (_currentEntityPackage + ".*");
    _builder.append(_plus, "");
    _builder.append(";");
    _builder.newLineIfNotEmpty();
    _builder.append("import ");
    _builder.append((this.daoIntfPackage + ".*"), "");
    _builder.append(";");
    _builder.newLineIfNotEmpty();
    _builder.append("import com.coral.vaadin.controller.Presenter;");
    _builder.newLine();
    _builder.append("import com.coral.vaadin.controller.PageChangeEvent;");
    _builder.newLine();
    _builder.append("import com.vaadin.ui.Component;");
    _builder.newLine();
    _builder.append("import com.coral.foundation.core.impl.MochaEventBus;");
    _builder.newLine();
    _builder.append("import com.coral.vaadin.widget.Viewer;");
    _builder.newLine();
    _builder.append("import com.coral.vaadin.widget.view.*;");
    _builder.newLine();
    _builder.append("import com.coral.vaadin.widget.view.builder.ViewContext;");
    _builder.newLine();
    _builder.append("import com.coral.foundation.spring.bean.SpringContextUtils;");
    _builder.newLine();
    _builder.append("import com.vaadin.ui.Button.ClickEvent;");
    _builder.newLine();
    _builder.append("import com.vaadin.ui.Button.ClickListener;");
    _builder.newLine();
    _builder.append("import com.coral.vaadin.widget.field.ActionButton;");
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
    _builder.append(this.createPresenterClassName, "  ");
    _builder.append(" is a auto Generated class. Please don\'t modify it.");
    _builder.newLineIfNotEmpty();
    _builder.append("  ");
    _builder.append("* @author Coral");
    _builder.newLine();
    _builder.append("  ");
    _builder.append("*/");
    _builder.newLine();
    _builder.append("public class ");
    _builder.append(this.createPresenterClassName, "");
    _builder.append(" extends CommonPresenter implements Presenter {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence GENBuildVeriable() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("private MochaEventBus eventBus;");
    _builder.newLine();
    _builder.append("private ViewContext viewContext;");
    _builder.newLine();
    _builder.append("private ");
    _builder.append(this.entityDaoIntf, "");
    _builder.append(" dao = SpringContextUtils.getBean(");
    _builder.append(this.entityDaoIntf, "");
    _builder.append(".class);");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence GENConstructor() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("public ");
    _builder.append(this.createPresenterClassName, "");
    _builder.append("(ViewContext viewContext) {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("this(viewContext, null);");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _builder.append("public ");
    _builder.append(this.createPresenterClassName, "");
    _builder.append("(ViewContext viewContext, MochaEventBus eventBus) {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("this.eventBus = eventBus;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("this.viewContext = viewContext;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("this.viewer = new EntityViewer(viewContext);");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence GENBuildMethod() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("@Override");
    _builder.newLine();
    _builder.append("public void setEditValue(Long id) {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("if(id != null) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append(this.entityName, "\t\t");
    _builder.append(" value = dao.findById(id);");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t");
    _builder.append("if(value != null) {");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("viewer.setValue((");
    _builder.append(this.entityName, "\t\t\t");
    _builder.append(")value);");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.newLine();
    _builder.append("public String getPresenterName() {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("return \"");
    _builder.append(this.entityName, "\t");
    _builder.append("\";");
    _builder.newLineIfNotEmpty();
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("@Override");
    _builder.newLine();
    _builder.append("public ViewContext getViewContext() {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("return viewContext;");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("public void bind() {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("//TODO add and edit your action.");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("ActionButton saveBtn = viewer.getButton(\"Save\");");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("if(saveBtn != null) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("saveBtn.addListener(new ClickListener() {");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("@Override");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("public void buttonClick(ClickEvent event) {");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("save();");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("});");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
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
    _builder.append(this.entityName, "\t");
    _builder.append(" value = (");
    _builder.append(this.entityName, "\t");
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
    _builder.append("/**");
    _builder.newLine();
    _builder.append("  ");
    _builder.append("* Back to ");
    _builder.append(this.searchViewClassName, "  ");
    _builder.append(".");
    _builder.newLineIfNotEmpty();
    _builder.append("  ");
    _builder.append("*/");
    _builder.newLine();
    _builder.append("public void back() {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("if(eventBus != null) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("eventBus.post(new PageChangeEvent(\"CargoPolicySearchView\"));");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
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
