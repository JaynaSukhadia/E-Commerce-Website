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
@Table(name="company")




public class Company {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	
	@Column(name="id", nullable = false)
	private int id;
	
	@Column(name="name", nullable = false)
	private String name;
	
	@Column(name="logo", nullable = false)
	private String logo;
	
	@Transient
	public String getPhotosImagePath()
	{
		if(logo==null || id==0)return null;
		return "/company-photos/" + id + "/" +logo;
	}
	
	@Column(name="email", nullable = false)
	private String email;
	
	@Column(name="mobile", nullable = false)
	private String mobile;
	
	@Column(name="address1", nullable = false)
	private String address1;
	
	@Column(name="address2", nullable = false)
	private String address2;
	
	@Column(name="fb", nullable = false)
	private String fb;
	
	@Column(name="twitter", nullable = false)
	private String twitter;
	
	
}
