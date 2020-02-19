package com.springBoot.keyAPI;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import domain.Key;

@RestController
@RequestMapping(value="/api/key")
public class KeyController {
	
	@GetMapping
	public String test() {
		return "test api";
	}
	@GetMapping(value="/getAll")
	public Key[] getAllKeys() {
		Key[] res = {new Key("testje"), new Key("testje2")}; 
		return res;
	}
	
}
