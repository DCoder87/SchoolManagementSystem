package com.school.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.school.beans.Institute;
import com.school.commom.responses.JsonResponses;
import com.school.request.InstituteRequest;
import com.school.service.InstituteService;

@RestController
@RequestMapping("/Institute")
public class InstituteController {

	@Autowired
	InstituteService InstituteService;

	@Autowired
	private ObjectMapper mapper;

	@Value("${file.upload-dir}")
	String FILE_DIRECTORY;

	LocalDateTime today = LocalDateTime.now();

	@SuppressWarnings("resource")
	@PostMapping
	public Map<String, Object> addInstitute(@RequestParam MultipartFile file, @RequestParam String InstituteData)
			throws IOException {
		try {
			File myFile = new File(FILE_DIRECTORY + file.getOriginalFilename());
			myFile.createNewFile();
			FileOutputStream fos = new FileOutputStream(myFile);
			// System.out.println(myFile);
			String InstituteLogo = file.getOriginalFilename();

			String allPath = FILE_DIRECTORY.concat(InstituteLogo);
			// System.out.println("Actual File Path is " + allPath);

			// Converting String into JSON
			InstituteRequest InstituteData1 = null;
			Institute InstituteDeails = null;
			try {
				InstituteData1 = mapper.readValue(InstituteData, InstituteRequest.class);

				// Institute
				Institute Institute = new Institute();
				Institute.setInstitute_name(InstituteData1.getInstitute_name());
				Institute.setAddress(InstituteData1.getAddress());
				Institute.setInstitute_slogen(InstituteData1.getInstitute_slogen());
				Institute.setEstablished_year(InstituteData1.getEstablished_year());
				Institute.setContact_number(InstituteData1.getContact_number());
				Institute.setEmail(InstituteData1.getEmail());
				Institute.setInstitute_logo(allPath);
				Institute.setWebsite(InstituteData1.getWebsite());
				Institute.setAccregated_status(InstituteData1.getAccregated_status());
				Institute.setStatus(1);
				Institute.setCreated_at(today);

				InstituteDeails = InstituteService.saveInstitute(Institute);

			} catch (JsonProcessingException e) {
				return JsonResponses.generateResponse1(false, InstituteData, "Invalid Data");
			}
			fos.write(file.getBytes());
			fos.close();

			return JsonResponses.generateResponse1(true, InstituteDeails, "Institute Data Saved Successfully");
		} catch (JsonProcessingException e) {
			return JsonResponses.generateResponse1(false, InstituteData, "Invalid Path");
		}
	}

	@SuppressWarnings("resource")
	@PutMapping("/{Institute_id}")
	public Map<String, Object> updateInstitute(@PathVariable int Institute_id, @RequestParam MultipartFile file,
			@RequestParam String InstituteData) throws IOException {
		try {
			File myFile = new File(FILE_DIRECTORY + file.getOriginalFilename());
			myFile.createNewFile();
			FileOutputStream fos = new FileOutputStream(myFile);
			// System.out.println(myFile);
			String InstituteLogo = file.getOriginalFilename();

			String allPath = FILE_DIRECTORY.concat(InstituteLogo);
			// System.out.println("Actual File Path is " + allPath);

			// Converting String into JSON
			InstituteRequest InstituteData1 = null;
			Institute InstituteDeails = null;
			try {
				InstituteData1 = mapper.readValue(InstituteData, InstituteRequest.class);

				// Institute
				Institute Institute = new Institute();
				Institute.setInstitute_id(Institute_id);
				Institute.setInstitute_name(InstituteData1.getInstitute_name());
				Institute.setAddress(InstituteData1.getAddress());
				Institute.setInstitute_slogen(InstituteData1.getInstitute_slogen());
				Institute.setEstablished_year(InstituteData1.getEstablished_year());
				Institute.setContact_number(InstituteData1.getContact_number());
				Institute.setEmail(InstituteData1.getEmail());
				Institute.setInstitute_logo(allPath);
				Institute.setWebsite(InstituteData1.getWebsite());
				Institute.setAccregated_status(InstituteData1.getAccregated_status());
				Institute.setStatus(1);
				Institute.setCreated_at(today);

				InstituteDeails = InstituteService.saveInstitute(Institute);

			} catch (JsonProcessingException e) {
				return JsonResponses.generateResponse1(false, InstituteData, "Invalid Data");
			}
			fos.write(file.getBytes());
			fos.close();

			return JsonResponses.generateResponse1(true, InstituteDeails, "Institute Data Saved Successfully");
		} catch (JsonProcessingException e) {
			return JsonResponses.generateResponse1(false, InstituteData, "Invalid Path");
		}
	}

	@GetMapping("/edit/{institute_id}")
	public Map<String, Object> findInstituteById(@PathVariable String institute_id) {
		Institute OneInstitute = InstituteService.getInstituteById(institute_id);
		if (OneInstitute != null) {
			return JsonResponses.generateResponse1(true, OneInstitute, "Institute Data Fetched Successfully");
		} else {
			return JsonResponses.generateResponse1(false, institute_id, "Institute Not Found for Id " + institute_id);
		}
	}
}
