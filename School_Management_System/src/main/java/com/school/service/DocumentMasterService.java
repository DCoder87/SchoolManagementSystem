package com.school.service;

import java.util.List;

import com.school.beans.DocumentMaster;
import com.school.request.DocumentMasterRequest;

public interface DocumentMasterService {
	
	DocumentMaster saveDocument(DocumentMasterRequest document,String Institute_Id);
	
	List<DocumentMaster> allDocuments(String Institute_id);
	
	List<DocumentMaster> allActiveDocuments(String Institute_id);
	
	List<DocumentMaster> allDeactiveDocuments(String Institute_id);
	
    DocumentMaster getById(int document_master_id,String Institute_id);
}
