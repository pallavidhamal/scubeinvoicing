package com.scube.invoicing.service;

import java.util.List;


import com.scube.invoicing.entity.CurrentOrdersInPipelineSummaryEntity;

public interface CurrentOrdersInPipelineService {

	List<CurrentOrdersInPipelineSummaryEntity> getCurrentOrdersInPipelineSummaryExcelFileData () throws Exception;
	
}
