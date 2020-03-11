package com.springBoot.keyAPI.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.NaturalId;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;


@Entity
@Table
public class User extends Audit implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private long userId;

	private String firstName, lastName;

	private String password;

	@NotNull
	@Column(unique=true)
	@Email
	private String email;


	@ManyToMany(mappedBy="users",cascade = CascadeType.PERSIST)
	@JsonIgnore
	public Set<Office> offices;

	/**
	 * Roles are being eagerly loaded here because
	 * they are a fairly small collection of items for this example.
	 */
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "user_roles", joinColumns
			= @JoinColumn(name = "userId")
			,
			inverseJoinColumns = @JoinColumn(name = "roleId"))
	private Set<Role> roles;

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public User(String firstName, String lastName, String email) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}

	public User(String firstName, String lastName, String email,String password) {
		this(firstName,lastName,email);
		this.password = password;
	}

	public User() {
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long personId) {
		this.userId = personId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<Office> getOffices() {
		return offices;
	}

	public void setOffices(Set<Office> offices) {
		this.offices = offices;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User{" +
				"userId=" + userId +
				", firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				", email='" + email + '\'' +
				", password='" + password + '\'' +
				", offices=" + offices +
				", roles=" + roles +
				'}';
	}
}
