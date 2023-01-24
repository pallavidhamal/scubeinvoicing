package com.scube.invoicing.service;

import java.text.ParseException;
import java.util.List;

import com.scube.invoicing.dto.ReportResponseDto;
import com.scube.invoicing.dto.incoming.ReportIncomingDto;

public interface ReportService {

	
/*	RoleEntity	findRoleNameByCode(String code);
	RoleEntity	findRoleId(String id);

	boolean addRole(@Valid RoleIncomingDto roleIncomingDto);

	boolean editRole(@Valid RoleIncomingDto roleIncomingDto);
	boolean deleteRole(String id);
	 List<RoleDto>  findAllRoles();
	 List<RoleDto> findActiveRoles();
	 RoleDto getRoleById(String id);*/
	
	List<ReportResponseDto> getColorCountByDateRange(ReportIncomingDto reportIncomingDto);
	
	List<ReportResponseDto> getFocusedProductRedAndGrey() throws ParseException;
	
	List<ReportResponseDto> getFocusedProductBlue() throws ParseException;
	
	List<ReportResponseDto> getExcessValuReportByProductAndDateRange(ReportIncomingDto reportIncomingDto);
	
	List<ReportResponseDto> getExcessValuReportByDateRange(ReportIncomingDto reportIncomingDto);
	
	List<ReportResponseDto> getMaterialReceivedAndIssued() throws ParseException;
	List<ReportResponseDto> getValueInINROfItemsInEachColorCode(ReportIncomingDto reportIncomingDto);
	
	List<ReportResponseDto> getOrdersToBeCancelled();
	
	List<ReportResponseDto> getOrdersToBePlaced() throws ParseException;
	
	List<ReportResponseDto> getMaterialDetails(ReportIncomingDto reportIncomingDto);
	
	List<ReportResponseDto> getPODetailsForOrdersToBeCancelled(ReportIncomingDto reportIncomingDto);
	
	ReportResponseDto getMaxReportDateFromRmTable();
	
	ReportResponseDto getTotalValueOrdersToBePlacedINR();
	
	ReportResponseDto getTotalValueOrdersToBeCancelledReportINR();
	
	List<ReportResponseDto> getMaterialReportByLast15Days(ReportIncomingDto reportIncomingDto);

}
