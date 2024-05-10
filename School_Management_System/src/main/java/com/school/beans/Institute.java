package com.school.beans;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Institute {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "institute_count")
	@SequenceGenerator(name = "institute_count", initialValue = 1, allocationSize = 1)
	@Column(length = 16)
	private int institute_id;

	@NotNull
	@Size(max = 100)
	@Column(length = 100)
	private String institute_name;

	@Size(max = 256)
	@Column(length = 256)
	private String institute_slogen;

	@Size(max = 256)
	@Column(length = 256)
	private String institute_logo;

	@NotNull
	@Size(max = 65535)
	@Column(length = 65535, columnDefinition = "Text")
	private String address;

	@Column(length = 32)
	private int established_year;

	@Pattern(regexp = "\\d{1,20}") // assuming the contact number length should be between 1 and 20
	@Size(max = 32)
	@Column(length = 32)
	private String contact_number;

	@Email
	@Size(max = 32)
	@Column(length = 32)
	private String email;

	@Size(max = 32)
	@Column(length = 32)
	private String website;

	@Size(max = 100)
	@Column(length = 100)
	private String accregated_status;

	@Column(length = 2)
	private int status;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime created_at;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime updated_at;

	public Institute() {
		super();
	}

	public Institute(int institute_id, String institute_name, String institute_slogen, String institute_logo,
			String address, int established_year, String contact_number, String email, String website,
			String accregated_status, int status, LocalDateTime created_at, LocalDateTime updated_at) {
		super();
		this.institute_id = institute_id;
		this.institute_name = institute_name;
		this.institute_slogen = institute_slogen;
		this.institute_logo = institute_logo;
		this.address = address;
		this.established_year = established_year;
		this.contact_number = contact_number;
		this.email = email;
		this.website = website;
		this.accregated_status = accregated_status;
		this.status = status;
		this.created_at = created_at;
		this.updated_at = updated_at;
	}

	public int getInstitute_id() {
		return institute_id;
	}

	public void setInstitute_id(int institute_id) {
		this.institute_id = institute_id;
	}

	public String getInstitute_name() {
		return institute_name;
	}

	public void setInstitute_name(String institute_name) {
		this.institute_name = institute_name;
	}

	public String getInstitute_slogen() {
		return institute_slogen;
	}

	public void setInstitute_slogen(String institute_slogen) {
		this.institute_slogen = institute_slogen;
	}

	public String getInstitute_logo() {
		return institute_logo;
	}

	public void setInstitute_logo(String institute_logo) {
		this.institute_logo = institute_logo;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getEstablished_year() {
		return established_year;
	}

	public void setEstablished_year(int established_year) {
		this.established_year = established_year;
	}

	public String getContact_number() {
		return contact_number;
	}

	public void setContact_number(String contact_number) {
		this.contact_number = contact_number;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getAccregated_status() {
		return accregated_status;
	}

	public void setAccregated_status(String accregated_status) {
		this.accregated_status = accregated_status;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public LocalDateTime getCreated_at() {
		return created_at;
	}

	public void setCreated_at(LocalDateTime created_at) {
		this.created_at = created_at;
	}

	public LocalDateTime getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(LocalDateTime updated_at) {
		this.updated_at = updated_at;
	}

	@Override
	public String toString() {
		return "Institute [institute_id=" + institute_id + ", institute_name=" + institute_name + ", institute_slogen="
				+ institute_slogen + ", institute_logo=" + institute_logo + ", address=" + address
				+ ", established_year=" + established_year + ", contact_number=" + contact_number + ", email=" + email
				+ ", website=" + website + ", accregated_status=" + accregated_status + ", status=" + status
				+ ", created_at=" + created_at + ", updated_at=" + updated_at + "]";
	}

}
