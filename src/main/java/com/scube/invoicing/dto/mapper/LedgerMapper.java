package com.scube.invoicing.dto.mapper;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import com.scube.invoicing.dto.LedgerResponseDto;
import com.scube.invoicing.entity.InvoiceLedgerEntity;

public class LedgerMapper {
	
	static Base64.Decoder decoder = Base64.getDecoder();
	
	public static LedgerResponseDto toInvoiceLedgerResponseDto(InvoiceLedgerEntity invoiceLedgerEntity) {
		Instant createdAt = invoiceLedgerEntity.getCreatedAt();
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	    String formattedDate = createdAt.atZone(ZoneId.systemDefault()).format(formatter);
		return new LedgerResponseDto()
				.setAmount(invoiceLedgerEntity.getAmount() != null ?
						new String(decoder.decode(invoiceLedgerEntity.getAmount())) : null)
				
				.setLedgerName(invoiceLedgerEntity.getLedgerMasterEntity().getLedgerName())	
				.setInvoiceDate(formattedDate)
				//.setInvoiceDate(DateUtils.formatDateToDDMMYYYYFormat(invoiceLedgerEntity.getCustomerInvoiceEntity().getInvoiceDate()))
				.setTransactionType(invoiceLedgerEntity.getTransactionType());
				
				//.setLedgerName("services");
				
				
	}
	
	public static List<LedgerResponseDto> toLedgerResponseDtosList(List<InvoiceLedgerEntity> customerLedgerEntitiesList) {
		// TODO Auto-generated method stub
		
		List<LedgerResponseDto> invoiceLedgerResponseDtos = new ArrayList<LedgerResponseDto>();
		for(InvoiceLedgerEntity invoiceLedgerEntity : customerLedgerEntitiesList) {
			invoiceLedgerResponseDtos.add(toInvoiceLedgerResponseDto(invoiceLedgerEntity));	
		}
		
		return invoiceLedgerResponseDtos;
	}

}
