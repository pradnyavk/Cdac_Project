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
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table(name="teachers")
@JsonIgnoreProperties(value = {"sessions", "teacherCourse"})
public class Teacher extends Base{
	  @Column(name="teacher_name")
	   private String teacherName;
	  
	  @Column(name="email")
	  private String email;
	  
	  @Column(name="age")
	  private int age;
	  
	  @Column(name="gender")
	  @Enumerated(EnumType.STRING)
	  private Gender gender;
	  
	  @Column(name="exp_year")
	  private long expYear;
	  
	  @Column(name="rate")
	  private int rate;
	  
	  @Column(name="per_hour_fees")
	  private long perHourFees;
	  
	  @Column(name="joining_date")
	  private LocalDate joiningDate;
	  
	  @Column(name="status")
	  private boolean status;
	  
	  @Lob
	  @Column(name="resume")
	  private byte[] resume;
	  
	  @Column(name="docType")
	  private String docType;
	  
	  @Embedded
	  private Address address;
	  
	  
	  
	  @OneToMany(mappedBy = "teacher", fetch = FetchType.LAZY)
	  private List<Session> sessions= new ArrayList<>();
	  @ElementCollection
	  @CollectionTable(
			  name="teachers_phone_no",
			  joinColumns = @JoinColumn(name="teacher_id")
			  )
	  @Column(name="phones")
	  private List<String> phones;
	   
	   @OneToMany(mappedBy="teacher", cascade = CascadeType.ALL, orphanRemoval = true, fetch=FetchType.LAZY)
	   private List<TeacherCourse> teacherCourse = new ArrayList<TeacherCourse>();
	   
	   @ManyToOne
	   @JoinColumn(name="user_id")
	   private User userName;
	   
	
	public Teacher() {
		super();
	}


	public Teacher(long id, long version,String teacherName, String email, int age, Gender gender, long expYear, int rate, long perHourFees,
			LocalDate joiningDate, boolean status, byte[] resume, Address address) {
		super(id, version);
		this.teacherName = teacherName;
		this.email = email;
		this.age = age;
		this.gender = gender;
		this.expYear = expYear;
		this.rate = rate;
		this.perHourFees = perHourFees;
		this.joiningDate = joiningDate;
		this.status = status;
		this.resume = resume;
		this.address = address;
	}
	   
	public Teacher(String teacherName, String email, int age, Gender gender, long expYear, int rate, long perHourFees,
			LocalDate joiningDate, boolean status, byte[] resume, Address address) {
		super();
		this.teacherName = teacherName;
		this.email = email;
		this.age = age;
		this.gender = gender;
		this.expYear = expYear;
		this.rate = rate;
		this.perHourFees = perHourFees;
		this.joiningDate = joiningDate;
		this.status = status;
		this.resume = resume;
		this.address = address;
	}


	public String getDocType() {
		return docType;
	}


	public void setDocType(String docType) {
		this.docType = docType;
	}


	public List<TeacherCourse> getTeacherCourse() {
		return teacherCourse;
	}


	public void setTeacherCourse(List<TeacherCourse> teacherCourse) {
		this.teacherCourse = teacherCourse;
	}


	public String getTeacherName() {
		return teacherName;
	}


	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public int getAge() {
		return age;
	}


	public void setAge(int age) {
		this.age = age;
	}


	public Gender getGender() {
		return gender;
	}


	public void setGender(Gender gender) {
		this.gender = gender;
	}


	public long getExpYear() {
		return expYear;
	}


	public void setExpYear(long expYear) {
		this.expYear = expYear;
	}


	public int getRate() {
		return rate;
	}


	public void setRate(int rate) {
		this.rate = rate;
	}


	public long getPerHourFees() {
		return perHourFees;
	}


	public void setPerHourFees(long perHourFees) {
		this.perHourFees = perHourFees;
	}


	public LocalDate getJoiningDate() {
		return joiningDate;
	}


	public void setJoiningDate(LocalDate joiningDate) {
		this.joiningDate = joiningDate;
	}


	public boolean isStatus() {
		return status;
	}


	public void setStatus(boolean status) {
		this.status = status;
	}


	public byte[] getResume() {
		return resume;
	}


	public void setResume(byte[] resume) {
		this.resume = resume;
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


	public List<String> getPhones() {
		return phones;
	}


	public void setPhones(List<String> phones) {
		this.phones = phones;
	}


	public User getUserName() {
		return userName;
	}


	public void setUserName(User userName) {
		this.userName = userName;
	}


	public void addSession(Session s) {
		this.sessions.add(s);
		s.setTeacher(this);
	}
	
	public void addTeacherCourse(TeacherCourse tc) {
	  this.teacherCourse.add(tc);
	  tc.setTeacher(this);
	}

	@Override
	public String toString() {
		return "Teacher [teacherName=" + teacherName + ", email=" + email + ", age=" + age + ", gender=" + gender
				+ ", expYear=" + expYear + ", rate=" + rate + ", perHourFees=" + perHourFees + ", joiningDate="
				+ joiningDate + ", status=" + status + ", userName=" + userName + "]";
	}  
}
