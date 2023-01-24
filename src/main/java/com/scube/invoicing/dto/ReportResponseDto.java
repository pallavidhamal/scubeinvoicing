package com.scube.invoicing.dto;

import java.io.File;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@ToString
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)

@Service
public class ReportResponseDto {
	
	private String report_date;
	private String brown;
	private String red;
//	private String grey;
	private String orange;
	private String yellow;
	private String blue;
	private String green;
	
	
	// Focused Product Dto
	private String mat_code;
	private String mat_Desc;
	
	private String uom;
	private String unit_price;
	
	private String unit;
	
	private String quantity;
	
	private String cur_inv_days;
	private String cur_inv_units;
	
	private String excess_value;
	
	private String color;
	
	private String sortcolor;

	
	//Value INR For Each Item In Color Code DTO
	private String brown_value;
	private String red_value;
	private String green_value;
	private String blue_value;
//	private String grey_value;
	private String orange_value;
	private String yellow_value;
	
	
	//Orders to be Placed and Cancelled Dto
	private String po_relcan_units;
	private String po_relcan_lacs;
	
	private String eoq;
	private String safety_factor;
	
	// Material Received and Issued
	
	private String last_rcpt_date;
	private String last_rcpt_qty;
	private String last_issue_date;	
	private String last_issue_qty;
	private String recqty_by_max_moeq;
	private String issued_qty;
	private String days_after_issue;
	
	private String cur_inv_lacs;
	
	//dump Data
	private File dumpDataFile;
	
	private String fpath;

	
	//PO Details Dto
	private String po_type;
	private String po_no;
	private String po_date;
	
	private String line_no;
	
	private String item;
	
	private double current_order_pipeline_units;	
	private double rate;
	
	private String currency;
	
	//Orders to be Placed & Cancelled
	private String reg_inter;
	private String kept_instock;
	private String readily_avlble;
	
	private double avgcons_perdayunits;
	private double supplier_moq;
	private double fs_days;
	
	private double ss_norm_units;
	private double ss_norm_units_lacs;
	
	private double max_invnorm_units;
	private double max_invnorm_lacs;
	
	private double avg_invnorm_units;
	
	private double orders_piplinenorm_units;
	private double orders_piplinenorm_lacs;
	
	private double max_invnorm_plantpipeline_units;
	
	private double cur_orders_pipe_units;
	private double cur_orders_pipe_lacs;
	
	private double maxinvnorm_curinv_curpiporders;
	
	private double avg_cons_lacs;
	
	private double maxinvnorm_curinvlacs;
	
	private int leadtime;
	private int transtime;
	
	 
	
}

