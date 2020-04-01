package com.springBoot.keyAPI.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.springBoot.keyAPI.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.springBoot.keyAPI.model.*;
import com.springBoot.keyAPI.services.OfficeService;

@RestController
@RequestMapping(value="/api/offices")
public class OfficeController {
	
	@Autowired
	private OfficeService service;

	@Autowired
	private UserService personService;

	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_COMPANY')")
	@GetMapping(value="/{id}")
	public Office findById(@PathVariable long id) {
		return service.getById(id);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping
	public List<Office> getAllOffices(){
		return service.getAll();
	}

	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_COMPANY')")
	@PostMapping(value="/{officeId}/authorizedPersons")
	public boolean addPersonToOffice(
									 @PathVariable long officeId,
									 @RequestBody User person){
		Office o = service.getById(officeId);
		User a = personService.getById(person.getUserId());
		if(o == null || a == null){
			return false;
		}
		o.addAuthorizedPerson(a);
		return this.service.update(o);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_COMPANY')")
	@DeleteMapping(value="/{officeId}/authorizedPersons")
	public boolean removePersonFromOffice(@PathVariable long officeId,
										  @RequestBody User person){
		Office o = service.getById(officeId);
		User a = personService.getById(person.getUserId());
		if(o == null || a == null){
			return false;
		}
		o.removeAuthorizedPerson(a);
		return this.service.update(o);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_COMPANY')")
	@GetMapping(value="/{id}/authorizedPersons")
	public Set<User> getAuthorizedPersonsByOfficeId(@PathVariable long id){
		Office o = this.service.getById(id);
		if(o == null){
			return new HashSet<User>();
		}
		return o.getUsers();
	}
}
