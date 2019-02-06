package com.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.entities.AppUser;
import com.service.AccountService;

@RestController
public class UserController {
	@Autowired
	private AccountService accountService;
	
	
	@PostMapping("/register")
	public AppUser register(@RequestBody AppUser user) {
		
		return accountService.saveUser(user);
	}
	
	@PostMapping("/login")
	public AppUser login(@RequestBody AppUser user) {
		
		return accountService.saveUser(user);
	}

}
