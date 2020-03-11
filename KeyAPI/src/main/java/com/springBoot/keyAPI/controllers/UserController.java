package com.springBoot.keyAPI.controllers;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.springBoot.keyAPI.model.Office;
import com.springBoot.keyAPI.model.dto.CompanyDTO;
import com.springBoot.keyAPI.model.dto.OfficeDTO;
import com.springBoot.keyAPI.model.dto.auth.UserDTO;
import com.springBoot.keyAPI.security.CurrentUser;
import com.springBoot.keyAPI.security.UserPrincipal;
import com.springBoot.keyAPI.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import com.springBoot.keyAPI.model.User;
import com.springBoot.keyAPI.services.UserService;

@RestController
@RequestMapping(value = "/api/users")
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
    @PreAuthorize("hasRole('ROLE_USER')")
    public UserDTO updateUser(@RequestBody User u) {
        User res = service.getById(u.getUserId());
        res.setFirstName(u.getFirstName());
        res.setLastName(u.getLastName());
        res.setEmail(u.getEmail());
        res.setPassword(bCryptPasswordEncoder.encode(u.getPassword()));
        if(service.update(res)){
            return new UserDTO(res.getUserId(),res.getFirstName(),res.getLastName(),res.getEmail(),u.getPassword());
        }else{
            return null;
        }
    }

    @GetMapping("/{id}/offices")
    public List<OfficeDTO> getAllOfficesWithUserId(@PathVariable long id) {
        Set<Office> offices = service.getById(id).getOffices();
        return offices.stream().map(o -> new OfficeDTO(o.getOfficeId(),
                o.getAddress(), new CompanyDTO(o.getCompany().getCompanyId(), o.getCompany().getName()))).collect(Collectors.toList());
    }

    @GetMapping("/me")
    @PreAuthorize("hasRole('ROLE_USER')")
    public UserDTO getCurrentUser(@CurrentUser UserPrincipal currentUser) {
        UserDTO user = new UserDTO(currentUser.getId()
                ,currentUser.getFirstName()
                ,currentUser.getLastName()
                ,currentUser.getUsername()
                );
        return user;
    }

    @GetMapping("/checkEmailAvailability")
    public boolean checkEmailAvailability(@RequestParam(value = "email") String email) {
        return !service.existsByEmail(email);
    }

}
