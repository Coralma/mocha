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
            _builder.append(codeTable.name, "	");
            _builder.append("\',\'");
            _builder.append(codeTable.ids, "	");
            _builder.append("\', NULL, NULL,\'");
            String _currentDateSQLString = DateUtils.currentDateSQLString();
            _builder.append(_currentDateSQLString, "	");
            _builder.append("\',\'");
            String _currentDateSQLString_1 = DateUtils.currentDateSQLString();
            _builder.append(_currentDateSQLString_1, "	");
            _builder.append("\');");
            _builder.newLineIfNotEmpty();
            {
              for(final CodeTableValue value : codeTable.values) {
                _builder.append("INSERT INTO T_CODE_TABLE_VALUE(DATAS, LANGUAGE, CODE_TABLE_NAME, C_CREATTIME, C_LASTMODIFIEDTIME) VALUES");
                _builder.newLine();
                _builder.append("\t");
                _builder.append("(\'");
                _builder.append(value.datas, "	");
                _builder.append("\',\'");
                _builder.append(value.language, "	");
                _builder.append("\', \'");
                _builder.append(codeTable.name, "	");
                _builder.append("\', \'");
                String _currentDateSQLString_2 = DateUtils.currentDateSQLString();
                _builder.append(_currentDateSQLString_2, "	");
                _builder.append("\',\'");
                String _currentDateSQLString_3 = DateUtils.currentDateSQLString();
                _builder.append(_currentDateSQLString_3, "	");
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
