package com.app.controller;


import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.pojos.Course;
import com.app.pojos.Session;
import com.app.pojos.Student;
import com.app.pojos.Teacher;
import com.app.service.StudentService;


@RestController
@RequestMapping("/student")
public class StudentController {
	
	@Autowired
	private StudentService dao;
	
	public StudentController() {
		System.out.println("in Student Controller contructor..");
	}
	
	@PostConstruct
	public void init() {
		System.out.println("inside init method");
	}
   
	@GetMapping("/list")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<?> findAllStudent(){
		List<Student> ls = new ArrayList<>();
		try {
		ls = dao.findAllStudent();
		}catch(RuntimeException e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(ls, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<Student> findStudentById(@PathVariable String id){
		System.out.println("inside findStudentById ");
		Student student = null;
		long id1 = Long.parseLong(id);
		try {
		student = dao.findStudentById(id1);
		}catch(RuntimeException e) {
			return new ResponseEntity<Student>(HttpStatus.NOT_FOUND);
		}	
		return new ResponseEntity<Student>(student, HttpStatus.OK);
		
	}
	
	@PostMapping(
			consumes = {org.springframework.http.MediaType.APPLICATION_JSON_VALUE}
			)
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<?> saveStudent(@RequestBody Student student){

		try {
		dao.saveStudent(student);
		
		}catch(RuntimeException e) {
			return new ResponseEntity<String>("student data cannot be null", HttpStatus.BAD_REQUEST); 
		}
		return new ResponseEntity<String>("Student data successfully saved", HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<?> updateStudent(@RequestBody Student student, @PathVariable String id){
		
	  try {
		long id1 = Long.parseUnsignedLong(id);
	  Student s = dao.findStudentById(id1);
	  s.setStudentName(student.getStudentName());
	  s.setDob(student.getDob());
	  s.setLocation(student.getLocation());
	  dao.saveStudent(s);
	  }catch(RuntimeException e) {
		  return new ResponseEntity<String>("something went worng try agian", HttpStatus.NO_CONTENT);
	  }
	  return new ResponseEntity<String>("data updated", HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<?> deleteStudent(@PathVariable String id){
		Student s = null;
		long id1 = Long.parseLong(id);
		try {
			s = dao.deleteById(id1);
		}catch(RuntimeException e) {
			return new ResponseEntity<Student>(s, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Student>(s, HttpStatus.OK);
	}
	
	@PutMapping("/{id}/teacher")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<?> addTeacher(@RequestBody Teacher teacher, @PathVariable String id){
		Student s = null ;
		long id1 = Long.parseLong(id);
		try {
			s = dao.findStudentById(id1);
			s.addTeacher(teacher);
		}catch(RuntimeException e) {
			return new ResponseEntity<Student>(s, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Student>(s, HttpStatus.OK);	
	}
	
	@PutMapping("/{id}/course")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<?> addCourse(@RequestBody Course course, @PathVariable String id){
		Student s = null;
		long id1 = Long.parseLong(id);
		try {
			s = dao.findStudentById(id1);
			s.addCourse(course);
		}catch(RuntimeException e) {
			return new ResponseEntity<Student>(s, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Student>(s, HttpStatus.OK);
		
	}
	
	@PutMapping("/{id}/session")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<?> addCourse(@RequestBody Session session, @PathVariable String id){
		Student s = null;
		long id1 = Long.parseLong(id);
		try {
			s = dao.findStudentById(id1);
			s.addSession(session);
		}catch(RuntimeException e) {
			return new ResponseEntity<Student>(s, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Student>(s, HttpStatus.OK);
		
	}
	
	@GetMapping("/user/{id}")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<?> getStudentsWhereUser(@PathVariable String id){
	    List<Student> students = new ArrayList<>();
		long id1 = Long.parseLong(id);
		try {
			students = dao.findAllStudentByUserId(id1);
		} catch (Exception e) {
			return new ResponseEntity<List<Student>>(students, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Student>>(students, HttpStatus.OK);
	}
}
