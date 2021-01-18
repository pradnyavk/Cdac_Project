package com.app.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.pojos.Course;
import com.app.pojos.Teacher;
import com.app.pojos.TeacherCourse;
import com.app.reposetory.TeacherRepo;

@Service
@Transactional
public class TeacherService {
	
	@Autowired
	private TeacherRepo dao;
	
	public TeacherService() {
		System.out.println("inside teacher repository...");
	}
	
	public Teacher saveTeacher(Teacher t) {
		System.out.println("inside service saveTeacher");
		Teacher t1 = null;
		t1 = dao.save(t);
		return t1;
	}
	public List<Teacher> findAllTeacher(){
		System.out.println("inside service findAllTeacher");
		List<Teacher> ls = new ArrayList<>();
		ls = dao.findAll();
		return ls;
	}
	public Teacher findTeacherById(long id) {
		System.out.println("inside service findTeacherbyid");
		Teacher t1 = null;
		t1 = dao.findTeacherById(id);
		return t1;
	}
	public Teacher deleteById(long id) {
		System.out.println("inside deleteByid");
		Teacher t1 = null;
		t1 = dao.deleteById(id);
		return t1;
	}

	public List<Teacher> findAllTeacherByUserId(long id1) {
		return dao.findAllTeacherByUserId(id1);
	}
	public ArrayList<Teacher> getTeachersBySubjectAndAdd(String state, String city){
		return dao.getTeachersBySubjectAndAdd( state, city);
	}

	public Teacher addCourseToTeacher(Course c1, long id1) {	
		Teacher t = dao.findTeacherById(id1);
		TeacherCourse tc = new TeacherCourse();
		tc.setStatus(false);
		tc.setCourse(c1);
		t.addTeacherCourse(tc);
		return t;
	}

	public ArrayList<Teacher> getTeacherListWithFalseStatus() {
		return dao.findTeacherWithFalseStatus();
	}

	public Teacher confirmStatus(long id1) {
		Teacher t = dao.findTeacherById(id1);
		t.setStatus(true);
		return dao.save(t);
	}

	public ArrayList<Teacher> removeTeacher(long id1) {
		 dao.removeTeacherById(id1);
		return this.getTeacherListWithFalseStatus();
	}
}
