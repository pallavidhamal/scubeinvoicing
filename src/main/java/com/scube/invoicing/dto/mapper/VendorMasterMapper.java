package com.scube.invoicing.dto.mapper;

import java.util.ArrayList;
import java.util.List;

import com.scube.invoicing.dto.VendorMasterResponseDto;
import com.scube.invoicing.entity.VendorMasterEntity;

public class VendorMasterMapper {
	
	public static VendorMasterResponseDto toVendorMasterResponseDto(VendorMasterEntity vendorMasterEntity) {
		return new VendorMasterResponseDto()
				.setVendorID(vendorMasterEntity.getId())
				.setVendorName(vendorMasterEntity.getVendorName())
				.setVendorContactNo(vendorMasterEntity.getVendorContactNo())
				.setVendorEmailID(vendorMasterEntity.getVendorEmailID());
	}
	
	
	public static List<VendorMasterResponseDto> toVendorMasterResponseDtosList(
			List<VendorMasterEntity> vendorMasterEntitiesList) {
		// TODO Auto-generated method stub	
		List<VendorMasterResponseDto> vendorMasterResponseDtosList = new ArrayList<VendorMasterResponseDto>();
		for(VendorMasterEntity vendorMasterEntity : vendorMasterEntitiesList) {
			vendorMasterResponseDtosList.add(toVendorMasterResponseDto(vendorMasterEntity));			
		}	
		return vendorMasterResponseDtosList;
	}

}
