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
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="brands")


public class Brand {


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
		return "/brand-photos/" + id + "/" +image;
	}
	
	@OneToMany(mappedBy = "brand", fetch = FetchType.LAZY)
	private Set<Product> productlist = new HashSet<>();
	
}
