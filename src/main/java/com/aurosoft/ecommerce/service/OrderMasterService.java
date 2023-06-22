package com.aurosoft.ecommerce.service;

import java.util.List;

import com.aurosoft.ecommerce.entity.OrderMaster;



public interface OrderMasterService {

	List<OrderMaster> listAllOrderMasters();
	OrderMaster  getOrderMasterById(int id);
	OrderMaster insertOrderMaster(OrderMaster ordermaster);
	OrderMaster updateOrderMaster(OrderMaster ordermaster);
	int deleteOrderMaster(int id);
	
}
