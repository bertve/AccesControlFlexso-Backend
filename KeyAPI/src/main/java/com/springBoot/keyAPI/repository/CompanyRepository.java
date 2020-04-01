package com.springBoot.keyAPI.repository;
import org.springframework.data.repository.CrudRepository;

import com.springBoot.keyAPI.model.Company;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyRepository extends CrudRepository<Company,Long> {
    Boolean existsByName(String name);
    Optional<Company> findByName(String name);
}
