package com.springBoot.keyAPI.repository;

import com.springBoot.keyAPI.model.Company;
import com.springBoot.keyAPI.model.Key;
import com.springBoot.keyAPI.model.KeyId;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface KeyRepository extends CrudRepository<Key, KeyId> {
    Optional<Key> findByUserIdAndOfficeIdAndDeviceId(Long userId,Long officeId,String deviceId);
}