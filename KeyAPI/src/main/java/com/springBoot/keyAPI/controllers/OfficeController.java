package com.springBoot.keyAPI.controllers;

import java.util.List;

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
import com.springBoot.keyAPI.services.OfficeService;

@RestController
@RequestMapping(value="/api/office")
public class OfficeController {
	
	@Autowired
	private OfficeService service;
	
	@GetMapping(value="/getAll")
	public List<Office> getAllOffices(){
		return service.getAll();
	}
	
	@GetMapping(value="/{id}")
	public Office findById(@PathVariable long id) {
		return service.getById(id);
	}
	
	@PostMapping(value="/add")
	public boolean addOffice(@RequestBody Office o) {
		return service.add(o);
	}
	
	@DeleteMapping(value="/delete/{id}")
	public boolean removeOffice(@PathVariable long id) {
		return service.remove(id);
	}
	
	@PutMapping(value="/update")
	public boolean updateOffice(@RequestBody Office o) {
		return service.update(o);
	}
	
	@DeleteMapping(value="/deleteAll")
	public boolean removeAllOffices() {
		return service.removeAll();
	}
	
	@PostMapping(value="{id}/authorizedPerson")
	public boolean addAuthorizedPersonToOffice(@PathVariable long id,@RequestBody AuthorizedPerson a) {
		Office o = service.getById(id);
		o.addAuthorizedPerson(a);
		return service.update(o);
	}
}
