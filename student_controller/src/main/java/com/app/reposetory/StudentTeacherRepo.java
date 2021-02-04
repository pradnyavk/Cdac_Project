package com.app.reposetory;

import java.util.ArrayList;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.app.pojos.Student;
import com.app.pojos.StudentTeacher;
import com.app.pojos.Teacher;

public interface StudentTeacherRepo extends JpaRepository<StudentTeacher, Long>{
	
	@Query("select tc.teacher from StudentTeacher st join st.student s join st.teacherCourse tc join tc.teacher where s.id = ?1 and st.status = true")
	public ArrayList<Teacher> findTeachersOfStudentId(long id);
    @Query("select st from StudentTeacher st where st.status = false")
	public ArrayList<StudentTeacher> findStudnetTeacherWhereStatusIsNuLL();
    @Modifying
    @Query("update StudentTeacher st set st.status = true where st.id = ?1")
	public void changeStatusOfId(long id1);
	public StudentTeacher findStudentTeacherById(long id1);
	
	@Query("select s from StudentTeacher st join st.student s join st.teacherCourse tc join tc.teacher t where t.id = ?1 and st.status = true")
	public ArrayList<Student> findStudentListByTeacherId(long id);
	
	@Query("select s from StudentTeacher st join st.student s join st.teacherCourse tc join tc.teacher t where t.id = ?1 and st.status = false")
	public ArrayList<Student> findStudentListByTeacherIdWithStatusIsFalse(long id);
	
	
	@Query("select st from StudentTeacher st join st.student s join st.teacherCourse tc join tc.teacher t where s.id = ?1 and t.id = ?2")
	public StudentTeacher getStudentTeacherByStudentIdAndTeacherId(long stId, long tId);
	

//	@Transactional
//	@Modifying
//	@Query("delete from StudentTeacher st where st.id = (select st1.id from StudentTeacher st1 join st1.student s join st1.teacherCourse tc join tc.teacher t where s.id = ?1 and t.id =?2)")
//	public int removeTeacher(long sid,long tid);
}
