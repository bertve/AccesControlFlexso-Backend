package com.springBoot.keyAPI.controllers;

import com.springBoot.keyAPI.model.Address;
import com.springBoot.keyAPI.model.Office;
import com.springBoot.keyAPI.model.dto.CompanyDTO;
import com.springBoot.keyAPI.model.dto.OfficeDTO;
import com.springBoot.keyAPI.services.OfficeService;
import com.springBoot.keyAPI.services.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.springBoot.keyAPI.model.Company;
import com.springBoot.keyAPI.services.CompanyService;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping(value="/api/companies")
public class CompanyController {
	@Autowired
	private CompanyService service;

	@Autowired
	private OfficeService officeService;

	@Autowired
	private UserService userService;

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping
	public List<Company> getAllCompanies(){
		return service.getAll();
	}

	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_COMPANY')")
	@GetMapping(value="/{id}")
	public Company findById(@PathVariable long id) {
		return service.getById(id);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_COMPANY')")
	@PutMapping
	public boolean updateCompany(@RequestBody Company c) {
		return service.update(c);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_COMPANY')")
	@GetMapping(value="/{id}/offices")
	public List<OfficeDTO> findOfficesByCompanyId(@PathVariable long id){
		Company c = service.getById(id);
		if (c != null) {
			return c.getOffices().stream().map(o -> new OfficeDTO(o.getOfficeId(),
					o.getAddress(), new CompanyDTO(o.getCompany().getCompanyId(), o.getCompany().getName()))).collect(Collectors.toList());
		}
		return null;
	}

	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_COMPANY')")
	@PostMapping(value="/{id}/offices")
	public boolean addOffice(@PathVariable long id,
							 @RequestBody Address a) {
		Company c = service.getById(id);
		Office o = new Office(a);
		if(c != null) {
			o.setCompany(c);
			return officeService.add(o);
		}
		return false;
	}

	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_COMPANY')")
	@DeleteMapping("/{companyId}/offices/{officeId}")
	public boolean removeOffice(@PathVariable long companyId,
								@PathVariable long officeId){
		Company c = this.service.getById(companyId);
		if(c != null ) {
			return officeService.remove(officeId);
		}
		return false;
	}


}
