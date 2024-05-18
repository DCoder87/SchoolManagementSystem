package com.school.dao;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.beans.DocumentMaster;
import com.school.beans.Institute;
import com.school.repository.DocumentMasterRepository;
import com.school.request.DocumentMasterRequest;
import com.school.service.DocumentMasterService;

@Service
public class DocumentMasterDao implements DocumentMasterService {

	@Autowired
	DocumentMasterRepository documentmasterRepository;
	
	LocalDateTime today = LocalDateTime.now();

	EncryptionAndDecryption decrypt = new EncryptionAndDecryption();
	
	@Override
	public DocumentMaster saveDocument(DocumentMasterRequest document, String Institute_Id) {
		String institute_id1 = decrypt.Decryption(Institute_Id);
		int i_id = Integer.parseInt(institute_id1);
		System.out.println(document.getDocument_description()+"\t"+document.getDocument_type());
		Institute institute=new Institute();
		institute.setInstitute_id(i_id);
		try {
			
			DocumentMaster savedocument=new DocumentMaster();
			savedocument.setDocument_type(document.getDocument_type());
			savedocument.setDocument_description(document.getDocument_description());
			savedocument.setStatus(1);
			savedocument.setInstitute(institute);
			
			return documentmasterRepository.save(savedocument);
		}
		catch(Exception ex)
		{
		return null;
		}
	}

	@Override
	public List<DocumentMaster> allDocuments(String Institute_id) {
		String institute_id1 = decrypt.Decryption(Institute_id);
		return documentmasterRepository.getAllDocuments(institute_id1);
	}

	@Override
	public List<DocumentMaster> allActiveDocuments(String Institute_id) {
		String institute_id1 = decrypt.Decryption(Institute_id);
		return documentmasterRepository.getAllActiveDocuments(institute_id1);
	}

	@Override
	public List<DocumentMaster> allDeactiveDocuments(String Institute_id) {
		String institute_id1 = decrypt.Decryption(Institute_id);
		return documentmasterRepository.getAllDeactiveDocument(institute_id1);
	}

	@Override
	public DocumentMaster getById(int document_master_id, String Institute_id) {
		String institute_id1 = decrypt.Decryption(Institute_id);
		return documentmasterRepository.findByDocuments_master_id(document_master_id, institute_id1);
	}


	

	
}
