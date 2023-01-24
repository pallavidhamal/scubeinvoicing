package com.scube.invoicing.controller;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.text.ParseException;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scube.invoicing.dto.incoming.ReportIncomingDto;
import com.scube.invoicing.dto.response.Response;
import com.scube.invoicing.service.ReportService;
import com.scube.invoicing.service.ResultService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(path = {"/api/v1/report"}, produces = APPLICATION_JSON_VALUE)
public class ReportController {

	private static final Logger logger = LoggerFactory.getLogger(ReportController.class);
	
	@Autowired
	ReportService reportService;
	
	@Autowired
	ResultService resultService;
	
	@PostMapping( value = "/getColorCountByDateRange" , consumes = APPLICATION_JSON_VALUE)
	public Response getColorCountByDateRange(@Valid @RequestBody ReportIncomingDto reportIncomingDto) {
		
		logger.info("***ReportController getColorCountByDateRange***");
		
		return Response.ok().setPayload(reportService.getColorCountByDateRange(reportIncomingDto));	
		
	}
	
	
	@GetMapping (value = "/getFocusedProductsListRedAndGrey")
	public Response getFocusedProductRedAAndGrey() throws ParseException {
		
		logger.info("***ReportController getFocusedProductRedAAndGrey***");
		
		return Response.ok().setPayload(reportService.getFocusedProductRedAndGrey());	
		
	}
	
	
	@GetMapping (value = "/getFocusedProductsListBlue")
	public Response getFocusedProductBlue() throws ParseException  {
		
		logger.info("***ReportController getFocusedProductBlue***");
		
		return Response.ok().setPayload(reportService.getFocusedProductBlue());	
		
	}
	
	@GetMapping ( value = "/getMaterialReceivedAndIssued")
	public Response getMaterialReceivedAndIssued() throws ParseException {
	
		logger.info("***Report Controller getMaterialReceivedAndIssued***");
		
		return Response.ok().setPayload(reportService.getMaterialReceivedAndIssued());
	}
	
	@GetMapping (value = "/getOrdersToBePlaced")
	public Response getOrderToBePlaced() throws ParseException {
		
		logger.info("***Report Controller getOrdersToBePlaced***");
		
		return Response.ok().setPayload(reportService.getOrdersToBePlaced());
	}
	
	@PostMapping (value = "/getExcessValuReportByProductAndDateRange")
	public Response getExcessValuReportByProductAndDateRange(@Valid @RequestBody ReportIncomingDto reportIncomingDto) {
		
		logger.info("***ReportController getExcessValuReportByProductAndDateRange***");
		
		return Response.ok().setPayload(reportService.getExcessValuReportByProductAndDateRange(reportIncomingDto));	
		
	}
	
	@PostMapping (value = "/getExcessValuReportByDateRange")
	public Response getExcessValuReportByDateRange(@Valid @RequestBody ReportIncomingDto reportIncomingDto) {
		
		logger.info("***ReportController getExcessValuReportByProductAndDateRange***");
		
		return Response.ok().setPayload(reportService.getExcessValuReportByDateRange(reportIncomingDto));	
		
	}
	
	@PostMapping (value = "/getValueInINROfItemsInEachColorCode")
	public Response getValueInINROfItemsInEachColorCode(@Valid @RequestBody ReportIncomingDto reportIncomingDto) {
		
		logger.info("***ReportController getExcessValuReportByProductAndDateRange***");
		
		return Response.ok().setPayload(reportService.getValueInINROfItemsInEachColorCode(reportIncomingDto));	
		
	}
	
	@GetMapping (value = "/getOrdersToBeCancelledReport")
	public Response getOrdersToBeCancelled() {
		
		logger.info("***ReportController getOrdersToBeCancelled***");
		
		return Response.ok().setPayload(reportService.getOrdersToBeCancelled());
		
	}
	
	@PostMapping (value = "/getMaterialdetailsByMaterialCode")
	public Response getMaterialDetails( @Valid @RequestBody ReportIncomingDto reportIncomingDto) {
		
		logger.info("***ReportController getMaterialdetailsByMaterialCode***");
		
		return Response.ok().setPayload(reportService.getMaterialDetails(reportIncomingDto));
	}
	
	
	@PostMapping (value = "/getPODetailsForOrdersToBeCancelled", consumes = APPLICATION_JSON_VALUE)
	public Response getPODetailsForOrdersToBeCancelled(@Valid @RequestBody ReportIncomingDto reportIncomingDto) {
		
		logger.info("***ReportController getPODetailsForOrdersToBeCancelled***");
		
		return Response.ok().setPayload(reportService.getPODetailsForOrdersToBeCancelled(reportIncomingDto));
	}
	
	
	@GetMapping (value = "/getMaxReportDateFromRmTable")
	public Response getMaxReportDateFromRmTable() {
		
		logger.info("----------------" + "ReportController getMaxReportDateFromRmTable" + "-----------------");
		
		return Response.ok().setPayload(reportService.getMaxReportDateFromRmTable());
		
	}
	
	
	@GetMapping (value = "/runProcedureForImportedExcelData")
	public Response runProcedureForImportedExcelDataAndDate() {
		
		logger.info("----------------" + "ReportController runProcedureForImportedExcelDataAndDate" + "-----------------");
		
		return Response.ok().setPayload(resultService.runProcedureForImportedExcelDataDate());
		
	}
	
	
	@GetMapping (value = "/getTotalValueOrdersToBePlacedINR")
	public Response getTotalValueOrdersToBePlacedINR() {
		
		logger.info("----------------" + "ReportController getTotalValueOrdersToBePlacedINR" + "-----------------");
		
		return Response.ok().setPayload(reportService.getTotalValueOrdersToBePlacedINR());
		
	}
	
	
	@GetMapping (value = "/getTotalValueOrdersToBeCancelledReportINR")
	public Response getTotalValueOrdersToBeCancelledReportINR() {
		
		logger.info("----------------" + "ReportController getTotalValueOrdersToBeCancelledReportINR" + "-----------------");
		
		return Response.ok().setPayload(reportService.getTotalValueOrdersToBeCancelledReportINR());
		
	}
	
	
	@PostMapping (value = "/getMaterialReportByLast15Days", consumes = APPLICATION_JSON_VALUE)
	public Response getMaterialReportByLast15Days(@Valid @RequestBody ReportIncomingDto reportIncomingDto) {
		
		logger.info("----------------" + "ReportController getMaterialReportByLast15Days" + "-----------------");
		
		return Response.ok().setPayload(reportService.getMaterialReportByLast15Days(reportIncomingDto));
	}
	
}
