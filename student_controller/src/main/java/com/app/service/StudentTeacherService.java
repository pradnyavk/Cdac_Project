package com.app.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.pojos.StudentTeacher;
import com.app.pojos.Teacher;
import com.app.reposetory.StudentTeacherRepo;

@Service
public class StudentTeacherService {
	
	@Autowired
	private StudentTeacherRepo dao;
	
	public StudentTeacherService() {
		System.out.println("student teacher service");
	}
	
	 public ArrayList<Teacher> findTeachersOfStudentId(long id){
		 return dao.findTeachersOfStudentId(id);
	 }
//	 public int removeTeacher(long sid,long tid) {
//		 return dao.removeTeacher(sid, tid);
//	 }

	public ArrayList<StudentTeacher> findStudentTeacherWhereStatusIsNull() {
		// TODO Auto-generated method stub
		return dao.findStudnetTeacherWhereStatusIsNuLL();
	}

	public void changeStatusOfId(long id1) {
		dao.changeStatusOfId(id1);
	}

	public StudentTeacher findStudentTeacherById(long id1) {
		
		return dao.findStudentTeacherById(id1);
	}

	public void save(StudentTeacher st2) {
		dao.save(st2);
		
	}
	
}
