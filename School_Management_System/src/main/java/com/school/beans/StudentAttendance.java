package com.school.beans;
import java.sql.Time;
import java.time.LocalDate;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class StudentAttendance {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@SequenceGenerator(name = "StudentAttendance_count", initialValue = 1, allocationSize = 1)
	private int student_attendance_id;

	@NotNull
	@ManyToOne(cascade = {CascadeType.REFRESH})
	@JoinColumn(name = "student_id")
	private Student student_id;
	
	@NotNull
	@ManyToOne(cascade = {CascadeType.REFRESH})
	@JoinColumn(name = "institute_id")
	private Institute institute;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate date;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
	private Time check_in_time;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
	private Time check_out_time;

	@Column(length = 256)
	private String location;

	@Column(length = 32)
	private int month_id;

	@Column(length = 32)
	private int year_id;

	@Column(length = 16)
	private int status;

	public StudentAttendance() {
		super();
	}

	public StudentAttendance(int student_attendance_id, Student student_id, Institute institute, LocalDate date,
			Time check_in_time, Time check_out_time, String location, int month_id, int year_id, int status) {
		super();
		this.student_attendance_id = student_attendance_id;
		this.student_id = student_id;
		this.institute = institute;
		this.date = date;
		this.check_in_time = check_in_time;
		this.check_out_time = check_out_time;
		this.location = location;
		this.month_id = month_id;
		this.year_id = year_id;
		this.status = status;
	}

	public int getStudent_attendance_id() {
		return student_attendance_id;
	}

	public void setStudent_attendance_id(int student_attendance_id) {
		this.student_attendance_id = student_attendance_id;
	}

	public Student getStudent_id() {
		return student_id;
	}

	public void setStudent_id(Student student_id) {
		this.student_id = student_id;
	}

	public Institute getInstitute() {
		return institute;
	}

	public void setInstitute(Institute institute) {
		this.institute = institute;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Time getCheck_in_time() {
		return check_in_time;
	}

	public void setCheck_in_time(Time check_in_time) {
		this.check_in_time = check_in_time;
	}

	public Time getCheck_out_time() {
		return check_out_time;
	}

	public void setCheck_out_time(Time check_out_time) {
		this.check_out_time = check_out_time;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getMonth_id() {
		return month_id;
	}

	public void setMonth_id(int month_id) {
		this.month_id = month_id;
	}

	public int getYear_id() {
		return year_id;
	}

	public void setYear_id(int year_id) {
		this.year_id = year_id;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "StudentAttendance [student_attendance_id=" + student_attendance_id + ", student_id=" + student_id
				+ ", institute=" + institute + ", date=" + date + ", check_in_time=" + check_in_time
				+ ", check_out_time=" + check_out_time + ", location=" + location + ", month_id=" + month_id
				+ ", year_id=" + year_id + ", status=" + status + "]";
	}
	
	
}
