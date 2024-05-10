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
public class DocumentMaster {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "document_count")
	@SequenceGenerator(name = "document_count", initialValue = 1, allocationSize = 1)
	@Column(length = 16)
	private int documents_master_id;

	@NotNull
	@Size(max = 256)
	@Column(length = 256)
	private String document_type;

	@NotNull
	@Size(max = 256)
	@Column(length = 256)
	private String document_description;

	@NotNull
	@ManyToOne(cascade = { CascadeType.REFRESH })
	@JoinColumn(name = "institute_id")
	private Institute institute;

	@Column(length = 2)
	private int status;

	public DocumentMaster() {
		super();
	}

	public DocumentMaster(int documents_master_id, @NotNull @Size(max = 256) String document_type,
			@NotNull @Size(max = 256) String document_description, @NotNull Institute institute, int status) {
		super();
		this.documents_master_id = documents_master_id;
		this.document_type = document_type;
		this.document_description = document_description;
		this.institute = institute;
		this.status = status;
	}

	public int getDocuments_master_id() {
		return documents_master_id;
	}

	public void setDocuments_master_id(int documents_master_id) {
		this.documents_master_id = documents_master_id;
	}

	public String getDocument_type() {
		return document_type;
	}

	public void setDocument_type(String document_type) {
		this.document_type = document_type;
	}

	public String getDocument_description() {
		return document_description;
	}

	public void setDocument_description(String document_description) {
		this.document_description = document_description;
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
		return "DocumentMaster [documents_master_id=" + documents_master_id + ", document_type=" + document_type
				+ ", document_description=" + document_description + ", institute=" + institute + ", status=" + status
				+ "]";
	}

}
