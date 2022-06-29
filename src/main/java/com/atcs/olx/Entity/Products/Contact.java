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
@Table(name= "contact")
public class Contact {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	@Column(nullable = false, length = 25)
    private String firstname;

    @Column(nullable = false, length = 25)
    private String lastname;
	
	@Column(nullable = false, length = 100)
	private String email;
	
	@Column(nullable = false, length = 25)
	private String phone_number;
	
	@OneToOne
	Product product;
}


