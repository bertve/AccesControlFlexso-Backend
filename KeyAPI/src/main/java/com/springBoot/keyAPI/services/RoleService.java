package com.springBoot.keyAPI.services;

import com.springBoot.keyAPI.model.Role;
import com.springBoot.keyAPI.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class RoleService implements IService<Role> {
    @Autowired
    private RoleRepository repo;

    @Override
    public List<Role> getAll() {
        List<Role> res = new ArrayList<>();
        repo.findAll().forEach(res::add);
        return res;
    }

    @Override
    public boolean add(Role item) {
        try {
            repo.save(item);
            return true;
        }catch(Exception e) {
            return false;
        }
    }

    @Override
    public boolean remove(long id) {
        Role r = repo.findById(id).orElse(null);
        if(r != null) {
            repo.delete(r);
            return true;
        }
        return false;    }

    @Override
    public Role getById(long id) {
        return  repo.findById(id).orElse(null);
    }

    @Override
    public boolean update(Role item) {
        return this.add(item);
    }

    @Override
    public boolean removeAll() {
        this.repo.deleteAll();
        List<Role> res = new ArrayList<>();
        repo.findAll().forEach(res::add);
        return res.isEmpty();
    }

}
