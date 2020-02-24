package com.springBoot.keyAPI.repository;
import org.springframework.data.repository.CrudRepository;

import com.springBoot.keyAPI.domain.Company;

public interface CompanyRepository extends CrudRepository<Company,Long> {

}
