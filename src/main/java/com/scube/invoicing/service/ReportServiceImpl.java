 package com.scube.invoicing.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scube.invoicing.dto.ReportResponseDto;
import com.scube.invoicing.dto.incoming.ReportIncomingDto;
import com.scube.invoicing.dto.mapper.MaterialDetailsMapper;
import com.scube.invoicing.dto.mapper.OrderToBePlacedMapper;
import com.scube.invoicing.dto.mapper.OrdersToBeCancelledAndPlacedMapper;
import com.scube.invoicing.dto.mapper.ReportColorCountMapper;
import com.scube.invoicing.dto.mapper.ReportFocusedProductMapper;
import com.scube.invoicing.dto.mapper.ReportMaterialReceivedAndIssuedMapper;
import com.scube.invoicing.exception.BRSException;
import com.scube.invoicing.repository.ReportRepository; 


@Service
public class ReportServiceImpl implements ReportService{
	
	private static final Logger logger = LoggerFactory.getLogger(ReportServiceImpl.class);
	
	@Autowired
	ReportRepository reportRepository;
	

	@Override
	public List<ReportResponseDto> getColorCountByDateRange(ReportIncomingDto reportIncomingDto) {
		// TODO Auto-generated method stub
		
		List<Map<String, String>> listDtl=reportRepository.getItemColorCountByDateRange(reportIncomingDto.getFromdt(),reportIncomingDto.getTodt());
		
		List<ReportResponseDto> DtoLst = ReportColorCountMapper.tocolorCountRespDto(listDtl);
		
		return DtoLst;
		
	}


	@Override
	public List<ReportResponseDto> getFocusedProductRedAndGrey() throws ParseException {
		// TODO Auto-generated method stub
		
		List<Map<String, String>> focusedProductRedAndGreyList = reportRepository.getFocusedProductByColorRedAndGrey();
		
		List<ReportResponseDto> reportResponseDtosList = ReportFocusedProductMapper.toFocusedProductRespDto(focusedProductRedAndGreyList);
		
		return reportResponseDtosList;
	}
	
	
	@Override
	public List<ReportResponseDto> getFocusedProductBlue() throws ParseException  {
		// TODO Auto-generated method stub
		
		List<Map<String, String>> focusedProductBlueList = reportRepository.getFocusedProductDetailsByColorBlue();
		
		List<ReportResponseDto> reportResponseDtosList = ReportFocusedProductMapper.toFocusedProductRespDto(focusedProductBlueList);
		
		return reportResponseDtosList;
	}


	@Override
	public List<ReportResponseDto> getExcessValuReportByProductAndDateRange(ReportIncomingDto reportIncomingDto) {
		// TODO Auto-generated method stub
		
		logger.info("***ReportServiceImpl getExcessValuReportByProductAndDateRange***");
		
		List<ReportResponseDto> defaultValuesResponseList = new ArrayList<ReportResponseDto>();
		List<ReportResponseDto> reportResponseDtoList = new ArrayList<ReportResponseDto>();
		
		if(reportIncomingDto.getMaterialCode().equals("All")) {
			
			logger.info("-------------" + "Default All Values" + "----------------");
			
			List<Map<String, String>> defaultAllValuesList = reportRepository.getExcessValuReportByDefaultAll(reportIncomingDto.getFromdt(), reportIncomingDto.getTodt());
			
			defaultValuesResponseList = ReportFocusedProductMapper.toFocusedProductRespDto(defaultAllValuesList);
			
			logger.info("Size" + "-------------" + defaultAllValuesList.size());
			
			return defaultValuesResponseList;
		}
		
		else {
			
			logger.info("-------------" + "Selected Values Only" + "----------------");
			
			List<Map<String, String>> excessValueReportList = reportRepository.getExcessValuReportByProductAndDateRange(
				reportIncomingDto.getFromdt(), reportIncomingDto.getTodt(), reportIncomingDto.getMaterialCode());		
		
			logger.info("Start Date " + reportIncomingDto.getFromdt() + "End Date " + reportIncomingDto.getTodt() +
				"Materil Code " + reportIncomingDto.getMaterialCode());
			
			logger.info("Size" + "-------------" + excessValueReportList.size());
			
			reportResponseDtoList = ReportFocusedProductMapper.toFocusedProductRespDto(excessValueReportList);
		
		}
		
		return reportResponseDtoList;
	}
	
	@Override
	public List<ReportResponseDto> getExcessValuReportByDateRange(ReportIncomingDto reportIncomingDto) {
		// TODO Auto-generated method stub
		
		logger.info("***ReportServiceImpl getExcessValuReportByProductAndDateRange***");
		
		List<Map<String, String>> excessValueReportList = reportRepository.getExcessValuReportByDateRange(
				reportIncomingDto.getFromdt(), reportIncomingDto.getTodt());
		
		logger.info("Start Date " + reportIncomingDto.getFromdt() + "End Daate " + reportIncomingDto.getTodt() +
				"Materil Code " + reportIncomingDto.getMaterialCode());
		
		List<ReportResponseDto> reportResponseDtoList = ReportFocusedProductMapper.toFocusedProductRespDto(excessValueReportList);
		
		return reportResponseDtoList;
		
	}


	@Override
	public List<ReportResponseDto> getMaterialReceivedAndIssued() throws ParseException {
		// TODO Auto-generated method stub
		
		logger.info("***ReportServiceImpl getMaterialReceivedAndIssued***");
		
		List<Map<String, String>> materialRecievedAndIssuedList = reportRepository.getMaterialReceivedAndIssued();
		
		List<ReportResponseDto> reportResponseDtoList = ReportMaterialReceivedAndIssuedMapper.toMaterialReceivedAndIssuedResponseDtos(materialRecievedAndIssuedList);
		
		return reportResponseDtoList;
	}


	@Override
	public List<ReportResponseDto> getOrdersToBePlaced() throws ParseException {
		// TODO Auto-generated method stub
		
		logger.info("***ReportServiceImpl getOrdersToBePlaced***");
		
		List<Map<String, String>> ordersToBePlacedList = reportRepository.getOrdersToBePlaced();
		
		List<ReportResponseDto> reportResponseDtoList = OrderToBePlacedMapper.toOrderToBePlacedResponseDtos(ordersToBePlacedList);
				
		return reportResponseDtoList;
	}

	@Override
	public List<ReportResponseDto> getOrdersToBeCancelled() {
		// TODO Auto-generated method stub
		
		logger.info("***ReportServiceImpl getOrdersToBeCancelled***");
		
		List<Map<String, String>> ordersToBeCancelledList = reportRepository.getOrdersToBeCancelledList();
		
		List<ReportResponseDto> reportResponseDtoList = OrdersToBeCancelledAndPlacedMapper.toOrdersToBeCancelledReportList(ordersToBeCancelledList);
		
		return reportResponseDtoList;
	}
	
	@Override
	public List<ReportResponseDto> getValueInINROfItemsInEachColorCode(ReportIncomingDto reportIncomingDto) {
		// TODO Auto-generated method stub
		
		logger.info("***ReportServiceImpl getValueInINROfItemsInEachColorCode***");
		
		List<Map<String, String>> valueOfItemInEachColorCodeList = reportRepository.getValueInINRForEachColorItem(reportIncomingDto.getFromdt(),
				reportIncomingDto.getTodt());
		
		logger.info("Start Date " + reportIncomingDto.getFromdt() + "End Date " + reportIncomingDto.getTodt());
		
		List<ReportResponseDto> reportResponseDtoList = ReportColorCountMapper.toValueInINRForEachItemInColorCode(valueOfItemInEachColorCodeList);
		
		return reportResponseDtoList;
	}


	@Override
	public List<ReportResponseDto> getMaterialDetails(ReportIncomingDto reportIncomingDto) {
		// TODO Auto-generated method stub
		
		logger.info("***ReportServiceImpl getMaterialDetails***");
		
		List<Map<String, String>> materialDetailsList = reportRepository.getMaterialDetails(reportIncomingDto.getMaterialCode());
		
		logger.info("material Code" + reportIncomingDto.getMaterialCode());
		
		List<ReportResponseDto> reportResponseDtoList = MaterialDetailsMapper.toMaterialDetailsRespDto(materialDetailsList);
		
		return reportResponseDtoList;
		
	}


	@Override
	public List<ReportResponseDto> getPODetailsForOrdersToBeCancelled(ReportIncomingDto reportIncomingDto) {
		// TODO Auto-generated method stub
		
		logger.info("----------------------ReportServiceImpl getPODetailsForOrdersToBeCancelled-----------------------");
		
		logger.info("---------" + "Mat Code " + " =====> " + reportIncomingDto.getMaterialCode());
		
		List<Map<String, String>> materialPODetailsList = reportRepository.getPODetailsForOrdersToBeCancelled(reportIncomingDto.getMaterialCode());
		
		List<ReportResponseDto> reportResponseDtosList = OrdersToBeCancelledAndPlacedMapper.toMaterialCodePODetailsList(materialPODetailsList);
		
		return reportResponseDtosList;
	}


	@Override
	public ReportResponseDto getMaxReportDateFromRmTable() {
		// TODO Auto-generated method stub
		
		logger.info("----------------------ReportServiceImpl getMaxReportDateFromRmTable-----------------------");
		
		List<Map<String, String>>  reportEntity = reportRepository.getMaxReportDate();
		
		if(reportEntity == null) {
			throw BRSException.throwException("Error : No Records Present");
		}
		
		ReportResponseDto reportResponseDto = OrdersToBeCancelledAndPlacedMapper.toMaxReportDateFromRMTable(reportEntity);
		
		return reportResponseDto;
	}


	@Override
	public ReportResponseDto getTotalValueOrdersToBePlacedINR() {
		// TODO Auto-generated method stub
		
		logger.info("----------------------ReportServiceImpl getTotalValueOrdersToBePlacedINR-----------------------");
		
		List<Map<String, String>>  reportResonseEntityList = reportRepository.getTotalValueOrdersToBeReleasedForOrdersToBePlacedReportInINR();
		
		if(reportResonseEntityList == null) {
			throw BRSException.throwException("Error : No Records Present");
		}
		
		ReportResponseDto reportResponseDto = OrdersToBeCancelledAndPlacedMapper.toTotalValueOfOrdersReleasedInOrdersPlacedAndCancelled(reportResonseEntityList);
		
		
		
		return reportResponseDto;
	}
	
	
	@Override
	public ReportResponseDto getTotalValueOrdersToBeCancelledReportINR() {
		// TODO Auto-generated method stub
		
		logger.info("----------------------ReportServiceImpl getTotalValueOrdersToBeCancelledReportINR-----------------------");
		
		List<Map<String, String>>  reportResonseEntityList = reportRepository.getTotalValueOrdersToBeReleasedForOrdersToBeCancelledReportInINR();
		
		if(reportResonseEntityList == null) {
			throw BRSException.throwException("Error : No Records Present");
		}
		
		ReportResponseDto reportResponseDto = OrdersToBeCancelledAndPlacedMapper.toTotalValueOfOrdersReleasedInOrdersPlacedAndCancelled(reportResonseEntityList);
		
		return reportResponseDto;
	}


	@Override
	public List<ReportResponseDto> getMaterialReportByLast15Days(ReportIncomingDto reportIncomingDto) {
		// TODO Auto-generated method stub
		
		logger.info("----------------------ReportServiceImpl getMaterialReportByLast15Days-----------------------");
		
		String mat_code = reportIncomingDto.getMaterialCode();
		
		logger.info("------------" + " Material Code " + mat_code);
		
		List<Map<String, String>> reportResponseEntityList = reportRepository.getMaterialReportByLast15Days(mat_code);
		
		if(reportResponseEntityList == null) {
			throw BRSException.throwException("Error : No Records Present");
		}
		
		List<ReportResponseDto> reportResponseDtosList = ReportMaterialReceivedAndIssuedMapper.toMaterialReportForLast15DaysResponseDtos(reportResponseEntityList);
		
		return reportResponseDtosList;
	}

	
}
