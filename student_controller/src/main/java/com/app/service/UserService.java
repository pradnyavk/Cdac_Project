package com.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.app.pojos.User;
import com.app.reposetory.UserRepo;

@Service
public class UserService {
	
	@Autowired
	private UserRepo dao;
	
	public UserService() {
		System.out.println("inside UserService constr....");
	}
	
	public User saveUser(User user) {
		System.out.println("inside service save User");
		User user1 = null;
		user1 = dao.save(user);
		return user1;
	}
	public List<User> findAllUser(){
		System.out.println("inside service findAllUser");
		List<User> ls = new ArrayList<>();
		ls = dao.findAll();
		return ls;
	}
	public User findUserById(long id) {
		System.out.println("inside service findUserbyid");
		User user = null;
		user = dao.findUserById(id);
		return user;
	}
	public User deleteById(long id) {
		System.out.println("inside deleteByid");
		User user = null;
		user = dao.deleteById(id);
		return user;
	}
	public User findUserByEmail(String email) {
		System.out.println("inside service findUserByEmail");
		return dao.findUserByEmail(email);
	}
	public User save(User u) {
		return dao.save(u);
	}
	public User findCourseById(long id1) {
		return dao.findUserById(id1);
	}
}
