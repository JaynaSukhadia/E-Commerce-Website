package com.aurosoft.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aurosoft.ecommerce.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{

	
	User findByEmailAndPassword(String email, String password);
	User  findByEmail(String email);
	boolean existsByEmail(String email);
	
}
