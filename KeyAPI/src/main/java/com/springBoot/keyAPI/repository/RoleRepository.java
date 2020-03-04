package com.springBoot.keyAPI.repository;

import com.springBoot.keyAPI.model.Role;
import com.springBoot.keyAPI.model.RoleName;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
    Optional<Role> findByRoleName(RoleName roleName);
}