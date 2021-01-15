package com.app.reposetory;


import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.app.pojos.Course;
import com.app.pojos.Teacher;

@Repository
public interface TeacherRepo extends JpaRepository<Teacher, Long> {
	
	
	public List<Teacher> findAll();
	public Teacher findTeacherById(long id);
	public Teacher deleteById(long id);
	@Query("select t from Teacher t where t.userName.id = :id")
	public List<Teacher> findAllTeacherByUserId(@Param(value="id") long id1);
	
	@Query("select t from Teacher t where t.address.state=?1 and t.address.city=?2")
	public ArrayList<Teacher> getTeachersBySubjectAndAdd(String state, String city);
	@Query("select t from Teacher t where t.status = false")
	public ArrayList<Teacher> findTeacherWithFalseStatus();
	public int removeTeacherById(long id1);
	
}
