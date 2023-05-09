package com.scube.invoicing.service;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.scube.invoicing.dto.CustomerInvoiceResponseDto;
import com.scube.invoicing.dto.incoming.CustomerInvoiceServiceIncomingDto;
import com.scube.invoicing.dto.incoming.CustomerInvoiceIncomingDto;
import com.scube.invoicing.dto.mapper.CustomerInvoiceMapper;
import com.scube.invoicing.entity.CompanyMasterEntity;
import com.scube.invoicing.entity.CustomerInvoiceEntity;
import com.scube.invoicing.entity.CustomerInvoiceServiceEntity;
import com.scube.invoicing.entity.CustomerMasterEntity;
import com.scube.invoicing.entity.GSTMasterEntity;
import com.scube.invoicing.entity.ServiceMasterEntity;
import com.scube.invoicing.exception.BRSException;
import com.scube.invoicing.exception.EntityType;
import com.scube.invoicing.exception.ExceptionType;
import com.scube.invoicing.repository.CustomerInvoiceRepository;
import com.scube.invoicing.repository.CustomerInvoiceServiceRepository;
import com.scube.invoicing.util.DateUtils;

@Service
public class CustomerInvoiceServiceImpl implements CustomerInvoiceService {
	
	@Autowired
	CustomerMasterService customerMasterService;
	
	@Autowired
	GSTMasterService gstMasterService;
	
	@Autowired
	ServiceMasterDetailsService serviceMasterDetailsService;
	
	@Autowired
	CompanyMasterService companyMasterService;
	
	@Autowired
	CustomerInvoiceServiceRepository customerInvoiceServiceRepository;
	
	@Autowired
	CustomerInvoiceRepository customerInvoiceRepository;
	
	@Value("${company.name}")
	private String companyName;
	
	private static final Logger logger = LoggerFactory.getLogger(CustomerInvoiceServiceImpl.class);

	Base64.Encoder baseEncoder = Base64.getEncoder();
	Base64.Decoder baseDecoder = Base64.getDecoder();
	
	// Create New Invoice and Service API
	@Override
	public String addCustomerInvoiceAndServiceInfo(@Valid CustomerInvoiceIncomingDto customerInvoiceIncomingDto) {
		// TODO Auto-generated method stub
		
		logger.info("----- CustomerInvoiceServiceImpl addCustomerInvoiceAndServiceData ----");
		
		if(customerInvoiceIncomingDto.getCustomerID() == "" || 
				customerInvoiceIncomingDto.getCustomerID().trim().isEmpty()) {
			throw BRSException.throwException("Error : Customer ID cannot be blank or null");
		}
	
		CustomerMasterEntity customerMasterEntity = customerMasterService.getCustomerDetailsByCustomerId
				(customerInvoiceIncomingDto.getCustomerID());			
		
		CustomerInvoiceEntity customerInvoiceEntity = new CustomerInvoiceEntity();
		
		customerInvoiceEntity.setIsdeleted("N");
		
		// Customer Info
		customerInvoiceEntity.setCustomerMasterEntity(customerMasterEntity);
		
		customerInvoiceEntity.setCustEmailId(customerInvoiceIncomingDto.getCustEmailId());
		customerInvoiceEntity.setCustomerBillingAddress(customerInvoiceIncomingDto.getCustomerBillingAddress());
		
		if(customerInvoiceIncomingDto.getShippingDate() != null && customerInvoiceIncomingDto.getShippingDate() != "") {
			customerInvoiceEntity.setShippingDate(DateUtils.stringToDateConvert(customerInvoiceIncomingDto.getShippingDate()));
		}
		
		customerInvoiceEntity.setShippingTo(customerInvoiceIncomingDto.getShippingTo());
		customerInvoiceEntity.setShippingVia(customerInvoiceIncomingDto.getShippingVia());
		customerInvoiceEntity.setTerms(customerInvoiceIncomingDto.getTerms());
		
		// Balance
		customerInvoiceEntity.setBalance(customerInvoiceIncomingDto.getBalance() != null ? 
				baseEncoder.encodeToString(customerInvoiceIncomingDto.getBalance().getBytes(StandardCharsets.UTF_8)) : null);
		
		// CGST/ SGST/ IGST/ GST4 values
		customerInvoiceEntity.setCgstAmount(customerInvoiceIncomingDto.getCgstAmount() != null ? 
			baseEncoder.encodeToString(customerInvoiceIncomingDto.getCgstAmount().getBytes(StandardCharsets.UTF_8)) : null);
		customerInvoiceEntity.setSgstAmount(customerInvoiceIncomingDto.getSgstAmount() != null ? 
			baseEncoder.encodeToString(customerInvoiceIncomingDto.getSgstAmount().getBytes(StandardCharsets.UTF_8)) : null);
		customerInvoiceEntity.setIgstAmount(customerInvoiceIncomingDto.getIgstAmount() != null ? 
				baseEncoder.encodeToString(customerInvoiceIncomingDto.getIgstAmount().getBytes(StandardCharsets.UTF_8)) : null);
		customerInvoiceEntity.setGst4Amount(customerInvoiceIncomingDto.getGst4Amount() != null ? 
				baseEncoder.encodeToString(customerInvoiceIncomingDto.getGst4Amount().getBytes(StandardCharsets.UTF_8)) : null);
		
		// Deposit/ Discounts/ Sub-total/ Total Amount
		customerInvoiceEntity.setDeposit(customerInvoiceIncomingDto.getDeposit() != null ? 
				baseEncoder.encodeToString(customerInvoiceIncomingDto.getDeposit().getBytes(StandardCharsets.UTF_8)) : null);
		customerInvoiceEntity.setDiscounts(customerInvoiceIncomingDto.getDiscounts() != null ? 
				baseEncoder.encodeToString(customerInvoiceIncomingDto.getDiscounts().getBytes(StandardCharsets.UTF_8)) : null);
		customerInvoiceEntity.setTotalAmount(baseEncoder.encodeToString(String.valueOf(
				customerInvoiceIncomingDto.getTotalAmount()).getBytes(StandardCharsets.UTF_8)));
		customerInvoiceEntity.setSubTotal(baseEncoder.encodeToString(String.valueOf(
				customerInvoiceIncomingDto.getSubTotal()).getBytes(StandardCharsets.UTF_8)));
		
		// Invoice and Actual TDS 
		customerInvoiceEntity.setInvoiceTds(customerInvoiceIncomingDto.getInvoiceTds() != null ? 
				baseEncoder.encodeToString(customerInvoiceIncomingDto.getInvoiceTds().getBytes(StandardCharsets.UTF_8)) : null); 
/*		customerInvoiceEntity.setActualTds(customerInvoiceIncomingDto.getActualTds() != null ? 
				baseEncoder.encodeToString(customerInvoiceIncomingDto.getActualTds().getBytes(StandardCharsets.UTF_8)) : null); */
		
		// Message on Invoice and Statement
		customerInvoiceEntity.setMessageInvoice(customerInvoiceIncomingDto.getMessageInvoice());
		customerInvoiceEntity.setMessageStatement(customerInvoiceIncomingDto.getMessageStatement());
		
		// Invoice Due Date and Tracking No
		customerInvoiceEntity.setDueDate(DateUtils.stringToDateConvert(customerInvoiceIncomingDto.getDueDate()));
		customerInvoiceEntity.setTrackingNo(customerInvoiceIncomingDto.getTrackingNo());
		
		// Invoice No and Date
		
//		customerInvoiceEntity.setInvoiceNo("INVOICE-00"+RandomUtils.generateRandomNumber());
		customerInvoiceEntity.setInvoiceDate(DateUtils.stringToDateConvert(customerInvoiceIncomingDto.getInvoiceDate()));
		
		// Invoice Payment Status
		customerInvoiceEntity.setPaymentStatus("Payment Pending");
		
		customerInvoiceRepository.save(customerInvoiceEntity);
		
		Set<CustomerInvoiceServiceEntity> customerInvoiceServiceEntityList = new HashSet<CustomerInvoiceServiceEntity>();
		
		for(CustomerInvoiceServiceIncomingDto customerInvoiceServiceIncomingDto : 
			customerInvoiceIncomingDto.getCustomerInvoiceServiceDtos()) {
			
			GSTMasterEntity gstMasterEntity = gstMasterService.getGstMasterEntityByGstID(customerInvoiceServiceIncomingDto.getGstID());
			ServiceMasterEntity serviceMasterEntity = serviceMasterDetailsService.
					getServiceMasterEntityByServiceID(customerInvoiceServiceIncomingDto.getProductService());
			
			CustomerInvoiceServiceEntity customerInvoiceServiceEntity = new CustomerInvoiceServiceEntity();
			
			customerInvoiceServiceEntity.setIsdeleted("N");
			
			// Set Customer/Invoice/GST/Service Entity
			customerInvoiceServiceEntity.setCustomerMasterEntity(customerMasterEntity);
			customerInvoiceServiceEntity.setCustomerInvoiceEntity(customerInvoiceEntity);
			customerInvoiceServiceEntity.setGstMasterEntity(gstMasterEntity);
			customerInvoiceServiceEntity.setServiceMasterEntity(serviceMasterEntity);
			
			// Add Invoice services
			customerInvoiceServiceEntity.setDescription(customerInvoiceServiceIncomingDto.getDescription());
			customerInvoiceServiceEntity.setHsnorSac(customerInvoiceServiceIncomingDto.getHsnOrSac() != null ?
					customerInvoiceServiceIncomingDto.getHsnOrSac() : null);
			customerInvoiceServiceEntity.setSku(customerInvoiceServiceIncomingDto.getSku() != null ?
					customerInvoiceServiceIncomingDto.getSku() : null);
			customerInvoiceServiceEntity.setQuantity(customerInvoiceServiceIncomingDto.getQuantity());
			customerInvoiceServiceEntity.setRate(customerInvoiceServiceIncomingDto.getRate());
			customerInvoiceServiceEntity.setAmount(baseEncoder.encodeToString(
					customerInvoiceServiceIncomingDto.getAmount().getBytes(StandardCharsets.UTF_8)));
			customerInvoiceServiceEntity.setServiceAmountWithGst(baseEncoder.encodeToString(
					customerInvoiceServiceIncomingDto.getServiceAmountWithGst().getBytes(StandardCharsets.UTF_8)));
			
			customerInvoiceServiceEntity.setGstAmount(baseEncoder.encodeToString(
					customerInvoiceServiceIncomingDto.getGstAmount().getBytes(StandardCharsets.UTF_8)));
			
			customerInvoiceServiceEntityList.add(customerInvoiceServiceEntity);
			
		}
		customerInvoiceServiceRepository.saveAll(customerInvoiceServiceEntityList);
		
		return customerInvoiceEntity.getId();
	}

	// Update Existing Invoice API
	@Override
	public CustomerInvoiceResponseDto updateCustomerInvoiceAndServiceInfo(@Valid CustomerInvoiceIncomingDto customerInvoiceIncomingDto) {
		// TODO Auto-generated method stub
		logger.info("----- CustomerInvoiceServiceImpl updateCustomerServiceInfo ----");
		
		if(customerInvoiceIncomingDto.getCustomerID() == "" || 
				customerInvoiceIncomingDto.getCustomerID().trim().isEmpty()) {
			throw BRSException.throwException("Error : Customer ID cannot be blank or null");
		}
		
		CompanyMasterEntity companyMasterEntity = companyMasterService.getCompanyEntityByCompanyName(companyName);
		CustomerMasterEntity customerMasterEntity = customerMasterService.getCustomerDetailsByCustomerId
				(customerInvoiceIncomingDto.getCustomerID());
		
		CustomerInvoiceEntity customerInvoiceEntity = customerInvoiceRepository.findById(customerInvoiceIncomingDto.getInvoiceID()).get();
		
		customerInvoiceEntity.setIsdeleted("N");
		
		// Customer Info
		customerInvoiceEntity.setCustomerMasterEntity(customerMasterEntity);
		
		customerInvoiceEntity.setCustEmailId(customerInvoiceIncomingDto.getCustEmailId());
		customerInvoiceEntity.setCustomerBillingAddress(customerInvoiceIncomingDto.getCustomerBillingAddress());
		customerInvoiceEntity.setShippingDate(DateUtils.stringToDateConvert(customerInvoiceIncomingDto.getShippingDate()));
		customerInvoiceEntity.setShippingTo(customerInvoiceIncomingDto.getShippingTo());
		customerInvoiceEntity.setShippingVia(customerInvoiceIncomingDto.getShippingVia());
		customerInvoiceEntity.setTerms(customerInvoiceIncomingDto.getTerms());
		
		// Balance
		customerInvoiceEntity.setBalance(customerInvoiceIncomingDto.getBalance() != null ? 
				baseEncoder.encodeToString(customerInvoiceIncomingDto.getBalance().getBytes(StandardCharsets.UTF_8)) : null);
		
		// CGST/ SGST/ IGST/ GST4 values
		customerInvoiceEntity.setCgstAmount(customerInvoiceIncomingDto.getCgstAmount() != null ? 
			baseEncoder.encodeToString(customerInvoiceIncomingDto.getCgstAmount().getBytes(StandardCharsets.UTF_8)) : null);
		customerInvoiceEntity.setSgstAmount(customerInvoiceIncomingDto.getSgstAmount() != null ? 
			baseEncoder.encodeToString(customerInvoiceIncomingDto.getSgstAmount().getBytes(StandardCharsets.UTF_8)) : null);
		customerInvoiceEntity.setIgstAmount(customerInvoiceIncomingDto.getIgstAmount() != null ? 
				baseEncoder.encodeToString(customerInvoiceIncomingDto.getIgstAmount().getBytes(StandardCharsets.UTF_8)) : null);
		customerInvoiceEntity.setGst4Amount(customerInvoiceIncomingDto.getGst4Amount() != null ? 
				baseEncoder.encodeToString(customerInvoiceIncomingDto.getGst4Amount().getBytes(StandardCharsets.UTF_8)) : null);
		
		// Deposit/ Discounts/ Sub-total/ Total Amount
		customerInvoiceEntity.setDeposit(customerInvoiceIncomingDto.getDeposit() != null ? 
				baseEncoder.encodeToString(customerInvoiceIncomingDto.getDeposit().getBytes(StandardCharsets.UTF_8)) : null);
		customerInvoiceEntity.setDiscounts(customerInvoiceIncomingDto.getDiscounts() != null ? 
				baseEncoder.encodeToString(customerInvoiceIncomingDto.getDiscounts().getBytes(StandardCharsets.UTF_8)) : null);
		customerInvoiceEntity.setTotalAmount(baseEncoder.encodeToString(String.valueOf(
				customerInvoiceIncomingDto.getTotalAmount()).getBytes(StandardCharsets.UTF_8)));
		customerInvoiceEntity.setSubTotal(baseEncoder.encodeToString(String.valueOf(
				customerInvoiceIncomingDto.getSubTotal()).getBytes(StandardCharsets.UTF_8)));
		
		// Invoice and Actual TDS 
		customerInvoiceEntity.setInvoiceTds(customerInvoiceIncomingDto.getInvoiceTds() != null ? 
				baseEncoder.encodeToString(customerInvoiceIncomingDto.getInvoiceTds().getBytes(StandardCharsets.UTF_8)) : null); 
/*		customerInvoiceEntity.setActualTds(customerInvoiceIncomingDto.getActualTds() != null ? 
				baseEncoder.encodeToString(customerInvoiceIncomingDto.getActualTds().getBytes(StandardCharsets.UTF_8)) : null); */
		
		// Message on Invoice and Statement
		customerInvoiceEntity.setMessageInvoice(customerInvoiceIncomingDto.getMessageInvoice());
		customerInvoiceEntity.setMessageStatement(customerInvoiceIncomingDto.getMessageStatement());
		
		// Invoice Due Date and Tracking No
		customerInvoiceEntity.setDueDate(DateUtils.stringToDateConvert(customerInvoiceIncomingDto.getDueDate()));
		customerInvoiceEntity.setTrackingNo(customerInvoiceIncomingDto.getTrackingNo());
		
		// Invoice No and Date
/*		customerInvoiceEntity.setInvoiceNo("INVOICE-00"+RandomUtils.generateRandomNumber()); */
		customerInvoiceEntity.setInvoiceDate(DateUtils.stringToDateConvert(customerInvoiceIncomingDto.getInvoiceDate()));
		
		// Invoice Payment Status
		customerInvoiceEntity.setPaymentStatus("Payment Pending");
		
		customerInvoiceRepository.save(customerInvoiceEntity);
		
		List<CustomerInvoiceServiceEntity> customerInvoiceServiceList = customerInvoiceServiceRepository.
				findByCustomerInvoiceEntityAndCustomerMasterEntity(customerInvoiceEntity, customerMasterEntity);
		
		customerInvoiceServiceRepository.deleteAll(customerInvoiceServiceList);
		
		Set<CustomerInvoiceServiceEntity> customerInvoiceServiceEntityList = new HashSet<CustomerInvoiceServiceEntity>();
		
		for(CustomerInvoiceServiceIncomingDto customerInvoiceServiceIncomingDto : 
			customerInvoiceIncomingDto.getCustomerInvoiceServiceDtos()) {
			
			GSTMasterEntity gstMasterEntity = gstMasterService.getGstMasterEntityByGstID(customerInvoiceServiceIncomingDto.getGstID());
			ServiceMasterEntity serviceMasterEntity = serviceMasterDetailsService.
					getServiceMasterEntityByServiceID(customerInvoiceServiceIncomingDto.getProductService());
			
			CustomerInvoiceServiceEntity customerInvoiceServiceEntity = new CustomerInvoiceServiceEntity();
			
			customerInvoiceServiceEntity.setIsdeleted("N");
			
			// Set Customer/Invoice/GST/Service Entity
			customerInvoiceServiceEntity.setCustomerMasterEntity(customerMasterEntity);
			customerInvoiceServiceEntity.setCustomerInvoiceEntity(customerInvoiceEntity);
			customerInvoiceServiceEntity.setGstMasterEntity(gstMasterEntity);
			customerInvoiceServiceEntity.setServiceMasterEntity(serviceMasterEntity);
			
			// Add Invoice services
			customerInvoiceServiceEntity.setDescription(customerInvoiceServiceIncomingDto.getDescription());
			customerInvoiceServiceEntity.setHsnorSac(customerInvoiceServiceIncomingDto.getHsnOrSac() != null ?
					customerInvoiceServiceIncomingDto.getHsnOrSac() : null);
			customerInvoiceServiceEntity.setSku(customerInvoiceServiceIncomingDto.getSku() != null ?
					customerInvoiceServiceIncomingDto.getSku() : null);
			customerInvoiceServiceEntity.setQuantity(customerInvoiceServiceIncomingDto.getQuantity());
			customerInvoiceServiceEntity.setRate(customerInvoiceServiceIncomingDto.getRate());
			customerInvoiceServiceEntity.setAmount(baseEncoder.encodeToString(
					customerInvoiceServiceIncomingDto.getAmount().getBytes(StandardCharsets.UTF_8)));
			customerInvoiceServiceEntity.setServiceAmountWithGst(baseEncoder.encodeToString(
					customerInvoiceServiceIncomingDto.getServiceAmountWithGst().getBytes(StandardCharsets.UTF_8)));
			
			customerInvoiceServiceEntity.setGstAmount(baseEncoder.encodeToString(
					customerInvoiceServiceIncomingDto.getGstAmount().getBytes(StandardCharsets.UTF_8)));
			customerInvoiceServiceEntityList.add(customerInvoiceServiceEntity);
		}
		customerInvoiceServiceRepository.saveAll(customerInvoiceServiceEntityList);
		
		return CustomerInvoiceMapper.toCustomerServiceAndCompanyResponseMailDto(customerInvoiceEntity, companyMasterEntity);
	}

	// Remove Invoice and Service Details - Cancel Button
	@Override
	public boolean removeCustomerInvoiceAndServiceInfo(String customerID, String invoiceNo) {
		// TODO Auto-generated method stub
		logger.info("----- CustomerInvoiceServiceImpl removeCustomerInvoiceAndServiceData ----");
		
		CustomerMasterEntity customerMasterEntity = customerMasterService.getCustomerDetailsByCustomerId(customerID);
		
		if(customerMasterEntity == null) {
			throw BRSException.throwException("Error : No Customer details found.");
		}
		
		CustomerInvoiceEntity customerInvoiceEntity = customerInvoiceRepository.findByCustomerMasterEntityAndInvoiceNo(
				customerMasterEntity, invoiceNo);
		
		if(customerInvoiceEntity == null) {
			throw BRSException.throwException("Error : No Invoice details found for " + customerMasterEntity.getCompanyName());
		}
		
		List<CustomerInvoiceServiceEntity> customerInvoiceServiceList = customerInvoiceServiceRepository.
				findByCustomerInvoiceEntityAndCustomerMasterEntity(customerInvoiceEntity, customerMasterEntity);
		
		if(customerInvoiceServiceList.size() == 0) {
			throw BRSException.throwException("Error : No Customer Service details found for " + customerInvoiceEntity.getInvoiceNo());
		}
		
		for(int i=0; i<customerInvoiceServiceList.size(); i++) {
			
			customerInvoiceServiceList.get(i).setIsdeleted("Y");
			customerInvoiceServiceRepository.save(customerInvoiceServiceList.get(i));
		}
		
		customerInvoiceEntity.setIsdeleted("Y");
		customerInvoiceRepository.save(customerInvoiceEntity);
		
		
		
		//customerInvoiceServiceRepository.deleteAll(customerInvoiceServiceList);
		//customerInvoiceRepository.delete(customerInvoiceEntity);
		
		return true;
	}

	@Override
	public CustomerInvoiceEntity getCustomerInvoiceEntityByCustomerIDAndInvoiceNo(CustomerMasterEntity customerMasterEntity, 
			String invoiceNo) {
		// TODO Auto-generated method stub
		return customerInvoiceRepository.findByCustomerMasterEntityAndInvoiceNo(customerMasterEntity, invoiceNo);
	}

	@Override
	public List<CustomerInvoiceServiceEntity> getCustomerServiceInfoByCustomerDetailsAndInvoiceDetails (
			CustomerMasterEntity customerMasterEntity, CustomerInvoiceEntity customerInvoiceEntity) {
		// TODO Auto-generated method stub
		logger.info("----- CustomerInvoiceServiceImpl getCustomerServiceInfoByCustomerDetailsAndInvoiceDetails ----");
		
		return customerInvoiceServiceRepository.findByCustomerInvoiceEntityAndCustomerMasterEntity(customerInvoiceEntity, customerMasterEntity);
	}

	// All Customer Invoice and Service List API -- Data Table for All Invoice List API
	@Override
	public List<CustomerInvoiceResponseDto> getAllCustomerInvoiceAndServiceList() {
		// TODO Auto-generated method stub
		logger.info("----- CustomerInvoiceServiceImpl getAllCustomerInvoiceAndServiceList ----");
		
		//List<CustomerInvoiceEntity> customerInvoiceEntitiesList = customerInvoiceRepository.findAll();
		List<CustomerInvoiceEntity> customerInvoiceEntitiesList = customerInvoiceRepository.getAllCustomerInvoiceListByStatus();
		return CustomerInvoiceMapper.toAllCustomerInvoiceResponseDtosList(customerInvoiceEntitiesList);
	}

	
	// Update Invoice Payment Status API
	@Override
	public boolean updateCustomerInvoicePaymentStatus(@Valid CustomerInvoiceIncomingDto customerInvoiceIncomingDto) {
		// TODO Auto-generated method stub
		logger.info("----- CustomerInvoiceServiceImpl updateCustomerInvoicePaymentStatus ----");
		
		if(customerInvoiceIncomingDto.getCustomerID() == "" || customerInvoiceIncomingDto.getCustomerID().trim().isEmpty()) {
			throw BRSException.throwException("Error : Customer ID cannot be empty or blank");
		}
		
		if(customerInvoiceIncomingDto.getInvoiceNo() == "" || customerInvoiceIncomingDto.getInvoiceNo().trim().isEmpty()) {
			throw BRSException.throwException("Error : Customer Invoice No cannot be empty or blank");
		}
		
		CustomerMasterEntity customerMasterEntity = customerMasterService.getCustomerDetailsByCustomerId(customerInvoiceIncomingDto.getCustomerID());
		
		if(customerMasterEntity == null) {
			throw BRSException.throwException("Error : NO Customer Records found.");
		}
		
		CustomerInvoiceEntity customerInvoiceEntity = getCustomerInvoiceEntityByCustomerIDAndInvoiceNo(customerMasterEntity, 
				customerInvoiceIncomingDto.getInvoiceNo());
		
		if(customerInvoiceEntity == null)  {
			throw BRSException.throwException(EntityType.INVOICE, ExceptionType.ENTITY_NOT_FOUND, customerInvoiceIncomingDto.getInvoiceNo());
		}
		
		customerInvoiceEntity.setActualTds(customerInvoiceIncomingDto.getActualTds() != null ? 
			baseEncoder.encodeToString(customerInvoiceIncomingDto.getActualTds().getBytes(StandardCharsets.UTF_8)) : null);
		customerInvoiceEntity.setPaymentCompletedDate(DateUtils.today());
		customerInvoiceEntity.setPaymentStatus("Payment Completed");
		customerInvoiceRepository.save(customerInvoiceEntity);
		
		return true;
	}

	// Get List of Customer Invoice By Customer ID
	@Override
	public List<CustomerInvoiceResponseDto> getCustomerInvoiceListByCustomerID(String customerID) {
		// TODO Auto-generated method stub
		logger.info("----- CustomerInvoiceServiceImpl getCustomerInvoiceListByCustomerID ----");
		
		CustomerMasterEntity customerMasterEntity = customerMasterService.getCustomerDetailsByCustomerId(customerID);
		List<CustomerInvoiceEntity> customerInvoiceEntityList = customerInvoiceRepository.findByCustomerMasterEntity(customerMasterEntity);
		
		List<CustomerInvoiceServiceEntity> customerInvoiceServiceEntityList = new ArrayList<CustomerInvoiceServiceEntity>();
		for(int i=0; i<customerInvoiceEntityList.size(); i++) {
			customerInvoiceServiceEntityList = customerInvoiceServiceRepository.findByCustomerInvoiceEntity(customerInvoiceEntityList.get(i));
		}
		
		return CustomerInvoiceMapper.toCustomerInvoiceResponseDtosList(customerInvoiceEntityList, customerInvoiceServiceEntityList);
	}

	@Override
	public CustomerInvoiceResponseDto getCustomerInvoiceAndServiceResponseDto(String customerID) {
		// TODO Auto-generated method stub
		logger.info("----- CustomerInvoiceServiceImpl getCustomerInvoiceAndServiceResponseDto ----");
		
		CustomerInvoiceEntity customerInvoiceEntity = customerInvoiceRepository.findById(customerID).get();
		List<CustomerInvoiceServiceEntity> customerInvoiceServiceEntityList = customerInvoiceServiceRepository.
				findByCustomerInvoiceEntity(customerInvoiceEntity);
		
		return CustomerInvoiceMapper.toCustomerInvoiceResponseDto(customerInvoiceEntity, customerInvoiceServiceEntityList);
	}

	// Get Customer Invoice List By Invoice ID
	@Override
	public CustomerInvoiceResponseDto getCustomerInvoiceListByInvoiceID(String invoiceID) {
		// TODO Auto-generated method stub
		logger.info("----- CustomerInvoiceServiceImpl getCustomerInvoiceListByInvoiceID ----");
		
		CustomerInvoiceEntity customerInvoiceEntity = customerInvoiceRepository.findById(invoiceID).get();
		List<CustomerInvoiceServiceEntity> customerInvoiceServiceEntityList = 
				customerInvoiceServiceRepository.findByCustomerInvoiceEntity(customerInvoiceEntity);
		return CustomerInvoiceMapper.toCustomerInvoiceResponseDto(customerInvoiceEntity, customerInvoiceServiceEntityList);
	}

	// Get All Customer Invoice List By Date Range
	@Override
	public List<CustomerInvoiceResponseDto> getAllCustomerInvoiceListByDateRange(CustomerInvoiceIncomingDto customerServiceIncomingDto) {
		// TODO Auto-generated method stub
		logger.info("----- CustomerInvoiceServiceImpl getAllCustomerInvoiceListByDateRange ----");
		
		List<CustomerInvoiceEntity> customerInvoiceEntitiesList = customerInvoiceRepository.
				getAllCustomerInvoiceListByPaymentStatusAndDateRange("Payment Completed", customerServiceIncomingDto.getStartDate(),
						customerServiceIncomingDto.getEndDate());
		
		return CustomerInvoiceMapper.toAllCustomerInvoiceResponseDtosList(customerInvoiceEntitiesList);
	}

	@Override
	public CustomerInvoiceEntity getCustomerInvoiceEntityByInvoiceID(String invoiceID) {
		// TODO Auto-generated method stub
		logger.info("----- CustomerInvoiceServiceImpl getCustomerInvoiceEntityByInvoiceID ----");
		
		return customerInvoiceRepository.findById(invoiceID).get();
	}

	@Override
	public List<CustomerInvoiceServiceEntity> getCustomerInvoiceServiceEntityByInvoiceID(String invoiceID) {
		// TODO Auto-generated method stub
		logger.info("----- CustomerInvoiceServiceImpl getAllCustomerInvoiceListByDateRange ----");
		
		CustomerInvoiceEntity customerInvoiceEntity = customerInvoiceRepository.findById(invoiceID).get();
		
		List<CustomerInvoiceServiceEntity> customerInvoiceServiceEntityList = customerInvoiceServiceRepository.
				findByCustomerInvoiceEntity(customerInvoiceEntity);
		
		return customerInvoiceServiceEntityList;
	}
	
	@Override
	public List<CustomerInvoiceEntity> getCustomerInvoiceEntityByCustomerEntity(
			CustomerMasterEntity customerMasterEntity) {
		// TODO Auto-generated method stub
		if( customerMasterEntity == null) {
			throw BRSException.throwException("Error : Customer ID cannot be blank or empty.");
		}
		
		return customerInvoiceRepository.findByCustomerMasterEntity(customerMasterEntity);
	}

	@Override
	public CustomerInvoiceResponseDto getInvoiceMailResponseByInvoiceID(String invoiceID) {
		// TODO Auto-generated method stub
		logger.info("----- CustomerInvoiceServiceImpl getInvoiceMailResponseByInvoiceID ----");
		
		logger.info("_invoice ID :---" + invoiceID);
		
		CustomerInvoiceEntity customerInvoiceEntity = customerInvoiceRepository.findById(invoiceID).get();
		
		if(customerInvoiceEntity == null) {
			throw BRSException.throwException("Error : Customer Invoice Record does not exist.");
		}
		
		CompanyMasterEntity companyMasterEntity = companyMasterService.getCompanyEntityByCompanyName(companyName);
		
		return CustomerInvoiceMapper.toCustomerServiceAndCompanyResponseMailDto(customerInvoiceEntity, companyMasterEntity);
	}


}
