package com.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.entities.AppUser;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private AuthenticationManager authenticationManager;

	public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
		super();
		this.authenticationManager = authenticationManager;
	}
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		// TODO Auto-generated method stub
		AppUser appuser = null;
		try {
			appuser = new ObjectMapper().readValue(request.getInputStream(), AppUser.class);
			System.out.println(appuser.getUsername());
			System.out.println(appuser.getPassword());
			return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(appuser.getUsername(), appuser.getPassword()));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("problem req cont " +e);
		}
		
	}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request,
			HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		// TODO Auto-generated method stub
		User user = (User) authResult.getPrincipal();
		List<String> roles = new ArrayList<> ();
		user.getAuthorities().forEach(r -> {
			roles.add(r.getAuthority());
		});
		System.out.println("build jwt");
		String jwt= JWT.create()
				.withIssuer(request.getRequestURI())
				.withSubject(user.getUsername())
				.withArrayClaim("roles", roles.toArray(new String[roles.size()]))
				.withExpiresAt(new Date(System.currentTimeMillis()+ SecurityParms.EXPERATION))
				.sign(Algorithm.HMAC256(SecurityParms.SECRET));
		response.addHeader("Authorization",jwt);
		System.out.println(" jwt = " + jwt);

		//super.successfulAuthentication(request, response, chain, authResult);
	}

}
