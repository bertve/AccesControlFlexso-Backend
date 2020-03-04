package com.springBoot.keyAPI.repository;
import org.springframework.data.repository.CrudRepository;

import com.springBoot.keyAPI.model.Company;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends CrudRepository<Company,Long> {

}
