package com.school.request;

public class FeesRequest {
	
	private int student_id;
	
	private int standard_id;
	
	private double total_amount;
	 
	private double late_fee;

    private double grand_total;

	private String amount_type;

	public int getStudent_id() {
		return student_id;
	}

	public int getStandard_id() {
		return standard_id;
	}

	public double getTotal_amount() {
		return total_amount;
	}

	public double getLate_fee() {
		return late_fee;
	}

	public double getGrand_total() {
		return grand_total;
	}

	public String getAmount_type() {
		return amount_type;
	}

	@Override
	public String toString() {
		return "FeesRequest [student_id=" + student_id + ", standard_id=" + standard_id + ", total_amount="
				+ total_amount + ", late_fee=" + late_fee + ", grand_total=" + grand_total + ", amount_type="
				+ amount_type + "]";
	}
	
	

}
