package com.springBoot.keyAPI.controllers;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.springBoot.keyAPI.domain.AuthorizedPerson;
import com.springBoot.keyAPI.domain.Company;
import com.springBoot.keyAPI.services.CompanyService;
import com.springBoot.keyAPI.domain.Office;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping(value="/api/companies")
public class CompanyController {
	@Autowired
	private CompanyService service;
	
	@GetMapping
	public List<Company> getAllCompanies(){
		return service.getAll();
	}
	
	@GetMapping(value="/{id}")
	public Company findById(@PathVariable long id) {
		return service.getById(id);
	}
	
	@PostMapping
	public boolean addCompany(@RequestBody Company c) {
		return service.add(c);
	}
	
	@DeleteMapping(value="/{id}")
	public boolean removeCompany(@PathVariable long id) {
		return service.remove(id);
	}
	
	@PutMapping
	public boolean updateCompany(@RequestBody Company c) {
		return service.update(c);
	}
	
	@DeleteMapping
	public boolean removeAllCompanies() {
		return service.removeAll();
	}	
	
}
