package com.school.beans;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class StudentStandard {

	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "studentstandard_count")
    @SequenceGenerator(name = "studentstandard_count", initialValue = 1, allocationSize = 1)
	@Column(length = 16)
    private int student_standard_id;
	
	@NotNull
    @ManyToOne(cascade = {CascadeType.REFRESH})
	@JoinColumn(name = "institute_id")
    private Institute institute ;
	
	 @NotNull
	 @OneToOne(cascade = {CascadeType.REFRESH})
	 @JoinColumn(name = "student_id")
	 private Student student;
	 
	 @NotNull
	 @ManyToOne(cascade = {CascadeType.REFRESH})
	 @JoinColumn(name = "standard_id")
	 private StandardMaster standardmaster;
	 
	 private double grade;
	 
	 @Size(max = 32)
	 @Column(length = 32)
	 private int academic_start_year;
	 
	 @Size(max = 32)
	 @Column(length = 32)
	 private int academic_end_year;
	 
	 @NotNull
	 @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	 private Date admission_date;
	 
	 @Column(length = 2)
	 private int status;

	public StudentStandard() {
		super();
	}

	

	public StudentStandard(int student_standard_id, @NotNull Institute institute, @NotNull Student student,
			@NotNull StandardMaster standardmaster, double grade, @Size(max = 32) int academic_start_year,
			@Size(max = 32) int academic_end_year, @NotNull Date admission_date, int status) {
		super();
		this.student_standard_id = student_standard_id;
		this.institute = institute;
		this.student = student;
		this.standardmaster = standardmaster;
		this.grade = grade;
		this.academic_start_year = academic_start_year;
		this.academic_end_year = academic_end_year;
		this.admission_date = admission_date;
		this.status = status;
	}



	public int getStudent_standard_id() {
		return student_standard_id;
	}

	public void setStudent_standard_id(int student_standard_id) {
		this.student_standard_id = student_standard_id;
	}

	public Institute getInstitute() {
		return institute;
	}

	public void setInstitute(Institute institute) {
		this.institute = institute;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public StandardMaster getStandardmaster() {
		return standardmaster;
	}

	public void setStandardmaster(StandardMaster standardmaster) {
		this.standardmaster = standardmaster;
	}

	public double getGrade() {
		return grade;
	}

	public void setGrade(double grade) {
		this.grade = grade;
	}

	public int getAcademic_start_year() {
		return academic_start_year;
	}

	public void setAcademic_start_year(int academic_start_year) {
		this.academic_start_year = academic_start_year;
	}

	public int getAcademic_end_year() {
		return academic_end_year;
	}

	public void setAcademic_end_year(int academic_end_year) {
		this.academic_end_year = academic_end_year;
	}

	public Date getAdmission_date() {
		return admission_date;
	}

	public void setAdmission_date(Date admission_date) {
		this.admission_date = admission_date;
	}
	

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "StudentStandard [student_standard_id=" + student_standard_id + ", institute=" + institute + ", student="
				+ student + ", standardmaster=" + standardmaster + ", grade=" + grade + ", academic_start_year="
				+ academic_start_year + ", academic_end_year=" + academic_end_year + ", admission_date="
				+ admission_date + ", status=" + status + "]";
	}

	
	 
	 
}
