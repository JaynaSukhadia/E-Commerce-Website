package com.aurosoft.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aurosoft.ecommerce.entity.Cart;
import com.aurosoft.ecommerce.entity.Product;
import com.aurosoft.ecommerce.entity.User;
import com.aurosoft.ecommerce.service.CartService;
import com.aurosoft.ecommerce.service.ProductService;
import com.aurosoft.ecommerce.service.UserService;

@Controller
@RequestMapping("/cart")
public class CartController {

	
	@Autowired
	private	CartService cartService;
	private UserService userService;
	private ProductService productService;

	

	public CartController(CartService cartService, UserService userService, ProductService productService) {
		super();
		this.cartService = cartService;
		this.userService = userService;
		this.productService = productService;
	}

	@GetMapping("/list")
	public String listCart(Model m)
	{
		List<Cart> list=cartService.listAllCarts();
		m.addAttribute("list", list);
		
		return "admin/cart-list";
	}
	
	@GetMapping("/new")
	public String showForm(Cart cart,Model m)
	{
		List<User> list=userService.listAllUsers();
		m.addAttribute("listUser", list);
		
		List<Product> list1=productService.listAllProducts();
		m.addAttribute("listProduct", list1);
		
		return "admin/cart-add";
	}
	
	@PostMapping("/insert")
	public String insert(@ModelAttribute("cart")Cart cart, 
						@ModelAttribute ("product")Product product)
	{
		cart.setRate(product.getRate());
		cart.setAmount(product.getRate()* cart.getQty());
		
		cartService.insertCart(cart);
		
		return "redirect:/cart/list";
	}
	
	@GetMapping(value="/edit/{id}")
	public String edit(@PathVariable int id,Model m)
	{
		Cart cart = cartService.getCartById(id);
		m.addAttribute("cart",cart);
		
		List<User> list=userService.listAllUsers();
		m.addAttribute("listUser", list);
		
		List<Product> list1=productService.listAllProducts();
		m.addAttribute("listProduct", list1);
		
		return "admin/cart-edit";
	}
	
	@PostMapping(value="/update")
	public String update(@ModelAttribute("cart") Cart cart)
	{
		cartService.updateCart(cart);
		
		return "redirect:/cart/list";
	}
	
	@GetMapping(value="/delete/{id}")
	public String delete(@PathVariable int id)
	{
		cartService.deleteCart(id);
		
		return "redirect:/cart/list";
	}
}
