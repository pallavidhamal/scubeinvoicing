package com.scube.invoicing.service;


import java.io.File;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.scube.invoicing.dto.incoming.CreateInvoiceIncomingDto;
import com.scube.invoicing.entity.CheckCreditNoteMailStatusEntity;
import com.scube.invoicing.entity.CheckInvoiceMailStatusEntity;
import com.scube.invoicing.entity.CustomerCreditNoteEntity;
import com.scube.invoicing.entity.CustomerInvoiceEntity;
import com.scube.invoicing.repository.CheckCreditNoteMailStatusRepository;
import com.scube.invoicing.repository.CheckInvoiceMailStatusRepository;


@Service
public class EmailService {
	
	@Value ("${mail.from.id}")
	private String fromMailId;
	
	@Value ("${mail.from.pwd}")
	private String fromMailPwd;
	
	@Value ("${mail.host}")
	private String fromMailIdHost;
	
	@Value ("${mail.receipient.id}")
	private String receipientMailId;
	
	@Autowired
	CheckInvoiceMailStatusRepository checkMailStatusRepository;
	
	@Autowired
	CheckCreditNoteMailStatusRepository checkCreditNoteMailStatusRepository;
	
	
	private static final Logger logger = LoggerFactory.getLogger(EmailService.class);
	
	public void sendMailWithAttachment(File rejectedFileName, File acceptedFileName) throws Exception {
		
		logger.info("---- EmailService sendMailWithAttachment ---- ");
		
		logger.info("Acccepted File Path " + acceptedFileName);
		
		String withoutExtensionFileName = FilenameUtils.removeExtension(acceptedFileName.getName());
		
		logger.info("File Name without extension : " + withoutExtensionFileName);
		
		StringBuffer failureReason = null;
		
		String host = fromMailIdHost;
		
		Properties properties = System.getProperties();
		
		logger.info("Properties are ------" + properties);
		
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", "465");
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.smtp.auth", "true");
		
		String mailTextContent = "Dear Team, \r\r " +
							" PFA Rejected Records for " + withoutExtensionFileName + "\r\r " + 
							"Inventory Management Team ";
		
		String subjectLine = "Rejected Records";
		
		Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
			
			protected PasswordAuthentication getPasswordAuthentication() {				
				
				return new PasswordAuthentication(fromMailId, fromMailPwd);
	
			}		
			
		});
		
		session.setDebug(true);
		
		try {
			
			/*
			 * MimeMessage mimeMessage = new MimeMessage(session);
			 * 
			 * MimeBodyPart textBodyPart = new MimeBodyPart();
			 * textBodyPart.setText(mailTextContent);
			 * 
			 * MimeMultipart mimeMultipart = new MimeMultipart();
			 * mimeMultipart.addBodyPart(textBodyPart);
			 * 
			 * mimeMessage.saveChanges();
			 * 
			 * Message message = new MimeMessage(session); message.setFrom(new
			 * InternetAddress(fromMailId));
			 * 
			 * MimeBodyPart fileMimeBodyPart = new MimeBodyPart();
			 * fileMimeBodyPart.attachFile(rejectedFileName);
			 * 
			 * Multipart multipart = new MimeMultipart();
			 * multipart.addBodyPart(textBodyPart); multipart.addBodyPart(fileMimeBodyPart);
			 * 
			 * message.setContent(multipart);
			 * 
			 * InternetAddress internetFromMailAddress = new InternetAddress(fromMailId);
			 * InternetAddress internetReceipientMailAddress = new
			 * InternetAddress(receipientMailId);
			 * 
			 * mimeMessage.setSender(internetFromMailAddress);
			 * message.setSubject(subjectLine);
			 * 
			 * message.addRecipients(Message.RecipientType.TO,
			 * InternetAddress.parse(receipientMailId));
			 * 
			 * mimeMessage.setRecipient(Message.RecipientType.TO,
			 * internetReceipientMailAddress); mimeMessage.setContent(mimeMultipart);
			 * 
			 * logger.info("------------" + "Sending" + "---------------");
			 * 
			 * Transport.send(message);
			 * 
			 * logger.info("Mail Sent Successfully...................");
			 */
			
			MimeMessage mimeMessage = new MimeMessage(session);
			
			MimeBodyPart textBodyPart = new MimeBodyPart();			
			textBodyPart.setText(mailTextContent);
			
			MimeMultipart mimeMultipart = new MimeMultipart();			
			mimeMultipart.addBodyPart(textBodyPart);
			
			mimeMessage.setFrom(new InternetAddress(fromMailId));
			
			
			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(textBodyPart);
			
			mimeMessage.setContent(multipart);
			
			InternetAddress internetFromMailAddress = new InternetAddress(fromMailId);
			
			mimeMessage.setSender(internetFromMailAddress);
			mimeMessage.setSubject(subjectLine);
			
			mimeMessage.addRecipients(Message.RecipientType.TO, 
                    InternetAddress.parse(receipientMailId));
			
			MimeBodyPart fileMimeBodyPart = new MimeBodyPart();
			fileMimeBodyPart.attachFile(rejectedFileName);
			multipart.addBodyPart(fileMimeBodyPart);
			
			
			mimeMessage.setContent(multipart);
			
			logger.info("------------" + "Sending" + "---------------");
			
			Transport.send(mimeMessage);
			
			logger.info("Mail Sent Successfully...................");
			
			
			
			
			
			
		} 
		catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			StringBuffer exception = new StringBuffer(e.getMessage().toString());
			  
            if (exception.indexOf("ConnectException") >= 0)      // connection problem.
            {
            	failureReason = new StringBuffer(" Unable to Connect Mail server");
            }
            else if (exception.indexOf("SendFailedException") >= 0)      // Wrong To Address 
            {
                failureReason = new StringBuffer("Wrong To Mail address");
            }
            else if (exception.indexOf("FileNotFoundException") >= 0)    //File Not Found at Specified Location
            {
            	failureReason = new StringBuffer("File Not Found at Specific location");                   
            }
            else        // Email has not been sent.
            {
            	failureReason = new StringBuffer("Email has not been sent.");
            }
			
    		logger.info("*****EmailService exception*****" + failureReason);
			
			
		}
		
	}
	
	
	public void sendMailWithForgetPasswordURLForRegisteredUserEmail(String userEmailId, String userName, String resetPwdUrl, String encodedUserMobileNo) {
		
		logger.info("*****EmailService sendMailWithForgetPasswordURLForRegisteredUserEmail*****");
		
		logger.info("Registered Mail ID ---------------" + userEmailId + "Registered UserName ---------------" + userName);
		
		logger.info("Encoded Mobile No ---------------" + encodedUserMobileNo + "URL ---------------" + resetPwdUrl);
		
		StringBuffer failureReason = null;
		
		String host = fromMailIdHost;
		
		Properties properties = System.getProperties();
		
		logger.info("Properties are ------" + properties);
		
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", "465");
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.smtp.auth", "true");
		
		String mailTextContent = "Dear, " + userName + "\r\r" + 
						"We have received your reset password request. Please click link below to reset your password."
					+"<a href='http://" + resetPwdUrl +"/invoicingweb/resetPassword?" + encodedUserMobileNo +"'><strong>Reset Link</strong></a>" +
					"<br><br> Thanks, <br> Team SCUBE Technologies";
		
		String subjectLine = "Link to reset your password";
	
		Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
			
			protected PasswordAuthentication getPasswordAuthentication() {				
				
				return new PasswordAuthentication(fromMailId, fromMailPwd);
				
	
			}		
			
		});
		
		session.setDebug(true);
		
		try {
			
			MimeMessage mimeMessage = new MimeMessage(session);
			
			MimeBodyPart textBodyPart = new MimeBodyPart();	
			textBodyPart.setText(mailTextContent,"UTF-8", "html");
			
			MimeMultipart mimeMultipart = new MimeMultipart();			
			mimeMultipart.addBodyPart(textBodyPart);
			
			mimeMessage.saveChanges();
			
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(fromMailId));		
			
			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(textBodyPart);
			
			message.setContent(multipart);
			
			InternetAddress internetFromMailAddress = new InternetAddress(fromMailId);
			InternetAddress internetReceipientMailAddress = new InternetAddress(userEmailId);
			
			mimeMessage.setSender(internetFromMailAddress);
			message.setSubject(subjectLine);
			message.addRecipient(Message.RecipientType.TO, internetReceipientMailAddress);
			mimeMessage.setRecipient(Message.RecipientType.TO, internetReceipientMailAddress);
			mimeMessage.setContent(mimeMultipart);
			
			logger.info("------------" + "Sending" + "---------------");
			
			Transport.send(message);
			
			logger.info("Mail Sent Successfully...................");
			
		} 
		catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			StringBuffer exception = new StringBuffer(e.getMessage().toString());
			  
            if (exception.indexOf("ConnectException") >= 0)      // connection problem.
            {
            	failureReason = new StringBuffer(" Unable to Connect Mail server");
            }
            else if (exception.indexOf("SendFailedException") >= 0)      // Wrong To Address 
            {
                failureReason = new StringBuffer("Wrong To Mail address");
            }
            else if (exception.indexOf("FileNotFoundException") >= 0)    //File Not Found at Specified Location
            {
            	failureReason = new StringBuffer("File Not Found at Specific location");                   
            }
            else        // Email has not been sent.
            {
            	failureReason = new StringBuffer("Email has not been sent.");
            }
			
    		logger.info("*****EmailService exception*****" + failureReason);
			
			
		}
		
	}
	
	public void sendMailWithAttachment(File changesInMatMasterFile) throws Exception {
		
		String host = fromMailIdHost;
		
		Properties properties = System.getProperties();
		
		logger.info("Properties are ------" + properties);
		
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", "465");
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.smtp.auth", "true");
		
		String mailTextContent = "Dear Team, \r\r " +
				" PFA Changes In Material Master Records for " + "\r\r " + 
				"Inventory Management Team ";

		String subjectLine = "Changes In Material Master ";
		
		Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
			
			protected PasswordAuthentication getPasswordAuthentication() {				
				
				return new PasswordAuthentication(fromMailId, fromMailPwd);
	
			}		
			
		});
		
		session.setDebug(true);
		
		try {
			
			
			
			MimeMessage mimeMessage = new MimeMessage(session);
			
			MimeBodyPart textBodyPart = new MimeBodyPart();			
			textBodyPart.setText(mailTextContent);
			
			MimeMultipart mimeMultipart = new MimeMultipart();			
			mimeMultipart.addBodyPart(textBodyPart);
			
			mimeMessage.setFrom(new InternetAddress(fromMailId));
			
			
			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(textBodyPart);
			
			mimeMessage.setContent(multipart);
			
			InternetAddress internetFromMailAddress = new InternetAddress(fromMailId);
			
			mimeMessage.setSender(internetFromMailAddress);
			mimeMessage.setSubject(subjectLine);
			
			mimeMessage.addRecipients(Message.RecipientType.TO, 
                    InternetAddress.parse(receipientMailId));
			
			MimeBodyPart fileMimeBodyPart = new MimeBodyPart();
			fileMimeBodyPart.attachFile(changesInMatMasterFile);
			multipart.addBodyPart(fileMimeBodyPart);
			
			
			mimeMessage.setContent(multipart);
			
			logger.info("------------" + "Sending" + "---------------");
			
			Transport.send(mimeMessage);
			
			logger.info("Mail Sent Successfully...................");
			
			
			
		
	
}
		catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			StringBuffer exception = new StringBuffer(e.getMessage().toString());
			  
            if (exception.indexOf("ConnectException") >= 0)      // connection problem.
            {
            	exception = new StringBuffer(" Unable to Connect Mail server");
            }
            else if (exception.indexOf("SendFailedException") >= 0)      // Wrong To Address 
            {
                exception = new StringBuffer("Wrong To Mail address");
            }
            else if (exception.indexOf("FileNotFoundException") >= 0)    //File Not Found at Specified Location
            {
            	exception = new StringBuffer("File Not Found at Specific location");                   
            }
            else        // Email has not been sent.
            {
            	exception = new StringBuffer("Email has not been sent.");
            }
			
    		logger.info("*****EmailService exception*****" + exception);
	
		}
	}
	
	
	public void sendMailForDuplicateRecords(File inputFile) throws Exception {
		
		String host = fromMailIdHost;
		
		Properties properties = System.getProperties();
		
		logger.info("Properties are ------" + properties);
		
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", "465");
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.smtp.auth", "true");
		
		String mailTextContent = "Dear Team, \r\r " +
				" Records are already present for " + inputFile.getName() + "\r\r " + 
				"Team S CUBE Technologies Pvt Ltd ";

		String subjectLine = " Duplicate Records Entry ";
		
		Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
			
			protected PasswordAuthentication getPasswordAuthentication() {				
				
				return new PasswordAuthentication(fromMailId, fromMailPwd);
	
			}		
			
		});
		
		session.setDebug(true);
		
		try {
			
			/*
			 * MimeMessage mimeMessage = new MimeMessage(session);
			 * 
			 * MimeBodyPart textBodyPart = new MimeBodyPart();
			 * textBodyPart.setText(mailTextContent);
			 * 
			 * MimeMultipart mimeMultipart = new MimeMultipart();
			 * mimeMultipart.addBodyPart(textBodyPart);
			 * 
			 * mimeMessage.saveChanges();
			 * 
			 * Message message = new MimeMessage(session); message.setFrom(new
			 * InternetAddress(fromMailId));
			 * 
			 * MimeBodyPart fileMimeBodyPart = new MimeBodyPart();
			 * fileMimeBodyPart.attachFile(inputFile);
			 * 
			 * Multipart multipart = new MimeMultipart();
			 * multipart.addBodyPart(textBodyPart); multipart.addBodyPart(fileMimeBodyPart);
			 * 
			 * message.setContent(multipart);
			 * 
			 * InternetAddress internetFromMailAddress = new InternetAddress(fromMailId);
			 * InternetAddress internetReceipientMailAddress = new
			 * InternetAddress(receipientMailId);
			 * 
			 * mimeMessage.setSender(internetFromMailAddress);
			 * message.setSubject(subjectLine);
			 * //message.addRecipient(Message.RecipientType.TO,
			 * internetReceipientMailAddress);
			 * 
			 * message.addRecipients(Message.RecipientType.TO,
			 * InternetAddress.parse(receipientMailId));
			 * 
			 * 
			 * 
			 * mimeMessage.setRecipient(Message.RecipientType.TO,
			 * internetReceipientMailAddress); mimeMessage.setContent(mimeMultipart);
			 * 
			 * logger.info("------------" + "Sending" + "---------------");
			 * 
			 * Transport.send(message);
			 * 
			 * logger.info("Mail Sent Successfully...................");
			 */
			
			MimeMessage mimeMessage = new MimeMessage(session);
			
			MimeBodyPart textBodyPart = new MimeBodyPart();			
			textBodyPart.setText(mailTextContent);
			
			MimeMultipart mimeMultipart = new MimeMultipart();			
			mimeMultipart.addBodyPart(textBodyPart);
			
			mimeMessage.setFrom(new InternetAddress(fromMailId));
			
			
			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(textBodyPart);
			
			mimeMessage.setContent(multipart);
			
			InternetAddress internetFromMailAddress = new InternetAddress(fromMailId);
			
			mimeMessage.setSender(internetFromMailAddress);
			mimeMessage.setSubject(subjectLine);
			
			mimeMessage.addRecipients(Message.RecipientType.TO, 
                    InternetAddress.parse(receipientMailId));
			
			/*
			 * MimeBodyPart fileMimeBodyPart = new MimeBodyPart();
			 * fileMimeBodyPart.attachFile(rejectedFileName);
			 * multipart.addBodyPart(fileMimeBodyPart);
			 */
			
			mimeMessage.setContent(multipart);
			
			logger.info("------------" + "Sending" + "---------------");
			
			Transport.send(mimeMessage);
			
			logger.info("Mail Sent Successfully...................");
	
}
		catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			StringBuffer exception = new StringBuffer(e.getMessage().toString());
			  
            if (exception.indexOf("ConnectException") >= 0)      // connection problem.
            {
            	exception = new StringBuffer(" Unable to Connect Mail server");
            }
            else if (exception.indexOf("SendFailedException") >= 0)      // Wrong To Address 
            {
                exception = new StringBuffer("Wrong To Mail address");
            }
            else if (exception.indexOf("FileNotFoundException") >= 0)    //File Not Found at Specified Location
            {
            	exception = new StringBuffer("File Not Found at Specific location");                   
            }
            else        // Email has not been sent.
            {
            	exception = new StringBuffer("Email has not been sent.");
            }
			
    		logger.info("*****EmailService exception*****" + exception);
	
		}
	}
	
public void sendMailForExcelNotPresent() throws Exception {
		
		String host = fromMailIdHost;
		
		Properties properties = System.getProperties();
		
		logger.info("Properties are ------" + properties);
		
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", "465");
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.smtp.auth", "true");
		
		String mailTextContent = "Dear Team, \r\r " +
				" Some excel is missing so could not load data for today. "+ "\r\r " + 
				" Team S CUBE Technologies Pvt Ltd ";

		String subjectLine = " Could Not Load Data For Today ";
		
		Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
			
			protected PasswordAuthentication getPasswordAuthentication() {				
				
				return new PasswordAuthentication(fromMailId, fromMailPwd);
	
			}		
			
		});
		
		session.setDebug(true);
		
		try {
			
			MimeMessage mimeMessage = new MimeMessage(session);
			
			MimeBodyPart textBodyPart = new MimeBodyPart();			
			textBodyPart.setText(mailTextContent);
			
			MimeMultipart mimeMultipart = new MimeMultipart();			
			mimeMultipart.addBodyPart(textBodyPart);
			
			
			mimeMessage.setFrom(new InternetAddress(fromMailId));
			
			
			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(textBodyPart);
			
			mimeMessage.setContent(multipart);
			
			InternetAddress internetFromMailAddress = new InternetAddress(fromMailId);
			
			mimeMessage.setSender(internetFromMailAddress);
			mimeMessage.setSubject(subjectLine);
			
			mimeMessage.addRecipients(Message.RecipientType.TO, 
                    InternetAddress.parse(receipientMailId));
			
			mimeMessage.setContent(multipart);
			
			logger.info("------------" + "Sending" + "---------------");
			
			Transport.send(mimeMessage);
			
			logger.info("Mail Sent Successfully...................");
	
}
		catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			StringBuffer exception = new StringBuffer(e.getMessage().toString());
			  
            if (exception.indexOf("ConnectException") >= 0)      // connection problem.
            {
            	exception = new StringBuffer(" Unable to Connect Mail server");
            }
            else if (exception.indexOf("SendFailedException") >= 0)      // Wrong To Address 
            {
                exception = new StringBuffer("Wrong To Mail address");
            }
            else if (exception.indexOf("FileNotFoundException") >= 0)    //File Not Found at Specified Location
            {
            	exception = new StringBuffer("File Not Found at Specific location");                   
            }
            else        // Email has not been sent.
            {
            	exception = new StringBuffer("Email has not been sent.");
            }
			
    		logger.info("*****EmailService exception*****" + exception);
	
		}
	}

	public void sendInvoiceMailToCustomer(CreateInvoiceIncomingDto createInvoiceIncomingDto, File attachedFile, List<CheckInvoiceMailStatusEntity> checkMailStatusEntityList,
			String invoiceUrl,CustomerInvoiceEntity customerInvoiceEntity) throws Exception {
	
		logger.info(" ---- EmailService sendInvoiceMailToCustomer ---- ");
		
		StringBuffer failureReason = null;
		String host = fromMailIdHost;
		
		Properties properties = System.getProperties();
		logger.info("Properties are ------" + properties);
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", "465");
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.smtp.auth", "true");
		
		
		String mailTextContent = createInvoiceIncomingDto.getMailBody()+ " <br> Click on below click to download invoice."+
				"<br> <a href=http://"	+ invoiceUrl + "/invoicingweb/emailPage?" + customerInvoiceEntity.getId() + " ><strong>Download Link</strong></a>";						
				//"<a href='http://" + invoiceUrl +"/invoicingweb/emailPage?" + customerInvoiceEntity.getId() +"'><strong>Link to Download invoice</strong></a>";
		
		
		
		/*
		 * String mailTextContent = "Dear, " + userName + "\r\r" +
		 * "We have received your reset password request. Please click link below to reset your password."
		 * +"<a href='http://" + resetPwdUrl +"/invoicingweb/resetPassword?" +
		 * encodedUserMobileNo +"'><strong>Reset Link</strong></a>" +
		 * "<br><br> Thanks, <br> Team SCUBE Technologies";
		 */
		
		
		
		String subjectLine = createInvoiceIncomingDto.getSubject();
		
		Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {			
				return new PasswordAuthentication(createInvoiceIncomingDto.getFromEmailID(), fromMailPwd);
			}
		});
		session.setDebug(true);
	
		try {
			MimeMessage mimeMessage = new MimeMessage(session);
			
			MimeBodyPart textBodyPart = new MimeBodyPart();			
			textBodyPart.setText(mailTextContent ,"UTF-8", "html");
			
			MimeMultipart mimeMultipart = new MimeMultipart();			
			mimeMultipart.addBodyPart(textBodyPart);
			
			mimeMessage.setFrom(new InternetAddress(createInvoiceIncomingDto.getFromEmailID()));
			
			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(textBodyPart);
			
			mimeMessage.setContent(multipart);
			
			InternetAddress internetFromMailAddress = new InternetAddress(createInvoiceIncomingDto.getFromEmailID());
			
			mimeMessage.setSender(internetFromMailAddress);
			mimeMessage.setSubject(subjectLine);
			
			mimeMessage.addRecipients(Message.RecipientType.TO, 
	                InternetAddress.parse(createInvoiceIncomingDto.getToEmailID()));
			mimeMessage.addRecipients(Message.RecipientType.CC, 
	                InternetAddress.parse(createInvoiceIncomingDto.getCc()));
			mimeMessage.addRecipients(Message.RecipientType.BCC, 
	                InternetAddress.parse(createInvoiceIncomingDto.getBccEmailID()));
			
			
			  MimeBodyPart fileMimeBodyPart = new MimeBodyPart();
			  fileMimeBodyPart.attachFile(attachedFile);
			 multipart.addBodyPart(fileMimeBodyPart);
			
			mimeMessage.setContent(multipart);
			
			logger.info("------------" + "Sending" + "---------------");
			Transport.send(mimeMessage);
			logger.info("Mail Sent Successfully...................");
			
			updateInvoiceMailStatusIfSuccess(checkMailStatusEntityList);
			
		} 
		catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			StringBuffer exception = new StringBuffer(e.getMessage().toString());
			  
	        if (exception.indexOf("ConnectException") >= 0)      // connection problem.
	        {
	        	failureReason = new StringBuffer(" Unable to Connect Mail server");
	        }
	        else if (exception.indexOf("SendFailedException") >= 0)      // Wrong To Address 
	        {
	            failureReason = new StringBuffer("Wrong To Mail address");
	        }
	        else if (exception.indexOf("FileNotFoundException") >= 0)    //File Not Found at Specified Location
	        {
	        	failureReason = new StringBuffer("File Not Found at Specific location");                   
	        }
	        else        // Email has not been sent.
	        {
	        	failureReason = new StringBuffer("Email has not been sent.");
	        }
			
			logger.info("---- EmailService exception ----" + failureReason);
			
			logger.info("--- Mail not sent status updated ----");
			
		}
	}
	
	
	public boolean updateCreditNoteMailStatusIfSuccess(List<CheckCreditNoteMailStatusEntity> checkCreditNoteMailStatusEntityList) {
		CheckCreditNoteMailStatusEntity checkMailStatusEntity = new CheckCreditNoteMailStatusEntity();
		
		for(int i=0; i<checkCreditNoteMailStatusEntityList.size(); i++) {
			checkMailStatusEntity = checkCreditNoteMailStatusRepository.findById(checkCreditNoteMailStatusEntityList.get(i).getId()).get();
			checkMailStatusEntity.setMailStatus("SENT");
		}
		
		checkCreditNoteMailStatusRepository.save(checkMailStatusEntity);
		
		logger.info("--- Mail Sent Status Updated in Credit Note ----");
		return true;
	}
	
	
	public boolean updateInvoiceMailStatusIfSuccess(List<CheckInvoiceMailStatusEntity> checkMailStatusEntityList) {
		CheckInvoiceMailStatusEntity checkInvoiceMailStatusEntity = new CheckInvoiceMailStatusEntity();
		
		for(int i=0; i<checkMailStatusEntityList.size(); i++) {
			checkInvoiceMailStatusEntity = checkMailStatusRepository.findById(checkMailStatusEntityList.get(i).getId()).get();
			checkInvoiceMailStatusEntity.setMailStatus("SENT");
		}
		
		checkMailStatusRepository.save(checkInvoiceMailStatusEntity);
		
		logger.info("--- Mail Sent Status Updated in Invoice ----");
		return true;
	}
	
	
	public void sendCreditNoteMail(CreateInvoiceIncomingDto createInvoiceIncomingDto, File attachedFile,
			List<CheckCreditNoteMailStatusEntity> checkCreditNoteMailStatusEntityList, String invoiceUrl, CustomerCreditNoteEntity customerCreditNoteEntity
			) throws Exception {
	
		logger.info(" ---- EmailService sendCreditNoteMail ---- ");
		
		StringBuffer failureReason = null;
		String host = fromMailIdHost;
		
		Properties properties = System.getProperties();
		logger.info("Properties are ------" + properties);
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", "465");
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.smtp.auth", "true");
		
		String mailTextContent = createInvoiceIncomingDto.getMailBody()+ " <br> Click on below click to download credit Note."+
				"<br> <a href=http://"	+ invoiceUrl + "/invoicingweb/emailPageForCreditNote?" + customerCreditNoteEntity.getId() + " ><strong>Download Link</strong></a>";
		
		
		String subjectLine = createInvoiceIncomingDto.getSubject();
		
		Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {			
				return new PasswordAuthentication(createInvoiceIncomingDto.getFromEmailID(), fromMailPwd);
			}
		});
		session.setDebug(true);
	
		try {
			MimeMessage mimeMessage = new MimeMessage(session);
			
			MimeBodyPart textBodyPart = new MimeBodyPart();			
			textBodyPart.setText(mailTextContent,"UTF-8", "html");
			
			MimeMultipart mimeMultipart = new MimeMultipart();			
			mimeMultipart.addBodyPart(textBodyPart);
			
			mimeMessage.setFrom(new InternetAddress(createInvoiceIncomingDto.getFromEmailID()));
			
			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(textBodyPart);
			
			mimeMessage.setContent(multipart);
			
			InternetAddress internetFromMailAddress = new InternetAddress(createInvoiceIncomingDto.getFromEmailID());
			
			mimeMessage.setSender(internetFromMailAddress);
			mimeMessage.setSubject(subjectLine);
			
			mimeMessage.addRecipients(Message.RecipientType.TO, 
	                InternetAddress.parse(createInvoiceIncomingDto.getToEmailID()));
			mimeMessage.addRecipients(Message.RecipientType.CC, 
	                InternetAddress.parse(createInvoiceIncomingDto.getCc()));
			logger.info("------------" + "getcc" + "---------------" + createInvoiceIncomingDto.getCc());
			mimeMessage.addRecipients(Message.RecipientType.BCC, 
	                InternetAddress.parse(createInvoiceIncomingDto.getBccEmailID()));
			
			
			  MimeBodyPart fileMimeBodyPart = new MimeBodyPart();
			  fileMimeBodyPart.attachFile(attachedFile);
			  multipart.addBodyPart(fileMimeBodyPart);
			 
			mimeMessage.setContent(multipart);
			
			logger.info("------------" + "Sending" + "---------------");
			Transport.send(mimeMessage);
			logger.info("Mail Sent Successfully...................");	
			
			updateCreditNoteMailStatusIfSuccess(checkCreditNoteMailStatusEntityList);
		
		}
		catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			StringBuffer exception = new StringBuffer(e.getMessage().toString());
			  
	        if (exception.indexOf("ConnectException") >= 0)      // connection problem.
	        {
	        	failureReason = new StringBuffer(" Unable to Connect Mail server");
	        }
	        else if (exception.indexOf("SendFailedException") >= 0)      // Wrong To Address 
	        {
	            failureReason = new StringBuffer("Wrong To Mail address");
	        }
	        else if (exception.indexOf("FileNotFoundException") >= 0)    //File Not Found at Specified Location
	        {
	        	failureReason = new StringBuffer("File Not Found at Specific location");                   
	        }
	        else        // Email has not been sent.
	        {
	        	failureReason = new StringBuffer("Email has not been sent.");
	        }
			
			logger.info("---- EmailService exception ----" + failureReason);
			
		}
	}
}
