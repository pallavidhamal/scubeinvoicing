package com.scube.invoicing.service;

import java.util.List;

import javax.validation.Valid;

import com.scube.invoicing.dto.VendorMasterResponseDto;
import com.scube.invoicing.dto.incoming.VendorMasterIncomingDto;
import com.scube.invoicing.entity.VendorMasterEntity;

public interface VendorMasterService {
	
	boolean addNewVendor(@Valid VendorMasterIncomingDto vendorMasterIncomingDto);
	
	boolean updateVendor(@Valid VendorMasterIncomingDto vendorMasterIncomingDto);
	
	boolean deleteVendorByVendorID(String vendorID);
	
	VendorMasterResponseDto getVendorInfoByVendorID(String vendorID);
	
	List<VendorMasterResponseDto> getAllVendorList();

	VendorMasterEntity getVendorMasterEntityByVendorID(String vendorID);
	
}
