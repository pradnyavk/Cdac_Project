package com.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.pojos.Course;
import com.app.pojos.Teacher;
import com.app.reposetory.TeacherRepo;

@Service
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
}
