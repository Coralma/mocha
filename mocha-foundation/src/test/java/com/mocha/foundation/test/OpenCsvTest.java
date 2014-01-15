package com.mocha.foundation.test;

import java.text.DecimalFormat;

import com.ibm.icu.math.BigDecimal;


public class OpenCsvTest {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		DecimalFormat df1 = new DecimalFormat("#0.00"); 
        System.out.println(df1.format(new BigDecimal(0.235634))); 
        System.out.println(df1.format(34.234634)); 

		
//		String csv = "D:\\temp\\output2.csv";
//		File file = new File(csv);
//		Writer fileWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file),Charset.forName("GBK").newEncoder()));
//		CSVWriter writer = new CSVWriter(fileWriter, CSVWriter.DEFAULT_SEPARATOR, '\u0000');
//		 
//		List<String[]> data = new ArrayList<String[]>();
//		data.add(new String[] {"上海", "无锡"});
//		data.add(new String[] {"2Germany", "Berlin"});
//		 
//		writer.writeAll(data);
//		
//		data = new ArrayList<String[]>();
//		data.add(new String[] {"AIndia", "New Delhi","Lee"});
//		data.add(new String[] {"BUnited States", "Washington D.C","Vance"});
//		data.add(new String[] {"CGermany", "Berlin", "Roful"});
//		writer.writeAll(data);
//		 
//		writer.close();
	}

}
