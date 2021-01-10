package com.app.reposetory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.pojos.Session;

public interface SessionRepo extends JpaRepository<Session, Long> {
	
	public List<Session> findAll();
	public Session findUserById(long id);
	public Session deleteById(long id);
}
