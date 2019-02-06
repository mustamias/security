package com.security;

public interface SecurityParms {
	public static final String HEADE_NAME= "Authorization";
	public static final String SECRET= "secret";
	public static final long EXPERATION = 1*24*3600;
	public static final String HEADER_PREFIX= "Bearer ";


}
