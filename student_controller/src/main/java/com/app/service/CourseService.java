package com.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.pojos.Course;
import com.app.pojos.User;
import com.app.reposetory.CourseRepo;

@Service
public class CourseService {
	@Autowired
	private CourseRepo dao;
	
	public CourseService() {
		System.out.println("Inside Course Service");
	}
	
	public List<Course> findAll(){
		List<Course> ls = new ArrayList<>();
		ls = dao.findAll();
		return ls;
	}
	
	public Course findCourseByCourseName(String courseName) {
		return dao.findCourseByCourseName(courseName);
	}

	public User findCourseById(long id) {
		return dao.findCourseById(id);
	}

}
