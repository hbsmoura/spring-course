package com.hbsmoura.springcourse.security;

import java.util.Calendar;
import java.util.List;

import org.springframework.stereotype.Component;

import com.hbsmoura.springcourse.constant.SecurityConstants;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWTManager {

	public String createToken(String email, List<String> roles) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, SecurityConstants.JWT_EXP_DAYS);
		
		String jwt = Jwts.builder()
						 .setSubject(email)
						 .setExpiration(calendar.getTime())
						 .claim(SecurityConstants.JWT_ROLE_KEY, roles)
						 .signWith(SignatureAlgorithm.HS512, SecurityConstants.API_KEY.getBytes())
						 .compact();
		
		return jwt;
	}
}
