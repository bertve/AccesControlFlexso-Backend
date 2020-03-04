package com.springBoot.keyAPI.repository;

import org.springframework.data.repository.CrudRepository;

import com.springBoot.keyAPI.model.Office;
import org.springframework.stereotype.Repository;

@Repository
public interface OfficeRepository extends CrudRepository<Office, Long> {

}
