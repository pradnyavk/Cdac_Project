package com.app.controller;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.pojos.Student;
import com.app.pojos.Teacher;
import com.app.pojos.User;
import com.app.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService dao;
	
	public UserController() {
		System.out.println("inside UserService constructor....");
	}
	
	@GetMapping("/list")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<?> getUserList(){
		System.out.println("inside findAllUser");
		List<User> ls = new ArrayList<>();
		try {
			ls = dao.findAllUser();
		} catch (RuntimeException e) {
			return new ResponseEntity<String>("no result found", HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(ls, HttpStatus.OK);
	}
	@GetMapping("/{id}")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<?> getUserById(@PathVariable String id){
		System.out.println("inside find User by Id");
		long id1 = Long.parseLong(id);
		User user = null;
		try {
			user = dao.findUserById(id1);
		} catch (RuntimeException e) {
			return new ResponseEntity<String>("no result found", HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
	@PostMapping(
			consumes = {org.springframework.http.MediaType.APPLICATION_JSON_VALUE}
			)
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<?> getUserById(@RequestBody User user){
		System.out.println("inside save User");
		User user1 = null;
		try {
			user1 = dao.saveUser(user);
		} catch (RuntimeException e) {
			return new ResponseEntity<String>("Error while saving", HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(user1, HttpStatus.OK);
	}
	@PutMapping("/{id}")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<?> getUserById(@RequestBody User user, @PathVariable String id){
		System.out.println("inside update User");
		long id1 = Long.parseLong(id);
		User user1 = null;
		try {
			user1 = dao.findUserById(id1);
			if(user1 != null) {
			     user1 = user;
				dao.saveUser(user1);
			}
		}catch (RuntimeException e) {
			return new ResponseEntity<>("Error while updating.. try again", HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>("data updated successfully..", HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<?> deleteUserById(@PathVariable String id){
		System.out.println("inside deleteTeacher");
		long id1 = Long.parseLong(id);
		User user = null;
		try {
			user = dao.deleteById(id1);
		} catch (RuntimeException e) {
			return new ResponseEntity<>("Error while Deleting", HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(user, HttpStatus.OK);	
	}
	
//===============================================================================================
	@PostMapping("/veryfication")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<?> veryfieUser(@RequestBody User user){
		User user1 = null;
		user1 = dao.findUserByEmail(user.getEmail());
		System.out.println(user1);
		System.out.println(user);
		if(user1.getPassword().equals(user.getPassword())) {
			System.out.println("verification done.."); 
			return new ResponseEntity<User>(user1, HttpStatus.OK);
		}
		return new ResponseEntity<String>("invalid Email or password", HttpStatus.NO_CONTENT);
	}
	
	@PutMapping("/{id}/student")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<?> addStudent(@RequestBody Student student, @PathVariable String id){
		User user = null;
		List<Student> ls = new ArrayList<Student>();
		long id1 = Long.parseLong(id);
		System.out.println(id1);
		
		  try { 
			  user = dao.findUserById(id1);
			  user.addStudent(student); 
			  user = dao.saveUser(user);
			  ls = user.getStudents();
			  for(Student s : ls) {
				  s.setUserName(null);
			  }
		 } catch(RuntimeException e) {
		      return new  ResponseEntity<List<Student>>(ls, HttpStatus.NO_CONTENT); 
		 }
		return new ResponseEntity<List<Student>>(ls, HttpStatus.OK);
	}
	
	@PutMapping("/{id}/teacher")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<?> addteacher(@RequestBody Teacher teacher, @PathVariable String id){
		User user = null;
		List<Teacher> ls = new ArrayList<Teacher>();
		long id1 = Long.parseLong(id);
		System.out.println(id1);
		
		  try { 
			  user = dao.findUserById(id1);
			  user.addTeacher(teacher); 
			  user = dao.saveUser(user);
			  ls = user.getTeachers();
			  for(Teacher t : ls) {
				  t.setUserName(null);
			  }
		 } catch(RuntimeException e) {
		      return new  ResponseEntity<List<Teacher>>(ls, HttpStatus.NO_CONTENT); 
		 }
		return new ResponseEntity<List<Teacher>>(ls, HttpStatus.OK);
	}
}
