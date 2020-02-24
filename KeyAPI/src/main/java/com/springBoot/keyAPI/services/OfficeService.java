package com.springBoot.keyAPI.services;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springBoot.keyAPI.domain.Office;
import com.springBoot.keyAPI.repository.OfficeRepository;
@Service
public class OfficeService implements IService<Office> {
	
	@Autowired
	private OfficeRepository repo;

	@Override
	public List<Office> getAll() {
		List<Office> res = new ArrayList<>();
		repo.findAll().forEach(res::add);
		return res;
	}

	@Override
	public boolean add(Office item) {
		try {
			repo.save(item);
			return true;
		}catch(Exception e) {
			return false;
		}
	}

	@Override
	public boolean remove(long id) {
		Office o = repo.findById(id).orElse(null);
		if(o != null) {
			repo.delete(o);
			return true;
		}
		return false;
	}

	@Override
	public Office getById(long id) {
		Office o = repo.findById(id).orElse(null);
		return o;		
	}

	@Override
	public boolean update(Office item) {
		return this.add(item);
	}

}
