package com.scube.invoicing.service;

import java.util.List;


import com.scube.invoicing.dto.CurrentOrdersInPipelineResponseDto;
import com.scube.invoicing.dto.incoming.ReportIncomingDto;
import com.scube.invoicing.entity.CurrentOrdersInPipelineDetailsEntity;

public interface CurrentOrdersInPipelineDetailService {
	
	List<CurrentOrdersInPipelineDetailsEntity> getCurrentOrdersInPipelineDetailsExcelFileData() throws Exception;
	
	List<CurrentOrdersInPipelineResponseDto> getCurrentOrdersInPipelineDetailsByDateRange(ReportIncomingDto reportIncomingDto);
	
}
