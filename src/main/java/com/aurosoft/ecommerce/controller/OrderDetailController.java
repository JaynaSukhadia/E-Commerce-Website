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

import com.aurosoft.ecommerce.entity.OrderDetail;
import com.aurosoft.ecommerce.entity.OrderMaster;
import com.aurosoft.ecommerce.entity.Product;

import com.aurosoft.ecommerce.service.OrderDetailService;
import com.aurosoft.ecommerce.service.OrderMasterService;
import com.aurosoft.ecommerce.service.ProductService;

@Controller
@RequestMapping("/orderdetail")

public class OrderDetailController {

	
	@Autowired
private	OrderDetailService orderDetailService;
private ProductService productService;
private OrderMasterService orderMasterService;

	public OrderDetailController(OrderDetailService orderDetailService, ProductService productService,
		OrderMasterService orderMasterService) {
	super();
	this.orderDetailService = orderDetailService;
	this.productService = productService;
	this.orderMasterService = orderMasterService;
}

	@GetMapping("/list")
	public String listOrderDetail(Model m)
	{
		List<OrderDetail> list=orderDetailService.listAllOrderDetails();
		m.addAttribute("list", list);
		
		return "admin/orderdetail-list";
	}
	
	@GetMapping("/new")
	public String showForm(OrderDetail orderDetail,Model m)
	{
		
		List<OrderMaster> list=orderMasterService.listAllOrderMasters();
		m.addAttribute("listOrderMaster", list);
		
		
		List<Product> list1=productService.listAllProducts();
		m.addAttribute("listProduct", list1);
		
		return "admin/orderdetail-add";
	}
	
	@PostMapping("/insert")
	public String insert(@ModelAttribute("orderDetail")OrderDetail orderDetail)
	{
		orderDetailService.insertOrderDetail(orderDetail);
		
		return "redirect:/orderdetail/list";
	}
	
	@GetMapping(value="/edit/{id}")
	public String edit(@PathVariable int id,Model m)
	{
		OrderDetail orderDetail =orderDetailService.getOrderDetailById(id);
		m.addAttribute("orderDetail",orderDetail);
		
		List<OrderMaster> list=orderMasterService.listAllOrderMasters();
		m.addAttribute("listOrderMaster", list);
		
		
		List<Product> list1=productService.listAllProducts();
		m.addAttribute("listProduct", list1);
		
		
		return "admin/orderdetail-edit";
	}
	
	@PostMapping(value="/update")
	public String update(@ModelAttribute("orderDetail") OrderDetail orderDetail)
	{
		orderDetailService.updateOrderDetail(orderDetail);
		return "redirect:/orderdetail/list";
	}
	
	@GetMapping(value="/delete/{id}")
	public String delete(@PathVariable int id)
	{
		
		orderDetailService.deleteOrderDetail(id);
		return "redirect:/orderdetail/list";
	}
	
}
