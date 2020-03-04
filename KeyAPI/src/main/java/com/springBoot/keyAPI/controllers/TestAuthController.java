package com.springBoot.keyAPI.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/test")
public class TestAuthController {
    @GetMapping
    public String test(){
        return "is not locked";
    }
}
