package com.app.controller;
import java.util.HashSet;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.pojos.CourseName;
import com.app.pojos.SubjectName;

@RestController
@RequestMapping("/list")
public class EnumsController {
	
	public EnumsController() {
		System.out.println("inside EnumController");
	}
	
	@GetMapping("/courses")
	public ResponseEntity<?> findListOfCourses(){
		System.out.println("inside find list of courses");
		Set<String> courses =new HashSet<>();
		for(CourseName c: CourseName.values()) {
			courses.add(c.name());
		}
		return new ResponseEntity<>(courses,HttpStatus.OK);
	}
	
	@GetMapping("/subjects")
	public ResponseEntity<?> findListOf(){
		System.out.println("inside find list of subjects");
		Set<String> subjects =new HashSet<>();
		for(SubjectName s: SubjectName.values()) {
			subjects.add(s.name());
		}
		return new ResponseEntity<>(subjects,HttpStatus.OK);
	}
}
