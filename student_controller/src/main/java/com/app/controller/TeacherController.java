package com.app.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.app.pojos.Teacher;
import com.app.service.TeacherService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/teacher")
public class TeacherController {
	 Logger logger =  LoggerFactory.getLogger(TeacherController.class);
	 
	@Autowired
	private TeacherService dao;

	public TeacherController() {
		logger.info("Teacher Controller constructor Initiated @@");
	}

	@GetMapping("/list")
	public ResponseEntity<?> findAllTeacher() {
	    logger.info("===============finding all teacher===================");
		List<Teacher> ls = new ArrayList<>();
		try {
			ls = dao.findAllTeacher();
		} catch (RuntimeException e) {
			logger.error("Something wrong while finding all teacher: "+e.getMessage());
			return new ResponseEntity<String>("no result found", HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(ls, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<?> findTeacherById(@PathVariable String id) {
		System.out.println("inside findTeacher by Id");
		long id1 = Long.parseLong(id);
		Teacher t = null;
		try {
			t = dao.findTeacherById(id1);
		} catch (RuntimeException e) {
			logger.error("Something wrong while finding teacher by id: "+e.getMessage());
			return new ResponseEntity<String>("no result found", HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Teacher>(t, HttpStatus.OK);
	}

	@PostMapping(value = "{id}",
			consumes = { org.springframework.http.MediaType.APPLICATION_JSON_VALUE })
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<?> saveTeacher(@RequestBody Teacher t, @PathVariable String id) {
		System.out.println("inside saveTeacher");
		Teacher t1 = null;
		System.out.println(t);
		try {
			t1 = dao.saveTeacher(t);
		} catch (RuntimeException e) {
			logger.error("Something wrong while saving teacher: "+e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<String>("Error while saving", HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(t1, HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updateTeacher(@RequestBody Teacher t, @PathVariable String id) {
		System.out.println("inside update Teacher");
		long id1 = Long.parseLong(id);
		Teacher t1 = null;
		try {
			t1 = dao.findTeacherById(id1);
			if (t1 != null) {
				t1 = t;
				dao.saveTeacher(t1);
			}
		} catch (RuntimeException e) {
			logger.error("Something wrong while updating teacher: "+e.getMessage());
			return new ResponseEntity<>("Error while updating.. try again", HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>("data updated successfully..", HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteTeacher(@PathVariable String id) {
		System.out.println("inside deleteTeacher");
		long id1 = Long.parseLong(id);
		Teacher t1 = null;
		try {
			t1 = dao.deleteById(id1);
		} catch (RuntimeException e) {
			logger.error("Something wrong while deleting teacher : "+e.getMessage());
			return new ResponseEntity<>("Error while Deleting", HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(t1, HttpStatus.OK);
	}

	/*
	 * @PutMapping("/{id}/student")
	 * 
	 * @CrossOrigin(origins = "http://localhost:4200") // CORS public
	 * ResponseEntity<?> addStudent(@RequestBody Student student, @PathVariable
	 * String id){ Teacher t = null; long id1 = Long.parseLong(id); try { t =
	 * dao.findTeacherById(id1); t.addStudent(student); }catch(RuntimeException e) {
	 * return new ResponseEntity<Teacher>(t, HttpStatus.NO_CONTENT); } return new
	 * ResponseEntity<Teacher>(t, HttpStatus.OK);
	 * 
	 * }
	 * 
	 * @PutMapping("/{id}/course")
	 * 
	 * @CrossOrigin(origins = "http://localhost:4200") // CORS public
	 * ResponseEntity<?> addCourse(@RequestBody Course course, @PathVariable String
	 * id){ Teacher t = null; long id1 = Long.parseLong(id); try { t =
	 * dao.findTeacherById(id1); t.addCourse(course); }catch(RuntimeException e) {
	 * return new ResponseEntity<Teacher>(t, HttpStatus.NO_CONTENT); } return new
	 * ResponseEntity<Teacher>(t, HttpStatus.OK);
	 * 
	 * }
	 */

	@PutMapping("/{id}/Session")
	@CrossOrigin(origins = "http://localhost:4200") // CORS
	public ResponseEntity<?> addSession(@RequestBody Session session, @PathVariable String id) {
		Teacher t = null;
		long id1 = Long.parseLong(id);
		try {
			t = dao.findTeacherById(id1);
			t.addSession(session);
		} catch (RuntimeException e) {
			logger.error("Something wrong while adding session "+e.getMessage());
			return new ResponseEntity<Teacher>(t, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Teacher>(t, HttpStatus.OK);

	}

	
// find all teacher where user id is id
	@GetMapping("/list/user/{id}")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<?> getStudentsWhereUser(@PathVariable String id) {
		List<Teacher> teachers = new ArrayList<>();
		long id1 = Long.parseLong(id);
		try {
			teachers = dao.findAllTeacherByUserId(id1);
		} catch (Exception e) {
			logger.error("Something wrong while getting teacher by user id"+e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<List<Teacher>>(teachers, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Teacher>>(teachers, HttpStatus.OK);
	}

	@GetMapping("/list/detail")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<?> getTeacherWhereSubjectAndADD(@RequestParam String state, @RequestParam String city) {
		List<Teacher> teachers = new ArrayList<>();
		try {
			teachers = dao.getTeachersBySubjectAndAdd(state, city);	
		} catch (Exception e) {
			logger.error("Something wrong while getting teacher using add and sube: "+e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<List<Teacher>>(teachers, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Teacher>>(teachers, HttpStatus.OK);
	}
 
	
	
//  add course to teacher in course_teacher table
	
	@PostMapping("/addCourse/{id}")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<?> addCourseToTeacher(@RequestParam String course, @PathVariable String id){
		logger.info("Inside add course in Teacher");
		long id1 = Long.parseLong(id);
	    System.out.println(course);
		Teacher t = null;
		try {
			logger.info("mapper initiallized");
			ObjectMapper mapper = new ObjectMapper();
			logger.info("value reading.....");
			Course c1 = mapper.readValue(course, Course.class);
			logger.info("value read");
			t = dao.addCourseToTeacher(c1, id1);
			logger.info("course added successfully");
		}catch(Exception e) {
			logger.error("something went wrong chekk exception details");
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Teacher>(HttpStatus.OK);
	}

	
// searcher new registered teacher
	@GetMapping("/falseStatus")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<?> teacherListWithFalseStatus(){
		ArrayList<Teacher> al = new ArrayList<>();
		
		
		try {
			logger.info("list fatching.....");
			al = dao.getTeacherListWithFalseStatus();
			logger.info("done fetching");
		}catch(Exception e) {
			logger.error("something went wrong chekk exception details");
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<ArrayList<Teacher>>(al, HttpStatus.OK);
	}
	
// confirm new registered teacher passing id of that teacher
	@GetMapping("/confirmTeacher/{id}")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<?> confirmTeacherStatus(@PathVariable String id){
		long id1 = Long.parseLong(id);
		ArrayList<Teacher> al = new ArrayList<>();
		try {
			  logger.info("confirming status");
			  dao.confirmStatus(id1);
			 al = dao.getTeacherListWithFalseStatus();
			 System.out.println("done");
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<ArrayList<Teacher>>(al,HttpStatus.OK);
	}


//to reject new registered teacher
//===>>> also remove user associated with this teacher and send mail to registered email..... 
	@GetMapping("/remove/{id}")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<?> removeteacher(@PathVariable String id){
		long id1 = Long.parseLong(id);
		ArrayList<Teacher> al = new ArrayList<>();
		try {
			al = dao.removeTeacher(id1);
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<ArrayList<Teacher>>(al,HttpStatus.OK);
	}
}
