package com.school.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.DeleteMapping;
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
import com.school.beans.Teacher;
import com.school.commom.responses.JsonResponses;
import com.school.dao.EncryptionAndDecryption;
import com.school.request.TeacherRequest;
import com.school.service.TeacherService;

@RestController
@RequestMapping("/teacher")
public class TeacherController {

	@Autowired
	TeacherService teacherService;

	@Autowired
	private ObjectMapper mapper;

	@Value("${file.upload-dir}")
	String FILE_DIRECTORY;

	EncryptionAndDecryption decrypt = new EncryptionAndDecryption();

	@GetMapping("/{institute_id}")
	public Map<String, Object> getTeachers(@PathVariable String institute_id) {

		List<Teacher> allTeachers = teacherService.getallTeachers(institute_id);

		if (allTeachers.isEmpty()) {
			return JsonResponses.generateResponse1(false, allTeachers, "Institution Id is Invalid Or List is Empty");
		} else {
			return JsonResponses.generateResponse1(true, allTeachers, "Teachers Details Get Successfully");
		}
	}

	@SuppressWarnings("resource")
	@PostMapping("/{institute_id}")
	public Map<String, Object> addTeacher(@RequestParam MultipartFile file, @PathVariable String institute_id,
			@RequestParam String TeacherData) throws IOException {
		try {
			File myFile = new File(FILE_DIRECTORY + file.getOriginalFilename());
			myFile.createNewFile();
			FileOutputStream fos = new FileOutputStream(myFile);

			String TeacherProfile = file.getOriginalFilename();

			String allPath = FILE_DIRECTORY.concat(TeacherProfile);

			TeacherRequest TeacherData1 = null;
			Teacher TeacherDetails = null;
			try {
				String institute_id1 = decrypt.Decryption(institute_id);
				int i_id = Integer.parseInt(institute_id1);

				TeacherData1 = mapper.readValue(TeacherData, TeacherRequest.class);

				Institute institute = new Institute();
				institute.setInstitute_id(i_id);

				Teacher teacher = new Teacher();
				teacher.setAddress(TeacherData1.getAddress());
				teacher.setAlternate_number(TeacherData1.getAlternate_number());
				teacher.setContact_number(TeacherData1.getContact_number());
				teacher.setEmail(TeacherData1.getEmail());
				teacher.setExperience(TeacherData1.getExperience());
				teacher.setGender(TeacherData1.getGender());
				teacher.setImage(allPath);
				teacher.setJoining_date(TeacherData1.getJoining_date());
				teacher.setQualification(TeacherData1.getQualification());
				teacher.setTeacher_name(TeacherData1.getTeacher_name());
				teacher.setStatus(1);
				teacher.setInstitute(institute);

				TeacherDetails = teacherService.saveTeacher(teacher);

			} catch (JsonProcessingException e) {
				return JsonResponses.generateResponse1(false, TeacherData, "Invalid Data");
			}
			fos.write(file.getBytes());
			fos.close();

			return JsonResponses.generateResponse1(true, TeacherDetails, "Teacher Data Saved Successfully");
		} catch (JsonProcessingException e) {
			return JsonResponses.generateResponse1(false, TeacherData, "Invalid Path");
		}
	}

	@SuppressWarnings("resource")
	@PutMapping("/update/{institute_id}/{teacher_id}")
	public Map<String, Object> updateTeacher(@RequestParam MultipartFile file, @PathVariable String institute_id,
			@PathVariable int teacher_id, @RequestParam String TeacherData) throws IOException {
		try {
			File myFile = new File(FILE_DIRECTORY + file.getOriginalFilename());
			myFile.createNewFile();
			FileOutputStream fos = new FileOutputStream(myFile);

			String TeacherProfile = file.getOriginalFilename();

			String allPath = FILE_DIRECTORY.concat(TeacherProfile);

			TeacherRequest TeacherData1 = null;
			Teacher TeacherDetails = null;
			try {
				String institute_id1 = decrypt.Decryption(institute_id);
				int i_id = Integer.parseInt(institute_id1);

				TeacherData1 = mapper.readValue(TeacherData, TeacherRequest.class);

				Institute institute = new Institute();
				institute.setInstitute_id(i_id);

				Teacher teacher = new Teacher();
				teacher.setAddress(TeacherData1.getAddress());
				teacher.setAlternate_number(TeacherData1.getAlternate_number());
				teacher.setContact_number(TeacherData1.getContact_number());
				teacher.setEmail(TeacherData1.getEmail());
				teacher.setExperience(TeacherData1.getExperience());
				teacher.setGender(TeacherData1.getGender());
				teacher.setImage(allPath);
				teacher.setJoining_date(TeacherData1.getJoining_date());
				teacher.setQualification(TeacherData1.getQualification());
				teacher.setTeacher_name(TeacherData1.getTeacher_name());
				teacher.setTeacher_id(teacher_id);
				teacher.setStatus(1);
				teacher.setInstitute(institute);

				TeacherDetails = teacherService.saveTeacher(teacher);

			} catch (JsonProcessingException e) {
				return JsonResponses.generateResponse1(false, TeacherData, "Invalid Data");
			}
			fos.write(file.getBytes());
			fos.close();

			return JsonResponses.generateResponse1(true, TeacherDetails, "Teacher Data Saved Successfully");
		} catch (JsonProcessingException e) {
			return JsonResponses.generateResponse1(false, TeacherData, "Invalid Path");
		}
	}

	@Transactional
	@DeleteMapping("/delete/{institute_id}/{teacher_id}")
	public Map<String, Object> deleteTeacherById(@PathVariable String institute_id, @PathVariable int teacher_id)
			throws Exception {

		int deleted = teacherService.deleteTeacherByid(teacher_id, institute_id);

		if (deleted == 1) {
			return JsonResponses.generateResponse2(true, "Teacher Deleted Successfully");
		} else {
			return JsonResponses.generateResponse2(false, "No Teacher Found for this ID: " + teacher_id);
		}

	}

	@GetMapping("/edit/{institute_id}/{teacher_id}")
	public Map<String, Object> findTeacherById(@PathVariable String institute_id, @PathVariable int teacher_id) {
		Teacher teacher = teacherService.findTeacherById(teacher_id, institute_id);
		if (teacher != null) {
			return JsonResponses.generateResponse1(true, teacher, "Teacher Data Fetched Successfully");
		} else {
			return JsonResponses.generateResponse1(false, teacher_id, "Teacher Not Found for ID: " + teacher_id);
		}
	}

}
