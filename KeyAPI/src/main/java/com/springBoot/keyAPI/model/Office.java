package com.springBoot.keyAPI.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table
public class Office extends Audit implements Serializable  {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long officeId;
	
	@NotNull
	@Embedded
	private Address address;
	
	@ManyToOne(fetch=FetchType.LAZY,optional = false)
	@JoinColumn(name="companyId",nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE )
	@JsonIgnore
	private Company company;
	
	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(name="officeUsers",
	joinColumns=@JoinColumn(name="officeId"),
	inverseJoinColumns=@JoinColumn(name="userId"))
	@JoinColumn()
	private Set<User> users;


	public Office() {
	}

	public Office(Address address) {
		super();
		this.address = address;
		this.users = new HashSet<>();
	}

	public void addAuthorizedPerson(User a) {
		this.users.add(a);
	}
	
	public void removeAuthorizedPerson(User a) {
		this.users.remove(a);
	}

	public void setCompany(Company c) {
		this.company = c;
	}

	public long getOfficeId() {
		return officeId;
	}

	public void setOfficeId(long officeId) {
		this.officeId = officeId;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Company getCompany() {
		return company;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}
}
