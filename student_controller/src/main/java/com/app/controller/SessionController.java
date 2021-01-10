package com.app.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.pojos.Session;
import com.app.service.SessionService;

@RestController
@RequestMapping("/session")
public class SessionController {
	
	@Autowired
	private SessionService dao;
	
	public SessionController() {
		System.out.println("inside session Controller...");
	}
	
	@GetMapping("/list")
	public ResponseEntity<?> findAllSession(){
		System.out.println("inside findAllTeacher");
		List<Session> ls = new ArrayList<>();
		try {
			ls = dao.findAllSession();
		} catch (RuntimeException e) {
			return new ResponseEntity<String>("no result found", HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(ls, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> findTeacherById(@PathVariable String id){
		System.out.println("inside findSession by Id");
		long id1 = Long.parseLong(id);
		Session session = null;
		try {
			session = dao.findSessionById(id1);
		} catch (RuntimeException e) {
			return new ResponseEntity<String>("no result found", HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(session, HttpStatus.OK);
	}
	@PostMapping(
			consumes = {org.springframework.http.MediaType.APPLICATION_JSON_VALUE}
			)
	public ResponseEntity<?> saveSession(@RequestBody Session session){
		System.out.println("inside saveSession");
		Session session1 = null;
		try {
			session1 = dao.saveSession(session);
		} catch (RuntimeException e) {
			return new ResponseEntity<String>("Error while saving", HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(session1, HttpStatus.OK);
	}
	@PutMapping("/{id}")
	public ResponseEntity<?> updateSession(@RequestBody Session session, @PathVariable String id){
		System.out.println("inside update Session");
		long id1 = Long.parseLong(id);
		Session session1 = null;
		try {
			session1 = dao.findSessionById(id1);
			if(session1 != null) {
				session1 = session;
				dao.saveSession(session1);
			}
		}catch (RuntimeException e) { 
			return new ResponseEntity<>("Error while updating.. try again", HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>("data updated successfully..", HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteSession(@PathVariable String id){
		System.out.println("inside deleteSession");
		long id1 = Long.parseLong(id);
		Session session1 = null;
		try {
			session1 = dao.deleteById(id1);
		} catch (RuntimeException e) {
			return new ResponseEntity<>("Error while Deleting", HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(session1, HttpStatus.OK);
	}
}
