package com.aurosoft.ecommerce.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="products")


public class Product {


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id", nullable = false)
	private int id;
	
	
	@Column(name="name",nullable = false)
	private String name;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="category_id", referencedColumnName = "id", nullable = false)
	private Category category;
	
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="brand_id", referencedColumnName = "id", nullable = false)
	private Brand brand;	
	
	
	@OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
	private Set<OrderDetail> orderdetaillist = new HashSet<>();
	
	@OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
	private Set<Cart> cartlist = new HashSet<>();
	
	
	@Column(name="image", nullable = false)
	private String image;
	@Transient
	public String getPhotosImagePath()
	{
		if(image==null || id==0)return null;
		return "/product-photos/" + id + "/" +image;
	}

	@Column(name="mrp",nullable = false)
	private Float mrp;
	
	
	@Column(name="rate",nullable = false)
	private Float rate;


}
