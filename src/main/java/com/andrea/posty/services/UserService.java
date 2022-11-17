package com.andrea.posty.services;

import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.andrea.posty.models.LoginUser;
import com.andrea.posty.models.User;
import com.andrea.posty.repositories.UserRepository;

@Service
public class UserService {
	@Autowired 
	private UserRepository userRepo;
	
	public User register(User u, BindingResult result) {
		
		if(!u.getPassword().equals(u.getConfirmPassowrd())) {
			result.rejectValue("password", "Matches", "Passwords must match!");
		}
		Optional<User> user =userRepo.findOneByEmail(u.getEmail());
		if (user.isPresent()) {
			result.rejectValue("email", "Taken", "That email is already in use!");
		}
		if (result.hasErrors()) {
			return null;
		}
		else {
			String hassPass= BCrypt.hashpw(u.getPassword(), BCrypt.gensalt());   //need this to slow down hackers
			u.setPassword(hassPass);
			return userRepo.save(u);
		}
	}
	
	public User login(LoginUser u) {
		Optional<User> user = userRepo.findOneByEmail(u.getEmail());
		if (user.isPresent()) {
			if (BCrypt.checkpw(u.getPassword(), user.get().getPassword())) {
				return user.get();
			}
		}
		return null;
	}
	public User getById(Long id) {
		Optional<User> user = userRepo.findById(id);
		if (user.isPresent()) {
			return user.get();
		}
		else {
			return null;
		}
	}
}
