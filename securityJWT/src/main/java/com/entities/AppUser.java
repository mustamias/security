package com.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;


@Entity
public class AppUser {

	@Id  @GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(unique = true)
	private String username;
	private String password;
	private boolean activated;
	@ManyToMany(fetch = FetchType.EAGER)
	private List<AppRole> roles = new ArrayList<>();
	public AppUser(Long id, String username, String password, boolean activated, List<AppRole> roles) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.activated = activated;
		this.roles = roles;
	}
	public AppUser() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "AppUser [id=" + id + ", username=" + username + ", password=" + password + ", activated=" + activated
				+ ", roles=" + roles + "]";
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isActivated() {
		return activated;
	}
	public void setActivated(boolean activated) {
		this.activated = activated;
	}
	public List<AppRole> getRoles() {
		return roles;
	}
	public void setRoles(List<AppRole> roles) {
		this.roles = roles;
	}
	
	
}
