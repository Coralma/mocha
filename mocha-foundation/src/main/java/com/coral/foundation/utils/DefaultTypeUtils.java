package com.coral.foundation.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;

public class DefaultTypeUtils {

	public static String getDefaultTypeString(Object value) {
		if(value == null) {
			return "";
		} else if(value instanceof Date) {
			return DateUtils.date2String((Date)value, DateUtils.dayFormat);
		} else if(value instanceof BigDecimal || value instanceof Double) {
			DecimalFormat df = new DecimalFormat("#0.00");
			return df.format(value);
		} else {
			return value.toString();
		}
	}
}
