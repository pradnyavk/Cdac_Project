package com.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.pojos.Session;
import com.app.reposetory.SessionRepo;

@Service
public class SessionService {
	@Autowired
	private SessionRepo dao;
	
	public SessionService() {
		System.out.println("inside UserService constr....");
	}
	
	public Session saveSession(Session session) {
		System.out.println("inside service save Session");
		Session session1 = null;
		session1 = dao.save(session);
		return session1;
	}
	public List<Session> findAllSession(){
		System.out.println("inside service findAllSession");
		List<Session> ls = new ArrayList<>();
		ls = dao.findAll();
		return ls;
	}
	public Session findSessionById(long id) {
		System.out.println("inside service findSessionbyid");
		Session session = null;
		session = dao.findUserById(id);
		return session;
	}
	public Session deleteById(long id) {
		System.out.println("inside service deleteByid");
		Session session = null;
		session = dao.deleteById(id);
		return session;
	}
}
