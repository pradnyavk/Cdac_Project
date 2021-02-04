package com.app.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.app.pojos.Student;
import com.app.pojos.Teacher;
import com.app.pojos.User;
import com.app.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@RestController
@RequestMapping("/user")
public class UserController {
	Logger logger = LoggerFactory.getLogger(UserController.class);
	@Autowired
	private UserService dao;

	public UserController() {
		logger.info("User Controller constructor Initiated @@");
	}

//	@GetMapping("/list")
//	@CrossOrigin(origins = "http://localhost:4200")
//	public ResponseEntity<?> getUserList(){
//		System.out.println("inside findAllUser");
//		List<User> ls = new ArrayList<>();
//		try {
//			ls = dao.findAllUser();
//		} catch (RuntimeException e) {
//			return new ResponseEntity<String>("no result found", HttpStatus.NO_CONTENT);
//		}
//		return new ResponseEntity<>(ls, HttpStatus.OK);
//	}

//	@PutMapping("/{id}")
//	@CrossOrigin(origins = "http://localhost:4200")
//	public ResponseEntity<?> updateUser(@RequestBody User user){
//		User user1 = null;
//		try {
//			user1 = dao.findUserById(user.getId());
//			if(user1 != null) {
//			     user1 = user;
//				dao.saveUser(user1);
//			}
//		}catch (RuntimeException e) {
//			return new ResponseEntity<>("Error while updating.. try again", HttpStatus.NO_CONTENT);
//		}
//		return new ResponseEntity<>("data updated successfully..", HttpStatus.OK);
//	}
//	
//	@DeleteMapping("/{id}")
//	@CrossOrigin(origins = "http://localhost:4200")
//	public ResponseEntity<?> deleteUserById(@PathVariable String id){
//		System.out.println("inside deleteTeacher");
//		long id1 = Long.parseLong(id);
//		User user = null;
//		try {
//			user = dao.deleteById(id1);
//		} catch (RuntimeException e) {
//			return new ResponseEntity<>("Error while Deleting", HttpStatus.NO_CONTENT);
//		}
//		return new ResponseEntity<>(user, HttpStatus.OK);	
//	}

//===============================================================================================

//finding user by its id 
	
	@GetMapping("/{id}")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<?> getUserById(@PathVariable String id) {
		System.out.println("inside find User by Id");
		long id1 = Long.parseLong(id);
		User user = null;
		try {
			user = dao.findUserById(id1);
		} catch (RuntimeException e) {
			return new ResponseEntity<String>("no result found", HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	
//new user registration without profile pic
	@PostMapping
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<?> saveUser(@RequestBody User user) {
		logger.info("insde save user scope");
		User user1 = null;
		try {
			logger.info("start saving .....");
			user1 = dao.saveUser(user);
			logger.info("out of scope");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Something wrong .... while saving user....." + e.getStackTrace());
			return new ResponseEntity<String>("Error while saving", HttpStatus.EXPECTATION_FAILED);
		}
		return new ResponseEntity<>(user1, HttpStatus.OK);
	}

// new User registration with profile picture 
	@PostMapping("/withPP")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<?> saveUser(@RequestParam String user, @RequestParam MultipartFile pp) {
		logger.info("insde save user scope pp");
		User user1 = null;
		try {

			ObjectMapper mapper = new ObjectMapper();
			user1 = mapper.readValue(user, User.class);
			logger.info("user deserialize");
			user1.setPp(pp.getBytes());
			user1.setPpType(pp.getContentType());
			logger.info("start saving .....");
			user1 = dao.saveUser(user1);
			logger.info("out of scope");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Something wrong .... while saving user" + e.getStackTrace());
			return new ResponseEntity<String>("Error while saving", HttpStatus.EXPECTATION_FAILED);
		}
		return new ResponseEntity<>(user1, HttpStatus.OK);
	}

// login verification of user
	@PostMapping("/veryfication")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<?> veryfieUser(@RequestBody User user) {
		logger.info("================login request{} time of User "+user.getEmail()+" is : "+LocalDate.now()+"=========");
		User user1 = null;
		user1 = dao.findUserByEmail(user.getEmail());
		System.out.println(user1);
		System.out.println(user);
		if (user1.getPassword().equals(user.getPassword())) {
			logger.info("verification done !!!!!!");
			return new ResponseEntity<User>(user1, HttpStatus.OK);
		}
		return new ResponseEntity<String>("invalid Email or password..", HttpStatus.OK);
	}
	
//adding new student to user giving user Id 
	@PutMapping(value = "/{id}/addStudent")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<?> addStudent(@RequestParam String student, @PathVariable String id) {
		logger.info("\n================= AddStudent function started ===========================\n");
		User user = null;
		List<Student> ls = new ArrayList<Student>();
		long id1 = Long.parseLong(id);
		System.out.println(id1);

		try {

			ObjectMapper mapper = new ObjectMapper();
			mapper.registerModule(new JavaTimeModule());
			Student s1 = mapper.readValue(student, Student.class);
			logger.info("searchind user....");
			user = dao.findUserById(id1);
			logger.info("user found");
			user.addStudent(s1);
			user = dao.saveUser(user);
			logger.info("student added successfully");
			ls = user.getStudents();
			for (Student s : ls) {
				s.setUserName(null);
			}
			logger.info("out of AddStudent scope");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Something wrong ... with adding student\n" + e.getStackTrace());
			return new ResponseEntity<List<Student>>(ls, HttpStatus.EXPECTATION_FAILED);
		}
		return new ResponseEntity<List<Student>>(ls, HttpStatus.OK);
	}
	
//adding new teacher user user id and teacher data
	@PostMapping(value = "/teacher/{id}")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<?> addTeacher(@RequestParam String teacherData, @RequestParam MultipartFile doc,
			@PathVariable String id) {
		logger.info("\n========================Adding Teacher to User==========================\n");
		Teacher teacher = null;
		long id1 = Long.parseLong(id);
		try {

			ObjectMapper mapper = new ObjectMapper();
			mapper.registerModule(new JavaTimeModule());
			teacher = mapper.readValue(teacherData, Teacher.class);
			teacher.setResume(doc.getBytes());
			teacher.setDocType(doc.getContentType());

			logger.info("searching user......");
			User u = dao.findUserById(id1);
			logger.info("user found");
			u.addTeacher(teacher);
			logger.info("teacher added successfully");
			User u1 = dao.save(u);
			teacher = u1.getTeachers().get(0);
			logger.info("out of scope!!!!!");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Something wrong .... while adding teacher :" + e.getMessage());
			return new ResponseEntity<Teacher>(teacher, HttpStatus.EXPECTATION_FAILED);
		}
		return new ResponseEntity<Teacher>(teacher, HttpStatus.OK);
	}
}
