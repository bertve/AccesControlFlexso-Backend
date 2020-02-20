package com.springBoot.keyAPI;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import domain.Company;
import services.CompanyService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping(value="/api/company")
public class CompanyController {
	@Autowired
	private CompanyService service;
	
	@GetMapping(value="/getAll")
	public List<Company> getAllCompanies(){
		return service.getAll();
	}
	
	@GetMapping(value="/{id}")
	public Company findById(@PathVariable long id) {
		return service.getById(id);
	}
	
	@PostMapping(value="/add")
	public boolean addCompany(@RequestBody Company c) {
		return service.add(c);
	}
	
	@DeleteMapping(value="/delete/{id}")
	public boolean removeCompany(@PathVariable long id) {
		return service.remove(id);
	}
	
	@PutMapping(value="/update")
	public boolean updateCompany(@RequestBody Company c) {
		return service.update(c);
	}
}
