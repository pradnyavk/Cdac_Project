package com.app.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.pojos.Course;
import com.app.pojos.Teacher;
import com.app.pojos.User;
import com.app.service.CourseService;

@RestController
@RequestMapping("/course")
public class CourseController {
	
	@Autowired
	private CourseService dao;
	
	public CourseController() {
		System.out.println("inside Corse controller constr");
	}
	
	@GetMapping("/list")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<?> getCourseList() {
		List<Course> al = new ArrayList<>();
		try {
		al = dao.findAll();
		}catch(RuntimeException e) {
			return new ResponseEntity<List<Course>>(al, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Course>>(al, HttpStatus.OK);
	}
	
	/*
	 * @GetMapping("/{name}")
	 * 
	 * @CrossOrigin(origins = "http://localhost:4200") public ResponseEntity<?>
	 * findCourseByName(@PathVariable String name){ Course c = null; List<Teacher>
	 * ls= new ArrayList<>(); try { c = dao.findCourseByCourseName(name); ls =
	 * c.getTeachers(); } catch (RuntimeException e) { return new
	 * ResponseEntity<List<Teacher>>(ls, HttpStatus.NO_CONTENT); } return new
	 * ResponseEntity<List<Teacher>>(ls, HttpStatus.OK); }
	 */
}
