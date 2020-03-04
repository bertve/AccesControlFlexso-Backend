package com.springBoot.keyAPI.repository;

import org.springframework.data.repository.CrudRepository;

import com.springBoot.keyAPI.model.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Boolean existsByEmail(String email);

}
