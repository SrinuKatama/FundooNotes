package com.bridgelabz.fundoonotes.utility;

// Author :Srinu


import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JWTutil
{
	private String SECRET_KEY="12345";
	
	public String jwtTockenGenerate(String useremail)
	{
		Map<String,Object> claims=new HashMap<>();
		return createJwtToken(claims,useremail) ;
		
	}

	// For Encoding
	
	private String createJwtToken(Map<String, Object> claims, String subject) 
	{
		return Jwts.builder().setClaims(claims).
				setSubject(subject).
				setIssuedAt(new Date(System.currentTimeMillis())).
				setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)).
				signWith(SignatureAlgorithm.HS256, SECRET_KEY).
				compact();
	}
	
	
	//For Decoding
	
	public String parse(String token) 
	{
		boolean isValid = false;
		String email = null;
		if (token != null) 
		{
			Claims claims = Jwts.parser().
					setSigningKey(SECRET_KEY).
					parseClaimsJws(token).
					getBody();
			email = claims.getSubject();
			isValid = claims.getExpiration().after(Date.from(Instant.now()));
		}
		if (isValid) 
		{
			return email;
		} else 
		{
			return null;
		}
	}

}
