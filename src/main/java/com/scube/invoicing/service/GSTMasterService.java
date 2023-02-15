package com.scube.invoicing.service;

import java.util.List;

import javax.validation.Valid;

import com.scube.invoicing.dto.GSTMasterResponseDto;
import com.scube.invoicing.dto.incoming.GSTMasterIncomingDto;
import com.scube.invoicing.entity.GSTMasterEntity;

public interface GSTMasterService {
	
	boolean addGstInfoDetails(@Valid GSTMasterIncomingDto gstMasterIncomingDto);
	
	boolean updateGstInfoDetails(@Valid GSTMasterIncomingDto gstMasterIncomingDto);
	
	boolean deleteGstInfoDetailsByGstId(String gstID);
	
	GSTMasterResponseDto getGstInfoDetailsByGstId(String gstID);
	
	List<GSTMasterResponseDto> getAllGstInfoDetails();
	
	GSTMasterEntity getGstMasterEntityByGstID(String gstID);

}
