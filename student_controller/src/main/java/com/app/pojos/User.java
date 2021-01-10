package com.app.pojos;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table(name="users")
@JsonIgnoreProperties(value = {"teachers", "students"})
public class User extends Base {
	
	@Column(name="uname")
	private String name;
	@Column(name="uemail")
	private String email;
	@Column(name="upassword")
	private String password;
	@Column(name="role")
	@Enumerated(EnumType.STRING)
	private Role role;
	@Column(name="gender")
	@Enumerated(EnumType.STRING)
	private Gender gender;
	
	
	
	@OneToMany(mappedBy = "userName", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Teacher> teachers = new ArrayList<Teacher>();
	@OneToMany(mappedBy = "userName", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Student> students = new ArrayList<Student>();
	
	public User() {
		System.out.println("this is user Constr");
	}

	public User(String name, String email, String password) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
	}

	public User(long id, long version, String name, String email, String password) {
		super(id, version);
		this.name = name;
		this.email = email;
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public List<Teacher> getTeachers() {
		return teachers;
	}

	public void setTeachers(List<Teacher> teachers) {
		this.teachers = teachers;
	}

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}
	
	public void addStudent(Student s) {
		this.students.add(s);
		s.setUserName(this);
	}
	
	public void addTeacher(Teacher t) {
		this.teachers.add(t);
		t.setUserName(this);
	}
	@Override
	public String toString() {
		return "User [name=" + name + ", email=" + email + ", password=" + password + "]";
	}
}
