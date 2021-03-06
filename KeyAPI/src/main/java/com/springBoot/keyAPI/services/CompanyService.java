package com.springBoot.keyAPI.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springBoot.keyAPI.model.Company;
import com.springBoot.keyAPI.repository.CompanyRepository;

@Service
public class CompanyService implements IService<Company>{
	
	@Autowired
	private CompanyRepository companyRepo;
	
	public List<Company> getAll() {
		List<Company> res = new ArrayList<>();
		companyRepo.findAll().forEach(res::add);
		return res;
	}

	public boolean add(Company item) {
		try {
			companyRepo.save(item);
			return true;
		}catch(Exception e) {
			return false;
		}
	}	

	public boolean remove(long id) {
		Company c = companyRepo.findById(id).orElse(null);
		if(c != null) {
			companyRepo.delete(c);
			return true;
		}
		return false;
	}

	public Company getById(long id) {
		return companyRepo.findById(id).orElse(null);
	}

	public boolean update(Company item) {
		return this.add(item);
	}

	
	public boolean removeAll() {
		this.companyRepo.deleteAll();
		List<Company> res = new ArrayList<>();
		companyRepo.findAll().forEach(res::add);
		return res.isEmpty();
	}

	public Company getByName(String name){
		return this.companyRepo.findByName(name).orElse(null);
	}
	
}
