package com.aurosoft.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aurosoft.ecommerce.entity.OrderDetail;

public interface OrderDetailRepository extends JpaRepository<OrderDetail,Integer>{

}
