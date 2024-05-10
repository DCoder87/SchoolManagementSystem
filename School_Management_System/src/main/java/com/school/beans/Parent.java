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
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
public class Parent {
	
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "parent_count")
    @SequenceGenerator(name = "parent_count", initialValue = 1, allocationSize = 1)
	@Column(length = 16)
    private int parent_id;
	
	@NotNull
    @ManyToOne(cascade = {CascadeType.REFRESH})
	@JoinColumn(name = "institute_id")
    private Institute institute ;

    @NotNull
    @Size(max = 32)
    @Column(length = 32)
    private String parent_name;

    @Pattern(regexp = "\\d{1,20}") // assuming the mobile number length should be between 1 and 20
    @NotNull
    @Size(max = 32)
    @Column(name = "mobile")
    private String mobile;

    @Email
    @Size(max = 50)
    @Column(name = "email")
    private String email;

    @Size(max = 100)
    @Column(length = 100)
    private String address;

    @Size(max = 32)
    @Column(name = "alternate_mobile")
    private String alternate_mobile;

    @Size(max = 100)
    @Column(name = "occupation")
    private String occupation;

    @Size(max = 300)
    @Column(name = "education_qualification")
    private String education_qualification;

    @Column(length = 2)
    private int status;

	public Parent() {
		super();
	}

	public Parent(int parent_id, @NotNull Institute institute, @NotNull @Size(max = 32) String parent_name,
			@Pattern(regexp = "\\d{1,20}") @NotNull @Size(max = 32) String mobile, @Email @Size(max = 50) String email,
			@Size(max = 100) String address, @Size(max = 32) String alternate_mobile,
			@Size(max = 100) String occupation, @Size(max = 300) String education_qualification, int status) {
		super();
		this.parent_id = parent_id;
		this.institute = institute;
		this.parent_name = parent_name;
		this.mobile = mobile;
		this.email = email;
		this.address = address;
		this.alternate_mobile = alternate_mobile;
		this.occupation = occupation;
		this.education_qualification = education_qualification;
		this.status = status;
	}

	public int getParent_id() {
		return parent_id;
	}

	public void setParent_id(int parent_id) {
		this.parent_id = parent_id;
	}

	public Institute getInstitute() {
		return institute;
	}

	public void setInstitute(Institute institute) {
		this.institute = institute;
	}

	public String getParent_name() {
		return parent_name;
	}

	public void setParent_name(String parent_name) {
		this.parent_name = parent_name;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAlternate_mobile() {
		return alternate_mobile;
	}

	public void setAlternate_mobile(String alternate_mobile) {
		this.alternate_mobile = alternate_mobile;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public String getEducation_qualification() {
		return education_qualification;
	}

	public void setEducation_qualification(String education_qualification) {
		this.education_qualification = education_qualification;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Parent [parent_id=" + parent_id + ", institute=" + institute + ", parent_name=" + parent_name
				+ ", mobile=" + mobile + ", email=" + email + ", address=" + address + ", alternate_mobile="
				+ alternate_mobile + ", occupation=" + occupation + ", education_qualification="
				+ education_qualification + ", status=" + status + "]";
	}
    
    
}
