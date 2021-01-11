package com.app.reposetory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.app.pojos.Student;
import com.app.pojos.StudentTeacher;



public interface StudentRepo extends JpaRepository<Student, Long> {
	
	@Query("select s from Student s")
	List<Student> findAllStudent();
	
	Student deleteById(long id);
	Student findStudentById(long id);
    @Query("select s from Student s where s.userName.id = :id")
	List<Student> findAllStudentByUserId(@Param(value = "id")long id);
    
	@Query("select st from Student s join s.studentTeacher st join st.teacherCourse tc join tc.teacher t where s.id = ?1 and t.id = ?2")
	public StudentTeacher findStudentTeacher(long sid, long tid );
}
