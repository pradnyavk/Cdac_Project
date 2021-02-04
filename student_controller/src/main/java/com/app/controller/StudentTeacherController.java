package com.app.controller;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
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
@CrossOrigin
public class StudentTeacherController {
	
	Logger logger =  LoggerFactory.getLogger(StudentTeacherController.class);
	
	@Autowired
	private StudentTeacherService dao;
	@Autowired
	private StudentService dao1;
	
	public StudentTeacherController() {
		logger.info("StudentTeacher constructor initiated @@@");
	}
	
// used by student to check the list of teacher that is who is subscribed
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
	
	@GetMapping("/list")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<?> getAllStudentTeacherWhereStatusisFalse(){
		ArrayList<StudentTeacher> st = new ArrayList<>();
		
		try {
			st = dao.findStudentTeacherWhereStatusIsNull();
		}catch(RuntimeException e) {
			return new ResponseEntity<ArrayList<StudentTeacher>>(st, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<ArrayList<StudentTeacher>>(st, HttpStatus.OK);
	}
	
// used by admin to allocate teacher to student
	@GetMapping("/updatestatus/{id}")
	@CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<?> updateStudentTeacherStatus(@PathVariable String id){
		 long id1 = Long.parseLong(id);
		 ArrayList<StudentTeacher> st = new ArrayList<>();
		 try {	
			    StudentTeacher st2 =dao.findStudentTeacherById(id1);
			    st2.setStatus(true);
			    dao.save(st2);
				st = dao.findStudentTeacherWhereStatusIsNull();
			}catch(RuntimeException e) {
				return new ResponseEntity<ArrayList<StudentTeacher>>(st, HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<ArrayList<StudentTeacher>>(st, HttpStatus.OK);
		
	}
	
// all avliable job for teacher ...//used by teacher
	@GetMapping("/studentList/{teacherId}")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<?> getStudentListByTeacherId(@PathVariable String teacherId){
		long id = Long.parseLong(teacherId);
		 ArrayList<Student> st = new ArrayList<>();
		 try {
			  st = dao.findStudentListByTeacherId(id);
		 }catch (RuntimeException e) {
			 return new ResponseEntity<>(HttpStatus.NO_CONTENT);	
		}
		 return new ResponseEntity<ArrayList<Student>>(st, HttpStatus.OK);
	}
	
// used by teacher to check new avliable job are there .. req from student
	@GetMapping("/studentList/newJob/{teacherId}")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<?> getStudentListByTeacherIdWithStatusIsFalse(@PathVariable String teacherId){
		long id = Long.parseLong(teacherId);
		 ArrayList<Student> st = new ArrayList<>();
		 try {
			  st = dao.findStudentListByTeacherIdWithStatusIsFalse(id);
		 }catch (RuntimeException e) {
			 return new ResponseEntity<>(HttpStatus.NO_CONTENT);	
		}
		 return new ResponseEntity<ArrayList<Student>>(st, HttpStatus.OK);
	}
	
// used by student to remove teacher.......
//==== implement mailing whenever it remove happen admin gets email of removel
	@GetMapping("/removeStudentTeacher/{teacherId}/{studentId}")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<?> removeStudentfromteacher(@PathVariable String studentId, @PathVariable String teacherId){
		long stId  = Long.parseLong(studentId);
		long tId = Long.parseLong(teacherId);
		try {
			dao.removeStudentFromTeacher(stId, tId);
			dao.findTeachersOfStudentId(stId);
		}catch (RuntimeException e) {
			 return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		 return new ResponseEntity<ArrayList<Teacher>>(dao.findTeachersOfStudentId(stId),HttpStatus.OK);
	}
	
}
