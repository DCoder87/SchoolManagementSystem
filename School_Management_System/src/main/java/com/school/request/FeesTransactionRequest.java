package com.school.request;

import java.util.Date;

public class FeesTransactionRequest {

	private int fee_id;
	
	private int student_id;
	
	private int type_id;	
	    
	private Date next_due_date;
	
	private double paid_amount;

    private String amount_type;

    private boolean bill_status;

	public int getFee_id() {
		return fee_id;
	}

	public int getStudent_id() {
		return student_id;
	}

	public int getType_id() {
		return type_id;
	}


	public Date getNext_due_date() {
		return next_due_date;
	}

	public double getPaid_amount() {
		return paid_amount;
	}

	public String getAmount_type() {
		return amount_type;
	}

	public boolean isBill_status() {
		return bill_status;
	}

	@Override
	public String toString() {
		return "FeesTransactionRequest [fee_id=" + fee_id + ", student_id=" + student_id + ", type_id=" + type_id
				+ ", next_due_date=" + next_due_date + ", paid_amount=" + paid_amount + ", amount_type=" + amount_type
				+ ", bill_status=" + bill_status + "]";
	}

	
    
}
