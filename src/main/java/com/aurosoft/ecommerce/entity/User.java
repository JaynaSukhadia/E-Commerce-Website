package com.aurosoft.ecommerce.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
@Table(name="users")


public class User {


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	
	@Column(name="id", nullable = false)
	private int id;
	
	@Column(name="fname", nullable = false)
	private String fname;
	
	@Column(name="lname", nullable = false)
	private String lname;
	
	@Column(name="city", nullable = false)
	private String city;
	
	@Column(name="mobile", nullable = false)
	private String mobile;
	
	@Column(name="email", nullable = false)
	private String email;
	
	@Column(name="pass", nullable = false)
	private String password;
	
	@Column(name="role", nullable = false)
	private String role;
	
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	private Set<OrderMaster> orderlist = new HashSet<>();
	
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	private Set<Cart> cartlist = new HashSet<>();
}
