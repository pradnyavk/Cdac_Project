package com.app.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.pojos.Teacher;
import com.app.pojos.TeacherCourse;
import com.app.reposetory.TeacherCourseRpo;

@Service
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
}
