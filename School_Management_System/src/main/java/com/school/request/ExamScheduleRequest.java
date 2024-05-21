package com.school.request;

import java.sql.Time;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ExamScheduleRequest {

	private String exam_name;

	private int subject_id;

	private int student_standard_id;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date exam_date;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
	private Time exam_start_time;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
	private Time exam_end_time;

	private String exam_center;

	public ExamScheduleRequest() {
		super();
	}

	public String getExam_name() {
		return exam_name;
	}

	public int getSubject_id() {
		return subject_id;
	}

	public int getStudent_standard_id() {
		return student_standard_id;
	}

	public Date getExam_date() {
		return exam_date;
	}

	public Time getExam_start_time() {
		return exam_start_time;
	}

	public Time getExam_end_time() {
		return exam_end_time;
	}

	public String getExam_center() {
		return exam_center;
	}

	@Override
	public String toString() {
		return "ExamScheduleRequest [exam_name=" + exam_name + ", subject_id=" + subject_id + ", student_standard_id="
				+ student_standard_id + ", exam_date=" + exam_date + ", exam_start_time=" + exam_start_time
				+ ", exam_end_time=" + exam_end_time + ", exam_center=" + exam_center + "]";
	}

}
