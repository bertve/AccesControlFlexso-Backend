package com.springBoot.keyAPI.services;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springBoot.keyAPI.domain.AuthorizedPerson;
import com.springBoot.keyAPI.domain.Company;
import com.springBoot.keyAPI.repository.AuthorizedPersonRepository;
@Service
public class AuthorizedPersonService implements IService<AuthorizedPerson> {

	@Autowired
	private AuthorizedPersonRepository repo;
	
	public List<AuthorizedPerson> getAll() {
		List<AuthorizedPerson> res = new ArrayList<>();
		repo.findAll().forEach(res::add);
		return res;
	}

	public boolean add(AuthorizedPerson item) {
		try {
			repo.save(item);
			return true;
		}catch(Exception e) {
			return false;
		}
	}

	public boolean remove(long id) {
		AuthorizedPerson a = repo.findById(id).orElse(null);
		if(a != null) {
			repo.delete(a);
			return true;
		}
		return false;
	}

	public AuthorizedPerson getById(long id) {
		AuthorizedPerson a = repo.findById(id).orElse(null);
		return a;	
	}

	public boolean update(AuthorizedPerson item) {
		return this.add(item);
	}

	public boolean removeAll() {
		this.repo.deleteAll();
		List<AuthorizedPerson> res = new ArrayList<>();
		repo.findAll().forEach(res::add);
		return res.isEmpty()? true:false;	
		}

}
