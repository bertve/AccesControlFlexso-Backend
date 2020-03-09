package com.springBoot.keyAPI.services;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.springBoot.keyAPI.model.User;
import com.springBoot.keyAPI.repository.UserRepository;

@Service
public class UserService implements IService<User> {

	@Autowired
	private UserRepository repo;
	
	public List<User> getAll() {
		List<User> res = new ArrayList<>();
		repo.findAll().forEach(res::add);
		return res;
	}

	public boolean add(User item) {
		try {
			repo.save(item);
			return true;
		}catch(Exception e) {
			return false;
		}
	}

	public boolean remove(long id) {
		User a = repo.findById(id).orElse(null);
		if(a != null) {
			repo.delete(a);
			return true;
		}
		return false;
	}

	public User getById(long id) {
		return repo.findById(id).orElse(null);
	}

	public boolean update(User item) {
		return this.add(item);
	}

	public boolean removeAll() {
		this.repo.deleteAll();
		List<User> res = new ArrayList<>();
		repo.findAll().forEach(res::add);
		return res.isEmpty();
	}

	public boolean existsByEmail(String email) {
		return repo.existsByEmail(email);
	}
}
