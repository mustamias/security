package com.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.dao.AppRoleRepository;
import com.dao.AppUserRepository;
import com.entities.AppRole;
import com.entities.AppUser;

@Service
public class AccountServiceImpl implements AccountService {
	
	
	private AppUserRepository appUserRepository;
	
	private AppRoleRepository appRoleRepository;
	
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public AccountServiceImpl(AppUserRepository appUserRepository, AppRoleRepository appRoleRepository,
			BCryptPasswordEncoder bCryptPasswordEncoder) {
		super();
		this.appUserRepository = appUserRepository;
		this.appRoleRepository = appRoleRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	@Override
	public AppUser saveUser(AppUser user) {
		// TODO Auto-generated method stub
		
		AppUser user1 = appUserRepository.findByusername(user.getUsername());
		if(user1 != null) throw new RuntimeException("user Existe");
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setActivated(true);
		user1 = appUserRepository.save(user);
		return addRoleToUser(user.getUsername(), "USER");
	}

	@Override
	public AppRole addRole(AppRole role) {
		// TODO Auto-generated method stub
		return appRoleRepository.save(role);
	}

	@Override
	public AppUser loaadUserByUsername(String userName) {
		// TODO Auto-generated method stub
		return appUserRepository.findByusername(userName);
	}

	@Override
	public AppUser addRoleToUser(String username, String rolename) {
		// TODO Auto-generated method stub
		AppUser user = appUserRepository.findByusername(username);
		AppRole role = appRoleRepository.findByRoleName(rolename);
		user.getRoles().add(role);
		return appUserRepository.save(user);
		
	}

}
