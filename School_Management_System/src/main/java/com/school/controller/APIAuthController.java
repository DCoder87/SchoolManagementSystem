package com.school.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Random;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.school.Jwt.AuthRequest;
import com.school.Jwt.AuthResponse;
import com.school.Jwt.JwtTokenUtil;
import com.school.beans.Institute;
import com.school.beans.ResetPasswordOtp;
import com.school.beans.Role;
import com.school.beans.Users;
import com.school.commom.responses.JsonResponses;
import com.school.dao.EncryptionAndDecryption;
import com.school.repository.RoleRepository;
import com.school.repository.UsersRepository;
import com.school.request.*;
import com.school.service.EmailSenderService;
import com.school.service.InstituteService;
import com.school.service.PasswordResetOtpService;
import com.school.service.RoleService;
import com.school.service.UsersService;

@RestController
public class APIAuthController {

	@Autowired
	AuthenticationManager authManager;

	@Autowired
	JwtTokenUtil jwtUtil;

	@Autowired
	PasswordResetOtpService passwordResetOtpService;

	@Autowired
	private EmailSenderService senderService;

	@Autowired
	InstituteService instituteService;

	@Autowired
	private ObjectMapper mapper;

	@Autowired
	UsersService usersService;

	@Autowired
	UsersRepository usersRepository;

	@Autowired
	RoleService roleService;

	@Autowired
	RoleRepository roleRepository;

	Random random = new Random(1000);

	LocalDateTime today = LocalDateTime.now();

	String today1 = today.toString();

	EncryptionAndDecryption encrypt = new EncryptionAndDecryption();

	PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	@Value("${file.upload-dir}")
	String FILE_DIRECTORY;

	// Login
	@Transactional
	@PostMapping("auth/login")
	public Map<String, Object> login(@RequestBody AuthRequest request) {
		try {
			Authentication authentication = authManager.authenticate(
					new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

			Users users = (Users) authentication.getPrincipal();

			Institute institute = users.getInstitute();
			int institute_id = institute.getInstitute_id();

			String encoded = encrypt.Encryption(institute_id);

			String accessToken = jwtUtil.generateAccessToken(users);
			AuthResponse response = new AuthResponse(users.getUsername(), accessToken, encoded);
			// return ResponseEntity.ok(response);
			return JsonResponses.generateResponse1(true, response, "User Logged in Successfully");

		} catch (BadCredentialsException e) {
			return JsonResponses.generateResponse1(false, request, "Bad Credentials");
		}
	}

	// User register
	@PostMapping("auth/register")
	public Map<String, Object> registerUser(@RequestBody UserRequest users) {
		String userEmail = users.getEmail();
		Users u2 = usersService.findByEmail(userEmail);
		Users username = usersService.findByUsername(users.getUsername());
		Role roleUser1 = roleService.findByRollname("ROLE_USER");
		if (roleUser1 == null) {
			Role r2 = new Role();
			r2.setRollname("ROLE_USER");
			r2.setRoleDescription("This is Default Role for Newly Created User");
			roleRepository.save(r2);
		}
		if (u2 == null && username == null) {

			Institute institute = new Institute();
			institute.setInstitute_id(users.getInstitute_id());

			Users mainUser = new Users();

			mainUser.setPassword(passwordEncoder.encode(users.getPassword()));
			mainUser.setFirst_name(users.getFirst_name());
			mainUser.setLast_name(users.getLast_name());
			mainUser.setEmail(userEmail);
			mainUser.setUsername(users.getUsername());
			mainUser.setStatus(1);
			mainUser.setInstitute(institute);
			mainUser.setCreated_at(today);

			Role roleUser = roleService.findByRollname("ROLE_USER");

			mainUser.addRole(roleUser);

			Users details = usersRepository.save(mainUser);

			return JsonResponses.generateResponse1(true, details, "User Registered Successfully");
		} else {
			return JsonResponses.generateResponse1(false, userEmail, "Email Or Username Already Exists");
		}

	}

	@Transactional
	@PostMapping("send-otp")
	public Map<String, Object> SendOtp(@RequestParam String email) {
		Users user = usersService.findByEmail(email);

		int otp = random.nextInt(999999);

		if (user != null) {

			ResetPasswordOtp user1 = new ResetPasswordOtp();
			user1.setOtp(otp);
			user1.setUser(user);
			user1.setUser_email(email);
			user1.setStatus(1);
			user1.setOtpRequestedTime(today);

			String subject = "Password Reset OTP - Box Calculaion Portal";

			String message = "Hello <b>" + user.getFirst_name() + "</b>,<br>"
					+ "&emsp; You recently requested to reset the password for your Box Calculation account. Enter the below One time password to proceed."
					+ "<h3> Your OTP : " + otp + " </h3>"
					+ "&ensp; If you did not request a password reset, please ignore this email or reply to let us know. This password reset OTP is only valid for the next 24 Hours."
					+ "<br>" + "<br>" + "Thanks & Regards," + "<br>" + "<b>Box Calculaion team</b>" + "<br>"
					+ "<h4>Please do not reply to this e-mail, this is a system generated email sent from an unattended mail box.</h4>";

			try {
				senderService.sendHtmlEmail(subject, message, email);
			} catch (Exception e) {
				return JsonResponses.generateResponse1(false, email, "Invalid Email");
			}

			ResetPasswordOtp user2 = passwordResetOtpService.findByEmail(email);

			if (user2 == null) {
				passwordResetOtpService.savepasswordResetOtp(user2);
			} else {
				passwordResetOtpService.updateStatusByEmail(email);
				passwordResetOtpService.savepasswordResetOtp(user1);
			}

			return JsonResponses.generateResponse1(true, email, "Email Send Successfully");
		} else {
			return JsonResponses.generateResponse1(false, email, "Email Not Found");
		}
	}

	// Generating Random String
	public String generatePasswordResetToken(int length) {
		// create a string of all characters
		String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890abcdefghijklmnopqrstuvwxyz";

		// create random string builder
		StringBuilder sb = new StringBuilder();

		// create an object of Random class
		Random random = new Random();

		// specify length of random string
		// int length = 15;

		for (int i = 0; i < length; i++) {

			// generate random index number
			int index = random.nextInt(alphabet.length());

			// get character specified by index
			// from the string
			char randomChar = alphabet.charAt(index);

			// append the character to string builder
			sb.append(randomChar);
		}

		String randomString = sb.toString();

		return randomString;

	}

	// Verify Otp and update status of that email Id
	@Transactional
	@PostMapping("verify-otp")
	public Map<String, Object> verifyOtp(@RequestBody verifyOtpRequest otpRequest) {
		String email = otpRequest.getEmail();

		try {
			ResetPasswordOtp user = passwordResetOtpService.findByEmail(email);

			// Otp Validity Check
			boolean timecheck = user.isOTPRequired();

			// DB Otp
			int dbotp = user.getOtp();

			// User Otp
			int otp = otpRequest.getOtp();

			if (user != null && dbotp == otp) {
				if (timecheck == false) {
					return JsonResponses.generateResponse1(false, otp,
							"Otp Expired... Please generate a new One Time Password...!!!");
				}

				String tokenValue = this.generatePasswordResetToken(16);

				passwordResetOtpService.updateStatusByEmailAndResetToken(email, tokenValue);

				return JsonResponses.generateResponse1(true, tokenValue, "Otp Verified Successfully");
			} else {
				return JsonResponses.generateResponse1(false, otp, "Otp Not Matched");
			}
		} catch (Exception e) {
			return JsonResponses.generateResponse1(false, email, "Invalid Email");
		}
	}

	@Transactional
	@PutMapping("update-password/{token}")
	public Map<String, Object> updatePassword(@PathVariable String token,
			@RequestBody updatePasswordRequest passwordRequest) {

		try {

			ResetPasswordOtp Dbuser = passwordResetOtpService.findByResetToken(token);

			String resetToken = Dbuser.getReset_token();

			if (token.equals(resetToken)) {

				String email = passwordRequest.getEmail();

				String pwd = passwordRequest.getPassword();
				String password = passwordEncoder.encode(pwd);

				int institute_id = passwordRequest.getInstitute_id();

				String institute_id2 = String.valueOf(institute_id);

				Users user1 = usersService.findByEmail(email);

				int user_id = user1.getId();

				if (user1 != null && password != null) {

					usersService.updatePasswordByEmailAndId(password, email, user_id, institute_id2);

					return JsonResponses.generateResponse1(true, email, "Password Changed Successfully");
				} else {
					return JsonResponses.generateResponse1(false, email, "Password Not Changed");
				}
			} else {
				return JsonResponses.generateResponse1(false, Dbuser, "Token Not Found");
			}
		} catch (Exception e) {
			return JsonResponses.generateResponse1(false, token, "Token is Invalid");
		}
	}

	@SuppressWarnings("resource")
	@PostMapping("/auth/institute")
	public Map<String, Object> addinstitute(@RequestParam MultipartFile file, @RequestParam String InstituteData)
			throws IOException {
		try {
			File myFile = new File(FILE_DIRECTORY + file.getOriginalFilename());
			myFile.createNewFile();
			FileOutputStream fos = new FileOutputStream(myFile);
			// System.out.println(myFile);
			String instituteLogo = file.getOriginalFilename();

			String allPath = FILE_DIRECTORY.concat(instituteLogo);
			// System.out.println("Actual File Path is " + allPath);

			// Converting String into JSON
			InstituteRequest InstituteData1 = null;
			Institute InstituteDeails = null;
			try {
				InstituteData1 = mapper.readValue(InstituteData, InstituteRequest.class);

				String institute_name = InstituteData1.getInstitute_name();
				String institute_email = InstituteData1.getEmail();

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

				Institute instituteNameAvl = instituteService.checkInstituteExistsOrNotByName(institute_name);
				Institute instituteEmailAvl = instituteService.checkInstituteExistsOrNotByEmail(institute_email);
				if (instituteNameAvl != null || instituteEmailAvl != null) {
					return JsonResponses.generateResponse1(false, InstituteData,
							"institute Already Exists.. Please Contact Administrator...!");
				}
				InstituteDeails = instituteService.saveInstitute(Institute);

				// int id = instituteDeails.getinstitute_id();
				// System.out.println("institute_id " + id);

			} catch (JsonProcessingException e) {
				return JsonResponses.generateResponse1(false, InstituteData, "Invalid Data");
			}
			fos.write(file.getBytes());
			fos.close();

			return JsonResponses.generateResponse1(true, InstituteDeails, "institute Data Saved Successfully");
		} catch (JsonProcessingException e) {
			return JsonResponses.generateResponse1(false, InstituteData, "Invalid Path");
		}

	}

}
