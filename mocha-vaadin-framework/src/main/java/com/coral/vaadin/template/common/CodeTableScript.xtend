package com.coral.vaadin.template.common

import java.util.List
import com.coral.foundation.md.model.Mocha
import com.coral.foundation.utils.DateUtils

class CodeTableScript {
	
	List<Mocha> mochas
  	
  	def init(List<Mocha> mochas) {
  		this.mochas = mochas;
	}
	
	def generate()'''
		«FOR mocha : mochas»
			«FOR codeTable : mocha.codeTableList»
				INSERT INTO T_CODE_TABLE (NAME, IDS, PARENT, app, C_CREATTIME, C_LASTMODIFIEDTIME) VALUES
					('«codeTable.name»','«codeTable.ids»', NULL, NULL,'«DateUtils::currentDateSQLString»','«DateUtils::currentDateSQLString»');
				«FOR value : codeTable.values»
					INSERT INTO T_CODE_TABLE_VALUE(DATAS, LANGUAGE, CODE_TABLE_NAME, C_CREATTIME, C_LASTMODIFIEDTIME) VALUES
						('«value.datas»','«value.language»', '«codeTable.name»', '«DateUtils::currentDateSQLString»','«DateUtils::currentDateSQLString»');
				«ENDFOR»
			«ENDFOR»
		«ENDFOR»
	'''
}