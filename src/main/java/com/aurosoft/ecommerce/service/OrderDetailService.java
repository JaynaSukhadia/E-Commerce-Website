package com.aurosoft.ecommerce.service;

import java.util.List;

import com.aurosoft.ecommerce.entity.OrderDetail;


public interface OrderDetailService {

	

	List<OrderDetail> listAllOrderDetails();
	OrderDetail  getOrderDetailById(int id);
	OrderDetail insertOrderDetail(OrderDetail orderdetail);
	OrderDetail updateOrderDetail(OrderDetail orderdetail);
	int deleteOrderDetail(int id);
}
