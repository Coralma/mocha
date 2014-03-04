package com.coral.vaadin.template.common;

import com.coral.foundation.md.model.CodeTable;
import com.coral.foundation.md.model.CodeTableValue;
import com.coral.foundation.md.model.Mocha;
import com.coral.foundation.utils.DateUtils;
import java.util.List;
import org.eclipse.xtend2.lib.StringConcatenation;

@SuppressWarnings("all")
public class CodeTableScript {
  private List<Mocha> mochas;
  
  public List<Mocha> init(final List<Mocha> mochas) {
    List<Mocha> _mochas = this.mochas = mochas;
    return _mochas;
  }
  
  public CharSequence generate() {
    StringConcatenation _builder = new StringConcatenation();
    {
      for(final Mocha mocha : this.mochas) {
        {
          List<CodeTable> _codeTableList = mocha.getCodeTableList();
          for(final CodeTable codeTable : _codeTableList) {
            _builder.append("INSERT INTO T_CODE_TABLE (NAME, IDS, PARENT, app, C_CREATTIME, C_LASTMODIFIEDTIME) VALUES");
            _builder.newLine();
            _builder.append("\t");
            _builder.append("(\'");
            _builder.append(codeTable.name, "\t");
            _builder.append("\',\'");
            _builder.append(codeTable.ids, "\t");
            _builder.append("\', NULL, NULL,\'");
            String _currentDateSQLString = DateUtils.currentDateSQLString();
            _builder.append(_currentDateSQLString, "\t");
            _builder.append("\',\'");
            String _currentDateSQLString_1 = DateUtils.currentDateSQLString();
            _builder.append(_currentDateSQLString_1, "\t");
            _builder.append("\');");
            _builder.newLineIfNotEmpty();
            {
              for(final CodeTableValue value : codeTable.values) {
                _builder.append("INSERT INTO T_CODE_TABLE_VALUE(DATAS, LANGUAGE, CODE_TABLE_NAME, C_CREATTIME, C_LASTMODIFIEDTIME) VALUES");
                _builder.newLine();
                _builder.append("\t");
                _builder.append("(\'");
                _builder.append(value.datas, "\t");
                _builder.append("\',\'");
                _builder.append(value.language, "\t");
                _builder.append("\', \'");
                _builder.append(codeTable.name, "\t");
                _builder.append("\', \'");
                String _currentDateSQLString_2 = DateUtils.currentDateSQLString();
                _builder.append(_currentDateSQLString_2, "\t");
                _builder.append("\',\'");
                String _currentDateSQLString_3 = DateUtils.currentDateSQLString();
                _builder.append(_currentDateSQLString_3, "\t");
                _builder.append("\');");
                _builder.newLineIfNotEmpty();
              }
            }
          }
        }
      }
    }
    return _builder;
  }
}
