package com.aurosoft.ecommerce.service;

import java.util.List;

import com.aurosoft.ecommerce.entity.Cart;
import com.aurosoft.ecommerce.entity.User;

public interface CartService {

	List<Cart> getCartByUser(User user);
	List<Cart> listAllCarts();
	Cart  getCartById(int id);
	Cart insertCart(Cart cart);
	Cart updateCart(Cart cart);
	int deleteCart(int id);
}
