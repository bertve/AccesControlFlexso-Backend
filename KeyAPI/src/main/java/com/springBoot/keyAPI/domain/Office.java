package com.springBoot.keyAPI.domain;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.springBoot.keyAPI.domain.Address;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table
public class Office implements Serializable  {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long officeId;
	@Embedded
	private Address address;
	@ManyToOne(optional = false,fetch=FetchType.LAZY)
	@JoinColumn(name="companyId")
	private Company company;
	@ManyToMany
	@JoinTable(name="officeAuthorizedPerson",
	joinColumns=@JoinColumn(name="officeId"),
	inverseJoinColumns=@JoinColumn(name="personId"))
	@JoinColumn()
	private Set<AuthorizedPerson> authorizedPersons;	
	
	
	public Office(Address address, Company company) {
		super();
		this.address = address;
		this.company = company;
	}

	public void addAuthorizedPerson(AuthorizedPerson a) {
		this.authorizedPersons.add(a);
	}
}
