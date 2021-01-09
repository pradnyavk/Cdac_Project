package com.app.controller;

import java.util.ArrayList;
import java.util.List;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.pojos.Course;
import com.app.pojos.Session;
import com.app.pojos.Student;
import com.app.pojos.Teacher;
import com.app.service.TeacherService;

@RestController
@RequestMapping("/teacher")
public class TeacherController {
	
	@Autowired
	private TeacherService dao;
	
	public TeacherController() {
		System.out.println("inside teacher Controller..");
	}
	
	@GetMapping("/list")
	public ResponseEntity<?> findAllTeacher(){
		System.out.println("inside findAllTeacher");
		List<Teacher> ls = new ArrayList<>();
		try {
			ls = dao.findAllTeacher();
		} catch (RuntimeException e) {
			return new ResponseEntity<String>("no result found", HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(ls, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> findTeacherById(@PathVariable String id){
		System.out.println("inside findTeacher by Id");
		long id1 = Long.parseLong(id);
		Teacher t = null;
		try {
			t = dao.findTeacherById(id1);
		} catch (RuntimeException e) {
			return new ResponseEntity<String>("no result found", HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(t, HttpStatus.OK);
	}
	@PostMapping(
			consumes = {org.springframework.http.MediaType.APPLICATION_JSON_VALUE}
			)
	public ResponseEntity<?> saveTeacher(@RequestBody Teacher t){
		System.out.println("inside saveTeacher");
		Teacher t1 = null;
		try {
			t1 = dao.saveTeacher(t);
		} catch (RuntimeException e) {
			return new ResponseEntity<String>("Error while saving", HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(t1, HttpStatus.OK);
	}
	@PutMapping("/{id}")
	public ResponseEntity<?> updateTeacher(@RequestBody Teacher t, @PathVariable String id){
		System.out.println("inside update Teacher");
		long id1 = Long.parseLong(id);
		Teacher t1 = null;
		try {
			t1 = dao.findTeacherById(id1);
			if(t1 != null) {
				t1 = t;
				dao.saveTeacher(t1);
			}
		}catch (RuntimeException e) {
			return new ResponseEntity<>("Error while updating.. try again", HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>("data updated successfully..", HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteTeacher(@PathVariable String id){
		System.out.println("inside deleteTeacher");
		long id1 = Long.parseLong(id);
		Teacher t1 = null;
		try {
			t1 = dao.deleteById(id1);
		} catch (RuntimeException e) {
			return new ResponseEntity<>("Error while Deleting", HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(t1, HttpStatus.OK);
	}
	
	@PutMapping("/{id}/student")
	@CrossOrigin(origins = "http://localhost:4200") // 	CORS
	public ResponseEntity<?> addStudent(@RequestBody Student student, @PathVariable String id){
		Teacher t = null;
		long id1 = Long.parseLong(id);
		try {
			t = dao.findTeacherById(id1);
			t.addStudent(student);
		}catch(RuntimeException e) {
			return new ResponseEntity<Teacher>(t, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Teacher>(t, HttpStatus.OK);
		
	}
	
	@PutMapping("/{id}/course")
	@CrossOrigin(origins = "http://localhost:4200") // 	CORS
	public ResponseEntity<?> addCourse(@RequestBody Course course, @PathVariable String id){
		Teacher t = null;
		long id1 = Long.parseLong(id);
		try {
			t = dao.findTeacherById(id1);
			t.addCourse(course);
		}catch(RuntimeException e) {
			return new ResponseEntity<Teacher>(t, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Teacher>(t, HttpStatus.OK);
		
	}
	
	@PutMapping("/{id}/Session")
	@CrossOrigin(origins = "http://localhost:4200") // 	CORS
	public ResponseEntity<?> addSession(@RequestBody Session session, @PathVariable String id){
		Teacher t = null;
		long id1 = Long.parseLong(id);
		try {
			t = dao.findTeacherById(id1);
			t.addSession(session);
		}catch(RuntimeException e) {
			return new ResponseEntity<Teacher>(t, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Teacher>(t, HttpStatus.OK);
		
	}
	
	@GetMapping("/user/{id}")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<?> getStudentsWhereUser(@PathVariable String id){
	    List<Teacher> teachers = new ArrayList<>();
		long id1 = Long.parseLong(id);
		try {
			teachers = dao.findAllTeacherByUserId(id1);
		} catch (Exception e) {
			return new ResponseEntity<List<Teacher>>(teachers, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Teacher>>(teachers, HttpStatus.OK);
	}
	
	@GetMapping("/list/detail")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<?> getTeacherWhereSubjectAndADD(@RequestParam String subject, @RequestParam String state, @RequestParam String city){
		List<Teacher> teachers = new ArrayList<>();
		List<Teacher> teacher = new ArrayList<>();
		Course c = new Course(subject);
		try {
			teacher = dao.getTeachersBySubjectAndAdd( state,  city);
			for(Teacher t : teacher) {
				if(t.getCourses().contains(c)) {
					teachers.add(t);
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<Teacher>>(teachers, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Teacher>>(teachers, HttpStatus.OK);
	}
}
