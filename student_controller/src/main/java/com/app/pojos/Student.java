package com.app.pojos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table(name="students")
@JsonIgnoreProperties(value = {"teachers", "courses", "sessions"})
public class Student extends Base {
	
   @Column(name="student_name")
   private String studentName;
   @Column(name="date_of_birth")
   private LocalDate dob; 
   @Column(name="email", unique = true)
   private String email;
   @Column(name="gender")
   @Enumerated(EnumType.STRING)
   private Gender gender;
   @Embedded
   private Address address;
   
   @ElementCollection
   @CollectionTable(name = "phone_no",
   			joinColumns = @JoinColumn(name="student_id")
		   )
   @Column(name="phone")
   private List<String> phone;
   
   @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
   private List<StudentTeacher> studentTeacher = new ArrayList<>();
   
   @ManyToMany(mappedBy = "students", cascade = CascadeType.ALL)
   private List<Session> sessions = new ArrayList<>();
   
   @ManyToOne
   @JoinColumn(name="user_id")
   private User userName;


public Student(String studentName, LocalDate dob, String email) {
	super();
	this.studentName = studentName;
	this.dob = dob;
    this.email = email;
}


public Student() {
	super();
}
public Student(long id, long version,String studentName, LocalDate dob,String email,Gender gender) {
	super(id, version);
	this.studentName = studentName;
	this.dob = dob;
	 this.email = email;
	 this.gender = gender;
}




public String getStudentName() {
	return studentName;
}


public void setStudentName(String studentName) {
	this.studentName = studentName;
}


public LocalDate getDob() {
	return dob;
}


public void setDob(LocalDate dob) {
	this.dob = dob;
}


public Address getLocation() {
	return address;
}


public void setLocation(Address location) {
	this.address = location;
}



public User getUserName() {
	return userName;
}


public void setUserName(User userName) {
	this.userName = userName;
}



public Address getAddress() {
	return address;
}


public void setAddress(Address address) {
	this.address = address;
}


public List<Session> getSessions() {
	return sessions;
}


public void setSessions(List<Session> sessions) {
	this.sessions = sessions;
}

public String getEmail() {
	return email;
}


public void setEmail(String email) {
	this.email = email;
}


public List<String> getPhone() {
	return phone;
}


public void setPhone(List<String> phone) {
	this.phone = phone;
}



public Gender getGender() {
	return gender;
}


public void setGender(Gender gender) {
	this.gender = gender;
}

public void addSession(Session s) {
	this.sessions.add(s);
	s.getStudents().add(this);
}


public void addStudentTeacher(StudentTeacher st) {
	studentTeacher.add(st);
	st.setStudent(this);
}

public void removeStudentTeacher(StudentTeacher st) {
	this.studentTeacher.remove(st);
	st.setStudent(null);
}



@Override
public boolean equals(Object o) {
    long i = ((Student)o).getId() - this.getId();
    if(i == 0)
    	return true;
    return false;
}


@Override
public String toString() {
	return "Student [studentName=" + studentName + ", dob=" + dob + ", location=" + address +"]";
}

}
