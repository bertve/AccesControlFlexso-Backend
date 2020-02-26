package com.springBoot.keyAPI.controllers;

import java.util.List;
import java.util.Set;

import com.springBoot.keyAPI.services.AuthorizedPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springBoot.keyAPI.domain.*;
import com.springBoot.keyAPI.services.CompanyService;
import com.springBoot.keyAPI.services.OfficeService;

@RestController
@RequestMapping(value="/api")
public class OfficeController {
	
	@Autowired
	private OfficeService service;
	
	@Autowired
	private CompanyService companyService;

	@Autowired
	private AuthorizedPersonService personService;
	
	@GetMapping(value="/companies/{id}/offices")
	public Set<Office> findByCompanyId(@PathVariable long id){
		Company c = companyService.getById(id);
		if (c != null) {
			return c.getOffices();
		}
		return null;
	}
	
	@PostMapping(value="/companies/{id}/offices")
	public boolean addOffice(@PathVariable long id,
			@RequestBody Office o) {
		 Company c = companyService.getById(id);
		 if(c != null) {
			 o.setCompany(c);
			 return service.add(o);
		 }
		 return false;
	}
	
	
	@PutMapping("/companies/{companyId}/offices/{officeId}")
	public boolean updateOffice(@PathVariable long companyId,
			@PathVariable long officeId,
			@RequestBody Office o) {
		Company c = this.companyService.getById(companyId);
		if(c != null) {
			Office toBeUpdated = this.service.getById(officeId);
			toBeUpdated.setAddress(o.getAddress());
			return service.add(toBeUpdated);
		}
		return false;
	}
	
	@GetMapping(value="/offices/{id}")
	public Office findById(@PathVariable long id) {
		return service.getById(id);
	}
	
	@DeleteMapping(value="/offices/{id}")
	public boolean removeOffice(@PathVariable long id) {	
		return service.remove(id);
	}
	
	@GetMapping(value="/offices")
	public List<Office> getAllOffices(){
		return service.getAll();
	}

	@PostMapping(value="/offices/{officeId}")
	public boolean addPersonToOffice(
									 @PathVariable long officeId,
									 @RequestBody AuthorizedPerson person){
		Office o = service.getById(officeId);
		AuthorizedPerson a = personService.getById(person.getPersonId());
		System.out.println(o.toString());
		System.out.println(a.toString());
		o.addAuthorizedPerson(a);
		return this.service.update(o);
	}

	@DeleteMapping(value="/offices/{officeId}")
	public boolean removePersonFromOffice(@PathVariable long officeId,
										  @RequestBody AuthorizedPerson person){
		Office o = service.getById(officeId);
		AuthorizedPerson a = personService.getById(person.getPersonId());
		System.out.println(o.toString());
		System.out.println(a.toString());
		o.removeAuthorizedPerson(a);
		return this.service.update(o);
	}
}
