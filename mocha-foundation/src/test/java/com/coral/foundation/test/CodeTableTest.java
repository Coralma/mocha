/**
 * 
 */
package com.coral.foundation.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.coral.foundation.security.basic.dao.CodeTableDao;
import com.coral.foundation.security.model.CodeTable;
import com.coral.foundation.security.model.CodeTableValue;
import com.coral.foundation.spring.bean.SpringContextUtils;
import com.coral.foundation.utils.CodeTableUtils;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/applicationContext.xml"})
public class CodeTableTest {

	@Test
	public void testCodeTableCreate() {
//		CodeTableDao codeTableDao = SpringContextUtils.getBean(CodeTableDao.class);
//		for(CodeTable ct : codeTableDao.findAll()) {
//			codeTableDao.remove(ct.getID());
//		}
		
		String name = "customer-status";
//		CodeTable codeTable = new CodeTable();
//		codeTable.setName(name);
//		codeTable.setIds("1;2;3;4");
//		
//		List<CodeTableValue> codeTableValues = new ArrayList<CodeTableValue>();
//		CodeTableValue codeTableValue = new CodeTableValue();
//		codeTableValue.setCodeTable(codeTable);
//		codeTableValue.setCodeTableName(name);
//		codeTableValue.setLanguage("zh");
//		codeTableValue.setDatas("已获得客户;潜在客户;失败客户;关闭");
//		codeTableValues.add(codeTableValue);
//		
//		codeTableValue = new CodeTableValue();
//		codeTableValue.setCodeTable(codeTable);
//		codeTableValue.setCodeTableName(name);
//		codeTableValue.setLanguage("en");
//		codeTableValue.setDatas("Achieved;Potential;Failure;Close");
//		codeTableValues.add(codeTableValue);
//		
//		codeTable.setCodeTableValues(codeTableValues);
//		codeTableDao.persist(codeTable);
//		
		CodeTable ct = CodeTableUtils.get(name);
		System.out.println(ct.getIds());
		System.out.println(CodeTableUtils.parse(ct, "en"));
		System.out.println(CodeTableUtils.parse(ct, "zh"));
	}
}
