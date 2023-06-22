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
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity

@AllArgsConstructor
@NoArgsConstructor
@Table(name="categories")

public class Category {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id", nullable = false)
	private int id;
	
	
	@Column(name="name",nullable = false)
	private String name;
	
	@Column(name="image", nullable = false)
	private String image;
	@Transient
	public String getPhotosImagePath()
	{
		if(image==null || id==0)return null;
		return "/category-photos/" + id + "/" +image;
	}

	@OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
	private Set<Product> productlist = new HashSet<>();
	
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Set<Product> getProductlist() {
		return productlist;
	}

	public void setProductlist(Set<Product> productlist) {
		this.productlist = productlist;
	}
	
	
}
