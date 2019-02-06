package com;

import java.util.ArrayList;
import java.util.stream.Stream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.entities.AppRole;
import com.entities.AppUser;
import com.service.AccountService;

@SpringBootApplication
public class SecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecurityApplication.class, args);
	}
	@Bean
	CommandLineRunner start(AccountService accountService) {
		return args->{
			accountService.addRole(new AppRole(null, "USER"));
			accountService.addRole(new AppRole(null, "ADMIN"));
			Stream.of("user1","user2","user3","admin1").forEach(u-> {
				accountService.saveUser(new AppUser(null, u, "1234", true, new ArrayList<>()));
			});
			
			accountService.addRoleToUser("admin1", "ADMIN");
		};
	}
	
	@Bean
	BCryptPasswordEncoder getBCPE( ) {
		return new BCryptPasswordEncoder();
	}

}

