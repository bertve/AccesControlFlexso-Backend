package com.springBoot.keyAPI.repository;

import org.springframework.data.repository.CrudRepository;

import com.springBoot.keyAPI.domain.Office;

public interface OfficeRepository extends CrudRepository<Office, Long> {

}
