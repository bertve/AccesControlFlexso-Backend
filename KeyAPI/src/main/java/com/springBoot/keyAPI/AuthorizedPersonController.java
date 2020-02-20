package com.springBoot.keyAPI;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import service.AuthorizedPersonDao;
import domain.*;

@RestController
@RequestMapping(value="/api/authorizedPerson")
public class AuthorizedPersonController {
	@Autowired
	private AuthorizedPersonDao authorizedPersonDao;
	
	@GetMapping(value="/getAll")
	public List<AuthorizedPerson> getAllAuthorizedPersons(){
		return authorizedPersonDao.getAll();
	}
}
