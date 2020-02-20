package com.springBoot.keyAPI;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
@RequestMapping("/")
@Controller
public class HomeController {
	@GetMapping
	public String getKeyAPI() {
		return "redirect:/api";
	}
	
	@GetMapping("/api")
	public String test(){
		return "api is running";
	}
}
