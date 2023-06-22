package com.aurosoft.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aurosoft.ecommerce.entity.Cart;
import com.aurosoft.ecommerce.entity.User;

public interface CartRepository extends JpaRepository<Cart, Integer>{

	List<Cart> findByUser(User user);
}
