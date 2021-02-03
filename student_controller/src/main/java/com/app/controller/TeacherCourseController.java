package com.app.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.pojos.Teacher;
import com.app.pojos.TeacherCourse;
import com.app.service.TeacherCourseService;

import net.bytebuddy.asm.Advice.Origin;

@RestController
@RequestMapping("/teacherCourse")
public class TeacherCourseController {
	
	@Autowired
	private TeacherCourseService dao;
	
	public TeacherCourseController() {
		System.out.println("this is teacher course contoller");
	}
	
	@GetMapping("/teacher")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<?> getCourseListwherecourse(@RequestParam String courseName, @RequestParam String state, @RequestParam String city) {
		System.out.println("ion");
		ArrayList<Teacher> tc = new ArrayList<>();
		ArrayList<Teacher> finalList = new ArrayList<>();
		
		try {
			tc = dao.findListOfTeacherCourseWhereCourse(courseName);
			System.out.println(tc);
			for(Teacher t : tc) {
				if(t.getAddress().getCity().equals(city) && t.getAddress().getState().equals(state) && t.isStatus()) {
					finalList.add(t);
				}
			}
			System.out.println(finalList);
		} catch (RuntimeException e) {
			e.printStackTrace();
			return new ResponseEntity<ArrayList<Teacher>>(finalList,HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<ArrayList<Teacher>>(finalList, HttpStatus.OK);
	}
	
	@GetMapping("/{teacherId}/{courseName}")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<?> getTeacherCourse(@PathVariable String teacherId, @PathVariable String courseName){
		
		long tid = Long.parseLong(teacherId);
		TeacherCourse tc = null;
		try {
			tc = dao.findTeacherCourse(tid, courseName);
			System.out.println("teacher_Course"+ tc);
		}catch(RuntimeException e) {
			return new ResponseEntity<TeacherCourse>(tc,HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<TeacherCourse>(tc, HttpStatus.OK);	
	}
	
	@GetMapping("/confirmStatus/{id}/{courseName}")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<?> confirmTeacherCourseStatus(@PathVariable String id, @PathVariable String courseName){
		
		long tid = Long.parseLong(id);
		TeacherCourse tc = null;
		try {
			tc = dao.findTeacherCourse(tid, courseName);
			tc.setStatus(true);
			dao.save(tc);
		}catch (Exception e) {
			return new ResponseEntity<TeacherCourse>(tc,HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<TeacherCourse>(tc, HttpStatus.OK);
	}
	
	@GetMapping("/newList")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<?> getListOfTeacherCourseWhereStatusIsFalse(){
		TeacherCourse tc = null;
		try {
		   tc = dao.findTeacherCourseWhereStatusIsFalse();
		   System.out.println("teacherCourse"+tc);
		}catch (Exception e) {
			return new ResponseEntity<TeacherCourse>(tc,HttpStatus.NO_CONTENT);		
			}
		return new ResponseEntity<TeacherCourse>(tc, HttpStatus.OK);

	}
	
	@GetMapping("/confirmStatus/{teacherCourseId}")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<?> confirmTeacherCourseStatus(@PathVariable String teacherCourseId ){
		
		long id = Long.parseLong(teacherCourseId);
		TeacherCourse tc = null;
		try {
			tc = dao.findTeacherCourseById(id);
			tc.setStatus(true);
			dao.save(tc);
		}catch (Exception e) {
			return new ResponseEntity<TeacherCourse>(tc,HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<TeacherCourse>(tc, HttpStatus.OK);
	}
	
	@GetMapping("/rejectStatus/{teacherCourseId}")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<?> rejectTeacherCourse(@PathVariable String teacherCourseId ){
		
		long id = Long.parseLong(teacherCourseId);
		TeacherCourse tc = null;
		try {
			dao.rejectTeacherCourse(id);
		}catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
