package com.scube.invoicing.util;

import java.text.DecimalFormat;

public class NumberFormatUtils {
	
	private static final DecimalFormat decimalFormat = new DecimalFormat("0.00");
	
	public static String decimalFormat(Double value) {
		
		String formattedValue = decimalFormat.format(value);
		
		return formattedValue;
	}
	
}
