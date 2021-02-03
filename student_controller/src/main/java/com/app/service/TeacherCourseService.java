package com.app.service;

import java.util.ArrayList;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.pojos.Teacher;
import com.app.pojos.TeacherCourse;
import com.app.reposetory.TeacherCourseRpo;

@Service
@Transactional
public class TeacherCourseService {
	
	@Autowired
	private TeacherCourseRpo dao;
	
	public TeacherCourseService() {
		System.out.println("this is Teacher course service");
	}
   public  ArrayList<Teacher> findListOfTeacherCourseWhereCourse(String courseName){
    	return dao.findListOfTeacherCourseWhereCourse(courseName);
    }
	
   public TeacherCourse findTeacherCourse(long id, String courseName) {
	   return dao.findTeacherCourse(id, courseName);
   }
public TeacherCourse save(TeacherCourse tc) {
	return dao.save(tc);
}
public TeacherCourse findTeacherCourseWhereStatusIsFalse() {
	return dao.findTeacherCourseWhereStatusIsFalse();
}
public TeacherCourse findTeacherCourseById(long id) {
	return dao.findTeacherCourseById(id);
}

public void rejectTeacherCourse(long id) {
	dao.deleteById(id);
}

}
