package com.atcs.olx.Entity.Products;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="location")
public class Location {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	@Column(nullable = false)
	private String city;
	
	@Column(nullable = false)
	private String country;
	
	@Column(nullable = false)
	private String address;
	
	@Column
	private int pincode;
	
	@OneToOne
	Product product;
}
