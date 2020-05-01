package com.springBoot.keyAPI.services;

import com.springBoot.keyAPI.model.Key;
import com.springBoot.keyAPI.model.KeyId;
import com.springBoot.keyAPI.repository.KeyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class KeyService{
    @Autowired
    private KeyRepository repo;

    public List<Key> getAll() {
        List<Key> res = new ArrayList<>();
        repo.findAll().forEach(res::add);
        return res;
    }

    public void add(Key item) {
            repo.save(item);
    }

    public boolean remove(KeyId id) {
        Key key = repo.findById(id).orElse(null);
        if(key != null) {
            repo.delete(key);
            return true;
        }
        return false;
    }

    public Key getById(KeyId id) {
        return repo.findByUserIdAndOfficeIdAndDeviceId(id.getUserId(),id.getOfficeId(),id.getDeviceId()).orElse(null);
    }

    public void update(Key item) {
         this.add(item);
    }

}
