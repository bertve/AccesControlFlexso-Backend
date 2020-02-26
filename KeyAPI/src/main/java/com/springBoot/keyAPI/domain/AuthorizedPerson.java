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
public class AuthorizedPerson extends Audit implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private long personId;
	private String firstName, lastName,email;
	@ManyToMany(mappedBy="authorizedPersons",cascade = CascadeType.PERSIST)
	public Set<Office> offices;
	public AuthorizedPerson(String firstName, String lastName, String email) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.offices = new HashSet<>();
	}
	
	

}
