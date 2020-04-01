package com.springBoot.keyAPI.controllers;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import com.springBoot.keyAPI.model.Office;
import com.springBoot.keyAPI.model.RoleName;
import com.springBoot.keyAPI.model.dto.CompanyDTO;
import com.springBoot.keyAPI.model.dto.OfficeDTO;
import com.springBoot.keyAPI.model.dto.auth.UserDTO;
import com.springBoot.keyAPI.security.CurrentUser;
import com.springBoot.keyAPI.security.UserPrincipal;
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
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping
    public List<User> getAllUsers() {
        return service.getAll();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')or hasRole('ROLE_COMPANY')")
    @GetMapping(value = "/{id}")
    public User findById(@PathVariable long id) {
        return service.getById(id);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping(value = "/{id}")
    public boolean removeUser(@PathVariable long id) {
        return service.remove(id);
    }

    @PutMapping
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_COMPANY') or hasRole('ROLE_ADMIN')")
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
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_COMPANY') or hasRole('ROLE_ADMIN')")
    public List<OfficeDTO> getAllOfficesWithUserId(@PathVariable long id) {
        Set<Office> offices = service.getById(id).getOffices();
        return offices.stream().map(o -> new OfficeDTO(o.getOfficeId(),
                o.getAddress(), new CompanyDTO(o.getCompany().getCompanyId(), o.getCompany().getName()))).collect(Collectors.toList());
    }

    @GetMapping("/me")
    @PreAuthorize("hasRole('ROLE_USER')or hasRole('ROLE_COMPANY') or hasRole('ROLE_ADMIN')")
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

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/companies")
    public List<User> getAllUsersWithRoleCompany(){
        List<User> res = service.getAll();
        return res.stream().filter(u -> u.getRoles().stream().anyMatch(r-> r.getRoleName() == RoleName.ROLE_COMPANY))
                .collect(Collectors.toList());
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_COMPANY')")
    @GetMapping("/normal")
    public List<User> getAllUsersWithRoleUser(){
        List<User> res = service.getAll();
        return res.stream().filter(u -> u.getRoles().stream().anyMatch(r-> r.getRoleName() == RoleName.ROLE_USER))
                .collect(Collectors.toList());
    }

}
