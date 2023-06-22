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

import com.aurosoft.ecommerce.entity.User;
import com.aurosoft.ecommerce.service.UserService;

@Controller
@RequestMapping("/user")

public class UserController {
	@Autowired
	private	UserService userService;
	
	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}

	@GetMapping("/list")
	public String listUser(Model m)
	{
		List<User> list=userService.listAllUsers();
		m.addAttribute("list", list);
		
		return "admin/user-list";
	}
	
	@GetMapping("/new")
	public String showForm(User user)
	{
		
		return "admin/user-add";
	}
	
	@PostMapping("/insert")
	public String insert(@ModelAttribute("user")User user)
	{
		userService.insertUser(user);
		return "redirect:/user/list";
	}
	
	@GetMapping(value="/edit/{id}")
	public String edit(@PathVariable int id,Model m)
	{
		User user = userService.getUserById(id);
		m.addAttribute("user",user);
		
		return "admin/user-edit";
	}
	
	@PostMapping(value="/update")
	public String update(@ModelAttribute("user") User user)
	{
		userService.updateUser(user);
		return "redirect:/user/list";
	}
	
	@GetMapping(value="/delete/{id}")
	public String delete(@PathVariable int id)
	{
		userService.deleteUser(id);
		return "redirect:/user/list";
	}
	
}
