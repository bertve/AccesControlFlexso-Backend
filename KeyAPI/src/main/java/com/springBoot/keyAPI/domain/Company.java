package com.springBoot.keyAPI.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table
public class Company implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long companyId;
	private String name;
	@OneToMany(mappedBy="company")
	private Set<Office> offices;
	public Company(String name) {
		super();
		this.name = name;
		this.offices = new HashSet<>();
	} 
	
	public void addOffice(Office o) {
		offices.add(o);
	}
	
	public void removeOffice(Office o) {
		offices.remove(o);
	}
		
}
