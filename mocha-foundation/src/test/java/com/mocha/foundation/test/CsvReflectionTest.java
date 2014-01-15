package com.mocha.foundation.test;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.PropertyUtils;

public class CsvReflectionTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		DefaultCsvDto dto = new DefaultCsvDto();
		dto.setAge("20");
		dto.setName("Coral");
		showData(dto);
	}

	public static void showData(CsvDto dto) throws Exception {
		System.out.println(PropertyUtils.getProperty(dto, "age"));
		System.out.println(PropertyUtils.getProperty(dto, "name"));
	}
}
