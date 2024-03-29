package com.exelcia.webapi.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.exelcia.webapi.model.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;

@Component
public class JwtTokenProvider {
	
	@Value("${app.security_key}")
	private String secret;
	
	@Value("${app.security_time}")
	private int expiration;
	
	public String generateToken(Authentication authentication) {
		User user =  (User)authentication.getPrincipal();
			
		Date expireDate = new Date(System.currentTimeMillis() +expiration * 1000 );
		
		return Jwts.builder()
				.setSubject(Long.toString(user.getId()))
				.setIssuedAt(new Date())
				.setExpiration(expireDate)
				.signWith(SignatureAlgorithm.HS512, secret)
				.compact();
	}
	
	
	public Long getUserIdFromJwt(String token) {
		Claims claims = Jwts.parser()
							.setSigningKey(secret)
							.parseClaimsJws(token)
							.getBody();
		return Long.parseLong(claims.getSubject());
	}
	
	public boolean isValidateToken(String token) {
		try {
			Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
			return true;
		
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return false;
		
	}

}
