package com.school.request;

import java.util.Date;

public class StudentStandardRequest {
	
	private int student_id;
	
	private int standard_id;
	
	 private double grade;
	 
	 private int academic_start_year;
	 
	 private int academic_end_year;
	 
	 private Date admission_date;

	public int getStudent_id() {
		return student_id;
	}

	public int getStandard_id() {
		return standard_id;
	}

	public double getGrade() {
		return grade;
	}

	public int getAcademic_start_year() {
		return academic_start_year;
	}

	public int getAcademic_end_year() {
		return academic_end_year;
	}

	public Date getAdmission_date() {
		return admission_date;
	}

	@Override
	public String toString() {
		return "StudentStandard [student_id=" + student_id + ", standard_id=" + standard_id + ", grade=" + grade
				+ ", academic_start_year=" + academic_start_year + ", academic_end_year=" + academic_end_year
				+ ", admission_date=" + admission_date + "]";
	}
	 
	 
}
