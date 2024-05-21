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

import com.school.beans.SubjectMaster;

import com.school.commom.responses.JsonResponses;
import com.school.repository.InstituteRepository;
import com.school.repository.SubjectMasterRepository;
import com.school.request.SubjectMasterRequest;
import com.school.service.SubjectMasterService;

@RestController
@RequestMapping("subjectmaster")
public class SubjectMasterController {

	@Autowired
	InstituteRepository instituteRepository;

	@Autowired
	SubjectMasterService subjectmasterservice;

	@Autowired
	SubjectMasterRepository subjectmasterRepository;

	@PostMapping("{institute_id}")
	public Map<String, Object> saveSubject(@RequestBody SubjectMasterRequest subject,
			@PathVariable String institute_id) {

		System.out.println(subject.getSubject_name());
		
		SubjectMaster savesubject = subjectmasterservice.saveSubjectMaster(subject, institute_id);

		return savesubject != null ? JsonResponses.generateResponse1(true, savesubject, "subject Added")
				: JsonResponses.generateResponse1(false, subject, "Invalid Data");

	}

	@GetMapping("{institute_id}")
	public Map<String, Object> getallSubject(@PathVariable String institute_id) {
		List<SubjectMaster> allsubjects = subjectmasterservice.getAllSubject(institute_id);

		return !allsubjects.isEmpty() ? JsonResponses.generateResponse1(true, allsubjects, "all subject fetched")
				: JsonResponses.generateResponse1(false, allsubjects, "No Subject Found");

	}

	@PutMapping("/update/{institute_id}/{subject_id}")
	public Map<String, Object> updatesubject(@RequestBody SubjectMasterRequest subject, @PathVariable int subject_id,
			@PathVariable String institute_id) {
		SubjectMaster oldsubject = subjectmasterservice.getById(subject_id, institute_id);

		if (oldsubject != null) {
			oldsubject.setSubject_name(subject.getSubject_name());
			

			return subjectmasterRepository.save(oldsubject) != null
					? JsonResponses.generateResponse1(true,subjectmasterRepository.save(oldsubject) , "subject updated")
					: JsonResponses.generateResponse1(false, subject, "Invalid data");
		}

		return JsonResponses.generateResponse1(false, subject, "Invalid data");

	}

	@GetMapping("edit/{institute_id}/{subject_id}")
	public Map<String, Object> getSubject(@PathVariable int subject_id, @PathVariable String institute_id) {
		SubjectMaster subjectdata = subjectmasterservice.getById(subject_id, institute_id);

		return subjectdata != null ? JsonResponses.generateResponse1(true, subjectdata, "all subject fetched")
				: JsonResponses.generateResponse1(false, subjectdata,
						"Not subject Found for this id" + subject_id + "");
	}

	@GetMapping("/active/{institute_id}")
	public Map<String, Object> getAllActive(@PathVariable String institute_id) {
		List<SubjectMaster> allactive = subjectmasterservice.getActiveSubejct(institute_id);

		return !allactive.isEmpty() ? JsonResponses.generateResponse1(true, allactive, "all active subject fetched")
				: JsonResponses.generateResponse1(false, allactive, "No Active Subject Available");
	}

	@GetMapping("/deactive/{institute_id}")
	public Map<String, Object> getAllDective(@PathVariable String institute_id) {
		List<SubjectMaster> alldeactive = subjectmasterservice.getDeactiveSubject(institute_id);

		return !alldeactive.isEmpty()
				? JsonResponses.generateResponse1(true, alldeactive, "all deactive subject fetched")
				: JsonResponses.generateResponse1(false, alldeactive, "No Deactive Subject Available");
	}

	@PutMapping("active/{institute_id}/{subject_id}")
	public Map<String, Object> activeTransactionTypeMaster(@PathVariable int subject_id,
			@PathVariable String institute_id) {
		SubjectMaster subject1 = subjectmasterservice.getById(subject_id, institute_id);

		if (subject1 != null) {
			subject1.setStatus(1);
			SubjectMaster activesubject = subjectmasterRepository.save(subject1);
			return activesubject != null ? JsonResponses.generateResponse1(true, activesubject, "Subject  Activated")
					: JsonResponses.generateResponse1(false, subject_id, "Not Activated");

		}

		return JsonResponses.generateResponse1(false, subject_id, "Subject Not Found for this Id " + subject_id + "");

	}

	@DeleteMapping("deactive/{institute_id}/{subject_id}")
	public Map<String, Object> deactiveTransactionTypeMaster(@PathVariable int subject_id,
			@PathVariable String institute_id) {
		SubjectMaster subject1 = subjectmasterservice.getById(subject_id, institute_id);

		if (subject1 != null) {
			subject1.setStatus(0);
			SubjectMaster deactivesubject = subjectmasterRepository.save(subject1);
			return deactivesubject != null
					? JsonResponses.generateResponse1(true, deactivesubject, "Subject  Deactivated")
					: JsonResponses.generateResponse1(false, subject_id, "Not Deactivated");

		}

		return JsonResponses.generateResponse1(false, subject_id, "Subject Not Found for this Id " + subject_id + "");

	}

}
