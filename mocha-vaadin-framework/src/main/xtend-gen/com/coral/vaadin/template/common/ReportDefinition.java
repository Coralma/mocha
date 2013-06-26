package com.coral.vaadin.template.common;

import com.coral.foundation.constant.SystemConstant;
import com.coral.foundation.md.model.Mocha;
import com.coral.foundation.md.model.ReportColumnDef;
import com.coral.foundation.md.model.ReportDef;
import com.coral.foundation.md.model.ReportJoinDef;
import com.coral.foundation.md.model.ReportTableDef;
import java.util.List;
import org.eclipse.xtend2.lib.StringConcatenation;

@SuppressWarnings("all")
public class ReportDefinition {
  private List<Mocha> mochas;
  
  private ReportDef reportDef;
  
  private String reportName;
  
  public String init(final List<Mocha> mochas, final ReportDef reportDef) {
    String _xblockexpression = null;
    {
      this.mochas = mochas;
      this.reportDef = reportDef;
      String _name = reportDef.getName();
      String _reportName = this.reportName = _name;
      _xblockexpression = (_reportName);
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
    _builder.append("\t");
    CharSequence _GENConstructor = this.GENConstructor();
    _builder.append(_GENConstructor, "	");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("\t");
    CharSequence _GENBuildMethod = this.GENBuildMethod();
    _builder.append(_GENBuildMethod, "	");
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
    _builder.append(SystemConstant.GENERATED_PAGE, "");
    _builder.append(";");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("import java.util.List;");
    _builder.newLine();
    _builder.append("import java.util.ArrayList;");
    _builder.newLine();
    _builder.append("import com.coral.foundation.security.model.*;");
    _builder.newLine();
    _builder.append("import com.coral.foundation.report.AbstrctAppRawData;");
    _builder.newLine();
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence GENClassHead() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("public class ");
    _builder.append(this.reportName, "");
    _builder.append(" extends AbstrctAppRawData {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("private static String appName=\"");
    _builder.append(this.reportName, "	");
    _builder.append("\";");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence GENConstructor() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("public ");
    _builder.append(this.reportName, "");
    _builder.append("() {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("super(appName);");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence GENBuildMethod() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("public List<ReportTable> getReportTables() {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("List<ReportTable> reportTables = new ArrayList<ReportTable>();");
    _builder.newLine();
    {
      List<ReportTableDef> _reportTables = this.reportDef.getReportTables();
      for(final ReportTableDef reportTableDef : _reportTables) {
        _builder.append("\t");
        String _name = reportTableDef.getName();
        final String reportVariable = (_name + "Report");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append("ReportTable ");
        _builder.append(reportVariable, "	");
        _builder.append(" = new ReportTable();");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append("reportTables.add(");
        _builder.append(reportVariable, "	");
        _builder.append(");");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append(reportVariable, "	");
        _builder.append(".setTableName(\"");
        String _tableName = reportTableDef.getTableName();
        _builder.append(_tableName, "	");
        _builder.append("\");");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append(reportVariable, "	");
        _builder.append(".setTableLabel(\"");
        String _label = reportTableDef.getLabel();
        _builder.append(_label, "	");
        _builder.append("\");");
        _builder.newLineIfNotEmpty();
        {
          List<ReportColumnDef> _columns = reportTableDef.getColumns();
          for(final ReportColumnDef columnDef : _columns) {
            _builder.append("\t");
            String _name_1 = columnDef.getName();
            final String columnVariable = (reportVariable + _name_1);
            _builder.newLineIfNotEmpty();
            _builder.append("\t");
            _builder.append("ReportColumn ");
            _builder.append(columnVariable, "	");
            _builder.append(" = new ReportColumn();");
            _builder.newLineIfNotEmpty();
            _builder.append("\t");
            _builder.append(columnVariable, "	");
            _builder.append(".setColumnName(\"");
            String _columnName = columnDef.getColumnName();
            _builder.append(_columnName, "	");
            _builder.append("\");");
            _builder.newLineIfNotEmpty();
            _builder.append("\t");
            _builder.append(columnVariable, "	");
            _builder.append(".setColumnLabel(\"");
            String _label_1 = columnDef.getLabel();
            _builder.append(_label_1, "	");
            _builder.append("\");");
            _builder.newLineIfNotEmpty();
            _builder.append("\t");
            _builder.append(reportVariable, "	");
            _builder.append(".getReportColumns().add(");
            _builder.append(columnVariable, "	");
            _builder.append(");");
            _builder.newLineIfNotEmpty();
          }
        }
        _builder.append("\t");
        _builder.newLine();
      }
    }
    _builder.append("\t");
    _builder.newLine();
    {
      List<ReportTableDef> _reportTables_1 = this.reportDef.getReportTables();
      for(final ReportTableDef reportTableDef_1 : _reportTables_1) {
        _builder.append("\t");
        String _name_2 = reportTableDef_1.getName();
        final String reportVariable_1 = (_name_2 + "Report");
        _builder.newLineIfNotEmpty();
        {
          List<ReportJoinDef> _joinDefs = reportTableDef_1.getJoinDefs();
          for(final ReportJoinDef joinTableDef : _joinDefs) {
            _builder.append("\t");
            String _name_3 = joinTableDef.getName();
            final String joinTableRef = (_name_3 + "Report");
            _builder.newLineIfNotEmpty();
            _builder.append("\t");
            String _name_4 = joinTableDef.getName();
            String _plus = (reportVariable_1 + _name_4);
            final String joinTableVariable = (_plus + "Join");
            _builder.newLineIfNotEmpty();
            _builder.append("\t");
            _builder.append("ReportJoinTable ");
            _builder.append(joinTableVariable, "	");
            _builder.append(" = new ReportJoinTable();");
            _builder.newLineIfNotEmpty();
            _builder.append("\t");
            _builder.append(joinTableVariable, "	");
            _builder.append(".setReportTable(");
            _builder.append(joinTableRef, "	");
            _builder.append(");");
            _builder.newLineIfNotEmpty();
            _builder.append("\t");
            _builder.append(reportVariable_1, "	");
            _builder.append(".getReportJoinReportTableId().add(");
            _builder.append(joinTableVariable, "	");
            _builder.append(");");
            _builder.newLineIfNotEmpty();
          }
        }
      }
    }
    _builder.append("\t");
    _builder.append("return reportTables;");
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
