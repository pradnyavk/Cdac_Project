package com.app.reposetory;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.pojos.Teacher;
import com.app.pojos.TeacherCourse;

public interface TeacherCourseRpo extends JpaRepository<TeacherCourse, Long> {
	
	@Query("select tc.teacher from TeacherCourse tc join tc.course c where c.courseName = ?1")
	ArrayList<Teacher> findListOfTeacherCourseWhereCourse(String courseName); 
	@Query("select tc from TeacherCourse tc join tc.course c join tc.teacher t where t.id = ?1 and c.courseName = ?2 ")
	TeacherCourse findTeacherCourse(long id, String courseName);
	@Query("select tc from TeacherCourse tc where tc.status = false")
	TeacherCourse findTeacherCourseWhereStatusIsFalse();
	TeacherCourse findTeacherCourseById(long id);
}
