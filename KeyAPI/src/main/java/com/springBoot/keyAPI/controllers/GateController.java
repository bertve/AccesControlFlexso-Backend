package com.springBoot.keyAPI.controllers;

import com.springBoot.keyAPI.model.User;
import com.springBoot.keyAPI.services.OfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/gate")
public class GateController {

    @Autowired
    private OfficeService officeService;

    @GetMapping("/{officeId}")
    public List<Long> getAuthorizedIdsByOfficeId(@PathVariable("officeId")Long id){
        return this.officeService.getById(id).getUsers().stream().map(User::getUserId).collect(Collectors.toList());
    }
}
