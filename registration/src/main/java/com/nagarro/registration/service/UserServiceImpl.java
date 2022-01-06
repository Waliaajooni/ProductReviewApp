package com.nagarro.registration.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.nagarro.registration.dao.UserDao;
import com.nagarro.registration.models.User;
import com.nagarro.registration.models.UserDetailsDto;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	UserDao userDao;

	@Override
	public List<User> getAllUsers() {
		return userDao.findAll();
	}

	@Override
	public User getUser(long userId) {
		return userDao.findById(userId).get();
	}

	@Override
	public User addUser(User user) {
		userDao.save(user);
		return user;
	}

	@Override
	public User updateUser(User user) {
		userDao.save(user);
		return user;
	}

	@Override
	public User getUserByEmail(String mail) {
		List<User> users = getAllUsers();
		
		for(User user: users) {
			if (user.getUsrmail().equalsIgnoreCase(mail)) {
				return user;
			}
		}
		
		return null;
	}
	
	@Override
	public User checkUserForLogin(String mail, String password) {
		List<User> users = getAllUsers();
		
		for (User user: users) {
			if (user.getUsrmail().equalsIgnoreCase(mail) && user.getUsrpassword().equals(password)) {
				return user;
			}
		}
		return null;
	}
	
	

	@Override
	public UserDetails loadUserByUsername(String usermail) throws UsernameNotFoundException {
		
		Optional<User> user = userDao.findByUsrmail(usermail);
		
		if (user.isEmpty()) {
			throw new UsernameNotFoundException("User not found");
		}
		return new UserDetailsDto(user.get());
	}


}
