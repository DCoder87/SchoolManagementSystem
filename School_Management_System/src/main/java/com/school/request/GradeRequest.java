package com.school.request;

public class GradeRequest {
	
	private int standard_id;
	
	private double grade;
	
	private int student_id;

	public int getStandard_id() {
		return standard_id;
	}

	public double getGrade() {
		return grade;
	}

	public int getStudent_id() {
		return student_id;
	}

	@Override
	public String toString() {
		return "GradeRequest [standard_id=" + standard_id + ", grade=" + grade + ", student_id=" + student_id + "]";
	}
	
	
}
