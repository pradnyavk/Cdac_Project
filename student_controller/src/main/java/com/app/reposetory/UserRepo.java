package com.app.reposetory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.app.pojos.User;

public interface UserRepo extends JpaRepository<User, Long>{
	
	public List<User> findAll();
	public User findUserById(long id);
	public User deleteById(long id);
	public User findUserByEmail(String email);
}
