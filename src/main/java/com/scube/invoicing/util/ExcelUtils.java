package com.scube.invoicing.util;

import org.apache.poi.xssf.usermodel.XSSFCell;

public class ExcelUtils {
	
	public static String checkIfCellBlank(XSSFCell cell) {

		if (cell != null && cell.toString() != "")
			return cell.getStringCellValue();
		else
			return "";
	}
	
	
	public static String getFormattedCellValue(XSSFCell cell) {

		String cellValue="";
		if (cell != null && cell.toString() != "")
		{
			int cellType = cell.getCellType();
			if (cellType == 1) 
			{
				cellValue=cell.getStringCellValue();
			} 
			else
			{
				Double intVal = (Double) cell.getNumericCellValue();
				cellValue=intVal.toString();
			}
		}
		else
			cellValue="";  //for blank/null cells
			
		return cellValue;
	}
	
}
