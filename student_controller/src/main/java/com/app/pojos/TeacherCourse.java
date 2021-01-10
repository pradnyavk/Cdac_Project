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

@Entity
@Table(name="teacher_course")
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
}