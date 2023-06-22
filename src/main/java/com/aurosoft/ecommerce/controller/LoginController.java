package com.aurosoft.ecommerce.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.aurosoft.ecommerce.entity.User;
import com.aurosoft.ecommerce.service.UserService;


import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {

	
	UserService userService;
	
	public LoginController(UserService userService) {
		super();
		this.userService = userService;
	}

	
	
	@GetMapping("/signup")
	public String signup(User user) {
		
		return "signup";
		}

	
	
	@PostMapping("/signup")
	public String newsignup(@ModelAttribute("user") User user,HttpSession session)
	{
		//user.setReg_date(new Date());
		userService.insertUser(user);
		
		session.setAttribute("msg", "Signup Successfully, you can login.....");
		return "redirect:/login";
	}
	
	@GetMapping("/forgot-pass")
	public String forgotPass(User user) {
		
		return "forgot-pass";
		}
	
	
	@PostMapping("/update_forgot_pass")
	public String forgotpass(@RequestParam("email") String email,
			 HttpSession session) 
	{
		User user = userService.findByEmail(email);
	if(user !=null)
	{
		session.setAttribute("email", email);
		return "redirect:/reset-pass";
	}else
	{
			session.setAttribute("msg","something went wrong");
			return "redirect:/forgot-pass";
		}
	}
	
	
	@GetMapping("/reset-pass")
	public String reset() {
		
		return "reset-pass";
	}

	@PostMapping("/reset-pass1")
	public String resetpass(@RequestParam("password") String password,
			@RequestParam("cpassword") String cpassword, HttpSession session)
	{
		if(password.equals(cpassword))
		{
			String email = session.getAttribute("email").toString();
			User user = userService.findByEmail(email);
			user.setPassword(password);
			user = userService.updateUser(user);
			if(user!=null)
			{
				session.setAttribute("msg","Password Reset");
				return "redirect:/login";
			}
			else
			{
				session.setAttribute("msg", "Something went wrong!!");
				return "redirect:/reset-pass";
			}
		}
			else
			{
				session.setAttribute("msg", "Password and confirm password not match");
				return "redirect:/reset-pass";
			}
		}

	
	@GetMapping("/logout")
	public String logout() {
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
			HttpSession session = attr.getRequest().getSession();
		
		if(session.getAttribute("uname") != null)
		 session.removeAttribute("uname");

		if(session.getAttribute("uid") != null)
			 session.removeAttribute("uid");
			
		if(session.getAttribute("urole") != null)
			 session.removeAttribute("urole");
		
		
		session.setAttribute("msg", "You are successfully logout from system");
				return "redirect:/index";
		} 
}
