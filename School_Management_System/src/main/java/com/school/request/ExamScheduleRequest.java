package com.school.request;

import java.sql.Time;
import java.util.Date;

public class ExamScheduleRequest {

	private String exam_name;

	private int subject_id;

	private int student_standard_id;

	private Date exam_date;

	private Time exam_start_time;

	private Time exam_end_time;

	private String exam_center;

	private int institute_id;

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

	public int getInstitute_id() {
		return institute_id;
	}

	@Override
	public String toString() {
		return "ExamScheduleRequest [exam_name=" + exam_name + ", subject_id=" + subject_id + ", student_standard_id="
				+ student_standard_id + ", exam_date=" + exam_date + ", exam_start_time=" + exam_start_time
				+ ", exam_end_time=" + exam_end_time + ", exam_center=" + exam_center + ", institute_id=" + institute_id
				+ "]";
	}

}
