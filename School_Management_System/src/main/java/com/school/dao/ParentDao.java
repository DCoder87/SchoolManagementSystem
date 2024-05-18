package com.school.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.beans.Institute;
import com.school.beans.Parent;
import com.school.repository.InstituteRepository;
import com.school.repository.ParentRepository;
import com.school.request.ParentRequest;
import com.school.service.ParentService;

@Service
public class ParentDao implements ParentService {

	@Autowired
	ParentRepository parentRepository;
	
	@Autowired
	InstituteRepository institutionRepository;


	EncryptionAndDecryption decrypt = new EncryptionAndDecryption();
	
	@Override
	public List<Parent> getallParents(String institute_id) {
		String institute_id1 = decrypt.Decryption(institute_id);
		return parentRepository.ListOfAllParents(institute_id1);
	}

	@Override
	public Parent saveParent(ParentRequest parentRequest, String institute_id) {

		String institute_id1 = decrypt.Decryption(institute_id);
		int i_id = Integer.parseInt(institute_id1);
		
		System.out.println(parentRequest);
		try {
			Institute institute = new Institute();
			institute.setInstitute_id(i_id);

			Parent parent = new Parent();
			parent.setAddress(parentRequest.getAddress());
			parent.setAlternate_mobile(parentRequest.getAlternate_mobile());
			parent.setEducation_qualification(parentRequest.getEducation_qualification());
			parent.setEmail(parentRequest.getEmail());
			parent.setMobile(parentRequest.getMobile());
			parent.setOccupation(parentRequest.getOccupation());
			parent.setParent_name(parentRequest.getParent_name());
			parent.setStatus(1);
			parent.setInstitute(institute);

			
			return parentRepository.save(parent);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Parent updateParent(ParentRequest parentRequest, String institute_id, int id) {
		String institute_id1 = decrypt.Decryption(institute_id);
		int i_id = Integer.parseInt(institute_id1);

		try {
			Institute institute = new Institute();
			institute.setInstitute_id(i_id);

			Parent parent = new Parent();
			parent.setAddress(parentRequest.getAddress());
			parent.setAlternate_mobile(parentRequest.getAlternate_mobile());
			parent.setEducation_qualification(parentRequest.getEducation_qualification());
			parent.setEmail(parentRequest.getEmail());
			parent.setMobile(parentRequest.getMobile());
			parent.setOccupation(parentRequest.getOccupation());
			parent.setParent_name(parentRequest.getParent_name());
			parent.setStatus(1);
			parent.setParent_id(id);
			parent.setInstitute(institute);

			
			return parentRepository.save(parent);
		} catch (Exception e) {
			return null;
		}
	}

	

	@Override
	public Optional<Parent> findParentById(int id, String institute_id) {
		String institute_id1  = decrypt.Decryption(institute_id);
		return parentRepository.findParentById(id, institute_id1);
	}

	@Override
	public int deleteParentByid(int id, String institute_id) {
		String institute_id1  = decrypt.Decryption(institute_id);
		int deleted = parentRepository.deleteByParentId(id, institute_id1);
		if (deleted == 1) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public int activeParentByid(int id, String institute_id) {
		String institute_id1  = decrypt.Decryption(institute_id);
		int activated = parentRepository.activeByParentId(id, institute_id1);
		if( activated == 1) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public List<Parent> getdeletedParents(String institute_id) {
		String institute_id1  = decrypt.Decryption(institute_id);
		return parentRepository.ListOfdeletedParents(institute_id1);
	}

	@Override
	public String findParentByParentId(String parent_id, String institute_id) {
		String institute_id1  = decrypt.Decryption(institute_id);
		String prnt_id = parentRepository.findParentByParentId(parent_id, institute_id1);
		return prnt_id;
	}

}
