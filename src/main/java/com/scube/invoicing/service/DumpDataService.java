package com.scube.invoicing.service;

import java.util.List;

import com.scube.invoicing.dto.ReportResponseDto;
import com.scube.invoicing.dto.incoming.DumpDataIncomingDto;
import com.scube.invoicing.entity.DumpDataEntity;

public interface DumpDataService {
	
	ReportResponseDto getDumpDataByDate(DumpDataIncomingDto dumpDataIncomingDto) throws Exception;

}
