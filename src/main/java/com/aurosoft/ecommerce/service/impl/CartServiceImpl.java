package com.aurosoft.ecommerce.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aurosoft.ecommerce.entity.Cart;
import com.aurosoft.ecommerce.entity.User;
import com.aurosoft.ecommerce.repository.CartRepository;
import com.aurosoft.ecommerce.service.CartService;
@Service
public class CartServiceImpl implements CartService{

	@Autowired
	CartRepository cartRepository;
	
	@Override
	public List<Cart> listAllCarts() {
		
		return cartRepository.findAll();
	}

	@Override
	public Cart getCartById(int id) {
	
		return cartRepository.findById(id).orElseThrow();
	}

	@Override
	public Cart insertCart(Cart cart) {
		
		return cartRepository.save(cart);
	}

	@Override
	public Cart updateCart(Cart cart) {
		
		return cartRepository.save(cart);
	}

	@Override
	public int deleteCart(int id) {
		cartRepository.deleteById(id);
		return id;
	}

	@Override
	public List<Cart> getCartByUser(User user) {
		
		return cartRepository.findByUser(user);
	}

}
