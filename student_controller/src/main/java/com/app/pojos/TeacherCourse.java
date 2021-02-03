package com.app.pojos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="teacher_course")
@JsonIgnoreProperties(value = {"studentTeacher"})
public class TeacherCourse extends Base {
	
	@ManyToOne
	@JoinColumn(name="teacher_id")
	private Teacher teacher;
	
	@ManyToOne
	@JoinColumn(name="course_id")
	private Course course;
	
	@OneToMany(mappedBy="teacherCourse", cascade= CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<StudentTeacher> studentTeacher = new ArrayList<>();
	
	@Column(name="status")
	private boolean status;
	
	@Column(name="joinig_date")
	private LocalDate joiningDate;

	public TeacherCourse() {
		super();
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public List<StudentTeacher> getStudentTeacher() {
		return studentTeacher;
	}

	public void setStudentTeacher(List<StudentTeacher> studentTeacher) {
		this.studentTeacher = studentTeacher;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public LocalDate getJoiningDate() {
		return joiningDate;
	}

	public void setJoiningDate(LocalDate joiningDate) {
		this.joiningDate = joiningDate;
	}

	@Override
	public String toString() {
		return "TeacherCourse [teacher=" + teacher + ", course=" + course + ", status=" + status + ", joiningDate=" + joiningDate + "]";
	}
	
	
}
