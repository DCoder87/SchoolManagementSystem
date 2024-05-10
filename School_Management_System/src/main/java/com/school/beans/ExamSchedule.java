package com.school.beans;

import java.sql.Time;
import java.util.Date;

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

import org.hibernate.annotations.BatchSize;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class ExamSchedule {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "exam_schedule_count")
	@SequenceGenerator(name = "exam_schedule_count", initialValue = 1, allocationSize = 1)
	@Column(length = 16)
	private int exam_id;

	@NotNull
	@BatchSize(size = 100)
	@Column(length = 100)
	private String exam_name;

	@ManyToOne
	@JoinColumn(name = "subject_id", referencedColumnName = "subject_id")
	private SubjectMaster subjectMaster;

	@ManyToOne
	@JoinColumn(name = "standard_id", referencedColumnName = "standard_id")
	private StandardMaster standardMaster;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date exam_date;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
	private Time exam_start_time;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
	private Time exam_end_time;

	@NotNull
	@BatchSize(size = 100)
	@Column(length = 100)
	private String exam_center;

	@Column(length = 2)
	private int status;

	@NotNull
	@ManyToOne(cascade = { CascadeType.REFRESH })
	@JoinColumn(name = "institute_id")
	private Institute institute;

	public ExamSchedule() {
		super();
	}

	public ExamSchedule(int exam_id, @NotNull String exam_name, SubjectMaster subjectMaster,
			StandardMaster standardMaster, Date exam_date, Time exam_start_time, Time exam_end_time,
			@NotNull String exam_center, int status, @NotNull Institute institute) {
		super();
		this.exam_id = exam_id;
		this.exam_name = exam_name;
		this.subjectMaster = subjectMaster;
		this.standardMaster = standardMaster;
		this.exam_date = exam_date;
		this.exam_start_time = exam_start_time;
		this.exam_end_time = exam_end_time;
		this.exam_center = exam_center;
		this.status = status;
		this.institute = institute;
	}

	public int getExam_id() {
		return exam_id;
	}

	public void setExam_id(int exam_id) {
		this.exam_id = exam_id;
	}

	public String getExam_name() {
		return exam_name;
	}

	public void setExam_name(String exam_name) {
		this.exam_name = exam_name;
	}

	public SubjectMaster getSubjectMaster() {
		return subjectMaster;
	}

	public void setSubjectMaster(SubjectMaster subjectMaster) {
		this.subjectMaster = subjectMaster;
	}

	public StandardMaster getStandardMaster() {
		return standardMaster;
	}

	public void setStandardMaster(StandardMaster standardMaster) {
		this.standardMaster = standardMaster;
	}

	public Date getExam_date() {
		return exam_date;
	}

	public void setExam_date(Date exam_date) {
		this.exam_date = exam_date;
	}

	public Time getExam_start_time() {
		return exam_start_time;
	}

	public void setExam_start_time(Time exam_start_time) {
		this.exam_start_time = exam_start_time;
	}

	public Time getExam_end_time() {
		return exam_end_time;
	}

	public void setExam_end_time(Time exam_end_time) {
		this.exam_end_time = exam_end_time;
	}

	public String getExam_center() {
		return exam_center;
	}

	public void setExam_center(String exam_center) {
		this.exam_center = exam_center;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Institute getInstitute() {
		return institute;
	}

	public void setInstitute(Institute institute) {
		this.institute = institute;
	}

	@Override
	public String toString() {
		return "ExamSchedule [exam_id=" + exam_id + ", exam_name=" + exam_name + ", subjectMaster=" + subjectMaster
				+ ", standardMaster=" + standardMaster + ", exam_date=" + exam_date + ", exam_start_time="
				+ exam_start_time + ", exam_end_time=" + exam_end_time + ", exam_center=" + exam_center + ", status="
				+ status + ", institute=" + institute + "]";
	}

}