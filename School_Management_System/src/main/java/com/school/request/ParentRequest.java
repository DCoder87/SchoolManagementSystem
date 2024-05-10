package com.school.request;

public class ParentRequest {

	private String parent_name;

    private String mobile;

    private String email;

    private String address;

    private String alternate_mobile;

    private String occupation;

    private String education_qualification;

	
   
	public String getParent_name() {
		return parent_name;
	}



	public String getMobile() {
		return mobile;
	}



	public String getEmail() {
		return email;
	}



	public String getAddress() {
		return address;
	}



	public String getAlternate_mobile() {
		return alternate_mobile;
	}



	public String getOccupation() {
		return occupation;
	}



	public String getEducation_qualification() {
		return education_qualification;
	}



	@Override
	public String toString() {
		return "ParentRequest [parent_name=" + parent_name + ", mobile=" + mobile + ", email=" + email + ", address="
				+ address + ", alternate_mobile=" + alternate_mobile + ", occupation=" + occupation
				+ ", education_qualification=" + education_qualification + "]";
	}
	
	
    
}
