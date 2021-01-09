package com.app.pojos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Session")
public class Session extends Base{
	
	@Column(name="start_time")
	private LocalDate startTime;
	@Column(name="end_time")
	private LocalDate endTime;
	@Column(name="topic")
	private String topic;
	@Column(name="feedback")
	private int feedback;
	@Column(name="session_cost")
	private long sessionCost;
	
	
	@ManyToOne
	@JoinColumn(name="course_id")
	private Course course;
	
	@ManyToOne
	@JoinColumn(name="teacher_id")
	private Teacher teacher;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(
			joinColumns = @JoinColumn(name="session_id"),
			inverseJoinColumns = @JoinColumn(name="student_id")
			)
	private List<Student> students = new ArrayList<>();

	
	
	public Session() {
		super();
	}

	public Session(LocalDate startTime, LocalDate endTime, String topic, int feedback, long sessionCost) {
		super();
		this.startTime = startTime;
		this.endTime = endTime;
		this.topic = topic;
		this.feedback = feedback;
		this.sessionCost = sessionCost;
	}
	public Session(long id, long version, LocalDate startTime, LocalDate endTime, String topic, int feedback, long sessionCost) {
		super();
		this.startTime = startTime;
		this.endTime = endTime;
		this.topic = topic;
		this.feedback = feedback;
		this.sessionCost = sessionCost;
	}



	public LocalDate getStartTime() {
		return startTime;
	}



	public void setStartTime(LocalDate startTime) {
		this.startTime = startTime;
	}



	public LocalDate getEndTime() {
		return endTime;
	}



	public void setEndTime(LocalDate endTime) {
		this.endTime = endTime;
	}



	public String getTopic() {
		return topic;
	}



	public void setTopic(String topic) {
		this.topic = topic;
	}



	public int getFeedback() {
		return feedback;
	}



	public void setFeedback(int feedback) {
		this.feedback = feedback;
	}



	public long getSessionCost() {
		return sessionCost;
	}



	public void setSessionCost(long sessionCost) {
		this.sessionCost = sessionCost;
	}



	public Course getCourse() {
		return course;
	}



	public void setCourse(Course course) {
		this.course = course;
	}



	public Teacher getTeacher() {
		return teacher;
	}



	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}



	public List<Student> getStudents() {
		return students;
	}



	public void setStudents(List<Student> students) {
		this.students = students;
	}
	
	
	public void addStudent(Student s) {
		this.students.add(s);
	    s.getSessions().add(this);
	}

	@Override
	public String toString() {
		return "Session [startTime=" + startTime + ", endTime=" + endTime + ", topic=" + topic + ", feedback="
				+ feedback + ", sessionCost=" + sessionCost + "]";
	}
	
}
