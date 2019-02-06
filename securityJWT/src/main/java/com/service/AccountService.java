package com.service;

import com.entities.AppRole;
import com.entities.AppUser;

public interface AccountService {
	
	public AppUser saveUser(AppUser user);
	public AppRole addRole(AppRole role);
	public AppUser loaadUserByUsername(String userName);
	public AppUser addRoleToUser(String username, String rolename);
}
