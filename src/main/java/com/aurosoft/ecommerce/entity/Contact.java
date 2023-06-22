package com.aurosoft.ecommerce.entity;

import java.util.Date;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="contacts")

public class Contact {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	
	@Column(name="id", nullable = false)
	private int id;
	
	@Column(name="fname", nullable = false)
	private String fname;
	
	@Column(name="lname", nullable = false)
	private String lname;
	
	
	@Column(name="mobile", nullable = false)
	private String mobile;
	
	@Column(name="email", nullable = false)
	private String email;
	
	@Column(name="date",insertable = false, updatable = false)
	
	@Temporal(TemporalType.DATE)
	private Date date;
	
	
	
}
