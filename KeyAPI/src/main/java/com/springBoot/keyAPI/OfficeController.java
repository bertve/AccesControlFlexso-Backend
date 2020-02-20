package com.springBoot.keyAPI;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import domain.*;
import service.OfficeDao;

@RestController
@RequestMapping(value="/api/office")
public class OfficeController {
	
	@Autowired
	private OfficeDao officeDao;
	
	@GetMapping(value="/getAll")
	public List<Office> getAllOffices(){
		return officeDao.getAll();
	}
}
