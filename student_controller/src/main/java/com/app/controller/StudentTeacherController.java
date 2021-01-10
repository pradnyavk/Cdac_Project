package com.app.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.pojos.Student;
import com.app.pojos.StudentTeacher;
import com.app.pojos.Teacher;
import com.app.service.StudentService;
import com.app.service.StudentTeacherService;

@RestController
@RequestMapping("/studentTeacher")
public class StudentTeacherController {
	
	@Autowired
	private StudentTeacherService dao;
	@Autowired
	private StudentService dao1;
	
	public StudentTeacherController() {
		System.out.println("this is student Teacher controller");
	}
	
	
	@GetMapping("/student/{id}")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<?> getTeacherByStudentId(@PathVariable String id){
		long id1 = Long.parseLong(id);
		ArrayList<Teacher> teachers = new ArrayList<>();
		try {
			teachers = dao.findTeachersOfStudentId(id1);
		}catch(RuntimeException e) {
			return new ResponseEntity<ArrayList<Teacher>>(teachers, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<ArrayList<Teacher>>(teachers, HttpStatus.OK);
	}
	
	@GetMapping("/remove/teacher/{sid}/{tid}")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<?> removeTeacher(@PathVariable String sid, @PathVariable String tid){
		  
		long sid1 = Long.parseLong(sid);
		long tid1 = Long.parseLong(tid);
		String message = "";
		try {
			 StudentTeacher st = dao.findStudentTeacher(sid1, tid1);
			 Student s = dao1.findStudentById(sid1);
			 s.removeStudentTeacher(st);
			 dao1.saveStudent(s);
		}catch(RuntimeException e) {
			e.printStackTrace();
			return new ResponseEntity<String>(message, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<String>(message, HttpStatus.OK);
	}
	
	
}
