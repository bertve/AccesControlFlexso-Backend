package com.springBoot.keyAPI.controllers;

import java.util.List;
import java.util.Set;

import com.springBoot.keyAPI.domain.Office;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.springBoot.keyAPI.domain.AuthorizedPerson;
import com.springBoot.keyAPI.services.AuthorizedPersonService;

@RestController
@RequestMapping(value="/api/authorizedPersons")
public class AuthorizedPersonController {
	
	@Autowired
	private AuthorizedPersonService service;
	
	@GetMapping
	public List<AuthorizedPerson> getAllAuthorizedPersons(){
		return service.getAll();
	}
	
	@GetMapping(value="/{id}")
	public AuthorizedPerson findById(@PathVariable long id) {
		return service.getById(id);
	}
	
	@PostMapping
	public boolean addAuthorizedPerson(@RequestBody AuthorizedPerson a) {
		return service.add(a);
	}
	
	@DeleteMapping(value="/{id}")
	public boolean removeAuthorizedPerson(@PathVariable long id) {
		return service.remove(id);
	}
	
	@PutMapping
	public boolean updateAuthorizedPerson(@RequestBody AuthorizedPerson a) {
		return service.update(a);
	}
	
	@DeleteMapping
	public boolean removeAllAuthorizedPersons() {
		return service.removeAll();
	}

	@GetMapping("/{id}/offices")
	public Set<Office> getAllOfficesWithPersonId(@PathVariable long id){
		return service.getById(id).getOffices();
	}
	
}
