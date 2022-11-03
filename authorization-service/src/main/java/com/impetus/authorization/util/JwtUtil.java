package com.impetus.authorization.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JwtUtil {

	@Value("${jwt.secret}}")
	private String secret;

	@Value("${jwt.validity}")
	private int validity;

	public String getUsernameFromToken(String token) {
		String username= getClaimFromToken(token, Claims::getSubject);
		log.debug("username is : "+username);
		return username;
	}

	public Date getExpirationDateFromToken(String token) {
		return getClaimFromToken(token, Claims::getExpiration);
	}

	public <T> T getClaimFromToken(String token, Function<Claims, T> claimResolver) {
		final Claims claims = getAllClaimsFromToken(token);
		return claimResolver.apply(claims);
	}

	private Claims getAllClaimsFromToken(String token) {
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
	}

	public String generateToken(UserDetails userDetails) {
		Map<String, Object> claims = new HashMap<>();
		String token= doGenerateToken(claims, userDetails.getUsername());
		log.debug("token is : -"+token);
		return token;
	}

	private String doGenerateToken(Map<String, Object> claims, String subject) {
		return Jwts.builder().setClaims(claims).setSubject(subject)
				.setExpiration(new Date(System.currentTimeMillis() + validity * 1000))
				.signWith(SignatureAlgorithm.HS512, secret).compact();
	}

	public boolean validateToken(String token, UserDetails userDetails) {
		final String username = getUsernameFromToken(token);
		boolean validated=username.equals(userDetails.getUsername()) && !isTokenExpired(token) && userDetails.isEnabled();
		log.debug("token  validation is : "+validated);
		return validated;
	}

	private boolean isTokenExpired(String token) {
		final Date expiration = getExpirationDateFromToken(token);
		boolean ans= expiration.before(new Date());
		log.debug("token expiration status : "+ans);
		return ans;
	}
}

