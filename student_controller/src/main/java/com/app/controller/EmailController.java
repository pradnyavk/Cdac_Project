package com.app.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.pojos.EmailData;
import com.app.util.Email;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/email")
public class EmailController {
	 Logger logger =  LoggerFactory.getLogger(TeacherController.class);
	public EmailController() {
		System.out.println("Email Controller const");
	}
	
	@PostMapping(value="/sendEmail")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<?> sendEmail(@RequestParam String emailData){
		String recipient = "kparanav@gmail.com";
		logger.info("sending email...."+emailData);
		try {
			ObjectMapper mapper = new ObjectMapper();
		    EmailData emailData1 = mapper.readValue(emailData, EmailData.class);
			Email.sendMail(emailData1, recipient);
			logger.info("emailSent");
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
