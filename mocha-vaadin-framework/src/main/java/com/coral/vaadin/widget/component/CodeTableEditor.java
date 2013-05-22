/**
 * 
 */
package com.coral.vaadin.widget.component;

import java.util.List;

import com.coral.foundation.security.model.App;
import com.coral.foundation.security.model.CodeTable;
import com.vaadin.ui.VerticalLayout;

/**
 * @author Coral.Ma
 *
 */
public class CodeTableEditor extends VerticalLayout {

	public CodeTableEditor(App app) {
//		List<CodeTable> codeTables = app.getCodeTables();
//		for(CodeTable codeTable : codeTables) {
//			CodeTableSection section = new CodeTableSection(codeTable);
//			addComponent(section);
//		}
	}
	
	public class CodeTableSection extends VerticalLayout {
		
		private CodeTable codeTable;
		
		public CodeTableSection(CodeTable codeTable) {
			this.codeTable = codeTable;
//			String name = codeTable.getName();
//			String values = codeTable.getData();
		}

		/**
		 * @return the codeTable
		 */
		public CodeTable getCodeTable() {
			return codeTable;
		}

		/**
		 * @param codeTable the codeTable to set
		 */
		public void setCodeTable(CodeTable codeTable) {
			this.codeTable = codeTable;
		}
	}
}
