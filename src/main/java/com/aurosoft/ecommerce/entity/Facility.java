package com.aurosoft.ecommerce.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name="services")

public class Facility {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	
	@Column(name="id", nullable = false)
	private int id;
	
	@Column(name="title", nullable = false)
	private String title;
	
	@Column(name="detail", nullable = false)
	private String detail;
	
	@Column(name="icon", nullable = false)
	private String icon;
	@Transient
	public String getPhotosImagePath()
	{
		if(icon==null || id==0)return null;
		return "/service-photos/" + id + "/" +icon;
	}
}
