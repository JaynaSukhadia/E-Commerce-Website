package com.aurosoft.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aurosoft.ecommerce.entity.OrderMaster;

public interface OrderRepository extends JpaRepository<OrderMaster, Integer> {

}
