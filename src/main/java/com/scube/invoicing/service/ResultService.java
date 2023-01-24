package com.scube.invoicing.service;

import java.util.List;

import com.scube.invoicing.dto.ReportResponseDto;

public interface ResultService {
	
	List<ReportResponseDto> runProcedureForImportedExcelDataDate();
	List<ReportResponseDto> callImportItemProcedure();

}
