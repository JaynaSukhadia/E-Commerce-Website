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

import com.aurosoft.ecommerce.entity.OrderMaster;

import com.aurosoft.ecommerce.entity.User;
import com.aurosoft.ecommerce.service.OrderMasterService;
import com.aurosoft.ecommerce.service.UserService;


@Controller
@RequestMapping("/order")

public class OrderMasterController {

	@Autowired
	
	private OrderMasterService orderMasterService;
	private UserService userService;
	
	public OrderMasterController(OrderMasterService orderMasterService, UserService userService) {
		super();
		this.orderMasterService = orderMasterService;
		this.userService = userService;
	}
	
	@GetMapping("/list")
	public String listOrder(Model m)
	{
  	List<OrderMaster> list = orderMasterService.listAllOrderMasters();
  	m.addAttribute("list", list);
  	
  	return "admin/order-list";
  	
	}
	@GetMapping("/new")
	public String showForm(OrderMaster orderMaster, Model m)
	{
		List<User> list= userService.listAllUsers();
		m.addAttribute("listUser",list );
		
		return "admin/order-add";
	}
	
	@PostMapping("/insert")
	
		public String insert(@ModelAttribute("orderMaster") OrderMaster orderMaster)
		{
			orderMasterService.insertOrderMaster(orderMaster);
			return "redirect:/order/list";
		}
	
	@GetMapping(value="/edit/{id}")
	public String edit(@PathVariable int id, Model m)
	{
		OrderMaster orderMaster= orderMasterService.getOrderMasterById(id);
		m.addAttribute("orderMaster", orderMaster);
		
		List<User> list = userService.listAllUsers();
	  	m.addAttribute("listUser", list);
		
	  	return "admin/order-edit";
		
	}
	
	@PostMapping(value = "/update")
	public String update(@ModelAttribute("orderMaster") OrderMaster orderMaster)
	{
		orderMasterService.updateOrderMaster(orderMaster);
		return "redirect:/order/list";
		
	}
	
	@GetMapping(value="/delete/{id}")
	public String delete(@PathVariable int id, Model m)
	{
		orderMasterService.deleteOrderMaster(id);
		return "redirect:/order/list";
		
	}
	
	
}
