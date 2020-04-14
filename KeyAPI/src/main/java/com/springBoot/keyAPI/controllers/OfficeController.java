package com.springBoot.keyAPI.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.springBoot.keyAPI.model.dto.CompanyDTO;
import com.springBoot.keyAPI.model.dto.OfficeDTO;
import com.springBoot.keyAPI.model.dto.auth.UserDTO;
import com.springBoot.keyAPI.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
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
	public List<OfficeDTO> getAllOffices(){
		List<Office> offices = service.getAll();
		return offices.stream().map(o -> new OfficeDTO(o.getOfficeId(),
				o.getAddress(), new CompanyDTO(o.getCompany().getCompanyId(), o.getCompany().getName()))).collect(Collectors.toList());
	}

	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_COMPANY')")
	@PostMapping(value="/{officeId}/authorizedPersons/{userId}")
	public boolean addPersonToOffice(
									 @PathVariable long officeId,
									 @PathVariable long userId){
		Office o = service.getById(officeId);
		User a = personService.getById(userId);
		if(o == null || a == null){
			return false;
		}
		o.addAuthorizedPerson(a);
		return this.service.update(o);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_COMPANY')")
	@DeleteMapping(value="/{officeId}/authorizedPersons/{userId}")
	public boolean removePersonFromOffice(@PathVariable long officeId,
										  @PathVariable long userId){
		Office o = service.getById(officeId);
		User a = personService.getById(userId);
		if(o == null || a == null){
			return false;
		}
		o.removeAuthorizedPerson(a);
		return this.service.update(o);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_COMPANY')")
	@GetMapping(value="/{id}/authorizedPersons")
	public Set<UserDTO> getAuthorizedPersonsByOfficeId(@PathVariable long id){
		Office o = this.service.getById(id);
		if(o == null){
			return new HashSet<UserDTO>();
		}

		return o.getUsers().stream().map(u -> {
			CompanyDTO c = null;
			if(u.getCompany()!= null){
				c = new CompanyDTO(u.getCompany().getCompanyId(),u.getCompany().getName());
			}
			return new UserDTO(
					u.getUserId()
					,u.getFirstName()
					,u.getLastName()
					,u.getEmail()
					,u.getPassword()
					,u.getRoles()
					,c
			);
		}).collect(Collectors.toSet());
	}

	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_COMPANY')")
	@GetMapping(value="/{id}/unAuthorizedPersons")
	public Set<UserDTO> getUnAuthorizedPersonsByOfficeId(@PathVariable long id){
		Office o = this.service.getById(id);

		if(o == null){
			return new HashSet<UserDTO>();
		}


		List<User> all = this.personService.getAll().stream().filter(u -> u.getRoles().stream().anyMatch(r-> r.getRoleName() == RoleName.ROLE_USER))
				.collect(Collectors.toList());

		all.removeAll(o.getUsers());


		return all.stream().map(u -> {
			CompanyDTO c = null;
			if(u.getCompany()!= null){
				c = new CompanyDTO(u.getCompany().getCompanyId(),u.getCompany().getName());
			}
			return new UserDTO(
					u.getUserId()
					,u.getFirstName()
					,u.getLastName()
					,u.getEmail()
					,u.getPassword()
					,u.getRoles()
					,c
			);
		}).collect(Collectors.toSet());
	}

	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_COMPANY')")
	@PutMapping()
	public boolean updateOffice(
			@RequestBody Office o) {
		Office toBeUpdated = service.getById(o.getOfficeId());

		if(toBeUpdated!= null){
			toBeUpdated.setAddress(o.getAddress());
			return this.service.update(toBeUpdated);
		}

		return false;
	}
}
