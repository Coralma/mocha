package com.coral.vaadin.template.common;

import com.coral.foundation.constant.SystemConstant;
import com.coral.foundation.md.model.Mocha;
import com.coral.foundation.md.model.View;
import com.coral.foundation.md.model.ViewAction;
import com.coral.foundation.md.model.ViewField;
import com.coral.foundation.md.model.ViewSection;
import com.coral.foundation.md.model.helper.VAppGenHelper;
import java.util.List;
import org.eclipse.xtend2.lib.StringConcatenation;

@SuppressWarnings("all")
public class EntityEditView {
  private String viewClassName;
  
  private View view;
  
  private List<Mocha> mochas;
  
  public String init(final View view, final List<Mocha> mochas) {
    String _xblockexpression = null;
    {
      this.view = view;
      this.mochas = mochas;
      String _generateClassName = VAppGenHelper.generateClassName(view.name);
      String _viewClassName = this.viewClassName = _generateClassName;
      _xblockexpression = (_viewClassName);
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
    CharSequence _GENBuildMethod = this.GENBuildMethod();
    _builder.append(_GENBuildMethod, "\t");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("\t");
    CharSequence _GENGetMethod = this.GENGetMethod();
    _builder.append(_GENGetMethod, "\t");
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
    _builder.append(SystemConstant.ENTITY_EDIT_VIEW_PKG, "");
    _builder.append(";");
    _builder.newLineIfNotEmpty();
    _builder.append("import com.coral.vaadin.view.template.sat.panel.ISectionPanel;");
    _builder.newLine();
    _builder.append("import com.coral.vaadin.view.template.sat.panel.IViewPanel;");
    _builder.newLine();
    _builder.append("import com.coral.vaadin.view.template.sat.panel.IActionPanel;");
    _builder.newLine();
    _builder.append("import com.coral.vaadin.view.template.sat.panel.impl.EntityViewPanel;");
    _builder.newLine();
    _builder.append("import com.coral.vaadin.widget.Viewer;");
    _builder.newLine();
    _builder.append("import com.coral.vaadin.widget.fields.FieldStatus;");
    _builder.newLine();
    _builder.append("import ");
    _builder.append(this.view.entity.entityClass, "");
    _builder.append(";");
    _builder.newLineIfNotEmpty();
    return _builder;
  }
  
  public CharSequence GENClassHead() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("public class ");
    _builder.append(this.viewClassName, "");
    _builder.append(" extends EntityViewPanel implements Viewer {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public ");
    _builder.append(this.viewClassName, "\t");
    _builder.append("() {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t");
    _builder.append("super();");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence GENBuildMethod() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("public void build() {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("final IViewPanel viewPanel = createViewPanel();");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("ISectionPanel sectionPanel;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("FieldStatus fieldStatus;");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    {
      List<ViewSection> _sections = this.view.getSections();
      for(final ViewSection section : _sections) {
        _builder.append("\t");
        _builder.append("sectionPanel = createSectionPanel(\"");
        String _name = section.getName();
        _builder.append(_name, "\t");
        _builder.append("\");");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append("sectionPanel.setLabel(\"");
        _builder.append(section.label, "\t");
        _builder.append("\");");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append("viewPanel.addSection(sectionPanel);");
        _builder.newLine();
        _builder.append("\t");
        _builder.newLine();
        {
          List<ViewField> _viewFields = section.getViewFields();
          for(final ViewField field : _viewFields) {
            _builder.append("\t");
            _builder.append("fieldStatus = ");
            String _generateFieldStatus = VAppGenHelper.generateFieldStatus(field, this.mochas);
            _builder.append(_generateFieldStatus, "\t");
            _builder.newLineIfNotEmpty();
            _builder.append("\t");
            _builder.append("sectionPanel.addField(createFieldWidget(fieldStatus));");
            _builder.newLine();
            _builder.append("\t");
            _builder.newLine();
          }
        }
      }
    }
    _builder.append("\t");
    _builder.append("IActionPanel actionPanel = createActionPanel();");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("viewPanel.setActionPanel(actionPanel);");
    _builder.newLine();
    {
      List<ViewAction> _viewActions = this.view.getViewActions();
      for(final ViewAction action : _viewActions) {
        _builder.append("\t");
        _builder.append("actionPanel.addButton(createActionButton(\"");
        _builder.append(action.name, "\t");
        _builder.append("\", \"");
        String _generateActionLabel = VAppGenHelper.generateActionLabel(action);
        _builder.append(_generateActionLabel, "\t");
        _builder.append("\", \"");
        _builder.append(action.action, "\t");
        _builder.append("\"));");
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("addComponent(viewPanel);");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence GENGetMethod() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("public Class getEntityClass() {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("return ");
    _builder.append(this.view.entity.entityName, "\t");
    _builder.append(".class;");
    _builder.newLineIfNotEmpty();
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("public String getViewerTitle() {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("return \"");
    String _generateCreateEntityViewTitle = VAppGenHelper.generateCreateEntityViewTitle(this.view);
    _builder.append(_generateCreateEntityViewTitle, "\t");
    _builder.append("\";");
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
