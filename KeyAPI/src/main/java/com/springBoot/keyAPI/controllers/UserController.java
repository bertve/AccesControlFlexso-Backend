package com.springBoot.keyAPI.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.springBoot.keyAPI.model.Office;
import com.springBoot.keyAPI.model.Role;
import com.springBoot.keyAPI.model.dto.CompanyDTO;
import com.springBoot.keyAPI.model.dto.OfficeDTO;
import com.springBoot.keyAPI.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.springBoot.keyAPI.model.User;
import com.springBoot.keyAPI.services.UserService;

@RestController
@RequestMapping(value = "/api/authorizedPersons")
public class UserController {

    @Autowired
    private UserService service;
    @Autowired
    private RoleService roleService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping
    public List<User> getAllUsers() {
        return service.getAll();
    }

    @GetMapping(value = "/{id}")
    public User findById(@PathVariable long id) {
        return service.getById(id);
    }

    @PostMapping
    public boolean addUser(@RequestBody User a) {
        return service.add(a);
    }

    @DeleteMapping(value = "/{id}")
    public boolean removeUser(@PathVariable long id) {
        return service.remove(id);
    }

    @PutMapping
    public boolean updateUser(@RequestBody User u) {
        return service.update(u);
    }

    @GetMapping("/{id}/offices")
    public List<OfficeDTO> getAllOfficesWithUserId(@PathVariable long id) {
        Set<Office> offices = service.getById(id).getOffices();
        return offices.stream().map(o -> new OfficeDTO(o.getOfficeId(),
                o.getAddress(), new CompanyDTO(o.getCompany().getCompanyId(), o.getCompany().getName()))).collect(Collectors.toList());
    }

}
