package com.scube.invoicing.service;

import java.text.ParseException;
import java.util.List;

import javax.validation.Valid;

import com.scube.invoicing.dto.RmImportsuffDto;
import com.scube.invoicing.dto.RmformattedImportsuffDto;
import com.scube.invoicing.dto.incoming.ReportIncomingDto;

public interface RmImportSuffService {
	
//	List<RmImportsuffDto>getRmImportData() throws ParseException;

	List<RmImportsuffDto> getRmImportData(@Valid ReportIncomingDto reportIncomingDto);
	
	List<RmformattedImportsuffDto> getRmFormattedImportData(@Valid ReportIncomingDto reportIncomingDto);

}
