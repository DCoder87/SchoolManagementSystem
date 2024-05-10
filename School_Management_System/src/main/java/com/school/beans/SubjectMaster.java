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
import javax.validation.constraints.Size;

@Entity

public class SubjectMaster {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "subjectmaster_count")
	@SequenceGenerator(name = "subjectmaster_count", initialValue = 1, allocationSize = 1)
	@Column(length = 16)
	private int subject_id;
	
	@NotNull
	@Size(max = 256)
	@Column(length = 256)
	private String subject_name;
	
	
	
	private int status;


	@NotNull
    @ManyToOne(cascade = {CascadeType.REFRESH})
	@JoinColumn(name = "institute_id")
    private Institute institute ;


	public SubjectMaster() {
		super();
	}


	public SubjectMaster(int subject_id, @NotNull @Size(max = 256) String subject_name,
			@NotNull @Size(max = 256) int status, @NotNull Institute institute) {
		super();
		this.subject_id = subject_id;
		this.subject_name = subject_name;
		this.status = status;
		this.institute = institute;
	}


	public int getSubject_id() {
		return subject_id;
	}


	public void setSubject_id(int subject_id) {
		this.subject_id = subject_id;
	}


	public String getSubject_name() {
		return subject_name;
	}


	public void setSubject_name(String subject_name) {
		this.subject_name = subject_name;
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
		return "SubjectMaster [subject_id=" + subject_id + ", subject_name=" + subject_name + ", status=" + status
				+ ", institute=" + institute + "]";
	}

	
}
	