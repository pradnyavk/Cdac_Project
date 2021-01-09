package com.app.reposetory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.app.pojos.Student;



public interface StudentRepo extends JpaRepository<Student, Long> {
	
	@Query("select s from Student s")
	List<Student> findAllStudent();
	
	Student deleteById(long id);
	Student findStudentById(long id);
    @Query("select s from Student s where s.userName.id = :id")
	List<Student> findAllStudentByUserId(@Param(value = "id")long id);
}
