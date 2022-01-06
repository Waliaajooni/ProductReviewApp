package com.nagarro.registration.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.nagarro.registration.models.User;


public interface UserDao extends JpaRepository<User, Long>{
	
	public Optional<User> findByUsrmail(String username);
}
