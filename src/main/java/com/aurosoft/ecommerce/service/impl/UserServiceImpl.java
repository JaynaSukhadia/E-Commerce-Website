package com.aurosoft.ecommerce.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aurosoft.ecommerce.entity.User;
import com.aurosoft.ecommerce.repository.UserRepository;
import com.aurosoft.ecommerce.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserRepository userRepository;
	
	@Override
	public List<User> listAllUsers() {
		
		return userRepository.findAll();
	}

	@Override
	public User getUserById(int id) {
		
		return userRepository.findById(id).orElseThrow();
	}

	@Override
	public User insertUser(User user) {
		
		return userRepository.save(user);
	}

	@Override
	public User updateUser(User user) {
		
		return userRepository.save(user);
	}

	@Override
	public int deleteUser(int id) {
		userRepository.deleteById(id);
		return id;
	}

	public User findByEmailAndPassword(String email,String password) {
		
		return userRepository.findByEmailAndPassword(email, password);
	}

	@Override
	public User findByEmail(String email) {
		
		return userRepository.findByEmail(email);
	}

	@Override
	public boolean existsByEmail(String email) {
		
		return userRepository.existsByEmail(email);
	}

	
}
