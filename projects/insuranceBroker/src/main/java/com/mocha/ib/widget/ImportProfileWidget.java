package com.mocha.ib.widget;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.List;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.bean.ColumnPositionMappingStrategy;
import au.com.bytecode.opencsv.bean.CsvToBean;
import au.com.bytecode.opencsv.bean.HeaderColumnNameMappingStrategy;

import com.coral.foundation.oauth.APIKeys;
import com.mocha.cooperate.model.File;
import com.mocha.ib.model.InsuranceCustomer;
import com.vaadin.ui.Upload.SucceededEvent;

public class ImportProfileWidget extends FileUpload {

	private List<InsuranceCustomer> list;

	public ImportProfileWidget() {
		super();
		initFileListerner();
	}

	private void initFileListerner() {
		fileListener = new FileListener() {
			@Override
			public void upload(File uploadFile) {
				System.out.println("Start to load the profile now");
			}
		};
	}

	@SuppressWarnings("unused")
	private List<InsuranceCustomer> createParseResult(String parseString) {
		HeaderColumnNameMappingStrategy<InsuranceCustomer> strat = new HeaderColumnNameMappingStrategy<InsuranceCustomer>();
		strat.setType(InsuranceCustomer.class);
		CsvToBean<InsuranceCustomer> csv = new CsvToBean<InsuranceCustomer>();
		return csv.parse(strat, new StringReader(parseString));
	}

	/* load the profile csv and persist them */
	@Override
	public void uploadSucceeded(SucceededEvent event) {
		CSVReader reader;
		try {
			reader = new CSVReader(new FileReader(APIKeys.FILE_PATH + uploadFile.getName()), ',');
			HeaderColumnNameMappingStrategy<InsuranceCustomer> strat = new HeaderColumnNameMappingStrategy<InsuranceCustomer>();
			strat.setType(InsuranceCustomer.class);
			CsvToBean<InsuranceCustomer> csv = new CsvToBean<InsuranceCustomer>();
			setList(csv.parse(strat, reader));
			System.out.println(getList());
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public List<InsuranceCustomer> getList() {
		return list;
	}

	public void setList(List<InsuranceCustomer> list) {
		this.list = list;
	}
}
