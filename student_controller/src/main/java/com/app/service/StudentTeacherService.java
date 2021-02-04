package com.app.service;

import java.util.ArrayList;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.pojos.Student;
import com.app.pojos.StudentTeacher;
import com.app.pojos.Teacher;
import com.app.reposetory.StudentTeacherRepo;

@Service
@Transactional
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

	public ArrayList<Student> findStudentListByTeacherId(long id) {
		return dao.findStudentListByTeacherId(id);
	}
	
	public ArrayList<Student> findStudentListByTeacherIdWithStatusIsFalse(long id) {
		return dao.findStudentListByTeacherIdWithStatusIsFalse(id);
	}

	public void removeStudentFromTeacher(long stId, long tId) {
		StudentTeacher st = new StudentTeacher();
		st = dao.getStudentTeacherByStudentIdAndTeacherId( stId,  tId);
		dao.delete(st);
	}
	
}
