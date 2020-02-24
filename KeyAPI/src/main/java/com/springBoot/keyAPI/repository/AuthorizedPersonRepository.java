package com.springBoot.keyAPI.repository;

import org.springframework.data.repository.CrudRepository;

import com.springBoot.keyAPI.domain.AuthorizedPerson;

public interface AuthorizedPersonRepository extends CrudRepository<AuthorizedPerson, Long> {

}
