package com.aurosoft.ecommerce.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="orderdetails")


public class OrderDetail {

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id", nullable = false)
	private int id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="order_id", referencedColumnName = "id", nullable = false)
	private OrderMaster orderMaster;
	
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="product_id", referencedColumnName = "id", nullable = false)
	private Product product;
	
	
	@Column(name="qty", nullable = false)
	private int qty;
	
	
	@Column(name="rate", nullable = false)
	private Float rate;
	
	@Column(name="amount", nullable = false)
	private Float amount;
}
