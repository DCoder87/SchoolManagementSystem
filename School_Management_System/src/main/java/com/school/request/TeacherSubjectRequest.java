package com.school.request;

public class TeacherSubjectRequest {


	private int teacher_id;

	private int subject_id;

	private int standard_id;

	public TeacherSubjectRequest() {
		super();
	}

	public int getTeacher_id() {
		return teacher_id;
	}

	public int getSubject_id() {
		return subject_id;
	}

	public int getStandard_id() {
		return standard_id;
	}

	@Override
	public String toString() {
		return "TeacherSubjectRequest [teacher_id=" + teacher_id + ", subject_id=" + subject_id + ", standard_id="
				+ standard_id + "]";
	}


}
