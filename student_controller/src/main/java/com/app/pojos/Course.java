package com.app.pojos;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="courses")
public class Course extends Base{
	
	@Column(name="course_name")
	private String courseName;
	
	@ElementCollection
	@CollectionTable(name="subjects")
	private List<String> subjects;
	
	@OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<TeacherCourse> studentTeacher = new ArrayList<TeacherCourse>();
	
	public Course() {
		System.out.println("inside course contr");
	}

	public Course(String courseName) {
		super();
		this.courseName = courseName;
	}

	public Course(long id, long version,String courseName) {
		super(id, version);
		this.courseName = courseName;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public List<String> getSubjects() {
		return subjects;
	}

	public void setSubjects(List<String> subjects) {
		this.subjects = subjects;
	}

	@Override
	public boolean equals(Object o) {
	  boolean result;
	  result = ((Course)o).getCourseName().equals(this.getCourseName());
	  return result;
	}

	@Override
	public String toString() {
		return "Course [courseName=" + courseName + "]";
	}
}
