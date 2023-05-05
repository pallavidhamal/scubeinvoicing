package com.scube.invoicing.dto.mapper;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import com.scube.invoicing.dto.ExpenseLedgerResponseDto;
import com.scube.invoicing.entity.ExpenseLedgerEntity;

public class ExpenseLedgerMapper {
	
	static Base64.Decoder decoder = Base64.getDecoder();
	
	public static ExpenseLedgerResponseDto toExpenseLedgerResponseDto(ExpenseLedgerEntity expenseLedgerEntity) {
		Instant createdAt = expenseLedgerEntity.getCreatedAt();
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	    String formattedDate = createdAt.atZone(ZoneId.systemDefault()).format(formatter);
		return new ExpenseLedgerResponseDto()
				
				
				.setLedgerName(expenseLedgerEntity.getLedgerMasterEntity().getLedgerName())
				.setAmount(expenseLedgerEntity.getAmount() != null ? new String(decoder.decode(expenseLedgerEntity.getAmount())) : null)
				.setExpenseDate(formattedDate)
				.setTransactionType("");				
	}
	
	public static List<ExpenseLedgerResponseDto> toExpenseLedgerResponseDtosList(List<ExpenseLedgerEntity> customerLedgerEntitiesList) {
		// TODO Auto-generated method stub
		
		List<ExpenseLedgerResponseDto> expenseLedgerResponseDtos = new ArrayList<ExpenseLedgerResponseDto>();
		for(ExpenseLedgerEntity expenseLedgerEntity : customerLedgerEntitiesList) {
			expenseLedgerResponseDtos.add(toExpenseLedgerResponseDto(expenseLedgerEntity));	
		}
		
		return expenseLedgerResponseDtos;
	}
}
