package com.springBoot.keyAPI.domain;

import java.io.Serializable;
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
public class AuthorizedPerson implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private long personId;
	private String firstName, lastName,email;
	@ManyToMany(mappedBy="authorizedPersons")
	public Set<Office> offices;

}
