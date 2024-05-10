package com.school.beans;

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

@Entity
public class TeacherSubject {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "teacher_subject_count")
	@SequenceGenerator(name = "teacher_subject_count", initialValue = 1, allocationSize = 1)
	@Column(length = 16)
	private int mapped_id;

	@ManyToOne
	@JoinColumn(name = "teacher_id", referencedColumnName = "teacher_id")
	private Teacher teacher;

	@ManyToOne
	@JoinColumn(name = "subject_id", referencedColumnName = "subject_id")
	private SubjectMaster subjectMaster;

	@ManyToOne
	@JoinColumn(name = "standard_id", referencedColumnName = "standard_id")
	private StandardMaster standardMaster;

	@Column(length = 2)
	private int status;

	@NotNull
	@ManyToOne(cascade = { CascadeType.REFRESH })
	@JoinColumn(name = "institute_id")
	private Institute institute;

	public TeacherSubject() {
		super();
	}

	public TeacherSubject(int mapped_id, Teacher teacher, SubjectMaster subjectMaster, StandardMaster standardMaster,
			int status, @NotNull Institute institute) {
		super();
		this.mapped_id = mapped_id;
		this.teacher = teacher;
		this.subjectMaster = subjectMaster;
		this.standardMaster = standardMaster;
		this.status = status;
		this.institute = institute;
	}

	public int getMapped_id() {
		return mapped_id;
	}

	public void setMapped_id(int mapped_id) {
		this.mapped_id = mapped_id;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
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
		return "TeacherSubject [mapped_id=" + mapped_id + ", teacher=" + teacher + ", subjectMaster=" + subjectMaster
				+ ", standardMaster=" + standardMaster + ", status=" + status + ", institute=" + institute + "]";
	}

}