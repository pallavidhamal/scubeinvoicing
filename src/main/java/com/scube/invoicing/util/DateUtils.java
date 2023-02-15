package com.scube.invoicing.util;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Keshav Patel.
 */
public class DateUtils {

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    
    private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");

    private static final DateTimeFormatter myInvoiceDateTimeFormatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT)
			.withLocale(Locale.UK)
			.withZone(ZoneId.systemDefault());
    
    /**
     * Returns today's date as java.util.Date object
     *
     * @return today's date as java.util.Date object
     */
    public static Date today() {
        return new Date();
    }

    /**
     * Returns today's date as yyyy-MM-dd format
     *
     * @return today's date as yyyy-MM-dd format
     */
    public static String todayStr() {
        return sdf.format(today());
    }

    /**
     * Returns the formatted String date for the passed java.util.Date object
     *
     * @param date
     * @return
     */
    public static String formattedDate(Date date) {
        return date != null ? sdf.format(date) : todayStr();
    }
    
    public static String formatDateToDDMMYYYYFormat(Date date) {
        return date != null ? simpleDateFormat.format(date) : todayStr();
    }
    
    
   // String myInvoiceRoundOffDateAndTime = myInvoiceDateTimeFormatter.format(myInvoiceDate);
    
    public static String formattedInstantToDateTimeString(Instant date) {
        return date != null ? myInvoiceDateTimeFormatter.format(date) : todayStr();
    }
    
    
    // To Check if a String is a valid date format or not
    public static boolean isValidDate(String date) {
    	
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        try {
          dateFormat.parse(date.trim());
        } 
        catch (Exception pe) {
          return false;
        }
        return true;
    }
    
    
    public static String modifyDateLayout(String inputDate) throws ParseException {
    	
        Date date = new SimpleDateFormat("dd-MM-yyyy").parse(inputDate);
        String formattedDateToString = new SimpleDateFormat("dd-MM-yyyy").format(date);
        return formattedDateToString;
        
    }
    
    public static Date stringToDateConvert(String inputDate) throws ParseException {
    	
        Date date = new SimpleDateFormat("yyyy-MM-dd").parse(inputDate);
        return date;
        
    }

}