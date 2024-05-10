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
import javax.validation.constraints.*;

@Entity
public class TransactionTypeMaster {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Transaction_Master_count")
	@SequenceGenerator(name = "Transaction_Master_count", initialValue = 1, allocationSize = 1)
	@Column(length = 16)
	private int type_id;
	
	@NotNull
	@Size(max = 256)
	@Column(length = 256)
	private String type_name;
	
	@NotNull
	@Size(max = 256)
	@Column(length = 256)
	private String type_description;

	
	private int status;

	@NotNull
    @ManyToOne(cascade = {CascadeType.REFRESH})
	@JoinColumn(name = "institute_id")
    private Institute institute ;

	public TransactionTypeMaster() {
		super();
	}

	public TransactionTypeMaster(int type_id, @NotNull @Size(max = 256) String type_name,
			@NotNull @Size(max = 256) String type_description, @NotNull @Size(max = 256) int status,
			@NotNull Institute institute) {
		super();
		this.type_id = type_id;
		this.type_name = type_name;
		this.type_description = type_description;
		this.status = status;
		this.institute = institute;
	}

	public int getType_id() {
		return type_id;
	}

	public void setType_id(int type_id) {
		this.type_id = type_id;
	}

	public String getType_name() {
		return type_name;
	}

	public void setType_name(String type_name) {
		this.type_name = type_name;
	}

	public String getType_description() {
		return type_description;
	}

	public void setType_description(String type_description) {
		this.type_description = type_description;
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
		return "TransactionTypeMaster [type_id=" + type_id + ", type_name=" + type_name + ", type_description="
				+ type_description + ", status=" + status + ", institute=" + institute + "]";
	}

	
}
