package com.nagarro.registration.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.nagarro.registration.models.User;


public interface UserService extends UserDetailsService{
	
	public List<User> getAllUsers();
	
	public User getUser(long UserId);
	
	public User addUser(User User);
	
	public User updateUser(User User);
	
	public User getUserByEmail(String mail);
	
	public User checkUserForLogin(String mail, String password);
}
