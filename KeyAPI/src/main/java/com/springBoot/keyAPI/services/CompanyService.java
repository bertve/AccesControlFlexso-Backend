package com.springBoot.keyAPI.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springBoot.keyAPI.domain.Company;
import com.springBoot.keyAPI.repository.CompanyRepository;

@Service
public class CompanyService implements IService<Company>{
	@Autowired
	private CompanyRepository companyRepo;

	@Override
	public List<Company> getAll() {
		List<Company> res = new ArrayList<>();
		companyRepo.findAll().forEach(res::add);
		return res;
	}

	@Override
	public boolean add(Company item) {
		try {
			companyRepo.save(item);
			return true;
		}catch(Exception e) {
			return false;
		}
	}

	@Override
	public boolean remove(long id) {
		Company c = companyRepo.findById(id).orElse(null);
		if(c != null) {
			companyRepo.delete(c);
			return true;
		}
		return false;
	}

	@Override
	public Company getById(long id) {
		Company c = companyRepo.findById(id).orElse(null);
		return c;
	}

	@Override
	public boolean update(Company item) {
		return this.add(item);
	}


}
