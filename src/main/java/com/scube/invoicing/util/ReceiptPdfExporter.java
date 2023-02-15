package com.scube.invoicing.util;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.borders.SolidBorder;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.HorizontalAlignment;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import com.itextpdf.layout.properties.VerticalAlignment;
import com.scube.invoicing.dto.incoming.CreateInvoiceIncomingDto;
import com.scube.invoicing.entity.CompanyMasterEntity;
import com.scube.invoicing.entity.CustomerCreditNoteDetailsEntity;
import com.scube.invoicing.entity.CustomerCreditNoteEntity;
import com.scube.invoicing.entity.CustomerInvoiceEntity;
import com.scube.invoicing.entity.CustomerInvoiceServiceEntity;
import com.scube.invoicing.entity.CustomerMasterEntity;

@Service
public class ReceiptPdfExporter {

	private static final Logger logger = LoggerFactory.getLogger(ReceiptPdfExporter.class);

	private Path fileStorageLocation;
	private final String fileBaseLocation;

	public ReceiptPdfExporter(FileStorageProperties fileStorageProperties) {
		this.fileBaseLocation = fileStorageProperties.getUploadDir();
	}

	public final static String fileExtension = ".pdf";
	String imageFile = "D:/Sarvesh_Imp/logo.jpg";

	public File generateInvoice(List<CustomerInvoiceServiceEntity> customerInvoiceServiceList,
			CompanyMasterEntity companyMasterEntity, CustomerInvoiceEntity customerInvoiceEntity, 
			CreateInvoiceIncomingDto createInvoiceIncomingDto) throws Exception {

		String filename = "INVOICE_" + new Date().getTime() + "".concat(fileExtension);
		logger.info("Check File Name :--- " + filename);

		String newPath = this.fileBaseLocation + "/" + UploadPathContUtils.FILE_INVOICE_DIR;
		this.fileStorageLocation = Paths.get(newPath).toAbsolutePath().normalize();
		logger.info("File New Path " + newPath);

		Files.createDirectories(this.fileStorageLocation);

		String fileDestination = newPath + "/" + filename;
		logger.info("File Destination " + fileDestination);

		File invoiceFile = Paths.get(fileDestination).toFile();

		PdfWriter writer = new PdfWriter(fileDestination);
		PdfDocument pdfDocument = new PdfDocument(writer);
		Document layoutDocument = new Document(pdfDocument, PageSize.A4);

		ImageData imageData = ImageDataFactory.create(imageFile);
		Image image = new Image(imageData);
		image.scaleAbsolute(50f, 40f);
		image.setTextAlignment(TextAlignment.CENTER);

		Table companyDetailsTable = new Table(UnitValue.createPercentArray(4)).setAutoLayout();

		// companyDataTable cell 1
		companyDetailsTable.addCell(new Cell().setBorder(Border.NO_BORDER).add(image));

		// companyDataTable cell 2
		Cell companyDetailsCell = new Cell(2, 3);
		companyDetailsCell.setBorder(Border.NO_BORDER);
		companyDetailsCell.setTextAlignment(TextAlignment.LEFT);
		companyDetailsCell.setFontSize(8);

		companyDetailsCell.add(new Paragraph(companyMasterEntity.getCompanyName()).setBold());
		companyDetailsCell.add(new Paragraph(companyMasterEntity.getCompanyAddress()).setFontSize(8));
		companyDetailsCell.add(new Paragraph(companyMasterEntity.getCompanyWebsite()).setFontSize(8));
		companyDetailsCell.add(new Paragraph("GSTIN :- " + companyMasterEntity.getCompanyGstin()).setFontSize(8));
		companyDetailsCell.add(new Paragraph("PAN NO :- " + companyMasterEntity.getCompanyPanNo()).setFontSize(8));
		companyDetailsCell.add(new Paragraph("CIN :- " + companyMasterEntity.getCompanyCinNo()).setFontSize(8));

		companyDetailsTable.addCell(companyDetailsCell);
		layoutDocument.add(companyDetailsTable);
		
		layoutDocument.add(new Paragraph());
		layoutDocument.add(new Paragraph());

		Table addLineTable = new Table(UnitValue.createPercentArray(1)).useAllAvailableWidth();

		addLineTable.addCell(new Cell().add(new Paragraph())
				.setBorderTop(new SolidBorder(new DeviceRgb(58,70,109), 1))
				.setBorderLeft(new SolidBorder(new DeviceRgb(255,255,255), 1))
				.setBorderRight(new SolidBorder(new DeviceRgb(255,255,255), 1))
				.setBorderBottom(new SolidBorder(new DeviceRgb(255,255,255), 1))
		);

		layoutDocument.add(addLineTable);
		layoutDocument.add(new Paragraph());
		layoutDocument.add(new Paragraph());

		Table billingAndShippingAndPaymentDateTable = new Table(UnitValue.createPercentArray(5)).useAllAvailableWidth();

		Cell billingDetailsCell = new Cell();
		billingDetailsCell.setBorder(Border.NO_BORDER);
		billingDetailsCell.setFontSize(8);
		billingDetailsCell.setTextAlignment(TextAlignment.LEFT);

		billingDetailsCell.add(new Paragraph("BILL TO").setBold());
		billingDetailsCell.add(new Paragraph(customerInvoiceEntity.getCustomerBillingAddress()));
		billingDetailsCell.add(new Paragraph("State Code : 27"));
		billingDetailsCell.add(new Paragraph("GSTIN :- " + customerInvoiceEntity.getCustomerMasterEntity().getGstin()));
		billingDetailsCell.add(new Paragraph("PLACE OF SUPPLY").setBold());
		billingDetailsCell
				.add(new Paragraph("27 - " + customerInvoiceEntity.getCustomerMasterEntity().getBillingState()));

		billingAndShippingAndPaymentDateTable.addCell(billingDetailsCell);

		Cell shippingDetailsCell = new Cell();
		shippingDetailsCell.setBorder(Border.NO_BORDER);
		shippingDetailsCell.setFontSize(8);
		shippingDetailsCell.setTextAlignment(TextAlignment.LEFT);

		shippingDetailsCell.add(new Paragraph("SHIP TO").setBold());
		shippingDetailsCell.add(new Paragraph(customerInvoiceEntity.getCustomerMasterEntity().getTitle() + " "
				+ customerInvoiceEntity.getCustomerMasterEntity().getFirstName()));
		shippingDetailsCell.add(new Paragraph(customerInvoiceEntity.getCustomerBillingAddress()));
		shippingDetailsCell.add(new Paragraph("State Code : 27"));

		billingAndShippingAndPaymentDateTable.addCell(shippingDetailsCell);

		Cell dateBox1Cell = new Cell();
		dateBox1Cell.setBorder(Border.NO_BORDER);
		dateBox1Cell.setTextAlignment(TextAlignment.CENTER);
		dateBox1Cell.setVerticalAlignment(VerticalAlignment.MIDDLE);
		dateBox1Cell.setFontSize(10);
		dateBox1Cell.setBackgroundColor(new DeviceRgb(215, 215, 230));

		dateBox1Cell.add(new Paragraph("DATE"));
		dateBox1Cell.add(new Paragraph(customerInvoiceEntity.getInvoiceDate().toString()));

		billingAndShippingAndPaymentDateTable.addCell(dateBox1Cell);

		Cell dateBox2Cell = new Cell();
		dateBox2Cell.setBorder(Border.NO_BORDER);
		dateBox2Cell.setTextAlignment(TextAlignment.CENTER);
		dateBox2Cell.setVerticalAlignment(VerticalAlignment.MIDDLE);
		dateBox2Cell.setFontColor(new DeviceRgb(255, 255, 255));
		dateBox2Cell.setFontSize(10);
		dateBox2Cell.setBackgroundColor(new DeviceRgb(46, 35, 85));
		
		dateBox2Cell.add(new Paragraph("PLEASE PAY"));
		dateBox2Cell.add(new Paragraph("INR " + "0.00"));

		billingAndShippingAndPaymentDateTable.addCell(dateBox2Cell);

		Cell dateDateBox3Cell = new Cell();
		dateDateBox3Cell.setBorder(Border.NO_BORDER);
		dateDateBox3Cell.setTextAlignment(TextAlignment.CENTER);
		dateDateBox3Cell.setVerticalAlignment(VerticalAlignment.MIDDLE);
		dateDateBox3Cell.setFontSize(10);
		dateDateBox3Cell.setBackgroundColor(new DeviceRgb(215, 215, 230));

		dateDateBox3Cell.add(new Paragraph("DUE DATE"));
		dateDateBox3Cell.add(new Paragraph(customerInvoiceEntity.getDueDate().toString()));

		billingAndShippingAndPaymentDateTable.addCell(dateDateBox3Cell);

		layoutDocument.add(billingAndShippingAndPaymentDateTable);
		layoutDocument.add(new Paragraph());
		layoutDocument.add(new Paragraph());

		Table customerServiceDataTable = new Table(UnitValue.createPercentArray(6)).useAllAvailableWidth();

		customerServiceDataTable.addCell(new Cell(1, 6).add(new Paragraph())
				.setBorderTop(new SolidBorder(new DeviceRgb(173,173,173), 1))
				.setBorderLeft(new SolidBorder(new DeviceRgb(255,255,255), 1))
				.setBorderRight(new SolidBorder(new DeviceRgb(255,255,255), 1))
				.setBorderBottom(new SolidBorder(new DeviceRgb(255,255,255), 1))
		);

		layoutDocument.add(new Paragraph());

		customerServiceDataTable.addCell(new Cell().add(new Paragraph("NO")).setBorder(Border.NO_BORDER).setBold()
				.setTextAlignment(TextAlignment.LEFT).setFontSize(8));

		customerServiceDataTable.addCell(new Cell().add(new Paragraph("ACTIVITY")).setBorder(Border.NO_BORDER).setBold()
				.setTextAlignment(TextAlignment.CENTER).setFontSize(8));

		customerServiceDataTable.addCell(new Cell().add(new Paragraph("UNIT")).setBorder(Border.NO_BORDER).setBold()
				.setTextAlignment(TextAlignment.RIGHT).setFontSize(8));

		customerServiceDataTable.addCell(new Cell().add(new Paragraph("QTY")).setBorder(Border.NO_BORDER).setBold()
				.setTextAlignment(TextAlignment.RIGHT).setFontSize(8));

		customerServiceDataTable.addCell(new Cell().add(new Paragraph("RATE")).setBorder(Border.NO_BORDER).setBold()
				.setTextAlignment(TextAlignment.RIGHT).setFontSize(8));

		customerServiceDataTable.addCell(new Cell().add(new Paragraph("AMOUNT")).setBorder(Border.NO_BORDER).setBold()
				.setTextAlignment(TextAlignment.RIGHT).setFontSize(8));

		layoutDocument.add(new Paragraph());

		customerServiceDataTable.addCell(new Cell(1, 6).add(new Paragraph())
				.setBorderTop(new SolidBorder(new DeviceRgb(173,173,173), 1))
				.setBorderLeft(new SolidBorder(new DeviceRgb(255,255,255), 1))
				.setBorderRight(new SolidBorder(new DeviceRgb(255,255,255), 1))
				.setBorderBottom(new SolidBorder(new DeviceRgb(255,255,255), 1))
		);

		for (int i = 0; i < customerInvoiceServiceList.size(); i++) {
			customerServiceDataTable.addCell(new Cell().
					add(new Paragraph(String.valueOf(i + 1)))
					.setBorder(Border.NO_BORDER)
					.setFontSize(8)
					.setTextAlignment(TextAlignment.LEFT)
			);
			customerServiceDataTable.addCell(new Cell()
					.add(new Paragraph(customerInvoiceServiceList.get(i).getProductService() + " "
					+ customerInvoiceServiceList.get(i).getDescription()))
					.setBorder(Border.NO_BORDER)
					.setFontSize(8)
					.setTextAlignment(TextAlignment.LEFT)
			);
			customerServiceDataTable.addCell(new Cell()
					.add(new Paragraph(customerInvoiceServiceList.get(i).getSku()))
					.setBorder(Border.NO_BORDER)
					.setFontSize(8)
					.setTextAlignment(TextAlignment.RIGHT)
			);
			customerServiceDataTable.addCell(new Cell()
					.add(new Paragraph(customerInvoiceServiceList.get(i).getQuantity()))
					.setBorder(Border.NO_BORDER)
					.setFontSize(8)
					.setTextAlignment(TextAlignment.RIGHT)
			);
			customerServiceDataTable.addCell(new Cell()
					.add(new Paragraph(String.valueOf(customerInvoiceServiceList.get(i).getRate())))
					.setBorder(Border.NO_BORDER)
					.setFontSize(8)
					.setTextAlignment(TextAlignment.RIGHT)
			);
			customerServiceDataTable.addCell(new Cell()
					.add(new Paragraph(String.valueOf(customerInvoiceServiceList.get(i).getAmount())))
					.setBorder(Border.NO_BORDER)
					.setFontSize(8)
					.setTextAlignment(TextAlignment.RIGHT)
			);
			customerServiceDataTable.addCell(new Cell(1, 6).add(new Paragraph()).setBorder(Border.NO_BORDER));
			customerServiceDataTable.addCell(new Cell(1, 6).add(new Paragraph()).setBorder(Border.NO_BORDER));
		}
		
		customerServiceDataTable.addCell(new Cell(1, 6).add(new Paragraph())
				.setBorderTop(new SolidBorder(new DeviceRgb(173,173,173), 1))
				.setBorderLeft(new SolidBorder(new DeviceRgb(255,255,255), 1))
				.setBorderRight(new SolidBorder(new DeviceRgb(255,255,255), 1))
				.setBorderBottom(new SolidBorder(new DeviceRgb(255,255,255), 1))
		);

		layoutDocument.add(customerServiceDataTable);
		layoutDocument.add(new Paragraph());

		Table bankDetailsTable = new Table(UnitValue.createPercentArray(7)).useAllAvailableWidth();

		bankDetailsTable.addCell(new Cell(1,3).add(new Paragraph("Bank Details."))
					.setBorder(Border.NO_BORDER)
					.setFontSize(8)
					.setTextAlignment(TextAlignment.LEFT)
					.setHorizontalAlignment(HorizontalAlignment.LEFT)
				);
		bankDetailsTable.addCell(new Cell(1,2).add(new Paragraph("SUBTOTAL"))
				.setBorder(Border.NO_BORDER)
				.setFontSize(8)
				.setTextAlignment(TextAlignment.LEFT)
				.setHorizontalAlignment(HorizontalAlignment.LEFT)
				.setFontColor(new DeviceRgb(58,70,109))
			);
		bankDetailsTable.addCell(new Cell(1,2).add(new Paragraph(String.valueOf(createInvoiceIncomingDto.getSubTotal())))
				.setBorder(Border.NO_BORDER)
				.setFontSize(8)
				.setTextAlignment(TextAlignment.RIGHT)
				.setHorizontalAlignment(HorizontalAlignment.RIGHT)
				.setFontColor(new DeviceRgb(58,70,109))
			);
		
		bankDetailsTable.addCell(new Cell(1,3).add(new Paragraph("Beneficiary : " + companyMasterEntity.getBeneficiary()))
				.setBorder(Border.NO_BORDER)
				.setFontSize(8)
				.setTextAlignment(TextAlignment.LEFT)
				.setHorizontalAlignment(HorizontalAlignment.LEFT)
			);
		bankDetailsTable.addCell(new Cell(1,2).add(new Paragraph("CGST @ 9% on "))
			.setBorder(Border.NO_BORDER)
			.setFontSize(8)
			.setTextAlignment(TextAlignment.LEFT)
			.setHorizontalAlignment(HorizontalAlignment.LEFT)
			.setFontColor(new DeviceRgb(58,70,109))
		);
		bankDetailsTable.addCell(new Cell(1,2).add(new Paragraph(String.valueOf(createInvoiceIncomingDto.getCgstAmount())))
			.setBorder(Border.NO_BORDER)
			.setFontSize(8)
			.setTextAlignment(TextAlignment.RIGHT)
			.setHorizontalAlignment(HorizontalAlignment.RIGHT)
			.setFontColor(new DeviceRgb(58,70,109))
		);
		
		bankDetailsTable.addCell(new Cell(1,3).add(new Paragraph("Bank : " + companyMasterEntity.getBankName()))
				.setBorder(Border.NO_BORDER)
				.setFontSize(8)
				.setTextAlignment(TextAlignment.LEFT)
				.setHorizontalAlignment(HorizontalAlignment.LEFT)
			);
		bankDetailsTable.addCell(new Cell(1,2).add(new Paragraph("SGST @ 9% on "))
			.setBorder(Border.NO_BORDER)
			.setFontSize(8)
			.setTextAlignment(TextAlignment.LEFT)
			.setHorizontalAlignment(HorizontalAlignment.LEFT)
			.setFontColor(new DeviceRgb(58,70,109))
		);
		bankDetailsTable.addCell(new Cell(1,2).add(new Paragraph(String.valueOf(createInvoiceIncomingDto.getSgstAmount())))
			.setBorder(Border.NO_BORDER)
			.setFontSize(8)
			.setTextAlignment(TextAlignment.RIGHT)
			.setHorizontalAlignment(HorizontalAlignment.RIGHT)
			.setFontColor(new DeviceRgb(58,70,109))
		);
		
		bankDetailsTable.addCell(new Cell(1,3).add(new Paragraph("Account No : " + companyMasterEntity.getCompanyBankAccNo()))
				.setBorder(Border.NO_BORDER)
				.setFontSize(8)
				.setTextAlignment(TextAlignment.LEFT)
				.setHorizontalAlignment(HorizontalAlignment.LEFT)
			);
		bankDetailsTable.addCell(new Cell(1,2).add(new Paragraph("TOTAL"))
			.setBorder(Border.NO_BORDER)
			.setFontSize(8)
			.setTextAlignment(TextAlignment.LEFT)
			.setHorizontalAlignment(HorizontalAlignment.LEFT)
			.setFontColor(new DeviceRgb(58,70,109))
		);
		bankDetailsTable.addCell(new Cell(1,2).add(new Paragraph(String.valueOf(createInvoiceIncomingDto.getTotalAmount())))
			.setBorder(Border.NO_BORDER)
			.setFontSize(8)
			.setTextAlignment(TextAlignment.RIGHT)
			.setHorizontalAlignment(HorizontalAlignment.RIGHT)
			.setFontColor(new DeviceRgb(58,70,109))
		);
		
		bankDetailsTable.addCell(new Cell(1,3).add(new Paragraph("Address : " + companyMasterEntity.getBankAddress()))
				.setBorder(Border.NO_BORDER)
				.setFontSize(8)
				.setTextAlignment(TextAlignment.LEFT)
				.setHorizontalAlignment(HorizontalAlignment.LEFT)
			);
		bankDetailsTable.addCell(new Cell(1,2).add(new Paragraph())
			.setBorder(Border.NO_BORDER)
		);
		bankDetailsTable.addCell(new Cell(1,2).add(new Paragraph())
			.setBorder(Border.NO_BORDER)
		);
		
		bankDetailsTable.addCell(new Cell(1,3).add(new Paragraph("SWIFT CODE : " + companyMasterEntity.getSwiftCode()))
				.setBorder(Border.NO_BORDER)
				.setFontSize(8)
				.setTextAlignment(TextAlignment.LEFT)
				.setHorizontalAlignment(HorizontalAlignment.LEFT)
			);
		bankDetailsTable.addCell(new Cell(1,2).add(new Paragraph())
				.setBorder(Border.NO_BORDER));
		bankDetailsTable.addCell(new Cell(1,2).add(new Paragraph())
				.setBorder(Border.NO_BORDER));
		
		bankDetailsTable.addCell(new Cell(1,3).add(new Paragraph())
				.setBorder(Border.NO_BORDER));
		bankDetailsTable.addCell(new Cell(1,4).add(new Paragraph())
				.setBorderTop(new SolidBorder(new DeviceRgb(58,70,109), 1))
				.setBorderLeft(new SolidBorder(new DeviceRgb(255,255,255), 1))
				.setBorderRight(new SolidBorder(new DeviceRgb(255,255,255), 1))
				.setBorderBottom(new SolidBorder(new DeviceRgb(255,255,255), 1))
		);
		
		bankDetailsTable.addCell(new Cell(1,3).add(new Paragraph())
				.setBorder(Border.NO_BORDER));
		bankDetailsTable.addCell(new Cell(1,2).add(new Paragraph("TOTAL DUE"))
				.setBorder(Border.NO_BORDER)
				.setBold()
				.setFontSize(10)
				.setTextAlignment(TextAlignment.LEFT)
				.setHorizontalAlignment(HorizontalAlignment.LEFT)
				.setFontColor(DeviceRgb.makeLighter(new DeviceRgb(32,46,90))));
		bankDetailsTable.addCell(new Cell(1,2).add(new Paragraph("INR " + String.valueOf(createInvoiceIncomingDto.getTotalDueAmount())))
				.setBorder(Border.NO_BORDER)
				.setBold()
				.setFontSize(10)
				.setTextAlignment(TextAlignment.RIGHT)
				.setHorizontalAlignment(HorizontalAlignment.RIGHT)
				.setFontColor(DeviceRgb.makeLighter(new DeviceRgb(32,46,90))));
		
		bankDetailsTable.addCell(new Cell(1,3).add(new Paragraph())
				.setBorder(Border.NO_BORDER));
		bankDetailsTable.addCell(new Cell(1,4).add(new Paragraph())
				.setBorderTop(new SolidBorder(new DeviceRgb(58,70,109), 1))
				.setBorderLeft(new SolidBorder(new DeviceRgb(255,255,255), 1))
				.setBorderRight(new SolidBorder(new DeviceRgb(255,255,255), 1))
				.setBorderBottom(new SolidBorder(new DeviceRgb(255,255,255), 1))
		);
		
		layoutDocument.add(bankDetailsTable);	
		layoutDocument.add(new Paragraph());
		layoutDocument.add(new Paragraph());
		
		Table companyContactDetailsTable = new Table(UnitValue.createPercentArray(1)).useAllAvailableWidth();
		
		Cell companyContactDetailsCell = new Cell();
		companyContactDetailsCell.setFontSize(8);
		companyContactDetailsCell.setHorizontalAlignment(HorizontalAlignment.LEFT);
		companyContactDetailsCell.setBorder(Border.NO_BORDER);
		companyContactDetailsCell.setTextAlignment(TextAlignment.LEFT);
		
		companyContactDetailsCell.add(new Paragraph(companyMasterEntity.getCompanyName() + " Contact Details : "));
		companyContactDetailsCell.add(new Paragraph("Name : " + companyMasterEntity.getName()));
		companyContactDetailsCell.add(new Paragraph("Email ID : " + companyMasterEntity.getEmailId()));
		companyContactDetailsCell.add(new Paragraph("Phone : " + companyMasterEntity.getContactNo()));
		
		companyContactDetailsTable.addCell(companyContactDetailsCell);
		layoutDocument.add(companyContactDetailsTable);
		layoutDocument.add(new Paragraph());
		layoutDocument.add(new Paragraph());
		layoutDocument.add(new Paragraph());
		layoutDocument.add(new Paragraph());
		
		Table signatureDetailsTable = new Table(UnitValue.createPercentArray(4)).useAllAvailableWidth();
		
		signatureDetailsTable.addCell(new Cell(1,3).add(new Paragraph()).setBorder(Border.NO_BORDER));
		signatureDetailsTable.addCell(new Cell().add(new Paragraph())
				.setBorderTop(new SolidBorder(new DeviceRgb(0,0,0), 1))
				.setBorderLeft(new SolidBorder(new DeviceRgb(255,255,255), 1))
				.setBorderRight(new SolidBorder(new DeviceRgb(255,255,255), 1))
				.setBorderBottom(new SolidBorder(new DeviceRgb(255,255,255), 1))
		);
		
		signatureDetailsTable.addCell(new Cell(1,3).add(new Paragraph()).setBorder(Border.NO_BORDER));
		signatureDetailsTable.addCell(new Cell().add(new Paragraph("Authorized Signatory"))
				.setBorder(Border.NO_BORDER)
				.setFontSize(8)
				.setHorizontalAlignment(HorizontalAlignment.RIGHT)
				.setTextAlignment(TextAlignment.RIGHT));
		
		signatureDetailsTable.addCell(new Cell(1,4).add(new Paragraph()).setBorder(Border.NO_BORDER));
		
		signatureDetailsTable.addCell(new Cell().add(new Paragraph()).setBorder(Border.NO_BORDER));
		signatureDetailsTable.addCell(new Cell(1,3).add(new Paragraph(companyMasterEntity.getCompanyName()))
				.setBorder(Border.NO_BORDER)
				.setFontSize(8)
				.setHorizontalAlignment(HorizontalAlignment.RIGHT)
				.setTextAlignment(TextAlignment.RIGHT));		
		
		layoutDocument.add(signatureDetailsTable);

		layoutDocument.close();

		return invoiceFile;
	}
	
	
	public File generateCreditNote(List<CustomerCreditNoteDetailsEntity> creditNoteDetailsEntities, CustomerCreditNoteEntity customerCreditNoteEntity,
			CompanyMasterEntity companyMasterEntity, CustomerMasterEntity customerMasterEntity, 
			CreateInvoiceIncomingDto createInvoiceIncomingDto) throws Exception {
		
		logger.info("--------- ReceiptPdfExporter generateCreditNote -----------");
		
		String filename = "CREDIT_NOTE_" + new Date().getTime() + "".concat(fileExtension);
		logger.info("Check File Name :--- " + filename);
		
		String newPath = this.fileBaseLocation + "/" + UploadPathContUtils.FILE_CREDIT_NOTE_DIR;
		this.fileStorageLocation = Paths.get(newPath).toAbsolutePath().normalize();
		logger.info("File New Path " + newPath);

		Files.createDirectories(this.fileStorageLocation);

		String fileDestination = newPath + "/" + filename;
		logger.info("File Destination " + fileDestination);
		
		File creditNoteFile = Paths.get(fileDestination).toFile();

		PdfWriter writer = new PdfWriter(fileDestination);
		PdfDocument pdfDocument = new PdfDocument(writer);
		Document layoutDocument = new Document(pdfDocument, PageSize.A4);

		ImageData imageData = ImageDataFactory.create(imageFile);
		Image image = new Image(imageData);
		image.scaleAbsolute(50f, 40f);
		image.setTextAlignment(TextAlignment.CENTER);
		
		// Logo and Header Credit Note Table
		Table logoAndHeaderTable = new Table(UnitValue.createPercentArray(4)).useAllAvailableWidth();
		
		logoAndHeaderTable.addCell(new Cell().add(image)
				.setBorder(Border.NO_BORDER)
				.setHorizontalAlignment(HorizontalAlignment.LEFT)
		);
		
		logoAndHeaderTable.addCell(new Cell(1,3).add(new Paragraph("CREDIT NOTE"))
				.setBorder(Border.NO_BORDER)
				.setFontSize(25)
				.setTextAlignment(TextAlignment.RIGHT)
				.setHorizontalAlignment(HorizontalAlignment.RIGHT)
		);
		
		logoAndHeaderTable.addCell(new Cell(1,4).add(new Paragraph(customerCreditNoteEntity.getCreditNoteNo()))
				.setBorder(Border.NO_BORDER)
				.setFontSize(10)
				.setTextAlignment(TextAlignment.RIGHT)
				.setHorizontalAlignment(HorizontalAlignment.RIGHT)
		);
		
		layoutDocument.add(logoAndHeaderTable);
		layoutDocument.add(new Paragraph());
		layoutDocument.add(new Paragraph());
		
		// Company and Customer And Credit Remaining Table
		Table companyAndCustomerAndCreditsTable = new Table(UnitValue.createPercentArray(6)).useAllAvailableWidth();
		
		companyAndCustomerAndCreditsTable.addCell(new Cell(1,3).add(new Paragraph(companyMasterEntity.getCompanyName()))
				.setBorder(Border.NO_BORDER)
				.setBold()
				.setFontSize(8)
				.setTextAlignment(TextAlignment.LEFT)
		);
		
		companyAndCustomerAndCreditsTable.addCell(new Cell(1,3).add(new Paragraph()).setBorder(Border.NO_BORDER));
		
		companyAndCustomerAndCreditsTable.addCell(new Cell(1,2).add(new Paragraph(companyMasterEntity.getCompanyAddress()))
				.setBorder(Border.NO_BORDER)
				.setFontSize(8)
				.setTextAlignment(TextAlignment.LEFT)
		);
		companyAndCustomerAndCreditsTable.addCell(new Cell(1,4).add(new Paragraph()).setBorder(Border.NO_BORDER));
		
		companyAndCustomerAndCreditsTable.addCell(new Cell(1,4).add(new Paragraph()).setBorder(Border.NO_BORDER));
		companyAndCustomerAndCreditsTable.addCell(new Cell(1,2).add(new Paragraph("CREDITS REMAINING" + "\n"
				+ "INR " + createInvoiceIncomingDto.getCreditsRemaining()))
				.setHeight(50)
				.setBorder(new SolidBorder(new DeviceRgb(253,118,49), 1))
				.setWidth(5)
				.setFontSize(8)
				.setBackgroundColor(new DeviceRgb(247,247,247))
				.setVerticalAlignment(VerticalAlignment.MIDDLE)
				.setTextAlignment(TextAlignment.CENTER)
		);
		
		companyAndCustomerAndCreditsTable.addCell(new Cell(1,2).add(new Paragraph("GSTIN :- " + companyMasterEntity.getCompanyGstin()))
				.setBorder(Border.NO_BORDER)
				.setTextAlignment(TextAlignment.LEFT)
				.setFontSize(8)
		);
		companyAndCustomerAndCreditsTable.addCell(new Cell(1,4).add(new Paragraph()).setBorder(Border.NO_BORDER));
		
		companyAndCustomerAndCreditsTable.addCell(new Cell(1,2).add(new Paragraph("Phone :- " + companyMasterEntity.getContactNo()))
				.setBorder(Border.NO_BORDER)
				.setTextAlignment(TextAlignment.LEFT)
				.setFontSize(8)
		);
		companyAndCustomerAndCreditsTable.addCell(new Cell(1,4).add(new Paragraph()).setBorder(Border.NO_BORDER));
		
		companyAndCustomerAndCreditsTable.addCell(new Cell(1,2).add(new Paragraph("PAN :- " + companyMasterEntity.getCompanyPanNo()))
				.setBorder(Border.NO_BORDER)
				.setTextAlignment(TextAlignment.LEFT)
				.setFontSize(8)
		);
		companyAndCustomerAndCreditsTable.addCell(new Cell(1,4).add(new Paragraph()).setBorder(Border.NO_BORDER));
		
		companyAndCustomerAndCreditsTable.addCell(new Cell(1,6).add(new Paragraph()).setBorder(Border.NO_BORDER));
		companyAndCustomerAndCreditsTable.addCell(new Cell(1,6).add(new Paragraph()).setBorder(Border.NO_BORDER));
		companyAndCustomerAndCreditsTable.addCell(new Cell(1,6).add(new Paragraph()).setBorder(Border.NO_BORDER));
		
		companyAndCustomerAndCreditsTable.addCell(new Cell(1,2).add(new Paragraph("Bill To "))
				.setBorder(Border.NO_BORDER)
				.setBold()
				.setFontSize(8)
				.setTextAlignment(TextAlignment.LEFT)
		);
		companyAndCustomerAndCreditsTable.addCell(new Cell(1,4).add(new Paragraph()).setBorder(Border.NO_BORDER));
		
		companyAndCustomerAndCreditsTable.addCell(new Cell(1,2).add(new Paragraph(customerMasterEntity.getCompanyName()))
				.setBorder(Border.NO_BORDER)
				.setFontSize(8)
				.setTextAlignment(TextAlignment.LEFT)
		);
		companyAndCustomerAndCreditsTable.addCell(new Cell(1,4).add(new Paragraph()).setBorder(Border.NO_BORDER));
		
		companyAndCustomerAndCreditsTable.addCell(new Cell(1,2).add(new Paragraph(customerMasterEntity.getBillingAddress() +  " " + 
				customerMasterEntity.getBillingCity() + " " + customerMasterEntity.getBillingPinCode()
				+ " " + customerMasterEntity.getBillingState() + " " + customerMasterEntity.getBillingCountry()))
				.setBorder(Border.NO_BORDER)
				.setFontSize(8)
				.setTextAlignment(TextAlignment.LEFT)
		);
		companyAndCustomerAndCreditsTable.addCell(new Cell(1,4).add(new Paragraph()).setBorder(Border.NO_BORDER));
		
		companyAndCustomerAndCreditsTable.addCell(new Cell(1,6).add(new Paragraph()).setBorder(Border.NO_BORDER));
		companyAndCustomerAndCreditsTable.addCell(new Cell(1,6).add(new Paragraph()).setBorder(Border.NO_BORDER));
		
		companyAndCustomerAndCreditsTable.addCell(new Cell(1,2).add(new Paragraph("GSTIN :- " + customerMasterEntity.getGstin()))
				.setBorder(Border.NO_BORDER)
				.setFontSize(8)
				.setTextAlignment(TextAlignment.LEFT)
		);
		companyAndCustomerAndCreditsTable.addCell(new Cell(1,4).add(new Paragraph()).setBorder(Border.NO_BORDER));
		
		companyAndCustomerAndCreditsTable.addCell(new Cell(1,2).add(new Paragraph("Phone :- " + customerMasterEntity.getMobileNumber()))
				.setBorder(Border.NO_BORDER)
				.setFontSize(8)
				.setTextAlignment(TextAlignment.LEFT)
		);
		companyAndCustomerAndCreditsTable.addCell(new Cell(1,4).add(new Paragraph()).setBorder(Border.NO_BORDER));
		
		companyAndCustomerAndCreditsTable.addCell(new Cell(1,2).add(new Paragraph("PAN :- " + customerMasterEntity.getPanNo()))
				.setBorder(Border.NO_BORDER)
				.setFontSize(8)
				.setTextAlignment(TextAlignment.LEFT)
		);
		companyAndCustomerAndCreditsTable.addCell(new Cell(1,4).add(new Paragraph()).setBorder(Border.NO_BORDER));
		
		companyAndCustomerAndCreditsTable.addCell(new Cell(1,6).add(new Paragraph("Credit Date :      " + 
				DateUtils.formatDateToDDMMYYYYFormat(customerCreditNoteEntity.getCreditNoteDate())))
				.setBorder(Border.NO_BORDER)
				.setFontSize(8)
				.setTextAlignment(TextAlignment.RIGHT)
		);
		
		companyAndCustomerAndCreditsTable.addCell(new Cell(1,6).add(new Paragraph("Place Of Supply :      " + customerMasterEntity.getBillingState()))
				.setBorder(Border.NO_BORDER)
				.setFontSize(8)
				.setTextAlignment(TextAlignment.LEFT)
		);
		
		layoutDocument.add(companyAndCustomerAndCreditsTable);
		layoutDocument.add(new Paragraph());
		layoutDocument.add(new Paragraph());
		
		Table creditNoteDetailsTable = new Table(UnitValue.createPercentArray(7)).useAllAvailableWidth();
		
		creditNoteDetailsTable.addCell(new Cell().add(new Paragraph("#"))
				.setBorder(Border.NO_BORDER)
				.setFontSize(8)
				.setFontColor(new DeviceRgb(255,255,255))
				.setHorizontalAlignment(HorizontalAlignment.LEFT)
				.setTextAlignment(TextAlignment.LEFT)
				.setBackgroundColor(new DeviceRgb(60,61,58))
		);
		
		creditNoteDetailsTable.addCell(new Cell(1,3).add(new Paragraph("Items & Description"))
				.setBorder(Border.NO_BORDER)
				.setFontSize(8)
				.setFontColor(new DeviceRgb(255,255,255))
				.setHorizontalAlignment(HorizontalAlignment.LEFT)
				.setTextAlignment(TextAlignment.LEFT)
				.setBackgroundColor(new DeviceRgb(60,61,58))
		);
		
		creditNoteDetailsTable.addCell(new Cell().add(new Paragraph("Qty"))
				.setBorder(Border.NO_BORDER)
				.setFontSize(8)
				.setFontColor(new DeviceRgb(255,255,255))
				.setHorizontalAlignment(HorizontalAlignment.CENTER)
				.setTextAlignment(TextAlignment.CENTER)
				.setBackgroundColor(new DeviceRgb(60,61,58))
		);
		
		creditNoteDetailsTable.addCell(new Cell().add(new Paragraph("Rate"))
				.setBorder(Border.NO_BORDER)
				.setFontSize(8)
				.setFontColor(new DeviceRgb(255,255,255))
				.setHorizontalAlignment(HorizontalAlignment.CENTER)
				.setTextAlignment(TextAlignment.CENTER)
				.setBackgroundColor(new DeviceRgb(60,61,58))
		);
		
		creditNoteDetailsTable.addCell(new Cell().add(new Paragraph("Amount"))
				.setBorder(Border.NO_BORDER)
				.setFontSize(8)
				.setFontColor(new DeviceRgb(255,255,255))
				.setHorizontalAlignment(HorizontalAlignment.RIGHT)
				.setTextAlignment(TextAlignment.RIGHT)
				.setBackgroundColor(new DeviceRgb(60,61,58))
		);
		
		for(int i=0; i<creditNoteDetailsEntities.size(); i++) {
			creditNoteDetailsTable.addCell(new Cell().add(new Paragraph(String.valueOf(i+1)))
					.setBorder(Border.NO_BORDER)
					.setFontSize(8)
					.setHorizontalAlignment(HorizontalAlignment.LEFT)
					.setTextAlignment(TextAlignment.LEFT)
			);
			
			creditNoteDetailsTable.addCell(new Cell(1,3).add(new Paragraph(creditNoteDetailsEntities.get(i).getItemDescription()))
					.setBorder(Border.NO_BORDER)
					.setFontSize(8)
					.setHorizontalAlignment(HorizontalAlignment.LEFT)
					.setTextAlignment(TextAlignment.LEFT)
			);
			
			creditNoteDetailsTable.addCell(new Cell().add(new Paragraph(String.valueOf(creditNoteDetailsEntities.get(i).getQuantity())))
					.setBorder(Border.NO_BORDER)
					.setFontSize(8)
					.setHorizontalAlignment(HorizontalAlignment.CENTER)
					.setTextAlignment(TextAlignment.CENTER)
			);
			
			creditNoteDetailsTable.addCell(new Cell().add(new Paragraph(String.valueOf(creditNoteDetailsEntities.get(i).getRate())))
					.setBorder(Border.NO_BORDER)
					.setFontSize(8)
					.setHorizontalAlignment(HorizontalAlignment.CENTER)
					.setTextAlignment(TextAlignment.CENTER)
			);
			
			creditNoteDetailsTable.addCell(new Cell().add(new Paragraph(String.valueOf(creditNoteDetailsEntities.get(i).getAmount())))
					.setBorder(Border.NO_BORDER)
					.setFontSize(8)
					.setHorizontalAlignment(HorizontalAlignment.RIGHT)
					.setTextAlignment(TextAlignment.RIGHT)
			);
		}
		layoutDocument.add(new Paragraph());
		
		creditNoteDetailsTable.addCell(new Cell(1,7).add(new Paragraph()).setBorder(Border.NO_BORDER));
		creditNoteDetailsTable.addCell(new Cell(1,7).add(new Paragraph()).setBorder(Border.NO_BORDER));
		creditNoteDetailsTable.addCell(new Cell(1,7).add(new Paragraph())
				.setBorderTop(new SolidBorder(new DeviceRgb(173,173,173), 1))
				.setBorderLeft(new SolidBorder(new DeviceRgb(255,255,255), 1))
				.setBorderRight(new SolidBorder(new DeviceRgb(255,255,255), 1))
				.setBorderBottom(new SolidBorder(new DeviceRgb(255,255,255), 1))
		);
		
		layoutDocument.add(creditNoteDetailsTable);
		layoutDocument.add(new Paragraph());
		layoutDocument.add(new Paragraph());
		
		Table amountDetailsTable = new Table(UnitValue.createPercentArray(7)).useAllAvailableWidth();
		
		amountDetailsTable.addCell(new Cell(1,3).add(new Paragraph()).setBorder(Border.NO_BORDER));
		amountDetailsTable.addCell(new Cell(1,2).add(new Paragraph("Sub Total "))
				.setBorder(Border.NO_BORDER)
				.setFontSize(8)
				.setTextAlignment(TextAlignment.RIGHT)
				.setHorizontalAlignment(HorizontalAlignment.RIGHT)
		);
		amountDetailsTable.addCell(new Cell(1,2).add(new Paragraph(String.valueOf(createInvoiceIncomingDto.getSubTotal())))
				.setBorder(Border.NO_BORDER)
				.setFontSize(8)
				.setTextAlignment(TextAlignment.RIGHT)
				.setHorizontalAlignment(HorizontalAlignment.RIGHT)
		);
		
		amountDetailsTable.addCell(new Cell(1,3).add(new Paragraph()).setBorder(Border.NO_BORDER));
		amountDetailsTable.addCell(new Cell(1,2).add(new Paragraph("CGST "))
				.setBorder(Border.NO_BORDER)
				.setFontSize(8)
				.setTextAlignment(TextAlignment.RIGHT)
				.setHorizontalAlignment(HorizontalAlignment.RIGHT)
		);
		amountDetailsTable.addCell(new Cell(1,2).add(new Paragraph(String.valueOf(createInvoiceIncomingDto.getCgstAmount())))
				.setBorder(Border.NO_BORDER)
				.setFontSize(8)
				.setTextAlignment(TextAlignment.RIGHT)
				.setHorizontalAlignment(HorizontalAlignment.RIGHT)
		);
		
		amountDetailsTable.addCell(new Cell(1,3).add(new Paragraph()).setBorder(Border.NO_BORDER));
		amountDetailsTable.addCell(new Cell(1,2).add(new Paragraph("SGST "))
				.setBorder(Border.NO_BORDER)
				.setFontSize(8)
				.setTextAlignment(TextAlignment.RIGHT)
				.setHorizontalAlignment(HorizontalAlignment.RIGHT)
		);
		amountDetailsTable.addCell(new Cell(1,2).add(new Paragraph(String.valueOf(createInvoiceIncomingDto.getSgstAmount())))
				.setBorder(Border.NO_BORDER)
				.setFontSize(8)
				.setTextAlignment(TextAlignment.RIGHT)
				.setHorizontalAlignment(HorizontalAlignment.RIGHT)
		);
		
		amountDetailsTable.addCell(new Cell(1,3).add(new Paragraph()).setBorder(Border.NO_BORDER));
		amountDetailsTable.addCell(new Cell(1,2).add(new Paragraph("Total"))
				.setBorder(Border.NO_BORDER)
				.setFontSize(8)
				.setTextAlignment(TextAlignment.RIGHT)
				.setHorizontalAlignment(HorizontalAlignment.RIGHT)
		);
		amountDetailsTable.addCell(new Cell(1,2).add(new Paragraph(String.valueOf(createInvoiceIncomingDto.getTotalAmount())))
				.setBorder(Border.NO_BORDER)
				.setFontSize(8)
				.setTextAlignment(TextAlignment.RIGHT)
				.setHorizontalAlignment(HorizontalAlignment.RIGHT)
		);
		
		amountDetailsTable.addCell(new Cell(1,7).add(new Paragraph()).setBorder(Border.NO_BORDER));
		amountDetailsTable.addCell(new Cell(1,7).add(new Paragraph()).setBorder(Border.NO_BORDER));
		
		amountDetailsTable.addCell(new Cell(1,3).add(new Paragraph()).setBorder(Border.NO_BORDER));
		amountDetailsTable.addCell(new Cell(1,2).add(new Paragraph("Credits Remaining "))
				.setBorder(Border.NO_BORDER)
				.setFontSize(8)
				.setHeight(20)
				.setBackgroundColor(new DeviceRgb(245,244,243))
				.setTextAlignment(TextAlignment.RIGHT)
				.setVerticalAlignment(VerticalAlignment.MIDDLE)
				.setHorizontalAlignment(HorizontalAlignment.RIGHT)
		);
		amountDetailsTable.addCell(new Cell(1,2).add(new Paragraph("INR " + String.valueOf(createInvoiceIncomingDto.getCreditsRemaining())))
				.setBorder(Border.NO_BORDER)
				.setFontSize(8)
				.setHeight(20)
				.setBackgroundColor(new DeviceRgb(245,244,243))
				.setTextAlignment(TextAlignment.RIGHT)
				.setVerticalAlignment(VerticalAlignment.MIDDLE)
				.setHorizontalAlignment(HorizontalAlignment.RIGHT)
		);
		
		layoutDocument.add(amountDetailsTable);
		layoutDocument.add(new Paragraph());
		layoutDocument.add(new Paragraph());
		layoutDocument.close();
		
		return creditNoteFile;
	}
}
