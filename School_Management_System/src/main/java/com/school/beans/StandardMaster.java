package com.school.beans;

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

@Entity
public class StandardMaster {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "standardmaster_count")
	@SequenceGenerator(name = "standardmaster_count", initialValue = 1, allocationSize = 1)
	@Column(length = 16)
	private int standard_id;

	@NotNull
	@Size(max = 256)
	@Column(length = 256)
	private String standard_name;

	@OneToOne
	@JoinColumn(name = "teacher_id", referencedColumnName = "teacher_id")
	private Teacher teacher;

	@NotNull
	@ManyToOne(cascade = { CascadeType.REFRESH })
	@JoinColumn(name = "institute_id")
	private Institute institute;

	@Column(length = 2)
	private int status;

	public StandardMaster() {
		super();
	}

	public StandardMaster(int standard_id, @NotNull @Size(max = 256) String standard_name, Teacher teacher,
			@NotNull Institute institute, int status) {
		super();
		this.standard_id = standard_id;
		this.standard_name = standard_name;
		this.teacher = teacher;
		this.institute = institute;
		this.status = status;
	}

	public int getStandard_id() {
		return standard_id;
	}

	public void setStandard_id(int standard_id) {
		this.standard_id = standard_id;
	}

	public String getStandard_name() {
		return standard_name;
	}

	public void setStandard_name(String standard_name) {
		this.standard_name = standard_name;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public Institute getInstitute() {
		return institute;
	}

	public void setInstitute(Institute institute) {
		this.institute = institute;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "StandardMaster [standard_id=" + standard_id + ", standard_name=" + standard_name + ", teacher="
				+ teacher + ", institute=" + institute + ", status=" + status + "]";
	}

}
