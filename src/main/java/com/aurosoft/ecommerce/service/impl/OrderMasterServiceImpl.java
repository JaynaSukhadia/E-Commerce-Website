package com.aurosoft.ecommerce.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aurosoft.ecommerce.entity.OrderMaster;
import com.aurosoft.ecommerce.repository.OrderRepository;
import com.aurosoft.ecommerce.service.OrderMasterService;

@Service
public class OrderMasterServiceImpl implements OrderMasterService{

	@Autowired
	OrderRepository orderRepository;
	
	
	

	@Override
	public List<OrderMaster> listAllOrderMasters() {
		
		return orderRepository.findAll();
	}

	@Override
	public OrderMaster getOrderMasterById(int id) {
		
		return orderRepository.findById(id).orElseThrow();
	}

	@Override
	public OrderMaster insertOrderMaster(OrderMaster ordermaster) {
		
		return orderRepository.save(ordermaster);
	}

	@Override
	public OrderMaster updateOrderMaster(OrderMaster ordermaster) {
		
		return orderRepository.save(ordermaster);
	}

	@Override
	public int deleteOrderMaster(int id) {
		orderRepository.deleteById(id);
		return id;
	}

}
