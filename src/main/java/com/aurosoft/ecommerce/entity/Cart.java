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

import lombok.Getter;

import lombok.Setter;


@Entity


@Table(name="cart")

public class Cart {

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	
	@Column(name="id", nullable = false)
	private int id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="user_id", referencedColumnName = "id", nullable = false)
	private User user;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="product_id", referencedColumnName = "id", nullable = false)
	private Product product;
	
	@Column(name="qty", nullable = false)
	private int qty;
	
	@Column(name="rate", nullable = false)
	private Float rate;
	
	@Column(name="amount", nullable = false)
	private Float amount;

	
	
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public Float getRate() {
		return rate;
	}

	public void setRate(Float rate) {
		this.rate = rate;
	}

	public Float getAmount() {
		return amount;
	}

	public void setAmount(Float amount) {
		this.amount = amount;
	}

	public Cart(int id, User user, Product product, int qty, Float rate, Float amount) {
		super();
		this.id = id;
		this.user = user;
		this.product = product;
		this.qty = qty;
		this.rate = rate;
		this.amount = amount;
	}

	public Cart() {
		super();
	}

	public Cart(User user, Product product, int qty) {
		super();
		this.user = user;
		this.product = product;
		this.qty = qty;
	}
	
	
	
	
}
