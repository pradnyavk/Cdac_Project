package com.app.pojos;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="student_teacher",
uniqueConstraints = { @UniqueConstraint( columnNames = { "student_id", "teacherCourse_id" } ) }
		)
public class StudentTeacher extends Base {
	
	@ManyToOne
	@JoinColumn(name="student_id")
	private Student student;
    
	@ManyToOne
	@JoinColumn(name="teacherCourse_id")
	private TeacherCourse teacherCourse;
	
	@Column(name="status")
	private boolean status;
	@Column(name="hire_date")
	private LocalDate date;	

	
	
	//constructor using no any feild
	public StudentTeacher() {
		super();
	}


	//constructor using feilds
	public StudentTeacher(boolean status, LocalDate date) {
		super();
		this.status = status;
		this.date = date;
	}


	public Student getStudent() {
		return student;
	}


	public void setStudent(Student student) {
		this.student = student;
	}

	public boolean isStatus() {
		return status;
	}


	public void setStatus(boolean status) {
		this.status = status;
	}


	public LocalDate getDate() {
		return date;
	}


	public void setDate(LocalDate date) {
		this.date = date;
	}


	public TeacherCourse getTeacherCourse() {
		return teacherCourse;
	}


	public void setTeacherCourse(TeacherCourse teacherCourse) {
		this.teacherCourse = teacherCourse;
	}
	
	
	
	
}
