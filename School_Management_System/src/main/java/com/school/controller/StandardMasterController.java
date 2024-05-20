package com.school.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.school.beans.StandardMaster;
import com.school.commom.responses.JsonResponses;
import com.school.repository.StandardMasterRepository;
import com.school.request.StandardMasterRequest;
import com.school.service.StandardMasterService;


@RestController
@RequestMapping("standard")
public class StandardMasterController {
	
	@Autowired
	StandardMasterService standardmasterservice;
	
	@Autowired
	StandardMasterRepository standardmasterRepository;
	
	@PostMapping("/{institute_id}")
	public Map<String,Object> saveStandardMaster(@RequestBody StandardMasterRequest standardmaster,@PathVariable String institute_id)
	{
		StandardMaster savedstandard=standardmasterservice.savestandardMaster(standardmaster, institute_id);
		
		return savedstandard!=null?JsonResponses.generateResponse1(true, savedstandard,"Standard Saved"):
			JsonResponses.generateResponse1(false, standardmaster,"Invalid-data");
	}
	
	@GetMapping("{institute_id}")
	public Map<String,Object> getAllStadards(@PathVariable String institute_id)
	{
	   List<StandardMaster> allstandard=standardmasterservice.getAllStandard(institute_id);
	   
	   return allstandard.isEmpty()?JsonResponses.generateResponse1(false, allstandard, "No Record Found"):
		   JsonResponses.generateResponse1(true, allstandard,"All Standards Fetched");
	   
	}
	
	@PutMapping("/update/{institute_id}/{standard_id}")
	public Map<String,Object> updateStandard(@PathVariable String institute_id,@PathVariable int standard_id,@RequestBody  StandardMasterRequest standardmaster )
	{
		StandardMaster standard=standardmasterservice.getById(standard_id, institute_id);
		
		if(standard!=null)
		{
			standard.setStandard_name(standardmaster.getStandard_name());
			standard.setStandard_id(standardmaster.getTeacher_id());
			StandardMaster updatestandard=standardmasterRepository.save(standard);
			return updatestandard!=null?JsonResponses.generateResponse1(true, updatestandard,"Standard Updated"):JsonResponses.generateResponse1(false, standardmaster,"Invalid-data");
		}
		
		return JsonResponses.generateResponse1(false, null,"Invalid-data");
	}
	
	@GetMapping("/active/{institute_id}")
	public Map<String,Object> getAllActiveStadards(@PathVariable String institute_id)
	{
	   List<StandardMaster> allstandard=standardmasterservice.getActiveStandard(institute_id);
	   
	   return allstandard.isEmpty()?JsonResponses.generateResponse1(false, allstandard, "No Active Record Found"):
		   JsonResponses.generateResponse1(true, allstandard,"All Active Standards Fetched");
	   
	}
	
	@GetMapping("/deactive/{institute_id}")
	public Map<String,Object> getAllDeactiveStadards(@PathVariable String institute_id)
	{
	   List<StandardMaster> alldeactivestandard=standardmasterservice.getDeactiveStandard(institute_id);
	   
	   return alldeactivestandard.isEmpty()?JsonResponses.generateResponse1(false, alldeactivestandard, "No Deactive Record Found"):
		   JsonResponses.generateResponse1(true, alldeactivestandard,"All Deactive Standards Fetched");
	   
	}
	
	@GetMapping("edit/{Institute_id}/{standard_id}")
	public Map<String,Object> getStadardById(@PathVariable String Institute_id,@PathVariable int standard_id)
	{
		StandardMaster oldstandard=standardmasterservice.getById(standard_id, Institute_id);
		
		return oldstandard!=null?JsonResponses.generateResponse1(true, oldstandard,"Standard found"):
			JsonResponses.generateResponse1(false, standard_id,"Invalid-data");
	}
	
	@PutMapping("active/{Institute_id}/{standard_id}")
	public Map<String,Object> activestandard(@PathVariable String Institute_id,@PathVariable int standard_id)
	{
		StandardMaster oldstandard=standardmasterservice.getById(standard_id, Institute_id);
		
		if(oldstandard!=null)
		{
			oldstandard.setStatus(1);
			StandardMaster activestandard= standardmasterRepository.save(oldstandard);
			
			return activestandard!=null?JsonResponses.generateResponse1(true, activestandard,"Standard Activated"):
				JsonResponses.generateResponse1(false, activestandard, "Standard Not Activated");
		}
		
		return JsonResponses.generateResponse1(false, standard_id,"Invalid-data");
	}
	
	@DeleteMapping("active/{Institute_id}/{standard_id}")
	public Map<String,Object> deactivestandard(@PathVariable String Institute_id,@PathVariable int standard_id)
	{
		StandardMaster oldstandard=standardmasterservice.getById(standard_id, Institute_id);
		
		if(oldstandard!=null)
		{
			oldstandard.setStatus(0);
			StandardMaster deactivestandard= standardmasterRepository.save(oldstandard);
			
			return deactivestandard!=null?JsonResponses.generateResponse1(true, deactivestandard,"Standard Deactivated"):
				JsonResponses.generateResponse1(false, deactivestandard, "Standard Not Deactivated");
		}
		
		return JsonResponses.generateResponse1(false, standard_id,"Invalid-data");
	}
}
