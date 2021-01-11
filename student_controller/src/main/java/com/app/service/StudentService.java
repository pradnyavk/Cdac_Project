package com.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.pojos.Student;
import com.app.pojos.StudentTeacher;
import com.app.reposetory.StudentRepo;

@Service
public class StudentService {
	
	@Autowired
	private StudentRepo dao;
	
	
	public StudentService() {
		System.out.println("inside service class constructor..");
	}
	
	
	public Student saveStudent(Student s) {
		return dao.save(s);
	}
	public List<Student> findAllStudent(){
		List<Student> ls = new ArrayList<>();
		ls = dao.findAllStudent();
		return ls;
	}
	public Student deleteById(long id) {
	    Student s = null;
		s=dao.deleteById(id);
		return s;
	}
	public Student findStudentById(long id) {
	    Student s = null;
		s=dao.findStudentById(id);
		return s;
	}


	public List<Student> findAllStudentByUserId(long id) {
		return dao.findAllStudentByUserId(id);
	}
	
	 public StudentTeacher findStudentTeacher(long sid, long tid ) {
		 return dao.findStudentTeacher(sid, tid);
	 }
}
