package com.school.controller;

import java.util.Map;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.school.beans.DocumentMaster;

import com.school.commom.responses.JsonResponses;
import com.school.repository.DocumentMasterRepository;
import com.school.repository.InstituteRepository;
import com.school.request.DocumentMasterRequest;
import com.school.service.DocumentMasterService;

@RestController
@RequestMapping("/DocumentMaster")
public class DocumentMasterController {
	
	@Autowired
	InstituteRepository instituteRepository;
	
	@Autowired
	DocumentMasterRepository documentmasterRepository;
	
	@Autowired
	DocumentMasterService documentmasterservice;
	
	@PostMapping("{institute_id}")
	public Map<String,Object> addDocument(@RequestBody DocumentMasterRequest document,@PathVariable String institute_id)
	{
		DocumentMaster savedocument=documentmasterservice.saveDocument(document, institute_id);
		
		return savedocument!=null?JsonResponses.generateResponse1(true, savedocument,"Save Document"):
			JsonResponses.generateResponse1(false,document,"document not saved");
	}
	
	@GetMapping("{institute_id}")
	public Map<String,Object> getAllDocuments(@PathVariable String institute_id)
	{
		 List<DocumentMaster> alldocument=documentmasterservice.allDocuments(institute_id);
		
		return !alldocument.isEmpty()?JsonResponses.generateResponse1(true, alldocument,"All Documents Fetched"):
			JsonResponses.generateResponse1(false, alldocument,"None Documents found");
	}
	
	@GetMapping("edit/{institute_id}/{document_master_id}")
	public Map<String,Object> getDocument(@PathVariable int document_master_id,@PathVariable String institute_id)
	{
		DocumentMaster olddocumentmaster=documentmasterservice.getById(document_master_id, institute_id);
		
		return olddocumentmaster!=null?JsonResponses.generateResponse1(true, olddocumentmaster,"document found"):
			JsonResponses.generateResponse1(false,null,"document not found for this Id"+document_master_id+"");
	}
	
	@PutMapping("{institute_id}/{document_master_id}")
	
	public Map<String,Object> updateDocument(@PathVariable int document_master_id,@RequestBody DocumentMasterRequest document,@PathVariable String institute_id)
	{
		DocumentMaster olddocumentmaster=documentmasterservice.getById(document_master_id, institute_id);
		
		if(olddocumentmaster!=null)
		{
			olddocumentmaster.setDocument_type(document.getDocument_type());
			olddocumentmaster.setDocument_description(document.getDocument_description());
			DocumentMaster updatedocument=documentmasterRepository.save(olddocumentmaster);
			
			return updatedocument!=null?JsonResponses.generateResponse1(true, updatedocument,"document Updated"):JsonResponses.generateResponse1(false, document,"Invalid-Data");
		}
		return JsonResponses.generateResponse1(false,null,"document not found for this Id"+document_master_id+"");
	}
	
	@PutMapping("active/{institute_id}/{document_master_id}")
	public Map<String,Object> activeDocumentMaster(@PathVariable int document_master_id,@PathVariable String institute_id)
	{
		DocumentMaster olddocumentmaster=documentmasterservice.getById(document_master_id, institute_id);
		if( olddocumentmaster!=null)
		{
			olddocumentmaster.setStatus(1);
			DocumentMaster activedocument=documentmasterRepository.save(olddocumentmaster);
			
			return activedocument!=null?JsonResponses.generateResponse1(true, activedocument,"document Activated"):
				JsonResponses.generateResponse1(false, activedocument,"Document Not Activated");
		}
		return JsonResponses.generateResponse1(false,null,"document not found for this Id"+document_master_id+"");
	}
	
	@DeleteMapping("delete/{institute_id}/{document_master_id}")
	public Map<String,Object> deactiveDocumentMaster(@PathVariable int document_master_id,@PathVariable String institute_id)
	{
		DocumentMaster olddocumentmaster=documentmasterservice.getById(document_master_id, institute_id);
		if( olddocumentmaster!=null)
		{
			olddocumentmaster.setStatus(0);
			DocumentMaster deactivedocument=documentmasterRepository.save(olddocumentmaster);
			
			return deactivedocument!=null?JsonResponses.generateResponse1(true, deactivedocument,"Document Deleted Successfully"):
				JsonResponses.generateResponse1(false, deactivedocument,"Document Deletion Failed");
		}
		return JsonResponses.generateResponse1(false,null,"document not found for this Id"+document_master_id+"");
	}


}
