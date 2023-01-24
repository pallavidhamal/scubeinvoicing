package com.scube.invoicing.util;

public class StringNullEmpty {
	
	public static String stringNullAndEmptyToBlank(String string) {
		
		if (string == null || string.isEmpty() || string.trim().isEmpty()) {
			return "";
		}
		
		return string;
	}

}
