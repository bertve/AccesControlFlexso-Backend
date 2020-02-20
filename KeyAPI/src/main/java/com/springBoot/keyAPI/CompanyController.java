package com.springBoot.keyAPI;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import service.CompanyDao;
import domain.Company;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping(value="/api/company")
public class CompanyController {
	@Autowired
	private CompanyDao companyDao;
	
	@GetMapping(value="/getAll")
	public List<Company> getAllCompanies(){
		return companyDao.getAll();
	}
}
