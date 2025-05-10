package com.vital_essence.validation.util;

import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;

import org.springframework.beans.factory.annotation.Value;

@Component
public class JwtUtil {
	@Value("${jwt.secret:mudarissoemprod}")
	private String secret;
	
	@Value("${jwt.expiration:86400000}")
	private Long expiration;
	
	@Value("${jwt.expiration:604800000}")
	private Long rememberMeExpiration;
	
	private Key getSigningKey() {
		var keyBytes = secret.getBytes(StandardCharsets.UTF_8);
		return Keys.hmacShaKeyFor(keyBytes);
	}
}