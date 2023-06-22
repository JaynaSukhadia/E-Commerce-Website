package com.aurosoft.ecommerce.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aurosoft.ecommerce.entity.OrderDetail;
import com.aurosoft.ecommerce.repository.OrderDetailRepository;
import com.aurosoft.ecommerce.service.OrderDetailService;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {
	@Autowired
	OrderDetailRepository detailRepository;
	
	@Override
	public List<OrderDetail> listAllOrderDetails() {
		
		return detailRepository.findAll();
	}

	@Override
	public OrderDetail getOrderDetailById(int id) {
		
		return detailRepository.findById(id).orElseThrow();
	}

	@Override
	public OrderDetail insertOrderDetail(OrderDetail orderdetail) {
		
		return detailRepository.save(orderdetail);
	}

	@Override
	public OrderDetail updateOrderDetail(OrderDetail orderdetail) {
		
		return detailRepository.save(orderdetail);
	}

	@Override
	public int deleteOrderDetail(int id) {
		detailRepository.deleteById(id);
		return id;
	}

}
