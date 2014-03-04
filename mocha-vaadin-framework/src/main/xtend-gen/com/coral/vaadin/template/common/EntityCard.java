package com.coral.vaadin.template.common;

import com.coral.foundation.constant.SystemConstant;
import com.coral.foundation.md.model.Mocha;
import com.coral.foundation.md.model.View;
import com.coral.foundation.md.model.ViewAction;
import com.coral.foundation.md.model.ViewField;
import com.coral.foundation.md.model.ViewSection;
import com.coral.foundation.md.model.helper.VAppGenHelper;
import com.google.common.base.Objects;
import java.util.List;
import org.eclipse.xtend2.lib.StringConcatenation;

@SuppressWarnings("all")
public class EntityCard {
  private String cardClassName;
  
  private View view;
  
  private List<Mocha> mochas;
  
  public String init(final View view, final List<Mocha> mochas) {
    String _xblockexpression = null;
    {
      this.view = view;
      this.mochas = mochas;
      String _generateCardClassName = VAppGenHelper.generateCardClassName(view.name);
      String _cardClassName = this.cardClassName = _generateCardClassName;
      _xblockexpression = (_cardClassName);
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
    _builder.append("import com.coral.vaadin.view.template.sat.panel.IActionPanel;");
    _builder.newLine();
    _builder.append("import com.coral.vaadin.view.template.sat.panel.ISectionPanel;");
    _builder.newLine();
    _builder.append("import com.coral.vaadin.view.template.sat.panel.impl.DefaultSectionPanel;");
    _builder.newLine();
    _builder.append("import com.coral.vaadin.view.template.sat.panel.impl.SearchEntityCard;");
    _builder.newLine();
    _builder.append("import com.vaadin.ui.Alignment;");
    _builder.newLine();
    _builder.append("import com.vaadin.ui.HorizontalLayout;");
    _builder.newLine();
    _builder.append("import com.vaadin.ui.Layout;");
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
    _builder.append(this.cardClassName, "");
    _builder.append(" extends SearchEntityCard {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence GENBuildMethod() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("public void attach() {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("HorizontalLayout hlayout = new HorizontalLayout();");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("hlayout.setWidth(cardWidth);");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("this.addComponent(hlayout);");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("Layout icon = getSearchIcon();");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("hlayout.addComponent(icon);");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("hlayout.setComponentAlignment(icon,Alignment.MIDDLE_CENTER);");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("ISectionPanel sectionPanel = new DefaultSectionPanel();");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("sectionPanel.setWidth(cardInfoWidth);");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("sectionPanel.setSpacing(false);");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("FieldStatus fieldStatus = null;");
    _builder.newLine();
    {
      List<ViewSection> _sections = this.view.getSections();
      for(final ViewSection section : _sections) {
        {
          String _template = section.getTemplate();
          boolean _equals = "SearchCard".equals(_template);
          if (_equals) {
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
      }
    }
    _builder.append("\t");
    _builder.append("hlayout.addComponent(sectionPanel);");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("hlayout.setComponentAlignment(sectionPanel,Alignment.TOP_LEFT);");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("IActionPanel actionPanel = createCardActionPanel();");
    _builder.newLine();
    {
      List<ViewSection> _sections_1 = this.view.getSections();
      for(final ViewSection section_1 : _sections_1) {
        {
          String _template_1 = section_1.getTemplate();
          boolean _equals_1 = "SearchCard".equals(_template_1);
          if (_equals_1) {
            {
              List<ViewAction> _viewActions = section_1.getViewActions();
              for(final ViewAction action : _viewActions) {
                _builder.append("\t");
                _builder.append("actionPanel.addButton(createActionButton(\"");
                _builder.append(action.name, "\t");
                _builder.append("\", \"");
                String _generateActionLabel = VAppGenHelper.generateActionLabel(action);
                _builder.append(_generateActionLabel, "\t");
                _builder.append("\", \"");
                _builder.append(action.action, "\t");
                _builder.append("\"));\t\t");
                _builder.newLineIfNotEmpty();
              }
            }
          }
        }
      }
    }
    _builder.append("\t");
    _builder.append("hlayout.addComponent(actionPanel);");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("hlayout.setComponentAlignment(actionPanel, Alignment.MIDDLE_LEFT);");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence GENGetMethod() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("@Override");
    _builder.newLine();
    _builder.append("public String getIconName() {");
    _builder.newLine();
    {
      List<ViewSection> _sections = this.view.getSections();
      for(final ViewSection section : _sections) {
        {
          String _template = section.getTemplate();
          boolean _equals = "SearchCard".equals(_template);
          if (_equals) {
            {
              String _icon = section.getIcon();
              boolean _notEquals = (!Objects.equal(_icon, null));
              if (_notEquals) {
                _builder.append("\t");
                _builder.append("return \"");
                String _icon_1 = section.getIcon();
                _builder.append(_icon_1, "\t");
                _builder.append("\";");
                _builder.newLineIfNotEmpty();
              } else {
                _builder.append("\t");
                _builder.append("return null;");
                _builder.newLine();
              }
            }
          }
        }
      }
    }
    _builder.append("\t");
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
